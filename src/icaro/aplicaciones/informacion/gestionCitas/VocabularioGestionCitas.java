/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.informacion.gestionCitas;

import constantes.Constantes;



/**
 *
 * @author FGarijo
 */
public class VocabularioGestionCitas {
	
	// SALUDO
	public static final String NombreTipoNoticacionSaludo="Saludo";
	public static final String SaludoInicial1="Hola, yo soy " + Constantes.SYSTEM_NAME + ", tu recomendador de peliculas";
    public static final String[] SaludoInicial2={"Hola", "Que tal", "Buenas", "Que hay"};
	
    // PRESENTACI�N
    public static final String InfoGeneralFuncionalidad= "Soy un cinefilo, y me encantaria ayudarte a encontrar peliculas";
    
    // PEDIR INFO AL USUARIO
    public static final String[] PeticionInformacionGeneral1={"Te puedo sugerir alguna hoy?", "Quieres que te recomiende alguna?", "Necesitas ayuda para encontrar alguna?"};
    
    // DESPEDIDA
    public static final String[] Despedida = {"Adios", "Hasta la proxima", "Nos vemos", "Que te vaya bien"};
    
    public static final String ResultadoAutenticacion_DatosNoValidos= "usuarioNoValido";
    public static final String ResultadoAutenticacion_DatosValidos= "usuarioValido";
    public static final String NotificacionAccesoAutorizado="Autorizacion_Acceso_Notificado_Al_Usuario";
    public static final String ErrorObtencionInterfaz_RecPersistencia = "Error-AlObtener:Interfaz_Recurso_Persistencia";
    public static final String IdentRecursoExtractorSemantico= "ExtractorSemantico1";
    public static final String IdentRecursoComunicacionChat= "ComunicacionChat1";
    public static final String IdentAgenteAplicacionDialogoCitas= "AgenteAplicacionDialogoCitas1";
    public static final String IdentConexionAgte= "AgteMovies";
    public static final String IdentRecursoPersistenciaChat= "PersistenciaChat1";
    public static final String InfoUsuarioYaExiste= "infoUsuarioYaExistente";
    public static final String InfoUsuarioAltaGuardada="InfoAltaUsuarioGuardada"; 
    public static final String RespuestaNoEniendo1="Lo siento pero no entiendo nada de lo que dice";
    public static final String RespuestaNoEniendo2="Lo siento pero sigo sin entender nada";
    public static final String RespuestaNoEniendo3="definitivamente no me entero de lo que dice , creo que vamos a terminar la conversación";
    public static final String Despedida1="Bueno tenemos que dejarlo ha sido un placer";
    public static final String ExtraccionSemanticaNull= "SinSemantica";
    public static final String preambuloNoHeEntendido = " No he compredido bien lo que me ha dicho, pero vayamos al grano";
    public static final String peticionInfoIicialCita1 = " podría decirme con quien quiere la cita ";
}
