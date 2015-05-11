/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.informacion.gestionCitas;

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
public class VocabularioGestionCitas {

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

	public static Usuario usuario = null;

	// TODO A�adir aqu� las anotaciones que quieres que se tengan en cuenta.
	public static final List<String> NombresTipoNotificacion = Arrays.asList(
			"Saludo", "Despedida", "Afirmacion", "Negacion", "SexoHombre",
			"SexoMujer", "Numero", "GeneroAccion", "GeneroAventura",
			"GeneroAnimacion", "GeneroComedia", "GeneroCrimen",
			"GeneroDocumental", "GeneroDrama", "GeneroFamiliar",
			"GeneroFantasia", "GeneroExtranjero", "GeneroHistorico",
			"GeneroTerror", "GeneroMusical", "GeneroMisterio",
			"GeneroRomantico", "GeneroCienciaFiccion", "GeneroTV",
			"GeneroSuspense", "GeneroGuerra", "GeneroOeste");

	// SALUDO
	public static final String NombreTipoNotificacionSaludo = "Saludo";
	public static final String SaludoInicial1 = "Hola, yo soy "
			+ Constantes.SYSTEM_NAME + ", tu recomendador de peliculas";
	public static final String[] SaludoInicial2 = { "Hola", "Que tal",
			"Buenas", "Que hay" };

	// DESPEDIDA
	public static final String NombreTipoNotificacionDespedida = "Despedida";
	public static final String[] Despedida = { "Adios", "Hasta la proxima",
			"Nos vemos", "Que te vaya bien" };

	// AFIRMACION
	public static final String NombreTipoNotificacionAfirmacion = "Afirmacion";

	// NEGACION
	public static final String NombreTipoNotificacionNegacion = "Negacion";

	// SEXOHOMBRE
	public static final String NombreTipoNotificacionSexoHombre = "SexoHombre";

	// SEXOMUJER
	public static final String NombreTipoNotificacionSexoMujer = "SexoMujer";

	// NUMERO
	public static final String NombreTipoNotificacionNumero = "Numero";

	// NUMERO
	public static final String NombreTipoNotificacionValorarUltimaPelicula = "ValorarUltimaPelicula";

	// RECOMIENDA
	public static final String NombreTipoNotificacionRecomienda = "Recomienda";
	public static final String[] Recomienda = { "podrias ver" };

	// PRESENTACION
	public static final String NombreTipoNotificacionQuien = "Quien";
	public static final String InfoGeneralFuncionalidad = "Soy un cinefilo, y me encantaria ayudarte a encontrar peliculas";

	// PEDIR INFO AL USUARIO
	public static final String[] PeticionInformacionGeneral1 = {
			"Te puedo sugerir alguna hoy?",
			"Quieres que te recomiende alguna?",
			"Necesitas ayuda para encontrar alguna?" };

	// NO ENTENDER
	public static final String[] preambuloNoEntendido = {
			"No he entendido lo que me has dicho.",
			"Perdona, pero no he entendido bien.",
			"Lo siento, no he comprendido bien.",
			"Perdona, no comprendo que quieres decir." };

	// GENEROS
	public static final String NombreTipoNotificacionGenero = "Genero";
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

