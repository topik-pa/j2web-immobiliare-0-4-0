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
		System.out.print("Popolo le mappe degli indirizzi...");
		inizializzaMappaRegioneProvincia();
		inizializzaMappaProvinciaComuni();
		System.out.print(" fatto." + "\n");
		
		//Popolo la mappa dei limiti di caratteri per i campi testuali
		System.out.print("Popolo la mappa dei limiti di caratteri per i campi testuali...");
		inizializzaMappaLimiteCaratteri();
		System.out.print(" fatto." + "\n");
			
		System.out.print("Lancio la GUI...");
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
		System.out.print(" fatto." + "\n");
	}
	
	
	//Caricamento da dat
	@SuppressWarnings("unchecked")
	public static void caricaListPortaliDaDat() {
		File file = new File(datFilePath);
    	if(file.exists() && file.length()!=0) {
    			System.out.print("File .dat schede trovato. Caricamento dati...");
    			try {
    				ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file));
    				j2web_GUI.listSchedeImmobile = (LinkedList<SchedaImmobile>)inputFile.readObject();
    				inputFile.close();
    			} catch (FileNotFoundException e) {
    				JOptionPane.showMessageDialog(null, ModalWindowsDialogs[1], "Errore", JOptionPane.ERROR_MESSAGE);
    				e.printStackTrace();
    			} catch (IOException e) {
    				JOptionPane.showMessageDialog(null, ModalWindowsDialogs[2], "Errore", JOptionPane.ERROR_MESSAGE);
    				e.printStackTrace();
    			} catch (ClassNotFoundException e) {
    				JOptionPane.showMessageDialog(null, ModalWindowsDialogs[12], "Errore", JOptionPane.ERROR_MESSAGE);
    			}
    			System.out.print(" fatto." + "\n");
    		}   				
    	else {
    		System.out.println("File .dat schede non trovato.");
    	}
	}
	
	//Salvataggio su dat
	public static void salvaListPortaliSuDat() {
		try {
		   File file = new File(datFilePath);
	    	if(file.exists()) {
	    		System.out.print("File .dat schede trovato. Salvataggio dati...");
	    		ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(j2web_GUI.listSchedeImmobile);
				outputFile.close();
				System.out.print(" fatto." + "\n");
	    	}
	    	else {	    		
				FileOutputStream newFile = new FileOutputStream(datFilePath);
				System.out.print("File .dat non schede trovato. Creazione del file..." + newFile.toString());
				ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file));
				outputFile.writeObject(j2web_GUI.listSchedeImmobile);
				outputFile.close();
				System.out.print(" fatto." + "\n");
	    	}
		}
		catch (FileNotFoundException e) {		
	        JOptionPane.showMessageDialog(null, ModalWindowsDialogs[1], "Errore", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, ModalWindowsDialogs[2], "Errore", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	//public static void 
	
	//Inizializza la mappa delle regioni e provincie
	public static void inizializzaMappaRegioneProvincia() {		
		regioneProvincie.put("Abruzzo", provincieAbruzzo);
		regioneProvincie.put("Basilicata", provincieBasilicata);
		regioneProvincie.put("Calabria", provincieCalabria);
		regioneProvincie.put("Campania", provincieCampania);
		regioneProvincie.put("Emilia-Romagna", provincieEmiliaRomagna);
		regioneProvincie.put("Friuli-Venezia Giulia", provincieFriuliVeneziaGiulia);
		regioneProvincie.put("Lazio", provincieLazio);
		regioneProvincie.put("Liguria", provincieLiguria);
		regioneProvincie.put("Lombardia", provincieLombardia);
		regioneProvincie.put("Marche", provincieMarche);
		regioneProvincie.put("Molise", provincieMolise);
		regioneProvincie.put("Piemonte", provinciePiemonte);
		regioneProvincie.put("Puglia", provinciePuglia);
		regioneProvincie.put("Sardegna", provincieSardegna);
		regioneProvincie.put("Sicilia", provincieSicilia);
		regioneProvincie.put("Toscana", provincieToscana);
		regioneProvincie.put("Trentino-Alto Adige", provincieTrentinoAltoAdige);
		regioneProvincie.put("Umbria", provincieUmbria);
		regioneProvincie.put("Valle d'Aosta", provincieValleDAosta);
		regioneProvincie.put("Veneto", provincieVeneto);
	}
		
	//Inizializza la mappa delle regioni e provincie
	static void inizializzaMappaProvinciaComuni() {		
		provinciaComuni.put("L'Aquila", comuniLAquila);
		provinciaComuni.put("Chieti", comuniChieti);
		provinciaComuni.put("Pescara", comuniPescara);
		provinciaComuni.put("Teramo", comuniTeramo);
		provinciaComuni.put("Matera", comuniMatera);
		provinciaComuni.put("Potenza", comuniPotenza);
		provinciaComuni.put("Cosenza", comuniCosenza);
		provinciaComuni.put("Catanzaro", comuniCatanzaro);
		provinciaComuni.put("Crotone", comuniCrotone);
		provinciaComuni.put("Reggio Calabria", comuniReggioCalabria);
		provinciaComuni.put("Vibo Valentia", comuniViboValentia);
		provinciaComuni.put("Avellino", comuniAvellino);
		provinciaComuni.put("Benevento", comuniBenevento);
		provinciaComuni.put("Caserta", comuniCaserta);
		provinciaComuni.put("Napoli", comuniNapoli);
		provinciaComuni.put("Salerno", comuniSalerno);
		provinciaComuni.put("Bologna", comuniBologna);
		provinciaComuni.put("Forlì-Cesena", comuniForlìCesena);
		provinciaComuni.put("Ferrara", comuniFerrara);
		provinciaComuni.put("Modena", comuniModena);
		provinciaComuni.put("Piacenza", comuniPiacenza);
		provinciaComuni.put("Parma", comuniParma);
		provinciaComuni.put("Ravenna", comuniRavenna);
		provinciaComuni.put("Reggio Emilia", comuniReggioEmilia);
		provinciaComuni.put("Rimini", comuniRimini);
		provinciaComuni.put("Gorizia", comuniGorizia);
		provinciaComuni.put("Pordenone", comuniPordenone);
		provinciaComuni.put("Trieste", comuniTrieste);
		provinciaComuni.put("Udine", comuniUdine);
		provinciaComuni.put("L'Aquila", comuniLAquila);
		provinciaComuni.put("Frosinone", comuniFrosinone);
		provinciaComuni.put("Latina", comuniLatina);
		provinciaComuni.put("Rieti", comuniRieti);
		provinciaComuni.put("Roma", comuniRoma);
		provinciaComuni.put("Viterbo", comuniViterbo);
		provinciaComuni.put("Genova", comuniGenova);
		provinciaComuni.put("Imperia", comuniImperia);
		provinciaComuni.put("La Spezia", comuniLaSpezia);
		provinciaComuni.put("Savona", comuniSavona);
		provinciaComuni.put("Bergamo", comuniBergamo);
		provinciaComuni.put("Brescia", comuniBrescia);
		provinciaComuni.put("Como", comuniComo);
		provinciaComuni.put("Cremona", comuniCremona);
		provinciaComuni.put("Lecco", comuniLecco);
		provinciaComuni.put("Lodi", comuniLodi);
		provinciaComuni.put("Milano", comuniMilano);
		provinciaComuni.put("Mantova", comuniMantova);
		provinciaComuni.put("Pavia", comuniPavia);
		provinciaComuni.put("Sondrio", comuniSondrio);
		provinciaComuni.put("Varese", comuniVarese);
		provinciaComuni.put("Ancona", comuniAncona);
		provinciaComuni.put("Ascoli Piceno", comuniAscoliPiceno);
		provinciaComuni.put("Pesaro e Urbino", comuniPesaroEUrbino);
		provinciaComuni.put("Campobasso", comuniCampobasso);
		provinciaComuni.put("Isernia", comuniIsernia);
		provinciaComuni.put("Alessandria", comuniAlessandria);
		provinciaComuni.put("Asti", comuniAsti);
		provinciaComuni.put("Biella", comuniBiella);
		provinciaComuni.put("Cuneo", comuniCuneo);
		provinciaComuni.put("Novara", comuniNovara);
		provinciaComuni.put("Torino", comuniTorino);
		provinciaComuni.put("Verbano-Cusio-Ossola", comuniVerbanoCusioOssola);
		provinciaComuni.put("Vercelli", comuniVercelli);
		provinciaComuni.put("Bari", comuniBari);
		provinciaComuni.put("Brindisi", comuniBrindisi);
		provinciaComuni.put("Foggia", comuniFoggia);
		provinciaComuni.put("Lecce", comuniLecce);
		provinciaComuni.put("Taranto", comuniTaranto);
		provinciaComuni.put("Olbia-Tempio", comuniOlbiaTempio);
		provinciaComuni.put("Ogliastra", comuniOgliastra);
		provinciaComuni.put("Medio Campidano", comuniMedioCampidano);
		provinciaComuni.put("Carbonia Iglesias", comuniCarboniaIglesias);
		provinciaComuni.put("Cagliari", comuniCagliari);
		provinciaComuni.put("Nuoro", comuniNuoro);
		provinciaComuni.put("Oristano", comuniOristano);
		provinciaComuni.put("Sassari", comuniSassari);
		provinciaComuni.put("Agrigento", comuniAgrigento);
		provinciaComuni.put("Caltanissetta", comuniCaltanissetta);
		provinciaComuni.put("Catania", comuniCatania);
		provinciaComuni.put("Enna", comuniEnna);
		provinciaComuni.put("Messina", comuniMessina);
		provinciaComuni.put("Palermo", comuniPalermo);
		provinciaComuni.put("Ragusa", comuniRagusa);
		provinciaComuni.put("Siracusa", comuniSiracusa);
		provinciaComuni.put("Trapani", comuniTrapani);
		provinciaComuni.put("Arezzo", comuniArezzo);
		provinciaComuni.put("Firenza", comuniFirenze);
		provinciaComuni.put("Grosseto", comuniGrosseto);
		provinciaComuni.put("Livorno", comuniLivorno);
		provinciaComuni.put("Lucca", comuniLucca);
		provinciaComuni.put("Massa-Carrara", comuniMassaCarrara);
		provinciaComuni.put("Pisa", comuniPisa);
		provinciaComuni.put("Siena", comuniSiena);
		provinciaComuni.put("Bolzano", comuniBolzano);
		provinciaComuni.put("Trento", comuniTrento);
		provinciaComuni.put("Perugia", comuniPerugia);
		provinciaComuni.put("Terni", comuniTerni);
		provinciaComuni.put("Aosta", comuniAosta);
		provinciaComuni.put("Belluno", comuniBelluno);
		provinciaComuni.put("Padova", comuniPadova);
		provinciaComuni.put("Rovigo", comuniRovigo);
		provinciaComuni.put("Treviso", comuniTreviso);
		provinciaComuni.put("Venezia", comuniVenezia);
		provinciaComuni.put("Vicenza", comuniVicenza);
		provinciaComuni.put("Verona", comuniVerona);
	}
	
	//Inizializza la mappa delle regioni e provincie
	public static void inizializzaMappaLimiteCaratteri() {	
		maxCaratteri.put("txtCodiceInserzione", 10);
		maxCaratteri.put("textTitoloAnnuncio", 150);
		maxCaratteri.put("textFieldCap", 7);
		maxCaratteri.put("textFieldIndirizzoLocalita", 70);
		maxCaratteri.put("textAreaTestoAnnuncio", 2000);
		maxCaratteri.put("textSuperficieImmobile", 10);
		maxCaratteri.put("textFieldPrezzoImmobile", 15);
		maxCaratteri.put("textFieldAnnoCostruzione", 4);
		maxCaratteri.put("textFieldNumeroTotalePiani", 2);
	}
}