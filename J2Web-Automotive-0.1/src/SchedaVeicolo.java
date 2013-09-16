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


public class SchedaVeicolo implements Serializable, parametriGenerali  {
	
	private static final long serialVersionUID = 1L;
	
	//Attributi della scheda immobile	
	long idScheda = new Date().getTime();	//id univoco riferito alla scheda
	String codiceScheda= "_veicolo_";
	
	String marcaVeicolo;
	
	//File
	File immagine1;	File immagine2;	File immagine3;	File immagine4;	File immagine5;	File immagine6;	File immagine7;	File immagine8;	File immagine9;	File immagine0; 	
	File[] arrayImages = {immagine1, immagine2, immagine3, immagine4, immagine5, immagine6, immagine7, immagine8, immagine9, immagine0};
	

	//Percorso file dat per la scheda
	String singolaSchedaDatPath;

	//Una scheda immobile può essere ospitata in diversi portali, la seguente tabella hash contiene i codici dei portali(key) e il codice di inserimento(value) in cui la scheda è attualmente inserita
	Map<String,String> mappaPortaliOspitanti = new Hashtable<String,String>();
	
	//Costruttore
	@SuppressWarnings("rawtypes")
	public SchedaVeicolo () {	 	
	
		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		String veicolo = J2Web_UI.getRdbtnAutoveicolo().isSelected()?"auto":"moto";
		
		String tipologiaVeicolo = (String) J2Web_UI.getComboBox_Tipologia().getSelectedItem();
		int tipologiaVeicoloIndex =J2Web_UI.getComboBox_Tipologia().getSelectedIndex();
		marcaVeicolo = (String) J2Web_UI.getComboBox_Marca().getSelectedItem();
		int marcaVeicoloIndex =J2Web_UI.getComboBox_Marca().getSelectedIndex();
		String carrozzeriaVeicolo = (String) J2Web_UI.getComboBox_Carrozzeria().getSelectedItem();
		int carrozzeriaVeicoloIndex =J2Web_UI.getComboBox_Carrozzeria().getSelectedIndex();
		String postiASedereVeicolo = (String) J2Web_UI.getComboBox_PostiASedere().getSelectedItem();
		int postiASedereVeicoloIndex =J2Web_UI.getComboBox_PostiASedere().getSelectedIndex();
		String finitureInterneVeicolo = (String) J2Web_UI.getComboBox_FinitureInterni().getSelectedItem();
		int finitureInterneVeicoloIndex =J2Web_UI.getComboBox_FinitureInterni().getSelectedIndex();
		String coloreInterniVeicolo = (String) J2Web_UI.getComboBox_ColoreInterni().getSelectedItem();
		int coloreInterniVeicoloIndex =J2Web_UI.getComboBox_ColoreInterni().getSelectedIndex();
		String versioneVeicolo = (String) J2Web_UI.getComboBox_Versione().getSelectedItem();
		int versioneVeicoloIndex =J2Web_UI.getComboBox_Versione().getSelectedIndex();
		String meseImmatricolazioneVeicolo = (String) J2Web_UI.getComboBox_MeseImmatricolazione().getSelectedItem();
		int meseImmatricolazioneVeicoloIndex =J2Web_UI.getComboBox_MeseImmatricolazione().getSelectedIndex();
		String annoImmatricolazioneVeicolo = (String) J2Web_UI.getComboBox_AnnoImmatricolazione().getSelectedItem();
		int annoImmatricolazioneVeicoloIndex =J2Web_UI.getComboBox_AnnoImmatricolazione().getSelectedIndex();
		String coloreEsternoVeicolo = (String) J2Web_UI.getComboBox_ColoreEsterno().getSelectedItem();
		int coloreEsternoVeicoloIndex =J2Web_UI.getComboBox_ColoreEsterno().getSelectedIndex();
		String numeroPrecedentiProprietariVeicolo = (String) J2Web_UI.getComboBox_PrecedentiProprietari().getSelectedItem();
		int numeroPrecedentiProprietariVeicoloIndex =J2Web_UI.getComboBox_PrecedentiProprietari().getSelectedIndex();
		String tipologiaCambioVeicolo = (String) J2Web_UI.getComboBox_Cambio().getSelectedItem();
		int tipologiaCambioVeicoloIndex = J2Web_UI.getComboBox_Cambio().getSelectedIndex();
		String numeroRapportiVeicolo = (String) J2Web_UI.getComboBox_NumeroRapporti().getSelectedItem();
		int numeroRapportiVeicoloIndex = J2Web_UI.getComboBox_NumeroRapporti().getSelectedIndex();
		String classeEmissioniVeicolo = (String) J2Web_UI.getComboBox_ClasseEmissioni().getSelectedItem();
		int classeEmissioniVeicoloIndex = J2Web_UI.getComboBox_ClasseEmissioni().getSelectedIndex();

		
		boolean disponibilitaCupolino = J2Web_UI.getChckbxCupolino().isSelected()?true:false;
		boolean disponibilitaAllestimentoHandicap = J2Web_UI.getChckbxHandicap().isSelected()?true:false;
		boolean disponibilitaServoSterzo = J2Web_UI.getChckbxServosterzo().isSelected()?true:false;
		boolean disponibilitaSediliSportivi = J2Web_UI.getChckbxSediliSportivi().isSelected()?true:false;
		boolean disponibilitaParkDistControl = J2Web_UI.getChckbxParkDistControl().isSelected()?true:false;
		boolean disponibilitaFreniADisco = J2Web_UI.getChckbxFreniADisco().isSelected()?true:false;
		boolean disponibilitaRadioOLettoreCD = J2Web_UI.getChckbxRadiolettoreCd().isSelected()?true:false;
		boolean disponibilitaAntifurto = J2Web_UI.getChckbxAntifurto().isSelected()?true:false;
		boolean disponibilitaABS = J2Web_UI.getChckbxAbs().isSelected()?true:false;
		boolean disponibilitaGancioTraino = J2Web_UI.getChckbxGancioTraino().isSelected()?true:false;
		boolean disponibilitaVolanteMultifunzione = J2Web_UI.getChckbxVolanteMultifunzione().isSelected()?true:false;
		boolean disponibilitaImmobilizer = J2Web_UI.getChckbxImmobilizer().isSelected()?true:false;
		boolean disponibilitaPortaPacchi = J2Web_UI.getChckbxPortapacchi().isSelected()?true:false;
		boolean disponibilitaAirBag = J2Web_UI.getChckbxAirbag().isSelected()?true:false;
		boolean disponibilitaESP = J2Web_UI.getChckbxEsp().isSelected()?true:false;
		boolean disponibilitaAlzacristalliElettrici = J2Web_UI.getChckbxAlzacristalliElettrici().isSelected()?true:false;
		boolean disponibilitaNavigatoreSattelitare = J2Web_UI.getChckbxNavigatoreSatellitare().isSelected()?true:false;
		boolean disponibilitaCerchiInLega = J2Web_UI.getChckbxCerchiInLega().isSelected()?true:false;
		boolean disponibilitaContrlAutomTrazione = J2Web_UI.getChckbxContrAutomTrazione().isSelected()?true:false;
		boolean disponibilitaChiusuraCentralizzata = J2Web_UI.getChckbxChiusuraCentralizzata().isSelected()?true:false;
		boolean disponibilitaSediliRiscaldati = J2Web_UI.getChckbxSediliRiscaldati().isSelected()?true:false;
		boolean disponibilitaClima = J2Web_UI.getChckbxClima().isSelected()?true:false;
		boolean disponibilitaAvviamentoElettrico = J2Web_UI.getChckbxAvviamentoElettrico().isSelected()?true:false;
		boolean disponibilitaAvviamentoAPedale = J2Web_UI.getChckbxAvviamentoAPedale().isSelected()?true:false;
		boolean disponibilitaBauletto = J2Web_UI.getChckbxBauletto().isSelected()?true:false;
		boolean coloreMetalizzato = J2Web_UI.getChckbxMetallizzato().isSelected()?true:false;
		boolean ivaDeducibile = J2Web_UI.getChckbxIvaDeducibile().isSelected()?true:false;
		
		
		String KWVeicolo = J2Web_UI.getTextField_Kw().getText().trim();
		String CVVeicolo = J2Web_UI.getTextField_Cv().getText().trim();
		String chilometraggioVeicolo = J2Web_UI.getTextField_Chilometraggio().getText().trim();
		String prezzoVeicolo = J2Web_UI.getTextField_Prezzo().getText().trim();
		String comsumeMedioVeicolo = J2Web_UI.getTextField_ConsumoMedio().getText().trim();
		String cilindrataVeicolo = J2Web_UI.getTextField_Cilindrata().getText().trim();
		String urlVideoYouTube = J2Web_UI.getTextField_YouTubeUrl().getText().trim();
		
		String descrizioneVeicolo = J2Web_UI.getTextPane_Descrizione().getText().trim();
		
		File imgFile1 = J2Web_UI.getFileImmagine1();
		if(imgFile1!=null && imgFile1.exists()) {
			arrayImages[1] = imgFile1;
		}
		File imgFile2 = J2Web_UI.getFileImmagine2();
		if(imgFile2!=null && imgFile2.exists()) {
			arrayImages[2] = imgFile1;
		}
		File imgFile3 = J2Web_UI.getFileImmagine3();
		if(imgFile3!=null && imgFile3.exists()) {
			arrayImages[3] = imgFile1;
		}
		File imgFile4 = J2Web_UI.getFileImmagine4();
		if(imgFile4!=null && imgFile4.exists()) {
			arrayImages[4] = imgFile1;
		}
		File imgFile5 = J2Web_UI.getFileImmagine5();
		if(imgFile5!=null && imgFile5.exists()) {
			arrayImages[5] = imgFile1;
		}
		File imgFile6 = J2Web_UI.getFileImmagine6();
		if(imgFile6!=null && imgFile6.exists()) {
			arrayImages[6] = imgFile1;
		}
		File imgFile7 = J2Web_UI.getFileImmagine7();
		if(imgFile7!=null && imgFile7.exists()) {
			arrayImages[7] = imgFile1;
		}
		File imgFile8 = J2Web_UI.getFileImmagine8();
		if(imgFile8!=null && imgFile8.exists()) {
			arrayImages[8] = imgFile1;
		}
		File imgFile9 = J2Web_UI.getFileImmagine9();
		if(imgFile9!=null && imgFile9.exists()) {
			arrayImages[9] = imgFile1;
		}
		File imgFile10 = J2Web_UI.getFileImmagine10();
		if(imgFile10!=null && imgFile10.exists()) {
			arrayImages[10] = imgFile1;
		}
		
	
		//Inizializzo il path per il file hash di questa scheda
		singolaSchedaDatPath = ".\\schede\\" + idScheda + "-" + idScheda + ".dat";
		
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