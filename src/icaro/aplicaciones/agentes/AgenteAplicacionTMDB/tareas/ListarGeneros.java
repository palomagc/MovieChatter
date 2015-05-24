package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import java.util.ArrayList;
import java.util.List;

import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ListarGeneros extends TareaSincrona {

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
		try {
			// Se busca la interfaz del recurso en el repositorio de interfaces
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);

			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionTMDB);
			/*
			 * ItfUsoComunicacionTMDB itfUsoComTMDB=(ItfUsoComunicacionTMDB)
			 * this.repoIntfaces.obtenerInterfazUso(identComunicacionTMDB); if (itfUsoComTMDB ==
			 * null){ this.generarErrorCreacionComponente("itfComunicacionTMDB es null"); }else
			 * comunicChat.setItfusoRecComunicacionTMDB(itfUsoComTMDB);
			 */
			List<Genre> genres = new ArrayList<Genre>();
			if (itfUsoComunicacionTMDB != null) {
				genres = itfUsoComunicacionTMDB.getMovieGenresList("en");
			}
			if (recComunicacionChat != null && genres != null) {
				recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);
				// int numRecomienda = (int) ((100 * Math.random()) %
				// Vocabulario.Recomienda.length);
				String mensajeAenviar = Vocabulario.DisponemosGeneros[0];
				for (Genre g : genres) {
					if (genres.indexOf(g) == genres.size()-1)
						mensajeAenviar += g.getName() + Vocabulario.DisponemosGeneros[3];
					else if (genres.indexOf(g) == genres.size()-2)
						mensajeAenviar += g.getName() + Vocabulario.DisponemosGeneros[2];
					else
						mensajeAenviar += g.getName() + Vocabulario.DisponemosGeneros[1];
				}
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, Vocabulario.ErrorObtencionInterfaz
								+ Vocabulario.IdentRecursoComunicacionChat,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, Vocabulario.ErrorAccesoInterfaz
							+ Vocabulario.IdentRecursoComunicacionChat,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
