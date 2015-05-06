package icaro.aplicaciones.recursos.comunicacionTMDB.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;

/*
 * Campos de Person: adult, also_known_as, biography, birthday, deathday,
 *  homepage, id, imdb_id, name, place_of_birth, popularity, profile_path
 *  
 * Variante Lista: adult, id, known_for, name, popularity, profile_path
 */

public class PeopleParser {

	public static List<Person> parseList(InputStream in) {
		List<Person> personList = new ArrayList<Person>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(new InputStreamReader(in));
			JSONArray array = GetJSON.getArray(jsonObj, "results");
			
			if (array!=null && !array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					Person person = new Person();
	
					person.setId(GetJSON.getInteger(obj, "id"));
					person.setName(GetJSON.getString(obj, "name"));
					person.setPopularity(GetJSON.getFloat(obj, "popularity"));
					personList.add(person);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personList;
	}

	public static Person parsePerson(InputStream content) {
		Person person = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(content));
			person = new Person();

			person.setId(GetJSON.getInteger(obj, "id"));
			person.setName(GetJSON.getString(obj, "name"));
			person.setBiography(GetJSON.getString(obj, "biography"));
			person.setBirthday(GetJSON.getDate(obj, "birthday"));
			person.setDeathday(GetJSON.getDate(obj, "deathday"));
			person.setBirthPlace(GetJSON.getString(obj, "place_of_birth"));
			person.setPopularity(GetJSON.getFloat(obj, "popularity"));

			// also_known
			List<String> alsoList = new ArrayList<String>();
			JSONArray array = GetJSON.getArray(obj, "also_known");
			if (array!=null && !array.isEmpty()) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObj = (JSONObject) array.get(i);
					alsoList.add(GetJSON.getString(jsonObj, "name"));
				}
				person.setAlsoKnown(alsoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
}
