package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class GuardarValoracion extends TareaSincrona {

	/**
	 * Constructor
	 *
	 * @param Description
	 *            of the Parameter
	 * @param Description
	 *            of the Parameter
	 */
	private Objetivo contextoEjecucionTarea = null;

	// TODO hacerlo entero
	
	@Override
	public void ejecutar(Object... params) {
		try {
			Notificacion notif = (Notificacion) params[0];
			Objetivo objAntiguo = (Objetivo) params[1];
			if(notif.getTipoNotificacion().equals(VocabularioGestionCitas.NombreTipoNotificacionNegacion)){
				// TODO no quiere ponerle nota a la peli
			}else{
				String nota = ((Notificacion)params[0]).getTipoNotificacion();
				String idPelicula = VocabularioGestionCitas.usuario.getPeliculaActual().getIdPelicula();
				Valoracion aux = new Valoracion(idPelicula, nota);
				VocabularioGestionCitas.usuario.getValoraciones().add(aux);
				
				ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;
				itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
						.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
				itfUsoRecursoUsuario.modificarUsuario(VocabularioGestionCitas.usuario.getNombre(), VocabularioGestionCitas.usuario);
			}
			// Reestablece el objetivo para que le recomiende otra peli con las mismas caracter�sticas
			objAntiguo.setPending();
			this.getEnvioHechos().actualizarHecho(objAntiguo);

		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

}
