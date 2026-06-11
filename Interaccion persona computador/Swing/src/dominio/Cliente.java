package dominio;

public class Cliente {
	private String nombre;
	private String apellidos;
	private String provincia;
	//Constructores
	Cliente () {}
	public Cliente (String nomb, String apell, String prov)
	{
	nombre = nomb;
	apellidos = apell;
	provincia = prov;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos + ", provincia=" + provincia + "]";
	}
	//Métodos set
	public void setNombre(String nomb){nombre = nomb;}
	public void setApellidos(String apell){apellidos = apell;}
	public void setProvincia(String prov){provincia = prov;}
	//Métodos get
	public String getNombre(){return nombre;}
	public String getApellidos(){return apellidos;}
	public String getProvincia(){return provincia;}
}
