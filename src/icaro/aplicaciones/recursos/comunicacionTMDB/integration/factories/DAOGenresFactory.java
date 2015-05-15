package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOGenres;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp.DAOGenresFactoryImp;

public abstract class DAOGenresFactory {

	private static DAOGenresFactory instance;

	public static DAOGenresFactory getInstance() {
		if (instance == null)
			instance = new DAOGenresFactoryImp();
		return instance;
	}

	public abstract DAOGenres getDaoGenres();

}
