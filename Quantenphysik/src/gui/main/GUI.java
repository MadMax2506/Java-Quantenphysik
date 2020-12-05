package gui.main;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gui.main.panel.HomePanel;
import gui.main.panel.ImpulsWellenlaengePanel;

public class GUI extends JFrame {

	public final String HOME_PANEL_NAME 						= "HOME";
	public final String IMPULSWELLENLAENGE_BERECHNEN_PANEL_NAME = "Impuls Wellenlaenge berechnen";
	public final String IMPULSWELLENLAENGE_LADEN_PANEL_NAME 	= "Impuls Wellenlaenge laden";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUI() {
		int width 	= 550;
		int height 	= 400;
		
		set_look_and_feel();
		
		setTitle("Quantenphysik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOption = new JMenu("Optionen");
		menuBar.add(mnOption);
		
		JMenuItem mntmHome = new JMenuItem("Startseite");
		mntmHome.setOpaque(true);
		mntmHome.addActionListener((e) -> show_panel(HOME_PANEL_NAME));
		mnOption.add(mntmHome);
		
		mnOption.addSeparator();
		
		JMenu mntmImpulsWellenlaenge = new JMenu("Impuls Wellenlaenge");
		mnOption.add(mntmImpulsWellenlaenge);
		
		JMenuItem mntmImpulsWellenlaengeBerechnen = new JMenuItem("Berechnen");
		mntmImpulsWellenlaengeBerechnen.addActionListener((e) -> show_panel(IMPULSWELLENLAENGE_BERECHNEN_PANEL_NAME));
		mntmImpulsWellenlaenge.add(mntmImpulsWellenlaengeBerechnen);
		
		JMenuItem mntmImpulsWellenlaengeLaden = new JMenuItem("Laden");
		mntmImpulsWellenlaengeLaden.addActionListener((e) -> show_panel(IMPULSWELLENLAENGE_LADEN_PANEL_NAME));
		mntmImpulsWellenlaenge.add(mntmImpulsWellenlaengeLaden);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel homePanel = new HomePanel();
		contentPane.add(homePanel, HOME_PANEL_NAME);
		
		JPanel impulsWellenlaengePanel = new ImpulsWellenlaengePanel(height, width);
		contentPane.add(impulsWellenlaengePanel, IMPULSWELLENLAENGE_BERECHNEN_PANEL_NAME);
		
		show_panel(HOME_PANEL_NAME);
	}
	
	private void show_panel(String name) {
		CardLayout c = (CardLayout)contentPane.getLayout();
		c.show(contentPane, name);
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
