package persoalan;
import matriks.Matriks;

public class Regresi {

	public static String regresiLinierBerganda(Matriks m) {
		
		Matriks regMtx = new Matriks(m.getRow(), m.getCol());
		Matriks yMtx = new Matriks(m.getRow(), 1);
		Matriks valB = new Matriks(m.getRow(), m.getCol() + 1);
		//Matriks splMtx = new Matriks(valB.getRow(), 1);
		
		for (int g = 0; g < m.getRow(); g++) {
			for (int h = 0; h < m.getCol(); h++) {
				if (h == 0) {
					regMtx.setMat(g, h, 1);
				}
				else {
					regMtx.setMat(g, h, m.getMat(g, h - 1));
				}
			}
		}
		for (int y = 0; y < m.getRow(); y++) {
			yMtx.setMat(y, 0, m.getMat(y, m.getCol() - 1));
		}
		
		for (int e = 0; e < m.getRow(); e++) {
			for (int f = 0; f < m.getCol() + 1; f++) {
				if (f == m.getCol()) {
					valB.setMat(e, f, yMtx.getMat(e, 0));
				}
				else {
					valB.setMat(e, f, regMtx.getMat(e, f));
				}
			}
		}
		
		valB.gaussElimintation();
			
		/*for (int p = valB.getRow() - 1; p > -1; p--) {
			for (int q = valB.getCol() - 1; q > -1; q--) {
				if (valB.getMat(p, q) != 0) {
					if (q != 0) {
						if (p != valB.getRow() - 1) {
							valB.setMat(p, q, (valB.getMat(p, q) * valB.getMat(p + 1,  q)));
						}							
						
					}
				}
			}
		}*/
		//p = 1
		//q = 3
		
		/*Matriks gaussXMtx = new Matriks(valB.getRow(), valB.getCol() - 1);
		Matriks gaussYMtx = new Matriks(valB.getRow(), 1);
		
		for (int n = 0; n < valB.getRow(); n++) {
			for (int o = 0; n < valB.getCol() - 1; o++) {
				gaussXMtx.setMat(n, o, valB.getMat(n, o));
			}
		}
		
		for (int l = 0; l < valB.getRow(); l++) {
			gaussYMtx.setMat(l, 0, valB.getMat(l, valB.getCol() - 1));
		}
			
		for (int p = valB.getRow() - 1; p > -1; p--) {
			
		}*/
		
		//Matriks valB = ((((regMtx.mul(regMtx.transpose())).inverse()).mul(regMtx.transpose())).mul(yMtx));
		
		String pers = "f(x) = ";
		for (int i = 0; i < m.getRow(); i++) {
			if (i == 0) {
				pers += Double.toString(valB.getMat(i, valB.getCol() - 1));
			}
			else if (valB.getMat(i, valB.getCol() - 1) >= 0) {
				pers += " + " + valB.getMat(i, valB.getCol() - 1) + "X" + "[" + i + "]";
			}
			else {
				pers += " - " + Math.abs(valB.getMat(i, valB.getCol() - 1)) + "X" + "[" + i + "]";
			}
			
		}
		return pers;
		
		
	}
	
	
}
