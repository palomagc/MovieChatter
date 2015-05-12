package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constantes.Busqueda;
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

public class RecomendarPeliculaVarios extends TareaSincrona {

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
		 * Recomienda una película de un género
		 */
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		//String notifica = (String) params[0];
		//String param = (String) params[1];
		String year = null;
		List<Integer> genres = new ArrayList<>();
		List<Integer> people = new ArrayList<>();
		Busqueda busquedaAux = VocabularioGestionCitas.busqueda;

		try {
			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);

			List<Movie> movies = new ArrayList<Movie>();
			List<String> moviesAux = new ArrayList<String>();
			if (itfUsoComunicacionTMDB != null) {
				if (busquedaAux.getYear() != null) {
					year = busquedaAux.getYear();
					//Busqueda.setYear(param);
					//movies = itfUsoComunicacionTMDB.getMoviesByYear(param,
					//MovieSort.PopularityDesc, "es", 1);
				}
				if (busquedaAux.getPeople() != null && busquedaAux.getPeople().size() > 0) {
					people = busquedaAux.getPeople();
					//movies = itfUsoComunicacionTMDB.getMoviesByPerson(personId,
					//MovieSort.PopularityDesc, "es", 1);
				}
				if (busquedaAux.getGenres() != null && busquedaAux.getGenres().size() > 0) {
					genres = busquedaAux.getGenres();
					//movies = itfUsoComunicacionTMDB.getMoviesByGenre(genre.getId(),
					//MovieSort.PopularityDesc, "es", 1);
				}
				/* Date after, Date before, String sort, Float minAverage,
					Integer minCount, List<Integer> genres, List<Integer> people, String year,
					String language, int page*/
				movies = itfUsoComunicacionTMDB.discoverMovies(null, null, MovieSort.PopularityDesc,
						null, null, genres, people, year, "es", 1);
				if (movies != null)
					for (Movie movie : movies)
						moviesAux.add(movie.getTitle());
				busquedaAux.setResult(moviesAux);
				Notificacion notif = new Notificacion();
		 		notif.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionComprobarDatosBusqueda);
		 		getComunicator().enviarInfoAotroAgente(notif,
						VocabularioGestionCitas.IdentAgenteAplicacionGuia);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}

}
