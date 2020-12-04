package gui.panel.impulswellenlaenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiagramFrame extends JFrame {

	private static final long serialVersionUID = -6746551090428171825L;

	private JPanel contentPane;
	
	public DiagramFrame(JSONObject data_json) {
		int width = 550;
		int height = 300;
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, width, height);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground( Color.BLACK );
		
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
			contentPane.add(chartPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel foot = new JPanel();
		foot.setForeground(Color.WHITE);
		foot.setBackground(Color.BLACK);
		contentPane.add(foot, BorderLayout.SOUTH);
		GridBagLayout gbl_foot = new GridBagLayout();
		gbl_foot.columnWidths = new int[]{(width * 1) / 5, (width * 4) / 5, 0};
		gbl_foot.rowHeights = new int[]{0, 0, 0};
		gbl_foot.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_foot.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		foot.setLayout(gbl_foot);
		
		JLabel lblFktgleichung = new JLabel("Funktionsgleichung");
		lblFktgleichung.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFktgleichung = new GridBagConstraints();
		gbc_lblFktgleichung.insets = new Insets(0, 0, 5, 5);
		gbc_lblFktgleichung.gridx = 0;
		gbc_lblFktgleichung.gridy = 0;
		foot.add(lblFktgleichung, gbc_lblFktgleichung);
		
		JLabel lblFktgleichungValue = new JLabel("New label");
		GridBagConstraints gbc_lblFktgleichungValue = new GridBagConstraints();
		gbc_lblFktgleichungValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblFktgleichungValue.gridx = 1;
		gbc_lblFktgleichungValue.gridy = 0;
		foot.add(lblFktgleichungValue, gbc_lblFktgleichungValue);
		
		JLabel lblProportionalittsfaktor = new JLabel("Proportionalit\u00E4tsfaktor");
		lblProportionalittsfaktor.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblProportionalittsfaktor = new GridBagConstraints();
		gbc_lblProportionalittsfaktor.insets = new Insets(0, 0, 0, 5);
		gbc_lblProportionalittsfaktor.gridx = 0;
		gbc_lblProportionalittsfaktor.gridy = 1;
		foot.add(lblProportionalittsfaktor, gbc_lblProportionalittsfaktor);
		
		JLabel lblProportionalitätsfaktorValue = new JLabel("New label");
		GridBagConstraints gbc_lblProportionalitätsfaktorValue = new GridBagConstraints();
		gbc_lblProportionalitätsfaktorValue.gridx = 1;
		gbc_lblProportionalitätsfaktorValue.gridy = 1;
		foot.add(lblProportionalitätsfaktorValue, gbc_lblProportionalitätsfaktorValue);
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
