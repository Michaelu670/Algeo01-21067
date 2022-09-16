package matriks;

import java.util.Scanner;

public class Matriks {
	double[][] mat = null;
	int rowCnt, colCnt;
	
	/* ***CONSTRUCTOR*** */
	public Matriks(int rowSz, int colSz) {
		mat = new double[rowSz][colSz];
		rowCnt = rowSz;
		colCnt = colSz;
	}
	
	/* ***BACA/TULIS*** */
	public void print() {
		/*	Print matriks */
		System.out.println("MATRIKS " + rowCnt + "*" + colCnt);
		System.out.print("[");
		for(int i = 0; i < getRow(); i++) {
			System.out.print("[");
			for(int j = 0; j < getCol(); j++) {
				if(j > 0) System.out.print(",");
				System.out.print(getMat(i, j));
			}
			if(i == getRow() - 1) System.out.print("]");
			else System.out.println("],");
		}
		System.out.println("]");
	}
	
	public void read() {
		/* Membaca sebuah matriks dari keyboard
		 * Dimulai dengan membaca jumlah baris dan jumlah kolom
		 * TODO : perbaiki metode read sesuai permintaan tugas
		 */
		Scanner s = new Scanner(System.in);
		System.out.print("Jumlah baris: ");
		rowCnt = s.nextInt();
		System.out.print("Jumlah kolom: ");
		colCnt = s.nextInt();
		mat = new double[rowCnt][colCnt];
		System.out.println("Matriks:");
		for(int i = 0; i < getRow(); i++) {
			for(int j = 0; j < getCol(); j++) {
				mat[i][j] = s.nextDouble();
			}
		}
		s.close();
	}
	/* ***SELECTOR*** */
	public double getMat(int r, int c) {
		return mat[r][c];
	}
	public int getRow() {
		return rowCnt;
	}
	public int getCol() {
		return colCnt;
	}
	
	public void setMat(int r, int c, double newVal) {
		mat[r][c] = newVal;
	}
	public void setRow(int newVal) {
		rowCnt = newVal;
	}
	public void setCol(int newVal) {
		colCnt = newVal;
	}
	
	/* ***PRIMITIF MATRIKS*** */
	public Matriks mul(Matriks m2) {
		/* Mengembalikan hasil perkalian matriks self dengan matriks m2 */
		/* Prakondisi : jumlah baris m = jumlah kolom m2 */
		assert(colCnt == m2.rowCnt);
		Matriks ret = new Matriks(rowCnt, m2.colCnt);
		for(int i = 0; i < rowCnt; i++) {
			for(int j = 0; j < colCnt; j++) {
				ret.mat[i][j] = 0;
				for(int k = 0; k < colCnt; k++) {
					ret.mat[i][j] += mat[i][k] * m2.mat[k][j];
				}
			}
		}
		return ret;
	}
	
	public Matriks mul(double pengali) {
		/* Mengalikan matriks dengan konstan pengali */
		/* I.S. pengali terdefinisi */
		/* F.S. matriks dikali dengan pengali */
		Matriks ret = this;
		for(int i = 0; i < getRow(); i++) {
			for(int j = 0; j < getCol(); j++) {
				ret.mat[i][j] *= pengali;
			}
		}
		return ret;
	}
	
	
	/* OBE */
	public void tukarBaris(int baris1, int baris2) {
		/* Swap tiap elemen dari baris1 dan baris2 
		 * I.S. baris1 dan baris2 terdefinisi 
		 * F.S. baris1 dan baris2 matriks ditukar */
		for(int i = 0; i < getCol(); i++) {
			double tmp = mat[baris1][i];
			mat[baris1][i] = mat[baris2][i];
			mat[baris2][i] = tmp;
		}
	}
	
	public void kaliBaris(int baris, double pengali) {
		/* Kalikan tiap elemen dari suatu baris dengan konstan pengali
		 * I.S. baris dan pengali terdefinisi 
		 * F.S. seluruh elemen pada baris dikalikan dengan pengali */
		for(int i = 0; i < getCol(); i++) {
			mat[baris][i] *= pengali;
		}
	}
	
	public void tambahBaris(int baris, int barisPenambah, double pengali) {
		/* Menambah tiap elemen baris dengan pengali*barisPenambah */
		/* I.S. baris, barisPenambah terdefinisi dan nilainya diantara [0, colCnt) 
		 * 		pengali terdefinisi
		 * F.S. tiap elemen pada baris ke-baris ditambah pengali* baris ke-barisPenambah*/
		
		for(int i = 0; i < getCol(); i++) {
			mat[baris][i] += pengali * mat[barisPenambah][i];
		}
	}
	
	/* Metode penyelesaian SPL */
	public void gaussElimintation() {
		
	}
	
	public void gaussJordanElimination() {
		/* Melakukan eliminasi gauss jordan pada matriks */
		/* Langkah: 
		 * 1. 
		 */
	}
	
	public void inverse() {
		
	}
	
	public void determinant() {
		
	}
	
	public void augment() {
		
	}
	
	/* Static */
	public static Matriks one(int sz) {
		Matriks ret = new Matriks(sz, sz);
		for(int i = 0; i < sz; i++) {
			for(int j = 0; j < sz; j++) {
				if(i == j) ret.mat[i][j] = 1.0;
				else ret.mat[i][j] = 0.0;
			}
		}
		return ret;
	}
	
	public static Matriks zero(int sz) {
		Matriks ret = new Matriks(sz, sz);
		for(int i = 0; i < sz; i++) {
			for(int j = 0; j < sz; j++) {
				ret.mat[i][j] = 0.0;
			}
		}
		return ret;
	}
}
