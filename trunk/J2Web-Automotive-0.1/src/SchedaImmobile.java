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
import java.util.Comparator;
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
	File immagine1;	File immagine2;	File immagine3;	File immagine4;	File immagine5;	File immagine6;	File immagine7;	File immagine8;	File immagine9;	File immagine0; 	
	File[] arrayImages = {immagine1, immagine2, immagine3, immagine4, immagine5, immagine6, immagine7, immagine8, immagine9, immagine0};
	//10
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
	String tipologiaCucina;
	String statoImmobile;
	String arredamenti;
	String piano;
	String certificazioniEnergetiche;
	String certificazioneIpe;
	String tipologiaRiscaldamento;
	String clima;
	String parcheggio;
	String giardino; //12
	//Checkbox
	boolean bandaLarga;
	boolean ascensore;
	//boolean casaEcologica;
	//boolean vicinanzeBus;
	boolean cantina;
	boolean satellite;
	boolean sistemaDiAllarme;
	boolean cancelloElettrico;
	//boolean vicinanzeMetro;
	boolean vicinanzeMezziPubblici;
	boolean rampePerDisabili;  //8
	//TOT:46
	
	//Gli indici delle combobox 
	int comboBoxRegioneIndex;
	int comboBoxProvinciaIndex;
	int comboBoxComuneIndex;
	int comboBoxCategoriaImmobileIndex;
	int comboBoxTipologiaImmobileIndex;
	int comboBoxTipologiaContrattoIndex;
	int comboBoxNumeroLocaliIndex;
	int comboBoxNumeroCamereIndex;
	int comboBoxNumeroBagniIndex;
	int comboBoxTipologiaCucinaIndex;
	int comboBoxStatoImmobileIndex;
	int comboBoxArredamentiIndex;
	int comboBoxPianoIndex;
	int comboBoxCertificazioniEnergeticheIndex;
	int comboBoxTipologiaRiscaldamentoIndex;
	int comboBoxClimaIndex;
	int comboBoxParcheggioIndex;
	int comboBoxGiardinoIndex;

	//Percorso file dat per la scheda
	String singolaSchedaDatPath;

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
		for(int i=0; i<arrayImages.length; i++) {
			String imgPath = (String)((JTextField)mapCampiForm.get("textFieldImmagine" + (i+1))).getText();
			File imgFile = new File(imgPath);
			if(imgFile.exists()) {
				arrayImages[i] = imgFile;	//tutte le altre sono null
			}
		}
		
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
		
		tipologiaCucina = ((String)((JComboBox)mapCampiForm.get("comboBoxCucina")).getSelectedItem());
		
		statoImmobile = ((String)((JComboBox)mapCampiForm.get("comboBoxStatoImmobile")).getSelectedItem());
		
		arredamenti = ((String)((JComboBox)mapCampiForm.get("comboBoxArredamenti")).getSelectedItem());
		
		piano = ((String)((JComboBox)mapCampiForm.get("comboBoxPiano")).getSelectedItem());
		
		certificazioniEnergetiche = ((String)((JComboBox)mapCampiForm.get("comboBoxCertificazioniEnergetiche")).getSelectedItem());
		
		certificazioneIpe = ((String)((JComboBox)mapCampiForm.get("comboBoxCertificazioneIpe")).getSelectedItem());
		
		tipologiaRiscaldamento = ((String)((JComboBox)mapCampiForm.get("comboBoxTipologiaRiscaldamento")).getSelectedItem());
		
		clima = ((String)((JComboBox)mapCampiForm.get("comboBoxClima")).getSelectedItem());
		
		parcheggio = ((String)((JComboBox)mapCampiForm.get("comboBoxParcheggio")).getSelectedItem());
		
		giardino = ((String)((JComboBox)mapCampiForm.get("comboBoxGiardino")).getSelectedItem());
		
		//Check box
		bandaLarga = (((JCheckBox) mapCampiForm.get("chckbxBandaLarga")).isSelected())?true:false;
		
		ascensore = (((JCheckBox) mapCampiForm.get("chckbxAscensore")).isSelected())?true:false;
		
		//casaEcologica = (((JCheckBox) mapCampiForm.get("chckbxCasaEcologica")).isSelected())?true:false;
		
		//vicinanzeBus = (((JCheckBox) mapCampiForm.get("chckbxVicinanzeBus")).isSelected())?true:false;
		
		cantina = (((JCheckBox) mapCampiForm.get("chckbxCantina")).isSelected())?true:false;
		
		satellite = (((JCheckBox) mapCampiForm.get("chckbxSatellite")).isSelected())?true:false;
		
		sistemaDiAllarme = (((JCheckBox) mapCampiForm.get("chckbxSistemaDiAllarme")).isSelected())?true:false;
		
		cancelloElettrico = (((JCheckBox) mapCampiForm.get("chckbxCancelloElettrico")).isSelected())?true:false;
		
		//vicinanzeMetro = (((JCheckBox) mapCampiForm.get("chckbxVicinanzeMetro")).isSelected())?true:false;
		vicinanzeMezziPubblici = (((JCheckBox) mapCampiForm.get("chckbxVicinanzeMezziPubblici")).isSelected())?true:false;

		rampePerDisabili = (((JCheckBox) mapCampiForm.get("chckbxRampePerDisabili")).isSelected())?true:false;
		
		
		//Inizializzo il path per il file hash di questa scheda
		singolaSchedaDatPath = ".\\schede\\" + codiceInserzione + "-" + idScheda + ".dat";
		
		//Salvo gli indici delle select
		comboBoxRegioneIndex=((int)((JComboBox)mapCampiForm.get("comboBoxRegione")).getSelectedIndex());
		comboBoxProvinciaIndex=((int)((JComboBox)mapCampiForm.get("comboBoxProvincia")).getSelectedIndex());
		comboBoxComuneIndex=((int)((JComboBox)mapCampiForm.get("comboBoxComune")).getSelectedIndex());
		comboBoxCategoriaImmobileIndex=((int)((JComboBox)mapCampiForm.get("comboBoxCategoriaImmobile")).getSelectedIndex());
		comboBoxTipologiaImmobileIndex=((int)((JComboBox)mapCampiForm.get("comboBoxTipologiaImmobile")).getSelectedIndex());
		comboBoxTipologiaContrattoIndex=((int)((JComboBox)mapCampiForm.get("comboBoxTipologiaContratto")).getSelectedIndex());
		comboBoxNumeroLocaliIndex=((int)((JComboBox)mapCampiForm.get("comboBoxNumeroLocali")).getSelectedIndex());
		comboBoxNumeroCamereIndex=((int)((JComboBox)mapCampiForm.get("comboBoxNumeroCamere")).getSelectedIndex());
		comboBoxNumeroBagniIndex=((int)((JComboBox)mapCampiForm.get("comboBoxNumeroBagni")).getSelectedIndex());
		comboBoxTipologiaCucinaIndex=((int)((JComboBox)mapCampiForm.get("comboBoxCucina")).getSelectedIndex());
		comboBoxStatoImmobileIndex=((int)((JComboBox)mapCampiForm.get("comboBoxStatoImmobile")).getSelectedIndex());
		comboBoxArredamentiIndex=((int)((JComboBox)mapCampiForm.get("comboBoxArredamenti")).getSelectedIndex());
		comboBoxPianoIndex=((int)((JComboBox)mapCampiForm.get("comboBoxPiano")).getSelectedIndex());
		comboBoxCertificazioniEnergeticheIndex=((int)((JComboBox)mapCampiForm.get("comboBoxCertificazioniEnergetiche")).getSelectedIndex());
		comboBoxTipologiaRiscaldamentoIndex=((int)((JComboBox)mapCampiForm.get("comboBoxTipologiaRiscaldamento")).getSelectedIndex());
		comboBoxClimaIndex=((int)((JComboBox)mapCampiForm.get("comboBoxClima")).getSelectedIndex());
		comboBoxParcheggioIndex=((int)((JComboBox)mapCampiForm.get("comboBoxParcheggio")).getSelectedIndex());
		comboBoxGiardinoIndex=((int)((JComboBox)mapCampiForm.get("comboBoxGiardino")).getSelectedIndex());
	}

	
	//Metodi
	
	//Carica tabella quando premi il radio button relativo alla scheda
	@SuppressWarnings("unchecked")
	public void caricaTabellaHash() {
		
		//Lettura schede dal file .dat
		System.out.print("TEST: " + singolaSchedaDatPath);
        File file = new File(singolaSchedaDatPath);
    	if(file.exists()) {
    		System.out.print("File hash scheda trovato. Lettura dati da " + singolaSchedaDatPath);
    		try {
    			if(file.length()!=0) {
    				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
    				mappaPortaliOspitanti = (Hashtable<String,String>)inputFile.readObject();
					inputFile.close();					
    			}
    			System.out.print(" fatto." + "\n");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash_FileNotFoundException"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash_IOException"), "IOException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash_ClassNotFoundException"), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
			} 		
    	}
    	else {
    		try {
				FileOutputStream newFile = new FileOutputStream(singolaSchedaDatPath);
				System.out.print("File hash non trovato. Creazione di un nuovo file hash per questa scheda..." + newFile.toString());
				System.out.print(" fatto." + "\n");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash_FileNotFoundException"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    	}
	}
	
	
	//Verifico la presenza della scheda immobile in un dato portale
	public boolean isOnThisPortal(String idPortale) {
		if(mappaPortaliOspitanti.containsKey(idPortale)) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	
	//Aggiorno la lista dei portali in cui la scheda è inserita (nuovo inserimento)
	public void aggiungiInserimentoPortale(String idPortale, String codiceInserzione) {
		mappaPortaliOspitanti.put(idPortale, codiceInserzione);
				
		//Salvataggio tabella hash
		salvaTabellaHash(singolaSchedaDatPath, mappaPortaliOspitanti);
	}

	
	//Aggiorno la lista dei portali in cui la scheda è inserita (cancellazione)	
	public void eliminaInserimentoPortale(String idPortale) {
		//Aggiorno la lista
		mappaPortaliOspitanti.remove(idPortale);
		
		//Salvataggio tabella
		salvaTabellaHash(singolaSchedaDatPath, mappaPortaliOspitanti);
		    
	}
	
	
	public void salvaTabellaHash(String schedaDatPath, Map<String,String> mappaPortaliOspitanti) {
		try {
 		   File file = new File(schedaDatPath);
	    	if(file.exists()) {
	    		System.out.print("File hash scheda trovato. Salvataggio dati su " + schedaDatPath);
	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(mappaPortaliOspitanti);
				outputFile.close();
				System.out.print(" fatto." + "\n");
	    	}
	    	else {
	    		//La tabella hash è creata in ogni caso al momento della prima lettura della stessa
	    		System.out.println("File hash scheda non trovato.");
	    	}
		} catch (FileNotFoundException e0) {		
	        JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaTabellaHash_FileNotFoundException"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
	        e0.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaTabellaHash_IOException"), "IOException", JOptionPane.ERROR_MESSAGE);
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











//Classi Comparator per l'ordinamento delle schede secondo specifici criteri
class IdComparator implements Comparator<SchedaImmobile> {

    public int compare(SchedaImmobile s1, SchedaImmobile s2) {
        if (s1.idScheda > s2.idScheda)
            return 1;
        else if (s1.idScheda < s2.idScheda)
            return -1;
        else
            return 0;
    }
}


class CodeComparator implements Comparator<SchedaImmobile>  {
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) { 	
    	int i = s1.codiceInserzione.compareTo(s2.codiceInserzione); 
    	System.out.println("i:" + i);
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}


class CityComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.comune.compareTo(s2.comune); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}


class ProvinceComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.provincia.compareTo(s2.provincia); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}


class RegionComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.regione.compareTo(s2.regione); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}