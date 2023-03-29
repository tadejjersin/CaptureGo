package logika;

import java.util.HashSet;
import java.util.Set;

public class Zeton {
	Koordinati koordinati;
	Set<Koordinati> sosedi;
	Polje polje;
	
	public Zeton(int x, int y) {
		koordinati = new Koordinati(x, y);
		polje = Polje.PRAZNO;
		sosedi = new HashSet<Koordinati>();
		int[] arr = {1, -1};
		for (int i : arr) {
			for (int j : arr) {
				if (0 <= (x+i) && (x+1) <= 8 && (y+j) <= 8 && 0 <= (y+j)) sosedi.add(new Koordinati(x+i, y+j));
			}
		}
	}
	
	public void spremeniBarvo(Polje p) {
		this.polje = p;
	}
	
}