	public static final String[] NombresTipoNotificacionGenero = {
			"GeneroAccion", "GeneroAventura", "GeneroAnimacion",
			"GeneroComedia", "GeneroCrimen", "GeneroDocumental", "GeneroDrama",
			"GeneroFamiliar", "GeneroFantasia", "GeneroExtranjero",
			"GeneroHistorico", "GeneroTerror", "GeneroMusical",
			"GeneroMisterio", "GeneroRomantico", "GeneroCienciaFiccion",
			"GeneroTV", "GeneroSuspense", "GeneroGuerra", "GeneroOeste" };
	public static final Map<String, Genero> Generos = new HashMap<String, Genero>();
	static {
		Generos.put("GeneroAccion", new Genero("Action", "acci�n"));
		Generos.put("GeneroAventura", new Genero("Adventure", "aventura"));
		Generos.put("GeneroAnimacion", new Genero("Animation", "animaci�n"));
		Generos.put("GeneroComedia", new Genero("Comedy", "comedia"));
		Generos.put("GeneroCrimen", new Genero("Crime", "crimen"));
		Generos.put("GeneroDocumental", new Genero("Documentary", "documental"));
		Generos.put("GeneroDrama", new Genero("Drama", "drama"));
		Generos.put("GeneroFamiliar", new Genero("Family", "familiar"));
		Generos.put("GeneroFantasia", new Genero("Fantasy", "fantas�a"));
		Generos.put("GeneroExtranjero", new Genero("Foreign", "extranjero"));
		Generos.put("GeneroHistorico", new Genero("History", "hist�rico"));
		Generos.put("GeneroTerror", new Genero("Horror", "terror"));
		Generos.put("GeneroMusical", new Genero("Music", "musical"));
		Generos.put("GeneroMisterio", new Genero("Mystery", "misterio"));
		Generos.put("GeneroRomantico", new Genero("Romance", "rom�ntico"));
		Generos.put("GeneroCienciaFiccion", new Genero("Science Fiction",
				"ciencia ficci�n"));
		Generos.put("GeneroTV", new Genero("TV Movie", "tv"));
		Generos.put("GeneroSuspense", new Genero("Thriller", "suspense"));
		Generos.put("GeneroGuerra", new Genero("War", "guerra"));
		Generos.put("GeneroOeste", new Genero("Western", "oeste"));
	}

	// NOMBRE CHAT
	public static final String IdentConexionAgte = "AgteMovies";

	// NOMBRE AGENTES
	public static final String IdentAgenteAplicacionDialogoCitas = "AgenteAplicacionDialogoCitas1";
	public static final String IdentAgenteAplicacionGuia = "AgenteAplicacionGuia1";
	public static final String IdentAgenteAplicacionUsuario = "AgenteAplicacionUsuario1";
	public static final String IdentAgenteAplicacionTMDB = "AgenteAplicacionTMDB1";

	// NOMBRE RECURSOS
	public static final String IdentRecursoExtractorSemantico = "ExtractorSemantico1";
	public static final String IdentRecursoComunicacionChat = "ComunicacionChat1";
	public static final String IdentRecursoComunicacionTMDB = "ComunicacionTMDB1";
	public static final String IdentRecursoPersistenciaChat = "PersistenciaChat1";
	public static final String IdentRecursoUsuario = "RecursoUsuario1";

	public static final String NombreTipoNotificacionCitasConGuia = "CitasConGuia";

	public static final String ResultadoAutenticacion_DatosNoValidos = "usuarioNoValido";
	public static final String ResultadoAutenticacion_DatosValidos = "usuarioValido";
	public static final String NotificacionAccesoAutorizado = "Autorizacion_Acceso_Notificado_Al_Usuario";
	public static final String ErrorObtencionInterfaz_RecPersistencia = "Error-AlObtener:Interfaz_Recurso_Persistencia";
	public static final String InfoUsuarioYaExiste = "infoUsuarioYaExistente";
	public static final String InfoUsuarioAltaGuardada = "InfoAltaUsuarioGuardada";
	public static final String RespuestaNoEniendo1 = "Lo siento pero no entiendo nada de lo que dice";
	public static final String RespuestaNoEniendo2 = "Lo siento pero sigo sin entender nada";
	public static final String RespuestaNoEniendo3 = "definitivamente no me entero de lo que dice , creo que vamos a terminar la conversaciÃ³n";
	public static final String Despedida1 = "Bueno tenemos que dejarlo ha sido un placer";
	public static final String ExtraccionSemanticaNull = "SinSemantica";
	public static final String peticionInfoIicialCita1 = " PodrÃ­a recomendarte alguna pelÃ­cula?";

}
