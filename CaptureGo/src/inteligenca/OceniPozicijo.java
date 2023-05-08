package inteligenca;

import java.util.HashSet;
import java.util.Set;

import logika.Igra;
import logika.Koordinati;
import logika.Polje;
import logika.SkupinaZetonov;
import logika.Zeton;

public class OceniPozicijo {
	
	public static int oceniPozicijo(Igra igra) {
		int n = 0;
		for (SkupinaZetonov skupinaZet : igra.skupine_zetonov) {
			Polje barva = skupinaZet.barva;
			int m = tockeSkupine(skupinaZet.skupina, barva, igra);
			if (barva == Polje.CRNO) {
				n += m;
			}
			else {
				n -= m;
			}
		}
		
		return n;
	}
	
	public static int tockeSkupine(Set<Zeton> skupina, Polje barva, Igra igra) {
		Set<Zeton> svobode = new HashSet<Zeton>();
		for (Zeton zeton : skupina) {
			for (Koordinati koordSosed : zeton.sosedi) {
				Zeton sosed = igra.mreza.get(koordSosed);
				if (sosed.polje == Polje.PRAZNO) {
					svobode.add(sosed);
				}
			}
		}
		int n = (int) Math.pow(3, svobode.size());
		n += skupina.size();
		return n;
	}
	
}
