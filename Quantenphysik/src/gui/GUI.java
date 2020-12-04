package gui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.panel.HomePanel;
import gui.panel.ImpulsWellenlaengePanel;

public class GUI extends JFrame {

	public final String HOME_PANEL_NAME = "HOME";
	public final String ELEKTONEN_PANEL_NAME = "ELEKTRONEN";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setBackground(Color.BLACK);
		int width 	= 550;
		int height 	= 450;
		
		setTitle("Quantenphysik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnRechenoperation = new JMenu("Rechenoperationen");
		mnRechenoperation.setBackground(Color.BLACK);
		mnRechenoperation.setForeground(Color.WHITE);
		menuBar.add(mnRechenoperation);
		
		JMenuItem mntmHome = new JMenuItem("Startseite");
		mntmHome.setForeground(Color.WHITE);
		mntmHome.setBackground(Color.BLACK);
		mnRechenoperation.add(mntmHome);
		mntmHome.addActionListener((e) -> show_panel(HOME_PANEL_NAME));
		mnRechenoperation.addSeparator();
		
		JMenuItem mntmElektronen = new JMenuItem("Impuls Wellenlaenge");
		mntmElektronen.setBackground(Color.BLACK);
		mntmElektronen.setForeground(Color.WHITE);
		mnRechenoperation.add(mntmElektronen);
		mntmElektronen.addActionListener((e) -> show_panel(ELEKTONEN_PANEL_NAME));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.setBackground( Color.BLACK );
		
		JPanel homePanel = new HomePanel();
		contentPane.add(homePanel, HOME_PANEL_NAME);
		
		JPanel impulsWellenlaengePanel = new ImpulsWellenlaengePanel(height, width);
		contentPane.add(impulsWellenlaengePanel, ELEKTONEN_PANEL_NAME);
		
		show_panel(HOME_PANEL_NAME);
	}
	
	private void show_panel(String name) {
		CardLayout c = (CardLayout)contentPane.getLayout();
		c.show(contentPane, name);
	}

}
