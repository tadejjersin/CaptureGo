package logika;

import java.util.HashSet;
import java.util.Set;

public class SkupinaZetonov {
	public Set<Zeton> skupina;
	public Polje barva;
	
	public SkupinaZetonov(Zeton z) {
		skupina = new HashSet<Zeton>();
		skupina.add(z);
		this.barva = z.polje;
	}
	
	public SkupinaZetonov(SkupinaZetonov s) {
		barva = s.barva;
		skupina = new HashSet<Zeton>();
		for (Zeton z : s.skupina) skupina.add(new Zeton(z));
	}
	
	public void dodajZeton(Zeton zeton) {
		skupina.add(zeton);
	}
	
	public boolean isIn(Zeton z) {
		for (Zeton e : skupina) {
			if (z.equals(e)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Zeton z : skupina) s += z.toString();
		return "SKUPINA " + s;
	}
}
