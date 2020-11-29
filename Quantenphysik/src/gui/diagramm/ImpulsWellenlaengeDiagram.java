package gui.diagramm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImpulsWellenlaengeDiagram extends JFrame {

	private static final long serialVersionUID = -6746551090428171825L;

	public ImpulsWellenlaengeDiagram(JSONObject data_json) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		try {
			
			XYDotRenderer dot = new XYDotRenderer();		
			dot.setDotHeight(5);
			dot.setDotWidth(5);
			
			NumberAxis xax = new NumberAxis("1 / Lambda [ 10 ^ -11 m ]");
			NumberAxis yax = new NumberAxis("Impuls [ 10 ^ 23 m ]");
			
			DefaultXYDataset dataset = new DefaultXYDataset();
			dataset.addSeries( "", prepare_data(data_json) );
			
			XYPlot plot = new XYPlot(dataset,xax,yax, dot);
			JFreeChart chart = new JFreeChart(plot);
			
			ChartPanel chartPanel = new ChartPanel(chart);
			setContentPane(chartPanel);
		} catch (Exception e) {
			e.printStackTrace();
			setContentPane(new JPanel());
		}
	}

	
	private double[][] prepare_data(JSONObject data_json) throws JSONException {
		// daten aus json laden
		JSONArray lambda = data_json.getJSONArray("lambda");
		JSONArray impuls = data_json.getJSONArray("impuls");
		
		//  daten initialisieren
		int length 				= lambda.length();
		double[] lambda_prepare = new double[length];
		double[] impuls_prepare = new double[length];
		
		for(int i = 0; i < length; i++) {
			lambda_prepare[i] = (1 / lambda.getDouble(i) ) * Math.pow(10, -11);
			impuls_prepare[i] = impuls.getDouble(i) * Math.pow(10, 23);
		}
		return new double[][]{ lambda_prepare, impuls_prepare };
	}
}
