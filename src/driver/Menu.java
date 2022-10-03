package driver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import matriks.Matriks;

public class Menu {
	public static void printMenu() {
		System.out.println("\n----- M E N U -----");
		System.out.println("1. Sistem Persamaan Linier");
		System.out.println("2. Determinan");
		System.out.println("3. Matriks Balikan");
		System.out.println("4. Interpolasi Polinom");
		System.out.println("5. Bicubic Interpolation");
		System.out.println("6. Regresi Linier Berganda");
		System.out.println("7. Keluar");
		System.out.println("Pilih menu yang tersedia: ");
	}
	
	public static void printOpsi() {
		System.out.println("\n1. Keyboard");
		System.out.println("2. File Eksternal");
		System.out.println("Pilih jenis input:");	
	}
	
	public static void metodeSPL() {
		System.out.println("\n1. Metode eliminasi Gauss");
		System.out.println("2. Metode eliminasi Gauss-Jordan");
		System.out.println("3. Matriks Balikan");
		System.out.println("4. Kaidah Cramer");
		System.out.println("5. Kembali ke menu utama");
		System.out.println("Pilih metode penyelesaian: ");
	}
	
	public static void SPL(int metode, Matriks m, Scanner s) {
 		if (metode == 1) {
 			m.gaussSPL(s);
 		} else if (metode == 2) {
 			m.gaussJordanSPL(s);
 		} else if (metode == 3) {
 			m.inverseMethod(s);
		} else if (metode == 4) {
			m.cramer(s);
		}
	}
	
	public static void metodeDeterminan() {
		System.out.println("\n1. Determinan reduksi baris");
		System.out.println("2. Determinan ekspansi kofaktor");
		System.out.println("3. Kembali");
	}
	
	public static void metodeBicubic() {
		System.out.println("\n1. Bicubic Interpolation");
		System.out.println("2. Perbesaran gambar");
		System.out.println("3. Kembali");
	}
	
	public static void metodeBalikan() {
		System.out.println("\n1. Metode adjoin");
		System.out.println("2. Metode matriks augmented");
		System.out.println("3. Kembali");
		
	}
	
	public static void outputToFile(Scanner s, String str) {
		System.out.print("\nApakah output ingin dimasukkan ke file? (Y/N) ");
		String ans = s.next();
		if((ans.charAt(0) != 'Y') && (ans.charAt(0) != 'y')) {
			return;
		}
		try {
			System.out.print("Nama file tujuan : ");
			String dest = s.next();
			FileWriter writer = new FileWriter("test/".concat(dest));
			writer.write(str);
			writer.close();
			System.out.println("Berhasil menulis ke file.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
