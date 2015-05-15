package icaro.aplicaciones.recursos.comunicacionTMDB.util;

import java.util.Date;

public class DatesUtil {

	private static final long milisegundosDeUnDia = 1000l * 60l * 60l * 24l;

	public static boolean DistanceFromNowIsMoreThan(Date date1, int days) {
		return DistanceIsMoreThan(new Date(), date1, days);
	}

	public static boolean DistanceIsMoreThan(Date date1, Date date2, int days) {
		return date1.getTime() - date2.getTime() > milisegundosDeUnDia * 30l;
	}
}
