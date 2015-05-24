/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.informacion;

import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constantes.Constantes;

/**
 *
 * @author FGarijo
 */
public class Vocabulario {

	public static class Genero {
		String english;
		String spanish;

		Genero(String english, String spanish) {
			this.english = english;
			this.spanish = spanish;
		}

		public String getEnglish() {
			return english;
		}

		public String getSpanish() {
			return spanish;
		}
	}

	// OBJETOS ESTATICOS
	public static Usuario usuario = null;
	public static Busqueda busqueda = new Busqueda();

	// TODO Añadir aquí las anotaciones que quieres que se tengan en cuenta.
	// NOMBRES NOTIFICACIONES
	public static final List<String> NombresTipoNotificacion = Arrays.asList("Saludo", "Despedida",
			"Afirmacion", "Negacion", "SexoHombre", "SexoMujer", "Generos", "GeneroAccion",
			"GeneroAventura", "GeneroAnimacion", "GeneroComedia", "GeneroCrimen",
			"GeneroDocumental", "GeneroDrama", "GeneroFamiliar", "GeneroFantasia",
			"GeneroExtranjero", "GeneroHistorico", "GeneroTerror", "GeneroMusical",
			"GeneroMisterio", "GeneroRomantico", "GeneroCienciaFiccion", "GeneroTV",
			"GeneroSuspense", "GeneroGuerra", "GeneroOeste", "Actor", "Numero", "Anos", "YaVista",
			"Orden", "Dinamico", "Nota", "Tonterias", "Argumento", "ValoracionPositiva",
			"ValoracionNegativa", "Quien", "Utilidad");

	public static final String NombreTipoNotificacionSaludo = "Saludo";
	public static final String NombreTipoNotificacionDespedida = "Despedida";
	public static final String NombreTipoNotificacionAfirmacion = "Afirmacion";
	public static final String NombreTipoNotificacionNegacion = "Negacion";
	public static final String NombreTipoNotificacionSexoHombre = "SexoHombre";
	public static final String NombreTipoNotificacionSexoMujer = "SexoMujer";
	public static final String NombreTipoNotificacionActor = "Actor";
	public static final String NombreTipoNotificacionAnos = "Anos";
	public static final String NombreTipoNotificacionNumero = "Numero";
	public static final String NombreTipoNotificacionYaVista = "YaVista";
	public static final String NombreTipoNotificacionOrden = "Orden";
	public static final String NombreTipoNotificacionDinamico = "Dinamico";
	public static final String NombreTipoNotificacionNota = "Nota";
	public static final String NombreTipoNotificacionTonterias = "Tonterias";
	public static final String NombreTipoNotificacionArgumento = "Argumento";
	public static final String NombreTipoNotificacionValoracionPositiva = "ValoracionPositiva";
	public static final String NombreTipoNotificacionValoracionNegativa = "ValoracionNegativa";
	// TODO recomienda y quien no estan en la lista de arriba RECORDATORIO
	public static final String NombreTipoNotificacionRecomienda = "Recomienda";
	public static final String NombreTipoNotificacionQuien = "Quien";
	public static final String NombreTipoNotificacionUtilidad = "Utilidad";
	// TODO objetivos, no notificaciones
	public static final String NombreTipoNotificacionCitasConGuia = "CitasConGuia";
	public static final String NombreTipoNotificacionComprobarDatosBusqueda = "ComprobarDatosBusqueda";
	public static final String NombreTipoNotificacionValorarUltimaPelicula = "ValorarUltimaPelicula";
	public static final String NombreTipoNotificacionBusquedaVariosCampos = "BusquedaVariosCampos";

	public static final String NombreTipoNotificacionGeneros = "Generos";
	public static final String NombreTipoNotificacionGeneroAccion = "GeneroAccion";
	public static final String NombreTipoNotificacionGeneroAventura = "GeneroAventura";
	public static final String NombreTipoNotificacionGeneroAnimacion = "GeneroAnimacion";
	public static final String NombreTipoNotificacionGeneroComedia = "GeneroComedia";
	public static final String NombreTipoNotificacionGeneroCrimen = "GeneroCrimen";
	public static final String NombreTipoNotificacionGeneroDocumental = "GeneroDocumental";
	public static final String NombreTipoNotificacionGeneroDrama = "GeneroDrama";
	public static final String NombreTipoNotificacionGeneroFamiliar = "GeneroFamiliar";
	public static final String NombreTipoNotificacionGeneroFantasia = "GeneroFantasia";
	public static final String NombreTipoNotificacionGeneroExtranjero = "GeneroExtranjero";
	public static final String NombreTipoNotificacionGeneroHistorico = "GeneroHistorico";
	public static final String NombreTipoNotificacionGeneroTerror = "GeneroTerror";
	public static final String NombreTipoNotificacionGeneroMusical = "GeneroMusical";
	public static final String NombreTipoNotificacionGeneroMisterio = "GeneroMisterio";
	public static final String NombreTipoNotificacionGeneroRomantico = "GeneroRomantico";
	public static final String NombreTipoNotificacionGeneroCienciaFiccion = "GeneroCienciaFiccion";
	public static final String NombreTipoNotificacionGeneroTV = "GeneroTV";
	public static final String NombreTipoNotificacionGeneroSuspense = "GeneroSuspense";
	public static final String NombreTipoNotificacionGeneroGuerra = "GeneroGuerra";
	public static final String NombreTipoNotificacionGeneroOeste = "GeneroOeste";

