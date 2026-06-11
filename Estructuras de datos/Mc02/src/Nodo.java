
public class Nodo {
	private String info;
	private Nodo sig;
	
	public Nodo(String info, Nodo sig) {
		super();
		this.info = info;
		this.sig = sig;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Nodo getSig() {
		return sig;
	}

	public void setSig(Nodo sig) {
		this.sig = sig;
	}

	@Override
	public String toString() {
		return "Nodo [info=" + info + "]";
	}
	
}
