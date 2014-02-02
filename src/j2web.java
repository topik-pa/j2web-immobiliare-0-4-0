import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class j2web implements parametriGenerali {

	//Caricamento delle schede veicolo create nelle precedenti sessioni
	//@SuppressWarnings("unchecked")
	public static void caricaListaSchedeVeicoloCreate() {
		File file = new File(pathFileDatSchedeVeicolo);
		if(file.exists() && file.length()!=0) {
			System.out.print(" File .dat schede veicolo trovato. Caricamento dati...");
			try {
				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
				@SuppressWarnings("unchecked")
				LinkedList<SchedaVeicolo> readObject = (LinkedList<SchedaVeicolo>)inputFile.readObject();
				J2Web_UI.listSchedeVeicolo = readObject;
				inputFile.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeVeicoloCreate"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeVeicoloCreate"), "IOException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeVeicoloCreate"), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
			}
		}   				
		else {
			System.out.println(" File .dat schede non trovato o file .dat vuoto.");
		}
	}

	//Caricamento delle schede cliente create nelle precedenti sessioni
	//@SuppressWarnings("unchecked")
	public static void caricaListaSchedeClienteCreate() {
		File file = new File(pathFileDatSchedeCliente);
		if(file.exists() && file.length()!=0) {
			System.out.print(" File .dat schede cliente trovato. Caricamento dati...");
			try {
				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
				@SuppressWarnings("unchecked")
				LinkedList<SchedaCliente> readObject = (LinkedList<SchedaCliente>)inputFile.readObject();
				J2Web_UI.listSchedeCliente = readObject;
				inputFile.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeClienteCreate"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeClienteCreate"), "IOException", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeClienteCreate"), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
			}
		}   				
		else {
			System.out.println(" File .dat schede cliente non trovato o file .dat vuoto.");
		}
	}

	//Salvataggio delle schede veicolo create nelle precedenti sessioni
	public static void salvaListaSchedeVeicoloCreate() {
		try {
			File file = new File(pathFileDatSchedeVeicolo);
			if(file.exists()) {
				System.out.print("File .dat schede trovato. Salvataggio dati scheda veicolo...");
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(J2Web_UI.listSchedeVeicolo);
				outputFile.close();
				System.out.print(" fatto." + "\n");
			}
			else {	    		
				FileOutputStream newFile = new FileOutputStream(pathFileDatSchedeVeicolo);
				System.out.print("File .dat non schede trovato. Creazione del file scheda veicolo..." + newFile.toString());
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(J2Web_UI.listSchedeVeicolo);
				outputFile.close();
				System.out.print(" fatto." + "\n");
			}
		}
		catch (FileNotFoundException e) {		
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaListaSchedeVeicoloCreate"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaListaSchedeVeicoloCreate"), "IOException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	//Salvataggio delle schede cliente create nelle precedenti sessioni
	public static void salvaListaSchedeClienteCreate() {
		try {
			File file = new File(pathFileDatSchedeCliente);
			if(file.exists()) {
				System.out.print("File .dat schede cliente trovato. Salvataggio dati...");
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(J2Web_UI.listSchedeCliente);
				outputFile.close();
				System.out.print(" fatto." + "\n");
			}
			else {	    		
				FileOutputStream newFile = new FileOutputStream(pathFileDatSchedeCliente);
				System.out.print("File .dat schede cliente non trovato. Creazione del file..." + newFile.toString());
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(J2Web_UI.listSchedeCliente);
				outputFile.close();
				System.out.print(" fatto." + "\n");
			}
		}
		catch (FileNotFoundException e) {		
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaListaSchedeClienteCreate"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaListaSchedeClienteCreate"), "IOException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	//Inizializza la mappa contenente i campi testuali e i corrispondenti limiti di caratteri
	public static void inizializzaMappaLimiteCaratteri() {	

		maxCaratteri.put("txtFieldKw", 3);
		maxCaratteri.put("txtFieldCv", 3);
		maxCaratteri.put("textField_Chilometraggio", 6);
		maxCaratteri.put("textField_Prezzo", 6);
		maxCaratteri.put("comboBox_Cilindrata", 6);
		maxCaratteri.put("comboBox_ConsumoMedio", 5);
		maxCaratteri.put("textPane_Descrizione", 1000);
		maxCaratteri.put("textFieldTelefonoGenerico", 15);
		maxCaratteri.put("textFieldTelefonoReferente", 15);
		maxCaratteri.put("textFieldEmailReferente", 40);
		maxCaratteri.put("textFieldIndirizzo", 30);	
		maxCaratteri.put("txtField_YouTubeUrl", 50);

		maxCaratteri.put("formCliente_textFieldNome", 30);
		maxCaratteri.put("formCliente_textFieldCognome", 30);
		maxCaratteri.put("formCliente_textFieldEmail", 40);
		maxCaratteri.put("formCliente_textFieldTelefono1", 15);
		maxCaratteri.put("formCliente_textFieldTelefono2", 15);
		maxCaratteri.put("formCliente_textFieldVia", 30);
		maxCaratteri.put("formCliente_textFieldNumeroCivico", 7);
		maxCaratteri.put("formCliente_textFieldCAP", 5);
		maxCaratteri.put("formCliente_textFieldCitta", 30);

	}

	//Inizializza la mappa dei dialoghi modali
	public static void inizializzaMappaDialoghiModali() {	

		MapModalWindowsDialogs.put("sendConfirmationMail", "Non è stato possibile inviare inviare la mail di conferma inserimento.");
		MapModalWindowsDialogs.put("caricaListaSchedeClienteCreate", "Impossibile accedere al file delle schede cliente: non è stato possibile caricare le schede precedentemente salvate.");
		MapModalWindowsDialogs.put("caricaListaSchedeVeicoloCreate", "Impossibile accedere al file delle schede veicolo: non è stato possibile caricare le schede precedentemente salvate.");
		MapModalWindowsDialogs.put("salvaListaSchedeVeicoloCreate", "Impossibile accedere al file delle schede veicolo: non è stato possibile salvare la scheda.");
		MapModalWindowsDialogs.put("salvaListaSchedeClienteCreate", "Impossibile accedere al file delle schede cliente: non è stato possibile salvare la scheda.");
		MapModalWindowsDialogs.put("selezionaImmagine", "Attenzione: formato di file non valido. Le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega.");
		MapModalWindowsDialogs.put("caricaTabellaHash", "Impossibile accedere al file hash scheda: non è stato possibile caricare le impostazioni della scheda. ");
		MapModalWindowsDialogs.put("salvaTabellaHash", "Impossibile accedere al file hash scheda: non è stato possibile salvare le impostazioni della scheda. ");
		MapModalWindowsDialogs.put("creazioneDellaSchedaVeicolo", "Attenzione: alcuni campi obbligatori non sono stati compilati. ");
		MapModalWindowsDialogs.put("creazioneDellaSchedaCliente", "Attenzione: alcuni campi obbligatori non sono stati compilati. ");
		MapModalWindowsDialogs.put("primoTrackingFallito", "Verificare la connessione ad Internet, senza connessione alcune funzionalità di J2Web sono inibite. ");
		MapModalWindowsDialogs.put("sincronizzazioneImpossibile", "La sincronizzazione della scheda corrente non è possibile per il portale selezionato. ");

		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_IOException", "Errore I/O. \n\nE\' stata inviata una segnalazione per risolvere il problema.");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseStatusCodeException", "Errore HTTP Response Status Code. \n\nE\' stata inviata una segnalazione per risolvere il problema.");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseHeaderException", "Errore HTTP Response Header. \n\nE\' stata inviata una segnalazione per risolvere il problema.");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseBodyException", "Errore HTTP Response Body. \n\nE\' stata inviata una segnalazione per risolvere il problema.");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_Generic", "Errore Generico. \n\nE\' stata inviata una segnalazione per risolvere il problema.");

		MapModalWindowsDialogs.put("menu_ConfermaEliminaTutteLeSchedeVeicolo", "Questa azione eliminerà tutte le schede veicolo. Confermi? ");
		MapModalWindowsDialogs.put("menu_ConfermaEliminaTutteLeSchedeCliente", "Questa azione eliminerà tutte le schede cliente. Confermi? ");
		
		MapModalWindowsDialogs.put("caricaSchedeVeicolo_ImmaginiNonDisponibili", "Attenzione: non è stato possibile recuperare alcune immagini delle schede veicolo. ");

	}

	//Inizializza la lista dei portali attivi
	public static void inizializzaPortaliAttivi(J2Web_UI j2Web_UI) {	

		//Classloader per il recupero delle risorse esterne
		ClassLoader cl = j2Web_UI.getClass().getClassLoader();

		ImageIcon _portaleMLSIcon  = new ImageIcon(cl.getResource("images/_nektasoft.png"));
		PortaleWeb _portaleMLS = new _portaleMLS(_portaleMLSIcon, "1 - Portale Multi Level Sharing", "001", true);
		J2Web_UI.listPortaliSincronizzazione.add(_portaleMLS);

		ImageIcon _autoscout24ItIcon  = new ImageIcon(cl.getResource("images/_autoscout24It.png"));
		PortaleWeb _autoscout24It = new _autoscout24It(_autoscout24ItIcon, "2 - autoscout24.it", "002", false);
		J2Web_UI.listPortaliSincronizzazione.add(_autoscout24It);

		ImageIcon _cuboAutoIcon  = new ImageIcon(cl.getResource("images/_cuboAutoIt.png"));
		PortaleWeb _cuboAutoIt = new _cuboAutoIt(_cuboAutoIcon, "3 - cuboauto.it", "003", true);
		J2Web_UI.listPortaliSincronizzazione.add(_cuboAutoIt);

		ImageIcon _autosupermarketIcon  = new ImageIcon(cl.getResource("images/_autosupermarketIt.png"));
		PortaleWeb _autosupermarketIt = new _autosupermarketIt(_autosupermarketIcon, "4 - autosupermarket.it", "004", false);
		J2Web_UI.listPortaliSincronizzazione.add(_autosupermarketIt);

		/*ImageIcon _automobileIcon  = new ImageIcon(cl.getResource("images/automobileIt.png"));
		PortaleWeb _automobileIt = new _automobileIt(_automobileIcon, "5 - automobile.it", "005", true);
		J2Web_UI.listPortaliSincronizzazione.add(_automobileIt);*/

		ImageIcon _vendiautoComIcon  = new ImageIcon(cl.getResource("images/_vendiautoCom.png"));
		PortaleWeb _vendiautoCom = new _vendiautoCom(_vendiautoComIcon, "5 - vendiauto.com", "005", true);
		J2Web_UI.listPortaliSincronizzazione.add(_vendiautoCom);

		ImageIcon _subitoItIcon  = new ImageIcon(cl.getResource("images/_subitoIt.png"));
		PortaleWeb _subitoIt = new _subitoIt(_subitoItIcon, "6 - subito.it", "006", true);
		J2Web_UI.listPortaliSincronizzazione.add(_subitoIt);
		
		ImageIcon _autoprontaconsegnaComIcon  = new ImageIcon(cl.getResource("images/_autoprontaconsegnaCom.png"));
		PortaleWeb _autoprontaconsegnaCom = new _autoprontaconsegnaCom(_autoprontaconsegnaComIcon, "7 - autoprontaconsegna.com", "005", true);
		J2Web_UI.listPortaliSincronizzazione.add(_autoprontaconsegnaCom);

		/*ImageIcon _kijijiItIcon  = new ImageIcon(cl.getResource("images/_kijijiIt.png"));
		PortaleWeb _kijijiIt = new _kijijiIt(_kijijiItIcon, "5 - kijiji.it", "005", true);
		J2Web_UI.listPortaliSincronizzazione.add(_kijijiIt);*/

	}

	//Controlla l'esistenza della cartella di sistema ed eventualmente crea l'albero di directory j2web
	public static void  checkPaths() {

		File dirJ2web = new File(pathJ2web);
		File dirSchede = new File(pathSchede);

		//If the directory does not exist, create it
		if (!dirJ2web.exists()) {
			System.out.println("...creating directory: " + pathJ2web + "...");
			boolean result = dirJ2web.mkdir();  

			if(result) {    
				System.out.println("/n...DIR j2web created...");  
			}
		}

		// if the directory does not exist, create it
		if (!dirSchede.exists()) {
			System.out.println("...creating directory: " + pathJ2web + "...");
			boolean result = dirSchede.mkdir();  

			if(result) {    
				System.out.println("/n...DIR schede created...");  
			}
		}

	}

	//Funzione di tracking eventi su j2web
	public static void  trackEvent(String eventAction, String eventLabel) throws IOException {

		if(eventLabel==null) {eventLabel="";}

		HttpPortalGetConnection trackEvent = new HttpPortalGetConnection();
		try {
			trackEvent.get("GET alla risorsa di tracking", urlTrackingRemoto + "?eventAction=" + URLEncoder.encode(eventAction, "UTF-8") + "&eventLabel=" + URLEncoder.encode(eventLabel, "UTF-8"), null, null, false);
		} catch (IOException e) {
			e.printStackTrace();
			throw(new IOException(e));
		}
	}


}