package icaro.aplicaciones.recursos.comunicacionTMDB.model;

import java.util.Date;
import java.util.List;

/*
 * Campos Movie: adult, backdrop_path, belongs_to_collection, budget, genres, homepage,
 * id, imdb_id, original_language, original_title, overview, popularity, poster_path, production_companies,
 * production_countries, release_date, revenue, runtime, spoken_languages, status, tagline,
 * title, video, vote_average, vote_count
 * 
 * Variante Lista: adult, backdrop_path, id, original_title, release_date, poster_path,
 * popularity, title, video, vote_average, vote_count
 */

public class Movie {

	private int id;
	private String title;
	private String originalTitle;
	private Date date;
	private int duration; // en mins
	private List<String> producerCountries;
	private List<Genre> genres; // varias categorias!!
	private String overview;
	private float voteAverage; // del 1 al 10
	private int voteCount; // del 1 al 10
	private float popularity;
	private int collectionId;
	private String collectionName;
	private boolean completed;

	public Movie() {
		super();
		this.id = -1;
		this.title = "";
		this.originalTitle = "";
		this.date = null;
		this.duration = 0;
		this.producerCountries = null;
		this.genres = null;
		this.overview = "";
		this.voteAverage = 0;
		this.voteCount = 0;
		this.popularity = 0;
		this.collectionId = -1;
		this.collectionName = "";
		this.completed = false;
	}

	public Movie(int id, String title, String originalTitle, Date date,
			int duration, List<String> producerCountries, List<Genre> genres,
			String overview, float voteAverage, int voteCount, float popularity,
			int collectionId, String collectionName, boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.originalTitle = originalTitle;
		this.date = date;
		this.duration = duration;
		this.producerCountries = producerCountries;
		this.genres = genres;
		this.overview = overview;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.popularity = popularity;
		this.collectionId = collectionId;
		this.collectionName = collectionName;
		this.completed = completed;
	}
// TODO !!!!!!!!!!!!1
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<String> getProducerCountries() {
		return producerCountries;
	}

	public void setProducerCountries(List<String> producerCountries) {
		this.producerCountries = producerCountries;
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

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
