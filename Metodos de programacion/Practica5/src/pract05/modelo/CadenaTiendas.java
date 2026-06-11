package pract05.modelo;

import java.util.ArrayList;


/**
 * Cadena de tiendas con tarjetas de descuento.
 * 
 * @author  Metodos de Programacion (UC) y Aaron Alegria
 * @version mar-22
 */
public class CadenaTiendas {
	private ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();

	/**
	 * Anhade una tarjeta.
	 * @param tarjeta tarjeta a anhadir.
	 */
	public void anhadeTarjeta(Tarjeta tarjeta) {
		tarjetas.add(tarjeta);
	}

	/**
	 * Registra una compra para la tarjeta con el codigo indicado.
	 * @param codTarjeta codigo de la tarjeta a la que asignar la compra.
	 * @param compra compra realizada.
	 * @return true si la compra ha sido asignada con exito y false si no
	 * existe ninguna tarjeta con ese codigo.
	 */
	public boolean registraCompra(String codTarjeta, Compra compra) {
		Tarjeta t = buscaTarjeta(codTarjeta);
		if (t != null) {
			t.registraCompra(compra);
			return true;
		}
		return false;
	}

	/**
	 * Busca la tarjeta con el codigo indicado.
	 * @param codTarjeta codigo de la tarjeta buscada.
	 * @return la tarjeta con el codigo indicado o null si no existe ninguna
	 * tarjeta con ese codigo.
	 */
	public Tarjeta buscaTarjeta(String codTarjeta) {
		for (Tarjeta tarjeta: tarjetas) {
			if (tarjeta.codigo().equals(codTarjeta)) {
				return tarjeta;
			}
		}
		return null;
	}

	/**
	 * Retorna la compra que ocupa la posicion indicada en la tarjeta
	 * correspondiente al codigo pasado como parametro.
	 * @param codTarjeta codigo de la tarjeta en la que buscar la compra.
	 * @param posCompra posicion de la compra en el historico de compras de
	 * la tarjeta.
	 * @return la compra buscada o null si no existe ninguna tarjeta con el codigo
	 * indicado o si la posicion no corresponde a ninguna compra.
	 */
	public Compra buscaCompraDeTarjeta(String codTarjeta, int posCompra) {
		Tarjeta t = buscaTarjeta(codTarjeta);
		if (t != null) {
			return t.compraEnPos(posCompra);
		}
		return null;
	}

}
