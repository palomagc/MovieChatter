package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import java.util.ArrayList;

public class GuardarSexo extends TareaSincrona {

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
			String sexo = (String) params[0];
			VocabularioGestionCitas.usuario.setSexo(sexo);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;
<<<<<<< HEAD
				itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
						.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
				itfUsoRecursoUsuario.modificarUsuario(VocabularioGestionCitas.usuario.getNombre(), VocabularioGestionCitas.usuario);
				
				
				// Podriamos deducir información según el sexo, basandonos en estereotipos.
				ArrayList<String> generosQueleGustan = new ArrayList<String>();
				if(sexo.equalsIgnoreCase("SexoHombre")){ // SexoHombre
//					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroAccion);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroCienciaFiccion);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroGuerra);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroMisterio);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroSuspense);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroTerror);
					
				}else{ // SexoMujer
//					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGenero);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroDrama);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroFamiliar);
					generosQueleGustan.add(VocabularioGestionCitas.NombreTipoNotificacionGeneroRomantico);
					
				}
				
				// TODO hay que guardar lo que le podría gustar por su sexo.
				
=======
			itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
			itfUsoRecursoUsuario.modificarUsuario(VocabularioGestionCitas.usuario.getNombre(),
					VocabularioGestionCitas.usuario);
>>>>>>> origin/master

		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
