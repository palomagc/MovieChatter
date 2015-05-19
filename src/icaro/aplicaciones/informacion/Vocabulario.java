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

	// TODO Aï¿½adir aquï¿½ las anotaciones que quieres que se tengan en cuenta.
	// NOMBRES NOTIFICACIONES
	public static final List<String> NombresTipoNotificacion = Arrays.asList("Saludo", "Despedida",
			"Afirmacion", "Negacion", "SexoHombre", "SexoMujer", "Generos", "GeneroAccion",
			"GeneroAventura", "GeneroAnimacion", "GeneroComedia", "GeneroCrimen",
			"GeneroDocumental", "GeneroDrama", "GeneroFamiliar", "GeneroFantasia",
			"GeneroExtranjero", "GeneroHistorico", "GeneroTerror", "GeneroMusical",
			"GeneroMisterio", "GeneroRomantico", "GeneroCienciaFiccion", "GeneroTV",
			"GeneroSuspense", "GeneroGuerra", "GeneroOeste", "Actor", "Numero", "Anos", "YaVista",
			"Orden", "Dinamico", "Nota", "Tonterias", "Argumento", "ValoracionPositiva", "ValoracionNegativa");

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
		Generos.put("GeneroAccion", new Genero("Action", "acciï¿½n"));
		Generos.put("GeneroAventura", new Genero("Adventure", "aventura"));
		Generos.put("GeneroAnimacion", new Genero("Animation", "animaciï¿½n"));
		Generos.put("GeneroComedia", new Genero("Comedy", "comedia"));
		Generos.put("GeneroCrimen", new Genero("Crime", "crimen"));
		Generos.put("GeneroDocumental", new Genero("Documentary", "documental"));
		Generos.put("GeneroDrama", new Genero("Drama", "drama"));
		Generos.put("GeneroFamiliar", new Genero("Family", "familiar"));
		Generos.put("GeneroFantasia", new Genero("Fantasy", "fantasï¿½a"));
		Generos.put("GeneroExtranjero", new Genero("Foreign", "extranjero"));
		Generos.put("GeneroHistorico", new Genero("History", "histï¿½rico"));
		Generos.put("GeneroTerror", new Genero("Horror", "terror"));
		Generos.put("GeneroMusical", new Genero("Music", "musical"));
		Generos.put("GeneroMisterio", new Genero("Mystery", "misterio"));
		Generos.put("GeneroRomantico", new Genero("Romance", "romï¿½ntico"));
		Generos.put("GeneroCienciaFiccion", new Genero("Science Fiction", "ciencia ficciï¿½n"));
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

	// SALUDO
	public static final String SaludoInicial1 = "Hola, yo soy " + Constantes.SYSTEM_NAME
			+ ", tu recomendador de pelï¿½culas";
	public static final String[] SaludoInicial2 = { "Hola", "Quï¿½ tal", "Buenas", "Que hay" };

	// FRASES DE NUESTRO SISTEMA
	public static final String[] Despedida = { "Adiï¿½s.", "Hasta la prï¿½xima.", "Nos vemos.",
			"Que te vaya bien.", "Ha sido un placer.", "Cuando quieras repetimos." };
	public static final String[] Disfruta = { "Que la disfrutes.", "Ya me contaras quï¿½ tal.",
			"Que te guste.", "Ojala te guste.", "A por las palomitas." };
	public static final String InfoFuncionalidad = "Soy un cinï¿½filo, y me encantarï¿½a ayudarte a encontrar pelï¿½culas.";
	public static final String[] Params = { "Podrï¿½as introducir un aï¿½o.",
			"Puedes aï¿½adir un actor a tu filtrado.", "Podrï¿½as aï¿½adir otro gï¿½nero a la bï¿½squeda.",
			"Puedes introducir otro gï¿½nero, actor o aï¿½o como filtro" };
	public static final String[] PeticionInfoGeneral = { "ï¿½Te puedo sugerir alguna hoy?",
			"Quieres que te recomiende alguna?", "ï¿½Necesitas ayuda para encontrar alguna?" };	
	public static final String[] Recomienda = { "podrias ver", "te sugiero", "te propongo" };
	public static final String EligeGenero = "¿De que genero te apetece ver la pelicula?";
	// TODO mejorar la frase
	public static final String LimpiarBusqueda = "La consulta con los parï¿½metros dados no ha obtenido ningï¿½n resultado."
			+ " Se limpian los parï¿½metros de bï¿½squeda.";

	// FRASES ANTERIORES
	public static final String ErrorObtencionInterfaz_RecPersistencia = "Error-AlObtener:Interfaz_Recurso_Persistencia";
	public static final String ExtraccionSemanticaNull = "SinSemantica";
	public static final String InfoUsuarioYaExiste = "infoUsuarioYaExistente";
	public static final String InfoUsuarioAltaGuardada = "InfoAltaUsuarioGuardada";
	public static final String NotificacionAccesoAutorizado = "Autorizacion_Acceso_Notificado_Al_Usuario";
	public static final String ResultadoAutenticacion_DatosNoValidos = "usuarioNoValido";
	public static final String ResultadoAutenticacion_DatosValidos = "usuarioValido";

}
