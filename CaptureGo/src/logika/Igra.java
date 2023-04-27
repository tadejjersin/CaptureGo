package logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import splosno.Poteza;

public class Igra {
	public Map<Koordinati, Zeton> mreza;
	public Igralec na_potezi;
	public Set<SkupinaZetonov> skupine_zetonov;
	//public int dimMreze;
	
	public Igra() {
		//dimMreze = 9;
		mreza = new HashMap<Koordinati, Zeton>();
		na_potezi = Igralec.CRNI;
		skupine_zetonov = new HashSet<SkupinaZetonov>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				mreza.put(new Koordinati(i, j), new Zeton(i, j));
			}
		}
	}
	
	// naredi kopijo igre
	public Igra(Map<Koordinati, Zeton> mreza, Igralec na_potezi, Set<SkupinaZetonov> skupine_zetonov) {
		this.mreza = new HashMap<Koordinati, Zeton>();
		this.skupine_zetonov = new HashSet<SkupinaZetonov>();
		this.na_potezi = na_potezi;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.mreza.put(new Koordinati(i, j), new Zeton(mreza.get(new Koordinati(i, j))));
			}
		}
		for (SkupinaZetonov s : skupine_zetonov) {
			this.skupine_zetonov.add(new SkupinaZetonov(s));
		}
	}
	
	
	public Igralec naPotezi() {
		return na_potezi;
	}
	
	
	
	public Stanje stanje() { 
		SkupinaZetonov obkoljena = null;
		Set<SkupinaZetonov> obkoljene_druga_barva = new HashSet<SkupinaZetonov>();
		for (SkupinaZetonov sk : skupine_zetonov) {
			if (jeObkoljena(sk)) {
				switch (sk.barva) {
				case BELO:
					if (na_potezi.polje() == Polje.BELO) obkoljene_druga_barva.add(sk);
					else obkoljena = sk;
					break;
				case CRNO:
					if (na_potezi.polje() == Polje.CRNO) obkoljene_druga_barva.add(sk);
					else obkoljena = sk;
					break;
				case PRAZNO:
					assert false;
				}
			}
		}
		if (obkoljene_druga_barva.size() > 0) {
			for (SkupinaZetonov s : obkoljene_druga_barva) {
				for (Zeton z : s.skupina) z.obkoli();
			}
			switch (na_potezi) {
			case BELI:
				return Stanje.ZMAGA_CRNI;
			case CRNI:
				return Stanje.ZMAGA_BELI;
			}
		} else if (obkoljena != null) {
			for (Zeton z : obkoljena.skupina) z.obkoli();
			switch (na_potezi) {
			case BELI:
				return Stanje.ZMAGA_BELI;
			case CRNI:
				return Stanje.ZMAGA_CRNI;
			}
		}
		for (Zeton z : mreza.values()) {
			if (z.polje == Polje.PRAZNO) return Stanje.V_TEKU;
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
	
	
	public boolean narediPotezo(Poteza poteza) {
		int x = poteza.getX();
		int y = poteza.getY();
		Koordinati k = new Koordinati(x, y);
		Zeton zeton = mreza.get(k);
		if (zeton.polje == Polje.PRAZNO) {
			zeton.spremeniBarvo(na_potezi.polje());
			SkupinaZetonov s = new SkupinaZetonov(zeton);
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
			skupine_zetonov.add(s);
			
			na_potezi = na_potezi.nasprotnik();
			return true;
		}
		return false;
	}

	public LinkedList<Poteza> poteze() {
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		if (stanje() == Stanje.V_TEKU) {
			for (Entry<Koordinati, Zeton> entry: this.mreza.entrySet()) {
				Zeton o = entry.getValue();
				if (o.polje == Polje.PRAZNO) {
					int x = o.koordinati.getX();
					int y = o.koordinati.getY();
					moznePoteze.add(new Poteza(x,y));
				}
			}
		}
		return moznePoteze; 
	}
}
