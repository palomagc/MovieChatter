package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
import icaro.aplicaciones.informacion.Notificacion;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
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
		Usuario usuario = Vocabulario.usuario;
		try {
			String notifica = ((Notificacion) params[0]).getTipoNotificacion();
			Objetivo objAntiguo = (Objetivo) params[1];
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoUsuario);
			if (itfUsoRecursoUsuario != null && usuario.getPeliculaActual() != null) {
				String idPelicula = usuario.getPeliculaActual().getIdPelicula();
				if (usuario.getIdValoraciones().contains(idPelicula)
						&& !notifica.equals(Vocabulario.NombreTipoNotificacionNegacion)
						&& !notifica.equals(Vocabulario.NombreTipoNotificacionAfirmacion)) {
					// TODO cuando no es NOTA refocaliza
					// int index = usuario.getIdValoraciones().indexOf(idPelicula);
					// Valoracion aux = usuario.getValoraciones().get(index);
					String nota = "";
					if (notifica.equals(Vocabulario.NombreTipoNotificacionValoracionPositiva))
						nota = "5";
					else if (notifica.equals(Vocabulario.NombreTipoNotificacionValoracionNegativa))
						nota = "0";
					else
						nota = ((Notificacion) params[0]).getMensajeNotificacion();
					Valoracion aux = new Valoracion(idPelicula, nota);
					// usuario.getValoraciones().get(index).setNota(nota);
					itfUsoRecursoUsuario.nuevaValoracion(usuario.getNombre(), aux);

					// Enviamos un mensaje amigable.
					// Se busca la interfaz del recurso en el repositorio de interfaces
					ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
							.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);
					if (recComunicacionChat != null) {
						recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);

						String mensajeAenviar = Vocabulario.Valoraciones[6];
						if (Integer.parseInt(nota) == 0) {
							mensajeAenviar = Vocabulario.Valoraciones[0];
						} else if (Integer.parseInt(nota) == 1) {
							mensajeAenviar = Vocabulario.Valoraciones[1];
						} else if (Integer.parseInt(nota) == 2) {
							mensajeAenviar = Vocabulario.Valoraciones[2];
						} else if (Integer.parseInt(nota) == 3) {
							mensajeAenviar = Vocabulario.Valoraciones[3];
						} else if (Integer.parseInt(nota) == 4) {
							mensajeAenviar = Vocabulario.Valoraciones[4];
						} else if (Integer.parseInt(nota) == 5) {
							mensajeAenviar = Vocabulario.Valoraciones[5];
						}
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					}
				}
			}
			// Reestablece el objetivo para que le recomiende otra peli con las mismas
			// caracteristicas
			if (objAntiguo == null)
				this.getEnvioHechos().insertarHecho(new RecomendarPelicula());
			else {
				objAntiguo.setPending(); // RecomendarPelicula
				this.getEnvioHechos().actualizarHecho(objAntiguo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					Vocabulario.ErrorEjecucionTarea + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
