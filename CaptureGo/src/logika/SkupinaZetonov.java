package logika;

import java.util.HashSet;
import java.util.Set;

public class SkupinaZetonov {
	public Set<Zeton> skupina;
	public Polje barva;
	public int oko;
	
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
	
	public void dodajZeton(Igra igra, Zeton zeton) {
		skupina.add(zeton);
		Polje barva = this.barva;
		// poglejmo, če se je naredilo oko
		for (Koordinati k: zeton.sosedi) {
			Zeton o = igra.mreza.get(k);
			if (o.polje == Polje.PRAZNO) {
				// preštejemo, če so vsa 4 polja okoli njega prave barve, oz rob plošče
				int stSosedov = 0;
				for (Koordinati h: o.sosedi) {
					Zeton s = igra.mreza.get(k);
					if (s.polje == barva) {
						stSosedov += 1;
					}
				}
				if (o.koordinati.getX() == 0 | o.koordinati.getX() == 8) {
					stSosedov += 1;
				}
				if (o.koordinati.getY() == 0 | o.koordinati.getY() == 8) {
					stSosedov += 1;
				}
				
				// ali je o oko skupine?
				if (stSosedov == 4) {
					this.oko += 1;
				}
				
			}
			
		}
		
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
