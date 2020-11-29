package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;

public class ElektonenPanel extends JPanel {

	private static final long serialVersionUID = 1L;

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

	}

}
