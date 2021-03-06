/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.AgenteAplicacionAccesoCognitivo.tareas;

/**
 *
 * @author FGarijo
 */
import icaro.aplicaciones.informacion.InfoAccesoSinValidar;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.persistenciaAccesoSimple.ItfUsoPersistenciaAccesoSimple;
import icaro.aplicaciones.recursos.visualizacionAcceso.ItfUsoVisualizadorAcceso;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.PerformativaUsuario;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;

/**
 *
 * @author Francisco J Garijo
 */
public class ValidarDatosAutenticacionUsuarioIT extends Tarea {
	private String identAgenteOrdenante;
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		String IdentRecursoVisualizacionAcceso = Vocabulario.IdentRecursoVisualizacionAccesoInicial;
		String IdentRecursoPersistencia = Vocabulario.IdentRecursoPersistenciaAcceso;
		// Se extraen los datos de los parametros
		PerformativaUsuario infoUsuario = (PerformativaUsuario) params[0];
		Object[] parametrosPerfortiva = (Object[]) infoUsuario.getParametros();
		InfoAccesoSinValidar infoAcceso = (InfoAccesoSinValidar) parametrosPerfortiva[0];
		try {
			// Se obtienen las interfaces de los recursos. Si no se pueden obtener las interfaces se
			// debe generar un informe de tarea
			String identTarea = this.getIdentTarea();
			identAgenteOrdenante = this.getIdentAgente();
			ItfUsoVisualizadorAcceso visualizadorAcceso = (ItfUsoVisualizadorAcceso) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfaz(NombresPredefinidos.ITF_USO + IdentRecursoVisualizacionAcceso);
			if (visualizadorAcceso == null)
				this.generarInformeConCausaTerminacion(identTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-Al Obtener la interfaz de u so de :"
								+ IdentRecursoVisualizacionAcceso, CausaTerminacionTarea.ERROR);
			else
				visualizadorAcceso.mostrarVisualizadorAcceso(identAgenteOrdenante,
						NombresPredefinidos.TIPO_COGNITIVO);
			ItfUsoPersistenciaAccesoSimple itfUsoPersistencia = (ItfUsoPersistenciaAccesoSimple) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfaz(NombresPredefinidos.ITF_USO + IdentRecursoPersistencia);
			if (itfUsoPersistencia == null)
				this.generarInformeConCausaTerminacion(identTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-AlObtener:Interfaz_"
								+ IdentRecursoPersistencia, CausaTerminacionTarea.ERROR);
			else {
				boolean resultadoValidacion = itfUsoPersistencia.compruebaUsuario(
						infoAcceso.tomaUsuario(), infoAcceso.tomaPassword());
				String contenidoInformeTarea;
				if (resultadoValidacion) {
					contenidoInformeTarea = Vocabulario.ResultadoAutenticacion_DatosValidos;
				} else {
					contenidoInformeTarea = Vocabulario.ResultadoAutenticacion_DatosNoValidos;
				}
				this.generarInformeOK(identTarea, contextoEjecucionTarea, identAgenteOrdenante,
						contenidoInformeTarea);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.generarInformeConCausaTerminacion(this.getIdentTarea(), contextoEjecucionTarea,
					identAgenteOrdenante, Vocabulario.ErrorInterfaz_RecPersistencia,
					CausaTerminacionTarea.ERROR);
		}
	}
}
