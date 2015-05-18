package constantes;

import java.util.Random;

public class Frases {

	// TODO Escribir frases parecidas para que no parezca tan monótono el sistema.
	
	// Estos son ejemplos de creación de frases aleatorias.
	public static final String FrasesDePrueba(){
		String[] frases = new String[]{
		"Esta es una frase de prueba.",
		"Esta es una oración de prueba."
				};
		return frases[new Random().nextInt(frases.length)];
	}
	public static final String FrasesDePruebaConParametros(String... parametros){
		String[] frases = new String[]{
				"Esta es una frase de prueba. " + "El sexo del usuario es: " + parametros[0] + ". La edad del usuario es: " + parametros[1] + ".",
				"Esta es una oración de prueba. " + "El usuario es un " + parametros[0] + ". El usuario tiene " + parametros[1] + " años."
				};		
		return frases[new Random().nextInt(frases.length)];
	};
	
	
}
