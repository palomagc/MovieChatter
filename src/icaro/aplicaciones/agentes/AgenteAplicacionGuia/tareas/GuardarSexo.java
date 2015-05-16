package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.Vocabulario;
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
			Vocabulario.usuario.setSexo(sexo);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;

				itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
						.obtenerInterfazUso(Vocabulario.IdentRecursoUsuario);
				itfUsoRecursoUsuario.modificarUsuario(Vocabulario.usuario.getNombre(), Vocabulario.usuario);
				
				
				// Podriamos deducir información según el sexo, basandonos en estereotipos.
				ArrayList<String> generosQueleGustan = new ArrayList<String>();
				if(sexo.equalsIgnoreCase("SexoHombre")){ // SexoHombre
//					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroAccion);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroCienciaFiccion);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroGuerra);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroMisterio);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroSuspense);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroTerror);
					
				}else{ // SexoMujer
//					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGenero);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroDrama);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroFamiliar);
					generosQueleGustan.add(Vocabulario.NombreTipoNotificacionGeneroRomantico);
					
				}
				
				// TODO hay que guardar lo que le podría gustar por su sexo.
				

			itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoUsuario);
			itfUsoRecursoUsuario.modificarUsuario(Vocabulario.usuario.getNombre(),
					Vocabulario.usuario);


		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
