
package gui.main.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import rechenoperationen.ImpulsWellenlaenge;

public class ImpulsWellenlaengeBerechnenPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JTextField txtAnzahlDerElemente;
	public JTextField txtBeschleunigungsspanne;
	public JTextField txtInterferenzRadius;
	public JTextField txtLaenge;
	public JTextField txtKristallgitter;
	public JTextField txtK;
	
	public JLabel lblCurrentStep;
	
	public JCheckBox cbSave;
	public JCheckBox cbRechenweg;
	public JCheckBox cbDiagramm;
	
	public JButton btn_zurück;
	public JButton btn_weiter;

	/**
	 * Create the panel.
	 */
	public ImpulsWellenlaengeBerechnenPanel(int height, int width) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel head = new JPanel();
		add(head, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel("Wellencharakter von Elektronen");
		lbltitle.setFont(new Font("TI-Nspire Sans", Font.PLAIN, 20));
		head.add(lbltitle);
		
		JPanel main = new JPanel();
		GridBagLayout gbl_main = new GridBagLayout();
		gbl_main.columnWidths = new int[]{ 0, 0, 0, 0};
		gbl_main.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_main.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_main.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		main.setLayout(gbl_main);
		JScrollPane impulsWellenlaengeScrollPanel = new JScrollPane(main, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(impulsWellenlaengeScrollPanel, BorderLayout.CENTER);
		
		JLabel lblAnzahlDerElemente = new JLabel("Anzahl der Elemente");
		GridBagConstraints gbc_lblAnzahlDerElemente = new GridBagConstraints();
		gbc_lblAnzahlDerElemente.anchor = GridBagConstraints.EAST;
		gbc_lblAnzahlDerElemente.fill = GridBagConstraints.BOTH;
		gbc_lblAnzahlDerElemente.insets = new Insets(0, 10, 5, 5);
		gbc_lblAnzahlDerElemente.gridx = 0;
		gbc_lblAnzahlDerElemente.gridy = 0;
		main.add(lblAnzahlDerElemente, gbc_lblAnzahlDerElemente);
		
		txtAnzahlDerElemente = new JTextField();
		GridBagConstraints gbc_txtAnzahlDerElemente = new GridBagConstraints();
		gbc_txtAnzahlDerElemente.insets = new Insets(0, 0, 5, 10);
		gbc_txtAnzahlDerElemente.fill = GridBagConstraints.BOTH;
		gbc_txtAnzahlDerElemente.gridx = 1;
		gbc_txtAnzahlDerElemente.gridy = 0;
		main.add(txtAnzahlDerElemente, gbc_txtAnzahlDerElemente);
		txtAnzahlDerElemente.setColumns(10);
		
		JButton btn_check = new JButton("✔");
		btn_check.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btn_weiter = new GridBagConstraints();
		gbc_btn_weiter.insets = new Insets(0, 0, 5, 10);
		gbc_btn_weiter.fill = GridBagConstraints.BOTH;
		gbc_btn_weiter.gridx = 2;
		gbc_btn_weiter.gridy = 0;
		main.add(btn_check, gbc_btn_weiter);
		
		JSeparator separator_one = new JSeparator();
		GridBagConstraints gbc_separator_one = new GridBagConstraints();
		gbc_separator_one.fill = GridBagConstraints.BOTH;
		gbc_separator_one.gridwidth = 3;
		gbc_separator_one.gridx = 0;
		gbc_separator_one.gridy = 1;
		main.add(separator_one, gbc_separator_one);
		
		JPanel dynamic_controll = new JPanel();
		FlowLayout flowLayout = (FlowLayout) dynamic_controll.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_dynamic_controll = new GridBagConstraints();
		gbc_dynamic_controll.gridwidth = 3;
		gbc_dynamic_controll.insets = new Insets(0, 0, 5, 0);
		gbc_dynamic_controll.fill = GridBagConstraints.BOTH;
		gbc_dynamic_controll.gridx = 0;
		gbc_dynamic_controll.gridy = 2;
		main.add(dynamic_controll, gbc_dynamic_controll);
		
		lblCurrentStep = new JLabel();
		dynamic_controll.add(lblCurrentStep);
		
		btn_zurück = new JButton("<<");
		btn_zurück.setFont(new Font("Dialog", Font.BOLD, 10));
		dynamic_controll.add(btn_zurück);
		
		btn_weiter = new JButton(">>");
		btn_weiter.setFont(new Font("Dialog", Font.BOLD, 10));
		dynamic_controll.add(btn_weiter);
		
		JLabel lblBeschleunigungsspanne = new JLabel("Beschleunigungsspanne (in " + ImpulsWellenlaenge.EINHEIT_BESCHLEUNIGUNGSSPANNE + ")");
		GridBagConstraints gbc_lblBeschleunigungsspanne = new GridBagConstraints();
		gbc_lblBeschleunigungsspanne.fill = GridBagConstraints.BOTH;
		gbc_lblBeschleunigungsspanne.insets = new Insets(0, 10, 5, 5);
		gbc_lblBeschleunigungsspanne.gridx = 0;
		gbc_lblBeschleunigungsspanne.gridy = 3;
		main.add(lblBeschleunigungsspanne, gbc_lblBeschleunigungsspanne);
		
		txtBeschleunigungsspanne = new JTextField();
		GridBagConstraints gbc_txtBeschleunigungsspanne = new GridBagConstraints();
		gbc_txtBeschleunigungsspanne.fill = GridBagConstraints.BOTH;
		gbc_txtBeschleunigungsspanne.insets = new Insets(0, 0, 5, 10);
		gbc_txtBeschleunigungsspanne.gridwidth = 2;
		gbc_txtBeschleunigungsspanne.gridx = 1;
		gbc_txtBeschleunigungsspanne.gridy = 3;
		main.add(txtBeschleunigungsspanne, gbc_txtBeschleunigungsspanne);
		txtBeschleunigungsspanne.setColumns(10);
		
		JLabel lblRadius = new JLabel("Interferenzradius (in " + ImpulsWellenlaenge.EINHEIT_INTERFERENZRADIUS + ")");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.fill = GridBagConstraints.BOTH;
		gbc_lblRadius.insets = new Insets(0, 10, 5, 5);
		gbc_lblRadius.gridx = 0;
		gbc_lblRadius.gridy = 4;
		main.add(lblRadius, gbc_lblRadius);
		
		txtInterferenzRadius = new JTextField();
		txtInterferenzRadius.setColumns(10);
		GridBagConstraints gbc_txtInterferenzRadius = new GridBagConstraints();
		gbc_txtInterferenzRadius.fill = GridBagConstraints.BOTH;
		gbc_txtInterferenzRadius.insets = new Insets(0, 0, 5, 10);
		gbc_txtInterferenzRadius.gridwidth = 2;
		gbc_txtInterferenzRadius.gridx = 1;
		gbc_txtInterferenzRadius.gridy = 4;
		main.add(txtInterferenzRadius, gbc_txtInterferenzRadius);
		
		JSeparator separator_two = new JSeparator();
		GridBagConstraints gbc_separator_two = new GridBagConstraints();
		gbc_separator_two.insets = new Insets(0, 0, 5, 0);
		gbc_separator_two.fill = GridBagConstraints.BOTH;
		gbc_separator_two.gridx = 0;
		gbc_separator_two.gridy = 5;
		gbc_separator_two.gridwidth = 3;
		main.add(separator_two, gbc_separator_two);
		
		JLabel lblLaenge = new JLabel("Länge (in cm)");
		GridBagConstraints gbc_lblLaenge = new GridBagConstraints();
		gbc_lblLaenge.fill = GridBagConstraints.BOTH;
		gbc_lblLaenge.insets = new Insets(0, 10, 5, 5);
		gbc_lblLaenge.gridx = 0;
		gbc_lblLaenge.gridy = 6;
		main.add(lblLaenge, gbc_lblLaenge);
		
		txtLaenge = new JTextField();
		txtLaenge.setColumns(10);
		GridBagConstraints gbc_txtLaenge = new GridBagConstraints();
		gbc_txtLaenge.fill = GridBagConstraints.BOTH;
		gbc_txtLaenge.insets = new Insets(0, 0, 5, 10);
		gbc_txtLaenge.gridwidth = 2;
		gbc_txtLaenge.gridx = 1;
		gbc_txtLaenge.gridy = 6;
		main.add(txtLaenge, gbc_txtLaenge);
		
		JLabel lblKristallgitter = new JLabel("Kristallgitter (in 10 ^ " + ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_KRISTALLGITTER + " " + ImpulsWellenlaenge.EINHEIT_KRISTALLGITTER + ")");
		GridBagConstraints gbc_lblKristallgitter = new GridBagConstraints();
		gbc_lblKristallgitter.fill = GridBagConstraints.BOTH;
		gbc_lblKristallgitter.insets = new Insets(0, 10, 5, 5);
		gbc_lblKristallgitter.gridx = 0;
		gbc_lblKristallgitter.gridy = 7;
		main.add(lblKristallgitter, gbc_lblKristallgitter);
		
		txtKristallgitter = new JTextField();
		txtKristallgitter.setColumns(10);
		GridBagConstraints gbc_txtKristallgitter = new GridBagConstraints();
		gbc_txtKristallgitter.fill = GridBagConstraints.BOTH;
		gbc_txtKristallgitter.insets = new Insets(0, 0, 5, 10);
		gbc_txtKristallgitter.gridwidth = 2;
		gbc_txtKristallgitter.gridx = 1;
		gbc_txtKristallgitter.gridy = 7;
		main.add(txtKristallgitter, gbc_txtKristallgitter);
		
		JLabel lblk = new JLabel("k");
		GridBagConstraints gbc_lblk = new GridBagConstraints();
		gbc_lblk.fill = GridBagConstraints.BOTH;
		gbc_lblk.insets = new Insets(0, 10, 5, 5);
		gbc_lblk.gridx = 0;
		gbc_lblk.gridy = 8;
		main.add(lblk, gbc_lblk);
		
		txtK = new JTextField();
		txtK.setColumns(10);
		GridBagConstraints gbc_txtK = new GridBagConstraints();
		gbc_txtK.fill = GridBagConstraints.BOTH;
		gbc_txtK.insets = new Insets(0, 0, 5, 10);
		gbc_txtK.gridwidth = 2;
		gbc_txtK.gridx = 1;
		gbc_txtK.gridy = 8;
		main.add(txtK, gbc_txtK);
		
		JSeparator separator_three = new JSeparator();
		GridBagConstraints gbc_separator_three = new GridBagConstraints();
		gbc_separator_three.fill = GridBagConstraints.BOTH;
		gbc_separator_three.gridwidth = 3;
		gbc_separator_three.insets = new Insets(10, 0, 5, 0);
		gbc_separator_three.gridx = 0;
		gbc_separator_three.gridy = 9;
		main.add(separator_three, gbc_separator_three);
		
		JLabel lblRechenweg = new JLabel("Rechenweg anzeigen");
		GridBagConstraints gbc_lblRechenweg = new GridBagConstraints();
		gbc_lblRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblRechenweg.insets = new Insets(0, 10, 5, 5);
		gbc_lblRechenweg.gridx = 0;
		gbc_lblRechenweg.gridy = 10;
		main.add(lblRechenweg, gbc_lblRechenweg);
		
		cbRechenweg = new JCheckBox();
		GridBagConstraints gbc_cbRechenweg = new GridBagConstraints();
		gbc_cbRechenweg.insets = new Insets(0, 0, 5, 10);
		gbc_cbRechenweg.fill = GridBagConstraints.BOTH;
		gbc_cbRechenweg.gridwidth = 2;
		gbc_cbRechenweg.gridx = 1;
		gbc_cbRechenweg.gridy = 10;
		main.add(cbRechenweg, gbc_cbRechenweg);
		
		JLabel lblSave = new JLabel("Rechenweg speichern");
		GridBagConstraints gbc_lblSave = new GridBagConstraints();
		gbc_lblSave.fill = GridBagConstraints.BOTH;
		gbc_lblSave.insets = new Insets(0, 10, 5, 5);
		gbc_lblSave.gridx = 0;
		gbc_lblSave.gridy = 11;
		main.add(lblSave, gbc_lblSave);
		
		cbSave = new JCheckBox();
		GridBagConstraints gbc_cbSave = new GridBagConstraints();
		gbc_cbSave.insets = new Insets(0, 0, 5, 10);
		gbc_cbSave.fill = GridBagConstraints.BOTH;
		gbc_cbSave.gridwidth = 2;
		gbc_cbSave.gridx = 1;
		gbc_cbSave.gridy = 11;
		main.add(cbSave, gbc_cbSave);
		
		JLabel lblDiagram = new JLabel("Diagramm anzeigen");
		GridBagConstraints gbc_lblDiagram = new GridBagConstraints();
		gbc_lblDiagram.fill = GridBagConstraints.BOTH;
		gbc_lblDiagram.insets = new Insets(0, 10, 5, 5);
		gbc_lblDiagram.gridx = 0;
		gbc_lblDiagram.gridy = 12;
		main.add(lblDiagram, gbc_lblDiagram);
		
		cbDiagramm = new JCheckBox();
		cbDiagramm.setSelected(true);
		GridBagConstraints gbc_cbDiagramm = new GridBagConstraints();
		gbc_cbDiagramm.fill = GridBagConstraints.BOTH;
		gbc_cbDiagramm.insets = new Insets(0, 0, 5, 10);
		gbc_cbDiagramm.gridwidth = 2;
		gbc_cbDiagramm.gridx = 1;
		gbc_cbDiagramm.gridy = 12;
		main.add(cbDiagramm, gbc_cbDiagramm);
		
		JSeparator separator_four = new JSeparator();
		GridBagConstraints gbc_separator_four = new GridBagConstraints();
		gbc_separator_four.fill = GridBagConstraints.BOTH;
		gbc_separator_four.gridwidth = 3;
		gbc_separator_four.insets = new Insets(5, 0, 10, 0);
		gbc_separator_four.gridx = 0;
		gbc_separator_four.gridy = 13;
		main.add(separator_four, gbc_separator_four);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton btnBerechnen = new JButton("Berechnen");
		panel.add(btnBerechnen);

		
		// aktionen initalisieren
		ImpulsWellenlaengeBerechnenAction panel_action = new ImpulsWellenlaengeBerechnenAction(this);
		
		// anzahl der elemente

		txtAnzahlDerElemente.addActionListener((e) -> panel_action.set_anzahl_der_elemente());
		btn_check.addActionListener((e) -> panel_action.set_anzahl_der_elemente());
		
		// aktuelles element
		btn_zurück.addActionListener((e) -> panel_action.set_aktuelles_element_index(false));
		btn_weiter.addActionListener((e) -> panel_action.set_aktuelles_element_index(true));
		
		// inputs
		txtBeschleunigungsspanne.addKeyListener( addKeyListener(panel_action) );
		txtInterferenzRadius.addKeyListener( addKeyListener(panel_action) );
		txtLaenge.addKeyListener( addKeyListener(panel_action) );
		txtKristallgitter.addKeyListener( addKeyListener(panel_action) );
		txtK.addKeyListener( addKeyListener(panel_action) );
		
		// berechnen
		txtBeschleunigungsspanne.addActionListener((e) -> panel_action.berechnen());
		txtInterferenzRadius.addActionListener((e) -> panel_action.berechnen());
		txtLaenge.addActionListener((e) -> panel_action.berechnen());
		txtKristallgitter.addActionListener((e) -> panel_action.berechnen());
		txtK.addActionListener((e) -> panel_action.berechnen());
		btnBerechnen.addActionListener((e) -> panel_action.berechnen());
		
		// checkbox
		cbSave.addActionListener((e) -> panel_action.set_checkboxen());
		cbDiagramm.addActionListener((e) -> panel_action.set_checkboxen());
		cbRechenweg.addActionListener((e) -> panel_action.set_checkboxen());
	}

	
	private KeyListener addKeyListener(ImpulsWellenlaengeBerechnenAction panel_action) {
		return new KeyListener() {	
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getSource() == txtBeschleunigungsspanne) {
					panel_action.set_beschleunigungsspanne();
				} else if(e.getSource() == txtInterferenzRadius) {
					panel_action.set_interferenzradius();
				} else if(e.getSource() == txtLaenge) {
					panel_action.set_laenge();
				} else if(e.getSource() == txtKristallgitter) {
					panel_action.set_kristallgitter();
				} else if(e.getSource() == txtK) {
					panel_action.set_k();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		};
	}
}
