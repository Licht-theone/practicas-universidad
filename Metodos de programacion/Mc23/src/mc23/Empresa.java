package mc23;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import mc23.Cliente.MaxImporteCompra;
@SuppressWarnings("serial")
public class Empresa {
	public class ClienteNoExiste extends RuntimeException{}
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Compra> compras = new ArrayList<Compra>();

	public Empresa(String nomFich) throws FileNotFoundException{
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			in.useLocale(Locale.ENGLISH);
			String id;
			String nombre;
			double maxCompra;
			while (in.hasNext()) {
				id = in.next();
				if (in.hasNextDouble()) {
					maxCompra = in.nextDouble();
					ClienteNormal c = new ClienteNormal(id, maxCompra);
					clientes.add(c);
				} else {
					nombre = in.nextLine();
					ClientePremium c = new ClientePremium(id, nombre);
					clientes.add(c);
				}
			}
		}
	}

	public void realizaCompra(String id, Compra c) throws MaxImporteCompra, ClienteNoExiste{
		Cliente cl = buscaCliente(id);
		if (cl == null) {
			throw new ClienteNoExiste();
		}
		cl.realizaCompra(c);
		compras.add(c);
	}

	public Cliente buscaCliente(String id) {
		for (Cliente c: clientes) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public Compra buscaCompra(String nombre) {
		for (Compra c: compras) {
			if (c.nombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}

	public double porcentDescAct(String id) throws ClienteNoExiste{
		Cliente c = buscaCliente(id);
		if (c == null) {
			throw new ClienteNoExiste();
		}
		return c.descuento();
	}

	public double consultaPago(String id) throws ClienteNoExiste{
		Cliente c = buscaCliente(id);
		if (c == null) {
			throw new ClienteNoExiste();
		}
		return c.pagaCompras();
	}

	public Cliente clientePremMasCompras() {
		int max = Integer.MIN_VALUE;
		int nuevoMax;
		Cliente cl = null;
		for (Cliente c: clientes) {
			nuevoMax = c.numCompras();
			if (nuevoMax > max) {
				max = nuevoMax;
				cl = c;
			}
		}
		return cl;
	}
}
