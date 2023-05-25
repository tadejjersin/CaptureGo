package logika;

import java.util.HashSet;
import java.util.Set;

public class SkupinaZetonov {
	public Set<Zeton> skupina;
	public Polje barva;
	public int oko;
	
	// naredi novo skupino z enim žetonom
	public SkupinaZetonov(Zeton z) {
		skupina = new HashSet<Zeton>();
		skupina.add(z);
		this.barva = z.polje;
	}
	
	// naredi kopijo skupine (uporabno pri kopiranju igre)
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
				if (o.koordinati.x() == 0 | o.koordinati.x() == 8) {
					stSosedov += 1;
				}
				if (o.koordinati.y() == 0 | o.koordinati.y() == 8) {
					stSosedov += 1;
				}
				
				// ali je o oko skupine?
				if (stSosedov == 4) {
					this.oko += 1;
				}
				
			}
			
		}
		
	}
	
	// preveri, če je žeton v skupini
	public boolean vsebujeZeton(Zeton z) {
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
