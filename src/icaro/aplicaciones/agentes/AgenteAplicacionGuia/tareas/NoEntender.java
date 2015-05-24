/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerActor;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerDatosIniciales;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerEdad;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerGenero;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerInfoInterlocutor;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerPelicula;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerSexo;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerUltimaValoracion;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerValoracion;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ReconocerActor;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ReconocerDirector;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ReconocerPelicula;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ReconocerUsuario;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import constantes.Constantes;
import constantes.Frases;

/**
 * 
 * @author F Garijo
 */
public class NoEntender extends TareaSincrona {
	// private String identAgenteOrdenante;
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		Objetivo objetivoDelFoco = ((Focus) params[0]).getFoco();
		try {
			// Se busca la interfaz del recurso en el repositorio de interfaces
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);

				// Le decimos que lo ne hemos entendido.
				String mensajeAenviar = Frases.NoEntendido();
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

				Constantes.CONTADOR_ERRORES++; // Aumentamos este contador para saber cuántas veces
												// se ha equivocado el usuario y actuar de forma
												// razonable.
				if (Constantes.CONTADOR_ERRORES >= Constantes.MAXIMO_ERRORES) {
					Constantes.CONTADOR_ERRORES = 0;

					// Vamos a centrarnos en volver al objetivo inicial. Recomendar una peli,
					// intentaremos hacerlo sencillo un par de veces, si no cierra la aplicacion.
					Constantes.CONTADOR_REINTENTO++;

					if (Constantes.CONTADOR_REINTENTO >= Constantes.MAXIMO_ERRORES) {
						mensajeAenviar = Frases.NoNosEntendemosDarPorVencido();
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					} else {
						Objetivo obj = new RecomendarPelicula();
						obj.setSolving();
						this.getEnvioHechos().actualizarHecho(obj);
						mensajeAenviar = Frases.SolicitarResponderPreguntasFacil();
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					}

				} else {

					// TODO AQUI DEBERIAMOS MIRAR CUAL ES EL OBJETIVO ACTUAL Y DAR PISTAS O DECIRLE
					// QUE CAMBIAMOS DE OBJETIVO
					if (ObtenerActor.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoConseguirActor;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerDatosIniciales.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoDatosIni;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerEdad.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Frases.SolicitarEdad();
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerGenero.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Frases.SolicitarGenero();
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerInfoInterlocutor.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoDatos;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerPelicula.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoPelicula;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerSexo.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoSexo;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerUltimaValoracion.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoValorarUlt;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ObtenerValoracion.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoValorar;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (RecomendarPelicula.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoRecomendar;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ReconocerActor.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoActor;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ReconocerDirector.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoDirector;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ReconocerPelicula.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoPelicula;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

					} else if (ReconocerUsuario.class == objetivoDelFoco.getClass()) {
						mensajeAenviar = Vocabulario.IntentandoUsuario;
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					}

				}

			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, Vocabulario.ErrorObtencionInterfaz
								+ Vocabulario.IdentRecursoComunicacionChat,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, Vocabulario.ErrorAccesoInterfaz
							+ Vocabulario.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
