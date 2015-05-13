package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.PreguntarDatosInicialesUsuario;
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
			
			// TODO revisar esto pero parece que ya funciona automágicamente
			
			String edad = (String) params[0];
			VocabularioGestionCitas.usuario.setEdad(edad);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;
			itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
			itfUsoRecursoUsuario.modificarUsuario(VocabularioGestionCitas.usuario.getNombre(), VocabularioGestionCitas.usuario);

			Objetivo objetivo = new PreguntarDatosInicialesUsuario();
			objetivo.setSolved();
			this.getEnvioHechos().insertarHecho(objetivo);
			
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

}
