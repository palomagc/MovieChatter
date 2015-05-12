package constantes;

import java.util.ArrayList;
import java.util.List;

public final class Busqueda {
	
	private static List<Integer> genres;
	private static List<Integer> people;
	private static List<String> result;
	private static String year;
	public Busqueda(){
		genres = new ArrayList<Integer>();
		people = new ArrayList<Integer>();
		result = new ArrayList<String>();
		year = null;
	}
	
	public void addGenre(Integer param) { genres.add(param); }
	public void addPerson(Integer param) { people.add(param); }
	public void setYear(String param) { year = param; }
	public void setResult(List<String> resultAux) { result = resultAux; }
	public List<Integer> getGenres() { return genres; }
	public List<Integer> getPeople() { return people; }
	public List<String> getResult() { return result; }
	public String getYear() { return year; }
	public void resetParams() {
		genres.clear();
		people.clear();
		year = null;
	}

}
