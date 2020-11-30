package gui.panel.impulswellenlaenge;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import gui.panel.ImpulsWellenlaengePanel;
import rechenoperationen.ImpulsWellenlaenge;

public class PanelAction {
	private ImpulsWellenlaengePanel elektronenPanel;
	private boolean invalid_inputs;
	
	public PanelAction(ImpulsWellenlaengePanel elektronenPanel) {
		this.elektronenPanel = elektronenPanel;
	}
	
	public void check_checkboxen() {
		// Fortlaufende Actionen initialisieren
		boolean show_rechenweg = elektronenPanel.cbRechenweg.isSelected();
		boolean show_diagramm = elektronenPanel.cbDiagramm.isSelected();
		
		if(show_diagramm && !show_rechenweg) {
			
			elektronenPanel.cbDiagramm.setEnabled(false);
			elektronenPanel.cbRechenweg.setEnabled(true);
			
		} else if(!show_diagramm && show_rechenweg) {
			
			elektronenPanel.cbDiagramm.setEnabled(true);
			elektronenPanel.cbRechenweg.setEnabled(false);
		} else {
			
			elektronenPanel.cbDiagramm.setEnabled(true);
			elektronenPanel.cbRechenweg.setEnabled(true);
		}
	}
	
	public void calculate() {
		// Fortlaufende Actionen initialisieren
		boolean show_rechenweg = elektronenPanel.cbRechenweg.isSelected();
		boolean show_diagramm = elektronenPanel.cbDiagramm.isSelected();
		
		invalid_inputs = false;
		
		// Values initialisieren
		double beschleunigungsspanne_one = get_textfield_value(elektronenPanel.txtBeschleunigungsspanne_one);
		double beschleunigungsspanne_two = get_textfield_value(elektronenPanel.txtBeschleunigungsspanne_two);
		double[] beschleunigungsspanne = new double[]{ beschleunigungsspanne_one, beschleunigungsspanne_two };
		
		double radius_der_welle_one = get_textfield_value(elektronenPanel.txtRadius_one);
		double radius_der_welle_two = get_textfield_value(elektronenPanel.txtRadius_two);
		double[] radius_der_welle = new double[]{ radius_der_welle_one, radius_der_welle_two };
		
		double kristallgitter = get_textfield_value(elektronenPanel.txtKristallgitter) * Math.pow(10, -10);
		
		double laenge = get_textfield_value(elektronenPanel.txtLaenge);
		
		double k = get_textfield_value(elektronenPanel.txtK);
		
		if(invalid_inputs) {
			JOptionPane.showMessageDialog(null, "Bitte korrigieren Sie Ihre Eingaben.", "Fehlerhafte Eingaben", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ImpulsWellenlaenge iw;
		try {
			iw = new ImpulsWellenlaenge(beschleunigungsspanne, radius_der_welle, kristallgitter, laenge, (int)k);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Leider ist ein Systemfehler aufgetreten.\n Versuchen Sie es zu einem späteren Zeitpunkt erneut..", "Systemfehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//
		if(show_rechenweg) {
			JSONObject rechenweg_json = iw.get_rechenweg_json();
			EventQueue.invokeLater(() -> start_rechenweg(rechenweg_json));
		}
		
		if(show_diagramm) {
			JSONObject diagramm_json = iw.get_diagramm_json();
			EventQueue.invokeLater(() -> start_diagramm(diagramm_json));
		}
	}
	
	private double get_textfield_value(JTextField txtField) {
		try {
			return Double.parseDouble( txtField.getText() );
		} catch (Exception e) {
			invalid_inputs = true;
			txtField.setText("");
			
			return Float.NaN;
		}
	}
	
	private void start_diagramm(JSONObject data_json)  {
		try {
			DiagramFrame iwd = new DiagramFrame(data_json);
			iwd.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void start_rechenweg(JSONObject data_json)  {
		try {
			Rechenweg iwr = new Rechenweg(data_json);
			iwr.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
