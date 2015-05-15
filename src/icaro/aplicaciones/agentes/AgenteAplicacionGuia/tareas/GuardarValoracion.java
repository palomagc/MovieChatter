package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
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
	// private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		Usuario usuario = VocabularioGestionCitas.usuario;
		try {
			String notifica = ((Notificacion) params[0]).getTipoNotificacion();
			Objetivo objAntiguo = (Objetivo) params[1];
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
			if (itfUsoRecursoUsuario != null && usuario.getPeliculaActual() != null) {
				String idPelicula = usuario.getPeliculaActual().getIdPelicula();
				if (usuario.getIdValoraciones().contains(idPelicula)
						&& !notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionNegacion)
						&& !notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionAfirmacion)) {
					// TODO cuando no es NOTA refocaliza
					int index = usuario.getIdValoraciones().indexOf(idPelicula);
					Valoracion aux = usuario.getValoraciones().get(index);
					String nota = ((Notificacion) params[0]).getMensajeNotificacion();
					aux = new Valoracion(idPelicula, nota);
					// TODO deberia reemplazar la valoracion, por ahora solo la añade
					// en realidad la añadio con nota null al objeto usuario y ahora se guarda
					itfUsoRecursoUsuario.nuevaValoracion(usuario.getNombre(), aux);
					
				}
			}
			// Reestablece el objetivo para que le recomiende otra peli con las mismas
			// caracteristicas
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
