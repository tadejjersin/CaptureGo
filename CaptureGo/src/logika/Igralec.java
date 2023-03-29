package logika;

public enum Igralec {
	BELI, CRNI;
	
	public Igralec nasprotnik() {
		return (this == BELI ? CRNI : BELI);
	}
	
	public Polje polje() {
		return (this == BELI ? Polje.CRNO : Polje.BELO);
	}
}
