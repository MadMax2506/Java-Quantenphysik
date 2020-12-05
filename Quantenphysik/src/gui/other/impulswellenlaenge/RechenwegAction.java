package gui.other.impulswellenlaenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rechenoperationen.Helper;
import rechenoperationen.ImpulsWellenlaenge;

public class RechenwegAction {
	private RechenwegGUI rechenweg;
	
	private String[] combobox_labels;
	
	private double kristallgitter;
	private double laenge;
	private int k;
	
	private double[] beschleunigungsspanne_prepare;
	private double[] radius_prepare;
	private double[] lambda_prepare;
	private double[] impuls_prepare;
	private double[] geschwindigkeit_prepare;
	
	public RechenwegAction(RechenwegGUI rechenweg) {
		this.rechenweg = rechenweg;
	}
	
	public String[] get_combobox_labels() {
		return combobox_labels;
	}
	
	public void set_statische_rechenweg_werte() {
		String laenge 			= Helper.round(this.laenge, 4) + " " + ImpulsWellenlaenge.EINHEIT_LAENGE;
		String kristallgitter	= Helper.round(this.kristallgitter, 4) + " * (10 ^ -" + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_KRISTALLGITTER + ") " + ImpulsWellenlaenge.EINHEIT_KRISTALLGITTER;
		String k				= Helper.round(this.k, 4) + "";
		
		String elementarladung = Helper.round(ImpulsWellenlaenge.ELEMENTARLADUNG * Math.pow(10, ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_ELEMENTARLADUNG), 4)  + " 10 ^ -" + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_ELEMENTARLADUNG + " " + ImpulsWellenlaenge.EINHEIT_ELEMENTARLADUNG;
		String elektronenmasse = Helper.round(ImpulsWellenlaenge.ELEKTRONENMASSE * Math.pow(10, ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_ELEKTRONENMASSE), 4)  + " 10 ^ -" + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_ELEKTRONENMASSE + " " + ImpulsWellenlaenge.EINHEIT_ELEKTRONENMASSE;
		
		rechenweg.lblLaengeValue.setText( laenge );
		rechenweg.lblKristallgitterValue.setText( kristallgitter );
		rechenweg.lblKValue.setText( k );
		
		rechenweg.lblElementarladungValue.setText( elementarladung );
		rechenweg.lblElektronenmasseValue.setText( elektronenmasse );

		rechenweg.lblLambdaRechenweg.setText( ImpulsWellenlaenge.FORMEL_LAMBDA );
		rechenweg.lblImpulsRechenweg.setText( ImpulsWellenlaenge.FORMEL_IMPULS );
		rechenweg.lblGeschwindigkeitRechenweg.setText( ImpulsWellenlaenge.FORMEL_GESCHWINDIGKEIT );
	}
	
	public void set_dynamische_rechenweg_werte() {
		int selectindex = rechenweg.comboBox.getSelectedIndex();
		
		String beschleunigungsspanne = Helper.round(beschleunigungsspanne_prepare[selectindex], 4) + " " + ImpulsWellenlaenge.EINHEIT_BESCHLEUNIGUNGSSPANNE;
		String radius 				= Helper.round(radius_prepare[selectindex], 4) + " " + ImpulsWellenlaenge.EINHEIT_INTERFERENZRADIUS;
		
		double lambda_value = lambda_prepare[selectindex];
		String lambda 		= (lambda_value == ImpulsWellenlaenge.INFINITY ? "∞" : Helper.round(lambda_value, 4) + " * (10 ^ -" + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_LAMBDA + ")") + " " + ImpulsWellenlaenge.EINHEIT_LAMBDA;
		
		double impuls_value = impuls_prepare[selectindex];
		String impuls		= (impuls_value == ImpulsWellenlaenge.INFINITY ? "∞" : Helper.round(impuls_value, 4) + " * (10 ^ -" + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_IMPULS + ")" ) + " " + ImpulsWellenlaenge.EINHEIT_IMPULS;
		
		double geschwindigkeit_value	= geschwindigkeit_prepare[selectindex];
		String geschwindigkeit			= (geschwindigkeit_value == ImpulsWellenlaenge.INFINITY ? "∞" : Helper.round(geschwindigkeit_value, 4)) + " " + ImpulsWellenlaenge.EINHEIT_ELEKTRONENGESCHWINDIGKEIT;
		
		rechenweg.lblBeschleunigungsspanneValue.setText( beschleunigungsspanne );
		rechenweg.lblInterferenzradiusValue.setText( radius );
		
		rechenweg.lblLambdaValue.setText( lambda );
		rechenweg.lblImpulsValue.setText( impuls );
		rechenweg.lblGeschwindigkeitValue.setText( geschwindigkeit );
		
		rechenweg.repaint();
	}
	
	public String[] prepare_data(JSONObject data_json) throws JSONException {
		// daten aus json laden
		JSONArray beschleunigungsspanne = data_json.getJSONArray("beschleunigungsspanne");
		JSONArray radius 				= data_json.getJSONArray("radius");
		JSONArray lambda 				= data_json.getJSONArray("lambda");
		JSONArray impuls 				= data_json.getJSONArray("impuls");
		JSONArray geschwindigkeit 		= data_json.getJSONArray("geschwindigkeit");
		
		kristallgitter	= data_json.getDouble("kristallgitter") * Math.pow(10, ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_KRISTALLGITTER);
		laenge			= data_json.getDouble("laenge");
		k				= data_json.getInt("k");
		
		//  daten initialisieren
		int length 						= beschleunigungsspanne.length();
		combobox_labels					= new String[length];
		beschleunigungsspanne_prepare 	= new double[length];
		radius_prepare 					= new double[length];
		lambda_prepare 					= new double[length];
		impuls_prepare 					= new double[length];
		geschwindigkeit_prepare			= new double[length];
		
		for(int i = 0; i < length; i++) {
			combobox_labels[i]					= "Rechenweg für Element " + (i + 1) + " / " + length;
			beschleunigungsspanne_prepare[i] 	= beschleunigungsspanne.getDouble(i);
			radius_prepare[i] 					= radius.getDouble(i);
			lambda_prepare[i] 					= lambda.getDouble(i) * Math.pow(10, ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_LAMBDA);
			impuls_prepare[i] 					= impuls.getDouble(i) * Math.pow(10, ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_IMPULS);
			geschwindigkeit_prepare[i]			= geschwindigkeit.getDouble(i);
		}
		
		return combobox_labels;
	}
}
