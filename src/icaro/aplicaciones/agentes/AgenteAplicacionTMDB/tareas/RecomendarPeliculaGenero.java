package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import java.util.ArrayList;
import java.util.List;

import icaro.aplicaciones.informacion.Busqueda;
import icaro.aplicaciones.informacion.Notificacion;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
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
		Busqueda busqueda = Vocabulario.busqueda;
		Vocabulario.Genero genero = null;
		if (Vocabulario.Generos.containsKey(notifica))
			genero = Vocabulario.Generos.get(notifica);

		try {
			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionTMDB);

			List<Movie> movies = new ArrayList<Movie>();
			Genre genre = null;
			if (itfUsoComunicacionTMDB != null && genero != null) {
				List<Genre> genres = itfUsoComunicacionTMDB.getMovieGenresList("en");
				for (Genre g : genres)
					if (g.getName().equals(genero.getEnglish()))
						genre = g;
				if (genre != null) {
					movies = itfUsoComunicacionTMDB.getMoviesByGenre(genre.getId(),
							busqueda.getSort(), busqueda.getLanguage(), busqueda.getPage());
				}
			}
			if (movies != null) {
				Vocabulario.busqueda.addGenre(genre.getId());
				Vocabulario.busqueda.setResult(movies);
				// this.getEnvioHechos().insertarHecho(new ObtenerPelicula());
				// TODO poner notificacion porque es para otro agente
				Notificacion infoAenviar = new Notificacion();
				infoAenviar
						.setTipoNotificacion(Vocabulario.NombreTipoNotificacionComprobarDatosBusqueda);
				getComunicator().enviarInfoAotroAgente(infoAenviar,
						Vocabulario.IdentAgenteAplicacionGuia);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ Vocabulario.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
