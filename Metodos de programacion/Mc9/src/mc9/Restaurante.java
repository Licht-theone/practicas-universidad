package mc9;

public class Restaurante {
	private static final int NUM_MESAS = 15;
	private Mesa[] mesas = new Mesa[NUM_MESAS];
	
	public Restaurante() {
		for (int i = 0; i < NUM_MESAS; i++) {
			mesas[i] = new Mesa();
		}
	}
	
	public int buscaMesaLibre() {
		for (int i = 0 ; i < NUM_MESAS; i++) {
			if (mesas[i].estaLibre()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean anotaConsumicion(Consumicion c, int numMesa) {
		if (numMesa < 0 || numMesa >= NUM_MESAS || !mesas[numMesa].estaLibre()) {
			return false;
		}
		mesas[numMesa].asignaMesa();
		mesas[numMesa].anotaConsumicion(c);
		return true;
	}
	
	public double cobraMesa(int numMesa) {
		if (numMesa < 0 || numMesa >= NUM_MESAS || mesas[numMesa].estaLibre()) {
			return -1;
		}
		return mesas[numMesa].vaciaYcobra();
	}
	
	public int mesasConConsumicion(String desc) {
		int c = 0;
		for (int i = 0; i < NUM_MESAS; i++) {
			if (mesas[i].buscaConsumicion(desc) != null) {
				c++;
			}
		}
		return c;
	}
	
}
