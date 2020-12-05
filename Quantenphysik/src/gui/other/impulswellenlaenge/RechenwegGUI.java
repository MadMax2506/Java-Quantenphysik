package gui.other.impulswellenlaenge;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import rechenoperationen.Helper;
import rechenoperationen.ImpulsWellenlaenge;

public class RechenwegGUI extends JFrame {
	public JComboBox<String> comboBox;
	
	public JLabel lblBeschleunigungsspanneValue;
	public JLabel lblInterferenzradiusValue;
	public JLabel lblLaengeValue;
	public JLabel lblKristallgitterValue;
	public JLabel lblKValue;
	
	public JLabel lblLambdaValue;
	public JLabel lblLambdaRechenweg;
	
	public JLabel lblImpulsValue;
	public JLabel lblImpulsRechenweg;
	
	public JLabel lblGeschwindigkeitValue;
	public JLabel lblGeschwindigkeitRechenweg;
	
	private static final long serialVersionUID = -8069969261417568142L;
	private JPanel contentPane;
	
	public RechenwegGUI(JSONObject data_json) {
		int width = 1000;
		int height = 300;
		
		set_look_and_feel();
		
		RechenwegAction frame_action = new RechenwegAction(this);
		
		try {
			frame_action.prepare_data(data_json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		JScrollPane impulsWellenlaengeScrollPanel = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setContentPane(impulsWellenlaengeScrollPanel);
		
		comboBox = new JComboBox<String>();
		String[] combobox_labels = frame_action.get_combobox_labels();
		for(String lblBeschleunigungsspanne : combobox_labels) {
			comboBox.addItem(lblBeschleunigungsspanne);
		}
		if(combobox_labels.length > 1) {
			contentPane.add(comboBox, BorderLayout.NORTH);
		}
		
		JPanel main = new JPanel();
		contentPane.add(main, BorderLayout.CENTER);
		GridBagLayout gbl_main = new GridBagLayout();
		gbl_main.columnWidths = new int[]{(width * 2) / 5, (width * 1) / 5, (width * 2) / 5, 0};
		gbl_main.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_main.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_main.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		main.setLayout(gbl_main);
		
		JLabel lblBeschleunigungsspanne = new JLabel("Beschleunigungsspanne (u)");
		lblBeschleunigungsspanne.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblBeschleunigungsspanne = new GridBagConstraints();
		gbc_lblBeschleunigungsspanne.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeschleunigungsspanne.insets = new Insets(0, 10, 5, 10);
		gbc_lblBeschleunigungsspanne.gridx = 0;
		gbc_lblBeschleunigungsspanne.gridy = 0;
		main.add(lblBeschleunigungsspanne, gbc_lblBeschleunigungsspanne);
		
		lblBeschleunigungsspanneValue = new JLabel();
		lblBeschleunigungsspanneValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblBeschleunigungsspanneValue = new GridBagConstraints();
		gbc_lblBeschleunigungsspanneValue.gridwidth = 2;
		gbc_lblBeschleunigungsspanneValue.fill = GridBagConstraints.BOTH;
		gbc_lblBeschleunigungsspanneValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblBeschleunigungsspanneValue.gridx = 1;
		gbc_lblBeschleunigungsspanneValue.gridy = 0;
		main.add(lblBeschleunigungsspanneValue, gbc_lblBeschleunigungsspanneValue);
		
		JLabel lblInterferenzradius = new JLabel("Interferenzradius (r)");
		lblInterferenzradius.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblInterferenzradius = new GridBagConstraints();
		gbc_lblInterferenzradius.fill = GridBagConstraints.BOTH;
		gbc_lblInterferenzradius.insets = new Insets(0, 10, 5, 10);
		gbc_lblInterferenzradius.gridx = 0;
		gbc_lblInterferenzradius.gridy = 1;
		main.add(lblInterferenzradius, gbc_lblInterferenzradius);
		
		lblInterferenzradiusValue = new JLabel();
		lblInterferenzradiusValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblInterferenzradiusValue = new GridBagConstraints();
		gbc_lblInterferenzradiusValue.gridwidth = 2;
		gbc_lblInterferenzradiusValue.fill = GridBagConstraints.BOTH;
		gbc_lblInterferenzradiusValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblInterferenzradiusValue.gridx = 1;
		gbc_lblInterferenzradiusValue.gridy = 1;
		main.add(lblInterferenzradiusValue, gbc_lblInterferenzradiusValue);
		
		JLabel lblLaenge = new JLabel("Länge (l)");
		lblLaenge.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLaenge = new GridBagConstraints();
		gbc_lblLaenge.fill = GridBagConstraints.BOTH;
		gbc_lblLaenge.insets = new Insets(0, 10, 5, 10);
		gbc_lblLaenge.gridx = 0;
		gbc_lblLaenge.gridy = 2;
		main.add(lblLaenge, gbc_lblLaenge);
		
		lblLaengeValue = new JLabel();
		lblLaengeValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLaengeValue = new GridBagConstraints();
		gbc_lblLaengeValue.fill = GridBagConstraints.BOTH;
		gbc_lblLaengeValue.gridwidth = 2;
		gbc_lblLaengeValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblLaengeValue.gridx = 1;
		gbc_lblLaengeValue.gridy = 2;
		main.add(lblLaengeValue, gbc_lblLaengeValue);
		
		JLabel lblKristallgitter = new JLabel("Kristallgitter (d)");
		lblKristallgitter.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblKristallgitter = new GridBagConstraints();
		gbc_lblKristallgitter.fill = GridBagConstraints.BOTH;
		gbc_lblKristallgitter.insets = new Insets(0, 10, 5, 10);
		gbc_lblKristallgitter.gridx = 0;
		gbc_lblKristallgitter.gridy = 3;
		main.add(lblKristallgitter, gbc_lblKristallgitter);
		
		lblKristallgitterValue = new JLabel();
		lblKristallgitterValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblKristallgitterValue = new GridBagConstraints();
		gbc_lblKristallgitterValue.fill = GridBagConstraints.BOTH;
		gbc_lblKristallgitterValue.gridwidth = 2;
		gbc_lblKristallgitterValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblKristallgitterValue.gridx = 1;
		gbc_lblKristallgitterValue.gridy = 3;
		main.add(lblKristallgitterValue, gbc_lblKristallgitterValue);
		
		JLabel lblK = new JLabel("k");
		lblK.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblK = new GridBagConstraints();
		gbc_lblK.fill = GridBagConstraints.BOTH;
		gbc_lblK.insets = new Insets(0, 10, 5, 10);
		gbc_lblK.gridx = 0;
		gbc_lblK.gridy = 4;
		main.add(lblK, gbc_lblK);
		
		lblKValue = new JLabel();
		lblKValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblKValue = new GridBagConstraints();
		gbc_lblKValue.fill = GridBagConstraints.BOTH;
		gbc_lblKValue.gridwidth = 2;
		gbc_lblKValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblKValue.gridx = 1;
		gbc_lblKValue.gridy = 4;
		main.add(lblKValue, gbc_lblKValue);
		
		JSeparator separator_one = new JSeparator();
		GridBagConstraints gbc_separator_one = new GridBagConstraints();
		gbc_separator_one.gridwidth = 3;
		gbc_separator_one.fill = GridBagConstraints.BOTH;
		gbc_separator_one.insets = new Insets(10, 0, 5, 5);
		gbc_separator_one.gridx = 0;
		gbc_separator_one.gridy = 5;
		main.add(separator_one, gbc_separator_one);
		
		JLabel lblElektronenmasse = new JLabel("Elektronenmasse (m)");
		lblElektronenmasse.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblElektronenmasse = new GridBagConstraints();
		gbc_lblElektronenmasse.fill = GridBagConstraints.BOTH;
		gbc_lblElektronenmasse.insets = new Insets(0, 10, 5, 10);
		gbc_lblElektronenmasse.gridx = 0;
		gbc_lblElektronenmasse.gridy = 6;
		main.add(lblElektronenmasse, gbc_lblElektronenmasse);
		
		JLabel lblElektronenmasseValue = new JLabel( Helper.round(ImpulsWellenlaenge.ELEKTRONENMASSE * Math.pow(10, 31), 4)  + " 10 ^ -31 kg");
		lblElektronenmasseValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblKValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblElektronenmasseValue = new GridBagConstraints();
		gbc_lblElektronenmasseValue.fill = GridBagConstraints.BOTH;
		gbc_lblElektronenmasseValue.gridwidth = 2;
		gbc_lblElektronenmasseValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblElektronenmasseValue.gridx = 1;
		gbc_lblElektronenmasseValue.gridy = 6;
		main.add(lblElektronenmasseValue, gbc_lblElektronenmasseValue);
		
		JLabel lblElementarladung = new JLabel("Elementarladung (e)");
		lblElementarladung.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblElementarladung = new GridBagConstraints();
		gbc_lblElementarladung.fill = GridBagConstraints.BOTH;
		gbc_lblElementarladung.insets = new Insets(0, 10, 5, 10);
		gbc_lblElementarladung.gridx = 0;
		gbc_lblElementarladung.gridy = 7;
		main.add(lblElementarladung, gbc_lblElementarladung);
		
		JLabel lblElementarladungValue = new JLabel( Helper.round(ImpulsWellenlaenge.ELEMENTARLADUNG * Math.pow(10, 19), 4) + " 10 ^ -19 C");
		lblElementarladungValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblElementarladungValue = new GridBagConstraints();
		gbc_lblElementarladungValue.fill = GridBagConstraints.BOTH;
		gbc_lblElementarladungValue.gridwidth = 2;
		gbc_lblElementarladungValue.insets = new Insets(0, 10, 5, 10);
		gbc_lblElementarladungValue.gridx = 1;
		gbc_lblElementarladungValue.gridy = 7;
		main.add(lblElementarladungValue, gbc_lblElementarladungValue);
		
		JSeparator separator_two = new JSeparator();
		GridBagConstraints gbc_separator_two = new GridBagConstraints();
		gbc_separator_two.gridwidth = 3;
		gbc_separator_two.fill = GridBagConstraints.BOTH;
		gbc_separator_two.insets = new Insets(10, 0, 5, 5);
		gbc_separator_two.gridx = 0;
		gbc_separator_two.gridy = 8;
		main.add(separator_two, gbc_separator_two);
		
		JLabel lblLambda = new JLabel("Lamdba (λ)");
		lblLambda.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLambda = new GridBagConstraints();
		gbc_lblLambda.fill = GridBagConstraints.BOTH;
		gbc_lblLambda.insets = new Insets(0, 10, 5, 10);
		gbc_lblLambda.gridx = 0;
		gbc_lblLambda.gridy = 9;
		main.add(lblLambda, gbc_lblLambda);
		
		lblLambdaValue = new JLabel();
		lblLambdaValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLambdaValue = new GridBagConstraints();
		gbc_lblLambdaValue.fill = GridBagConstraints.BOTH;
		gbc_lblLambdaValue.gridwidth = 1;
		gbc_lblLambdaValue.insets = new Insets(0, 10, 5, 5);
		gbc_lblLambdaValue.gridx = 1;
		gbc_lblLambdaValue.gridy = 9;
		main.add(lblLambdaValue, gbc_lblLambdaValue);
		
		lblLambdaRechenweg = new JLabel();
		lblLambdaRechenweg.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblLambdaRechenweg = new GridBagConstraints();
		gbc_lblLambdaRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblLambdaRechenweg.gridwidth = 1;
		gbc_lblLambdaRechenweg.insets = new Insets(0, 10, 5, 5);
		gbc_lblLambdaRechenweg.gridx = 2;
		gbc_lblLambdaRechenweg.gridy = 9;
		main.add(lblLambdaRechenweg, gbc_lblLambdaRechenweg);
		
		JLabel lblImpuls = new JLabel("Impuls (P)");
		lblImpuls.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblImpuls = new GridBagConstraints();
		gbc_lblImpuls.fill = GridBagConstraints.BOTH;
		gbc_lblImpuls.insets = new Insets(0, 10, 5, 10);
		gbc_lblImpuls.gridx = 0;
		gbc_lblImpuls.gridy = 10;
		main.add(lblImpuls, gbc_lblImpuls);
		
		lblImpulsValue = new JLabel();
		lblImpulsValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblImpulsValue = new GridBagConstraints();
		gbc_lblImpulsValue.fill = GridBagConstraints.BOTH;
		gbc_lblImpulsValue.gridwidth = 1;
		gbc_lblImpulsValue.insets = new Insets(0, 10, 5, 5);
		gbc_lblImpulsValue.gridx = 1;
		gbc_lblImpulsValue.gridy = 10;
		main.add(lblImpulsValue, gbc_lblImpulsValue);
		
		lblImpulsRechenweg = new JLabel();
		lblImpulsRechenweg.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblImpulsRechenweg = new GridBagConstraints();
		gbc_lblImpulsRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblImpulsRechenweg.gridwidth = 1;
		gbc_lblImpulsRechenweg.insets = new Insets(0, 10, 5, 5);
		gbc_lblImpulsRechenweg.gridx = 2;
		gbc_lblImpulsRechenweg.gridy = 10;
		main.add(lblImpulsRechenweg, gbc_lblImpulsRechenweg);
		
		JLabel lblGeschwindigkeit = new JLabel("Geschwindigkeit (v)");
		lblGeschwindigkeit.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblGeschwindigkeit = new GridBagConstraints();
		gbc_lblGeschwindigkeit.fill = GridBagConstraints.BOTH;
		gbc_lblGeschwindigkeit.insets = new Insets(0, 10, 5, 10);
		gbc_lblGeschwindigkeit.gridx = 0;
		gbc_lblGeschwindigkeit.gridy = 11;
		main.add(lblGeschwindigkeit, gbc_lblGeschwindigkeit);
		
		lblGeschwindigkeitValue = new JLabel();
		lblGeschwindigkeitValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblGeschwindigkeitValue = new GridBagConstraints();
		gbc_lblGeschwindigkeitValue.fill = GridBagConstraints.BOTH;
		gbc_lblGeschwindigkeitValue.gridwidth = 1;
		gbc_lblGeschwindigkeitValue.insets = new Insets(0, 10, 5, 5);
		gbc_lblGeschwindigkeitValue.gridx = 1;
		gbc_lblGeschwindigkeitValue.gridy = 11;
		main.add(lblGeschwindigkeitValue, gbc_lblGeschwindigkeitValue);
		
		lblGeschwindigkeitRechenweg = new JLabel();
		lblGeschwindigkeitRechenweg.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblGeschwindigkeitRechenweg = new GridBagConstraints();
		gbc_lblGeschwindigkeitRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblGeschwindigkeitRechenweg.gridwidth = 1;
		gbc_lblGeschwindigkeitRechenweg.insets = new Insets(0, 10, 5, 5);
		gbc_lblGeschwindigkeitRechenweg.gridx = 2;
		gbc_lblGeschwindigkeitRechenweg.gridy = 11;
		main.add(lblGeschwindigkeitRechenweg, gbc_lblGeschwindigkeitRechenweg);
		
		// Actions
		frame_action.set_rechenweg_werte();
		comboBox.addActionListener((e) -> frame_action.set_rechenweg_werte());
	}
	
	private void set_look_and_feel() {		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (Exception e2) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e3) {
					try {
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					} catch (Exception e4) {}
				}
			}
		}
	}
	
}
