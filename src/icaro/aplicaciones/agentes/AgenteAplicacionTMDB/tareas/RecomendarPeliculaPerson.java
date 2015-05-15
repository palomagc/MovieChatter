package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import java.util.ArrayList;
import java.util.List;

import constantes.Busqueda;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class RecomendarPeliculaPerson extends TareaSincrona {

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
		// String notifica = (String) params[0];
		String person = (String) params[1];
		Busqueda busqueda = VocabularioGestionCitas.busqueda;

		try {
			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);

			List<Movie> movies = new ArrayList<Movie>();
			Integer personId = null;
			if (itfUsoComunicacionTMDB != null)
				personId = itfUsoComunicacionTMDB.searchPerson(person);
			if (personId != null)
				movies = itfUsoComunicacionTMDB.getMoviesByPerson(personId, busqueda.getSort(),
						busqueda.getLanguage(), busqueda.getPage());
			if (movies != null) {
				VocabularioGestionCitas.busqueda.addPerson(personId);
				VocabularioGestionCitas.busqueda.setResult(movies);
				// this.getEnvioHechos().insertarHecho(new ObtenerPelicula());
				// TODO poner notificacion porque es para otro agente
				Notificacion infoAenviar = new Notificacion();
				infoAenviar
						.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionComprobarDatosBusqueda);
				getComunicator().enviarInfoAotroAgente(infoAenviar,
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
