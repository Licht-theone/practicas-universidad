package mc17;

import java.util.ArrayList;

public class Gimnasio {
	private ArrayList<Socio> socios = new ArrayList<Socio>();
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	public Socio buscaPorDni(String dni) {
		for (Socio s: socios) {
			if (s.getDni().equals(dni)) {
				return s;
			}
		}
		return null;
	}
	
	public Curso buscaCurso(String nombre) {
		for (Curso c: cursos) {
			if (c.getNombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean anhadeCurso(Curso c) {
		if (buscaCurso(c.getNombre()) != null) {
			return false;
		}
		cursos.add(c);
		return true;
	}
	
	public boolean anhadeSocio(Socio s) {
		if (buscaPorDni(s.getDni()) != null) {
			if (s instanceof SocioInfantil && ((SocioInfantil) s).asociado() == null) {
				return false;
			}
			return false;
		}
		socios.add(s);
		return true;
	}
	
	public boolean anhadeCursoASocio(String dni, String nombre) {
		Socio s = buscaPorDni(dni);
		Curso c = buscaCurso(nombre);
		if (s == null || c == null) {
			return false;
		}
		s.anhadeCurso(c);
		return true;
	}
	
	public String informeSocios() {
		String s = "";
		for (Socio so: socios) {
			s += " " + so.toString(); 
		}
		return s;
	}
}
