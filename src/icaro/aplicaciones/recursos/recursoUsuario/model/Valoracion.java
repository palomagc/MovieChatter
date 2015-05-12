package icaro.aplicaciones.recursos.recursoUsuario.model;

public class Valoracion {

	private String idPelicula;
	private String nota;
	
	/**
	 * Nueva pelicula sin valorar vista por el usuario (nota = -1)
	 * @param idPelicula Identificador de la pelicula
	 */
	public Valoracion(String idPelicula){
		this.idPelicula = idPelicula;
		this.nota = null;
	}
	
	/**
	 * Nueva pelicula con valoracion vista por el usuario
	 * @param idPelicula Identificador de la pelicula
	 * @param nota Nota de la pelicula
	 */
	public Valoracion(String idPelicula, String nota){
		this.idPelicula = idPelicula;
		this.nota = nota;
	}

	
	
	public String getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(String idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}
