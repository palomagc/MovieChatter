package icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOPeople;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Person;
import icaro.aplicaciones.recursos.comunicacionTMDB.util.PeopleParser;

public class DAOPeopleImp implements DAOPeople {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",
			Locale.ENGLISH);

	private static final String API_KEY = "7d3938edb64159d9e153fe61c4438a70";

	@Override
	public List<Person> getPopularPeople(int page) {
		List<Person> people = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder();
		sb.append("http://api.themoviedb.org/3/person/popular");
		sb.append("?api_key=").append(API_KEY);
		sb.append("&page=").append(page);
		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			people = PeopleParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public List<Person> searchPeople(String consulta, int page) {
		List<Person> people = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/search/person");
		sb.append("?api_key=").append(API_KEY);
		sb.append("&query=").append(consulta);
		sb.append("&page=").append(page);
		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			people = PeopleParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public Person getPerson(int personId) {
		Person person = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/person/");
		sb.append(personId).append("?api_key=").append(API_KEY);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			person = PeopleParser.parsePerson(hr.getEntity().getContent());
			person.setCompleted(true);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public Person getLatestPerson() {
		Person person = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder();
		sb.append("http://api.themoviedb.org/3/person/latest");
		sb.append("?api_key=").append(API_KEY);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			person = PeopleParser.parsePerson(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return person;
	}
}
