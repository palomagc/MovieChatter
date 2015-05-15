package icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.imp;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOPeople;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.factories.DAOPeopleFactory;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp.DAOPeopleImp;

public class DAOPeopleFactoryImp extends DAOPeopleFactory {

	@Override
	public DAOPeople getDaoPeople() {
		return new DAOPeopleImp();
	}
}
