/*
 * DenegarAcceso.java
 
 */
package icaro.aplicaciones.agentes.AgenteAplicacionAccesoADO.tareas;

import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.visualizacionAcceso.ItfUsoVisualizadorAcceso;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

/**
 * 
 * @author Francisco J Garijo
 */
public class DenegarAcceso extends Tarea {
	private String identAgenteOrdenante;
	private Objetivo contextoEjecucionTarea = null;

	// private String identRecursoVisualizacionAcceso
	// =Vocabulario.IdentRecursoVisualizacionAccesoInicial;
	@Override
	public void ejecutar(Object... params) {
		String identDeEstaTarea = getClass().getSimpleName();
		String identRecursoVisualizacionAcceso = (String) params[0];
		try {
			identAgenteOrdenante = this.getAgente().getIdentAgente();
			ItfUsoVisualizadorAcceso visualizadorAcceso = (ItfUsoVisualizadorAcceso) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfaz(NombresPredefinidos.ITF_USO + identRecursoVisualizacionAcceso);
			if (visualizadorAcceso == null)
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, Vocabulario.ErrorObtencionRecurso
								+ identRecursoVisualizacionAcceso, CausaTerminacionTarea.ERROR);
			else
				visualizadorAcceso
						.mostrarMensajeError("Acceso denegado",
								"Identificador de usuario o Contraseņa incorrectas. Introduzcalas de nuevo");
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, Vocabulario.ErrorObtencionRecursos,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
