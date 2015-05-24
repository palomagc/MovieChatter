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
				"Esta es una oracion de prueba. " + "El usuario es un " + parametros[0] + ". El usuario tiene " + parametros[1] + " años."
				};		
		return frases[new Random().nextInt(frases.length)];
	};
	
	
	


	public static final String NoEntendido(){
		String[] frases = new String[]{
				"No he entendido lo que me has dicho.",
				"Perdona, pero no he entendido bien.",
				"Lo siento, no he comprendido bien.",
				"Perdona, no comprendo qué quieres decir.",
				"No he entendido lo que me has dicho, repítemelo.",
				"Ehhmmm... no he entendido bien lo que has dicho.",
				"¿Qué dices? No te he entendido.",
				"No comprendo, ¿qué quieres decir?",
				"Cómo?",
				"Qué?"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static final String SolicitarGenero(){
		String[] frases = new String[]{
				"¿Podrías decirme el género que te gustaría ver?",
				"¿De qué género te apetece ver la película?",
				"Dime un género y te recomiendo una película",
				"Si me dices un género te recomiendo una película"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String SolicitarEdad() {
		String[] frases = new String[]{
				"Dime qué edad tienes",
				"¿Cuantos años tienes?",
				"Dime tu edad",
				"¿Me dices tu edad?",
				"¿Me puedes decir cuántos años tienes?"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String SolicitarResponderPreguntasFacil() {
		String[] frases = new String[]{
				"Vamos a hacerlo fácil, yo voy a intentar recomendarte una peli, tu responde a mis preguntas",
				"Vamos a centrarnos, sólo sé de peliculas, intenta responder a lo que te pregunto",
				"A ver si nos ponemos de acuerdo, nada más que entiendo de películas, por favor responde a las preguntas"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String NoNosEntendemosDarPorVencido() {
		String[] frases = new String[]{
				"Parece que no hay forma de que nos entendamos, apaga el sistema. Adiós.",
				"Lo siento, pero no terminamos de ponernos de acuerdo, lo mejor es que lo dejemos. Chao",
				"No conseguimos llegar a un acuerdo, deberíamos intentarlo en otra ocasión. Que te vaya bien"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String QuienSoy() {
		String[] frases = new String[]{
				"Yo me llamo Joker, soy un sistema multiagente de tu televisión inteligente"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String UtilidadDelSistema() {
		String[] frases = new String[]{
				"Puedo ayudarte a encontrar películas que te apetezcan ver"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String Recomienda() {
		String[] frases = new String[]{
				"Podrias ver ",
				"Te sugiero ",
				"Te propongo "
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String Params() {
		String[] frases = new String[]{
				"Si introduces un año te pondré películas sólo de ese año",
				"Si me dices uno o varios actores intentaré que salgan",
				"Si me dices otro género procuraré buscar pelis que mezclen todos los géneros",
				"Puedes decirme géneros, actores o años para buscar algo más concreto"
				};
		return frases[new Random().nextInt(frases.length)];
	}
}
