package matriks;

import java.util.Arrays;
import java.util.Scanner;

import driver.Menu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
				System.out.printf("%f",getMat(i, j));
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
	}
	
	public static Matriks readFile(String file) {
		/* Membaca matriks dari file eksternal*/
		Matriks m = new Matriks();
		try {
			File namafile = new File("test/".concat(file));
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
				for(int k = 0; k < getCol(); k++) {
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
	
	public void bagiBaris(int baris, double pembagi) {
		/* Kalikan tiap elemen dari suatu baris dengan konstan pengali
		 * I.S. baris dan pengali terdefinisi 
		 * F.S. seluruh elemen pada baris dikalikan dengan pengali */
		for(int i = 0; i < getCol(); i++) {
			mat[baris][i] = mat[baris][i] / pembagi;
		}
	}
	
	public void tambahBaris(int baris, int barisPenambah, double pengali) {
		/* Menambah tiap elemen baris dengan pengali*barisPenambah */
		/* I.S. baris, barisPenambah terdefinisi dan nilainya diantara [0, colCnt) 
		 * 		pengali terdefinisi
		 * F.S. tiap elemen pada baris ke-baris ditambah pengali* baris ke-barisPenambah*/
		
		for(int i = 0; i < getCol(); i++) {
			mat[baris][i] = mat[baris][i] + pengali * mat[barisPenambah][i];
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
				i--;
				continue;
			}
			// bagi baris ke-i dengan nilai getMat(i, satuUtamaPos)
			double pembagi = getMat(i, satuUtamaPos);
			bagiBaris(i, pembagi);
			// kurangkan seluruh baris sehingga getMat(k, satuUtamaPos) == 0
			for(int k = i+1; k < getRow(); k++) {
				double pengali = getMat(k, satuUtamaPos);
				tambahBaris(k, i, -pengali);
			}
			
			satuUtamaPos++;
		}
		
	}
	
	public void gaussSPL(Scanner s) {
		/* I.S. matriks terdefinisi, memiliki ukuran positif
		 * F.S. mengeluarkan penyelesaian SPL dengan variabel sebanyak jumlah kolom - 1.
		 * 		Dapat berupa solusi tunggal, parametrik, maupun tidak ada solusi.
		 */
		double EPS = 1e-8;
		gaussElimintation();
		String fileOutput = "";
		if(isImpossible()) {
			System.out.println("Persamaan tidak memiliki solusi.");
			return;
		}
		if(isMany()) {
			// harus kerja dari bawah
			System.out.println("Solusi SPL banyak, yaitu:");
			fileOutput = "Solusi SPL banyak, yaitu:\n";
			int[] parameter = new int[getCol() - 1];
			double[][] matSubstitusi = new double[getCol() - 1][getCol()];
			
			for(int i = 0; i < getCol() - 1; i++) {
				parameter[i] = 0;
				Arrays.fill(matSubstitusi[i], 0);
			}
			int itr = 1;
			for(int i = getRow() - 1; i >= 0; i--) {
				int sU = -1;
				for(int j = 0; j < getCol() - 1; j++) {
					if(Math.abs(getMat(i, j)) < EPS) continue;
					if(sU == -1) {
						sU = j;
						parameter[sU] = -1;
						matSubstitusi[sU][getCol() - 1] = getMat(i, getCol() - 1);
					}
					else {
						// jika parameter masih 0 -> jadi parameter
						if(parameter[j] == 0) {
							parameter[j] = itr++;
							matSubstitusi[j][j] = 1;
						}
						
						for(int k = 0; k < getCol(); k++) {
							matSubstitusi[sU][k] -= matSubstitusi[j][k] * getMat(i, j);
						}
					}
				}
				
			}
			
			// cek jika ada variabel belum terpakai
			for(int i = 0; i < getCol() - 1; i++) {
				if(parameter[i] == 0) {
					parameter[i] = itr++;
					matSubstitusi[i][i] = 1;
				}
			}
			
			
			for(int i = 0; i < matSubstitusi.length; i++) {
				System.out.print("x" + (i + 1) + " =");
				fileOutput = fileOutput.concat("x" + String.valueOf(i+1) + " =");
				if(parameter[i] != -1) {
					System.out.println(" " + sVar(parameter[i]));
					fileOutput = fileOutput.concat(" " + sVar(parameter[i]) + "\n");
					continue;
				}
				System.out.print( " " + matSubstitusi[i][getCol() - 1] );
				fileOutput = fileOutput.concat(" " + String.valueOf( matSubstitusi[i][getCol() - 1] ));
				for(int j = 0; j < getCol() - 1; j++) {
					if(Math.abs( matSubstitusi[i][j] ) < EPS) continue;
					if(matSubstitusi[i][j] > 0) {
						System.out.print(" + ");
						fileOutput = fileOutput.concat(" + ");
					}
					else {
						System.out.print(" - ");
						fileOutput = fileOutput.concat(" - ");
					}
					System.out.print(Math.abs( matSubstitusi[i][j] ) + sVar(parameter[j]));
					fileOutput = fileOutput.concat(String.valueOf( Math.abs( matSubstitusi[i][j] ) ) + sVar(parameter[j]));
				}
				System.out.println();
				fileOutput = fileOutput.concat("\n");
			}
			Menu.outputToFile(s, fileOutput);
			return;
		}
		// solusi tunggal
		System.out.println("Solusi SPL Tunggal, yaitu:");
		fileOutput = "Solusi SPL Tunggal, yaitu:\n";
		double[] val = new double[getCol() - 1];
		for(int i = getCol() - 2; i >= 0; i--) {
			val[i] = getMat(i, getCol() - 1);
			for(int j = i+1; j < getCol() - 1; j++) {
				val[i] -= getMat(i, j) * val[j];
			}
		}
		for(int i = 0; i < getCol() - 1; i++) {
			System.out.printf("x%d = %.4f\n", i+1, val[i]);
			fileOutput = fileOutput.concat(String.format("x%d = %.4f\n", i+1, val[i]));
		}
		Menu.outputToFile(s, fileOutput);
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
				i--;
				continue;
			}
			// bagi baris ke-i dengan nilai getMat(i, satuUtamaPos)
			double pembagi = getMat(i, satuUtamaPos);
			bagiBaris(i, pembagi);
			// kurangkan seluruh baris sehingga getMat(k, satuUtamaPos) == 0
			for(int k = 0; k < getRow(); k++) {
				if(i == k) continue;
				if(getMat(k, satuUtamaPos) == 0) {
					continue;
				}
				double pengali = getMat(k, satuUtamaPos);
				tambahBaris(k, i, -pengali);
			}
			satuUtamaPos++;
		}
	}
	
	public void gaussJordanSPL(Scanner s) {
		/* I.S. Matriks terdefinisi, memiliki ukuran positif
		 * F.S. mengeluarkan penyelesaian SPL dengan variabel sebanyak jumlah kolom - 1.
		 * 		Dapat berupa solusi tunggal, parametrik, maupun tidak ada solusi.
		 * 		Menggunakan eliminasi Gauss-Jordan
		 */
		double EPS = 1e-8;
		gaussJordanElimination();
		String fileOutput = "";
		if(isImpossible()) {
			System.out.println("Persamaan tidak memiliki solusi.");
			return;
		}
		if(isMany()) {
			System.out.println("Solusi SPL banyak, yaitu:");
			fileOutput = "Solusi SPL banyak, yaitu:\n";
			int[] parameter = new int[getCol() - 1];
			String[] ans = new String[getCol() - 1];
			for(int i = 0; i < getCol() - 1; i++) {
				parameter[i] = 0;
				ans[i] = "";
			}
			int itr = 1;
			for(int i = 0; i < getRow(); i++) {
				int sU = -1;
				for(int j = 0; j < getCol() - 1; j++) {
					if(Math.abs( getMat(i, j) ) < EPS) continue;
					if(sU == -1) {
						sU = j;
						parameter[sU] = -1;
					}
					else if(parameter[j] == 0) {
						parameter[j] = itr;
						itr++;
						ans[j] = ans[j].concat( sVar(parameter[j]) );
					}
				}
				if(sU == -1) continue;
				ans[sU] = ans[sU].concat( String.valueOf( getMat(i, getCol() - 1) ) );
				
				for(int j = 0; j < getCol() - 1; j++) {
					if(Math.abs( getMat(i, j) ) < EPS) continue;
					if(sU != j) {
						if(getMat(i, j) > 0) {
							ans[sU] = ans[sU].concat(" - ");
						}
						else {
							ans[sU] = ans[sU].concat(" + ");							
						}
						
						ans[sU] = ans[sU].concat(String.valueOf(Math.abs( getMat(i, j) )));
						ans[sU] = ans[sU].concat(ans[j]);
					}
				}
			}
			
			// cek jika ada variabel belum terpakai
			for(int i = 0; i < getCol() - 1; i++) {
				if(parameter[i] == 0) {
					parameter[i] = itr++;
					ans[i] = ans[i].concat( sVar(parameter[i]) );
				}
			}
			
			for(int i = 0; i < ans.length; i++) {
				System.out.print("x".concat(String.valueOf(i+1)).concat(" = "));
				System.out.println(ans[i]);
				fileOutput = fileOutput.concat("x".concat(String.valueOf(i+1)).concat(" = "));
				fileOutput = fileOutput.concat(ans[i]).concat("\n");
			}
			Menu.outputToFile(s, fileOutput);
			return;
		}
		// solusi tunggal
		System.out.println("Solusi SPL Tunggal, yaitu:");
		fileOutput = "Solusi SPL Tunggal, yaitu:\n";
		for(int i = 0; i < getCol() - 1; i++) {
			System.out.printf("x%d = %.4f\n", i+1, getMat(i, getCol() - 1));
			fileOutput = fileOutput.concat(String.format("x%d = %.4f\n", i+1, getMat(i, getCol() - 1)));
		}
		Menu.outputToFile(s, fileOutput);
	}
	
	public void inverseMethod(Scanner s) {
		/* Penyelesaian SPL menggunakan metode invers */
		/* Ax = B, x = A^(-1)B */
		
		Matriks A = new Matriks(rowCnt, colCnt-1); // matriks A
		Matriks x = new Matriks(rowCnt, 1); // matriks x
		Matriks B = new Matriks(rowCnt, 1); // matriks B
		String fileOutput = "";
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
					fileOutput = fileOutput.concat(String.format("x%d = %.4f\n", i+1, x.getMat(i, 0)));
				}
				Menu.outputToFile(s, fileOutput);
			} else {
				System.out.println("Solusi tunggal tidak dapat ditentukan");
			}
		} else {
			System.out.println("Solusi tunggal tidak dapat ditentukan");
		}
	}
	
	public void cramer(Scanner s) {
		/*Penyelesaian SPL menggunakan metode cramer*/
		
		Matriks A = new Matriks(getRow(), getCol()-1); 
		Matriks B = new Matriks(getRow(), 1);
		Matriks ret = new Matriks (getRow(), 1);
		String fileOutput = "";
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
					fileOutput = fileOutput.concat(String.format("x%d = %.4f\n", i+1, ret.getMat(i, 0)));
				}
				Menu.outputToFile(s, fileOutput);
			} else {
				System.out.println("Solusi tunggal tidak dapat ditentukan");
			}
		} else {
			System.out.println("Solusi tunggal tidak dapat ditentukan");
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
	
	public String matrixToString() {
		  String str = "";
		  for (int i = 0; i < getRow(); i++ ) {
		   for (int j = 0; j< getCol(); j++) {
		    if (j == getCol()-1) {
		     str += getMat(i, j);
		    } else {
		     str += getMat(i, j) + " ";
		    }
		   }
		   str.concat( "\n" );
		  }
		  return str;
	}
	
	public boolean isImpossible() {
		/* return true jika SPL tidak memiliki penyelesaian,
		*  yaitu jika matriks memiliki baris 0 0 .. 0 1
		*/
		for(int i = 0; i < getRow(); i++) {
			Boolean isRowImpossible = true;
			for(int j = 0; j < getCol() - 1; j++) {
				if(getMat(i, j) != 0) {
					isRowImpossible = false;
					break;
				}
			}
			if(getMat(i, getCol() - 1) == 0) {
				isRowImpossible = false;
			}
			if(isRowImpossible) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isMany() {
		/* return true jika SPL memiliki banyak penyelesaian,
		*  yaitu jika baris yang memiliki nilai tak nol selain di kolom terakhir
		*  berjumlah kurang dari jumlahkolom - 1
		*  dan tidak isImpossible
		*/
		
		if(isImpossible()) return false;
		int cntActiveRow = 0;
		for(int i = 0; i < getRow(); i++) {
			Boolean isActiveRow = false;
			for(int j = 0; j < getCol() - 1; j++) {
				if(getMat(i, j) != 0) {
					isActiveRow = true;
					break;
				}
			}
			if(isActiveRow) {
				cntActiveRow++;
			}
		}
		
		if(cntActiveRow == getCol() - 1) {
			return false;
		}
		return true;
	}
	
	public static String sVar(int x) {
		x--;
		String ret = "";
		int jmlHuruf = 1;
		int jmlSekarang = 26;
		while(x > jmlSekarang) {
			jmlHuruf++;
			x = x - jmlSekarang;
			jmlSekarang *= 26;
		}
		for(int i = 0; i < jmlHuruf; i++) {
			ret= String.valueOf( (char)((int)('a') + (x % 26)) ).concat(ret);
		}
		return ret;
	}
	
	/* Common Matriks */
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
