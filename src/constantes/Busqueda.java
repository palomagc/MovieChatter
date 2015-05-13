package constantes;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;

import java.util.ArrayList;
import java.util.List;

public final class Busqueda {
	
	private static List<Integer> genres;
	private static List<Integer> people;
	private static List<Movie> result;
	private static String year;
	public Busqueda(){
		genres = new ArrayList<Integer>();
		people = new ArrayList<Integer>();
		result = new ArrayList<Movie>();
		year = null;
	}
	
	public void addGenre(Integer param) { genres.add(param); }
	public void addPerson(Integer param) { people.add(param); }
	public void setYear(String param) { year = param; }
	public void setResult(List<Movie> resultAux) { result = resultAux; }
	public List<Integer> getGenres() { return genres; }
	public List<Integer> getPeople() { return people; }
	public List<Movie> getResult() { return result; }
	public String getYear() { return year; }

	public void reset() {
		result.clear();
		genres.clear();
		people.clear();
		year = null;
	}

}
