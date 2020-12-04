package gui.panel.impulswellenlaenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rechenoperationen.Helper;
import rechenoperationen.ImpulsWellenlaenge;

public class RechenwegAction {
	private Rechenweg rechenweg;
	
	private double kristallgitter;
	private double laenge;
	private int k;
	
	private double[] beschleunigungsspanne_prepare;
	private double[] radius_prepare;
	private double[] lambda_prepare;
	private double[] impuls_prepare;
	private double[] geschwindigkeit_prepare;
	
	public RechenwegAction(Rechenweg rechenweg) {
		this.rechenweg = rechenweg;
	}
	
	public void change_rechenweg_values() {
		int selectindex = rechenweg.comboBox.getSelectedIndex();
		
		String beschleunigungsspanne 	= Helper.round(beschleunigungsspanne_prepare[selectindex], 4) + " V";
		String radius 					= Helper.round(radius_prepare[selectindex], 4) + " cm";
		String laenge 					= Helper.round(this.laenge, 4) + " cm";
		String kristallgitter			= Helper.round(this.kristallgitter, 4) + " * (10 ^ -10) m";
		String k						= Helper.round(this.k, 4) + "";
		
		double lambda_value 	= lambda_prepare[selectindex];
		String lambda 			= (lambda_value == ImpulsWellenlaenge.INFINITY ? "∞" : Helper.round(lambda_value, 4) + " * (10 ^ -11)") + " m";
		String lambda_rechenweg = "(2 * d * sin(0.5 * arcsin(r / l)) / k";
		
		double impuls_value 	= impuls_prepare[selectindex];
		String impuls			= (impuls_value == ImpulsWellenlaenge.INFINITY ? "∞" : Helper.round(impuls_value, 4) + " * (10 ^ -23)" ) + " m";
		String impuls_rechenweg = "sqrt(2 * m * e * U)";
		
		double geschwindigkeit_value 		= geschwindigkeit_prepare[selectindex];
		String geschwindigkeit				= (geschwindigkeit_value == ImpulsWellenlaenge.INFINITY ? "∞" : Helper.round(geschwindigkeit_value, 4)) + " m / s";
		String geschwindigkeit_rechenweg 	= "P / m";
		
		rechenweg.lblBeschleunigungsspanneValue.setText( beschleunigungsspanne );
		rechenweg.lblInterferenzradiusValue.setText( radius );
		rechenweg.lblLaengeValue.setText( laenge );
		rechenweg.lblKristallgitterValue.setText( kristallgitter );
		rechenweg.lblKValue.setText( k );
		
		rechenweg.lblLambdaValue.setText( lambda );
		rechenweg.lblLambdaRechenweg.setText( lambda_rechenweg );
		
		rechenweg.lblImpulsValue.setText( impuls );
		rechenweg.lblImpulsRechenweg.setText( impuls_rechenweg );
		
		rechenweg.lblGeschwindigkeitValue.setText( geschwindigkeit );
		rechenweg.lblGeschwindigkeitRechenweg.setText( geschwindigkeit_rechenweg );
		
		rechenweg.repaint();
	}
	
	public String[] prepare_data(JSONObject data_json) throws JSONException {
		// daten aus json laden
		JSONArray beschleunigungsspanne = data_json.getJSONArray("beschleunigungsspanne");
		JSONArray radius 				= data_json.getJSONArray("radius");
		JSONArray lambda 				= data_json.getJSONArray("lambda");
		JSONArray impuls 				= data_json.getJSONArray("impuls");
		JSONArray geschwindigkeit 		= data_json.getJSONArray("geschwindigkeit");
		
		kristallgitter	= data_json.getDouble("kristallgitter") * Math.pow(10, 10);
		laenge			= data_json.getDouble("laenge");
		k				= data_json.getInt("k");
		
		//  daten initialisieren
		int length 						= beschleunigungsspanne.length();
		String[] combobox_labels		= new String[length];
		beschleunigungsspanne_prepare 	= new double[length];
		radius_prepare 					= new double[length];
		lambda_prepare 					= new double[length];
		impuls_prepare 					= new double[length];
		geschwindigkeit_prepare			= new double[length];
		
		for(int i = 0; i < length; i++) {
			combobox_labels[i]					= "Rechenweg für Element " + (i + 1) + " / " + length;
			beschleunigungsspanne_prepare[i] 	= beschleunigungsspanne.getDouble(i);
			radius_prepare[i] 					= radius.getDouble(i);
			lambda_prepare[i] 					= lambda.getDouble(i) * Math.pow(10, 11);
			impuls_prepare[i] 					= impuls.getDouble(i) * Math.pow(10, 23);
			geschwindigkeit_prepare[i]			= geschwindigkeit.getDouble(i);
		}
		
		return combobox_labels;
	}
}
