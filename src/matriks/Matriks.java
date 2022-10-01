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
	public Matriks() {
		mat = new double[1][1];
		rowCnt = 1;
		colCnt = 1;
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
				System.out.printf("%.2f",getMat(i, j));
			}
			if(i == getRow() - 1) System.out.print("]");
			else System.out.println("],");
		}
		System.out.println("]");
	}
	
	public void read(Scanner s) {
		/* Membaca sebuah matriks dari keyboard
		 * Dimulai dengan membaca jumlah baris dan jumlah kolom
		 * TODO : perbaiki metode read sesuai permintaan tugas
		 */
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
	
	public static Matriks readFile(String file) {
		/* Membaca matriks dari file eksternal*/
		Matriks m = new Matriks();
		try {
			File namafile = new File(file);
			Scanner scanFile = new Scanner(namafile);
			int baris = 0;
			int total = 0;
		    //cek banyak baris
		    while(scanFile.hasNextLine()){
			baris++;
			scanFile.nextLine();
		    }
		    scanFile.close();
		    //cek total matriks
		    Scanner sum = new Scanner(namafile);
		    while(sum.hasNextDouble()){
			total++;
			sum.nextDouble();
		    }
		    sum.close();

		    m = new Matriks(baris, total/baris); // kolom = total/baris

		    Scanner input = new Scanner(namafile);
		    for(int i = 0; i < m.getRow(); i++){
			for(int j = 0; j < m.getCol(); j++){
			    if(input.hasNextDouble()){
				m.mat[i][j] = input.nextDouble();
			    }
			}
		    }
		    input.close(); 
		} catch (Exception e) {
				System.out.println("ERROR :" + e);
		}
		return m;
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
		assert(getCol() == m2.getRow());
		Matriks ret = new Matriks(getRow(), m2.getCol());
		for(int i = 0; i < ret.getRow(); i++) {
			for(int j = 0; j < ret.getCol(); j++) {
				ret.mat[i][j] = 0;
				for(int k = 0; k < getRow(); k++) {
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
	
	public Matriks transpose() {
		/* Mengembalikan matriks transpose */
		/* I.S. matriks terdefinisi */
		/* F.S. elemen mat[i][j]= mat[j][i] */
		Matriks tr = new Matriks(getCol(), getRow());
		for (int i = 0; i < rowCnt; i++){
			for (int j = 0; j < colCnt; j++) {
				tr.mat[j][i] = mat[i][j];
			}
		}
		return tr;
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
		/* I.S. Matriks terdefinisi
		 * F.S. melakukan eliminasi gauss pada matriks
		/* Langkah untuk tiap baris i: 
		 * 1. Tukar baris jika getMat(i, i) == 0
		 * 2. Bagi satu baris sehingga getMat(i, i) == 1
		 * 3. Buat seluruh getMat(k, satuUtama) == 0 untuk setiap k > i
		 */
		int satuUtamaPos = 0;
		for(int i = 0; i < getRow(); i++) {
			if(satuUtamaPos >= getCol()) break;
			
			if(getMat(i, satuUtamaPos) == 0) {
				for(int k = i+1; k < getRow(); k++) {
					if(getMat(k, satuUtamaPos) != 0) {
						// swap baris i dan k
						tukarBaris(i, k);
						break;
					}
				}
			}
			// kalau semua nilainya 0, naikkan posisi satu utama & cont
			if(getMat(i, satuUtamaPos) == 0) {
				satuUtamaPos++;
				continue;
			}
			// bagi baris ke-i dengan nilai getMat(i, satuUtamaPos)
			double pembagi = getMat(i, satuUtamaPos);
			kaliBaris(i, 1.0/pembagi);
			// kurangkan seluruh baris sehingga getMat(k, satuUtamaPos) == 0
			for(int k = i+1; k < getRow(); k++) {
				double pengali = getMat(k, satuUtamaPos);
				tambahBaris(k, i, -pengali);
			}
			
			satuUtamaPos++;
		}
		
	}
	
	public void gaussJordanElimination() {
		/* I.S. Matriks terdefinisi
		 * F.S. melakukan eliminasi gauss jordan pada matriks
		/* Langkah untuk tiap baris i: 
		 * 1. Tukar baris jika getMat(i, i) == 0
		 * 2. Bagi satu baris sehingga getMat(i, i) == 1
		 * 3. Buat seluruh getMat(k, satuUtama) == 0 untuk setiap k != i
		 */
		int satuUtamaPos = 0;
		for(int i = 0; i < getRow(); i++) {
			if(satuUtamaPos >= getCol()) break;
			
			if(getMat(i, satuUtamaPos) == 0) {
				for(int k = i+1; k < getRow(); k++) {
					if(getMat(k, satuUtamaPos) != 0) {
						// swap baris i dan k
						tukarBaris(i, k);
						break;
					}
				}
			}
			// kalau semua nilainya 0, naikkan posisi satu utama & cont
			if(getMat(i, satuUtamaPos) == 0) {
				satuUtamaPos++;
				continue;
			}
			// bagi baris ke-i dengan nilai getMat(i, satuUtamaPos)
			double pembagi = getMat(i, satuUtamaPos);
			kaliBaris(i, 1.0/pembagi);
			// kurangkan seluruh baris sehingga getMat(k, satuUtamaPos) == 0
			for(int k = 0; k < getRow(); k++) {
				if(i == k) continue;
				double pengali = getMat(k, satuUtamaPos);
				tambahBaris(k, i, -pengali);
			}
			
			satuUtamaPos++;
		}
	}
	
	public void inverseMethod() {
		/* Penyelesaian SPL menggunakan metode invers */
		/* Ax = B, x = A^(-1)B */
		
		Matriks A = new Matriks(rowCnt, colCnt-1); // matriks A
		Matriks x = new Matriks(rowCnt, 1); // matriks x
		Matriks B = new Matriks(rowCnt, 1); // matriks B
		
		if ((rowCnt == colCnt-1)) {	//ukuran matriks sesuai
			for (int i = 0; i < rowCnt; i++) {
				B.mat[i][0] = mat[i][colCnt-1];
			}
			for (int i = 0; i< rowCnt; i++) {
				for (int j = 0; j<colCnt-1; j++) {
					A.mat[i][j] = mat[i][j];
				}
			}
			if (A.determinant() != 0) {
				x =  A.inverse().mul(B);	
				for (int i = 0; i< A.getRow(); i++) {
					System.out.printf("x%d = %.4f\n", i+1, x.getMat(i, 0));
				}
			} else {
				System.out.println("Solusi tidak dapat ditentukan");
			}
		} else {
			System.out.println("Solusi tidak dapat ditentukan");
		}
	}
	
	public void cramer() {
		/*Penyelesaian SPL menggunakan metode cramer*/
		
		Matriks A = new Matriks(getRow(), getCol()-1); 
		Matriks B = new Matriks(getRow(), 1);
		Matriks ret = new Matriks (getRow(), 1);
		int i,j;	
		
		if (rowCnt == colCnt-1)  { //ukuran matriks sesuai
			for (i = 0; i < A.getRow(); i++) {
				for (j = 0; j < A.getCol(); j++) {
					A.setMat(i, j, getMat(i, j));
				}
			}

			for (i = 0; i < A.getRow(); i++) {
				B.setMat(i, 0, getMat(i, getCol()-1));
			}
			
			double det = A.determinant(); 
			
			if (det != 0) {
				for (i = 0; i< A.getRow(); i++) {
					for (int row2 = 0; row2 < getRow(); row2++) {  //copying nxn matrix 
						for (int col2 = 0; col2 < getCol()-1; col2++) {
							A.setMat(row2, col2, getMat(row2, col2));
						}
					}
					
					for (j = 0; j < getRow(); j++) {
						A.setMat(j, i, B.getMat(j, 0)); //substituting LastCol
					}
					
					ret.setMat(i, 0, A.determinant()/det);
				}
				for (i = 0; i< A.getRow(); i++) {
					System.out.printf("x%d = %.4f\n", i+1, ret.getMat(i, 0));
				}
			} else {
				System.out.println("Solusi tidak dapat ditentukan");
			}
		} else {
			System.out.println("Solusi tidak dapat ditentukan");
		}
	}
	
	public Matriks cramerMtx() {
		/*Penyelesaian SPL menggunakan metode cramer*/
		/*return dalam bentuk matriks*/
		Matriks A = new Matriks(getRow(), getCol()-1); 
		Matriks B = new Matriks(getRow(), 1);
		Matriks ret = new Matriks (getRow(), 1);
		int i,j;	
		if (rowCnt == colCnt-1)  { //ukuran matriks sesuai
			for (i = 0; i < A.getRow(); i++) {
				for (j = 0; j < A.getCol(); j++) {
					A.setMat(i, j, getMat(i, j));
				}
			}
			for (i = 0; i < A.getRow(); i++) {
				B.setMat(i, 0, getMat(i, getCol()-1));
			}
			double det = A.determinant(); 
			if (det != 0) {
				for (i = 0; i< A.getRow(); i++) {
					for (int row2 = 0; row2 < getRow(); row2++) {  //copying nxn matrix 
						for (int col2 = 0; col2 < getCol()-1; col2++) {
							A.setMat(row2, col2, getMat(row2, col2));
						}
					}
					for (j = 0; j < getRow(); j++) {
						A.setMat(j, i, B.getMat(j, 0)); //substituting LastCol
					}
					ret.setMat(i, 0, A.determinant()/det);
				}	
			}
		}
		return ret;
	}
	
	public double determinant() {
		/* Mencari hasil determinan sebuah matriks */
		/* Prekondisi: matriks terdefinisi berukuran nxn */
		double ans = 0;
		Matriks temp = new Matriks(getRow()-1, getCol()-1);
		int sign, i, j,k;
		sign = 1;
		
		if (getRow() == 1) {
			ans = (double) getMat(0, 0);
		} else {
			for(i=0; i<getRow(); i++) {
				for(j=1; j<getRow(); j++) {
					for (k=0; k< getRow(); k++) {
						if (k<i) {
							temp.setMat(j-1, k, getMat(j, k));
							
						} else if (k>i) {
							temp.setMat(j-1, k-1,getMat(j, k));
						}
					}
				}
				ans += sign*getMat(0, i)* temp.determinant();
				sign = -sign;
			}
		}
		return ans;
	}
	
	public double determinanReduksiBaris() {
		Matriks tempMtx = new Matriks(getRow(), getCol());
		
		for (int g = 0; g < getRow(); g++) {
			for (int h = 0; h < getCol(); h++) {
				tempMtx.setMat(g, h, getMat(g, h));
			}
		}
		
		double t = 0;
		for (int i = 0; i < getRow() - 1; i++) {
			if (getMat(i, 0) == 0) {
				tempMtx.tukarBaris(i, i + 1);
				t += 1;
			}
		} 
		int x = 0, y = 0;
		for (int a = 1; a < getRow() ; a++) {
			for (int b = 1; b < (getRow() - (a - 1)); b++) {
				double p = tempMtx.getMat(b + x, y) / tempMtx.getMat(x, y);
				if (((getMat(b + x, x) >= 0) && (p * getMat(x, x) >= 0)) || ((getMat(b + x, x) <= 0) && (p * getMat(x, x) <= 0))) {
					for (int c = 0; c < getCol() - (a - 1) ; c++) {
						tempMtx.setMat(b + x, c + x, ((tempMtx.getMat(b + x, c + x)) - (p * (tempMtx.getMat(x, c + x)))));
					}
				}
				else {	
					for (int c = 0; c < getCol() - (a - 1) ; c++) {
						tempMtx.setMat(b + x, c + x, ((tempMtx.getMat(b + x, c)) + (p * (tempMtx.getMat(x, c)))));
					}
				}
					
				}
			x += 1;
			y += 1;
			}
		
		double z = 1;
		for (int d = 1; d < getRow(); d++) {
			z *= (tempMtx.getMat(d, d));
		}
		return tempMtx.getMat(0, 0) * z * Math.pow((-1), t);		
	}
	
	public double cofactorElmt(int p, int q) {
		/* Menghitung cofactor tiap elemen pada matriks */
		/* Prekondisi: matriks berukuran nxn */
	
		Matriks temp = new Matriks(getRow()-1, getCol()-1);
		
		int row = 0;
		int col = 0;
		for (int i = 0; i< getRow()-1; i++) {
			if (i == p) {
				row++;
			}
			col = 0;
			for (int j = 0; j< getCol()-1; j++ ) {
				if (j == q) {
					col++;
				}
				temp.mat[i][j] = mat[row][col]; 
				col++;
			}
			row ++;
		}	
		return temp.determinant();
	}
	
	public Matriks cofactorMtx() {
		/* Mengembalikan cofactor matriks */
		/* Prekondisi: matriks terdefinisi berukuran nxn */
		Matriks cf = new Matriks(rowCnt, colCnt);
		for (int i = 0; i<rowCnt; i++) {
			for(int j = 0; j<colCnt; j++) {
				cf.mat[i][j]= Math.pow((-1), j+i)*cofactorElmt(i, j);
			}
		}
		return cf;
	}
	
	public Matriks adjoin() {
		Matriks adj = new Matriks(rowCnt, colCnt);
		adj = cofactorMtx().transpose();
		return adj;
	}
	
	public Matriks inverse() {
		Matriks inv = new Matriks(rowCnt, colCnt);
		if (determinant() == 0) {
			inv = null;
		} else {
			inv = adjoin().mul(1/(determinant()));
		}
		return inv;
	}
	
	public Matriks inverseByAugment() {
		Matriks inv = new Matriks(getRow(), getCol() * 2);
		for(int i = 0; i < getRow(); i++) {
			for(int j = 0; j < getCol(); j++) {
				inv.setMat(i, j, getMat(i, j));
				if(i == j) {
					inv.setMat(i, j + getCol(), 1);
				} else {
					inv.setMat(i, j + getCol(), 0);
				}
				
			}
		}
		
		inv.gaussJordanElimination();
		Matriks ret = new Matriks(getRow(), getCol());
		for(int i = 0; i < getRow(); i++) {
			for(int j = 0; j < getCol(); j++) {
				ret.setMat(i, j, inv.getMat(i, j + getCol()));
			}
		}
		
		return ret;
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
