package gui.other.impulswellenlaenge;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.json.JSONObject;

public class DiagrammGUI extends JFrame {

	private static final long serialVersionUID = -6746551090428171825L;

	private JPanel contentPane;
	
	public JLabel lblFktgleichungValue;
	public JLabel lblProportionalitaetsfaktorValue;
	
	public DiagrammGUI(JSONObject data_json) {
		int width = 550;
		int height = 300;
		
		set_look_and_feel();
		
		DiagrammAction frame_action = new DiagrammAction(this);
		
		try {
			frame_action.prepare_data(data_json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, width, height);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		try {
			JFreeChart chart = new JFreeChart( frame_action.get_plot() );
			
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setBackground(SystemColor.LIGHT_GRAY);
			contentPane.add(chartPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel foot = new JPanel();
		contentPane.add(foot, BorderLayout.SOUTH);
		GridBagLayout gbl_foot = new GridBagLayout();
		gbl_foot.columnWidths = new int[]{(width * 2) / 5, (width * 3) / 5, 0};
		gbl_foot.rowHeights = new int[]{0, 0, 0};
		gbl_foot.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_foot.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		foot.setLayout(gbl_foot);
		
		JLabel lblProportionalitaetsfaktor = new JLabel("Proportionalit\u00E4tskonstante");
		GridBagConstraints gbc_lblProportionalitaetsfaktor = new GridBagConstraints();
		gbc_lblProportionalitaetsfaktor.fill = GridBagConstraints.BOTH;
		gbc_lblProportionalitaetsfaktor.insets = new Insets(0, 0, 0, 5);
		gbc_lblProportionalitaetsfaktor.gridx = 0;
		gbc_lblProportionalitaetsfaktor.gridy = 0;
		foot.add(lblProportionalitaetsfaktor, gbc_lblProportionalitaetsfaktor);
		
		lblProportionalitaetsfaktorValue = new JLabel();
		GridBagConstraints gbc_lblProportionalitaetsfaktorValue = new GridBagConstraints();
		gbc_lblProportionalitaetsfaktorValue.fill = GridBagConstraints.BOTH;
		gbc_lblProportionalitaetsfaktorValue.gridx = 1;
		gbc_lblProportionalitaetsfaktorValue.gridy = 0;
		foot.add(lblProportionalitaetsfaktorValue, gbc_lblProportionalitaetsfaktorValue);
		
		JLabel lblFktgleichung = new JLabel("Funktionsgleichung");
		GridBagConstraints gbc_lblFktgleichung = new GridBagConstraints();
		gbc_lblFktgleichung.fill = GridBagConstraints.BOTH;
		gbc_lblFktgleichung.insets = new Insets(0, 0, 5, 5);
		gbc_lblFktgleichung.gridx = 0;
		gbc_lblFktgleichung.gridy = 1;
		foot.add(lblFktgleichung, gbc_lblFktgleichung);
		
		lblFktgleichungValue = new JLabel();
		GridBagConstraints gbc_lblFktgleichungValue = new GridBagConstraints();
		gbc_lblFktgleichungValue.fill = GridBagConstraints.BOTH;
		gbc_lblFktgleichungValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblFktgleichungValue.gridx = 1;
		gbc_lblFktgleichungValue.gridy = 1;
		foot.add(lblFktgleichungValue, gbc_lblFktgleichungValue);
		
		// Action
		frame_action.set_funktion();
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
