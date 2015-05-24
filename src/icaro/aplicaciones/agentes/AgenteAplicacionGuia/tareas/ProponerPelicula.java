package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import java.util.Iterator;

import constantes.Frases;
import icaro.aplicaciones.informacion.Busqueda;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ProponerPelicula extends TareaSincrona {

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

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		Busqueda busqueda = Vocabulario.busqueda;
		Usuario usuario = Vocabulario.usuario;
		try {
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);
			Objetivo obj = (Objetivo) params[0]; // ObtenerPelicula
			Objetivo objAntiguo = (Objetivo) params[1]; // RecomendarPelicula
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);
				String mensajeAenviar = "";
				Movie movie = null;
				if (busqueda.getResult().size() > 0) {

					// Si es la primera vez que vamos a mostrarle lo que tenemos le diremos unas
					// cuantas de las que tenemos.
					final int MAX_A_MOSTRAR = 5;
					int trueSize = busqueda.getResult().size();
					int size = ((trueSize > MAX_A_MOSTRAR) ? MAX_A_MOSTRAR : trueSize);
					if (!Vocabulario.usuario.conozcoListaPelis) {
						Vocabulario.usuario.conozcoListaPelis = true;
						mensajeAenviar += Vocabulario.Prometen[0] + size + Vocabulario.Prometen[1];
						Iterator<Movie> itMovie = busqueda.getResult().iterator();
						int count = 0;
						while (itMovie.hasNext() && count < size) {
							Movie m = itMovie.next();
							mensajeAenviar += m.getTitle();
							if (count == size - 2) {
								mensajeAenviar += Vocabulario.Prometen[3];
							} else if (count < size - 2) {
								mensajeAenviar += Vocabulario.Prometen[2];
							}
							count++;
						}
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
						mensajeAenviar = "";
					}

					Iterator<Movie> itMovie = busqueda.getResult().iterator();
					int count = 0;
					while (itMovie.hasNext() && count < size) {
						Movie m = itMovie.next();
						if (movie == null
								&& !usuario.getIdValoraciones().contains(
										Integer.toString(m.getId()))
								&& !usuario.getPeliculasOdiadas().contains(
										Integer.toString(m.getId()))) {
							movie = m;
						} else if (usuario.getIdValoraciones()
								.contains(Integer.toString(m.getId()))
								&& !Vocabulario.usuario.conozcoListaPelis) {
							mensajeAenviar += m.getTitle() + Vocabulario.YaViste;
							recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
							mensajeAenviar = "";
						} else if (usuario.getPeliculasOdiadas().contains(
								Integer.toString(m.getId()))
								&& !Vocabulario.usuario.conozcoListaPelis) {
							mensajeAenviar += m.getTitle() + Vocabulario.YaOdias;
							recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
							mensajeAenviar = "";
						}
						count++;
					}

					if (movie != null) {
						// Frase aleatoria + pelicula que le queremos recomendar.
						mensajeAenviar += Frases.Recomienda();
						// TODO parece que cuando le dices muchas veces que no quieres ver la peli
						// salta null pointer exception en la linea de aquí abajo.
						mensajeAenviar += (movie.getTitle() + ". " + Frases.Params());
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
						usuario.setPeliculaActual(new Valoracion(Integer.toString(movie.getId()),
								null));
						// Si encuentra una peli pone ObtenerPelicula a Solving
						obj.setSolving(); // ObtenerPelicula
						this.getEnvioHechos().actualizarHecho(obj);
					} else {
						busqueda.setPage(busqueda.getPage() + 1);
						// Si no la encuentra tiene que ejecutar la búsqueda con página siguiente
						// Pone todo a Pending
						obj.setPending(); // ObtenerPelicula
						this.getEnvioHechos().actualizarHecho(obj);
						objAntiguo.setPending(); // RecomendarPelicula
						this.getEnvioHechos().actualizarHecho(objAntiguo);
					}
				} else {
					mensajeAenviar = Vocabulario.LimpiarBusqueda;
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					// Si la busqueda tiene tamaño 0, tendrá que borrar borrar parametros
					// de la busqueda y poner a Pending todo
					busqueda.reset();
					obj.setPending(); // ObtenerPelicula
					this.getEnvioHechos().actualizarHecho(obj);
					objAntiguo.setPending(); // RecomendarPelicula
					this.getEnvioHechos().actualizarHecho(objAntiguo);
				}
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, Vocabulario.ErrorObtencionInterfaz
								+ Vocabulario.IdentRecursoComunicacionTMDB,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, Vocabulario.ErrorAccesoInterfaz
							+ Vocabulario.IdentRecursoComunicacionTMDB, CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
