package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerPelicula;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

<<<<<<< HEAD
			
			// Si hay un filtro con generos
			if(VocabularioGestionCitas.busqueda.getGenres().size() > 0){
				Notificacion notif = new Notificacion();
		 		notif.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionComprobarDatosBusqueda);
		 		getComunicator().enviarInfoAotroAgente(notif, VocabularioGestionCitas.IdentAgenteAplicacionGuia);
			}
			// Si no hay generos en la busqueda actual intentamos deducir alguno, si no le preguntamos al usuario
			else{

				// Intentar deducir generos.
				HashMap<Integer, Integer> historial = historialGenerosVistos(5);
				boolean encontrado = false;
				ArrayList<Integer> listaGenerosMasVistos = dameGenerosMasVistos(historial, 3);
				encontrado = listaGenerosMasVistos.size() > 0;
				
				if(encontrado){
					// TODO HAS DEDUCIDO UN GENERO: GUARDAR listaGenerosMasVistos EN LA LISTA PARA BUSCAR PELIS. (ADICIONALMENTE PODRIAMOS MOSTRAR UN MENSAJE AL USUARIO PARA QUE SEPA QUE LO HEMOS DEDUCIDO)
					// TODO LANZAR UN OBJETIVO POR EJEMPLO EL DE PREGUNTAR SI QUIERE FILTRAR POR ALGUN ACTOR.
				}else{
					// Si no se ha podido deducir un genero. Preguntamos al usuario.
					String identDeEstaTarea = this.getIdentTarea();
					String identAgenteOrdenante = this.getIdentAgente();
					try {
						// Se busca la interfaz del recurso en el repositorio de interfaces.
						ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
=======
			// TODO Mirar en el objeto estï¿½tico usuario a ver si
			// las 3 ultimas pelis son del mismo gï¿½nero
			// o
			// las 5 ultimas suman 3 del mismo gï¿½nero
			// deducimos que quiere ver ese gï¿½nero
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
>>>>>>> origin/master
						if (recComunicacionChat != null) {
							recComunicacionChat
									.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);

							// Preguntar el gï¿½nero que le apetece ver
							String mensajeAenviar = "De que genero te apetece ver la peli?";
							obj.setSolving();
<<<<<<< HEAD
							this.getEnvioHechos().actualizarHecho(obj); // TODO OJO AQUI ACTUALIZAMOS OBJ A SOLVING!!!
							
=======
							// TODO NO HACE FALTA HACER EL UPDATE? COMPROBAR QUE SE LANZA LA REGLA
							// QUE ESPERA A LA RESPUESTA

>>>>>>> origin/master
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
<<<<<<< HEAD
				
=======
				this.getEnvioHechos().insertarHecho(new ObtenerPelicula());
>>>>>>> origin/master
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}
<<<<<<< HEAD

	private HashMap<Integer, Integer> historialGenerosVistos(int maximoNumeroPeliculasProcesar) throws Exception{
		ArrayList<Valoracion> listaValoraciones = VocabularioGestionCitas.usuario.getValoraciones();
		Iterator<Valoracion> itValoraciones = listaValoraciones.iterator();
		
		// Vamos a utilizar el recurso TMDB para buscar las películas y acceder a su informacion.
		ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionTMDB);
		// Guardaremos para cada genero un contador de apariciones.
		HashMap<Integer, Integer> historial = new HashMap<Integer, Integer>();
		// TODO Las valoraciones de películas (caso: ya la he visto y le pongo X nota), deberían introducirse al final de la lista.
		while(itValoraciones.hasNext() && maximoNumeroPeliculasProcesar > 0){ // Recorremos las valoraciones para saber las películas que ha visto, y por tanto sus generos asociados. 
			int idPelicula = Integer.parseInt(itValoraciones.next().getIdPelicula());
			Movie movie = itfUsoComunicacionTMDB.getMovie(idPelicula, null); // language = NULL !!! ??? !!!
			
			List<Genre> genres = movie.getGenres();
			Iterator<Genre> itGenre = genres.iterator();
			while(itGenre.hasNext()){
				int genreId = itGenre.next().getId();
				if(historial.containsKey(genreId)){ // Si ya hay una peli que tenía un género lo aumentamos.
					int count = historial.get(genreId);
					count++;
					historial.put(genreId, count);
				}else{
					historial.put(genreId, 1);
				}
			}
			
			maximoNumeroPeliculasProcesar--;
		}
		
		return historial;
	}
	
	private ArrayList<Integer> dameGenerosMasVistos(HashMap<Integer, Integer> historialGeneros, int vecesMinimas){
		ArrayList<Integer> masVistos = new ArrayList<Integer>();
		int loMasVisto = 0;
		
		Iterator<Map.Entry<Integer, Integer>> itHistorial = historialGeneros.entrySet().iterator();
		while(itHistorial.hasNext()){ // Recorremos el historial de generos.
			Map.Entry<Integer, Integer> movie = itHistorial.next();
			if(movie.getValue() >= vecesMinimas && movie.getValue() >= loMasVisto){ // Si el numero de apariciones es >= que vecesMinimas.
				if(movie.getValue() > loMasVisto){ // Si es > que el mas visto lo añadimos y  borramos todo lo anterior.
					masVistos = new ArrayList<Integer>(); // Creamos un array nuevo para borrar lo anterior.
				}
				masVistos.add(movie.getKey()); // Añadimos el id del género.
			}
		}
		
		return masVistos;
	}
	
=======
>>>>>>> origin/master
}
