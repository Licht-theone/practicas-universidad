package mc5;

public class DatosEconomicos {
	private static int codigoPais = 1;
	private final String nombrePais;
	private final double pib;
	private final double ipc;
	private final int ano;

	/**
	 * @param nombrePais
	 * @param pib
	 * @param ipc
	 * @param ano
	 */
	public DatosEconomicos(double pib, double ipc, int ano) {
		nombrePais = "PAIS" + codigoPais;
		codigoPais++;
		this.pib = pib;
		this.ipc = ipc;
		this.ano = ano;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public double getPib() {
		return pib;
	}

	public double getIpc() {
		return ipc;
	}

	public int getAno() {
		return ano;
	}

	@Override
	public String toString() {
		return "DatosEconomicos [nombrePais=" + nombrePais + ", pib=" + pib + ", ipc=" + ipc + ", ano=" + ano + "]";
	}

}
