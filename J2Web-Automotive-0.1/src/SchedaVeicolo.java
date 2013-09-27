/*
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda veicolo
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
import java.util.UUID;
import javax.swing.JOptionPane;


public class SchedaVeicolo implements Serializable, parametriGenerali  {
	
	private static final long serialVersionUID = 1L;
	
	//Attributi della scheda veicolo	
	long idScheda = new Date().getTime();	//id univoco riferito alla scheda
	String codiceScheda= "j2w05_V" + UUID.randomUUID().toString(); //codice scheda univoco
	
	//Inizializzo il path per il file hash di questa scheda
	String singolaSchedaDatPath = ".\\schede\\" + codiceScheda + ".dat";
	
	String veicolo;
	String tipologiaVeicolo;
	int tipologiaVeicoloIndex;
	String marcaVeicolo;
	int marcaVeicoloIndex;
	String carrozzeriaVeicolo;
	int carrozzeriaVeicoloIndex;
	String postiASedereVeicolo;
	int postiASedereVeicoloIndex;
	String finitureInterneVeicolo;
	int finitureInterneVeicoloIndex;
	String coloreInterniVeicolo;
	int coloreInterniVeicoloIndex;
	String versioneVeicolo;
	int versioneVeicoloIndex;
	String meseImmatricolazioneVeicolo;
	int meseImmatricolazioneVeicoloIndex;
	String annoImmatricolazioneVeicolo;
	int annoImmatricolazioneVeicoloIndex;
	String coloreEsternoVeicolo;
	int coloreEsternoVeicoloIndex;
	String numeroPrecedentiProprietariVeicolo;
	int numeroPrecedentiProprietariVeicoloIndex;
	String tipologiaCambioVeicolo;
	int tipologiaCambioVeicoloIndex;
	String numeroRapportiVeicolo;
	int numeroRapportiVeicoloIndex;
	String classeEmissioniVeicolo;
	int classeEmissioniVeicoloIndex;
	
	boolean disponibilitaCupolino;
	boolean disponibilitaAllestimentoHandicap;
	boolean disponibilitaServoSterzo;
	boolean disponibilitaSediliSportivi;
	boolean disponibilitaParkDistControl;
	boolean disponibilitaFreniADisco;
	boolean disponibilitaRadioOLettoreCD;
	boolean disponibilitaAntifurto;
	boolean disponibilitaABS;
	boolean disponibilitaGancioTraino;
	boolean disponibilitaVolanteMultifunzione;
	boolean disponibilitaImmobilizer;
	boolean disponibilitaPortaPacchi;
	boolean disponibilitaAirBag;
	boolean disponibilitaESP;
	boolean disponibilitaAlzacristalliElettrici;
	boolean disponibilitaNavigatoreSattelitare;
	boolean disponibilitaCerchiInLega;
	boolean disponibilitaContrlAutomTrazione;
	boolean disponibilitaChiusuraCentralizzata;
	boolean disponibilitaSediliRiscaldati;
	boolean disponibilitaClima;
	boolean disponibilitaAvviamentoElettrico;
	boolean disponibilitaAvviamentoAPedale;
	boolean disponibilitaBauletto;
	boolean coloreMetalizzato;
	boolean ivaDeducibile;
	
	String KWVeicolo;
	String CVVeicolo;
	String chilometraggioVeicolo;
	String prezzoVeicolo;
	String comsumeMedioVeicolo;
	String cilindrataVeicolo;
	String urlVideoYouTube;
	String descrizioneVeicolo;
	
	//File
	File imgFile1;	File imgFile2;	File imgFile3;	File imgFile4;	File imgFile5;	File imgFile6;	File imgFile7;	File imgFile8;	File imgFile9;	File imgFile0; 	
	File[] arrayImages = new File[10]; //Attenzione: lascierò volutamente libera la prima posizione [0]
	
	//Una scheda immobile può essere ospitata in diversi portali, la seguente tabella hash contiene i codici dei portali(key) e il codice di inserimento(value) in cui la scheda è attualmente inserita
	Map<String,String> mappaPortaliOspitanti = new Hashtable<String,String>();
	
	
	//Costruttore
	public SchedaVeicolo () {	 	
	
		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		veicolo = J2Web_UI.getRdbtnAutoveicolo().isSelected()?"auto":"moto";
		tipologiaVeicolo = (String) J2Web_UI.getComboBox_Tipologia().getSelectedItem();
		tipologiaVeicoloIndex =J2Web_UI.getComboBox_Tipologia().getSelectedIndex();
		marcaVeicolo = (String) J2Web_UI.getComboBox_Marca().getSelectedItem();
		marcaVeicoloIndex =J2Web_UI.getComboBox_Marca().getSelectedIndex();
		carrozzeriaVeicolo = (String) J2Web_UI.getComboBox_Carrozzeria().getSelectedItem();
		carrozzeriaVeicoloIndex =J2Web_UI.getComboBox_Carrozzeria().getSelectedIndex();
		postiASedereVeicolo = (String) J2Web_UI.getComboBox_PostiASedere().getSelectedItem();
		postiASedereVeicoloIndex =J2Web_UI.getComboBox_PostiASedere().getSelectedIndex();
		finitureInterneVeicolo = (String) J2Web_UI.getComboBox_FinitureInterni().getSelectedItem();
		finitureInterneVeicoloIndex =J2Web_UI.getComboBox_FinitureInterni().getSelectedIndex();
		coloreInterniVeicolo = (String) J2Web_UI.getComboBox_ColoreInterni().getSelectedItem();
		coloreInterniVeicoloIndex =J2Web_UI.getComboBox_ColoreInterni().getSelectedIndex();
		versioneVeicolo = (String) J2Web_UI.getComboBox_Versione().getSelectedItem();
		versioneVeicoloIndex =J2Web_UI.getComboBox_Versione().getSelectedIndex();
		meseImmatricolazioneVeicolo = (String) J2Web_UI.getComboBox_MeseImmatricolazione().getSelectedItem();
		meseImmatricolazioneVeicoloIndex =J2Web_UI.getComboBox_MeseImmatricolazione().getSelectedIndex();
		annoImmatricolazioneVeicolo = (String) J2Web_UI.getComboBox_AnnoImmatricolazione().getSelectedItem();
		annoImmatricolazioneVeicoloIndex =J2Web_UI.getComboBox_AnnoImmatricolazione().getSelectedIndex();
		coloreEsternoVeicolo = (String) J2Web_UI.getComboBox_ColoreEsterno().getSelectedItem();
		coloreEsternoVeicoloIndex =J2Web_UI.getComboBox_ColoreEsterno().getSelectedIndex();
		numeroPrecedentiProprietariVeicolo = (String) J2Web_UI.getComboBox_PrecedentiProprietari().getSelectedItem();
		numeroPrecedentiProprietariVeicoloIndex =J2Web_UI.getComboBox_PrecedentiProprietari().getSelectedIndex();
		tipologiaCambioVeicolo = (String) J2Web_UI.getComboBox_Cambio().getSelectedItem();
		tipologiaCambioVeicoloIndex = J2Web_UI.getComboBox_Cambio().getSelectedIndex();
		numeroRapportiVeicolo = (String) J2Web_UI.getComboBox_NumeroRapporti().getSelectedItem();
		numeroRapportiVeicoloIndex = J2Web_UI.getComboBox_NumeroRapporti().getSelectedIndex();
		classeEmissioniVeicolo = (String) J2Web_UI.getComboBox_ClasseEmissioni().getSelectedItem();
		classeEmissioniVeicoloIndex = J2Web_UI.getComboBox_ClasseEmissioni().getSelectedIndex();
		
		disponibilitaCupolino = J2Web_UI.getChckbxCupolino().isSelected()?true:false;
		disponibilitaAllestimentoHandicap = J2Web_UI.getChckbxHandicap().isSelected()?true:false;
		disponibilitaServoSterzo = J2Web_UI.getChckbxServosterzo().isSelected()?true:false;
		disponibilitaSediliSportivi = J2Web_UI.getChckbxSediliSportivi().isSelected()?true:false;
		disponibilitaParkDistControl = J2Web_UI.getChckbxParkDistControl().isSelected()?true:false;
		disponibilitaFreniADisco = J2Web_UI.getChckbxFreniADisco().isSelected()?true:false;
		disponibilitaRadioOLettoreCD = J2Web_UI.getChckbxRadiolettoreCd().isSelected()?true:false;
		disponibilitaAntifurto = J2Web_UI.getChckbxAntifurto().isSelected()?true:false;
		disponibilitaABS = J2Web_UI.getChckbxAbs().isSelected()?true:false;
		disponibilitaGancioTraino = J2Web_UI.getChckbxGancioTraino().isSelected()?true:false;
		disponibilitaVolanteMultifunzione = J2Web_UI.getChckbxVolanteMultifunzione().isSelected()?true:false;
		disponibilitaImmobilizer = J2Web_UI.getChckbxImmobilizer().isSelected()?true:false;
		disponibilitaPortaPacchi = J2Web_UI.getChckbxPortapacchi().isSelected()?true:false;
		disponibilitaAirBag = J2Web_UI.getChckbxAirbag().isSelected()?true:false;
		disponibilitaESP = J2Web_UI.getChckbxEsp().isSelected()?true:false;
		disponibilitaAlzacristalliElettrici = J2Web_UI.getChckbxAlzacristalliElettrici().isSelected()?true:false;
		disponibilitaNavigatoreSattelitare = J2Web_UI.getChckbxNavigatoreSatellitare().isSelected()?true:false;
		disponibilitaCerchiInLega = J2Web_UI.getChckbxCerchiInLega().isSelected()?true:false;
		disponibilitaContrlAutomTrazione = J2Web_UI.getChckbxContrAutomTrazione().isSelected()?true:false;
		disponibilitaChiusuraCentralizzata = J2Web_UI.getChckbxChiusuraCentralizzata().isSelected()?true:false;
		disponibilitaSediliRiscaldati = J2Web_UI.getChckbxSediliRiscaldati().isSelected()?true:false;
		disponibilitaClima = J2Web_UI.getChckbxClima().isSelected()?true:false;
		disponibilitaAvviamentoElettrico = J2Web_UI.getChckbxAvviamentoElettrico().isSelected()?true:false;
		disponibilitaAvviamentoAPedale = J2Web_UI.getChckbxAvviamentoAPedale().isSelected()?true:false;
		disponibilitaBauletto = J2Web_UI.getChckbxBauletto().isSelected()?true:false;
		coloreMetalizzato = J2Web_UI.getChckbxMetallizzato().isSelected()?true:false;
		ivaDeducibile = J2Web_UI.getChckbxIvaDeducibile().isSelected()?true:false;
				
		KWVeicolo = J2Web_UI.getTextField_Kw().getText().trim();
		CVVeicolo = J2Web_UI.getTextField_Cv().getText().trim();
		chilometraggioVeicolo = J2Web_UI.getTextField_Chilometraggio().getText().trim();
		prezzoVeicolo = J2Web_UI.getTextField_Prezzo().getText().trim();
		comsumeMedioVeicolo = J2Web_UI.getTextField_ConsumoMedio().getText().trim();
		cilindrataVeicolo = J2Web_UI.getTextField_Cilindrata().getText().trim();
		urlVideoYouTube = J2Web_UI.getTextField_YouTubeUrl().getText().trim();
		
		descrizioneVeicolo = J2Web_UI.getTextPane_Descrizione().getText().trim();
		
		File imgFile1 = J2Web_UI.getFileImmagine1();
		if(imgFile1!=null && imgFile1.exists()) {
			arrayImages[1] = imgFile1;
		}
		File imgFile2 = J2Web_UI.getFileImmagine2();
		if(imgFile2!=null && imgFile2.exists()) {
			arrayImages[2] = imgFile2;
		}
		File imgFile3 = J2Web_UI.getFileImmagine3();
		if(imgFile3!=null && imgFile3.exists()) {
			arrayImages[3] = imgFile3;
		}
		File imgFile4 = J2Web_UI.getFileImmagine4();
		if(imgFile4!=null && imgFile4.exists()) {
			arrayImages[4] = imgFile4;
		}
		File imgFile5 = J2Web_UI.getFileImmagine5();
		if(imgFile5!=null && imgFile5.exists()) {
			arrayImages[5] = imgFile5;
		}
		File imgFile6 = J2Web_UI.getFileImmagine6();
		if(imgFile6!=null && imgFile6.exists()) {
			arrayImages[6] = imgFile6;
		}
		File imgFile7 = J2Web_UI.getFileImmagine7();
		if(imgFile7!=null && imgFile7.exists()) {
			arrayImages[7] = imgFile7;
		}
		File imgFile8 = J2Web_UI.getFileImmagine8();
		if(imgFile8!=null && imgFile8.exists()) {
			arrayImages[8] = imgFile8;
		}
		File imgFile9 = J2Web_UI.getFileImmagine9();
		if(imgFile9!=null && imgFile9.exists()) {
			arrayImages[9] = imgFile9;
		}
		File imgFile10 = J2Web_UI.getFileImmagine10();
		if(imgFile10!=null && imgFile10.exists()) {
			arrayImages[10] = imgFile10;
		}	
	
		
	}

	
	//Metodi
	
	//Carica tabella quando premi il radio button relativo alla scheda
	@SuppressWarnings("unchecked")
	public void caricaTabellaHash() {
		
		//Lettura schede dal file .dat
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
class IdComparator implements Comparator<SchedaVeicolo> {

    public int compare(SchedaVeicolo s1, SchedaVeicolo s2) {
        if (s1.idScheda > s2.idScheda)
            return 1;
        else if (s1.idScheda < s2.idScheda)
            return -1;
        else
            return 0;
    }
}


/*class CodeComparator implements Comparator<SchedaVeicolo>  {
	
    public int compare(SchedaVeicolo s1, SchedaVeicolo s2) { 	
    	int i = s1.codiceInserzione.compareTo(s2.codiceInserzione); 
    	System.out.println("i:" + i);
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/


/*class CityComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.comune.compareTo(s2.comune); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/


/*class ProvinceComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.provincia.compareTo(s2.provincia); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/


/*class RegionComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.regione.compareTo(s2.regione); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/