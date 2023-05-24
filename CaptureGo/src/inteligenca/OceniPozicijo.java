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
			int svobode = tockeSkupine(skupinaZet.skupina, barva, igra)[0];
			int velikost = tockeSkupine(skupinaZet.skupina, barva, igra)[1];
			if (barva == Polje.CRNO) {
				if (svobode<2) {
					return Integer.MIN_VALUE;
				}
				n += svobode*velikost;
			}
			else {
				if (svobode<2) {
					return Integer.MAX_VALUE;
				}
				n -= svobode*velikost;
			}
		}
		
		return n;
	}
	
	public static int[]  tockeSkupine(Set<Zeton> skupina, Polje barva, Igra igra) {
		Set<Zeton> svobode = new HashSet<Zeton>();
		for (Zeton zeton : skupina) {
			for (Koordinati koordSosed : zeton.sosedi) {
				Zeton sosed = igra.mreza.get(koordSosed);
				if (sosed.polje == Polje.PRAZNO) {
					svobode.add(sosed);
				}
			}
		}
		return new int[] {svobode.size(),skupina.size()};
	}
	
}
