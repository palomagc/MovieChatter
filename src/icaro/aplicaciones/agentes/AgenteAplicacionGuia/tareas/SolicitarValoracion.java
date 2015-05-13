package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;


import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class SolicitarValoracion extends TareaSincrona{

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
		 * Produce una despedida
		 */
		String identDeEstaTarea=this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		try {
			Usuario usuario = VocabularioGestionCitas.usuario;
			// Se busca la interfaz del recurso en el repositorio de interfaces 
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(
					VocabularioGestionCitas.IdentRecursoComunicacionChat);
			if (recComunicacionChat!=null){
				recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				String mensajeAenviar;
				ItfUsoRecursoUsuario itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
						.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
				
				if (usuario.getPeliculaActual() != null && itfUsoRecursoUsuario!=null) {
					String idPelicula = usuario.getPeliculaActual().getIdPelicula();
					Valoracion aux;
					aux = new Valoracion(idPelicula, null);
					//itfUsoRecursoUsuario.nuevaValoracion(usuario.getNombre(), aux);
					usuario.addValoracion(aux);
					
					Valoracion valoracion = VocabularioGestionCitas.usuario.getPeliculaActual();
					String idPeliculaActual = valoracion.getIdPelicula();
					Movie movie = null;
					ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);
					if(itfUsoComunicacionTMDB != null){
						movie = itfUsoComunicacionTMDB.getMovie(Integer.parseInt(idPeliculaActual), null);
					}
					mensajeAenviar = "Que nota quieres ponerle a " + movie.getTitle() + "?";
				} else
					mensajeAenviar = "Ha ocurrido un error";
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
