package icaro.aplicaciones.agentes.AgenteAplicacionDialogoCitasCognitivo.tareas;


import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaComunicacion;

public class EnviarNombreUsuario extends TareaComunicacion {

  /**
   *  Constructor
   *
   *@param    Description of the Parameter
   *@param    Description of the Parameter
   */
    private Objetivo contextoEjecucionTarea = null;
    @Override
 	public void ejecutar(Object... params) {
 
 		String identDeEstaTarea = this.getIdentTarea();
 		String identAgenteOrdenante = this.getIdentAgente();
 		String identInterlocutor = (String) params[0];
 		Notificacion notif = (Notificacion) params[1];
 		try {
 			notif.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionCitasConGuia);
			this.getEnvioHechos().insertarHecho(notif);
			this.informaraOtroAgente(notif, VocabularioGestionCitas.IdentAgenteAplicacionGuia);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
 }
 
