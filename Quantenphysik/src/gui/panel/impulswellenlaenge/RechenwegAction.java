package gui.panel.impulswellenlaenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RechenwegAction {
	private Rechenweg rechenweg;
	
	public double kristallgitter;
	public double laenge;
	public int k;
	
	public double[] beschleunigungsspanne_prepare;
	public double[] radius_prepare;
	public double[] lambda_prepare;
	public double[] impuls_prepare;
	
	public RechenwegAction(Rechenweg rechenweg) {
		this.rechenweg = rechenweg;
	}
	
	public void change_rechenweg_values() {
		int selectindex = rechenweg.comboBox.getSelectedIndex();
		
		String beschleunigungsspanne 	= beschleunigungsspanne_prepare[selectindex] + " V";
		String radius 					= radius_prepare[selectindex] + " cm";
		String laenge 					= this.laenge + " cm";
		String kristallgitter			= this.kristallgitter + " in 10 ^ -10 m";
		String k						= this.k + "";
		
		String lambda 			= lambda_prepare[selectindex] + " 10 ^ -11 m";
		String lambda_rechenweg = "(2 * d * sin(0.5 * arcsin(r / l)) / k";
		
		String impuls			= impuls_prepare[selectindex] + " 10 ^ -11 m";
		String impuls_rechenweg = "sqrt(2 * m * e * U)";
		
		rechenweg.lblBeschleunigungsspanneValue.setText( beschleunigungsspanne );
		rechenweg.lblWellenradiusValue.setText( radius );
		rechenweg.lblLaengeValue.setText( laenge );
		rechenweg.lblKristallgitterValue.setText( kristallgitter );
		rechenweg.lblKValue.setText( k );
		
		rechenweg.lblLambdaValue.setText( lambda );
		rechenweg.lblLambdaRechenweg.setText( lambda_rechenweg );
		
		rechenweg.lblImpulsValue.setText( impuls );
		rechenweg.lblImpulsRechenweg.setText( impuls_rechenweg );
		
		rechenweg.repaint();
	}
	
	public String[] prepare_data(JSONObject data_json) throws JSONException {
		// daten aus json laden
		JSONArray beschleunigungsspanne = data_json.getJSONArray("beschleunigungsspanne");
		JSONArray radius 				= data_json.getJSONArray("radius");
		JSONArray lambda 				= data_json.getJSONArray("lambda");
		JSONArray impuls 				= data_json.getJSONArray("impuls");
		
		kristallgitter	= data_json.getDouble("kristallgitter");
		laenge			= data_json.getDouble("laenge");
		k				= data_json.getInt("k");
		
		//  daten initialisieren
		int length 						= beschleunigungsspanne.length();
		String[] combobox_labels		= new String[length];
		beschleunigungsspanne_prepare 	= new double[length];
		radius_prepare 					= new double[length];
		lambda_prepare 					= new double[length];
		impuls_prepare 					= new double[length];
		
		for(int i = 0; i < length; i++) {
			combobox_labels[i]					= "Rechenweg (" + (i + 1) + ")";
			beschleunigungsspanne_prepare[i] 	= beschleunigungsspanne.getDouble(i);
			radius_prepare[i] 					= radius.getDouble(i);
			lambda_prepare[i] 					= lambda.getDouble(i);
			impuls_prepare[i] 					= impuls.getDouble(i);
		}
		
		return combobox_labels;
	}
}
