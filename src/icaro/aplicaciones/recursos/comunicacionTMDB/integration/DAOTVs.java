package icaro.aplicaciones.recursos.comunicacionTMDB.integration;

import java.util.Date;
import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.TV;

public interface DAOTVs {

	TV getTV(int TVId, String language);
	
	List<TV> getTVList(String order, String language, int page);

	List<TV> searchTVs(String query, String year, String language, int page);
	
	List<TV> discoverTVs(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Genre> genres, String year, String language, int page);
}
