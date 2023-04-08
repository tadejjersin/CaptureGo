package logika;

import java.util.HashSet;
import java.util.Set;

public class Zeton {
	public Koordinati koordinati;
	protected Set<Koordinati> sosedi;
	protected Polje polje;
	protected boolean obkoljen;
	protected int dim_mreze;
	
	public Zeton(int x, int y) {
		koordinati = new Koordinati(x, y);
		polje = Polje.PRAZNO;
		sosedi = new HashSet<Koordinati>();
		obkoljen = false;
		dim_mreze = 9;
		
		int[] arr = {1, -1};
		for (int i : arr) {
			for (int j : arr) {
				if (0 <= (x+i) && (x+1) <= (dim_mreze-1) && (y+j) <= (dim_mreze-1) && 0 <= (y+j)) 
					sosedi.add(new Koordinati(x+i, y+j));
			}
		}
	}
	
	public Polje polje() {
		return polje;
	}
	
	public void obkoli() {
		this.obkoljen = true;
	}
	
	public void spremeniBarvo(Polje p) {
		this.polje = p;
	}
	
}
