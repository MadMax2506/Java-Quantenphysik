package rechenoperationen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.App;

public class ImpulsWellenlaenge {
	public static final double INFINITY = -2.0101001043898;
	
	public static final double elektronenmasse 	= 9.10938356 * Math.pow(10, -31); // in kg
	public static final double elementarladung 	= 1.602 * Math.pow(10, -19); // in C
	
	private double[] beschleunigungsspanne; // in v
	private double[] interferenzradius; // in cm
	private double kristallgitter;
	private double laenge; // in cm
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
			System.out.println(res[i]);
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
		return Math.sqrt( 2 * elektronenmasse * elementarladung * u);
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
		return Math.sqrt( impuls / elektronenmasse);
	}
	
	// resultat -> save
	public void save_json(String filename) {
		JSONObject object = this.get_rechenweg_json();
		
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
			object_json.put("radius", get_array_json( interferenzradius ));
			
			// Kristallgitter
			object_json.put("kristallgitter", kristallgitter);
			
			// Laenge
			object_json.put("laenge", laenge);
			
			// k
			object_json.put("k", k);
			
			// Lambda
			object_json.put("lambda", get_array_json( get_lambda() ));
			
			// Impuls
			double[] impuls = get_impuls();
			object_json.put("impuls", get_array_json( impuls ));
			
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
