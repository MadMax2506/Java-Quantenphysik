package gui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.panels.HomePanel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1842167810443037193L;
	
	private JPanel jp_contentPane, jp_homePanel;
	private JMenuBar jmb_menubar;
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Quantenphysik");
		
		setMenu();
		initElements();
		addElements();
		setListener();
		
		setVisible(true);
	}

	public void setMenu() {
		jmb_menubar = new JMenuBar();
	}
	
	public void initElements() {
		// Content Panel
		jp_contentPane = new JPanel();
		jp_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jp_contentPane.setLayout(new GridBagLayout());
		
		jp_homePanel = new HomePanel();
	}
	
	public void addElements() {
		setContentPane(jp_contentPane);
		setJMenuBar(jmb_menubar);
		
		jp_contentPane.add(jp_homePanel);
	}
	
	public void setListener() {
		
	}
	
	public void visibilityOfPanels(boolean b_homePanelIsVisible) {
		jp_homePanel.setVisible(b_homePanelIsVisible);
	}
}
