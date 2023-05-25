package inteligenca;

import java.util.Comparator;

import logika.Igra;
import logika.Koordinati;
import logika.Polje;
import splosno.Poteza;

public class NewSort implements Comparator<Poteza> {
	public Igra igra;
	
	public NewSort(Igra igra) {
		this.igra = igra;
	}
	
	@Override
	public int compare(Poteza p, Poteza q) {
		return sosedi(q) - sosedi(p);
	}
	
	private int sosedi(Poteza p) {
		int c = 0;
		for (Koordinati k : igra.mreza.get(new Koordinati(p.x(), p.y())).sosedi) {
			if (igra.mreza.get(k).polje() != Polje.PRAZNO) c++;
		}
		return c;
	}
}
