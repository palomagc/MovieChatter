package icaro.aplicaciones.recursos.comunicacionTMDB;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOGenres;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOMovies;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOPeople;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOTVs;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOGenresFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOMoviesFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOPeopleFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOTVsFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.TV;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

public class ClaseGeneradoraComunicacionTMDB extends ImplRecursoSimple implements
		ItfUsoComunicacionTMDB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClaseGeneradoraComunicacionTMDB(String idRecurso) throws RemoteException {
		super(idRecurso);
	}

	@Override
	public List<Genre> getMovieGenresList(String language) {
		if (language == null)
			language = "es";
		DAOGenresFactory factoria = DAOGenresFactory.getInstance();
		DAOGenres dao = factoria.getDaoGenres();
		return dao.getMovieGenres(language);
	}

	@Override
	public List<Genre> getTVGenresList(String language) {
		if (language == null)
			language = "es";
		DAOGenresFactory factoria = DAOGenresFactory.getInstance();
		DAOGenres dao = factoria.getDaoGenres();
		return dao.getTVGenres(language);
	}

	/* Person */

	@Override
	public Person getPerson(Person person) {
		return getPerson(person.getId());
	}

	@Override
	public Person getPerson(int personId) {
		DAOPeopleFactory fPeople = DAOPeopleFactory.getInstance();
		DAOPeople dao = fPeople.getDaoPeople();
		return dao.getPerson(personId);
	}

	/* Rest Methods */

	@Override
	public Integer searchPerson(String query) {
		DAOPeopleFactory fPeople = DAOPeopleFactory.getInstance();
		query = query.replace(" ", "+");
		DAOPeople dao = fPeople.getDaoPeople();
		return dao.searchPeople(query, 1).get(0).getId();
	}

	@Override
	public List<Person> searchPeople(String query, int page) {
		DAOPeopleFactory fPeople = DAOPeopleFactory.getInstance();
		query = query.replace(" ", "+");
		DAOPeople dao = fPeople.getDaoPeople();
		return dao.searchPeople(query, page);
	}

	@Override
	public List<Person> getPopularPeople(int page) {
		DAOPeopleFactory fPeople = DAOPeopleFactory.getInstance();
		DAOPeople dao = fPeople.getDaoPeople();
		return dao.getPopularPeople(page);
	}

	@Override
	public Person getLatestPerson() {
		DAOPeopleFactory fPeople = DAOPeopleFactory.getInstance();
		DAOPeople dao = fPeople.getDaoPeople();
		return dao.getLatestPerson();
	}

	/* Movie */

	@Override
	public Movie getMovie(Movie movie, String language) {
		return getMovie(movie.getId(), language);
	}

	@Override
	public Movie getMovie(int movieId, String language) {
		DAOMoviesFactory fMovies = DAOMoviesFactory.getInstance();
		DAOMovies dao = fMovies.getDaoMovies();
		return dao.getMovie(movieId, language);
	}

	@Override
	public List<Movie> getMovieList(String order, String language, int page) {
		DAOMoviesFactory fMovies = DAOMoviesFactory.getInstance();
		DAOMovies dao = fMovies.getDaoMovies();
		return dao.getMovieList(order, language, page);
	}

	/* Movies By */

	@Override
	public List<Movie> getMoviesByGenre(Integer genre, String sort, String language, int page) {
		List<Integer> genres = new ArrayList<Integer>();
		genres.add(genre);
		return discoverMovies(null, null, sort, null, null, genres, null, null, language, page);
	}

	@Override
	public List<Movie> getMoviesByPerson(Integer person, String sort, String language, int page) {
		List<Integer> people = new ArrayList<Integer>();
		people.add(person);
		return discoverMovies(null, null, sort, null, null, null, people, null, language, page);
	}

	@Override
	public List<Movie> getMoviesByYear(String year, String sort, String language, int page) {
		return discoverMovies(null, null, sort, null, null, null, null, year, language, page);
	}

	/* Rest Methods */

	@Override
	public List<Movie> searchMovies(String query, String year, String language, int page) {
		DAOMoviesFactory fMovies = DAOMoviesFactory.getInstance();
		query = query.replace(" ", "+");
		DAOMovies dao = fMovies.getDaoMovies();
		return dao.searchMovies(query, year, language, page);
	}

	@Override
	public List<Movie> discoverMovies(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Integer> genres, List<Integer> people, String year,
			String language, int page) {

		DAOMoviesFactory fMovies = DAOMoviesFactory.getInstance();
		DAOMovies dao = fMovies.getDaoMovies();
		return dao.discoverMovies(after, before, sort, minAverage, minCount, genres, people, year,
				language, page);
	}

	/* TV */

	@Override
	public TV getTV(TV tv, String language) {
		return getTV(tv.getId(), language);
	}

	@Override
	public TV getTV(int tvId, String language) {
		DAOTVsFactory fTVs = DAOTVsFactory.getInstance();
		DAOTVs dao = fTVs.getDaoTVs();
		return dao.getTV(tvId, language);
	}

	@Override
	public List<TV> getTVList(String order, String language, int page) {
		DAOTVsFactory fTVs = DAOTVsFactory.getInstance();
		DAOTVs dao = fTVs.getDaoTVs();
		return dao.getTVList(order, language, page);
	}

	/* TVs By */

	@Override
	public List<TV> getTVsByGenre(Integer genre, String sort, String language, int page) {
		List<Integer> genres = new ArrayList<Integer>();
		genres.add(genre);
		return discoverTVs(null, null, sort, null, null, genres, null, language, page);
	}

	@Override
	public List<TV> getTVsByYear(String year, String sort, String language, int page) {
		return discoverTVs(null, null, sort, null, null, null, year, language, page);
	}

	/* Rest Methods */

	@Override
	public List<TV> searchTVs(String query, String year, String language, int page) {
		DAOTVsFactory fTVs = DAOTVsFactory.getInstance();
		query = query.replace(" ", "+");
		DAOTVs dao = fTVs.getDaoTVs();
		return dao.searchTVs(query, year, language, page);
	}

	@Override
	public List<TV> discoverTVs(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Integer> genres, String year, String language, int page) {
		DAOTVsFactory fTVs = DAOTVsFactory.getInstance();
		DAOTVs dao = fTVs.getDaoTVs();
		return dao.discoverTVs(after, before, sort, minAverage, minCount, genres, year, language,
				page);
	}
}
