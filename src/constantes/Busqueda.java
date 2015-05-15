package constantes;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.orders.MovieOrder;
import icaro.aplicaciones.recursos.comunicacionTMDB.orders.MovieSort;

import java.util.ArrayList;
import java.util.List;

public final class Busqueda {

	private static List<Integer> genres;
	private static List<Integer> people;
	private static List<Movie> result;
	private static List<String> selected;
	private static String year;
	private static String language;
	private static String order;
	private static String sort;
	private static int page;

	public Busqueda() {
		genres = new ArrayList<Integer>();
		people = new ArrayList<Integer>();
		result = new ArrayList<Movie>();
		selected = new ArrayList<String>();
		year = null;
		language = "es";
		order = MovieOrder.Popular;
		sort = MovieSort.PopularityDesc;
		page = 1;
	}

	public void addGenre(Integer param) {
		genres.add(param);
	}

	public void addPerson(Integer param) {
		people.add(param);
	}

	public void addSelected(String param) {
		selected.add(param);
	}

	public void setYear(String param) {
		year = param;
	}

	public void setLanguage(String param) {
		language = param;
	}

	public void setOrder(String param) {
		order = param;
	}

	public void setSort(String param) {
		sort = param;
	}

	public void setPage(int param) {
		page = param;
	}

	public void setResult(List<Movie> resultAux) {
		result = resultAux;
	}

	public List<Integer> getGenres() {
		return genres;
	}

	public List<Integer> getPeople() {
		return people;
	}

	public List<Movie> getResult() {
		return result;
	}

	public List<String> getSelected() {
		return selected;
	}

	public String getYear() {
		return year;
	}

	public String getLanguage() {
		return language;
	}

	public String getOrder() {
		return order;
	}

	public String getSort() {
		return sort;
	}

	public int getPage() {
		return page;
	}

	public void reset() {
		result.clear();
		genres.clear();
		people.clear();
		year = null;
		language = "es";
		order = MovieOrder.Popular;
		sort = MovieSort.PopularityDesc;
		page = 1;
	}

}
