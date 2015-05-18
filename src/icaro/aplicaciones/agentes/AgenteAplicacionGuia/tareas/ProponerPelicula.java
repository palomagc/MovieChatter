package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import java.util.Iterator;

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
			Objetivo obj = (Objetivo) params[0];
			Objetivo objAntiguo = (Objetivo) params[1];
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);
				String mensajeAenviar = "";
				Movie movie = null;
				if (busqueda.getResult().size() > 0) {
					
					// Si es la primera vez que vamos a mostrarle lo que tenemos le diremos unas cuantas de las que tenemos.
					final int MAX_A_MOSTRAR = 5;
					int trueSize = busqueda.getResult().size();
					int size = ((trueSize > MAX_A_MOSTRAR) ? MAX_A_MOSTRAR : trueSize);
					mensajeAenviar += "Me s� unas cuantas, las " + size + " que m�s prometen son ";
					Iterator<Movie> itMovie = busqueda.getResult().iterator();
					int count = 0;
					while(itMovie.hasNext() && count < size) {
						Movie m = itMovie.next();
						mensajeAenviar += m.getTitle();
						if(count == size-2){
							mensajeAenviar += " y "; 
						}else if(count < size-2){
							mensajeAenviar += ", ";
						}
						count++;
					}
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					mensajeAenviar = "";
					
					itMovie = busqueda.getResult().iterator();
					count = 0;
					while(itMovie.hasNext() && count < size) {
						Movie m = itMovie.next();
						if (movie == null && !usuario.getIdValoraciones().contains(Integer.toString(m.getId())) && !usuario.getPeliculasOdiadas().contains(Integer.toString(m.getId()))){
							movie = m;
						}else if(usuario.getIdValoraciones().contains(Integer.toString(m.getId()))){
							mensajeAenviar += m.getTitle() + " ya la has visto...\n";
							recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
							mensajeAenviar = "";
						}else if(usuario.getPeliculasOdiadas().contains(Integer.toString(m.getId()))){
							mensajeAenviar += m.getTitle() + " la odias...\n";
							recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
							mensajeAenviar = "";
						}
						count++;
					}
					if (movie != null) {
						// Frase aleatoria + pelicipa que le queremos recomendar.
						int numRecomienda = (int) ((100 * Math.random()) % Vocabulario.Recomienda.length);
						mensajeAenviar += Vocabulario.Recomienda[numRecomienda] + "  ";
						int numParams = (int) ((100 * Math.random()) % Vocabulario.Params.length);
						// TODO parece que cuando le dices muchhas veces que no quieres ver la peli
						// salta null pointer exception en la linea de aqu� abajo.
						mensajeAenviar += (movie.getTitle() + ". " + Vocabulario.Params[numParams]);
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
						usuario.setPeliculaActual(new Valoracion(Integer.toString(movie.getId()),
								null));
						// Si encuentra una peli pone ObtenerPelicula a Solving
						obj.setSolving();	// ObtenerPelicula
						this.getEnvioHechos().actualizarHecho(obj);
					} else {
						busqueda.setPage(busqueda.getPage()+1);
						// Si no la encuentra tiene que ejecutar la b�squeda con p�gina siguiente
						// Pone todo a Pending
						obj.setPending();	// ObtenerPelicula
						this.getEnvioHechos().actualizarHecho(obj);
						objAntiguo.setPending();	// RecomendarPelicula
						this.getEnvioHechos().actualizarHecho(objAntiguo);
					}
				} else {
					mensajeAenviar = Vocabulario.LimpiarBusqueda;
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					// Si la busqueda tiene tama�o 0, tendr� que borrar borrar parametros
					// de la busqueda y poner a Pending todo
					busqueda.reset();
					obj.setPending();	// ObtenerPelicula
					this.getEnvioHechos().actualizarHecho(obj);
					objAntiguo.setPending();	// RecomendarPelicula
					this.getEnvioHechos().actualizarHecho(objAntiguo);
				}
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-AlObtener:Interfaz:"
								+ Vocabulario.IdentRecursoComunicacionTMDB,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ Vocabulario.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
