package icaro.aplicaciones.recursos.comunicacionTMDB.integration.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import icaro.aplicaciones.recursos.comunicacionTMDB.orders.TVOrder;
import icaro.aplicaciones.recursos.comunicacionTMDB.orders.TVSort;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOTVs;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.TV;
import icaro.aplicaciones.recursos.comunicacionTMDB.util.TVsParser;

public class DAOTVsImp implements DAOTVs {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",
			Locale.ENGLISH);

	private static final String API_KEY = "7d3938edb64159d9e153fe61c4438a70";

	@Override
	public TV getTV(int TVId, String language) {
		TV tv = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/tv/");
		sb.append(TVId).append("?api_key=").append(API_KEY);
		if (language != null)
			sb.append("&language=").append(language);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			tv = TVsParser.parseTV(hr.getEntity().getContent());
			tv.setCompleted(true);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tv;
	}

	@Override
	public List<TV> getTVList(String order, String language, int page) {
		List<TV> tvs = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder();
		if (order.equals(TVOrder.Latest)) {
			sb.append("http://api.themoviedb.org/3/tv/latest");
		} else if (order.equals(TVOrder.OnTheAir)) {
			sb.append("http://api.themoviedb.org/3/tv/on_the_air");
		} else if (order.equals(TVOrder.AiringToday)) {
			sb.append("http://api.themoviedb.org/3/tv/airing_today");
		} else if (order.equals(TVOrder.TopRated)) {
			sb.append("http://api.themoviedb.org/3/tv/top_rated");
		} else if (order.equals(TVOrder.Popular)) {
			sb.append("http://api.themoviedb.org/3/tv/popular");
		} else {
			return null;
		}

		sb.append("?api_key=").append(API_KEY);
		if (language != null)
			sb.append("&language=").append(language);
		// sb.append("&page=").append(page);
		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			tvs = TVsParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tvs;
	}

	@Override
	public List<TV> searchTVs(String query, String year, String language, int page) {
		List<TV> tvs = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/search/tv");
		sb.append("?api_key=").append(API_KEY);
		sb.append("&query=").append(query);

		if (language != null)
			sb.append("&language=").append(language);
		if (year != null)
			sb.append("&year=").append(year);
		// sb.append("&page=").append(page);
		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			tvs = TVsParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tvs;
	}

	@Override
	public List<TV> discoverTVs(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Integer> genres, String year, String language, int page) {
		List<TV> tvs = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/discover/tv");
		sb.append("?api_key=").append(API_KEY);

		if (language != null)
			sb.append("&language=").append(language);
		if (page != 1)
			sb.append("&page=").append(page);
		if (after != null)
			sb.append("&first_air_date.gte=").append(dateFormatter.format(after));
		if (before != null)
			sb.append("&first_air_date.lte=").append(dateFormatter.format(before));
		if (sort == null)
			sort = TVSort.PopularityDesc;
		sb.append("&sort_by=").append(sort);
		if (minAverage != null)
			sb.append("&vote_average.gte=").append(minAverage);
		if (minCount != null)
			sb.append("&vote_count.gte=").append(minCount);

		if (genres != null) {
			sb.append("&with_genres=");
			for (int i = 0; i < genres.size(); i++) {
				sb.append(genres.get(i));
				if (i < genres.size() - 1)
					sb.append(","); // ","=AND "|"=OR
			}
		}
		if (year != null)
			sb.append("&first_air_date_year=").append(year);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			tvs = TVsParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tvs;
	}
}
