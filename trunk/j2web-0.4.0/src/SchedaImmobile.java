/*
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda immobile
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;


public class SchedaImmobile implements Serializable, parametriGenerali  {
	private static final long serialVersionUID = 1L;
	
	//Attributi della scheda immobile	
	long idScheda = new Date().getTime();	//id univoco riferito alla scheda
	
	//Campi testuali
	String codiceInserzione;
	String titoloAnnuncio;
	String cap;
	String indirizzoLocalita;
	String superficieImmobile;
	String prezzoImmobile;
	String numeroTotalePiani;
	String annoCostruzione; //8
	//File
	File immagine1;
	File immagine2;
	File immagine3;
	File immagine4;
	File immagine5;
	File immagine6;	
	File immagine7;
	File immagine8;
	File immagine9;
	File immagine10; //10
	//Textarea
	String testoAnnuncio; //1
	//Combobox
	String regione;
	String provincia;
	String comune;
	String categoriaImmobile;
	String tipologiaImmobile;
	String tipologiaContratto; //6
	
	String numeroLocali;
	String numeroCamere;
	String numeroBagni;
	String statoImmobile;
	String arredamenti;
	String piano;
	String certificazioniEnergetiche;
	String tipologiaRiscaldamento;
	String clima;
	String parcheggio;
	String giardino; //11
	//Checkbox
	boolean bandaLarga;
	boolean ascensore;
	boolean casaEcologica;
	boolean vicinanzeBus;
	boolean vistaDiPregio;
	boolean satellite;
	boolean sistemaDiAllarme;
	boolean cancelloElettrico;
	boolean vicinanzeMetro;
	boolean rampePerDisabili;  //10
	//TOT:46
	
	//Percorso file dat per la scheda
	String schedaDatPath = "./schede/" + codiceInserzione + "-" + idScheda + ".dat";

	//Una scheda immobile può essere ospitata in diversi portali, la seguente tabella hash contiene i codici dei portali(key) e il codice di inserimento(value) in cui la scheda è attualmente inserita
	Map<String,String> mappaPortaliOspitanti = new Hashtable<String,String>();
	
	//Costruttore
	@SuppressWarnings("rawtypes")
	public SchedaImmobile () {	 	
	
		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		//Textfield
		codiceInserzione = (String)((JTextField)mapCampiForm.get("textFieldCodiceInserzione")).getText().trim();
		if(codiceInserzione.length()>10) {
			codiceInserzione.substring(0, 9);
		}
		titoloAnnuncio = (String)((JTextField)mapCampiForm.get("textFieldTitoloAnnuncio")).getText().trim();
		if(titoloAnnuncio.length()>150) {
			titoloAnnuncio.substring(0, 149);
		}
		cap = (String)((JTextField)mapCampiForm.get("textFieldCap")).getText().trim();
		if(cap.length()>7) {
			cap.substring(0, 6);
		}
		indirizzoLocalita = (String)((JTextField)mapCampiForm.get("textFieldIndirizzoLocalita")).getText().trim();
		if(indirizzoLocalita.length()>70) {
			indirizzoLocalita.substring(0, 69);
		}
		superficieImmobile = (String)((JTextField)mapCampiForm.get("textFieldSuperficieImmobile")).getText().trim();
		if(superficieImmobile.length()>10) {
			superficieImmobile.substring(0, 14);
		}
		prezzoImmobile = (String)((JTextField)mapCampiForm.get("textFieldPrezzoImmobile")).getText().trim();
		if(prezzoImmobile.length()>15) {
			prezzoImmobile.substring(0, 14);
		}
		numeroTotalePiani = (String)((JTextField)mapCampiForm.get("textFieldNumeroTotalePiani")).getText().trim();
		if(numeroTotalePiani.length()>2) {
			numeroTotalePiani.substring(0, 1);
		}
		annoCostruzione = (String)((JTextField)mapCampiForm.get("textFieldAnnoCostruzione")).getText().trim();
		if(annoCostruzione.length()>4) {
			annoCostruzione.substring(0, 3);
		}
		//Immagini
		immagine1 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine1")).getText());
		immagine2 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine2")).getText());
		immagine3 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine3")).getText());
		immagine4 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine4")).getText());
		immagine5 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine5")).getText());
		immagine6 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine6")).getText());
		immagine7 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine7")).getText());
		immagine8 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine8")).getText());
		immagine9 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine9")).getText());
		immagine10 = new File((String)((JTextField)mapCampiForm.get("textFieldImmagine10")).getText());
		//TextArea
		testoAnnuncio = (String)((JTextArea)mapCampiForm.get("textAreaTestoAnnuncio")).getText().trim();
		if(testoAnnuncio.length()>2000) {
			testoAnnuncio.substring(0, 1999);
		}
		//Combo box
		regione = ((String)((JComboBox)mapCampiForm.get("comboBoxRegione")).getSelectedItem());
		provincia = ((String)((JComboBox)mapCampiForm.get("comboBoxProvincia")).getSelectedItem());
		comune = ((String)((JComboBox)mapCampiForm.get("comboBoxComune")).getSelectedItem());
		categoriaImmobile = ((String)((JComboBox)mapCampiForm.get("comboBoxCategoriaImmobile")).getSelectedItem());
		tipologiaImmobile = ((String)((JComboBox)mapCampiForm.get("comboBoxTipologiaImmobile")).getSelectedItem());
		tipologiaContratto = ((String)((JComboBox)mapCampiForm.get("comboBoxTipologiaContratto")).getSelectedItem());
		numeroLocali = ((String)((JComboBox)mapCampiForm.get("comboBoxNumeroLocali")).getSelectedItem());
		numeroCamere = ((String)((JComboBox)mapCampiForm.get("comboBoxNumeroCamere")).getSelectedItem());
		numeroBagni = ((String)((JComboBox)mapCampiForm.get("comboBoxNumeroBagni")).getSelectedItem());
		statoImmobile = ((String)((JComboBox)mapCampiForm.get("comboBoxStatoImmobile")).getSelectedItem());
		arredamenti = ((String)((JComboBox)mapCampiForm.get("comboBoxArredamenti")).getSelectedItem());
		piano = ((String)((JComboBox)mapCampiForm.get("comboBoxPiano")).getSelectedItem());
		certificazioniEnergetiche = ((String)((JComboBox)mapCampiForm.get("comboBoxCertificazioniEnergetiche")).getSelectedItem());
		tipologiaRiscaldamento = ((String)((JComboBox)mapCampiForm.get("comboBoxTipologiaRiscaldamento")).getSelectedItem());
		clima = ((String)((JComboBox)mapCampiForm.get("comboBoxClima")).getSelectedItem());
		parcheggio = ((String)((JComboBox)mapCampiForm.get("comboBoxParcheggio")).getSelectedItem());
		giardino = ((String)((JComboBox)mapCampiForm.get("comboBoxGiardino")).getSelectedItem());
		//Check box
		bandaLarga = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		ascensore = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		casaEcologica = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		vicinanzeBus = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		vistaDiPregio = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		satellite = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		sistemaDiAllarme = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		cancelloElettrico = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		vicinanzeMetro = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		rampePerDisabili = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		    
	}

	
	//Metodi
	
	//Carica tabella quando premi il radio button relativo alla scheda
	@SuppressWarnings("unchecked")
	public void caricaMappaPortaliOspitanti() {
		
		//Lettura schede dal file .dat
        File file = new File(schedaDatPath);
    	if(file.exists()) {
    		System.out.println("File .dat scheda trovato." + schedaDatPath);
    		try {
    			if(file.length()!=0) {
    				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
    				mappaPortaliOspitanti = (Hashtable<String,String>)inputFile.readObject();
					inputFile.close();
    			}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File hash non trovato: impossibile caricare la hashtable", "Errore", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al file hash: impossibile caricare la hashtable", "Errore", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Errore generico", "Errore", JOptionPane.ERROR_MESSAGE);
			}   		
    	}
    	else {
    		System.out.println("File hash non trovato.");
    		try {
				FileOutputStream newFile = new FileOutputStream(schedaDatPath);
				System.out.println("File hash non trovato. Creazione del file...: " + newFile.toString());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File hash non trovato: impossibile caricare la hash table", "Errore", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    	}
	}
	
	
	//Verifico la presenza della scheda immobile in un dato portale
	public boolean isOnThisPortal(String idPortale) {
		System.out.println("idportale: " + idPortale);
		if(mappaPortaliOspitanti.containsKey(idPortale)) {
			System.out.println("Scheda presente nel portale: " + idPortale);
			return true; 
		}
		else {
			System.out.println("La scheda non è presente nel portale: " + idPortale);
			return false;
		}
	}
	
	
	//Aggiorno la lista dei portali in cui la scheda è inserita (nuovo inserimento)
	public void aggiungiInserimentoPortale(String idPortale, String codiceInserzione) {
		mappaPortaliOspitanti.put(idPortale, codiceInserzione);
				
		//Salvataggio tabella
        try {
 		   File file = new File(schedaDatPath);
 	    	if(file.exists()) {
 	    		System.out.println("File hash scheda trovato.");
 	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
					outputFile.writeObject(mappaPortaliOspitanti);
					outputFile.close();
 	    	}
 	    	else {
 	    		System.out.println("File hash scheda non trovato.");
 	    	}
			} catch (FileNotFoundException e0) {
	            JOptionPane.showMessageDialog(null, "File hash scheda non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
	            e0.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al file hash scheda: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}	
	}

	
	//Aggiorno la lista dei portali in cui la scheda è inserita (cancellazione)	
	public void eliminaInserimentoPortale(String idPortale) {
		//Aggiorno la lista
		mappaPortaliOspitanti.remove(idPortale);
		
		//Salvataggio tabella
        try {
 		   File file = new File(schedaDatPath);
 	    	if(file.exists()) {
 	    		System.out.println("File hash scheda trovato.");
 	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
					outputFile.writeObject(mappaPortaliOspitanti);
					outputFile.close();
 	    	}
 	    	else {
 	    		System.out.println("File hash scheda non trovato.");
 	    	}
			} catch (FileNotFoundException e0) {
	            JOptionPane.showMessageDialog(null, "File hash scheda non trovato: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
	            e0.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al file .hash scheda: impossibile caricare le schede precedentemente inserite", "Errore", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}      
	}
	
	
	//Ottenere il codice di inserimento di una scheda tramite l'id del portale (cancellazione)	
	public String getCodiceInserimento(String idPortale) {		
		String codiceInserimento = "";
		if(mappaPortaliOspitanti.containsKey(idPortale)) {
			codiceInserimento = mappaPortaliOspitanti.get(idPortale);
		}
		return codiceInserimento;
	}
			
}