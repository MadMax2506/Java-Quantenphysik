package gui.other.impulswellenlaenge;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiagrammAction {
	private DiagrammGUI diagramm;
	
	private double proportionalitaetskonstante;
	
	private double[] lambda_prepare;
	private double[] impuls_prepare;
	
	public DiagrammAction(DiagrammGUI diagramm) {
		this.diagramm = diagramm;
	}
	
	//getter
	public XYPlot get_plot() {
		XYDotRenderer dot = new XYDotRenderer();		
		dot.setDotHeight(5);
		dot.setDotWidth(5);
		
		NumberAxis xax = new NumberAxis("1 / Lambda [ 10 ^ -11 m ]");
		NumberAxis yax = new NumberAxis("Impuls [ 10 ^ 23 m ]");
		
		DefaultXYDataset dataset = new DefaultXYDataset();
		dataset.addSeries( "", new double[][]{ lambda_prepare, impuls_prepare } );
		
		return new XYPlot(dataset,xax,yax, dot);
	}
	
	// setter
	public void set_funktion() {
		diagramm.lblFktgleichungValue.setText( "f(x) = " + proportionalitaetskonstante + " * x");
		diagramm.lblProportionalitaetsfaktorValue.setText( proportionalitaetskonstante + " Js" );
	}
	
	// other
	public void prepare_data(JSONObject data_json) throws JSONException {
		// daten aus json laden
		JSONArray lambda = data_json.getJSONArray("lambda");
		JSONArray impuls = data_json.getJSONArray("impuls");
		
		proportionalitaetskonstante = data_json.getDouble("proportionalitaetskonstante");
		
		//  daten initialisieren
		int length 		= lambda.length();
		lambda_prepare 	= new double[length];
		impuls_prepare 	= new double[length];
		
		for(int i = 0; i < length; i++) {
			lambda_prepare[i] = (1 / lambda.getDouble(i) ) * Math.pow(10, -11);
			impuls_prepare[i] = impuls.getDouble(i) * Math.pow(10, 23);
		}
	}
}
