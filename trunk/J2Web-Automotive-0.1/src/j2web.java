import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class j2web implements parametriGenerali {

	/**
	 * @param args
	 */
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Popolo le mappe degli indirizzi
		/*System.out.print("Popolo le mappe degli indirizzi...");
		inizializzaMappaRegioneProvincia();
		inizializzaMappaProvinciaComuni();
		System.out.print(" fatto." + "\n");*/
		
		//Popolo la mappa dei dialoghi modali
		System.out.print("Popolo la mappa dei dialoghi modali...");
		inizializzaMappaDialoghiModali();
		System.out.print(" fatto." + "\n");
		
		//Popolo la mappa dei limiti di caratteri per i campi testuali
		System.out.print("Popolo la mappa dei limiti di caratteri per i campi testuali...");
		inizializzaMappaLimiteCaratteri();
		System.out.print(" fatto." + "\n");
		
		//Popolo la lista delle schede caricando i dati dal file dat
		System.out.print("Carico la lista delle schede precedentemente salvate...");
		caricaListaSchedeCreate();
		System.out.print(" fatto." + "\n");
		
		//Inizializzo la lista concatenata che contiene le informazioni sui portali immobiliari
    	inizializzaPortaliAttivi();
			
		/*System.out.print("Lancio la GUI...");
		try {
			//Definisce il Look and Feel della GUI
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//Lancia la GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					j2web_GUI imagination = new j2web_GUI();
					imagination.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.print(" fatto." + "\n");*/
	}
	
	
	//Caricamento da dat
	//@SuppressWarnings("unchecked")
	public static void caricaListaSchedeCreate() {
		File file = new File(pathFileDatSchede);
    	if(file.exists() && file.length()!=0) {
    			System.out.print(" File .dat schede trovato. Caricamento dati...");
    			try {
    				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
    				@SuppressWarnings("unchecked")
					LinkedList<SchedaVeicolo> readObject = (LinkedList<SchedaVeicolo>)inputFile.readObject();
					J2Web_UI.listSchedeVeicolo = readObject;
    				inputFile.close();
    			} catch (FileNotFoundException e) {
    				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeImmobiliCreate_FileNotFoundException"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
    				e.printStackTrace();
    			} catch (IOException e) {
    				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeImmobiliCreate_IOException"), "IOException", JOptionPane.ERROR_MESSAGE);
    				e.printStackTrace();
    			} catch (ClassNotFoundException e) {
    				JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("caricaListaSchedeImmobiliCreate_ClassNotFoundException"), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
    			}
    		}   				
    	else {
    		System.out.println(" File .dat schede non trovato o file .dat vuoto.");
    	}
	}
	
	//Salvataggio su dat
	public static void salvaListaSchedeCreate() {
		try {
		   File file = new File(pathFileDatSchede);
	    	if(file.exists()) {
	    		System.out.print("File .dat schede trovato. Salvataggio dati...");
	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(J2Web_UI.listSchedeVeicolo);
				outputFile.close();
				System.out.print(" fatto." + "\n");
	    	}
	    	else {	    		
				FileOutputStream newFile = new FileOutputStream(pathFileDatSchede);
				System.out.print("File .dat non schede trovato. Creazione del file..." + newFile.toString());
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(J2Web_UI.listSchedeVeicolo);
				outputFile.close();
				System.out.print(" fatto." + "\n");
	    	}
		}
		catch (FileNotFoundException e) {		
	        JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaListaSchedeCreate_FileNotFoundException"), "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, MapModalWindowsDialogs.get("salvaListaSchedeCreate_FileNotFoundException"), "IOException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	//Inizializza la mappa delle regioni e provincie
	public static void inizializzaMappaLimiteCaratteri() {	
		/*
		maxCaratteri.put("txtCodiceInserzione", 10);
		maxCaratteri.put("textTitoloAnnuncio", 150);
		maxCaratteri.put("textFieldCap", 7);
		maxCaratteri.put("textFieldIndirizzoLocalita", 70);
		maxCaratteri.put("textAreaTestoAnnuncio", 2000);
		maxCaratteri.put("textSuperficieImmobile", 10);
		maxCaratteri.put("textFieldPrezzoImmobile", 15);
		maxCaratteri.put("textFieldAnnoCostruzione", 4);
		maxCaratteri.put("textFieldNumeroTotalePiani", 2);
		*/
	}

	//Inizializza la mappa dei dialoghi modali
	public static void inizializzaMappaDialoghiModali() {
		MapModalWindowsDialogs.put("caricaListaSchedeImmobiliCreate_FileNotFoundException", "File schede immobili non trovato: non è stato possibile caricare le schede immobili precedentemente salvate. ");
		MapModalWindowsDialogs.put("caricaListaSchedeImmobiliCreate_IOException", "Impossibile accedere al file delle schede immobili: non è stato possibile caricare le schede immobili precedentemente salvate. ");
		MapModalWindowsDialogs.put("caricaListaSchedeImmobiliCreate_ClassNotFoundException", "Errore durante l'accesso al file delle schede immobili: non è stato possibile caricare le schede immobili precedentemente salvate. ");
		MapModalWindowsDialogs.put("salvaListaSchedeImmobiliCreate_FileNotFoundException", "File schede immobili non trovato: non è stato possibile salvare le schede immobili. ");
		MapModalWindowsDialogs.put("menu_ConfermaEliminaTutteLeSchede", "Eliminazione di tutte le schede salvate. Continuare? ");
		MapModalWindowsDialogs.put("creazioneDellaSchedaImmobile_FormNonValido", "Attenzione: alcuni campi obbigatori non sono stati compilati. ");		
		MapModalWindowsDialogs.put("selezioneFileImmagne_SelezioneNonValida", "Attenzione: formato di file non valido. Le immagini devono essere in formato \"jpg\" e di dimensione massima 1 Mega. ");		
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_IOException", "Errore durante il collegamento al server remoto. ");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_Generic", "Errore in fase di inserimento scheda immobile. ");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseStatusCodeException", "Errore durante il collegamento al server remoto: il server ha risposto con uno status code inatteso. ");		
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseHeaderException", "Errore durante il collegamento al server remoto: il server ha risposto con un response header inatteso. ");
		MapModalWindowsDialogs.put("manageErrorsOnPortalSubmission_HttpWrongResponseBodyException", "Errore durante il collegamento al server remoto: il server ha risposto con un response body inatteso. ");
		MapModalWindowsDialogs.put("sendErrorMail_AddressException", "Impossibile inviare la mail: indirizzo non valido. ");
		MapModalWindowsDialogs.put("sendErrorMail_MessagingException", "Impossibile inviare la mail: errore durante l'invio. ");
		MapModalWindowsDialogs.put("caricaTabellaHash_FileNotFoundException", "File hash scheda non trovato: non è stato possibile caricare le impostazioni della scheda. ");
		MapModalWindowsDialogs.put("caricaTabellaHash_IOException", "Impossibile accedere al file hash scheda: non è stato possibile caricare le impostazioni della scheda. ");
		MapModalWindowsDialogs.put("caricaTabellaHash_ClassNotFoundException", "Errore durante l'accesso al file hash scheda: non è stato possibile caricare le impostazioni della scheda. ");
		MapModalWindowsDialogs.put("salvaTabellaHash_FileNotFoundException", "File hash scheda non trovato: non è stato possibile salvare le impostazioni della scheda. ");
		MapModalWindowsDialogs.put("salvaTabellaHash_IOException", "Impossibile accedere al file hash scheda: non è stato possibile salvare le impostazioni della scheda. ");
	}

	//Inizializza la lista dei portali attivi
	public static void inizializzaPortaliAttivi() {	
		
    	PortaleWeb _immobiliareIt = new _immobiliareIt("./images/_immobiliareIt4.jpg", "2 - immobiliare.it", "002", true);
    	J2Web_UI.listPortaliImmobiliari.add(_immobiliareIt);
    	
    	PortaleWeb _immobiliareIt2 = new _immobiliareIt("./images/_immobiliareIt4.jpg", "2 - immobiliare.it", "003", true);
    	J2Web_UI.listPortaliImmobiliari.add(_immobiliareIt2);
    	

    }
	
}