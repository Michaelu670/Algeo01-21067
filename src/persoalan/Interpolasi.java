package persoalan;

public class Interpolasi {
  
	public float interpolasi(double x, Matriks m ) {
		Matriks mtx = new Matriks(m.getRow(), m.getRow()+1);
		float ans = 0;
		
		for (int i = 0; i < mtx.getRow(); i++) {
			for (int j = 0; j < mtx.getCol(); j++) {
				if (j == mtx.getCol()-1) {
					mtx.setMat(i, j, m.getMat(i, j));
				} else {
					mtx.setMat(i, j, Math.pow(m.getMat(i, 0), j));
				}
			}
		}
		
		mtx = mtx.cramer();
		
		for (int i = 0; i< mtx.getRow(); i++) {
			ans += mtx.getMat(i, 0) * Math.pow(x, i);
		}
		return ans;
	}

}
