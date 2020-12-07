package gui.filechooser.impulswellenlaenge;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadFile extends JFileChooser {
	private static final long serialVersionUID = -8412299663743793005L;

	
	public LoadFile() {
		setDialogType(JFileChooser.OPEN_DIALOG);
        setFileSelectionMode(JFileChooser.FILES_ONLY); 
        setDialogTitle("Datei laden...");
        setVisible(true);
        
        FileNameExtensionFilter json_filtet = new FileNameExtensionFilter("JSON-Datei: .json", "json");
        removeChoosableFileFilter( getAcceptAllFileFilter() );
        setFileFilter(json_filtet);
	}
}
