package modelo;
import javax.swing.JOptionPane;

public class Principal1 {

	public static void main(String[] args) {
		//  Auto-generated method stub
		Contacto c1 = new Contacto ("Aaron", "12314134", "elevergo@gemail.com");
		c1.setEmail("aaronalegria19@gmail.com");
		c1.setTlf("603035675");
		System.out.println(c1); //syso
		//JOptionpane.
		JOptionPane.showMessageDialog(null, c1);
		Agenda ag = new Agenda(5);
		ag.atexto();
	}

}
