package gui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.panel.ElektonenPanel;
import gui.panel.HomePanel;

public class GUI extends JFrame {

	public final String HOME_PANEL_NAME = "HOME";
	public final String ELEKTONEN_PANEL_NAME = "ELEKTRONEN";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Quantenphysik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRechenoperation = new JMenu("Rechenoperationen");
		menuBar.add(mnRechenoperation);
		
		JMenuItem mntmHome = new JMenuItem("Startseite");
		mnRechenoperation.add(mntmHome);
		mntmHome.addActionListener((e) -> show_panel(HOME_PANEL_NAME));
		
		JMenuItem mntmElektronen = new JMenuItem("Wellencharakter von Elektronen");
		mnRechenoperation.add(mntmElektronen);
		mntmElektronen.addActionListener((e) -> show_panel(ELEKTONEN_PANEL_NAME));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.setBackground( Color.BLACK );
		
		JPanel homePanel = new HomePanel();
		contentPane.add(homePanel, HOME_PANEL_NAME);
		
		JPanel elektronenPanel = new ElektonenPanel();
		contentPane.add(elektronenPanel, ELEKTONEN_PANEL_NAME);
		
		show_panel(HOME_PANEL_NAME);
	}
	
	private void show_panel(String name) {
		CardLayout c = (CardLayout)contentPane.getLayout();
		c.show(contentPane, name);
	}

}
