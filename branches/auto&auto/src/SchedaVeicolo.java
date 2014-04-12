/**
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda veicolo
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class SchedaVeicolo implements Serializable, parametriGenerali  {

	private static final long serialVersionUID = 1L;

	//Path per il file hash di questa scheda
	String singolaSchedaDatPath;
	
	//La scheda è in modifica
	boolean schedaInModifica;

	//Attributi della scheda veicolo	
	long idScheda;	//id univoco riferito alla scheda che permette di ordinarle in modo cronologico
	String codiceScheda; //codice scheda univoco
	String veicolo;
	String tipologiaVeicolo;
	int tipologiaVeicoloIndex;
	String marcaVeicolo;
	int marcaVeicoloIndex;
	String modelloVeicolo;
	int modelloVeicoloIndex;
	String versioneVeicolo;
	int versioneVeicoloIndex;
	String carrozzeriaVeicolo;
	int carrozzeriaVeicoloIndex;
	String postiASedereVeicolo;
	int postiASedereVeicoloIndex;
	String finitureInterneVeicolo;
	int finitureInterneVeicoloIndex;
	String coloreInterniVeicolo;
	int coloreInterniVeicoloIndex;
	String meseImmatricolazioneVeicolo;
	int meseImmatricolazioneVeicoloIndex;
	String annoImmatricolazioneVeicolo;
	int annoImmatricolazioneVeicoloIndex;
	String coloreEsternoVeicolo;
	int coloreEsternoVeicoloIndex;
	String numeroPrecedentiProprietariVeicolo;
	int numeroPrecedentiProprietariVeicoloIndex;
	String tipologiaMotoreVeicolo;
	int tipologiaMotoreVeicoloIndex;
	String tipologiaCambioVeicolo;
	int tipologiaCambioVeicoloIndex;
	String numeroRapportiVeicolo;
	int numeroRapportiVeicoloIndex;
	String classeEmissioniVeicolo;
	int classeEmissioniVeicoloIndex;
	String carburanteVeicolo;
	int carburanteVeicoloIndex;
	String tipologiaContrattoVeicolo;
	int tipologiaContrattoVeicoloIndex;

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
	boolean disponibilitaNavigatoreSatellitare;
	boolean disponibilitaCerchiInLega;
	boolean disponibilitaContrlAutomTrazione;
	boolean disponibilitaChiusuraCentralizzata;
	boolean disponibilitaSediliRiscaldati;
	boolean disponibilitaClima;
	boolean disponibilitaAvviamentoElettrico;
	boolean disponibilitaAvviamentoAPedale;
	boolean disponibilitaBauletto;
	boolean coloreMetalizzato;
	//boolean prezzoTrattabile;

	String KWVeicolo;
	String CVVeicolo;
	String chilometraggioVeicolo;
	String prezzoVeicolo;
	String prezzoVeicoloCondivisione;
	String consumoMedioVeicolo;
	String cilindrataVeicolo;
	String urlVideoYouTube;
	String descrizioneVeicolo;
	String ragioneSociale;
	String Indirizzo;
	String Telefono;
	String nomeReferente;
	String TelefonoReferente;
	String emailReferente;

	//File
	File imgFile1;	File imgFile2;	File imgFile3;	File imgFile4;	File imgFile5;	File imgFile6;	File imgFile7;	File imgFile8;	File imgFile9;	File imgFile10; 	
	File[] arrayImages = new File[11]; //Attenzione: lascerò volutamente libera la prima posizione [0]

	//Una scheda veicolo può essere ospitata in diversi portali, la seguente tabella hash contiene i codici dei portali(key) e il codice di inserimento(value) in cui la scheda è attualmente inserita
	Map<String,String> mappaPortaliOspitanti;
	
	//Attributi aggiunti in seguito (possono mancare su schede clienti più vecchie della versione 1.0)
	//nessun attributo
	
	//Costruttore di default (schede nuove)
	public SchedaVeicolo() {
		this(new Date().getTime(), intestazioneCodiceSchedaVeicolo + (UUID.randomUUID().toString().substring(0,8)), new File[11], new Hashtable<String, String>());
	}
	
	//Costruttore 2 (da form di creazione scheda)
	public SchedaVeicolo (long idScheda, String codiceScheda, File[] immagini, Map<String,String> mappaPortaliOspitanti) {		

		//Attributi della scheda veicolo	
		this.idScheda = idScheda;	//id univoco riferito alla scheda per ordinamento cronologico
		this.codiceScheda= codiceScheda; //codice scheda univoco

		//Inizializzo il path per il file hash di questa scheda
		singolaSchedaDatPath = pathSchede + codiceScheda + ".dat";

		//Al momento dell'istanziazione, una scheda veicolo inizializza i propri campi prendendone il valore da quelli inseriti nel pannello form di creazione scheda veicolo
		//Radio button
		veicolo = J2Web_UI.getRdbtnAutoveicolo().isSelected()?"auto":"moto";

		//Combobox
		marcaVeicolo = ((String) J2Web_UI.getComboBox_Marca().getSelectedItem()).trim();
		marcaVeicoloIndex =J2Web_UI.getComboBox_Marca().getSelectedIndex();
		modelloVeicolo = ((String) J2Web_UI.getComboBox_Modello().getSelectedItem()).trim();
		modelloVeicoloIndex =J2Web_UI.getComboBox_Modello().getSelectedIndex();
		versioneVeicolo = ((String) J2Web_UI.getComboBox_Versione().getSelectedItem()).trim();
		versioneVeicoloIndex =J2Web_UI.getComboBox_Versione().getSelectedIndex();
		meseImmatricolazioneVeicolo = ((String) J2Web_UI.getComboBox_MeseImmatricolazione().getSelectedItem()).trim();
		meseImmatricolazioneVeicoloIndex =J2Web_UI.getComboBox_MeseImmatricolazione().getSelectedIndex();
		annoImmatricolazioneVeicolo = ((String) J2Web_UI.getComboBox_AnnoImmatricolazione().getSelectedItem()).trim();
		annoImmatricolazioneVeicoloIndex =J2Web_UI.getComboBox_AnnoImmatricolazione().getSelectedIndex();
		carburanteVeicolo = ((String) J2Web_UI.getComboBox_Carburante().getSelectedItem()).trim();
		carburanteVeicoloIndex = J2Web_UI.getComboBox_Carburante().getSelectedIndex();
		tipologiaVeicolo = ((String) J2Web_UI.getComboBox_Tipologia().getSelectedItem()).trim();
		tipologiaVeicoloIndex =J2Web_UI.getComboBox_Tipologia().getSelectedIndex();
		carrozzeriaVeicolo = ((String) J2Web_UI.getComboBox_Carrozzeria().getSelectedItem()).trim();
		carrozzeriaVeicoloIndex =J2Web_UI.getComboBox_Carrozzeria().getSelectedIndex();
		postiASedereVeicolo = ((String) J2Web_UI.getComboBox_PostiASedere().getSelectedItem()).trim();
		postiASedereVeicoloIndex =J2Web_UI.getComboBox_PostiASedere().getSelectedIndex();
		coloreEsternoVeicolo = ((String) J2Web_UI.getComboBox_ColoreEsterno().getSelectedItem()).trim();
		coloreEsternoVeicoloIndex =J2Web_UI.getComboBox_ColoreEsterno().getSelectedIndex();
		numeroPrecedentiProprietariVeicolo = ((String) J2Web_UI.getComboBox_PrecedentiProprietari().getSelectedItem()).trim();
		numeroPrecedentiProprietariVeicoloIndex =J2Web_UI.getComboBox_PrecedentiProprietari().getSelectedIndex();
		tipologiaContrattoVeicolo = ((String) J2Web_UI.getCombobox_Contratto().getSelectedItem()).trim();
		tipologiaContrattoVeicoloIndex = J2Web_UI.getCombobox_Contratto().getSelectedIndex();
		finitureInterneVeicolo = ((String) J2Web_UI.getComboBox_FinitureInterni().getSelectedItem()).trim();
		finitureInterneVeicoloIndex =J2Web_UI.getComboBox_FinitureInterni().getSelectedIndex();
		coloreInterniVeicolo = ((String) J2Web_UI.getComboBox_ColoreInterni().getSelectedItem()).trim();
		coloreInterniVeicoloIndex =J2Web_UI.getComboBox_ColoreInterni().getSelectedIndex();
		tipologiaMotoreVeicolo = ((String) J2Web_UI.getComboBox_Motore().getSelectedItem()).trim();
		tipologiaMotoreVeicoloIndex = J2Web_UI.getComboBox_Motore().getSelectedIndex();
		tipologiaCambioVeicolo = ((String) J2Web_UI.getComboBox_Cambio().getSelectedItem()).trim();
		tipologiaCambioVeicoloIndex = J2Web_UI.getComboBox_Cambio().getSelectedIndex();
		numeroRapportiVeicolo = ((String) J2Web_UI.getComboBox_NumeroRapporti().getSelectedItem()).trim();
		numeroRapportiVeicoloIndex = J2Web_UI.getComboBox_NumeroRapporti().getSelectedIndex();
		classeEmissioniVeicolo = ((String) J2Web_UI.getComboBox_ClasseEmissioni().getSelectedItem()).trim();
		classeEmissioniVeicoloIndex = J2Web_UI.getComboBox_ClasseEmissioni().getSelectedIndex();

		//Checkbox
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
		disponibilitaNavigatoreSatellitare = J2Web_UI.getChckbxNavigatoreSatellitare().isSelected()?true:false;
		disponibilitaCerchiInLega = J2Web_UI.getChckbxCerchiInLega().isSelected()?true:false;
		disponibilitaContrlAutomTrazione = J2Web_UI.getChckbxContrAutomTrazione().isSelected()?true:false;
		disponibilitaChiusuraCentralizzata = J2Web_UI.getChckbxChiusuraCentralizzata().isSelected()?true:false;
		disponibilitaSediliRiscaldati = J2Web_UI.getChckbxSediliRiscaldati().isSelected()?true:false;
		disponibilitaClima = J2Web_UI.getChckbxClima().isSelected()?true:false;
		disponibilitaAvviamentoElettrico = J2Web_UI.getChckbxAvviamentoElettrico().isSelected()?true:false;
		disponibilitaAvviamentoAPedale = J2Web_UI.getChckbxAvviamentoAPedale().isSelected()?true:false;
		disponibilitaBauletto = J2Web_UI.getChckbxBauletto().isSelected()?true:false;
		coloreMetalizzato = J2Web_UI.getChckbxMetallizzato().isSelected()?true:false;
		//ivaDeducibile = J2Web_UI.getChckbxIvaDeducibile().isSelected()?true:false;
		//prezzoTrattabile = J2Web_UI.getChckbxTrattabile().isSelected()?true:false;

		//Textfield (se sforano la lunghezza predefinita vengono tagliate)
		KWVeicolo = J2Web_UI.getTextField_Kw().getText().trim();	
		if(KWVeicolo.length()>maxCaratteri.get("txtFieldKw")) {KWVeicolo = KWVeicolo.substring(0, maxCaratteri.get("txtFieldKw")-1);}
		CVVeicolo = J2Web_UI.getTextField_Cv().getText().trim();
		if(CVVeicolo.length()>maxCaratteri.get("txtFieldCv")) {CVVeicolo = CVVeicolo.substring(0, maxCaratteri.get("txtFieldCv")-1);}
		chilometraggioVeicolo = J2Web_UI.getTextField_Chilometraggio().getText().trim();		
		if(chilometraggioVeicolo.length()>maxCaratteri.get("textField_Chilometraggio")) {chilometraggioVeicolo = chilometraggioVeicolo.substring(0, maxCaratteri.get("textField_Chilometraggio")-1);}
		prezzoVeicolo = J2Web_UI.getTextField_Prezzo().getText().trim();		
		if(prezzoVeicolo.length()>maxCaratteri.get("textField_Prezzo")) {prezzoVeicolo = prezzoVeicolo.substring(0, maxCaratteri.get("textField_Prezzo")-1);}
		prezzoVeicoloCondivisione = J2Web_UI.getTextField_PrezzoCondivisione().getText().trim();		
		if(prezzoVeicoloCondivisione.length()>maxCaratteri.get("textField_Prezzo")) {prezzoVeicoloCondivisione = prezzoVeicoloCondivisione.substring(0, maxCaratteri.get("textField_Prezzo")-1);}
		consumoMedioVeicolo = J2Web_UI.getTextField_ConsumoMedio().getText().trim();		
		if(consumoMedioVeicolo.length()>maxCaratteri.get("comboBox_ConsumoMedio")) {consumoMedioVeicolo = consumoMedioVeicolo.substring(0, maxCaratteri.get("comboBox_ConsumoMedio")-1);}
		cilindrataVeicolo = J2Web_UI.getTextField_Cilindrata().getText().trim();		
		if(cilindrataVeicolo.length()>maxCaratteri.get("comboBox_Cilindrata")) {cilindrataVeicolo = cilindrataVeicolo.substring(0, maxCaratteri.get("comboBox_Cilindrata")-1);}
		urlVideoYouTube = J2Web_UI.getTextField_YouTubeUrl().getText().trim();
		if(urlVideoYouTube.length()>maxCaratteri.get("txtField_YouTubeUrl")) {urlVideoYouTube = urlVideoYouTube.substring(0, maxCaratteri.get("txtField_YouTubeUrl")-1);}
		TelefonoReferente = J2Web_UI.getTextFieldTelefonoReferente().getText().trim();		
		if(TelefonoReferente.length()>maxCaratteri.get("textFieldTelefonoReferente")) {TelefonoReferente = TelefonoReferente.substring(0, maxCaratteri.get("textFieldTelefonoReferente")-1);}
		emailReferente = J2Web_UI.getTextFieldEmailReferente().getText().trim();		
		if(emailReferente.length()>maxCaratteri.get("textFieldEmailReferente")) {emailReferente = emailReferente.substring(0, maxCaratteri.get("textFieldEmailReferente")-1);}

		ragioneSociale = RAGIONESOCIALE_UTENTE;	
		Indirizzo = INDIRIZZO_UTENTE;
		Telefono = TELEFONO_UTENTE;		
		nomeReferente = UTENTE;

		//Textpane
		descrizioneVeicolo = J2Web_UI.getTextPane_Descrizione().getText().trim();
		String descrizioneVeicoloLinkFree = descrizioneVeicolo.replace("http://", "").replace("www.", "");
		descrizioneVeicolo = descrizioneVeicoloLinkFree;
		if(descrizioneVeicolo.length()>maxCaratteri.get("textPane_Descrizione")) {descrizioneVeicolo = descrizioneVeicolo.substring(0, maxCaratteri.get("textPane_Descrizione")-1);}

		//Mappa dei portali ospitanti
		this.mappaPortaliOspitanti = mappaPortaliOspitanti;
		
		//Immagini
		//Svuoto l'array immagini
		for(int i =0; i<arrayImages.length; i++) {
			arrayImages[i]=null;
		}
		
		//Copio l'array in input nell'array delle immagini attuali
		System.arraycopy(immagini, 0, arrayImages, 0, immagini.length);

		imgFile1 = J2Web_UI.getFileImmagine1();
		if(imgFile1!=null && imgFile1.exists()) {
			arrayImages[1] = imgFile1;
		}
		imgFile2 = J2Web_UI.getFileImmagine2();
		if(imgFile2!=null && imgFile2.exists()) {
			arrayImages[2] = imgFile2;
		}
		imgFile3 = J2Web_UI.getFileImmagine3();
		if(imgFile3!=null && imgFile3.exists()) {
			arrayImages[3] = imgFile3;
		}
		imgFile4 = J2Web_UI.getFileImmagine4();
		if(imgFile4!=null && imgFile4.exists()) {
			arrayImages[4] = imgFile4;
		}
		imgFile5 = J2Web_UI.getFileImmagine5();
		if(imgFile5!=null && imgFile5.exists()) {
			arrayImages[5] = imgFile5;
		}
		imgFile6 = J2Web_UI.getFileImmagine6();
		if(imgFile6!=null && imgFile6.exists()) {
			arrayImages[6] = imgFile6;
		}
		imgFile7 = J2Web_UI.getFileImmagine7();
		if(imgFile7!=null && imgFile7.exists()) {
			arrayImages[7] = imgFile7;
		}
		imgFile8 = J2Web_UI.getFileImmagine8();
		if(imgFile8!=null && imgFile8.exists()) {
			arrayImages[8] = imgFile8;
		}
		imgFile9 = J2Web_UI.getFileImmagine9();
		if(imgFile9!=null && imgFile9.exists()) {
			arrayImages[9] = imgFile9;
		}
		imgFile10 = J2Web_UI.getFileImmagine10();
		if(imgFile10!=null && imgFile10.exists()) {
			arrayImages[10] = imgFile10;
		}

	}


	//Costruttore 3 (da query SQL)
	public SchedaVeicolo (JSONArray JSONArray) {			

		veicolo = "auto";	//solo auto attualmente

		for (int h=1; h<JSONArray.length(); h++) {

			JSONObject json = null;
			try {
				json = new JSONObject(JSONArray.getString(h));
			} catch (NoSuchElementException | ParseException e) {
				//
				e.printStackTrace();
			}

			if(json.has("71")) {codiceScheda = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("71")));}
			if(json.has("4")) {marcaVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("4")));} 
			if(json.has("5")) {modelloVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("5")));} 
			if(json.has("6")) {versioneVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("6")));} 
			if(json.has("8")) {meseImmatricolazioneVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("8")));} 
			if(json.has("9")) {annoImmatricolazioneVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("9")));} 
			if(json.has("10")) {carburanteVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("10")));} 
			if(json.has("11")) {tipologiaVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("11")));} 
			if(json.has("12")) {carrozzeriaVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("12")));} 
			if(json.has("13")) {postiASedereVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("13")));} 
			if(json.has("14")) {KWVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("14")));}
			if(json.has("15")) {CVVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("15")));} 

			if(json.has("16")) {coloreEsternoVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("16")));}
			//if(json.has("18")) {prezzoVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("18")));}
			if(json.has("20")) {prezzoVeicolo = Integer.toString(json.getInt("20"));}
			if(json.has("21")) {prezzoVeicoloCondivisione = Integer.toString(json.getInt("21"));}
			if(json.has("65")) {ragioneSociale = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("65")));}
			if(json.has("68")) {nomeReferente = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("68")));}

			if(json.has("19")) {chilometraggioVeicolo = Integer.toString(json.getInt("19"));}

			if(json.has("24")) {tipologiaContrattoVeicolo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("24")));}

			if(json.has("66")) {Indirizzo = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("66")));}
			if(json.has("69")) {TelefonoReferente = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("69")));}
			if(json.has("70")) {emailReferente = StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("70")));}

			if(json.has("54") && !json.getString("54").equals("\u0000")) {

				URL dbUrl;

				try {
					dbUrl = new URL(urlLocationImmaginiInRemoto + StringUtils.newStringUtf8(Base64.decodeBase64(json.getString("54"))));
					URLConnection connection = dbUrl.openConnection();
					InputStream in = connection.getInputStream();

					//Per questa tipologia di scheda veicolo (da JSON) si prevede solo una immagine. 
					arrayImages[1] = new File("imagePreviewMLS.jpg");
					FileOutputStream fos = new FileOutputStream(arrayImages[1]);

					byte[] buf = new byte[1024];
					while (true) {
						int len = in.read(buf);
						if (len == -1) {
							break;
						}
						fos.write(buf, 0, len);
					}
					in.close();				
					fos.flush();
					fos.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}



	//Metodi

	//Carica la tabella hash quando premi il radio button relativo alla scheda
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
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash"), "IOException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash"), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
			} 		
		}
		else {
			try {
				FileOutputStream newFile = new FileOutputStream(singolaSchedaDatPath);
				System.out.print("File hash non trovato. Creazione di un nuovo file hash per questa scheda..." + newFile.toString());
				System.out.print(" fatto." + "\n");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaTabellaHash"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaTabellaHash"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
			e0.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaTabellaHash"), "IOException", JOptionPane.ERROR_MESSAGE);
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

	//Per ID della scheda (data di inserimento)
	public int compare(SchedaVeicolo s1, SchedaVeicolo s2) {
		if (s1.idScheda > s2.idScheda)
			return 1;
		else if (s1.idScheda < s2.idScheda)
			return -1;
		else
			return 0;
	}
}

class marcaComparator implements Comparator<SchedaVeicolo>	{

	public int compare(SchedaVeicolo s1, SchedaVeicolo s2) {    	
		int i = s1.marcaVeicolo.compareTo(s2.marcaVeicolo); 
		if (i > 0)
			return 1;
		else if (i < 0)
			return -1;
		else
			return 0;
	}
}

class tipologiaComparator implements Comparator<SchedaVeicolo>	{

	public int compare(SchedaVeicolo s1, SchedaVeicolo s2) {    	
		int i = s1.tipologiaVeicolo.compareTo(s2.tipologiaVeicolo); 
		if (i > 0)
			return 1;
		else if (i < 0)
			return -1;
		else
			return 0;
	}
}

class prezzoComparator implements Comparator<SchedaVeicolo>	{

	public int compare(SchedaVeicolo s1, SchedaVeicolo s2) {    	
		int i = s1.prezzoVeicolo.compareTo(s2.prezzoVeicolo); 
		if (i > 0)
			return 1;
		else if (i < 0)
			return -1;
		else
			return 0;
	}
}