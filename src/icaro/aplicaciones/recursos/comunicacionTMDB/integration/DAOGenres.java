package icaro.aplicaciones.recursos.comunicacionTMDB.integration;

import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;

public interface DAOGenres {

	List<Genre> getMovieGenres(String language);

	List<Genre> getTVGenres(String language);
}
