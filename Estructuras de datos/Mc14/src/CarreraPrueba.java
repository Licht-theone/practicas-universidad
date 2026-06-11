

public class CarreraPrueba {
	
	public static void main (String[] args) {
		Carrera car = new Carrera(); 
		
		car.anhadeParticipante(new Participante());
		car.anhadeParticipante(new Participante());
		car.anhadeParticipante(new Participante());
		car.anhadeParticipante(new Participante());
		
		Equipo e1 = new Equipo("A");
		Equipo e2 = new Equipo("B");

		car.anhadeEquipo(e1);
		car.anhadeEquipo(e2);
		
		// p dorsal 1 -> equipo 1 
		// p dordal 2 -> equipo 2 
		// p dorsal 3 -> equipo 3
		// p dorsal 4 -> equipo 4
		car.asignaParticipanteAEquipo(1, e1.nombre());
		car.asignaParticipanteAEquipo(2, e2.nombre());
		car.asignaParticipanteAEquipo(3, e1.nombre());
		car.asignaParticipanteAEquipo(4, e2.nombre());
	
		System.out.println(e1 + " ");
		
		// acabar carrera
		car.participanteLlegaAMeta(1, 1.5); // participante con dorsal 1 llega
		car.participanteLlegaAMeta(3, 2.2); // participante con dorsal 3 llega
		car.participanteLlegaAMeta(2, 1.6); // participante con dorsal 2 llega
		car.participanteLlegaAMeta(4, 1.8); // participante con dorsal 4 llega
		System.out.println(car.muestraClasificacionEquipos());
		
	}
	
}