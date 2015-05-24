package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerGenero;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class LanzarObjetivoObtenerGenero extends TareaSincrona {

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
			this.getEnvioHechos().insertarHecho(new ObtenerGenero());
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					Vocabulario.ErrorEjecucionTarea + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
