import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ListIterator;
import java.awt.FlowLayout;


public class j2web_GUI extends JFrame implements parametriGenerali {
	/**
	 * 
	 */
	
	//Alcuni campi devono essere inseriti al di fuori del costruttore per essere visibili all'intera classe
	private static final long serialVersionUID = 1L;
	
	
	
	
	static PanelCreazioneSchedeImmobile panelCreazioneSchedeImmobile;
	static PannelloListaSchedeImmobili panelListaSchedeImmobile;
	static PanelInserimentoImmobiliInPortali panelInserimentoImmobiliInPortali;

	/**
	 * Create the frame.
	 */
	public j2web_GUI() {
				
		//Proprietà della GUI
	    setIconImage(frameIcon);    
	    setTitle(nomeGUI);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GUI_bounds[0], GUI_bounds[1], GUI_bounds[2], GUI_bounds[3]);	
		
		/*Menu... in stand-by*/
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar_1.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		/*Menu*/	
		
		
		//Pannello SX - "Creazione scheda immobile"
		panelCreazioneSchedeImmobile = new PanelCreazioneSchedeImmobile();
		panelCreazioneSchedeImmobile.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creazione schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelCreazioneSchedeImmobile);
		//panelCreazioneSchedeImmobile.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//panelCreazioneSchedeImmobile.setLayout(new BorderLayout(0, 0));
		//panelCreazioneSchedeImmobile.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		//Pannello MID - "Lista schede create"
		panelListaSchedeImmobile = new PannelloListaSchedeImmobili();
		panelListaSchedeImmobile.setBorder(new TitledBorder(null, "Lista schede create", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelListaSchedeImmobile);
		panelListaSchedeImmobile.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Pannello DX - "Inserimento schede immobile"
		panelInserimentoImmobiliInPortali = new PanelInserimentoImmobiliInPortali();
		panelInserimentoImmobiliInPortali.setBorder(new TitledBorder(null, "Inserimento schede immobile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelInserimentoImmobiliInPortali);
		panelInserimentoImmobiliInPortali.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
	}
	
	//Popola la combobox Provincia quando viene modificata la Regione
	/*static void popolaComboBoxProvincia(String regione) {
		//Rimuovo tutte le opzioni della select Provincia
		//comboBoxProvincia.removeAllItems();
		//Rimuovo tutte le opzioni della select Comune
		comboBoxComune.removeAllItems();
		if(!regione.equals("Seleziona la Regione")) {
			System.out.println("Regione: " + regione);
			comboBoxProvincia.setModel(new DefaultComboBoxModel<String>(regioneProvincie.get(regione)));
		}	
	}*/
		
	//Popola la combobox Comune quando viene modificata la Provincia
	/*static void popolaComboBoxComune(String provincia) {
		if(!provincia.equals("Seleziona la Provincia")) {
			System.out.println("Provincia: " + provincia);
			comboBoxComune.setModel(new DefaultComboBoxModel<String>(provinciaComuni.get(provincia)));
		}
	}*/
	
	//Popola la combobox Categoria immobile quando viene modificata la Tipologia immobile
	/*static void popolaComboBoxTipologiaImmobile(String categoria) {
			System.out.println("Categoria: " + categoria);
			switch (categoria)
        	{
    	    case "Residenziale":
    	    	comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayTipologieImmobiliResidenziali));
    	        break;
    	    case "Commerciale":
    	    	comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayTipologieImmobiliCommerciali));
    	    	break;
    	    case "Industriale":
    	    	comboBoxTipologiaImmobile.setModel(new DefaultComboBoxModel<String>(arrayTipologieImmobiliIndustriali));
    	    	break;
    	    default:
    	    	comboBoxTipologiaImmobile.removeAllItems();            	    	
        	}
	}*/
	
	//Seleziona e carica le immagini nella form
	static void selezionaImmagine(JTextField relatedTextField) {
		JFileChooser dlgFile;
        String absPath;
        
        //Selezione del file immagine
        dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	//Controllo il formato del file
        	File selectedFile = dlgFile.getSelectedFile(); 
        	Long fileSize = selectedFile.length();                    	
        	String selectedFileName = selectedFile.getName().toLowerCase();                   	
            if(selectedFile.isFile() && selectedFileName.endsWith(format) && fileSize<=maxFileSize) {
                absPath = selectedFile.getAbsolutePath();
                relatedTextField.setText(absPath);
                relatedTextField.setEnabled(false);
            }
            else {
            	JOptionPane.showMessageDialog(null, "Formato di file non valido: le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
            }	
        }
	}

	//Resetta la form
	@SuppressWarnings("unchecked")
	static void resettaForm() {
		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = (JComponent)iteratorListCampiForm.next();
			switch (campoCorrente.getClass().getName())
			{
			    case "javax.swing.JTextField": //Campo testuale
			    	((JTextComponent) campoCorrente).setText("");
			    	campoCorrente.setEnabled(true);
			        break;
			    case "javax.swing.JTextArea": //Text Area
			    	((JTextComponent) campoCorrente).setText("");
			    	((JTextComponent) campoCorrente).setEnabled(true);
			        break;
			    case "javax.swing.JComboBox": //Select
			    	if(((JComboBox<String>) campoCorrente).getItemCount()>0) {	//se la checkbox è popolata...
			    		((JComboBox<String>) campoCorrente).setSelectedIndex(0);
			    	}
			    	((JComboBox<String>) campoCorrente).setEnabled(true);
			        break;
			    case "javax.swing.JButton": //Pulsante
			    	((JButton) campoCorrente).setEnabled(true);
			        break;
			    case "javax.swing.JCheckBox": //Checkbox
			    	((JCheckBox) campoCorrente).setSelected(false);
			    	((JCheckBox) campoCorrente).setEnabled(true);
			        break;
			    default://
			}
		}	
	}
	
	//Disabilita i campi della form
	//@SuppressWarnings("unchecked")
	static void disabilitaCampiForm() {
		ListIterator<JComponent> iteratorListCampiForm = listCampiForm.listIterator();
		while(iteratorListCampiForm.hasNext()) {
			JComponent campoCorrente = (JComponent)iteratorListCampiForm.next();
			campoCorrente.setEnabled(false);
			/*switch (campoCorrente.getClass().getName())
			{
			    case "javax.swing.JTextField": //Campo testuale
			    	((JTextComponent) campoCorrente).setEnabled(false);
			        break;
			    case "javax.swing.JTextArea": //Text Area
			    	((JTextComponent) campoCorrente).setEnabled(false);
			        break;
			    case "javax.swing.JComboBox": //Select
			    	((JComboBox<String>) campoCorrente).setEnabled(false);
			        break;
			    case "javax.swing.JButton": //Pulsante
			    	((JButton) campoCorrente).setEnabled(false);
			        break;
			    case "javax.swing.JCheckBox": //Checkbox
			    	((JCheckBox) campoCorrente).setEnabled(false);
			        break;
			    default://
			}*/
		}
	}
	
	//Verifica la validità della form
	@SuppressWarnings("unchecked")
	static boolean isFormValid() {
		
		String comboContent;
		boolean campiObbligatoriCompilati = true;
		
		//Controllo i campi testuali
		if(((JTextField)mapCampiForm.get("textFieldCodiceInserzione")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		if(((JTextField)mapCampiForm.get("textFieldTitoloAnnuncio")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		if(((JTextField)mapCampiForm.get("textFieldCap")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		if(((JTextField)mapCampiForm.get("textFieldIndirizzoLocalita")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		if(((JTextField)mapCampiForm.get("textFieldSuperficieImmobile")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		if(((JTextField)mapCampiForm.get("textFieldPrezzoImmobile")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		
		//Controllo la text area
		if(((JTextArea)mapCampiForm.get("textAreaTestoAnnuncio")).getText().isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		
		//Controllo le checkbox
		comboContent = ((String)((JComboBox<String>)mapCampiForm.get("comboBoxRegione")).getSelectedItem());
		if(comboContent.isEmpty() || comboContent.equals(arrayRegioni[0])) {
			campiObbligatoriCompilati = false;
		}
		comboContent = ((String)((JComboBox<String>)mapCampiForm.get("comboBoxProvincia")).getSelectedItem());
		if(comboContent==null || comboContent.isEmpty() || comboContent.equals(arrayProvincie[0])) {
			campiObbligatoriCompilati = false;
		}
		comboContent = ((String)((JComboBox<String>)mapCampiForm.get("comboBoxComune")).getSelectedItem());
		if(comboContent==null || comboContent.isEmpty()) {
			campiObbligatoriCompilati = false;
		}
		comboContent = ((String)((JComboBox<String>)mapCampiForm.get("comboBoxCategoriaImmobile")).getSelectedItem());
		if(comboContent==null || comboContent.isEmpty() || comboContent.equals(arrayCategorieImmobili[0])) {
			campiObbligatoriCompilati = false;
		}
		comboContent = ((String)((JComboBox<String>)mapCampiForm.get("comboBoxTipologiaImmobile")).getSelectedItem());
		if(comboContent==null || comboContent.isEmpty() || comboContent.equals(arrayTipologie[0])) {
			campiObbligatoriCompilati = false;
		}
		comboContent = ((String)((JComboBox<String>)mapCampiForm.get("comboBoxTipologiaContratto")).getSelectedItem());
		if(comboContent==null || comboContent.isEmpty() || comboContent.equals(arrayTipologie[0])) {
			campiObbligatoriCompilati = false;
		}
		
		//Se tutti i campi obbligatori hanno dati validi
       if(campiObbligatoriCompilati) {  	
           return true;
       }
       else {               	           
           return false;
       }
		
	}

	//Il nuovo oggetto scheda immobile viene inserito nella struttura dati e salvato nel file .dat relativo a tutte le schede
	static void aggiungiSchedaInDat(SchedaImmobile scheda) {
	
	//Aggiorno la lista delle schede immobile
	listSchedeImmobile.add(scheda);
		
	//Aggiorno il file dat delle schede
    try {
	   File file = new File(datFilePath);
    	if(file.exists()) {
    		System.out.println("File .dat trovato.");
    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(listSchedeImmobile);
				outputFile.close();
    	}
    	else {
				FileOutputStream newFile = new FileOutputStream(datFilePath);
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(listSchedeImmobile);
				outputFile.close();
				System.out.println("File .dat non trovato. Creazione del file...: " + newFile.toString());
    	}
		} catch (FileNotFoundException e0) {
            JOptionPane.showMessageDialog(null, "File .dat non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
            e0.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Impossibile accedere al file .dat: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
}