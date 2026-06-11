package mc8;

import java.util.ArrayList;

/**
 * Habitacion del hotel.
 * 
 * @author  Metodos de Programacion (UC)
 * @version mar-22
 */
public class Habitacion {
	private final int numero;
	private ArrayList<Estancia> registro;
	private Estancia estanciaAct;

	/**
	 * Construye la habitacion.
	 * @param numero numero de la habitacion.
	 */
	public Habitacion(int numero) {
		this.numero = numero;
		registro = new ArrayList<Estancia>();
	}

	/**
	 * Retorna el numero de la habitacion.
	 * @return el numero de la habitacion.
	 */
	public int numero() {
		return numero;
	}
	
	public boolean alojaHuespedes(Estancia es) {
		if (estanciaAct != null) {
			return false;
		}
		estanciaAct = es; 
		registro.add(es);
		return true;
	}
	
	public boolean estaVacia() {
		if (estanciaAct != null) {
			return false;
		}
		return true;
	}
	
	public boolean finalizaEstancia() {
		if (estanciaAct == null) {
			return false;
		}
		estanciaAct = null;
		return true;
	}
	
	public Estancia estanciaActual() {
		return estanciaAct;
	}
	
	public int numEstancias(String dniHuesped) {
		int c = 0;
		for (Estancia es: registro) {
			if (es.dniHuesped1().equals(dniHuesped) || es.dniHuesped2().equals(dniHuesped)) {
				c++;
			}
		}
		return c;
	}
	
	public Estancia buscaEstancia(int num) {
		if (num < 0 || num >= registro.size()) {
			return null;
		}
		return registro.get(num);
	}
	
}
