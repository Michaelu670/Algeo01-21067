package persoalan;

import matriks.Matriks;

public class Bicubic {
	
	/* Matriks inverse(X) sebagai pengali */
	public static Matriks mult() {
		Matriks ret = new Matriks(16, 16);
		for(int x = -1; x <= 2; x++) {
			for(int y = -1; y <= 2; y++) {
				for(int i = 0; i <= 3; i++) {
					for(int j = 0; j <= 3; j++) {
						ret.setMat((x+1)*4+(y+1), i*4+j, Math.pow(x, i) * Math.pow(y, j));
					}
				}
			}
		}
		
		return ret.inverseByAugment();
		
	}
}
