package gui.main.panel;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import app.App;
import gui.other.impulswellenlaenge.DiagrammGUI;
import gui.other.impulswellenlaenge.RechenwegGUI;
import rechenoperationen.Helper;
import rechenoperationen.ImpulsWellenlaenge;

public class ImpulsWellenlaengeBerechnenAction {
	public final double EMPTY_VALUE 	= -1.12321312321;
	public final int MIN_COUNT_ELEMENTS = 1;
	
	private ImpulsWellenlaengeBerechnenPanel elektronenPanel;
	
	// input des aktuellen elementes
	public int anzahl_der_elemente;
	public int aktuelles_element_index;
	
	// dynamische inputs
	private LinkedList<Double> beschleunigungsspanne;
	private LinkedList<Double> interferenz_radius;

	// statische inputs
	private double laenge;
	private double kristallgitter;
	private int k;
	
	private boolean save_rechenweg;
	private boolean show_rechenweg;
	private boolean show_diagramm;
	
	public ImpulsWellenlaengeBerechnenAction(ImpulsWellenlaengeBerechnenPanel elektronenPanel) {
		this.elektronenPanel = elektronenPanel;
		
		set_default();
	}
	
	// setter
	public void set_default() {
		// head
		anzahl_der_elemente = 2;
		aktuelles_element_index = 0;
		elektronenPanel.txtAnzahlDerElemente.setText(String.valueOf(anzahl_der_elemente));
		set_aktuelles_element_werte();
		check_element_kontroll_btn();
		
		// dynamische daten
		set_leeren_speicher();
		
		// statische daten
		laenge 			= EMPTY_VALUE;
		kristallgitter 	= EMPTY_VALUE;
		
		k = 1;
		elektronenPanel.txtK.setText(String.valueOf(k));
		
		show_rechenweg = true;
		elektronenPanel.cbRechenweg.setSelected(show_rechenweg);
		elektronenPanel.cbRechenweg.setEnabled(false);
		
		save_rechenweg = false;
		elektronenPanel.cbSave.setSelected(save_rechenweg);
		
		show_diagramm = false;
		elektronenPanel.cbDiagramm.setSelected(show_diagramm);
	}
	
	public void set_anzahl_der_elemente() {
		int neue_anzahl_der_elemente = get_textfield_value_int(elektronenPanel.txtAnzahlDerElemente, "");
		
		if(neue_anzahl_der_elemente < MIN_COUNT_ELEMENTS) {
			neue_anzahl_der_elemente = MIN_COUNT_ELEMENTS;
			elektronenPanel.txtAnzahlDerElemente.setText(String.valueOf(neue_anzahl_der_elemente));
			
			JOptionPane.showMessageDialog(null, "Die Anzahl der Elemente darf höchsten "  + MIN_COUNT_ELEMENTS + " betragen", "Fehlerhafte Anzahl an Elementen", JOptionPane.WARNING_MESSAGE);
		} else if(neue_anzahl_der_elemente == MIN_COUNT_ELEMENTS) {
			elektronenPanel.cbDiagramm.setSelected(false);
			elektronenPanel.cbDiagramm.setEnabled(false);
		} else {
			elektronenPanel.cbDiagramm.setEnabled(true);
		}
		
		anzahl_der_elemente 	= neue_anzahl_der_elemente;
		aktuelles_element_index = 0;
		
		set_leeren_speicher();
		set_aktuelles_element_werte();
		check_element_kontroll_btn();
	}
	
	public void set_aktuelles_element_index(boolean naechstes) {
		if(naechstes) {
			if(aktuelles_element_index + 1 == anzahl_der_elemente) {
				aktuelles_element_index = 0;
			} else {
				aktuelles_element_index++;
			}
		} else {
			if(aktuelles_element_index == 0) {
				aktuelles_element_index = anzahl_der_elemente - 1;
			} else {
				aktuelles_element_index--;
			}
		}
		
		set_aktuelles_element_werte();
	}
	
	private void set_aktuelles_element_werte() {
		elektronenPanel.lblCurrentStep.setText( anzahl_der_elemente == 1 ? "Element" : "Element " + (aktuelles_element_index + 1) + " / " + anzahl_der_elemente);
		
		try {
			// values setzten
			double elem_beschleunigungsspanne 	= beschleunigungsspanne.get(aktuelles_element_index);
			double elem_interferenz_radius 		= interferenz_radius.get(aktuelles_element_index);

			elektronenPanel.txtBeschleunigungsspanne.setText( elem_beschleunigungsspanne == EMPTY_VALUE ? "" : String.valueOf(elem_beschleunigungsspanne));
			elektronenPanel.txtInterferenzRadius.setText( elem_interferenz_radius == EMPTY_VALUE ? "" : String.valueOf(elem_interferenz_radius));
		} catch(NullPointerException npe) { }
	}
	
	private void set_leeren_speicher() {
		beschleunigungsspanne 	= new LinkedList<Double>();
		interferenz_radius 		= new LinkedList<Double>();
		for(int i = 0; i < this.anzahl_der_elemente; i++) {
			beschleunigungsspanne.add(EMPTY_VALUE);
			interferenz_radius.add(EMPTY_VALUE);
		}
	}
	
