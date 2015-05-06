package icaro.aplicaciones.recursos.comunicacionTMDB.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Campos de Person: adult, also_known_as, biography, birthday, deathday,
 *  homepage, id, imdb_id, name, place_of_birth, popularity, profile_path
 *  
 * Variante Lista: adult, id, known_for, name, popularity, profile_path
 */

public class Person {

	public enum PersonType {CREW, CAST, PEOPLE}
	private int id;
	private String name;
	private String biography;
	private Date birthday;
	private Date deathday;
	private String birthPlace;
	private float popularity;
	private List<String> alsoKnown;
	private PersonType personType;
	private boolean completed;

	public Person() {
		super();
		this.id = -1;
		this.name = "";
		this.biography = "";
		this.birthday = null;
		this.deathday = null;
		this.birthPlace = "";
		this.popularity = 0;
		this.alsoKnown = new ArrayList<String>();
		this.personType = null;
		this.completed = false;
	}

	public Person(int id, String name, String biography, Date birthday,
			Date deathday, String birthPlace, float popularity,
			List<String> alsoKnown, PersonType personType, boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.biography = biography;
		this.birthday = birthday;
		this.deathday = deathday;
		this.birthPlace = birthPlace;
		this.popularity = popularity;
		this.alsoKnown = alsoKnown;
		this.personType = personType;
		this.completed = completed;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDeathday() {
		return deathday;
	}

	public void setDeathday(Date deathday) {
		this.deathday = deathday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public float getPopularity() {
		return popularity;
	}

	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}

	public List<String> getAlsoKnown() {
		return alsoKnown;
	}

	public void setAlsoKnown(List<String> alsoKnown) {
		this.alsoKnown = alsoKnown;
	}
	
	public PersonType getPersonType() {
		return this.personType;
	}
	
	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
}
