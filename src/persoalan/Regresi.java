package persoalan;
import matriks.Matriks;

public class Regresi {

	public static String regresiLinierBerganda(Matriks m) {
		Matriks regMtx = new Matriks(m.getRow(), m.getCol());
		Matriks yMtx = new Matriks(m.getRow(), 1);
		
		for (int g = 0; g < m.getRow(); g++) {
			for (int h = 0; h < m.getCol() - 1; h++) {
				if (h == 0) {
					regMtx.setMat(g, h, 1);
				}
				else {
					regMtx.setMat(g, h, m.getMat(g, h));
				}
			}
		}
		for (int y = 0; y < m.getRow(); y++) {
			yMtx.setMat(y, 0, regMtx.getMat(y, regMtx.getCol()));
		}
			
		Matriks valB = regMtx.mul(regMtx.transpose()).inverse().mul(regMtx.transpose()).mul(yMtx);
		
		String pers = "f(x) = ";
		for (int i = 0; i < m.getRow(); i++) {
			if (i == 0) {
				pers += Double.toString(valB.getMat(i, valB.getCol()));
			}
			else if (valB.getMat(i, valB.getCol()) >= 0) {
				pers += " + " + valB.getMat(i, valB.getCol()) + "X";
			}
			else {
				pers += " - " + Math.abs(valB.getMat(i, valB.getCol())) + "X";
			}
			
		}
		return pers;
	}
	
	
}