	public void set_beschleunigungsspanne() {
		double number = get_textfield_value_double(elektronenPanel.txtBeschleunigungsspanne, "");
		beschleunigungsspanne.set(aktuelles_element_index, number);
	}
	
	public void set_interferenzradius() {
		double number = get_textfield_value_double(elektronenPanel.txtInterferenzRadius, "");
		interferenz_radius.set(aktuelles_element_index, number);
	}
	
	public void set_laenge() {
		laenge = get_textfield_value_double(elektronenPanel.txtLaenge, "");
	}
	
	public void set_kristallgitter() {
		kristallgitter = get_textfield_value_double(elektronenPanel.txtKristallgitter, "") * Math.pow(10, -ImpulsWellenlaenge.EXPONENT_10ER_POTENZ_KRISTALLGITTER);
	}

	public void set_k() {
		k = get_textfield_value_int(elektronenPanel.txtK, "");
	}
	
	public void set_checkboxen() {
		// Fortlaufende Actionen initialisieren
		save_rechenweg 	= elektronenPanel.cbSave.isSelected();
		show_rechenweg 	= elektronenPanel.cbRechenweg.isSelected();
		show_diagramm 	= elektronenPanel.cbDiagramm.isSelected();
		
		if( show_diagramm && !show_rechenweg
			|| !show_diagramm && show_rechenweg ) {
			elektronenPanel.cbDiagramm.setEnabled(anzahl_der_elemente > MIN_COUNT_ELEMENTS && !show_diagramm);
			elektronenPanel.cbRechenweg.setEnabled(!show_rechenweg);
		} else {
			elektronenPanel.cbDiagramm.setEnabled(anzahl_der_elemente > MIN_COUNT_ELEMENTS);
			elektronenPanel.cbRechenweg.setEnabled(true);
		}
	}
	
	// other
	public void berechnen() {
		if( !check_ob_guelltige_daten() ) {
			JOptionPane.showMessageDialog(null, "Bitte korrigieren Sie Ihre Eingaben.", "Fehlerhafte Eingaben", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ImpulsWellenlaenge impuls_wellenlaenge;
		JSONObject data_json;
		try {
			impuls_wellenlaenge = new ImpulsWellenlaenge(Helper.to_array(beschleunigungsspanne), Helper.to_array(interferenz_radius), kristallgitter, laenge, k);
			data_json = impuls_wellenlaenge.get_json();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Leider ist ein Systemfehler aufgetreten.\nVersuchen Sie es zu einem späteren Zeitpunkt erneut...", "Systemfehler", JOptionPane.ERROR_MESSAGE);
			return;
		}

		//
		if(save_rechenweg) {
			String name = JOptionPane.showInputDialog(null, "Bitte geben Sie eine den Namen der Datei ein (ohne \".json\").\nDie Datei wird in \"" + App.user_folder + "\" gespeichert.\n", "Dateiname", JOptionPane.PLAIN_MESSAGE);
			
			if(name != null) {
				impuls_wellenlaenge.save_json( name + ".json", data_json );
			}
		}
		
		if(show_rechenweg) {
			JSONObject rechenweg_json = data_json;
			EventQueue.invokeLater(() -> start_rechenweg(rechenweg_json));
		}
		
		if(show_diagramm) {
			JSONObject diagramm_json = data_json;
			EventQueue.invokeLater(() -> start_diagramm(diagramm_json));
		}
	}

	// check
	private boolean check_ob_guelltige_daten() {
		// dynamische Daten
		for(int i = 0; i < this.anzahl_der_elemente; i++) {
			if(beschleunigungsspanne.get(i) == EMPTY_VALUE
				|| interferenz_radius.get(i) == EMPTY_VALUE) {
				return false;
			}
		}
		
		// statische Daten
		if(laenge == EMPTY_VALUE
			|| kristallgitter == EMPTY_VALUE
			|| k == EMPTY_VALUE) {
			return false;
		}

		return true;
	}
	
	private void check_element_kontroll_btn() {
		if(anzahl_der_elemente == 1) {
			elektronenPanel.btn_weiter.setVisible(false);
			elektronenPanel.btn_zurück.setVisible(false);
		} else {
			elektronenPanel.btn_weiter.setVisible(true);
			elektronenPanel.btn_zurück.setVisible(true);
		}
	}
	
	// getter (private)
	private int get_textfield_value_int(JTextField txtField, String default_value) {
		try {
			return Integer.parseInt( txtField.getText() );
		} catch (Exception e) {
			txtField.setText(default_value);
			return (int) EMPTY_VALUE;
		}
	}
	
	private double get_textfield_value_double(JTextField txtField, String default_value) {
		try {
			String value 	= txtField.getText();
			value 			= value.replace(",", ".");  
			return Double.parseDouble( value );
		} catch (Exception e) {
			txtField.setText(default_value);
			return EMPTY_VALUE;
		}
	}
	
	// start
	private void start_diagramm(JSONObject data_json)  {
		try {
			DiagrammGUI iwd = new DiagrammGUI(data_json);
			iwd.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void start_rechenweg(JSONObject data_json)  {
		try {
			RechenwegGUI iwr = new RechenwegGUI(data_json);
			iwr.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
