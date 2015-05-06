package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOPeople;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp.DAOPeopleFactoryImp;


public abstract class DAOPeopleFactory {

	private static DAOPeopleFactory instance;
	public static DAOPeopleFactory getInstance(){
		if(instance == null)
			instance = new DAOPeopleFactoryImp();
		return instance;
	}
	
	public abstract DAOPeople getDaoPeople();
	
}