	public static final String[] NombresTipoNotificacionGenero = { "GeneroAccion",
			"GeneroAventura", "GeneroAnimacion", "GeneroComedia", "GeneroCrimen",
			"GeneroDocumental", "GeneroDrama", "GeneroFamiliar", "GeneroFantasia",
			"GeneroExtranjero", "GeneroHistorico", "GeneroTerror", "GeneroMusical",
			"GeneroMisterio", "GeneroRomantico", "GeneroCienciaFiccion", "GeneroTV",
			"GeneroSuspense", "GeneroGuerra", "GeneroOeste" };

	public static final Map<String, Genero> Generos = new HashMap<String, Genero>();
	static {
		Generos.put("GeneroAccion", new Genero("Action", "acción"));
		Generos.put("GeneroAventura", new Genero("Adventure", "aventura"));
		Generos.put("GeneroAnimacion", new Genero("Animation", "animación"));
		Generos.put("GeneroComedia", new Genero("Comedy", "comedia"));
		Generos.put("GeneroCrimen", new Genero("Crime", "crimen"));
		Generos.put("GeneroDocumental", new Genero("Documentary", "documental"));
		Generos.put("GeneroDrama", new Genero("Drama", "drama"));
		Generos.put("GeneroFamiliar", new Genero("Family", "familiar"));
		Generos.put("GeneroFantasia", new Genero("Fantasy", "fantasía"));
		Generos.put("GeneroExtranjero", new Genero("Foreign", "extranjero"));
		Generos.put("GeneroHistorico", new Genero("History", "histórico"));
		Generos.put("GeneroTerror", new Genero("Horror", "terror"));
		Generos.put("GeneroMusical", new Genero("Music", "musical"));
		Generos.put("GeneroMisterio", new Genero("Mystery", "misterio"));
		Generos.put("GeneroRomantico", new Genero("Romance", "romántico"));
		Generos.put("GeneroCienciaFiccion", new Genero("Science Fiction", "ciencia ficción"));
		Generos.put("GeneroTV", new Genero("TV Movie", "tv"));
		Generos.put("GeneroSuspense", new Genero("Thriller", "suspense"));
		Generos.put("GeneroGuerra", new Genero("War", "guerra"));
		Generos.put("GeneroOeste", new Genero("Western", "oeste"));
	}

	// NOMBRE CHAT
	public static final String IdentConexionAgte = "AgteMovies";

	// NOMBRE AGENTES
	public static final String IdentAgenteAplicacionAcceso = "AgenteAplicacionAcceso1";
	public static final String IdentAgenteAplicacionDialogoCitas = "AgenteAplicacionDialogoCitas1";
	public static final String IdentAgenteAplicacionGuia = "AgenteAplicacionGuia1";
	public static final String IdentAgenteAplicacionUsuario = "AgenteAplicacionUsuario1";
	public static final String IdentAgenteAplicacionTMDB = "AgenteAplicacionTMDB1";

	// NOMBRE RECURSOS
	public static final String IdentRecursoVisualizacionAccesoInicial = "VisualizacionAcceso1";
	public static final String IdentRecursoVisualizacionAccesoAlta = "VisualizacionAccesoAlta1";
	public static final String IdentRecursoPersistenciaAcceso = "Persistencia1";
	public static final String IdentRecursoExtractorSemantico = "ExtractorSemantico1";
	public static final String IdentRecursoComunicacionChat = "ComunicacionChat1";
	public static final String IdentRecursoComunicacionTMDB = "ComunicacionTMDB1";
	public static final String IdentRecursoPersistenciaChat = "PersistenciaChat1";
	public static final String IdentRecursoUsuario = "RecursoUsuario1";

	// SALUDO
	public static final String SaludoInicial1 = "Hola, yo soy " + Constantes.SYSTEM_NAME
			+ ", tu recomendador de películas";
	public static final String[] SaludoInicial2 = { "Hola, ", "Qué tal, ", "Buenas, ", "Que hay, " };

