package mc5;

import java.util.ArrayList;

public class HistoriaEconomica {
	private ArrayList<DatosEconomicos> lista;

	public HistoriaEconomica() {
		lista = new ArrayList<>();
	}

	public void anhadeDatos(DatosEconomicos d) {
		//puede dar errores (mismo nombre o año incorrecto)
		lista.add(d);
	}

	public int numDatos() {
		return lista.size();
	}

	public boolean hayIpcSup(int ipc) {
		for (DatosEconomicos datos: lista) {
			if (datos.getIpc() < ipc) {
				return true;
			}
		}
		return false;
	}

	public int añoIpcMaxLocal(String codigo) {
		DatosEconomicos anoAnt = null;
		DatosEconomicos anoAct = null; 
		DatosEconomicos anoPos = null;
		for (DatosEconomicos dato: lista) {
			if (dato.getNombrePais().equals(codigo)) {
				if (anoAct != null && anoAnt != null && anoPos != null) {
					if (anoAct.getIpc() > anoAnt.getIpc() && anoAct.getIpc() > anoPos.getIpc()) {
						return anoAct.getAno();
					}
				}
				if (anoAnt == null) {
					anoAnt = dato;
				}
				else if (anoAct == null) {
					anoAct = dato;
				}
				else {
					anoPos = dato;
				}

			}
		}

		return -1;
	}

	public String paisIpcMax(int anoInic, int anoFin) {
		DatosEconomicos d = null;
		double max = 0;
		double nuevoMax = 0;
		for (DatosEconomicos dato: lista) {
			if (dato.getAno() >= anoInic && dato.getAno() <= anoFin) {
				nuevoMax = dato.getIpc();
				if (nuevoMax > max) {
					max = nuevoMax;
					d = dato;
				}
			}
		}
		return d.getNombrePais();
	}

	private DatosEconomicos buscaPais (String codigo) {
		DatosEconomicos p = null;
		for (DatosEconomicos dato: lista) {
			if (dato.getNombrePais().equals(codigo)) {
				p = dato;
			}
		}

		return p;
	}
}
