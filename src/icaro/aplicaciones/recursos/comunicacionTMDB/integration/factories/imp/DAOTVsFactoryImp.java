package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOTVs;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOTVsFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp.DAOTVsImp;

public class DAOTVsFactoryImp extends DAOTVsFactory{


	@Override
	public DAOTVs getDaoTVs() {
		return new DAOTVsImp();
	}
}
