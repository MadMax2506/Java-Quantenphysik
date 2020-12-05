package gui.other.impulswellenlaenge;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rechenoperationen.ImpulsWellenlaenge;

public class DiagrammAction {
	private DiagrammGUI diagramm;
	
	private double proportionalitaetskonstante;
	
	private double[] lambda_kehrwert_prepare;
	private double[] impuls_prepare;
	
	public DiagrammAction(DiagrammGUI diagramm) {
		this.diagramm = diagramm;
	}
	
	//getter
	public XYPlot get_plot() {
		XYSplineRenderer dot = new XYSplineRenderer();
		
		NumberAxis xax = new NumberAxis("1 / Lambda [ 10 ^ -" + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_LAMBDA + " " + ImpulsWellenlaenge.EINHEIT_LAMBDA + "]");
		NumberAxis yax = new NumberAxis("Impuls [ 10 ^ " + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_IMPULS + " " + ImpulsWellenlaenge.EINHEIT_IMPULS + "]");
		
		DefaultXYDataset dataset = new DefaultXYDataset();
		dataset.addSeries( "berechntete Punkte", get_berechnete_werte() );
		dataset.addSeries( "Ausgleichsgerade", get_funktionswerte() );
		
		return new XYPlot(dataset, xax, yax, dot);
	}
	
	private double[][] get_berechnete_werte() {
		return new double[][] { lambda_kehrwert_prepare, impuls_prepare };
	}
	
	private double[][] get_funktionswerte() {
		int length 	= lambda_kehrwert_prepare.length;
		

		double[] x	= new double [length];
		double[] y	= new double [length];
		
		for(int i = 0; i < length; i++) {
			x[i] = lambda_kehrwert_prepare[i];
			y[i] = get_funktionswert(lambda_kehrwert_prepare[i]);
		}
		
		return new double[][] { x, y};
	}
	
	private double get_funktionswert(double x) {
		return x * proportionalitaetskonstante; 
	}
	
	// setter
	public void set_funktion() {
		diagramm.lblFktgleichungValue.setText( "f(x) = " + proportionalitaetskonstante + " * x");
		diagramm.lblProportionalitaetsfaktorValue.setText( proportionalitaetskonstante + " " + ImpulsWellenlaenge.EINHEIT_PROPORTIONALITAETSKONSTANTE );
	}
	
	// other
	public void prepare_data(JSONObject data_json) throws JSONException {
		// daten aus json laden
		JSONArray lambda_kehrwert = data_json.getJSONArray("1/lambda");
		JSONArray impuls = data_json.getJSONArray("impuls");
		
		proportionalitaetskonstante = data_json.getDouble("proportionalitaetskonstante");
		
		//  daten initialisieren
		int length 				= lambda_kehrwert.length();
		lambda_kehrwert_prepare = new double[length];
		impuls_prepare 			= new double[length];
		
		for(int i = 0; i < length; i++) {
			lambda_kehrwert_prepare[i] 	= lambda_kehrwert.getDouble(i) * Math.pow(10, -ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_LAMBDA);
			impuls_prepare[i] 			= impuls.getDouble(i) * Math.pow(10, ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_IMPULS);
		}
	}
}
