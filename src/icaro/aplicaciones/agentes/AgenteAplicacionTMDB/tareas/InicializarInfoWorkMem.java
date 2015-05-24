/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionTMDB.objetivos.ListarPeliculas;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class InicializarInfoWorkMem extends TareaSincrona {

	@Override
	public void ejecutar(Object... params) {
		try {
			// Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
			// String identTarea = this.getIdentTarea();
			// String nombreAgenteEmisor = this.getIdentAgente();
			this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(true);
			this.getItfConfigMotorDeReglas()
					.setfactHandlesMonitoring_afterActivationFired_DEBUGGING(true);
			this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
			this.getEnvioHechos().insertarHecho(new ListarPeliculas());

		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					Vocabulario.ErrorEjecucionTarea + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
