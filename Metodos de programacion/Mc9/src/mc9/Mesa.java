package mc9;

import java.util.ArrayList;

public class Mesa {
	private boolean estaLibre;
	private ArrayList<Consumicion> consumiciones;
	private double importeTotal;
	

	public Mesa() {
		consumiciones = new ArrayList<Consumicion>();
		importeTotal = 0;
		estaLibre = true;
	}
	
	public void anotaConsumicion(Consumicion c) {
		consumiciones.add(c);
		importeTotal += c.precio();
	}
	
	public boolean estaLibre() {
		return estaLibre;
	}
	
	public void asignaMesa() {
		estaLibre = false;
	}
	
	public double vaciaYcobra() {
		consumiciones.removeAll(consumiciones);
		double cobro = importeTotal;
		importeTotal = 0;
		estaLibre = true;
		return cobro;
	}
	
	
	public Consumicion buscaConsumicion(String desc) {
		for (Consumicion c: consumiciones) {
			if (c.descripcion().equals(desc)) {
				return c;
			}
		}
		return null;
	}
}
