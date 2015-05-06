package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOGenres;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOGenresFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp.DAOGenresImp;

public class DAOGenresFactoryImp extends DAOGenresFactory{

	@Override
	public DAOGenres getDaoGenres() {
		return new DAOGenresImp();
	}
}
