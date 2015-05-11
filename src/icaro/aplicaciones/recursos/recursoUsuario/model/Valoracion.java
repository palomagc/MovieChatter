package icaro.aplicaciones.recursos.recursoUsuario.model;

public class Valoracion {

	private String idPelicula;
	private double nota;
	
	/**
	 * Nueva pelicula sin valorar vista por el usuario (nota = -1)
	 * @param idPelicula Identificador de la pelicula
	 */
	public Valoracion(String idPelicula){
		this.idPelicula = idPelicula;
		this.nota = -1;
	}
	
	/**
	 * Nueva pelicula con valoracion vista por el usuario
	 * @param idPelicula Identificador de la pelicula
	 * @param nota Nota de la pelicula
	 */
	public Valoracion(String idPelicula, double nota){
		this.idPelicula = idPelicula;
		this.nota = nota;
	}

	
	
	public String getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(String idPelicula) {
		this.idPelicula = idPelicula;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

}
