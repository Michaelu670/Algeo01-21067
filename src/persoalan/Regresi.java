package persoalan;
import java.util.Scanner;

import matriks.Matriks;

public class Regresi {

public static void regresiLinierBerganda(Scanner s) {
		
		System.out.print("Masukkan jumlah peubah x : ");
		int n = s.nextInt();

		System.out.print("Masukkan jumlah sampel : ");
		int m = s.nextInt();
		
		System.out.println("Masukkan semua nilai-nilai x dan nilai y dalam bentuk Matriks Augmented :");
		double[][] mat = new double[m][n + 1];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n + 1; j++) {
				mat[i][j] = s.nextDouble();
			}
		}
		Matriks regMtx = new Matriks(m, n + 1);
		Matriks regMtx2 = null;
		Matriks yMtx = new Matriks(m, 1);
		Matriks yMtx2 = null;
		Matriks valB = new Matriks(n+1, n + 2);
		
		for (int g = 0; g < m; g++) {
			for (int h = 0; h < n + 1; h++) {
				if (h == 0) {
					regMtx.setMat(g, h, 1);
				}
				else {
					regMtx.setMat(g, h, mat[g][h - 1]);
				}
			}
		}
		
		for (int y = 0; y < m; y++) {
			yMtx.setMat(y, 0, mat[y][n]);
		}

		regMtx2 = regMtx.transpose().mul(regMtx);
		yMtx2 = regMtx.transpose().mul(yMtx);

		//Normal Estimation Equation for Multiple Linear Regression
		for (int e = 0; e < n + 1; e++) {
			for (int f = 0; f < n + 2; f++) {
				if (f == n + 1) {
					valB.setMat(e, f, yMtx2.getMat(e, 0));
				}
				else {
					valB.setMat(e, f, regMtx2.getMat(e, f));
				}
			}
		}
		
		valB.gaussElimintation();
		
		double[] valb = new double[valB.getCol() - 1];
		for(int i = valB.getCol() - 2; i >= 0; i--) {
			valb[i] = valB.getMat(i, valB.getCol() - 1);
			for(int j = i+1; j < valB.getCol() - 1; j++) {
				valb[i] -= valB.getMat(i, j) * valb[j];
			}
		}
		
		System.out.println();
		System.out.println("Persamaan Regresi :");
		String pers = "f(x) = ";
		for (int i = 0; i < valb.length; i++) {
			if (i == 0) {
				pers += valb[i];
			}
			else if (valb[i] >= 0) {
				pers += " + " + valb[i] + "X" + "[" + i + "]";
			}
			else {
				pers += " - " + Math.abs(valb[i]) + "X" + "[" + i + "]";
			}
			
		}
		System.out.println(pers);
		System.out.println();
		
		double[] valk = new double[n];
		
		
		System.out.println("Masukkan nilai X[k] yang ingin ditaksir :");
		for(int i = 0; i < n; i++) {
			String xk = "- ";
			int j = i + 1;
			xk += "X" + "[" + j + "] : ";
			System.out.print(xk);
			valk[i] = s.nextDouble();
		}
		
		double end = valb[0];
		for(int i = 1; i < n + 1; i++) {
			end += valb[i]*valk[i - 1];
		}
		String valt = "f(";
		for(int i = 0; i < n; i++) {
			if (i < n-1) {
				valt += valk[i] + ", ";
			}
			else {
				valt += valk[i] + ") = ";
			}
		}
		System.out.println();
		System.out.println("Taksiran nilai fungsi pada X[k] yang diberikan :");
		System.out.print(valt);
		System.out.println(end);
	}
	
	
}
