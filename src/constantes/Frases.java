package constantes;

import java.util.Random;

public class Frases {

	// TODO Escribir frases parecidas para que no parezca tan monótono el sistema.
	// TODO POR FAVOR ESCRIBIR LAS FRASES SIN ACENTOS (NI Ñ), ES UN HORROR EL CHAT. GRACIAS!
	
	// EJEMPLOS : Estos son ejemplos de creación de frases aleatorias !!!!!
	public static final String FrasesDePrueba(){
		String[] frases = new String[]{
				"Esta es una frase de prueba.",
				"Esta es una oracion de prueba."
				};
		return frases[new Random().nextInt(frases.length)];
	}
	public static final String FrasesDePruebaConParametros(String... parametros){
		String[] frases = new String[]{
				"Esta es una frase de prueba. " + "El sexo del usuario es: " + parametros[0] + ". La edad del usuario es: " + parametros[1] + ".",
				"Esta es una oracion de prueba. " + "El usuario es un " + parametros[0] + ". El usuario tiene " + parametros[1] + " anios."
				};		
		return frases[new Random().nextInt(frases.length)];
	};
	
	
	


	public static final String NoEntendido(){
		String[] frases = new String[]{
				"No he entendido lo que me has dicho.",
				"Perdona, pero no he entendido bien.",
				"Lo siento, no he comprendido bien.",
				"Perdona, no comprendo qué quieres decir.",
				"No he entendido lo que me has dicho, repitemelo.",
				"Ehhmmm... no he entendido bien lo que has dicho.",
				"Qué dices? No te he entendido.",
				"No comprendo, qué quieres decir?",
				"Cómo?",
				"Qué?"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static final String SolicitarGenero(){
		String[] frases = new String[]{
				"Podrias decirme el genero que te gustaria ver?",
				"De que genero te apetece ver la peli?",
				"Dime un genero y te recomiendo una pelicula",
				"Si me dices un genero te recomiendo una peli"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String SolicitarEdad() {
		String[] frases = new String[]{
				"Dime que edad tienes",
				"Cuantos anios tienes?",
				"Dime tu edad",
				"Me dices tu edad?",
				"Me puedes decir cuántos años tienes?"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String SolicitarResponderPreguntasFacil() {
		String[] frases = new String[]{
				"Vamos a hacerlo facil, yo voy a intentar recomendarte una peli, tu responde a mis preguntas",
				"Vamos a centrarnos, solo se de peliculas, intenta responder a lo que te pregunto",
				"A ver si nos ponemos de acuerdo, nada mas que entiendo de pelis, porfa responde a las preguntas"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String NoNosEntendemosDarPorVencido() {
		String[] frases = new String[]{
				"Parece que no hay forma de que nos entendamos, apaga el sistema. Adios.",
				"Lo siento, pero no terminamos de ponernos de acuerdo, lo mejor es que lo dejemos. Chao",
				"No conseguimos llegar a un acuerdo, deberiamos intentarlo en otra ocasion. Que te vaya bien"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
}
