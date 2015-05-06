package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOTVs;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp.DAOTVsFactoryImp;


public abstract class DAOTVsFactory {

	private static DAOTVsFactory instance;
	public static DAOTVsFactory getInstance(){
		if(instance == null)
			instance = new DAOTVsFactoryImp();
		return instance;
	}
	
	public abstract DAOTVs getDaoTVs();
	
}
