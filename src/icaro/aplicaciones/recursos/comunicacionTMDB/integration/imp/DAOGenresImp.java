package icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOGenres;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Genre;
import icaro.aplicaciones.recursos.comunicacionTMDB.util.GenresParser;

public class DAOGenresImp implements DAOGenres {

	private static final String API_KEY = "7d3938edb64159d9e153fe61c4438a70";
	
	@Override
	public List<Genre> getMovieGenres(String language) {
		return getGenres("movie", language);
	}
	
	@Override
	public List<Genre> getTVGenres(String language) {
		return getGenres("tv", language);
	}

	private List<Genre> getGenres(String show, String language) {
		List<Genre> genres = null;
		HttpClient hc = new DefaultHttpClient();
		
		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/genre/"+show+"/list?language="+language+"&api_key=");
		sb.append(API_KEY);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			genres = GenresParser.parseGenres(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return genres;
	}
}
