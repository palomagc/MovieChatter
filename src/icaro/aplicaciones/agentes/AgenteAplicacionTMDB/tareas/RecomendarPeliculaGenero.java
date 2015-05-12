package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import java.util.ArrayList;
import java.util.List;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.orders.MovieSort;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class RecomendarPeliculaGenero extends TareaSincrona {

	/**
	 * Constructor
	 *
	 * @param Description
	 *            of the Parameter
	 * @param Description
	 *            of the Parameter
	 */
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		/**
		 * Recomienda una pel�cula de un g�nero
		 */
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String notifica = (String) params[0];
		VocabularioGestionCitas.Genero genero = null;
		if (VocabularioGestionCitas.Generos.containsKey(notifica))
			genero = VocabularioGestionCitas.Generos.get(notifica);

		try {
			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);

			List<Movie> movies = new ArrayList<Movie>();
			List<String> moviesAux = new ArrayList<String>();
			Genre genre = null;
			if (itfUsoComunicacionTMDB != null && genero != null) {
				List<Genre> genres = itfUsoComunicacionTMDB.getMovieGenresList("en");
				for (Genre g : genres)
					if (g.getName().equals(genero.getEnglish()))
						genre = g;
				if (genre != null) {
					movies = itfUsoComunicacionTMDB.getMoviesByGenre(genre.getId(), MovieSort.PopularityDesc,
							"es", 1);
				}
			}
			if (movies != null) {
				for (Movie movie : movies) {
					moviesAux.add(movie.getTitle());
				}
				VocabularioGestionCitas.busqueda.addGenre(genre.getId());
				VocabularioGestionCitas.busqueda.setResult(moviesAux);
				Notificacion notif = new Notificacion();
				notif.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionComprobarDatosBusqueda);
		 		getComunicator().enviarInfoAotroAgente(notif,
						VocabularioGestionCitas.IdentAgenteAplicacionGuia);
			}
			// Se busca la interfaz del recurso en el repositorio de interfaces
			/*ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			if (recComunicacionChat != null && movies != null) {
				recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				int numRecomienda = (int) ((100 * Math.random()) % VocabularioGestionCitas.Recomienda.length);
				String mensajeAenviar = "Del genero " + genero.getSpanish() + " "
						+ VocabularioGestionCitas.Recomienda[numRecomienda] + "  ";
				for (Movie m : movies) {
					mensajeAenviar += m.getTitle() + ",     ";
				}
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-AlObtener:Interfaz:"
								+ VocabularioGestionCitas.IdentRecursoComunicacionTMDB,
						CausaTerminacionTarea.ERROR);
			}*/
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}

}
