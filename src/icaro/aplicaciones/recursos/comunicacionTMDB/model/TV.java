package icaro.aplicaciones.recursos.comunicacionTMDB.model;

import java.util.Date;
import java.util.List;

public class TV {

	private int id; // 1
	private String name; // 1
	private String originalName; // 1
	private Date date; // 1
	private List<String> countries; // 2 A2 (ISO)
	private List<Genre> genres; // varias categorias!! //2
	private String overview; // 2
	private float voteAverage; // del 1 al 10
	private int voteCount; // del 1 al 10
	private float popularity; // 1
	private boolean completed;

	public TV() {
		super();
		this.id = -1;
		this.name = "";
		this.originalName = "";
		this.date = null;
		this.countries = null;
		this.genres = null;
		this.overview = "";
		this.voteAverage = 0;
		this.voteCount = 0;
		this.popularity = 0;
		this.completed = false;
	}

	public TV(int id, String name, String originalName, Date date, List<String> countries,
			List<Genre> genres, String overview, float voteAverage, int voteCount,
			float popularity, boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.originalName = originalName;
		this.date = date;
		this.countries = countries;
		this.genres = genres;
		this.overview = overview;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.popularity = popularity;
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

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(float voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public float getPopularity() {
		return popularity;
	}

	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
}
