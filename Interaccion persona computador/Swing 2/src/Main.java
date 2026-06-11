import java.util.HashMap;
import java.util.Locale;
import java.util.Locale.Builder;
import java.util.Scanner;
@SuppressWarnings("unused")
public class Main {

	public static Locale localizacion;
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Seleccione lenguage/Select language (en/es)");
		String idioma = teclado.next();
		if (idioma.equals("es")) {
			localizacion = new Locale.Builder().setLanguage("es").setRegion("ES").build();
		} else if (idioma.equals("en")) {
			localizacion = new Locale.Builder().setLanguage("en").setRegion("US").build();
		} else {
			System.out.println("error");
			return;
		}
		
		VentanaPrincipal vp = new VentanaPrincipal();
	}
}
