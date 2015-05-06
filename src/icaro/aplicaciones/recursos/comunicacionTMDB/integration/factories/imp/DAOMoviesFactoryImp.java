package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOMovies;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOMoviesFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp.DAOMoviesImp;

public class DAOMoviesFactoryImp extends DAOMoviesFactory{


	@Override
	public DAOMovies getDaoMovies() {
		return new DAOMoviesImp();
	}
}
