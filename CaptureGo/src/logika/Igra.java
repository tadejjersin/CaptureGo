package logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import splosno.Poteza;

public class Igra {
	protected Map<Koordinati, Zeton> mreza;
	protected Igralec na_potezi;
	public Set<SkupinaZetonov> skupine_zetonov;
	public SkupinaZetonov obkoljena;
	public int dimMreze;
	
	public Igra() {
		dimMreze = 9;
		mreza = new HashMap<Koordinati, Zeton>();
		na_potezi = Igralec.CRNI;
		obkoljena = null;
		skupine_zetonov = new HashSet<SkupinaZetonov>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mreza.put(new Koordinati(i, j), new Zeton(i, j));
			}
		}
	}
	
	public Stanje stanje() { // ne dela še za izjeme iz pravil
		for (SkupinaZetonov sk : skupine_zetonov) {
			if (jeObkoljena(sk)) {
				switch (sk.barva) {
				case BELO:
					obkoljena = sk;
					return Stanje.ZMAGA_CRNI;
				case CRNO:
					obkoljena = sk;
					return Stanje.ZMAGA_BELI;
				case PRAZNO:
					assert false;
				}
			}
			for (Zeton z : mreza.values()) {
				if (z.polje == Polje.PRAZNO) return Stanje.V_TEKU;
			}
		}
		return Stanje.NEODLOCENO;
	}
	
	private boolean jeObkoljena(SkupinaZetonov s) {
		for (Zeton z : s.skupina) {
			for (Koordinati k : z.sosedi) {
				if (mreza.get(k).polje == Polje.PRAZNO) return false;
			}
		}
		return true;
	}
	
	
	public void narediPotezo(Poteza poteza) {
		int x = poteza.getX();
		int y = poteza.getY();
		Koordinati k = new Koordinati(x, y);
		Zeton zeton = mreza.get(k);
		if (zeton.polje == Polje.PRAZNO) {
			zeton.spremeniBarvo(na_potezi.polje());
			mreza.put(k, zeton);
			SkupinaZetonov s = new SkupinaZetonov(zeton);
			skupine_zetonov.add(s);
			for (Koordinati l : zeton.sosedi) {
				Zeton nov_zeton = mreza.get(l);
				if (nov_zeton.polje == na_potezi.polje()) {
					Iterator<SkupinaZetonov> iter = skupine_zetonov.iterator();
					while (iter.hasNext()) {
						SkupinaZetonov sk = iter.next();
						if (sk.skupina.contains(nov_zeton)) {
							for (Zeton z : sk.skupina) {
								s.skupina.add(z);
							}
							iter.remove();
						}
					}
				}
			}
			
			
			na_potezi = na_potezi.nasprotnik();
		}
	}
}
