package rechenoperationen;

import java.util.LinkedList;

public abstract class Helper {
	public static double round(double number, int places) {
		double factor = Math.pow(10, places);
		return ((double) Math.round( number * factor ) ) / factor;
	}
	
	public static double[] to_array(LinkedList<Double> list) {
		int length 		= list.size();
		double[] array 	= new double[length];
		
		for(int i = 0; i < length; i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
}
