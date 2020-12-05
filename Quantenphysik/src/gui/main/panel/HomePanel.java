package gui.main.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel main = new JPanel();
		add(main, BorderLayout.CENTER);
		main.setLayout(new BorderLayout(0, 0));
		main.setBackground( Color.BLACK );
		
		JLabel lblTitle = new JLabel("Herzlich Wilkommen");
		lblTitle.setForeground(Color.WHITE);
		main.add(lblTitle, BorderLayout.NORTH);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblImage = new JLabel( new ImageIcon( new File("assets/image/background_edit.jpg").getAbsolutePath() ) );
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lblImage);

	}

}
