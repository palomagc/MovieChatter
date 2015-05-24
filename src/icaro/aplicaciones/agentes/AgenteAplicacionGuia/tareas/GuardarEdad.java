package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;


import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerDatosIniciales;
import icaro.aplicaciones.informacion.Notificacion;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import java.util.ArrayList;

public class GuardarEdad extends TareaSincrona {

	/**
	 * Constructor
	 *
	 * @param Description
	 *            of the Parameter
	 * @param Description
	 *            of the Parameter
	 */
	@Override
	public void ejecutar(Object... params) {
		try {

			// TODO Revisar esto pero parece que ya funciona automagicamente
			Notificacion notifEdad = (Notificacion) params[0]; 
			String edad = notifEdad.getMensajeNotificacion();
			Vocabulario.usuario.setEdad(edad);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;
			itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoUsuario);
			itfUsoRecursoUsuario.modificarUsuario(Vocabulario.usuario.getNombre(),
					Vocabulario.usuario);

			Objetivo objetivo = new ObtenerDatosIniciales();
			objetivo.setSolved();
			this.getEnvioHechos().insertarHecho(objetivo);

			
			
			// Podriamos deducir informaci�n seg�n la edad, basandonos en estereotipos.
			ArrayList<String> generosQueleGustan = new ArrayList<String>();
			int anos = 0;
			if(anos < 8){ 
//				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAnimacion);
				
			}else if(anos < 13){
//				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAnimacion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroFantasia);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroOeste);
				
			}else if(anos < 13){
//				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAnimacion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroFantasia);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroOeste);
				
			}else if(anos < 23){
//				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAnimacion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroCienciaFiccion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroComedia);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroCrimen);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroMisterio);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroSuspense);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroTerror);
				
			}else if(anos < 40){
//				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroCienciaFiccion);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroComedia);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroCrimen);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroMisterio);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroSuspense);
				
			}else{
//				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroDocumental);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroComedia);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroFamiliar);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroHistorico);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroOeste);
				generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroSuspense);
				
			}
			
			// TODO hay que guardar lo que le podr�a gustar por su edad.
			
			



		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					Vocabulario.ErrorEjecucionTarea + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
