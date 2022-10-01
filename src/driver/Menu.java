package driver;

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
	
	public static void SPL(int metode, Matriks m) {
 		if (metode == 1) {
 			m.gaussElimintation();
 		} else if (metode == 2) {
 			m.gaussJordanSPL();
 		} else if (metode == 3) {
 			m.inverseMethod();
		} else if (metode == 4) {
			m.cramer();
		}
	}
	
	public static void metodeDeterminan() {
		System.out.println("\n1. Determinan reduksi baris");
		System.out.println("2. Determinan ekspansi kofaktor");
		System.out.println("3. Kembali");
	}
	
	public static void metodeBalikan() {
		System.out.println("\n1. Metode adjoin");
		System.out.println("2. Metode matriks augmented");
		System.out.println("3. Kembali");
		
	}
}
