package icaro.aplicaciones.recursos.comunicacionTMDB.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;

/*
 * Campos Movie: adult, backdrop_path, belongs_to_collection, budget, genres, homepage,
 * id, imdb_id, original_language, original_title, overview, popularity, poster_path, production_companies,
 * production_countries, release_date, revenue, runtime, spoken_languages, status, tagline,
 * title, video, vote_average, vote_count
 * 
 * Variante Lista: adult, backdrop_path, id, original_title, release_date, poster_path,
 * popularity, title, video, vote_average, vote_count
 */

public class MoviesParser {

	public static List<Movie> parseList(InputStream in) {
		List<Movie> MovieList = new ArrayList<Movie>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(new InputStreamReader(in));
			JSONArray array = GetJSON.getArray(jsonObj, "results");

			if (array != null && !array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					Movie movie = new Movie();

					movie.setId(GetJSON.getInteger(obj, "id"));
					movie.setTitle(GetJSON.getString(obj, "title"));
					movie.setOriginalTitle(GetJSON.getString(obj, "original_title"));
					movie.setDate(GetJSON.getDate(obj, "release_date"));
					movie.setVoteAverage(GetJSON.getFloat(obj, "vote_average"));
					movie.setVoteCount(GetJSON.getInteger(obj, "vote_count"));
					movie.setPopularity(GetJSON.getFloat(obj, "popularity"));
					// movie.setCompleted(true);
					MovieList.add(movie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MovieList;
	}

	public static Movie parseMovie(InputStream content) {
		Movie movie = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(content));
			movie = new Movie();

			movie.setId(GetJSON.getInteger(obj, "id"));
			movie.setTitle(GetJSON.getString(obj, "title"));
			movie.setOriginalTitle(GetJSON.getString(obj, "original_title"));
			movie.setDate(GetJSON.getDate(obj, "release_date"));
			movie.setVoteAverage(GetJSON.getFloat(obj, "vote_average"));
			movie.setVoteCount(GetJSON.getInteger(obj, "vote_count"));
			movie.setPopularity(GetJSON.getFloat(obj, "popularity"));

			if (obj.get("belongs_to_collection") != null) {
				movie.setCollectionId(GetJSON.getInteger(
						((JSONObject) obj.get("belongs_to_collection")), "id"));
				movie.setCollectionName(GetJSON.getString(
						((JSONObject) obj.get("belongs_to_collection")), "name"));
			}
			movie.setDuration(GetJSON.getInteger(obj, "runtime"));
			movie.setOverview(GetJSON.getString(obj, "overview"));

			// genres
			List<Genre> genreList = new ArrayList<Genre>();
			JSONArray array = GetJSON.getArray(obj, "genres");

			if (array != null && !array.isEmpty()) {
				for (Object o : array) {
					Genre genre = new Genre();
					genre.setId(GetJSON.getInteger((JSONObject) o, "id"));
					genre.setName(GetJSON.getString((JSONObject) o, "name"));
					genreList.add(genre);
				}
			}
			movie.setGenres(genreList);

			// production_countries
			List<String> producerCountries = new ArrayList<String>();
			array = GetJSON.getArray(obj, "production_countries");

			if (array != null && !array.isEmpty()) {
				for (Object o : array)
					producerCountries.add(GetJSON.getString((JSONObject) o, "name"));
			}
			movie.setProducerCountries(producerCountries);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;
	}
}
