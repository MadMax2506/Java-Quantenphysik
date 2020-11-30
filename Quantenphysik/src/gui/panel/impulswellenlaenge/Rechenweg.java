package gui.panel.impulswellenlaenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import rechenoperationen.ImpulsWellenlaenge;

public class Rechenweg extends JFrame {
	
	String[] combobox_labels;
	
	public JComboBox<String> comboBox;
	
	public JLabel lblBeschleunigungsspanneValue;
	public JLabel lblWellenradiusValue;
	public JLabel lblLaengeValue;
	public JLabel lblKristallgitterValue;
	public JLabel lblKValue;
	
	public JLabel lblLambdaValue;
	public JLabel lblLambdaRechenweg;
	
	public JLabel lblImpulsValue;
	public JLabel lblImpulsRechenweg;
	
	private static final long serialVersionUID = -8069969261417568142L;
	private JPanel contentPane;
	
	public Rechenweg(JSONObject data_json) {
		int width = 550;
		int height = 300;
		
		RechenwegAction re = new RechenwegAction(this);
		
		try {
			combobox_labels = re.prepare_data(data_json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		comboBox = new JComboBox<String>();
		for(String lblBeschleunigungsspanne : combobox_labels) {
			comboBox.addItem(lblBeschleunigungsspanne);
		}
		contentPane.add(comboBox, BorderLayout.NORTH);
		
		JPanel main = new JPanel();
		main.setForeground(Color.WHITE);
		main.setBackground(Color.BLACK);
		contentPane.add(main, BorderLayout.CENTER);
		GridBagLayout gbl_main = new GridBagLayout();
		gbl_main.columnWidths = new int[]{(width * 2) / 5, (width * 1) / 5, (width * 2) / 5, 0};
		gbl_main.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_main.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_main.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		main.setLayout(gbl_main);
		
		JLabel lblBeschleunigungsspanne = new JLabel("Beschleunigungsspanne (u)");
		lblBeschleunigungsspanne.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeschleunigungsspanne.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBeschleunigungsspanne = new GridBagConstraints();
		gbc_lblBeschleunigungsspanne.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeschleunigungsspanne.insets = new Insets(0, 10, 5, 10);
		gbc_lblBeschleunigungsspanne.gridx = 0;
		gbc_lblBeschleunigungsspanne.gridy = 0;
		main.add(lblBeschleunigungsspanne, gbc_lblBeschleunigungsspanne);
		
		lblBeschleunigungsspanneValue = new JLabel("");
		lblBeschleunigungsspanneValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeschleunigungsspanneValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBeschleunigungsspanneValue = new GridBagConstraints();
		gbc_lblBeschleunigungsspanneValue.gridwidth = 2;
		gbc_lblBeschleunigungsspanneValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeschleunigungsspanneValue.insets = new Insets(0, 0, 5, 10);
		gbc_lblBeschleunigungsspanneValue.gridx = 1;
		gbc_lblBeschleunigungsspanneValue.gridy = 0;
		main.add(lblBeschleunigungsspanneValue, gbc_lblBeschleunigungsspanneValue);
		
		JLabel lblWellenradius = new JLabel("Wellenradius (r)");
		lblWellenradius.setHorizontalAlignment(SwingConstants.LEFT);
		lblWellenradius.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblWellenradius = new GridBagConstraints();
		gbc_lblWellenradius.fill = GridBagConstraints.BOTH;
		gbc_lblWellenradius.insets = new Insets(0, 10, 5, 10);
		gbc_lblWellenradius.gridx = 0;
		gbc_lblWellenradius.gridy = 1;
		main.add(lblWellenradius, gbc_lblWellenradius);
		
		lblWellenradiusValue = new JLabel("");
		lblWellenradiusValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblWellenradiusValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblWellenradiusValue = new GridBagConstraints();
		gbc_lblWellenradiusValue.gridwidth = 2;
		gbc_lblWellenradiusValue.fill = GridBagConstraints.BOTH;
		gbc_lblWellenradiusValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblWellenradiusValue.gridx = 1;
		gbc_lblWellenradiusValue.gridy = 1;
		main.add(lblWellenradiusValue, gbc_lblWellenradiusValue);
		
		JLabel lblLaenge = new JLabel("Länge (l)");
		lblLaenge.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaenge.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLaenge = new GridBagConstraints();
		gbc_lblLaenge.fill = GridBagConstraints.BOTH;
		gbc_lblLaenge.insets = new Insets(0, 10, 5, 10);
		gbc_lblLaenge.gridx = 0;
		gbc_lblLaenge.gridy = 2;
		main.add(lblLaenge, gbc_lblLaenge);
		
		lblLaengeValue = new JLabel("");
		lblLaengeValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaengeValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLaengeValue = new GridBagConstraints();
		gbc_lblLaengeValue.fill = GridBagConstraints.BOTH;
		gbc_lblLaengeValue.gridwidth = 2;
		gbc_lblLaengeValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblLaengeValue.gridx = 1;
		gbc_lblLaengeValue.gridy = 2;
		main.add(lblLaengeValue, gbc_lblLaengeValue);
		
		JLabel lblKristallgitter = new JLabel("Kristallgitter (d)");
		lblKristallgitter.setHorizontalAlignment(SwingConstants.LEFT);
		lblKristallgitter.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblKristallgitter = new GridBagConstraints();
		gbc_lblKristallgitter.fill = GridBagConstraints.BOTH;
		gbc_lblKristallgitter.insets = new Insets(0, 10, 5, 10);
		gbc_lblKristallgitter.gridx = 0;
		gbc_lblKristallgitter.gridy = 3;
		main.add(lblKristallgitter, gbc_lblKristallgitter);
		
		lblKristallgitterValue = new JLabel("");
		lblKristallgitterValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaengeValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblKristallgitterValue = new GridBagConstraints();
		gbc_lblKristallgitterValue.fill = GridBagConstraints.BOTH;
		gbc_lblKristallgitterValue.gridwidth = 2;
		gbc_lblKristallgitterValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblKristallgitterValue.gridx = 1;
		gbc_lblKristallgitterValue.gridy = 3;
		main.add(lblKristallgitterValue, gbc_lblKristallgitterValue);
		
		JLabel lblK = new JLabel("k");
		lblK.setHorizontalAlignment(SwingConstants.LEFT);
		lblK.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblK = new GridBagConstraints();
		gbc_lblK.fill = GridBagConstraints.BOTH;
		gbc_lblK.insets = new Insets(0, 10, 5, 10);
		gbc_lblK.gridx = 0;
		gbc_lblK.gridy = 4;
		main.add(lblK, gbc_lblK);
		
		lblKValue = new JLabel("");
		lblKValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblKValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblKValue = new GridBagConstraints();
		gbc_lblKValue.fill = GridBagConstraints.BOTH;
		gbc_lblKValue.gridwidth = 2;
		gbc_lblKValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblKValue.gridx = 1;
		gbc_lblKValue.gridy = 4;
		main.add(lblKValue, gbc_lblKValue);
		
		JSeparator separator_one = new JSeparator();
		separator_one.setBackground(Color.WHITE);
		separator_one.setForeground(Color.WHITE);
		GridBagConstraints gbc_separator_one = new GridBagConstraints();
		gbc_separator_one.gridwidth = 3;
		gbc_separator_one.fill = GridBagConstraints.BOTH;
		gbc_separator_one.insets = new Insets(10, 0, 5, 5);
		gbc_separator_one.gridx = 0;
		gbc_separator_one.gridy = 5;
		main.add(separator_one, gbc_separator_one);
		
		JLabel lblElektronenmasse = new JLabel("Elektronenmasse (m)");
		lblElektronenmasse.setHorizontalAlignment(SwingConstants.LEFT);
		lblElektronenmasse.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblElektronenmasse = new GridBagConstraints();
		gbc_lblElektronenmasse.fill = GridBagConstraints.BOTH;
		gbc_lblElektronenmasse.insets = new Insets(0, 10, 5, 10);
		gbc_lblElektronenmasse.gridx = 0;
		gbc_lblElektronenmasse.gridy = 6;
		main.add(lblElektronenmasse, gbc_lblElektronenmasse);
		
		JLabel lblElektronenmasseValue = new JLabel(ImpulsWellenlaenge.elektronenmasse + " kg");
		lblKValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblKValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblElektronenmasseValue = new GridBagConstraints();
		gbc_lblElektronenmasseValue.fill = GridBagConstraints.BOTH;
		gbc_lblElektronenmasseValue.gridwidth = 2;
		gbc_lblElektronenmasseValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblElektronenmasseValue.gridx = 1;
		gbc_lblElektronenmasseValue.gridy = 6;
		main.add(lblElektronenmasseValue, gbc_lblElektronenmasseValue);
		
		JLabel lblElektronengeschwindigkeit = new JLabel("Elektronengeschwindigkeit (e)");
		lblElektronengeschwindigkeit.setHorizontalAlignment(SwingConstants.LEFT);
		lblElektronengeschwindigkeit.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblElektronengeschwindigkeit = new GridBagConstraints();
		gbc_lblElektronengeschwindigkeit.fill = GridBagConstraints.BOTH;
		gbc_lblElektronengeschwindigkeit.insets = new Insets(0, 10, 5, 10);
		gbc_lblElektronengeschwindigkeit.gridx = 0;
		gbc_lblElektronengeschwindigkeit.gridy = 7;
		main.add(lblElektronengeschwindigkeit, gbc_lblElektronengeschwindigkeit);
		
		JLabel lblElektronengeschwindigkeitValue = new JLabel(ImpulsWellenlaenge.elektronengeschwindigkeit + " C");
		lblElektronengeschwindigkeitValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblElektronengeschwindigkeitValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblElektronengeschwindigkeitValue = new GridBagConstraints();
		gbc_lblElektronengeschwindigkeitValue.fill = GridBagConstraints.BOTH;
		gbc_lblElektronengeschwindigkeitValue.gridwidth = 2;
		gbc_lblElektronengeschwindigkeitValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblElektronengeschwindigkeitValue.gridx = 1;
		gbc_lblElektronengeschwindigkeitValue.gridy = 7;
		main.add(lblElektronengeschwindigkeitValue, gbc_lblElektronengeschwindigkeitValue);
		
		JSeparator separator_two = new JSeparator();
		separator_two.setBackground(Color.WHITE);
		separator_two.setForeground(Color.WHITE);
		GridBagConstraints gbc_separator_two = new GridBagConstraints();
		gbc_separator_two.gridwidth = 3;
		gbc_separator_two.fill = GridBagConstraints.BOTH;
		gbc_separator_two.insets = new Insets(10, 0, 5, 5);
		gbc_separator_two.gridx = 0;
		gbc_separator_two.gridy = 8;
		main.add(separator_two, gbc_separator_two);
		
		JLabel lblLambda = new JLabel("Lamdba (λ)");
		lblLambda.setHorizontalAlignment(SwingConstants.LEFT);
		lblLambda.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLambda = new GridBagConstraints();
		gbc_lblLambda.fill = GridBagConstraints.BOTH;
		gbc_lblLambda.insets = new Insets(0, 10, 5, 0);
		gbc_lblLambda.gridx = 0;
		gbc_lblLambda.gridy = 9;
		main.add(lblLambda, gbc_lblLambda);
		
		lblLambdaValue = new JLabel("");
		lblLambdaValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblLambdaValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLambdaValue = new GridBagConstraints();
		gbc_lblLambdaValue.fill = GridBagConstraints.BOTH;
		gbc_lblLambdaValue.gridwidth = 1;
		gbc_lblLambdaValue.insets = new Insets(0, 0, 5, 10);
		gbc_lblLambdaValue.gridx = 1;
		gbc_lblLambdaValue.gridy = 9;
		main.add(lblLambdaValue, gbc_lblLambdaValue);
		
		lblLambdaRechenweg = new JLabel("");
		lblLambdaRechenweg.setHorizontalAlignment(SwingConstants.LEFT);
		lblLambdaRechenweg.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLambdaRechenweg = new GridBagConstraints();
		gbc_lblLambdaRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblLambdaRechenweg.gridwidth = 1;
		gbc_lblLambdaRechenweg.insets = new Insets(0, 0, 5, 10);
		gbc_lblLambdaRechenweg.gridx = 2;
		gbc_lblLambdaRechenweg.gridy = 9;
		main.add(lblLambdaRechenweg, gbc_lblLambdaRechenweg);
		
		JLabel lblImpuls = new JLabel("Lamdba (λ)");
		lblImpuls.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpuls.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblImpuls = new GridBagConstraints();
		gbc_lblImpuls.fill = GridBagConstraints.BOTH;
		gbc_lblImpuls.insets = new Insets(0, 10, 5, 0);
		gbc_lblImpuls.gridx = 0;
		gbc_lblImpuls.gridy = 10;
		main.add(lblImpuls, gbc_lblImpuls);
		
		lblImpulsValue = new JLabel("");
		lblImpulsValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpulsValue.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblImpulsValue = new GridBagConstraints();
		gbc_lblImpulsValue.fill = GridBagConstraints.BOTH;
		gbc_lblImpulsValue.gridwidth = 1;
		gbc_lblImpulsValue.insets = new Insets(0, 0, 5, 10);
		gbc_lblImpulsValue.gridx = 1;
		gbc_lblImpulsValue.gridy = 10;
		main.add(lblImpulsValue, gbc_lblImpulsValue);
		
		lblImpulsRechenweg = new JLabel("");
		lblImpulsRechenweg.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpulsRechenweg.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblImpulsRechenweg = new GridBagConstraints();
		gbc_lblImpulsRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblImpulsRechenweg.gridwidth = 1;
		gbc_lblImpulsRechenweg.insets = new Insets(0, 0, 5, 10);
		gbc_lblImpulsRechenweg.gridx = 2;
		gbc_lblImpulsRechenweg.gridy = 10;
		main.add(lblImpulsRechenweg, gbc_lblImpulsRechenweg);
		
		re.change_rechenweg_values();
		comboBox.addActionListener((e) -> re.change_rechenweg_values());
	}
	
}
