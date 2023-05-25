package logika;

public record Koordinati (int x, int y){

	
	@Override
	public String toString() {
		return "("+ x + ", "+ y + ")";
	}
}
