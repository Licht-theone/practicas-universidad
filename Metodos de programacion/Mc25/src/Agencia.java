import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Agencia {
	private ArrayList<Cliente> clientes;
	private ArrayList<Hotel> hoteles;
	/**
	 * 
	 */
	public Agencia(String nomFich) throws FileNotFoundException{
		try (Scanner in = new Scanner(new FileReader(nomFich))){
			String dni;
			double descuento;
			while (in.hasNext()) {
				dni = in.next();
				if (in.hasNextDouble()) {
					descuento = in.nextDouble();
					ClienteEmpresa c = new ClienteEmpresa(dni, descuento);
					clientes.add(c);
				} else if (in.hasNext("Asoc")){
					ClienteAsociado c = new ClienteAsociado(dni);
					clientes.add(c);
				} else {
					ClienteNormal c = new ClienteNormal(dni);
					clientes.add(c);
				}
			}
		}
	}
	
}
