package mc13;

import java.util.ArrayList;

public class Empresa {
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<Encargado> encargados = new ArrayList<Encargado>();
	
	public boolean anhadeEmpleado(Empleado e, String dniEncar) {
		if (buscaEmpleado(e.getDni()) != null) {
			return false;
		}
		Encargado a = buscaEncargado(dniEncar);
		if (a == null) {
			return false;
		}
		a.anhadeEmpleado();
		empleados.add(e);
		return true;
	}
	
	public boolean asignaInst(Instalacion i, String dni) {
		Empleado a = buscaEmpleado(dni);
		if (a == null) {
			Encargado b = buscaEncargado(dni);
			if (b == null) {
				return false;
			}
			else {
				b.anhadeInstalacion(i);
				return true;
			}
		}
		else {
			a.anhadeInstalacion(i);
			return true;
		}
	}
	
	public boolean anhadeEncargado(Encargado e) {
		if (buscaEncargado(e.getDni()) != null) {
			return false;
		}
		encargados.add(e);
		return true;
	}
	
	public Empleado buscaEmpleado(String dni) {
		for (Empleado e: empleados) {
			if (e.getDni().equals(dni)) {
				return e;
			}
		}
		return null;
	}
	
	public Encargado buscaEncargado(String dni) {
		for (Encargado e: encargados) {
			if (e.getDni().equals(dni)) {
				return e;
			}
		}
		return null;
	}
}
