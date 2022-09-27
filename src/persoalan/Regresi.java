package persoalan;
import matriks.Matriks;

public class Regresi {

	public static String regresiLinierBerganda(Matriks m) {
		
		Matriks regMtx = new Matriks(m.getRow(), m.getCol());
		Matriks yMtx = new Matriks(m.getRow(), 1);
		
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
			
		Matriks valB = ((((regMtx.mul(regMtx.transpose())).inverse()).mul(regMtx.transpose())).mul(yMtx));
		
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
