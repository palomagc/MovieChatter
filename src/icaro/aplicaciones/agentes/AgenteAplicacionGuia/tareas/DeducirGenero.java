package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.ObtenerActor;
import icaro.aplicaciones.informacion.Notificacion;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
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
	// private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		try {
			// Objetivo
			Objetivo obj = (Objetivo) params[0];

			// Si hay un filtro con generos
			if (Vocabulario.busqueda.getGenres().size() > 0) {
				Notificacion notif = new Notificacion();
				notif.setTipoNotificacion(Vocabulario.NombreTipoNotificacionComprobarDatosBusqueda);
				getComunicator()
						.enviarInfoAotroAgente(notif, Vocabulario.IdentAgenteAplicacionGuia);
			}
			// Si no hay generos en la busqueda actual intentamos deducir alguno, si no le
			// preguntamos al usuario
			else {

				// Intentar deducir generos.
				HashMap<Integer, Integer> historial = historialGenerosVistos(5);
				boolean encontrado = false;
				ArrayList<Integer> listaGenerosMasVistos = dameGenerosMasVistos(historial, 3);
				encontrado = listaGenerosMasVistos.size() > 0;

				if (encontrado) {
					// TODO HAS DEDUCIDO UN GENERO: GUARDAR listaGenerosMasVistos EN LA LISTA PARA
					// BUSCAR PELIS. (ADICIONALMENTE PODRIAMOS MOSTRAR UN MENSAJE AL USUARIO PARA
					// QUE SEPA QUE LO HEMOS DEDUCIDO)
					// TODO LANZAR UN OBJETIVO POR EJEMPLO EL DE PREGUNTAR SI QUIERE FILTRAR POR
					// ALGUN ACTOR.
					this.getEnvioHechos().actualizarHecho(new ObtenerActor());
				} else {
					// Si no se ha podido deducir un genero. Preguntamos al usuario.
					// String identDeEstaTarea = this.getIdentTarea();
					// String identAgenteOrdenante = this.getIdentAgente();
					try {
						// Se busca la interfaz del recurso en el repositorio de interfaces.
						ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
								.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);

						if (recComunicacionChat != null) {
							recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);

							// Preguntar el genero que le apetece ver
							String mensajeAenviar = Vocabulario.EligeGenero;
							recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
							obj.setSolving(); // ObtenerGenero
							this.getEnvioHechos().actualizarHecho(obj); // TODO OJO AQUI
																		// ACTUALIZAMOS OBJ A
																		// SOLVING!!!
						}
					} catch (Exception e) {
						e.printStackTrace();
						trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
								Vocabulario.ErrorEjecucionTarea + this.getIdentTarea() + e,
								InfoTraza.NivelTraza.error));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					Vocabulario.ErrorEjecucionTarea + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

	private HashMap<Integer, Integer> historialGenerosVistos(int maximoNumeroPeliculasProcesar)
			throws Exception {
		ArrayList<Valoracion> listaValoraciones = Vocabulario.usuario.getValoraciones();
		Iterator<Valoracion> itValoraciones = listaValoraciones.iterator();

		// Vamos a utilizar el recurso TMDB para buscar las pel�culas y acceder a su informacion.
		ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
				.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionTMDB);
		// Guardaremos para cada genero un contador de apariciones.
		HashMap<Integer, Integer> historial = new HashMap<Integer, Integer>();
		// TODO Las valoraciones de pel�culas (caso: ya la he visto y le pongo X nota), deber�an
		// introducirse al final de la lista.
		while (itValoraciones.hasNext() && maximoNumeroPeliculasProcesar > 0) { // Recorremos las
																				// valoraciones para
																				// saber las
																				// pel�culas que ha
																				// visto, y por
																				// tanto sus generos
																				// asociados.
			int idPelicula = Integer.parseInt(itValoraciones.next().getIdPelicula());
			Movie movie = itfUsoComunicacionTMDB.getMovie(idPelicula, null); // language = NULL !!!
																				// ??? !!!

			List<Genre> genres = movie.getGenres();
			Iterator<Genre> itGenre = genres.iterator();
			while (itGenre.hasNext()) {
				int genreId = itGenre.next().getId();
				if (historial.containsKey(genreId)) { // Si ya hay una peli que ten�a un g�nero lo
														// aumentamos.
					int count = historial.get(genreId);
					count++;
					historial.put(genreId, count);
				} else {
					historial.put(genreId, 1);
				}
			}

			maximoNumeroPeliculasProcesar--;
		}

		return historial;
	}

	private ArrayList<Integer> dameGenerosMasVistos(HashMap<Integer, Integer> historialGeneros,
			int vecesMinimas) {
		ArrayList<Integer> masVistos = new ArrayList<Integer>();
		int loMasVisto = 0;

		Iterator<Map.Entry<Integer, Integer>> itHistorial = historialGeneros.entrySet().iterator();
		while (itHistorial.hasNext()) { // Recorremos el historial de generos.
			Map.Entry<Integer, Integer> movie = itHistorial.next();
			if (movie.getValue() >= vecesMinimas && movie.getValue() >= loMasVisto) { // Si el
																						// numero de
																						// apariciones
																						// es >= que
																						// vecesMinimas.
				if (movie.getValue() > loMasVisto) { // Si es > que el mas visto lo a�adimos y
														// borramos todo lo anterior.
					masVistos = new ArrayList<Integer>(); // Creamos un array nuevo para borrar lo
															// anterior.
				}
				masVistos.add(movie.getKey()); // A�adimos el id del g�nero.
			}
		}

		return masVistos;
	}

}
