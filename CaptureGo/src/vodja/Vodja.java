package vodja;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import gui.Okno;
import logika.Igra;
import logika.Igralec;
import splosno.Poteza;

public class Vodja {	
	
	public static Map<Igralec,VrstaIgralca> vrstaIgralca;
	public static Okno okno;
	public static Igra igra = null;
	public static boolean clovekNaVrsti = false;
		
	public static void igrajNovoIgro () {
		igra = new Igra();
		igraj ();
	}
	public static void igraj () {
		okno.osveziGUI();
		switch (igra.stanje()) {
		case ZMAGA_CRNI: 
		case ZMAGA_BELI: 
		case NEODLOCENO: 
			return; 
		case V_TEKU: 
			Igralec igralec = igra.na_potezi;
			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
			switch (vrstaNaPotezi) {
			case C: 
				clovekNaVrsti = true;
				break;
			case R:
				igrajRacunalnikovoPotezo ();
				break;
			}
		}
	}
	
	private static Random random = new Random ();


	public static void igrajRacunalnikovoPotezo() {
		Igra zacetnaIgra = igra;
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void> () {
			@Override
			protected Void doInBackground() {
				try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {};	// za testiranje je boljše če je hitrješi
				return null;
			}
			@Override
			protected void done () {
				if (igra != zacetnaIgra) return;
				int randomIndex = random.nextInt(igra.poteze().size());
				Poteza poteza = igra.poteze().get(randomIndex);
				igra.narediPotezo(poteza);
				igraj();	
			}
		};
		worker.execute();	
	}
		
		
	public static void igrajClovekovoPotezo(Poteza poteza) {
		if (clovekNaVrsti && igra.poteze().contains(poteza)) {
			igra.narediPotezo(poteza);
			clovekNaVrsti = false;
			igraj();
		}
	}


}
