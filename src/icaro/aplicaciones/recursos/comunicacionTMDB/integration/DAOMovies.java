package icaro.aplicaciones.recursos.comunicacionTMDB.integration;

import java.util.Date;
import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;

public interface DAOMovies {

	Movie getMovie(int movieId, String language);
	
	List<Movie> getMovieList(String order, String language, int page);

	List<Movie> searchMovies(String query, String year, String language, int page);
	
	List<Movie> discoverMovies(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Genre> genres, List<Person> people, String year,
			String language, int page);
}