	// FRASES DE NUESTRO SISTEMA
	public static final String[] Despedida = { "Adiós, ", "Hasta la próxima, ", "Nos vemos, ",
			"Que te vaya bien, ", "Ha sido un placer, ", "Cuando quieras repetimos, " };
	public static final String[] Disfruta = { "Que la disfrutes.", "Ya me contaras qué tal.",
			"Que te guste.", "Ojalá te guste.", "A por las palomitas." };
	public static final String InfoFuncionalidad = "Soy un cinéfilo, y me encantaría ayudarte a encontrar películas. ";
	public static final String[] PeticionInfoGeneral = { "¿Te puedo sugerir alguna hoy?",
			"¿Quieres que te recomiende alguna?", "¿Necesitas ayuda para encontrar alguna?" };
	public static final String EligeGenero = "¿De que genero te apetece ver la película?";
	// TODO mejorar la frase
	public static final String LimpiarBusqueda = "La consulta con los parámetros dados no ha obtenido ningún resultado."
			+ " Se limpian los parámetros de búsqueda.";
	public static final String NoConocemos = ", no nos conocemos.";
	public static final String Volverias = ", ¡sabía que volverías!";
	public static final String QueActor = "¿De qué actor te apetece ver la película?";
	public static final String NoTacos = "¡No digas esas cosas!";
	public static final String ErrorValoracion = "Ha ocurrido un error al solicitar la valoración";
	public static final String ErrorUltValoracion = "Ha ocurrido un error al solicitar la última valoración";
	public static final String[] Parecio = { "¿Qué te pareció ",
			"? Puedes ponerle una nota (del 0 al 5)" };
	public static final String[] ParecioUlt = {
			"Anda, parece que te has dejado una película sin valorar. ¿Qué opinas de ",
			"? Puedes ponerle una nota (del 0 al 5)" };
	public static final String NingunaPelicula = "Pero... ¡si no hemos dicho ninguna película...! ";
	public static final String NoSabria = "No sabría decirte";
	public static final String ResponderPar = "¿Te gustaría responder a un par de preguntas para conocernos mejor?";
	public static final String CuantosAnios = "¿Cuantos años tienes?";
	public static final String QueSexo = "¿Eres hombre o mujer?";
	public static final String[] Prometen = { "Me sé unas cuantas, las ", " que más prometen son ",
			", ", " y " };
	public static final String[] Valoraciones = { "Asi que no te ha gustado nada... Lo siento",
			"Yo creo que no estaba tan mal", "Hay películas mejores, claro",
			"Es... para pasar el rato...", "Esta guay, si", "¡Si, es estupenda!",
			"No se que decir..." };
	public static final String YaViste = " ya la has visto...\n";
	public static final String YaOdias = " la odias...\n";
	public static final String IntentandoConseguirActor = "¡Estamos intentando conseguir un actor!";
	public static final String IntentandoDatosIni = "¡Estamos consiguiendo tus datos iniciales!";
	public static final String IntentandoDatos = "¡Estabamos intentando obtener tus datos!";
	public static final String IntentandoPelicula = "¡Estabamos poniendonos de acuerdo en encontrar una película!";
	public static final String IntentandoSexo = "¡Estabamos averiguando tu sexo!";
	public static final String IntentandoValorarUlt = "Estabamos intentando conseguir la valoracion de la película que viste por ultima vez";
	public static final String IntentandoValorar = "Estabamos intentando que valorases la pelicula";
	public static final String IntentandoRecomendar = "Estabamos poniendonos de acuerdo en que te recomendase alguna peli";
	public static final String IntentandoActor = "Estabas intentando decirme un actor";
	public static final String IntentandoDirector = "Estabas intentando decirme un director";
	public static final String IntentandoPeli = "Estabas intentando decirme una peli";
	public static final String IntentandoUsuario = "Estabas intentando decirme un usuario";

	public static final String[] DisponemosGeneros = {
			"Disponemos de los siguientes géneros de películas: ", ", ", " y ", "." };

	// FRASES ANTERIORES
	public static final String ErrorEjecucionTarea = "Error al ejecutar la tarea : ";
	public static final String ErrorAccesoInterfaz = "Error-Acceso:Interfaz:";
	public static final String ErrorObtencionInterfaz = "Error-AlObtener:Interfaz:";
	public static final String ErrorObtencionRecurso = "Error-AlObtener:Interfaz_Recurso:";
	public static final String ErrorObtencionRecursos = "Error-AlObtener:Interfaces_Recursos";
	public static final String ErrorInterfaz_RecPersistencia = "Error-AlObtener:Interfaz_Recurso_Persistencia";
	public static final String ErrorInterfaz_Agente = "Error-AlObtener:Interfaz_Agente:";
	public static final String ErrorUtilizacionRecurso = "Error-AlUtilizar:Interfaces_Recurso:";
	
	public static final String ExtraccionSemanticaNull = "SinSemantica";
	public static final String InfoUsuarioYaExiste = "infoUsuarioYaExistente";
	public static final String InfoUsuarioAltaGuardada = "InfoAltaUsuarioGuardada";
	public static final String NotificacionAccesoAutorizado = "Autorizacion_Acceso_Notificado_Al_Usuario";
	public static final String ResultadoAutenticacion_DatosNoValidos = "usuarioNoValido";
	public static final String ResultadoAutenticacion_DatosValidos = "usuarioValido";
	public static final String ResultadoArranqueGestorOK = "arranqueConfirmado";
	public static final String ResultadoVerificacionEntidadesDescripcionOK = "existenEntidadesDescripcion";
}
