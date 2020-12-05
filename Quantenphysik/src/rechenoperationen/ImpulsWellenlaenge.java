package rechenoperationen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.App;

public class ImpulsWellenlaenge {
	public static final double INFINITY = -2.0101001043898;
	
	public static final int EXPONENT_10ER_POTENZ_LAMBDA 			= 11;
	public static final int EXPONENT_10ER_POTENZ_IMPULS 			= 23;
	public static final int EXPONENT_10ER_POTENZ_KRISTALLGITTER 	= 10;
	public static final int EXPONENT_10ER_POTENZ_ELEKTRONENMASSE 	= 31;
	public static final int EXPONENT_10ER_POTENZ_ELEMENTARLADUNG 	= 19;
	
	public static final String EINHEIT_BESCHLEUNIGUNGSSPANNE = "V";
	public static final String EINHEIT_INTERFERENZRADIUS = "cm";
	public static final String EINHEIT_KRISTALLGITTER = "m";
	public static final String EINHEIT_LAENGE = "cm";
	public static final String EINHEIT_LAMBDA = "m";
	public static final String EINHEIT_IMPULS = "Ns";
	public static final String EINHEIT_ELEKTRONENMASSE = "kg";
	public static final String EINHEIT_ELEMENTARLADUNG = "C";
	public static final String EINHEIT_ELEKTRONENGESCHWINDIGKEIT = "m / s";
	
	public static final double ELEKTRONENMASSE 	= 9.10938356 * Math.pow(10, -EXPONENT_10ER_POTENZ_ELEKTRONENMASSE); // in kg
	public static final double ELEMENTARLADUNG 	= 1.602 * Math.pow(10, -EXPONENT_10ER_POTENZ_ELEMENTARLADUNG); // in C
	
	private double[] beschleunigungsspanne; // in v
	private double[] interferenzradius; // in cm
	private double kristallgitter; // in m
	private double laenge; // in cm
	private double k;
	
	public static void main(String[] args) {
		try {
			ImpulsWellenlaenge iw = new ImpulsWellenlaenge(new double[] {2200, 3500}, new double[] {1.58, 1.35}, 2.13 * Math.pow(10, -EXPONENT_10ER_POTENZ_KRISTALLGITTER), 13);
			iw.get_json();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	private double get_proportionalitaetskonstante(double[] lambda, double[] impuls) {
		int length 	= impuls.length;
		LinkedList<Double> steigungen = new LinkedList<Double>();
		
		for(int i = 0; i < (length - 1); i++) {
			double impuls_value_first 	= impuls[i] * Math.pow(10, EXPONENT_10ER_POTENZ_IMPULS);
			double lambda_value_first	= 1 / (lambda[i] * Math.pow(10, EXPONENT_10ER_POTENZ_LAMBDA));
			
			for(int y = (i + 1); y < length; y++) {
				double impuls_value_second 	= impuls[y] * Math.pow(10, EXPONENT_10ER_POTENZ_IMPULS);
				double lambda_value_second	= 1 / (lambda[y] * Math.pow(10, EXPONENT_10ER_POTENZ_LAMBDA));
				
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
	public void save_json(String filename) {
		JSONObject object = this.get_json();
		
		try {
			File f = new File( App.user_folder.toString() + "/" + filename);
			f.createNewFile();
			
			FileWriter fw		= new FileWriter(f);
			BufferedWriter bw	= new BufferedWriter(fw);
			
			bw.write(object.toString());
			bw.flush();
			bw.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject get_json() {
		// objekt initialisieren
		JSONObject object_json = new JSONObject();
		
		try {
			// Beschleunigungsspanne
			object_json.put("beschleunigungsspanne", get_array_json( beschleunigungsspanne ));
			
			// Radius
			object_json.put("radius", get_array_json( interferenzradius ));
			
			// Kristallgitter
			object_json.put("kristallgitter", kristallgitter);
			
			// Laenge
			object_json.put("laenge", laenge);
			
			// k
			object_json.put("k", k);
			
			// Lambda
			double[] lambda = get_lambda();
			object_json.put("lambda", get_array_json( lambda ));
			
			// Impuls
			double[] impuls = get_impuls();
			object_json.put("impuls", get_array_json( impuls ));
			
			// Proportionalitaetskonstante
			object_json.put("proportionalitaetskonstante", get_proportionalitaetskonstante(lambda, impuls) );
			
			// Geschwindigkeit
			object_json.put("geschwindigkeit", get_array_json( get_geschwindigkeit(impuls) ));
			
		} catch (Exception e) {
			e.printStackTrace();
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
