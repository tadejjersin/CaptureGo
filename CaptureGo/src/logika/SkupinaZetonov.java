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
	
	public void dodajZeton(Zeton zeton) {
		skupina.add(zeton);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Zeton z : skupina) s += z.toString();
		return "SKUPINA " + s;
	}
}
