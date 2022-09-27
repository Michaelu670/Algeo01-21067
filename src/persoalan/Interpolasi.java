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
	
	public static void interpolasi(double x, Matriks m ) {
		Matriks mtx = new Matriks(m.getRow(), m.getRow()+1);
		float ans = 0;
		
		for (int i = 0; i < mtx.getRow(); i++) {
			for (int j = 0; j < mtx.getCol(); j++) {
				if (j == mtx.getCol()-1) {
					mtx.setMat(i, j, m.getMat(i, 1));
				} else {
					mtx.setMat(i, j, Math.pow(m.getMat(i, 0), j));
				}
			}
		}
		mtx.print();
		mtx = Matriks.cramer(mtx);
		mtx.print();
		
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
