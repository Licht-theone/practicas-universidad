package macaya2;

public class Aeropuerto {
	//atributos
	private Avion[] aviones; //declarara array
	private int numAviones = 0; //num de av ya en el array o posicion del siguiente

	//metodos
	//constructor
	public Aeropuerto(int maxAviones) {
		aviones = new Avion[maxAviones]; //crear array
		numAviones = 0;
	}

	public boolean aterrizaAvion(Avion av) {
		//false si no puede aterrizar, true en caso contrario
		if (numAviones >= aviones.length) {
			System.out.println("No se puede aterrizar, esta lleno\n");
			return false;
		}
		aviones[numAviones] = av;
		numAviones++;
		return true;
	}

	public String toString() {
		String s = "Hay:"+numAviones+" aviones\n";
		for (int i = 0; i < numAviones; i++) {
			s += aviones[i].toString();
		}
		return s;
	}

	public Avion buscaAvion(String id) {
		for (int j = 0; j < numAviones; j++) {
			if (aviones[j].getId().equals(id)) {
				return aviones[j];
			}
		}
		return null;
	}
	public boolean despegaAvion(String id) {
		if (buscaAvion(id) == null) {
			System.out.println("no puede despegar, no encontrado");
			return false;
		}
		int i;
		for (i = 0; !aviones[i].getId().equals(id); i++);
		for ( ; i < numAviones-1; i++) {
			aviones[i] = aviones[i+1];
		}
		System.out.println("el avion ha despegado");
		return true;
	}
	
	
}
