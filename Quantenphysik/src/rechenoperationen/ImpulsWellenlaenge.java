package rechenoperationen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImpulsWellenlaenge {
	public static final double elektronenmasse 				= 9.10938356 * Math.pow(10, -31); // in kg
	public static final double elektronengeschwindigkeit 	= 1.602 * Math.pow(10, -19); // in C
	
	private double[] beschleunigungsspanne; // in v
	private double[] radius_der_welle; // in cm
	private double kristallgitter;
	private double laenge; // in cm
	private double k;
	
	// Konstruktor
	public ImpulsWellenlaenge(double[] beschleunigungsspanne, double[] radius_der_welle, double kristallgitter, double laenge) throws Exception {
		this(beschleunigungsspanne, radius_der_welle, kristallgitter, laenge, 1);
	}
	
	public ImpulsWellenlaenge(double[] beschleunigungsspanne, double[] radius_der_welle, double kristallgitter, double laenge, int k) throws Exception {
		// Prüfen, ob alle Parameter valide sind
		if( beschleunigungsspanne == null || radius_der_welle == null || 
			beschleunigungsspanne.length != radius_der_welle.length ||
			kristallgitter <= 0 || 
			laenge <= 0 ||
			k <= 0) {
			// Angaben sind falsch
			
			// Exception werfen
			throw new Exception("Fehlerhafte Angaben");
		}
		
		// Parameter initialisieren
		this.beschleunigungsspanne 	= beschleunigungsspanne;
		this.radius_der_welle 		= radius_der_welle;
		this.kristallgitter 		= kristallgitter;
		this.laenge 				= laenge;
		this.k						= k;
	}
	
	// lambda -> in m
	private double[] get_lambda() {
		int length 		= beschleunigungsspanne.length;
		double[] res 	= new double[length];
		
		for(int i = 0; i < length; i++) {
			res[i] = get_lamda(radius_der_welle[i]);
		}
		return res;
	}
	
	private double get_lamda(double r) {
		double theta = get_theta(r);
		return ( 2 * kristallgitter * Math.sin( theta ) ) / k;
	}
	
	private double get_theta(double r) {
		return 0.5 * Math.asin(r / laenge);
	}
	
	// impuls -> in Ns
	private double[] get_impuls() {
		int length 		= beschleunigungsspanne.length;
		double[] res	= new double[length];
		
		for(int i = 0; i < 2; i++) {
			res[i] = get_impuls(beschleunigungsspanne[i]);
		}
		return res;
	}
	
	private double get_impuls(double u) {
		return Math.sqrt( 2 * elektronenmasse * elektronengeschwindigkeit * u);
	}
	
	// resultat -> json format
	public JSONObject get_diagramm_json() {
		// objekt initialisieren
		JSONObject object_json = new JSONObject();
		
		try {
			// Lambda
			object_json.put("lambda", get_array_json( get_lambda() ));
			
			// Impuls
			object_json.put("impuls", get_array_json( get_impuls() ));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object_json;
	}
	
	public JSONObject get_rechenweg_json() {
		// objekt initialisieren
		JSONObject object_json = new JSONObject();
		
		try {
			// Beschleunigungsspanne
			object_json.put("beschleunigungsspanne", get_array_json( beschleunigungsspanne ));
			
			// Radius
			object_json.put("radius", get_array_json( radius_der_welle ));
			
			// Kristallgitter
			object_json.put("kristallgitter", kristallgitter);
			
			// Laenge
			object_json.put("laenge", laenge);
			
			// k
			object_json.put("k", k);
			
			// Lambda
			object_json.put("lambda", get_array_json( get_lambda() ));
			
			// Impuls
			object_json.put("impuls", get_array_json( get_impuls() ));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object_json;
	}
	
	private JSONArray get_array_json(double[] array) throws JSONException {
		JSONArray array_json = new JSONArray();
		
		for(double elem : array) {
			array_json.put(elem);
		}
		return array_json;
	}
	
	
}
