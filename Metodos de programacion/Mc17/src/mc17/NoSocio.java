package mc17;

public class NoSocio extends Usuario{
	private int librosMes = 0;
	public NoSocio(String dni) {
		super(dni);
	}
	
	public boolean prestaLibro(Libro l){
		if(!l.prestaLibro()) {
			return false;
		}
		librosMes++;
		return true;
	}
	
	public boolean devuelveLibro(Libro l) {
		if (!l.devuelveLibro()) {
			return false;
		}
		return true;
	}
	public double precioMes() {
		return PRECIO_POR_LIBRO * librosMes;
	}
}
