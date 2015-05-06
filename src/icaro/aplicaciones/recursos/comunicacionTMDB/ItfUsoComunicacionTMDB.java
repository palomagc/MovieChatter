package icaro.aplicaciones.recursos.comunicacionTMDB;

import java.util.Date;
import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.TV;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoComunicacionTMDB extends ItfUsoRecursoSimple {

	public List<Genre> getMovieGenresList(String language) throws Exception;
	//public List<String> getMovieGenresNamesList(String language);
	public List<Genre> getTVGenresList(String language) throws Exception;
	//public List<String> getTVGenresNamesList(String language);
	

	Person getPerson(int PersonId) throws Exception;
	Person getPerson(Person person) throws Exception;
	List<Person> searchPeople(String query, int page) throws Exception;
	List<Person> getPopularPeople(int page) throws Exception;
	Person getLatestPerson() throws Exception;


	Movie getMovie(int movieId, String language) throws Exception;
	Movie getMovie(Movie movie, String language) throws Exception;
	List<Movie> getMovieList(String order, String language, int page) throws Exception;

	List<Movie> getMoviesByGenre(Genre genre, String sort, String language, int page) throws Exception;
	List<Movie> getMoviesByPerson(Person person, String sort, String language, int page) throws Exception;
	List<Movie> getMoviesByYear(String year, String sort, String language, int page) throws Exception;
	
	List<Movie> searchMovies(String query, String year, String language, int page) throws Exception;
	List<Movie> discoverMovies(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Genre> genres, List<Person> people, String year,
			String language, int page) throws Exception;


	TV getTV(int TVId, String language) throws Exception;
	TV getTV(TV tv, String language) throws Exception;
	List<TV> getTVList(String order, String language, int page) throws Exception;
	List<TV> getTVsByGenre(Genre genre, String sort, String language, int page) throws Exception;
	List<TV> getTVsByYear(String year, String sort, String language, int page) throws Exception;

	List<TV> searchTVs(String query, String year, String language, int page) throws Exception;
	List<TV> discoverTVs(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Genre> genres, String year, String language, int page) throws Exception;
}
