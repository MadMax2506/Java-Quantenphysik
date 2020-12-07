package gui.main.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.main.panel.action.ImpulsWellenlaengeLaden;

public class ImpulsWellenlaengeLadenPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JCheckBox cbRechenweg;
	public JCheckBox cbDiagramm;
	
	public ImpulsWellenlaengeLadenPanel(int height, int width) {
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
		gbl_main.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		main.setLayout(gbl_main);
		add(main, BorderLayout.CENTER);
		
		JLabel lblRechenweg = new JLabel("Rechenweg anzeigen");
		GridBagConstraints gbc_lblRechenweg = new GridBagConstraints();
		gbc_lblRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblRechenweg.insets = new Insets(20, 10, 5, 5);
		gbc_lblRechenweg.gridx = 0;
		gbc_lblRechenweg.gridy = 0;
		main.add(lblRechenweg, gbc_lblRechenweg);
		
		cbRechenweg = new JCheckBox();
		GridBagConstraints gbc_cbRechenweg = new GridBagConstraints();
		gbc_cbRechenweg.insets = new Insets(0, 0, 5, 10);
		gbc_cbRechenweg.fill = GridBagConstraints.BOTH;
		gbc_cbRechenweg.gridwidth = 1;
		gbc_cbRechenweg.gridx = 1;
		gbc_cbRechenweg.gridy = 0;
		main.add(cbRechenweg, gbc_cbRechenweg);
		
		JLabel lblDiagram = new JLabel("Diagramm anzeigen");
		GridBagConstraints gbc_lblDiagram = new GridBagConstraints();
		gbc_lblDiagram.fill = GridBagConstraints.BOTH;
		gbc_lblDiagram.insets = new Insets(0, 10, 5, 5);
		gbc_lblDiagram.gridx = 0;
		gbc_lblDiagram.gridy = 1;
		main.add(lblDiagram, gbc_lblDiagram);
		
		cbDiagramm = new JCheckBox();
		cbDiagramm.setSelected(true);
		GridBagConstraints gbc_cbDiagramm = new GridBagConstraints();
		gbc_cbDiagramm.fill = GridBagConstraints.BOTH;
		gbc_cbDiagramm.insets = new Insets(0, 0, 5, 10);
		gbc_cbDiagramm.gridwidth = 1;
		gbc_cbDiagramm.gridx = 1;
		gbc_cbDiagramm.gridy = 1;
		main.add(cbDiagramm, gbc_cbDiagramm);
		
		JButton btn_laden = new JButton("Laden");
		GridBagConstraints gbc_btn_laden = new GridBagConstraints();
		gbc_btn_laden.fill = GridBagConstraints.BOTH;
		gbc_btn_laden.insets = new Insets(10, 0, 10, 0);
		gbc_btn_laden.gridwidth = 2;
		gbc_btn_laden.gridx = 0;
		gbc_btn_laden.gridy = 2;
		main.add(btn_laden, gbc_btn_laden);
		
		
		// aktionen initalisieren
		ImpulsWellenlaengeLaden panel_action = new ImpulsWellenlaengeLaden(this);

		
		btn_laden.addActionListener((e) -> panel_action.load_data());
		
		// checkbox
		cbDiagramm.addActionListener((e) -> panel_action.set_checkboxen());
		cbRechenweg.addActionListener((e) -> panel_action.set_checkboxen());
	}
}
