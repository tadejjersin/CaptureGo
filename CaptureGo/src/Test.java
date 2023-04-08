import gui.Okno;
import logika.Igra;

public class Test {
	public static void main(String[] args) {
	Okno okno = new Okno();
	okno.pack();
	okno.setVisible(true);
	okno.platno.nastaviIgro(new Igra());
	}
}
