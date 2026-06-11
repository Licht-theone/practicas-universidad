package pract10.modelo;

import java.util.ArrayList;

import pract10.modelo.tarjetas.Tarjeta;
import pract10.modelo.tarjetas.Tarjeta.SaldoInsuficiente;
import pract10.modelo.tarjetas.TarjetaBasica;
import pract10.modelo.tarjetas.TarjetaViajes;
import pract10.modelo.tarjetas.TarjetaViajes.PuntosInsuficientes;
import pract10.modelo.tarjetas.TarjetaViajesFamilia;
import pract10.modelo.tarjetas.TarjetaViajesNormal;

/**
 * Clase supermercado que gestiona las tarjetas.
 * @author Aaron Alegria
 * @version may-2021
 */
@SuppressWarnings("serial")
public class Supermercado {
	/**
	 * excepcion para si el usuario ya tiene una tarjeta.
	 */
	public class ClienteYaTieneTarjeta extends RuntimeException {
	}

	/**
	 * lanza excepcion si el tipo de tarjeta no es valido.
	 */
	public class TipoNoValido extends RuntimeException {
	}

	/**
	 * excepcion si el dni del cliente no existe.
	 */
	public class ClienteNoExiste extends RuntimeException {
	}

	private ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();	

	/**
	 * Metodo que añade una tarjeta.
	 * @param dni dni del dueño
	 * @param direccion direccion del dueño (Solo vn o vf)
	 * @param tipo tipo de tarjeta (B=Basica, VN= viajes normal, VF= viajes familia)
	 * @throws ClienteYaTieneTarjeta si el cliente ya tiene una tarjeta
	 * @throws TipoNoValido si el tipo de tarjeta no es valido
	 */
	public void registraTarjeta(String dni, String direccion, String tipo) 
			throws ClienteYaTieneTarjeta, TipoNoValido {
		if (buscaTarjeta(dni) != null) {
			throw new ClienteYaTieneTarjeta();
		}
		if (tipo.equals("B")) {
			TarjetaBasica t = new TarjetaBasica(dni);
			tarjetas.add(t);
		} else if (tipo.equals("VN")) {
			TarjetaViajesNormal t = new TarjetaViajesNormal(dni, direccion);
			tarjetas.add(t);
		} else if (tipo.equals("VF")) {
			TarjetaViajesFamilia t = new TarjetaViajesFamilia(dni, direccion);
			tarjetas.add(t);
		} else {
			throw new TipoNoValido();
		}
	}

	/**
	 * recarga el saldo de la tarjeta dada.
	 * @param dni dni del cliente
	 * @param saldo saldo a recargar
	 * @throws ClienteNoExiste si el cliente no tiene tarjeta
	 */
	public void recargaSaldo(String dni, double saldo) throws ClienteNoExiste {
		Tarjeta t = buscaTarjeta(dni);
		if (t == null) {
			throw new ClienteNoExiste();
		}
		t.anhadeSaldo(saldo);
	}

	/**
	 * realiza compra con la tarjeta especificada.
	 * @param dni dni del cliente
	 * @param importe importe de la compra
	 * @throws ClienteNoExiste si el cliente no tiene tarjeta
	 * @throws SaldoInsuficiente si no tiene saldo suficiente
	 */
	public void realizaCompra(String dni, double importe) 
			throws ClienteNoExiste, SaldoInsuficiente {
		Tarjeta t = buscaTarjeta(dni);
		if (t == null) {
			throw new ClienteNoExiste();
		}
		t.realizaCompra(importe);
	}

	/**
	 * metodo que canjea puntos de viaje.
	 * @param dni dni del dueño
	 * @param puntos puntos a canjear
	 * @throws ClienteNoExiste si el cliente no tiene tarjeta
	 * @throws PuntosInsuficientes si no hay puntos suficientes
	 * @throws TipoNoValido si la tarjeta del cliente no es de viajes
	 */
	public void canjeaPuntosViajes(String dni, int puntos) 
			throws ClienteNoExiste, PuntosInsuficientes, TipoNoValido {
		Tarjeta t = buscaTarjeta(dni);
		if (t == null) {
			throw new ClienteNoExiste();
		}
		if (t instanceof TarjetaBasica) {
			throw new TipoNoValido();
		}
		((TarjetaViajes) t).gastaPuntosViaje(puntos);
	}

	/**
	 * metodo que busca una tarjeta a partir de un dni.
	 * @param dni dni del dueño
	 * @return la tarjeta o null ni no existe
	 */
	public Tarjeta buscaTarjeta(String dni) {
		for (Tarjeta t: tarjetas) {
			if (t.dni().equals(dni)) {
				return t;
			}
		}
		return null;
	}
}
