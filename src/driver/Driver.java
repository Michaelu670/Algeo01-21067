package driver;

import java.util.Scanner;
import persoalan.*;
import driver.Menu;
import matriks.Matriks;

public class Driver {

	public static void main(String[] args) {
		int command = 0, metode = 0, opsi = 0;
		Scanner s = new Scanner(System.in);
			
		while (command != 7) {
			Menu.printMenu();
			
			command = s.nextInt();
			
			while (command < 1 || command > 7) {
				System.out.println("Menu tidak tersedia, mohon ulangi: ");
				command = s.nextInt();
			}
			
			if (command == 1) {
				Matriks m = new Matriks();
			
				Menu.metodeSPL();
				metode = s.nextInt();
				while (metode > 5 || metode < 1) {
					System.out.println("Mohon ulangi: ");
				}
				
				if(metode != 5) {
					Menu.printOpsi();
					opsi = s.nextInt();
					while (opsi != 1 && opsi != 2) {
						System.out.println("Mohon ulangi: ");
						opsi = s.nextInt();
					}
					
					if (opsi == 1) {
						m.read(s);
					} else {
						System.out.println("** SELURUH ALAMAT DITULIS RELATIF TERHADAP FOLDER test **");
						System.out.println("Masukkan nama file: ");
						String fileName = s.next();
						m = Matriks.readFile(fileName);
					}
					
					Menu.SPL(metode, m, s);
				}
				
			} else if (command == 2) {
				Matriks m = new Matriks();
				
				Menu.metodeDeterminan();
				metode = s.nextInt();
				while (metode > 3 || metode < 1) {
					System.out.println("Mohon ulangi: ");
				}
				
				if (metode != 3) {
					Menu.printOpsi();
					opsi = s.nextInt();
					while (opsi != 1 && opsi != 2) {
						System.out.println("Mohon ulangi: ");
						opsi = s.nextInt();
					}
					if (opsi == 1) {
						m.read(s);
					} else {
						System.out.println("** SELURUH ALAMAT DITULIS RELATIF TERHADAP FOLDER test **");
						System.out.println("Masukkan nama file: ");
						String fileName = s.next();
						m = Matriks.readFile(fileName);
					}
					
					if (m.getRow() != m.getCol()) {
						System.out.println("Determinan tidak dapat ditentukan");
					} else {
						if (metode == 1) {
							System.out.println("determinan = " +m.determinanReduksiBaris());
							Menu.outputToFile(s, "determinan = " + String.valueOf(m.determinanReduksiBaris()));
						} else {
							System.out.println("determinan = " +m.determinant());
							Menu.outputToFile(s, "determinan = " + String.valueOf(m.determinant()));
						}
					}
				}
			}
			
			else if (command == 3){
				Matriks m = new Matriks();
				
				Menu.metodeBalikan();
				metode = s.nextInt();
				while (metode > 3 || metode < 1) {
					System.out.println("Mohon ulangi: ");
				}
				
				if (metode != 3) {
					Menu.printOpsi();
					opsi = s.nextInt();
					while (opsi != 1 && opsi != 2) {
						System.out.println("Mohon ulangi: ");
						opsi = s.nextInt();
					}
					if (opsi == 1) {
						m.read(s);
					} else {
						System.out.println("** SELURUH ALAMAT DITULIS RELATIF TERHADAP FOLDER test **");
						System.out.println("Masukkan nama file: ");
						String fileName = s.next();
						m = Matriks.readFile(fileName);
					}
					if (m.getCol() != m.getRow()) {
						System.out.println("Matriks balikan tidak dapat ditentukan");
					} else {
						if (metode == 1) {
							m = m.inverseByAugment();
							m.print();
							Menu.outputToFile(s, m.toString());
							
						} else {
							m = m.inverse();
							m.print();
							Menu.outputToFile(s, m.toString());
						}
					}
				}
			}
			
			else if (command == 4) {
				Matriks m = new Matriks();
				Menu.printOpsi();
				opsi = s.nextInt();
				while (opsi != 1 && opsi != 2) {
					System.out.println("Mohon ulangi: ");
					opsi = s.nextInt();
				}
				if (opsi == 1) {
					Interpolasi.interpolasi(s);
				} else {
					System.out.println("** SELURUH ALAMAT DITULIS RELATIF TERHADAP FOLDER test **");
					System.out.println("Masukkan nama file: ");
					String fileName = s.next();
					m = Matriks.readFile(fileName);
					m.print();
					System.out.print("Nilai yang dicari: ");
					double x = s.nextDouble();
					Interpolasi.interpolasi(x,m,s);
				}
				
			}
			else if (command == 5) {
				Menu.metodeBicubic();
				metode = s.nextInt();
				if(metode == 1) {
					Menu.printOpsi();
					opsi = s.nextInt();
					if(opsi == 1) {
						Bicubic.bicubicInterpolation(s);
					}
					else {
						System.out.println("** SELURUH ALAMAT DITULIS RELATIF TERHADAP FOLDER test **");
						System.out.print("Masukkan nama file: ");
						String sourceFile = s.next();
						Bicubic.bicubicInterpolation(sourceFile, s);
					}
				}
				else if(metode == 2) {
					System.out.print("Masukkan nama file asal: ");
					String src = s.next();
					System.out.print("Masukkan nama file tujuan: ");
					String dest = s.next();
					Bicubic.zoomImg(src, dest);
				}
			}
			else if (command == 6) {
				Menu.printOpsi();
				opsi = s.nextInt();
				if(opsi == 1) {
					Regresi.regresiLinierBerganda(s);
				}
				else {
					System.out.println("** SELURUH ALAMAT DITULIS RELATIF TERHADAP FOLDER test **");
					System.out.print("Masukkan nama file: ");
					String sourceFile = s.next();
					Regresi.regresiLinierBerganda(sourceFile, s);
				}
			}
		}
		System.out.println("\nseeleesaaiii!!! :D\n");
		System.out.println("created with love\n-MiFeYo<3");
	}
}
