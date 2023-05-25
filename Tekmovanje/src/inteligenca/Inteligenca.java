package inteligenca;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

public class Inteligenca extends KdoIgra { 
	public Alphabeta ai;
	
	public Inteligenca() {
		super("Tadej Jeršin & Lana Prijon");
		ai = new Alphabeta(3);
	}
	
	public Poteza izberiPotezo(Igra igra) {
		return ai.izberiPotezo(igra);
	}
} 
