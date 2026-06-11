package mc4;

public class Avion implements Comparable<Avion> {
	private final String id;

	/**
	 * @param id id
	 */
	public Avion(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public int compareTo(Avion otro) {
		return this.id.compareTo(otro.getId());
	}

	@Override
	public String toString() {
		return "Avion [id=" + id + "]\n";
	}

}
