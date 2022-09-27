package persoalan;

import java.util.Scanner;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;

import matriks.Matriks;

public class Bicubic {
	
	/* STRING PARSE	 */
	public static double nextInt(String[] s) {
		int len = 0;
		double ret = 0;
		for(int i = 0; i < s[0].length() && s[0].charAt(i) != ' '; i++) {
			len++;
		}
		ret = Double.parseDouble(s[0].substring(0, len));
		s[0] = s[0].substring(Math.min(len + 1, s[0].length()));
		return ret;
	}
	
	/* FUNGSI BICUBIC INTERPOLATION	 */
	public static void bicubicInterpolation(Scanner s) {
		/* I.S. s terdefinisi dan mengambil input dari keyboard
		 * F.S. mengembalikan nilai bicubic interpolation pada titik yang diminta
		 */
		Matriks f = new Matriks(16, 1);
		for(int i = 0; i < 16; i++) {
			f.setMat(i, 0, s.nextDouble());
		}
		double tx, ty;
		tx = s.nextDouble();
		ty = s.nextDouble();
		bicubicInterpolation(f, tx, ty);
	}
	
	public static void bicubicInterpolation(String sourceFile) {
		/* I.S. sourceFile terdefinisi
		 * F.S. jika sourceFile merupakan path valid ke suatu file txt,
		 * 		mengembalikan nilai bicubic interpolation.
		 * 		jika tidak, mengembalikan null
		 * */
		Matriks f = new Matriks(16, 1);
		double tx = 5, ty = 5;
		try {
			File src = new File(sourceFile);
			Scanner scannerFile = new Scanner(src);
			int itr = 0;
			while(scannerFile.hasNextLine()) {
				String[] data = { scannerFile.nextLine() };
				while(!data[0].isBlank()) {
					if(itr >= 16) {
						tx = nextInt(data);
						ty = nextInt(data);
					}
					else {
						f.setMat(itr, 0, nextInt(data));
						itr++;
					}
					
				}
			}
			scannerFile.close();
		} catch (FileNotFoundException e) {
			System.out.print("Error!\n");
			e.printStackTrace();
			return;
		}
		bicubicInterpolation(f, tx, ty);
	}
	
	public static void bicubicInterpolation(Matriks f, double tx, double ty) {
		/* I.S. f terdefinisi matriks 16x1
		 * 		tx, ty terdefinisi dan 0 <= tx, ty <= 1
		 * F.S. output hasil interpolasi pada (tx, ty) 
		 * TODO file output
		 */
		Matriks a = mult().mul(f);
		double ans = 0;
		for(int i = 0; i <= 3; i++) {
			for(int j = 0; j <= 3; j++) {
				ans += a.getMat(i * 4 + j, 0) * Math.pow(tx, i) * Math.pow(ty, j);
			}
		}
		
		System.out.println("f(" + tx + " , " + ty + ") = " + ans);
	}
	
	public static double bicubicInterpolation(Matriks f, double tx, double ty, Matriks a) {
		double ans = 0;
		a = a.mul(f);
		for(int i = 0; i <= 3; i++) {
			for(int j = 0; j <= 3; j++) {
				ans += a.getMat(i * 4 + j, 0) * Math.pow(tx, i) * Math.pow(ty, j);
			}
		}
		return ans;
	}
	
	/* Matriks inverse(X) sebagai pengali */
	public static final Matriks mult() {
		Matriks ret = new Matriks(16, 16);
		for(int x = -1; x <= 2; x++) {
			for(int y = -1; y <= 2; y++) {
				for(int i = 0; i <= 3; i++) {
					for(int j = 0; j <= 3; j++) {
						ret.setMat((x+1)*4+(y+1), i*4+j, Math.pow(x, i) * Math.pow(y, j));
					}
				}
			}
		}
		
		return ret.inverseByAugment();
	}
	
	/* BONUS */
	public static void zoomImg(String sourceFile, String destFile) {
		BufferedImage img = null;
		File f = null;
		
		try {
			f = new File(sourceFile);
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
			return;
		}
		
		Matriks a = mult();
		
		BufferedImage imgZoom = new BufferedImage(img.getWidth() * 2, img.getHeight() * 2, BufferedImage.TYPE_INT_RGB);
		imgZoom.createGraphics();
		for(int i = 0; i < img.getWidth(); i++) {
			for(int j = 0; j < img.getHeight(); j++) {
				Matriks mr = new Matriks(16, 1);
				Matriks mg = new Matriks(16, 1);
				Matriks mb = new Matriks(16, 1);
				
				int idx = 0;
				for(int ii = i - 1; ii <= i + 2; ii++) {
					for(int jj = j - 1; jj <= j + 2; jj++) {
						int idx_i = ii, idx_j = jj;
						idx_i = Math.max(idx_i, 0);
						idx_i = Math.min(idx_i, img.getWidth() - 1);
						idx_j = Math.max(idx_j, 0);
						idx_j = Math.min(idx_j, img.getHeight() - 1);
						Color c = new Color( img.getRGB(idx_i, idx_j) );
						mr.setMat(idx, 0, c.getRed());
						mg.setMat(idx, 0, c.getGreen());
						mb.setMat(idx, 0, c.getBlue());
						idx++;
					}
				}
				for(int ii = 0; ii < 2; ii++) {
					for(int jj = 0; jj < 2; jj++) {
						Color c = new Color(
								Math.max( Math.min( (int)bicubicInterpolation(mr, (double)ii/2.0, (double)jj/2.0, a), 255), 0),
								Math.max( Math.min( (int)bicubicInterpolation(mg, (double)ii/2.0, (double)jj/2.0, a), 255), 0),
								Math.max( Math.min( (int)bicubicInterpolation(mb, (double)ii/2.0, (double)jj/2.0, a), 255), 0));
						imgZoom.setRGB(i*2 + ii, j*2 + jj, c.getRGB());
					}
				}
				
				
			}
		}
		
		try {
			File output_file = new File(destFile);
			ImageIO.write(imgZoom, "jpg", output_file);
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
