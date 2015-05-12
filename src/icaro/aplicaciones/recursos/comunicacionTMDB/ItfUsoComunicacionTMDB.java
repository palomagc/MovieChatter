package icaro.aplicaciones.recursos.comunicacionTMDB;

import java.util.Date;
import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.TV;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoComunicacionTMDB extends ItfUsoRecursoSimple {

	List<Genre> getMovieGenresList(String language) throws Exception;
	//List<String> getMovieGenresNamesList(String language);
	List<Genre> getTVGenresList(String language) throws Exception;
	//List<String> getTVGenresNamesList(String language);
	

	Person getPerson(int PersonId) throws Exception;
	Person getPerson(Person person) throws Exception;
	Integer searchPerson(String query) throws Exception;
	List<Person> searchPeople(String query, int page) throws Exception;
	List<Person> getPopularPeople(int page) throws Exception;
	Person getLatestPerson() throws Exception;


	Movie getMovie(int movieId, String language) throws Exception;
	Movie getMovie(Movie movie, String language) throws Exception;
	List<Movie> getMovieList(String order, String language, int page) throws Exception;

	List<Movie> getMoviesByGenre(Integer genre, String sort, String language, int page) throws Exception;
	List<Movie> getMoviesByPerson(Integer person, String sort, String language, int page) throws Exception;
	List<Movie> getMoviesByYear(String year, String sort, String language, int page) throws Exception;
	
	List<Movie> searchMovies(String query, String year, String language, int page) throws Exception;
	List<Movie> discoverMovies(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Integer> genres, List<Integer> people, String year,
			String language, int page) throws Exception;


	TV getTV(int TVId, String language) throws Exception;
	TV getTV(TV tv, String language) throws Exception;
	List<TV> getTVList(String order, String language, int page) throws Exception;
	List<TV> getTVsByGenre(Integer genre, String sort, String language, int page) throws Exception;
	List<TV> getTVsByYear(String year, String sort, String language, int page) throws Exception;

	List<TV> searchTVs(String query, String year, String language, int page) throws Exception;
	List<TV> discoverTVs(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Integer> genres, String year, String language, int page) throws Exception;
}
