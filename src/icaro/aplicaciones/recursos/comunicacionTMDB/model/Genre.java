package icaro.aplicaciones.recursos.comunicacionTMDB.model;

/*
 * Campos de Lista Genres: id, name
 */

public class Genre {

	private int id;
	private String name;
	
	public Genre(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Genre() {
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

	@Override
	public boolean equals(Object another) {
		if(!(another instanceof Genre))
			return false;
		return this.id == ((Genre)another).id;
	}
}
