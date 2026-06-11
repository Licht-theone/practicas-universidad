
public class LEP {
	private Nodo inicio;
	
	public LEP() {
		inicio = null;
	}
	
	public boolean estaVacia() {
		return inicio == null;
	}
	
	public void anhadeInicio(String s) {
		Nodo nuevo = new Nodo(s, null);
		nuevo.setSig(inicio);
		inicio = nuevo;
	}
	
	public String toString() {
		if (estaVacia()) {
			return "Lista Vacia";
		}
		String s = "";
		Nodo aux = inicio;
		while (aux != null) {
			s += " " + aux.getInfo();
			aux = aux.getSig();
		}
		return s;
	}
	
	public int cuantosElem() {
		if (estaVacia()) {
			return 0;
		}
		
		int c = 0;
		Nodo aux = inicio;
		while (aux != null) {
			aux = aux.getSig();
			c++;
		}
		return c;
	}
	
	public void anhadeFinal(String s) {
		if (estaVacia()) {
			anhadeInicio(s);
		} else {
			Nodo aux = inicio;
			while (aux.getSig() != null) {
				aux = aux.getSig();
			}
			Nodo nuevo = new Nodo(s, null);
			aux.setSig(nuevo);
		}
		
	}
	
}
