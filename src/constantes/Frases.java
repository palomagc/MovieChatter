package constantes;

import java.util.Random;

public class Frases {

	// TODO Escribir frases parecidas para que no parezca tan mon�tono el sistema.
	
	// Estos son ejemplos de creaci�n de frases aleatorias.
	public static final String FrasesDePrueba(){
		String[] frases = new String[]{
		"Esta es una frase de prueba.",
		"Esta es una oraci�n de prueba."
				};
		return frases[new Random().nextInt(frases.length)];
	}
	public static final String FrasesDePruebaConParametros(String... parametros){
		String[] frases = new String[]{
				"Esta es una frase de prueba. " + "El sexo del usuario es: " + parametros[0] + ". La edad del usuario es: " + parametros[1] + ".",
				"Esta es una oraci�n de prueba. " + "El usuario es un " + parametros[0] + ". El usuario tiene " + parametros[1] + " a�os."
				};		
		return frases[new Random().nextInt(frases.length)];
	};
	
	
}
