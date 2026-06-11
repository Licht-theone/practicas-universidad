package mc19;
@SuppressWarnings("serial")
public abstract class Vehiculo {
	public class NoEstacionado extends RuntimeException{}
	public class YaEstacionado extends RuntimeException{}
	protected final String matricula;
	protected Estacionamiento estacionado;
	/**
	 * @param matricula
	 * @param estacionado
	 */
	public Vehiculo(String matricula) {
		this.matricula = matricula;
		estacionado = null;
	}
	public Estacionamiento getEstacionado() {
		return estacionado;
	}
	public void setEstacionado(Estacionamiento estacionado) {
		this.estacionado = estacionado;
	}
	public String getMatricula() {
		return matricula;
	}
	public abstract void estacionaVehiculo(Estacionamiento e) throws YaEstacionado;
	public abstract void finalizaYCobra() throws NoEstacionado;
	public abstract double cobra();
}
