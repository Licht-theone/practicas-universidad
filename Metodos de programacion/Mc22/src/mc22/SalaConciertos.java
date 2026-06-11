package mc22;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import mc22.Abono.MaxEntradas;
import mc22.Concierto.ConciertoLleno;
@SuppressWarnings("serial")
public class SalaConciertos {
	public class AbonoNoExiste extends RuntimeException{}
	public class ConciertoNoExiste extends RuntimeException{}
	public class AbonoYaExiste extends RuntimeException{}
	public class YaTieneEntrada extends RuntimeException{}
	private ArrayList<Abono> abonos = new ArrayList<Abono>();
	private ArrayList<Concierto> conciertos = new ArrayList<Concierto>();
	
	
	/**
	 * 
	 */
	public SalaConciertos(String nomFich) throws FileNotFoundException{
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			while(in.hasNext()) {
				String nombre = in.next();
				double precio = in.nextDouble();
				int maxEntradas = in.nextInt();
				Concierto c = new Concierto(nombre, null, precio, maxEntradas);
				conciertos.add(c);
			}
		}
	}
	
	public Abono buscaAbono(String dni) {
		for (Abono ab: abonos) {
			if (ab.getDni().equals(dni)) {
				return ab;
			}
		}
		return null;
	}
	
	public Concierto buscaConcierto(String nombre) {
		for (Concierto c: conciertos) {
			if (c.getNombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}
	
	public void anhadeAbono(Abono ab) throws AbonoYaExiste {
		if (buscaAbono(ab.getDni()) != null) {
			throw new AbonoYaExiste();
		}
		abonos.add(ab);
	}
	
	public void compraEntrada(String dni, String nombre) throws AbonoNoExiste, ConciertoNoExiste, ConciertoLleno, MaxEntradas, YaTieneEntrada{
		Concierto c = buscaConcierto(nombre);
		Abono ab = buscaAbono(dni);
		if (c == null) {
			throw new ConciertoNoExiste();
		}
		if (ab == null) {
			throw new AbonoNoExiste();
		}
		if (ab.buscaConcierto(nombre) != null) {
			throw new YaTieneEntrada();
		}
		c.anhadeAsistente(ab);
		ab.compraEntrada(c, ab);
	}
	
	public double pagoAbonado(String dni) throws AbonoNoExiste {
		Abono ab = buscaAbono(dni);
		if (ab == null) {
			throw new AbonoNoExiste();
		}
		return ab.precioTot();
	}
	
	public Concierto conciertoMasX(String tipoAbono) {
		int max = Integer.MIN_VALUE;
		int nuevoMax = 0;
		Concierto con = null;
		if (tipoAbono.equals("fijo")) {
			for (Concierto c: conciertos) {
				nuevoMax = c.cuentaFijos();
				if (nuevoMax > max) {
					max = nuevoMax;
					con = c;
				}
			}
			return con;
		} else if (tipoAbono.equals("basico")) {
			for (Concierto c: conciertos) {
				nuevoMax = c.cuentaBas();
				if (nuevoMax > max) {
					max = nuevoMax;
					con = c;
				}
			}
			return con;
		} else {
			for (Concierto c: conciertos) {
				nuevoMax = c.cuentaFlex();
				if (nuevoMax > max) {
					max = nuevoMax;
					con = c;
				}
			}
			return con;
		}
	}
	
}
