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

import icaro.aplicaciones.recursos.comunicacionTMDB.orders.MovieOrder;
import icaro.aplicaciones.recursos.comunicacionTMDB.orders.MovieSort;
import icaro.aplicaciones.recursos.comunicacionTMDB.integration.DAOMovies;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.comunicacionTMDB.util.MoviesParser;

public class DAOMoviesImp implements DAOMovies {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",
			Locale.ENGLISH);

	private static final String API_KEY = "7d3938edb64159d9e153fe61c4438a70";

	@Override
	public Movie getMovie(int movieId, String language) {
		Movie movie = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/movie/");
		sb.append(movieId).append("?api_key=").append(API_KEY);
		if (language != null)
			sb.append("&language=").append(language);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			movie = MoviesParser.parseMovie(hr.getEntity().getContent());
			movie.setCompleted(true);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movie;
	}

	@Override
	public List<Movie> getMovieList(String order, String language, int page) {
		List<Movie> movies = null;

		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder();
		if (order.equals(MovieOrder.Latest)) {
			sb.append("http://api.themoviedb.org/3/movie/latest");
		} else if (order.equals(MovieOrder.Upcoming)) {
			sb.append("http://api.themoviedb.org/3/movie/upcoming");
		} else if (order.equals(MovieOrder.NowPlaying)) {
			sb.append("http://api.themoviedb.org/3/movie/now_playing");
		} else if (order.equals(MovieOrder.Popular)) {
			sb.append("http://api.themoviedb.org/3/movie/popular");
		} else if (order.equals(MovieOrder.TopRated)) {
			sb.append("http://api.themoviedb.org/3/movie/top_rated");
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
			movies = MoviesParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public List<Movie> searchMovies(String query, String year, String language, int page) {
		List<Movie> movies = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/search/movie");
		sb.append("?api_key=").append(API_KEY);
		sb.append("&query=").append(query);
		if (language != null)
			sb.append("&language=").append(language);
		if (year != null)
			sb.append("&year=").append(year);
		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			movies = MoviesParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public List<Movie> discoverMovies(Date after, Date before, String sort, Float minAverage,
			Integer minCount, List<Integer> genres, List<Integer> people, String year,
			String language, int page) {
		List<Movie> movies = null;
		HttpClient hc = new DefaultHttpClient();

		// Creating API query
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/discover/movie");
		sb.append("?api_key=").append(API_KEY);

		if (language != null)
			sb.append("&language=").append(language);
		if (page != 1)
			sb.append("&page=").append(page);
		if (after != null)
			sb.append("&release_date.gte=").append(dateFormatter.format(after));
		if (before != null)
			sb.append("&release_date.lte=").append(dateFormatter.format(before));
		if (sort == null)
			sort = MovieSort.PopularityDesc;
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
		if (people != null) {
			sb.append("&with_people=");
			for (int i = 0; i < people.size(); i++) {
				sb.append(people.get(i));
				if (i < people.size() - 1)
					sb.append(","); // ","=AND "|"=OR
			}
		}
		if (year != null)
			sb.append("&year=").append(year);

		// Connecting through httpget
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			// Parsing results
			movies = MoviesParser.parseList(hr.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movies;
	}
}
