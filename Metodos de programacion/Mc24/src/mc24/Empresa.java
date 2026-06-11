package mc24;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Empresa {
	public class VehiculoNoEncontrado extends RuntimeException{}
	public class AlquilerNoExiste extends RuntimeException{}
	public class AlquilerYaFinalizado extends RuntimeException{}
	private ArrayList<Alquiler> registro;
	private ArrayList<Vehiculo> vehiculos;

	public Empresa(String nomFich) throws FileNotFoundException{
		registro = new ArrayList<Alquiler>();
		vehiculos = new ArrayList<Vehiculo>();
		try (Scanner in = new Scanner(new FileReader(nomFich))){
			String matricula;
			int plazas;
			in.next();
			while (!in.hasNext("Lujo")) {
				matricula = in.next();
				plazas = in.nextInt();
				VehiculoNormal v = new VehiculoNormal(matricula, plazas);
				vehiculos.add(v);
			}
			in.next();
			while (in.hasNext()) {
				matricula = in.next();
				plazas = in.nextInt();
				VehiculoLujo v = new VehiculoLujo(matricula, plazas);
				vehiculos.add(v);
			}
		}
	}
	
	public int alquilaVehiculo(String dni, int minPlazas, boolean normales) throws VehiculoNoEncontrado{
		Vehiculo v;
		if (normales) {
			v = buscaPorPlazasNorm(minPlazas);
		} else {
			v = buscaPorPlazas(minPlazas);
		}
		if (v == null) {
			throw new VehiculoNoEncontrado();
		}
		Alquiler a = new Alquiler(v, dni);
		registro.add(a);
		return a.getId();
	}
	
	public Alquiler estadoVehi(String mat) throws VehiculoNoEncontrado{
		Vehiculo v = buscaVehiculo(mat);
		if (v == null) {
			throw new VehiculoNoEncontrado();
		}
		Alquiler al = null;
		for (Alquiler a: registro) {
			if (a.getVehiculo().equals(v)) {
				al = a;
			}
		}
		return al;
	}
	
	public Alquiler ultimoAlquiler(String dni) throws AlquilerNoExiste{
		Alquiler a = null;
		for (int i = registro.size() - 1; i >= 0; i--) {
			if (registro.get(i).getDni().equals(dni)) {
				a = registro.get(i);
				break;
			}
		}
		if (a == null) {
			throw new AlquilerNoExiste();
		}
		return a;
	}
	
	public void informeAlquileres(String mat) throws VehiculoNoEncontrado, IOException{
		Vehiculo v = buscaVehiculo(mat);
		if (v == null) {
			throw new VehiculoNoEncontrado();
		}
		try (PrintWriter out = new PrintWriter(new FileWriter(mat + ".txt"))) {
			for (Alquiler a: registro) {
				if (a.getVehiculo().equals(v)) {
					out.printf("Id: %d DNI: %s", a.getId(), a.getDni());
				}
			}
		}
	}
	
	public double finalizaAlqu(int id, int dias) throws AlquilerNoExiste, AlquilerYaFinalizado{
		Alquiler a = buscaAlq(id);
		if (a == null) {
			throw new AlquilerNoExiste();
		}
		if (a.isFinalizado()) {
			throw new AlquilerYaFinalizado();
		}
		a.finaliza();
		return dias * (a.getVehiculo().precio);
	}
	
	public Vehiculo buscaPorPlazas(int plazas) {
		for (Vehiculo v: vehiculos) {
			if (v.getPlazas() >= plazas && !v.isAlquilado()) {
				return v;
			}
		}
		return null;
	}
	
	public Vehiculo buscaPorPlazasNorm(int plazas) {
		for (Vehiculo v: vehiculos) {
			if (v.getPlazas() >= plazas && v instanceof VehiculoNormal && !v.isAlquilado()) {
				return v;
			}
		}
		return null;
	}
	
	public Vehiculo buscaVehiculo(String mat) {
		for (Vehiculo v: vehiculos) {
			if (v.getMatricula().equals(mat)) {
				return v;
			}
		}
		return null;
	}
	
	public Alquiler buscaAlq(int id) {
		for (Alquiler a: registro) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
}
