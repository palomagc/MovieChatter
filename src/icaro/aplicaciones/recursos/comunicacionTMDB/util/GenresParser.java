package icaro.aplicaciones.recursos.comunicacionTMDB.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;

/*
 * Campos de Lista Genres: id, name
 */

public class GenresParser {

	public static List<Genre> parseGenres(InputStream in) {
		List<Genre> genreList = new ArrayList<Genre>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(new InputStreamReader(in));
			JSONArray array = GetJSON.getArray(jsonObj, "genres");

			if (array != null && !array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					Genre genre = new Genre();

					genre.setId(GetJSON.getInteger(obj, "id"));
					genre.setName(GetJSON.getString(obj, "name"));
					genreList.add(genre);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genreList;
	}
}
