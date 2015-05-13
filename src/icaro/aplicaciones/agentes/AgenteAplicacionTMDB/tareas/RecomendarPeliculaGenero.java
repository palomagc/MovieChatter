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
				VocabularioGestionCitas.busqueda.addGenre(genre.getId());
				VocabularioGestionCitas.busqueda.setResult(movies);
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
