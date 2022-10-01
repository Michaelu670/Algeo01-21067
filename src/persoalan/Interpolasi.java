package persoalan;

import java.util.Scanner;

import matriks.Matriks;

public class Interpolasi {
	
	public static void interpolasi(Scanner s) {
		System.out.printf("Jumlah titik: ");
		int row = s.nextInt();
		Matriks m = new Matriks(row, 2);
		System.out.print("Nilai yang dicari: ");
		double x = s.nextDouble();
		
		System.out.println("Input: ");
		for (int i = 0; i<row; i++) {
			for (int j = 0; j<2; j++) {
				m.setMat(i, j, s.nextDouble());
			}
		}
		interpolasi(x, m);
	}
	
	private static Matriks specialGaussJordan(Matriks m) {
		/* PENTING : m harus bisa di-inverse
		 * Mengembalikan penyelesaian spl dengan metode gauss jordan
		 */
		m.print();
		m.gaussJordanElimination();
		m.print();
		Matriks ret = new Matriks(m.getRow(), 1);
		for(int i = 0; i < m.getRow(); i++) {
			ret.setMat(i, 0, m.getMat(i, m.getCol() - 1));
		}
		return ret;
	}
	
	public static void interpolasi(double x, Matriks m ) {
		Matriks mtx = new Matriks(m.getRow(), m.getRow()+1);
		double ans = 0;
		
		for (int i = 0; i < mtx.getRow(); i++) {
			for (int j = 0; j < mtx.getCol(); j++) {
				if (j == mtx.getCol()-1) {
					mtx.setMat(i, j, m.getMat(i, 1));
				} else {
					mtx.setMat(i, j, Math.pow(m.getMat(i, 0), j));
				}
			}
		}
		mtx = specialGaussJordan(mtx);
		
		for (int i = 0; i< mtx.getRow(); i++) {
			ans += mtx.getMat(i, 0) * Math.pow(x, i);
		}
		
		System.out.printf("f(x)=");
		
		for (int i = mtx.getRow()-1; i>=0; i--) {
			Character sign = '+';
			if (mtx.getMat(i, 0) < 0) {
				sign = ' ';
			}
			
			if (i == 0) {
				System.out.printf(" %c%.4f", sign, mtx.getMat(i,0), mtx.getRow()-1);
			} else if (i == mtx.getRow()-1){
				System.out.printf(" %.4fx^%d", mtx.getMat(i, 0), mtx.getRow()-1);
			} else if (i == 1){
				System.out.printf(" %c%.4fx", sign, mtx.getMat(i, 0));
			}else {
				System.out.printf(" %c%.4fx^%d", sign, mtx.getMat(i, 0), i);
			}
		}
		
		System.out.printf("\nf(%.2f)= %.4f", x, ans);
	}

	
}
