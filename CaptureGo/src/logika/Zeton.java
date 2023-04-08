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
			if (x+i < 9 && x+i >= 0) {
				sosedi.add(new Koordinati(x + i, y));
			}
			if (y+i < 9 && y+i >= 0) {
				sosedi.add(new Koordinati(x, y + i));
			}
		}
	}
	
	public Polje polje() {
		return polje;
	}
	
	public boolean obkoljen() {
		return obkoljen;
	}
	
	public void obkoli() {
		this.obkoljen = true;
	}
	
	public void spremeniBarvo(Polje p) {
		this.polje = p;
	}
	
	@Override
	public String toString() {
		return "[ZETON b=" + polje + " k=" + koordinati + "]";
	}
	
}
