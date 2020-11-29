package rechenoperationen;

public abstract class Helper {
	public static double round(double number, int places) {
		double factor = Math.pow(10, places);
		return ((double) Math.round( number * factor ) ) / factor;
	}
}
