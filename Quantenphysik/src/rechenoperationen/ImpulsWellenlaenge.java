package rechenoperationen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImpulsWellenlaenge {
	public static final double INFINITY = -2.0101001043898;
	
	// Exponenten
	public static final int EXPONENT_10ER_POTENZ_LAMBDA 			= 11;
	public static final int EXPONENT_10ER_POTENZ_IMPULS 			= 23;
	public static final int EXPONENT_10ER_POTENZ_KRISTALLGITTER 	= 10;
	public static final int EXPONENT_10ER_POTENZ_ELEKTRONENMASSE 	= 31;
	public static final int EXPONENT_10ER_POTENZ_ELEMENTARLADUNG 	= 19;
	
	// Einheiten
	public static final String EINHEIT_BESCHLEUNIGUNGSSPANNE 		= "V";
	public static final String EINHEIT_INTERFERENZRADIUS 			= "cm";
	public static final String EINHEIT_KRISTALLGITTER 				= "m";
	public static final String EINHEIT_LAENGE 						= "cm";
	public static final String EINHEIT_LAMBDA 						= "m";
	public static final String EINHEIT_IMPULS 						= "Ns";
	public static final String EINHEIT_ELEKTRONENMASSE 				= "kg";
	public static final String EINHEIT_ELEMENTARLADUNG 				= "C";
	public static final String EINHEIT_ELEKTRONENGESCHWINDIGKEIT 	= "m/s";
	public static final String EINHEIT_PROPORTIONALITAETSKONSTANTE 	= "Js";
	
	// Formelzeichen
	public static final String FORMELZEICHEN_BESCHLEUNIGUNGSSPANNE 		= "U";
	public static final String FORMELZEICHEN_INTERFERENZRADIUS 			= "r";
	public static final String FORMELZEICHEN_KRISTALLGITTER 			= "d";
	public static final String FORMELZEICHEN_LAENGE 					= "l";
	public static final String FORMELZEICHEN_LAMBDA 					= "λ";
	public static final String FORMELZEICHEN_IMPULS 					= "P";
	public static final String FORMELZEICHEN_ELEKTRONENMASSE 			= "m";
	public static final String FORMELZEICHEN_ELEMENTARLADUNG 			= "e";
	public static final String FORMELZEICHEN_ELEKTRONENGESCHWINDIGKEIT 	= "v";
	
	// Formeln
	public static final String FORMEL_LAMBDA 			= "(2 * " + FORMELZEICHEN_KRISTALLGITTER + " * sin(0.5 * arcsin(" + FORMELZEICHEN_INTERFERENZRADIUS + " / " + FORMELZEICHEN_LAENGE + ")) / k";
	public static final String FORMEL_IMPULS 			= "sqrt(2 * " + FORMELZEICHEN_ELEKTRONENMASSE + " * " + FORMELZEICHEN_ELEMENTARLADUNG + " * " + FORMELZEICHEN_BESCHLEUNIGUNGSSPANNE + ")";
	public static final String FORMEL_GESCHWINDIGKEIT 	= FORMELZEICHEN_IMPULS + " / " + FORMELZEICHEN_ELEKTRONENMASSE;
	
	// Konstanten
	public static final double ELEKTRONENMASSE 	= 9.10938356 * Math.pow(10, -EXPONENT_10ER_POTENZ_ELEKTRONENMASSE);
	public static final double ELEMENTARLADUNG 	= 1.602 * Math.pow(10, -EXPONENT_10ER_POTENZ_ELEMENTARLADUNG);
	
	private double[] beschleunigungsspanne;
	private double[] interferenzradius;
	private double kristallgitter;
	private double laenge;
	private double k;
	
	// Konstruktor
	public ImpulsWellenlaenge(double[] beschleunigungsspanne, double[] interferenzradius, double kristallgitter, double laenge) throws Exception {
		this(beschleunigungsspanne, interferenzradius, kristallgitter, laenge, 1);
	}
	
	public ImpulsWellenlaenge(double[] beschleunigungsspanne, double[] interferenzradius, double kristallgitter, double laenge, int k) throws Exception {
		// Prüfen, ob alle Parameter valide sind
		if( beschleunigungsspanne == null || interferenzradius == null || 
			beschleunigungsspanne.length != interferenzradius.length ||
			kristallgitter <= 0 || 
			laenge <= 0 ||
			k <= 0) {
			// Angaben sind falsch
			
			// Exception werfen
			throw new Exception("Fehlerhafte Angaben");
		}
		// Parameter initialisieren
		this.beschleunigungsspanne 	= beschleunigungsspanne;
		this.interferenzradius 		= interferenzradius;
		this.kristallgitter 		= kristallgitter;
		this.laenge 				= laenge;
		this.k						= k;
	}
	
	// lambda -> in m
	private double[] get_lambda() {
		int length 		= beschleunigungsspanne.length;
		double[] res 	= new double[length];
		
		for(int i = 0; i < length; i++) {
			res[i] = get_lamda(interferenzradius[i]);
		}
		
		return res;
	}
	
	private double get_lamda(double r) {
		double theta = get_theta(r);
		return 2/ k * kristallgitter * Math.sin( theta );
	}
	
	private double get_theta(double r) {
		return 0.5 * Math.asin(r / laenge);
	}
	
	// impuls -> in Ns
	private double[] get_impuls() {
		int length 		= beschleunigungsspanne.length;
		double[] res	= new double[length];
		
		for(int i = 0; i < length; i++) {
			res[i] = get_impuls(beschleunigungsspanne[i]);
		}
		return res;
	}
	
	private double get_impuls(double u) {
		return Math.sqrt( 2 * ELEKTRONENMASSE * ELEMENTARLADUNG * u);
	}
	
	// geschwindigkeit -> in m / s
	private double[] get_geschwindigkeit(double[] impuls) {
		int length 		= impuls.length;
		double[] res	= new double[length];
		
		for(int i = 0; i < length; i++) {
			res[i] = get_geschwindigkeit(impuls[i]);
		}
		return res;
	}
	
	private double get_geschwindigkeit(double impuls) {
		return Math.sqrt( impuls / ELEKTRONENMASSE);
	}
	
	private double[] get_lambda_kehrwert(double [] lambda) {
		int length 					= lambda.length;
		double[] lambda_kehrwert	= new double[length];
		
		for(int i = 0; i < length; i++) {
			lambda_kehrwert[i] = 1 / lambda[i];
		}
		
		return lambda_kehrwert;
	}
	
	private double get_proportionalitaetskonstante(double[] lambda_kehrwert, double[] impuls) {
		int length 	= impuls.length;
		LinkedList<Double> steigungen = new LinkedList<Double>();
		
		for(int i = 0; i < (length - 1); i++) {
			double impuls_value_first 	= impuls[i] * Math.pow(10, EXPONENT_10ER_POTENZ_IMPULS);
			double lambda_value_first	= lambda_kehrwert[i] * Math.pow(10, -EXPONENT_10ER_POTENZ_LAMBDA);
			
			for(int y = (i + 1); y < length; y++) {
				double impuls_value_second 	= impuls[y] * Math.pow(10, EXPONENT_10ER_POTENZ_IMPULS);
				double lambda_value_second	= lambda_kehrwert[y] * Math.pow(10, -EXPONENT_10ER_POTENZ_LAMBDA);
				
				double delta_y = impuls_value_first - impuls_value_second;
				double delta_x = lambda_value_first - lambda_value_second;
				
				steigungen.add( delta_y / delta_x );
			}
		}
		
		double proportionalitaetskonstante = 0;
		for(double steigung : steigungen) {
			proportionalitaetskonstante+= steigung;
		}
		return proportionalitaetskonstante / steigungen.size();
	}
	
	// resultat -> save
	public void save_json(File file, JSONObject object) {
		try {
			file.createNewFile();
			
			FileWriter fw		= new FileWriter(file);
			BufferedWriter bw	= new BufferedWriter(fw);
			
			bw.write(object.toString());
			bw.flush();
			bw.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject get_json() throws JSONException {
		// objekt initialisieren
		JSONObject object_json = new JSONObject();
		
		// Beschleunigungsspanne
		try {
			object_json.put("beschleunigungsspanne", get_array_json( beschleunigungsspanne ));
		} catch(Exception e) {
			object_json.put("beschleunigungsspanne", get_array_json( new double[]{0} ));
		}
		
		// Radius
		try {
			object_json.put("radius", get_array_json( interferenzradius ));
		} catch(Exception e) {
			object_json.put("radius", get_array_json( new double[]{0} ));
		}
		
		// Kristallgitter
		try {
			object_json.put("kristallgitter", kristallgitter);
		} catch(Exception e) {
			object_json.put("kristallgitter", 0);
		}
		
		// Laenge
		try {
			object_json.put("laenge", laenge );
		} catch(Exception e) {
			object_json.put("laenge", 0 );
		}
		
		// k
		try {
			object_json.put("k", k );
		} catch(Exception e) {
			object_json.put("k", 0 );
		}
		
		// Lambda
		double[] lambda;
		try {
			lambda = get_lambda();
			object_json.put("lambda", get_array_json( lambda ) );
		} catch(Exception e) {
			lambda = new double[] {0};
			object_json.put("lambda", get_array_json( lambda ) );
		}
		
		// Lambda Kehrwert
		double[] lambda_kehrwert;
		try {
			lambda_kehrwert = get_lambda_kehrwert(lambda);
			object_json.put("1/lambda", get_array_json( lambda_kehrwert ) );
		} catch(Exception e) {
			lambda_kehrwert = new double[] {0};
			object_json.put("1/lambda", get_array_json( lambda_kehrwert ) );
		}
		
		// Impuls
		double[] impuls;
		try {
			impuls = get_impuls();
			object_json.put("impuls", get_array_json( impuls ));
		} catch(Exception e) {
			impuls = new double[] {0};
			object_json.put("impuls", get_array_json( impuls ));
		}
		
		// Proportionalitaetskonstante
		try {
			object_json.put("proportionalitaetskonstante", get_proportionalitaetskonstante(lambda_kehrwert, impuls) );
		} catch(Exception e) {
			object_json.put("proportionalitaetskonstante", 0);
		}
		
		// Geschwindigkeit
		try {
			object_json.put("geschwindigkeit", get_array_json( get_geschwindigkeit(impuls) ) );
		} catch(Exception e) {
			object_json.put("geschwindigkeit", 0);
		}
		
		return object_json;
	}
	
	private JSONArray get_array_json(double[] array) throws JSONException {
		JSONArray array_json = new JSONArray();
		
		for(double elem : array) {
			try {
				array_json.put(elem);
			} catch (Exception e){
				array_json.put(INFINITY);
			}
		}
		return array_json;
	}
	
	
}
