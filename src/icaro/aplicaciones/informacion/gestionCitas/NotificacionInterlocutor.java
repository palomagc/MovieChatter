package icaro.aplicaciones.informacion.gestionCitas;

public class NotificacionInterlocutor {

	public String identNotificador;
	public String mensajeNotificacion;
	public Object contexto;
	public String identObjectRefNotificacion;
	public String tipoNotificacion;

	public NotificacionInterlocutor() {
	}

	public NotificacionInterlocutor(Notificacion notif) {
		identNotificador = notif.getidentNotificador();
		mensajeNotificacion = notif.getMensajeNotificacion();
		contexto = notif.getcontexto();
		identObjectRefNotificacion = notif.getidentObjectRefNotificacion();
		tipoNotificacion = notif.getTipoNotificacion();
	}
	
}
