package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;


import java.util.ArrayList;
import java.util.List;

import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ListarGeneros extends TareaSincrona{

	/**
	 *  Constructor
	 *
	 *@param    Description of the Parameter
	 *@param    Description of the Parameter
	 */
	
	private Objetivo contextoEjecucionTarea = null;
	@Override
	public void ejecutar(Object... params) {
		/**
		 * Recomienda una película de un género
		 */
		String identDeEstaTarea=this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		try {
			// Se busca la interfaz del recurso en el repositorio de interfaces 
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(
					VocabularioGestionCitas.IdentRecursoComunicacionChat);
			
			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(
					VocabularioGestionCitas.IdentRecursoComunicacionTMDB);
			/*ItfUsoComunicacionTMDB itfUsoComTMDB=(ItfUsoComunicacionTMDB) this.repoIntfaces.obtenerInterfazUso(identComunicacionTMDB);
			if (itfUsoComTMDB == null){
				this.generarErrorCreacionComponente("itfComunicacionTMDB es null");
			}else comunicChat.setItfusoRecComunicacionTMDB(itfUsoComTMDB);*/
			List<Genre> genres = new ArrayList<Genre>();
			if (itfUsoComunicacionTMDB != null){
				genres = itfUsoComunicacionTMDB.getMovieGenresList("en");
			}
			if (recComunicacionChat!=null && genres!=null){
				recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				//int numRecomienda = (int) ((100 * Math.random()) % VocabularioGestionCitas.Recomienda.length);
				String mensajeAenviar = "Disponemos de los siguientes géneros de películas ";
				for (Genre g : genres) {
					mensajeAenviar += g.getName() + ", ";
				}
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
			}
			else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz:"+
						VocabularioGestionCitas.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
			}
		} catch(Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-Acceso:Interfaz:"+
					VocabularioGestionCitas.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}


}
