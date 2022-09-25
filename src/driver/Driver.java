package driver;

import java.util.Scanner;
import persoalan.*;

import matriks.Matriks;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		Matriks m = new Matriks();
		m.read(s);
		m.print();
		m = m.inverseByAugment();
		m.print();
		
		Bicubic.bicubicInterpolation("Hello.txt");
	}

}
