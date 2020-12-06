package gui.main.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.main.panel.action.ImpulsWellenlaengeLaden;

import java.awt.GridLayout;

public class ImpulsWellenlaengeLadenPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public JList<String> list_files;
	
	public JCheckBox cbRechenweg;
	public JCheckBox cbDiagramm;

	/**
	 * Create the panel.
	 */
	public ImpulsWellenlaengeLadenPanel(int height, int width) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel head = new JPanel();
		add(head, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel("Wellencharakter von Elektronen");
		lbltitle.setFont(new Font("TI-Nspire Sans", Font.PLAIN, 20));
		head.add(lbltitle);
		
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(0, 1, 0, 0));
		add(main, BorderLayout.CENTER);
		
		list_files = new JList<String>();
		list_files.setLayoutOrientation(JList.VERTICAL);
		JScrollPane list_files_scrollPanel = new JScrollPane(list_files);
		main.add(list_files_scrollPanel);

		JPanel footer = new JPanel();
		GridBagLayout gbl_footer = new GridBagLayout();
		gbl_footer.columnWidths = new int[]{ 0, 0, 0, 0};
		gbl_footer.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_footer.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_footer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		footer.setLayout(gbl_footer);
		add(footer, BorderLayout.SOUTH);
		
		JLabel lblRechenweg = new JLabel("Rechenweg anzeigen");
		GridBagConstraints gbc_lblRechenweg = new GridBagConstraints();
		gbc_lblRechenweg.fill = GridBagConstraints.BOTH;
		gbc_lblRechenweg.insets = new Insets(20, 10, 5, 5);
		gbc_lblRechenweg.gridx = 0;
		gbc_lblRechenweg.gridy = 0;
		footer.add(lblRechenweg, gbc_lblRechenweg);
		
		cbRechenweg = new JCheckBox();
		GridBagConstraints gbc_cbRechenweg = new GridBagConstraints();
		gbc_cbRechenweg.insets = new Insets(0, 0, 5, 10);
		gbc_cbRechenweg.fill = GridBagConstraints.BOTH;
		gbc_cbRechenweg.gridwidth = 1;
		gbc_cbRechenweg.gridx = 1;
		gbc_cbRechenweg.gridy = 0;
		footer.add(cbRechenweg, gbc_cbRechenweg);
		
		JLabel lblDiagram = new JLabel("Diagramm anzeigen");
		GridBagConstraints gbc_lblDiagram = new GridBagConstraints();
		gbc_lblDiagram.fill = GridBagConstraints.BOTH;
		gbc_lblDiagram.insets = new Insets(0, 10, 5, 5);
		gbc_lblDiagram.gridx = 0;
		gbc_lblDiagram.gridy = 1;
		footer.add(lblDiagram, gbc_lblDiagram);
		
		cbDiagramm = new JCheckBox();
		cbDiagramm.setSelected(true);
		GridBagConstraints gbc_cbDiagramm = new GridBagConstraints();
		gbc_cbDiagramm.fill = GridBagConstraints.BOTH;
		gbc_cbDiagramm.insets = new Insets(0, 0, 5, 10);
		gbc_cbDiagramm.gridwidth = 1;
		gbc_cbDiagramm.gridx = 1;
		gbc_cbDiagramm.gridy = 1;
		footer.add(cbDiagramm, gbc_cbDiagramm);
		
		JButton btn_laden = new JButton("Laden");
		GridBagConstraints gbc_btn_laden = new GridBagConstraints();
		gbc_btn_laden.fill = GridBagConstraints.BOTH;
		gbc_btn_laden.insets = new Insets(10, 0, 10, 0);
		gbc_btn_laden.gridwidth = 2;
		gbc_btn_laden.gridx = 0;
		gbc_btn_laden.gridy = 2;
		footer.add(btn_laden, gbc_btn_laden);
		
		
		// aktionen initalisieren
		ImpulsWellenlaengeLaden panel_action = new ImpulsWellenlaengeLaden(this);
		
		list_files.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() > 0) {
		            int index = list_files.locationToIndex(evt.getPoint());
		            panel_action.set_selected_value(index);
		        }
		    }
		});
		
		btn_laden.addActionListener((e) -> panel_action.load_data());
		
		// checkbox
		cbDiagramm.addActionListener((e) -> panel_action.set_checkboxen());
		cbRechenweg.addActionListener((e) -> panel_action.set_checkboxen());
	}
}
