package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

<<<<<<< HEAD
import java.util.ArrayList;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.PreguntarDatosInicialesUsuario;
=======
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerDatosIniciales;
>>>>>>> origin/master
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

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

			// TODO revisar esto pero parece que ya funciona autom√°gicamente

			String edad = (String) params[0];
			VocabularioGestionCitas.usuario.setEdad(edad);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;
			itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
			itfUsoRecursoUsuario.modificarUsuario(VocabularioGestionCitas.usuario.getNombre(),
					VocabularioGestionCitas.usuario);

			Objetivo objetivo = new ObtenerDatosIniciales();
			objetivo.setSolved();
			this.getEnvioHechos().insertarHecho(objetivo);
<<<<<<< HEAD
			
			
			// Podriamos deducir informaciÛn seg˙n la edad, basandonos en estereotipos.
			ArrayList<String> generosQueleGustan = new ArrayList<String>();
			int anos = 0;
			if(anos < 8){ 
//				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAnimacion);
				
			}else if(anos < 13){
//				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAnimacion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroFantasia);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroOeste);
				
			}else if(anos < 13){
//				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAnimacion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroFantasia);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroOeste);
				
			}else if(anos < 23){
//				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAnimacion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroCienciaFiccion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroComedia);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroCrimen);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroMisterio);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroSuspense);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroTerror);
				
			}else if(anos < 40){
//				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAccion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAventura);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroCienciaFiccion);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroComedia);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroCrimen);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroMisterio);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroSuspense);
				
			}else{
//				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroDocumental);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroComedia);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroFamiliar);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroHistorico);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroOeste);
				generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroSuspense);
				
			}
			
			// TODO hay que guardar lo que le podrÌa gustar por su edad.
			
			
=======

>>>>>>> origin/master
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
