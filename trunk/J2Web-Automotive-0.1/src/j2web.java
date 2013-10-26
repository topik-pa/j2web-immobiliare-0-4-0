import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
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
			
		/*MapModalWindowsDialogs.put("menu_ConfermaEliminaTutteLeSchede", "Eliminazione di tutte le schede salvate. Continuare? ");
				
				
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_IOException", "Errore durante il collegamento al server remoto. ");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_Generic", "Errore in fase di inserimento scheda immobile. ");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseStatusCodeException", "Errore durante il collegamento al server remoto: il server ha risposto con uno status code inatteso. ");		
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseHeaderException", "Errore durante il collegamento al server remoto: il server ha risposto con un response header inatteso. ");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseBodyException", "Errore durante il collegamento al server remoto: il server ha risposto con un response body inatteso. ");
		MapModalWindowsDialogs.put("sendErrorMail_AddressException", "Impossibile inviare la mail: indirizzo non valido. ");
		MapModalWindowsDialogs.put("sendErrorMail_MessagingException", "Impossibile inviare la mail: errore durante l'invio. ");*/	
		
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
		
	}

	//Inizializza la lista dei portali attivi
	public static void inizializzaPortaliAttivi() {	
		
    	PortaleWeb _portaleMLS = new _portaleMLS("./images/_nektasoft.png", "1 - Portale Multi Level Sharing", "001", true);
    	J2Web_UI.listPortaliSincronizzazione.add(_portaleMLS);
    	
    	PortaleWeb _autoscout24It = new _autoscout24It("./images/_autoscout24It.png", "2 - autoscout24.it", "002", true);
    	J2Web_UI.listPortaliSincronizzazione.add(_autoscout24It);
    	
    }
	
}