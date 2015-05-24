package constantes;

import java.util.Random;

public class Frases {

	// TODO Escribir frases parecidas para que no parezca tan mon�tono el sistema.
	// TODO POR FAVOR ESCRIBIR LAS FRASES SIN ACENTOS (NI �), ES UN HORROR EL CHAT. GRACIAS!
	
	// EJEMPLOS : Estos son ejemplos de creaci�n de frases aleatorias !!!!!
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
				"Esta es una oracion de prueba. " + "El usuario es un " + parametros[0] + ". El usuario tiene " + parametros[1] + " a�os."
				};		
		return frases[new Random().nextInt(frases.length)];
	};
	
	
	


	public static final String NoEntendido(){
		String[] frases = new String[]{
				"No he entendido lo que me has dicho.",
				"Perdona, pero no he entendido bien.",
				"Lo siento, no he comprendido bien.",
				"Perdona, no comprendo qu� quieres decir.",
				"No he entendido lo que me has dicho, rep�temelo.",
				"Ehhmmm... no he entendido bien lo que has dicho.",
				"�Qu� dices? No te he entendido.",
				"No comprendo, �qu� quieres decir?",
				"C�mo?",
				"Qu�?"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static final String SolicitarGenero(){
		String[] frases = new String[]{
				"�Podr�as decirme el g�nero que te gustar�a ver?",
				"�De qu� g�nero te apetece ver la pel�cula?",
				"Dime un g�nero y te recomiendo una pel�cula",
				"Si me dices un g�nero te recomiendo una pel�cula"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String SolicitarEdad() {
		String[] frases = new String[]{
				"Dime qu� edad tienes",
				"�Cuantos a�os tienes?",
				"Dime tu edad",
				"�Me dices tu edad?",
				"�Me puedes decir cu�ntos a�os tienes?"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String SolicitarResponderPreguntasFacil() {
		String[] frases = new String[]{
				"Vamos a hacerlo f�cil, yo voy a intentar recomendarte una peli, tu responde a mis preguntas",
				"Vamos a centrarnos, s�lo s� de peliculas, intenta responder a lo que te pregunto",
				"A ver si nos ponemos de acuerdo, nada m�s que entiendo de pel�culas, por favor responde a las preguntas"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String NoNosEntendemosDarPorVencido() {
		String[] frases = new String[]{
				"Parece que no hay forma de que nos entendamos, apaga el sistema. Adi�s.",
				"Lo siento, pero no terminamos de ponernos de acuerdo, lo mejor es que lo dejemos. Chao",
				"No conseguimos llegar a un acuerdo, deber�amos intentarlo en otra ocasi�n. Que te vaya bien"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String QuienSoy() {
		String[] frases = new String[]{
				"Yo me llamo Joker, soy un sistema multiagente de tu televisi�n inteligente"
				};
		return frases[new Random().nextInt(frases.length)];
	}
	
	public static String UtilidadDelSistema() {
		String[] frases = new String[]{
				"Puedo ayudarte a encontrar pel�culas que te apetezcan ver"
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
				"Si introduces un a�o te pondr� pel�culas s�lo de ese a�o",
				"Si me dices uno o varios actores intentar� que salgan",
				"Si me dices otro g�nero procurar� buscar pelis que mezclen todos los g�neros",
				"Puedes decirme g�neros, actores o a�os para buscar algo m�s concreto"
				};
		return frases[new Random().nextInt(frases.length)];
	}
}
