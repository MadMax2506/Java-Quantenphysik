package gui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ElektonenPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtBeschleunigungsspanne_one;
	private JTextField txtBeschleunigungsspanne_two;
	private JTextField txtRadius_one;
	private JTextField txtRadius_two;
	private JTextField txtLaenge;
	private JTextField txtKristallgitter;
	private JTextField txtK;

	/**
	 * Create the panel.
	 */
	public ElektonenPanel() {
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
		main.setLayout(new GridLayout(0, 2, 10, 20));
		
		JLabel lblBeuschleunigungsspanne_one = new JLabel("Beuschleunigungsspanne 1 (in V)");
		lblBeuschleunigungsspanne_one.setForeground(Color.WHITE);
		main.add(lblBeuschleunigungsspanne_one);
		
		txtBeschleunigungsspanne_one = new JTextField();
		main.add(txtBeschleunigungsspanne_one);
		txtBeschleunigungsspanne_one.setColumns(10);
		
		JLabel lblRadius_one = new JLabel("Wellenradius 1 (in cm)");
		lblRadius_one.setForeground(Color.WHITE);
		main.add(lblRadius_one);
		
		txtRadius_one = new JTextField();
		txtRadius_one.setColumns(10);
		main.add(txtRadius_one);
		
		JLabel lblBeuschleunigungsspanne_two = new JLabel("Beuschleunigungsspanne 2 (in V)");
		lblBeuschleunigungsspanne_two.setForeground(Color.WHITE);
		main.add(lblBeuschleunigungsspanne_two);
		
		txtBeschleunigungsspanne_two = new JTextField();
		txtBeschleunigungsspanne_two.setColumns(10);
		main.add(txtBeschleunigungsspanne_two);
		
		JLabel lblRadius_two = new JLabel("Wellenradius 2 (in cm)");
		lblRadius_two.setForeground(Color.WHITE);
		main.add(lblRadius_two);
		
		txtRadius_two = new JTextField();
		txtRadius_two.setColumns(10);
		main.add(txtRadius_two);
		
		JLabel lblLaenge = new JLabel("Länge (in cm)");
		lblLaenge.setForeground(Color.WHITE);
		main.add(lblLaenge);
		
		txtLaenge = new JTextField();
		txtLaenge.setColumns(10);
		main.add(txtLaenge);
		
		JLabel lblKristallgitter = new JLabel("Kristallgitter");
		lblKristallgitter.setForeground(Color.WHITE);
		main.add(lblKristallgitter);
		
		txtKristallgitter = new JTextField();
		txtKristallgitter.setColumns(10);
		main.add(txtKristallgitter);
		
		JLabel lblk = new JLabel("k");
		lblk.setForeground(Color.WHITE);
		main.add(lblk);
		
		txtK = new JTextField();
		txtK.setColumns(10);
		main.add(txtK);
		
		JLabel lblDiagram = new JLabel("Diagramm");
		lblDiagram.setForeground(Color.WHITE);
		main.add(lblDiagram);
		
		JCheckBox cbDiagramm = new JCheckBox("Anzeigen");
		cbDiagramm.setForeground(Color.WHITE);
		cbDiagramm.setBackground(Color.BLACK);
		main.add(cbDiagramm);
		
		JLabel lblRechenweg = new JLabel("Rechenweg");
		lblRechenweg.setForeground(Color.WHITE);
		main.add(lblRechenweg);
		
		JCheckBox cbRechenweg = new JCheckBox("Anzeigen");
		cbRechenweg.setForeground(Color.WHITE);
		cbRechenweg.setBackground(Color.BLACK);
		main.add(cbRechenweg);
		
		JLabel label = new JLabel("");
		add(label, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("");
		add(lblNewLabel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.WHITE);
		add(panel, BorderLayout.SOUTH);
		
		JButton btnBerechnen = new JButton("Berechnen");
		panel.add(btnBerechnen);

	}

}
