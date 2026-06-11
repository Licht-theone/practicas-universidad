package mc4;

public class GUIBasica {

	public static void main(String[] args) {
		Pista pista = new Pista(0);
		Avion v1 = new Avion("IBE0001");
		pista.asignaVuelo(v1);
		pista.asignaVuelo(new Avion("FRA1241"));
		pista.asignaVuelo(new Avion("ZUR1348"));
		pista.asignaVuelo(new Avion("ALI0010"));
		System.out.println("Listado de vuelos desordenados");
		System.out.println(pista);
		System.out.println("Lista ordenada");
		pista.ordenaVuelo();
		System.out.println(pista);
	}

}
