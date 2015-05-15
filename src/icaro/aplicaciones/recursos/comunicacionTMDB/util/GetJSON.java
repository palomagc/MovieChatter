package icaro.aplicaciones.recursos.comunicacionTMDB.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GetJSON {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",
			Locale.ENGLISH);

	public static String getString(JSONObject obj, String string) {
		String result = null;
		if (obj.get(string) != null)
			result = (String) obj.get(string);
		return result;
	}

	public static Integer getInteger(JSONObject obj, String string) {
		Integer result = null;
		if (obj.get(string) != null)
			result = Integer.parseInt(obj.get(string).toString());
		return result;
	}

	public static Float getFloat(JSONObject obj, String string) {
		Float result = null;
		if (obj.get(string) != null)
			result = ((Double) obj.get(string)).floatValue();
		return result;
	}

	public static Date getDate(JSONObject obj, String string) {
		Date result = null;
		if (obj.get(string) != null)
			try {
				result = dateFormatter.parse((String) obj.get(string));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return result;
	}

	public static JSONArray getArray(JSONObject obj, String string) {
		JSONArray result = null;
		if (obj.get(string) != null)
			result = (JSONArray) obj.get(string);
		return result;
	}
}
