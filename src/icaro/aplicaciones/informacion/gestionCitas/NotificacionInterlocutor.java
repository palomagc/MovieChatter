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
	
	public String getIdentNotificador() {
		return identNotificador;
	}

	public void setIdentNotificador(String identNotificador) {
		this.identNotificador = identNotificador;
	}

	public String getMensajeNotificacion() {
		return mensajeNotificacion;
	}

	public void setMensajeNotificacion(String mensajeNotificacion) {
		this.mensajeNotificacion = mensajeNotificacion;
	}

	public Object getContexto() {
		return contexto;
	}

	public void setContexto(Object contexto) {
		this.contexto = contexto;
	}

	public String getIdentObjectRefNotificacion() {
		return identObjectRefNotificacion;
	}

	public void setIdentObjectRefNotificacion(String identObjectRefNotificacion) {
		this.identObjectRefNotificacion = identObjectRefNotificacion;
	}

	public String getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	
}
