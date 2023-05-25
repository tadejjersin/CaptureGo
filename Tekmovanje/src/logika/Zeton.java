package logika;

import java.util.HashSet;
import java.util.Set;

public class Zeton {
	public Koordinati koordinati;
	public Set<Koordinati> sosedi;
	public Polje polje;
	protected boolean obkoljen;	
	
	public Zeton(int x, int y) {
		koordinati = new Koordinati(x, y);
		polje = Polje.PRAZNO;
		sosedi = new HashSet<Koordinati>();
		obkoljen = false;

		
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
	
	//naredi kopijo zetona
	public Zeton(Zeton zeton) { 
		koordinati = zeton.koordinati;
		obkoljen = zeton.obkoljen;
		polje = zeton.polje;
		sosedi = zeton.sosedi;
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
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Zeton)) return false;
		Zeton z = (Zeton) o;
		return (z.koordinati.equals(this.koordinati) && z.polje.equals(this.polje));
	}
	
}
