package gui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import gui.panel.impulswellenlaenge.PanelAction;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;

public class ImpulsWellenlaengePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField txtBeschleunigungsspanne_one;
	public JTextField txtBeschleunigungsspanne_two;
	public JTextField txtRadius_one;
	public JTextField txtRadius_two;
	public JTextField txtLaenge;
	public JTextField txtKristallgitter;
	public JTextField txtK;
	
	public JCheckBox cbRechenweg;
	public JCheckBox cbDiagramm;

	/**
	 * Create the panel.
	 */
	public ImpulsWellenlaengePanel(int width) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel head = new JPanel();
		head.setBackground( Color.BLACK );
		add(head, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel("Wellencharakter von Elektronen");
		lbltitle.setFont(new Font("TI-Nspire Sans", Font.PLAIN, 20));
		lbltitle.setForeground( Color.WHITE );
		head.add(lbltitle);
		
		JPanel main = new JPanel();
		main.setBackground( Color.BLACK );
		add(main, BorderLayout.CENTER);
		GridBagLayout gbl_main = new GridBagLayout();
		gbl_main.columnWidths = new int[]{ (int)( (width * 3) / 5), (int)( (width * 2) / 5), 0};
		gbl_main.rowHeights = new int[]{7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 0};
		gbl_main.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_main.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		main.setLayout(gbl_main);
		
		JLabel lblBeuschleunigungsspanne_one = new JLabel("Beuschleunigungsspanne 1 (in V)");
		lblBeuschleunigungsspanne_one.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBeuschleunigungsspanne_one = new GridBagConstraints();
		gbc_lblBeuschleunigungsspanne_one.fill = GridBagConstraints.BOTH;
		gbc_lblBeuschleunigungsspanne_one.insets = new Insets(0, 10, 5, 0);
		gbc_lblBeuschleunigungsspanne_one.gridx = 0;
		gbc_lblBeuschleunigungsspanne_one.gridy = 0;
		main.add(lblBeuschleunigungsspanne_one, gbc_lblBeuschleunigungsspanne_one);
		
		txtBeschleunigungsspanne_one = new JTextField();
		GridBagConstraints gbc_txtBeschleunigungsspanne_one = new GridBagConstraints();
		gbc_txtBeschleunigungsspanne_one.fill = GridBagConstraints.BOTH;
		gbc_txtBeschleunigungsspanne_one.insets = new Insets(0, 0, 5, 10);
		gbc_txtBeschleunigungsspanne_one.gridx = 1;
		gbc_txtBeschleunigungsspanne_one.gridy = 0;
		main.add(txtBeschleunigungsspanne_one, gbc_txtBeschleunigungsspanne_one);
		txtBeschleunigungsspanne_one.setColumns(10);
		
		JLabel lblRadius_one = new JLabel("Interferenzradius 1 (in cm)");
		lblRadius_one.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblRadius_one = new GridBagConstraints();
		gbc_lblRadius_one.fill = GridBagConstraints.BOTH;
		gbc_lblRadius_one.insets = new Insets(0, 10, 5, 0);
		gbc_lblRadius_one.gridx = 0;
		gbc_lblRadius_one.gridy = 1;
		main.add(lblRadius_one, gbc_lblRadius_one);
		
		txtRadius_one = new JTextField();
		txtRadius_one.setColumns(10);
		GridBagConstraints gbc_txtRadius_one = new GridBagConstraints();
		gbc_txtRadius_one.fill = GridBagConstraints.BOTH;
		gbc_txtRadius_one.insets = new Insets(0, 0, 5, 10);
		gbc_txtRadius_one.gridx = 1;
		gbc_txtRadius_one.gridy = 1;
		main.add(txtRadius_one, gbc_txtRadius_one);
		
		JLabel lblBeuschleunigungsspanne_two = new JLabel("Beuschleunigungsspanne 2 (in V)");
		lblBeuschleunigungsspanne_two.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBeuschleunigungsspanne_two = new GridBagConstraints();
		gbc_lblBeuschleunigungsspanne_two.fill = GridBagConstraints.BOTH;
		gbc_lblBeuschleunigungsspanne_two.insets = new Insets(0, 10, 5, 0);
		gbc_lblBeuschleunigungsspanne_two.gridx = 0;
		gbc_lblBeuschleunigungsspanne_two.gridy = 2;
		main.add(lblBeuschleunigungsspanne_two, gbc_lblBeuschleunigungsspanne_two);
		
		txtBeschleunigungsspanne_two = new JTextField();
		txtBeschleunigungsspanne_two.setColumns(10);
		GridBagConstraints gbc_txtBeschleunigungsspanne_two = new GridBagConstraints();
		gbc_txtBeschleunigungsspanne_two.fill = GridBagConstraints.BOTH;
		gbc_txtBeschleunigungsspanne_two.insets = new Insets(0, 0, 5, 10);
		gbc_txtBeschleunigungsspanne_two.gridx = 1;
		gbc_txtBeschleunigungsspanne_two.gridy = 2;
		main.add(txtBeschleunigungsspanne_two, gbc_txtBeschleunigungsspanne_two);
		
		JLabel lblRadius_two = new JLabel("Interferenzradius 2 (in cm)");
		lblRadius_two.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblRadius_two = new GridBagConstraints();
		gbc_lblRadius_two.fill = GridBagConstraints.BOTH;
		gbc_lblRadius_two.insets = new Insets(0, 10, 5, 0);
		gbc_lblRadius_two.gridx = 0;
		gbc_lblRadius_two.gridy = 3;
		main.add(lblRadius_two, gbc_lblRadius_two);
		
		txtRadius_two = new JTextField();
		txtRadius_two.setColumns(10);
		GridBagConstraints gbc_txtRadius_two = new GridBagConstraints();
		gbc_txtRadius_two.fill = GridBagConstraints.BOTH;
		gbc_txtRadius_two.insets = new Insets(0, 0, 5, 10);
		gbc_txtRadius_two.gridx = 1;
		gbc_txtRadius_two.gridy = 3;
		main.add(txtRadius_two, gbc_txtRadius_two);
		
		JLabel lblLaenge = new JLabel("Länge (in cm)");
		lblLaenge.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLaenge = new GridBagConstraints();
		gbc_lblLaenge.fill = GridBagConstraints.BOTH;
		gbc_lblLaenge.insets = new Insets(0, 10, 5, 0);
		gbc_lblLaenge.gridx = 0;
		gbc_lblLaenge.gridy = 4;
		main.add(lblLaenge, gbc_lblLaenge);
		
		txtLaenge = new JTextField();
		txtLaenge.setColumns(10);
		GridBagConstraints gbc_txtLaenge = new GridBagConstraints();
		gbc_txtLaenge.fill = GridBagConstraints.BOTH;
		gbc_txtLaenge.insets = new Insets(0, 0, 5, 10);
		gbc_txtLaenge.gridx = 1;
		gbc_txtLaenge.gridy = 4;
		main.add(txtLaenge, gbc_txtLaenge);
		
		JLabel lblKristallgitter = new JLabel("Kristallgitter (in 10 ^ -10 m)");
		lblKristallgitter.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblKristallgitter = new GridBagConstraints();
		gbc_lblKristallgitter.fill = GridBagConstraints.BOTH;
		gbc_lblKristallgitter.insets = new Insets(0, 10, 5, 0);
		gbc_lblKristallgitter.gridx = 0;
		gbc_lblKristallgitter.gridy = 5;
		main.add(lblKristallgitter, gbc_lblKristallgitter);
		
		txtKristallgitter = new JTextField();
		txtKristallgitter.setColumns(10);
		GridBagConstraints gbc_txtKristallgitter = new GridBagConstraints();
		gbc_txtKristallgitter.fill = GridBagConstraints.BOTH;
		gbc_txtKristallgitter.insets = new Insets(0, 0, 5, 10);
		gbc_txtKristallgitter.gridx = 1;
		gbc_txtKristallgitter.gridy = 5;
		main.add(txtKristallgitter, gbc_txtKristallgitter);
		
		JLabel lblk = new JLabel("k");
		lblk.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblk = new GridBagConstraints();
		gbc_lblk.fill = GridBagConstraints.BOTH;
		gbc_lblk.insets = new Insets(0, 10, 5, 0);
		gbc_lblk.gridx = 0;
		gbc_lblk.gridy = 6;
		main.add(lblk, gbc_lblk);
		
		txtK = new JTextField();
		txtK.setColumns(10);
		GridBagConstraints gbc_txtK = new GridBagConstraints();
		gbc_txtK.fill = GridBagConstraints.BOTH;
		gbc_txtK.insets = new Insets(0, 0, 5, 10);
		gbc_txtK.gridx = 1;
		gbc_txtK.gridy = 6;
		main.add(txtK, gbc_txtK);
		
		JSeparator separator_one = new JSeparator();
		separator_one.setForeground(Color.WHITE);
		GridBagConstraints gbc_separator_one = new GridBagConstraints();
		gbc_separator_one.fill = GridBagConstraints.BOTH;
		gbc_separator_one.gridwidth = 2;
		gbc_separator_one.insets = new Insets(10, 0, 5, 0);
		gbc_separator_one.gridx = 0;
		gbc_separator_one.gridy = 7;
		main.add(separator_one, gbc_separator_one);
		
		JLabel lblDiagram = new JLabel("Diagramm anzeigen");
		lblDiagram.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDiagram = new GridBagConstraints();
		gbc_lblDiagram.fill = GridBagConstraints.BOTH;
		gbc_lblDiagram.insets = new Insets(0, 10, 5, 0);
		gbc_lblDiagram.gridx = 0;
		gbc_lblDiagram.gridy = 8;
		main.add(lblDiagram, gbc_lblDiagram);
		
		cbDiagramm = new JCheckBox();
		cbDiagramm.setSelected(true);
		cbDiagramm.setForeground(Color.WHITE);
		cbDiagramm.setBackground(Color.BLACK);
		GridBagConstraints gbc_cbDiagramm = new GridBagConstraints();
		gbc_cbDiagramm.fill = GridBagConstraints.BOTH;
		gbc_cbDiagramm.insets = new Insets(0, 0, 5, 10);
		gbc_cbDiagramm.gridx = 1;
		gbc_cbDiagramm.gridy = 8;
		main.add(cbDiagramm, gbc_cbDiagramm);
		
		JLabel lblRechenweg = new JLabel("Rechenweg anzeigen");
		lblRechenweg.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblRechenweg = new GridBagConstraints();
		gbc_lblRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblRechenweg.insets = new Insets(0, 10, 5, 0);
		gbc_lblRechenweg.gridx = 0;
		gbc_lblRechenweg.gridy = 9;
		main.add(lblRechenweg, gbc_lblRechenweg);
		
		cbRechenweg = new JCheckBox();
		cbRechenweg.setSelected(true);
		cbRechenweg.setForeground(Color.WHITE);
		cbRechenweg.setBackground(Color.BLACK);
		GridBagConstraints gbc_cbRechenweg = new GridBagConstraints();
		gbc_cbRechenweg.insets = new Insets(0, 0, 5, 10);
		gbc_cbRechenweg.fill = GridBagConstraints.BOTH;
		gbc_cbRechenweg.gridx = 1;
		gbc_cbRechenweg.gridy = 9;
		main.add(cbRechenweg, gbc_cbRechenweg);
		
		JSeparator separator_two = new JSeparator();
		separator_two.setForeground(Color.WHITE);
		GridBagConstraints gbc_separator_two = new GridBagConstraints();
		gbc_separator_two.fill = GridBagConstraints.BOTH;
		gbc_separator_two.gridwidth = 2;
		gbc_separator_two.insets = new Insets(5, 0, 10, 0);
		gbc_separator_two.gridx = 0;
		gbc_separator_two.gridy = 10;
		main.add(separator_two, gbc_separator_two);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.WHITE);
		add(panel, BorderLayout.SOUTH);
		
		JButton btnBerechnen = new JButton("Berechnen");
		panel.add(btnBerechnen);

		PanelAction ea = new PanelAction(this);
		
		btnBerechnen.addActionListener((e) -> ea.calculate());
		cbDiagramm.addActionListener((e) -> ea.check_checkboxen());
		cbRechenweg.addActionListener((e) -> ea.check_checkboxen());
	}

}
