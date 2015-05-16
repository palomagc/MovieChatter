package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import java.util.List;

import icaro.aplicaciones.informacion.Busqueda;
import icaro.aplicaciones.informacion.Notificacion;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class EnviarAgenteTMDB extends TareaSincrona {

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

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String notifica = (String) params[0];
		String param = (String) params[1];
		Busqueda busqueda = Vocabulario.busqueda;
		busqueda.setPage(1);
		if (busqueda.getYear() == null && busqueda.getGenres().size() == 0
				&& busqueda.getPeople().size() == 0) {
			// Enviar a agenteTMDB
			Notificacion infoAenviar = new Notificacion();
			infoAenviar.setTipoNotificacion(notifica);
			infoAenviar.setMensajeNotificacion(param);
			getComunicator().enviarInfoAotroAgente(infoAenviar,
					Vocabulario.IdentAgenteAplicacionTMDB);
		} else {
			// Mirar los parametros que hay en Busqueda
			if (notifica.equals(Vocabulario.NombreTipoNotificacionAnos)) {
				busqueda.setYear(param);
			} else {
				try {
					ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
							.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionTMDB);
					if (itfUsoComunicacionTMDB != null) {
						if (notifica.equals(Vocabulario.NombreTipoNotificacionActor)) {
							Integer personId = itfUsoComunicacionTMDB.searchPerson(param);
							if (personId != null && !busqueda.getPeople().contains(personId))
								busqueda.addPerson(personId);
						} else {
							Vocabulario.Genero genero = null;
							if (Vocabulario.Generos.containsKey(notifica))
								genero = Vocabulario.Generos.get(notifica);
							Genre genre = null;
							if (genero != null) {
								List<Genre> genresAux = itfUsoComunicacionTMDB
										.getMovieGenresList("en");
								for (Genre g : genresAux)
									if (g.getName().equals(genero.getEnglish()))
										genre = g;
								if (genre != null && !busqueda.getGenres().contains(genre.getId()))
									busqueda.addGenre(genre.getId());
							}
						}
					}
				} catch (Exception e) {
					this.generarInformeConCausaTerminacion(identDeEstaTarea,
							contextoEjecucionTarea, identAgenteOrdenante, "Error-Acceso:Interfaz:"
									+ Vocabulario.IdentRecursoComunicacionTMDB,
							CausaTerminacionTarea.ERROR);
					e.printStackTrace();
				}
			}
			Notificacion infoAenviar = new Notificacion();
			infoAenviar
					.setTipoNotificacion(Vocabulario.NombreTipoNotificacionBusquedaVariosCampos);
			infoAenviar.setMensajeNotificacion(param);
			getComunicator().enviarInfoAotroAgente(infoAenviar,
					Vocabulario.IdentAgenteAplicacionTMDB);
		}
	}
}
