package constantes;

import java.util.ArrayList;
import java.util.List;

public final class Busqueda {
	
	private static List<Integer> genres;
	private static List<Integer> people;
	private static List<String> result;
	private static String year;
	private static boolean ready;
	private Busqueda(){
		genres = new ArrayList<Integer>();
		people = new ArrayList<Integer>();
		result = new ArrayList<String>();
		year = null;
		ready = false;
	}
	
	public static void addGenre(Integer param) { genres.add(param); }
	public static void addPerson(Integer param) { people.add(param); }
	public static void setYear(String param) { year = param; }
	public static void setResult(List<String> resultAux) { result = resultAux; }
	public static void setReady(boolean readyAux) { ready = readyAux; }
	public static List<Integer> getGenres() { return genres; }
	public static List<Integer> getPeople() { return people; }
	public static List<String> getResult() { return result; }
	public static String getYear() { return year; }
	public static boolean isReady() { return ready; }
	public static void resetParams() {
		genres.clear();
		people.clear();
		year = null;
	}

}
