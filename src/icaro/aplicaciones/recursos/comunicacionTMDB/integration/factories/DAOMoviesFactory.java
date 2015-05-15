package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOMovies;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp.DAOMoviesFactoryImp;

public abstract class DAOMoviesFactory {

	private static DAOMoviesFactory instance;

	public static DAOMoviesFactory getInstance() {
		if (instance == null)
			instance = new DAOMoviesFactoryImp();
		return instance;
	}

	public abstract DAOMovies getDaoMovies();

}
