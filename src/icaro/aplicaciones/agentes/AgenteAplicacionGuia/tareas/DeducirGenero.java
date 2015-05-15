package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerPelicula;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import java.util.ArrayList;
import java.util.Iterator;

public class DeducirGenero extends TareaSincrona {

	/**
	 * Constructor
	 *
	 * @param Description
	 *            of the Parameter
	 * @param Description
	 *            of the Parameter
	 */
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		try {
			// Objetivo
			Objetivo obj = (Objetivo) params[0];

			// TODO Mirar en el objeto est�tico usuario a ver si
			// las 3 ultimas pelis son del mismo g�nero
			// o
			// las 5 ultimas suman 3 del mismo g�nero
			// deducimos que quiere ver ese g�nero
			ArrayList<Valoracion> listaValoraciones = VocabularioGestionCitas.usuario
					.getValoraciones();
			Iterator<Valoracion> itValoraciones = listaValoraciones.iterator();

			int contadorPelisRestantes = 3;
			int rachaDeGenero = 0;
			String generoActual = "";
			boolean encontrado = false;
			/*
			 * while((itValoraciones.hasNext() || contadorPelisRestantes <= 0) && !encontrado){
			 * Valoracion v = itValoraciones.next(); Movie movie = null; ItfUsoComunicacionTMDB
			 * itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB)
			 * NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
			 * .obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);
			 * if(itfUsoComunicacionTMDB != null){ // TODO aqui salta excepcion movie =
			 * itfUsoComunicacionTMDB.getMovie(Integer.parseInt(v.getIdPelicula()), null); }
			 * ArrayList<Genre> generos = (ArrayList<Genre>) movie.getGenres();
			 * if(generoActual.equals("")){ generoActual = generos.get(0).toString(); }
			 * if(generos.contains(generoActual)){ rachaDeGenero++; } contadorPelisRestantes--; }
			 * if(rachaDeGenero >= 3){ encontrado = true; }
			 */

			/*
			 * itValoraciones = listaValoraciones.iterator(); contadorPelisRestantes = 5;
			 * while((itValoraciones.hasNext() || contadorPelisRestantes <= 0) && !encontrado){
			 * Valoracion v = itValoraciones.next(); Movie movie = null; ItfUsoComunicacionTMDB
			 * itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB)
			 * NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
			 * .obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);
			 * if(itfUsoComunicacionTMDB != null){ movie =
			 * itfUsoComunicacionTMDB.getMovie(Integer.parseInt(v.getIdPelicula()), null); }
			 * ArrayList<Genre> generos = (ArrayList<Genre>) movie.getGenres();
			 * if(generoActual.equals("")){ generoActual = generos.get(0).toString(); }
			 * if(generos.contains(generoActual)){ rachaDeGenero++; } contadorPelisRestantes--; }
			 * if(rachaDeGenero >= 3){ encontrado = true; }
			 */
			if (encontrado) {
				// TODO HAS DEDUCIDO UN GENERO
			} else {
				// TODO MEJORAR ALGORITMO
				// Si no hay generos por los que buscar
				if (VocabularioGestionCitas.busqueda.getGenres().size() <= 0) {
					String identDeEstaTarea = this.getIdentTarea();
					String identAgenteOrdenante = this.getIdentAgente();
					try {
						// // Se busca la interfaz del recurso en el repositorio de
						// interfaces
						ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
								.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
						if (recComunicacionChat != null) {
							recComunicacionChat
									.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);

							// Preguntar el g�nero que le apetece ver
							String mensajeAenviar = "De que genero te apetece ver la peli?";
							obj.setSolving();
							// TODO NO HACE FALTA HACER EL UPDATE? COMPROBAR QUE SE LANZA LA REGLA
							// QUE ESPERA A LA RESPUESTA

							recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
						} else {
							identAgenteOrdenante = this.getAgente().getIdentAgente();
							this.generarInformeConCausaTerminacion(identDeEstaTarea,
									contextoEjecucionTarea, identAgenteOrdenante,
									"Error-AlObtener:Interfaz:"
											+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
									CausaTerminacionTarea.ERROR);
						}
					} catch (Exception e) {
						this.generarInformeConCausaTerminacion(identDeEstaTarea,
								contextoEjecucionTarea, identAgenteOrdenante,
								"Error-Acceso:Interfaz:"
										+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
								CausaTerminacionTarea.ERROR);
						e.printStackTrace();
					}
				}
				this.getEnvioHechos().insertarHecho(new ObtenerPelicula());
			}
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
}
