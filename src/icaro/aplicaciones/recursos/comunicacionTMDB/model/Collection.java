package icaro.aplicaciones.recursos.comunicacionTMDB.model;

/*
 * Campos de Collection: id, name, overview, poster_path, backdrop_path,
 * parts: [(title, id, release_date, poster_path, backdrop_path),..]
 * 
 * Variante Lista: id, backdrop_path, name, poster_path
 */

public class Collection {
	private int id;
	private String name;

	// private String overview;
	// private List<Movie> parts;

	public Collection(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Collection() {
		super();
		this.id = -1;
		this.name = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
