package logika;

import java.util.HashSet;
import java.util.Set;

public class SkupinaZetonov {
	protected Set<Zeton> skupina;
	protected Polje barva;
	
	public SkupinaZetonov(Zeton z) {
		skupina = new HashSet<Zeton>();
		skupina.add(z);
		this.barva = z.polje;
	}
	
	public void dodajZeton(Zeton zeton) {
		skupina.add(zeton);
	}
	
	
}
