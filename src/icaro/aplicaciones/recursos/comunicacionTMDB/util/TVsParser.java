package icaro.aplicaciones.recursos.comunicacionTMDB.util;

/*
 * Campos TV: backdrop_path, created_by, episode_run_time, first_air_date, genres,
 * homepage, id, in_production, languages, last_air_date, name, networks, number_of_episodes,
 * number_of_seasons, origin_country, original_language, original_name, overview,
 * popularity, poster_path, production_companies, seasons, status, type, vote_average,
 * vote_count
 * 
 * Variante Lista: backdrop_path, id, original_name, first_air_date, origin_country:["IE","CA"],
 * poster_path, popularity, name, vote_average, vote_count
 * 
 */

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.TV;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;

public class TVsParser {

	public static List<TV> parseList(InputStream in) {

		List<TV> TVList = new ArrayList<TV>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(new InputStreamReader(in));
			JSONArray array = GetJSON.getArray(jsonObj, "results");

			if (array != null && !array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					TV tv = new TV();

					tv.setId(GetJSON.getInteger(obj, "id"));
					tv.setName(GetJSON.getString(obj, "name"));
					tv.setOriginalName(GetJSON.getString(obj, "original_name"));
					tv.setDate(GetJSON.getDate(obj, "first_air_date"));
					tv.setVoteAverage(GetJSON.getFloat(obj, "vote_average"));
					tv.setVoteCount(GetJSON.getInteger(obj, "vote_count"));
					tv.setPopularity(GetJSON.getFloat(obj, "popularity"));
					TVList.add(tv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TVList;
	}

	public static TV parseTV(InputStream content) {
		TV tv = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(content));
			tv = new TV();

			tv.setId(GetJSON.getInteger(obj, "id"));
			tv.setName(GetJSON.getString(obj, "name"));
			tv.setOriginalName(GetJSON.getString(obj, "original_name"));
			tv.setDate(GetJSON.getDate(obj, "first_air_date"));
			tv.setVoteAverage(GetJSON.getFloat(obj, "vote_average"));
			tv.setVoteCount(GetJSON.getInteger(obj, "vote_count"));
			tv.setPopularity(GetJSON.getFloat(obj, "popularity"));
			tv.setOverview(GetJSON.getString(obj, "overview"));

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
			tv.setGenres(genreList);

			// countries
			List<String> countries = new ArrayList<String>();
			array = GetJSON.getArray(obj, "origin_country");

			if (array != null && !array.isEmpty()) {
				for (Object o : array)
					countries.add(GetJSON.getString((JSONObject) o, "name"));
			}
			tv.setCountries(countries);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tv;
	}
}
