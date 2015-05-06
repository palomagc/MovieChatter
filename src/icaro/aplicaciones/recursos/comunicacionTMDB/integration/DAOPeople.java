package icaro.aplicaciones.recursos.comunicacionTMDB.integration;

import java.util.List;

import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;

public interface DAOPeople {

	List<Person> getPopularPeople(int page);

	List<Person> searchPeople(String consulta, int page);

	Person getPerson(int PersonId);
	
	Person getLatestPerson();
}
