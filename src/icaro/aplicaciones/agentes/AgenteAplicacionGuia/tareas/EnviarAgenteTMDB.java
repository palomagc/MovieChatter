package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import java.util.List;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import constantes.Busqueda;

public class EnviarAgenteTMDB extends TareaSincrona {
	// TODO regla que acepte notificaciones de TMDB y envie al agente guia

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
		Busqueda busquedaAux = VocabularioGestionCitas.busqueda;
		if (busquedaAux.getYear() == null && busquedaAux.getGenres().size() == 0
				&& busquedaAux.getPeople().size() == 0) {
			// Enviar a agenteTMDB
			Notificacion infoAenviar = new Notificacion();
			infoAenviar.setTipoNotificacion(notifica);
			infoAenviar.setMensajeNotificacion(param);
			getComunicator().enviarInfoAotroAgente(infoAenviar,
					VocabularioGestionCitas.IdentAgenteAplicacionTMDB);
		} else {
			// Mirar los parametro que hay en Busqueda
			if (notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionAnos)) {
				busquedaAux.setYear(param);
			} else {
				try {
					ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
							.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);
					if (itfUsoComunicacionTMDB != null) {
						if (notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionActor)) {
							Integer personId = itfUsoComunicacionTMDB.searchPerson(param);
							if (personId != null)
								busquedaAux.addPerson(personId);
						} else {
							VocabularioGestionCitas.Genero genero = null;
							if (VocabularioGestionCitas.Generos.containsKey(notifica))
								genero = VocabularioGestionCitas.Generos.get(notifica);
							Genre genre = null;
							if (genero != null) {
								List<Genre> genresAux = itfUsoComunicacionTMDB
										.getMovieGenresList("en");
								for (Genre g : genresAux)
									if (g.getName().equals(genero.getEnglish()))
										genre = g;
								if (genre != null)
									busquedaAux.addGenre(genre.getId());
							}
						}
					}
				} catch (Exception e) {
					this.generarInformeConCausaTerminacion(identDeEstaTarea,
							contextoEjecucionTarea, identAgenteOrdenante, "Error-Acceso:Interfaz:"
									+ VocabularioGestionCitas.IdentRecursoComunicacionTMDB,
							CausaTerminacionTarea.ERROR);
					e.printStackTrace();
				}
			}
			Notificacion infoAenviar = new Notificacion();
			infoAenviar
					.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionBusquedaVariosCampos);
			infoAenviar.setMensajeNotificacion(param);
			getComunicator().enviarInfoAotroAgente(infoAenviar,
					VocabularioGestionCitas.IdentAgenteAplicacionTMDB);
		}
	}
}
