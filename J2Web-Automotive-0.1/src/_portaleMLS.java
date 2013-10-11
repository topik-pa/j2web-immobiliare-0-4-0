/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author marco
 */

//La classe principale
public class _portaleMLS extends PortaleWeb {     

    //Variabili generali
	 Connection con = null;
     PreparedStatement pst = null;

     String url = "jdbc:mysql://localhost:3306/veicoli";
     String user = "testuser";
     String password = "test623";

    	
    //private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
    private String codiceInserzione;
    private boolean inserimentoOK = false;
    private boolean debugMode = true;
    
  

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParametri;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;
    
    //Lista degli headers inviati in una singola connessione
    List<NameValuePair> requestHeaders; 

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;
    
    //Lista di alcuni (non necessariamente tutti) parametri inviati
   
	//Costruttore
	public _portaleMLS (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(urlIcona, valoreLabel, idPortale, isActive);
		
		//Inizializzo il cookie di sessione 
		SESSIONCOOKIENAME = "";
		SESSIONCOOKIEDOMAIN = "";
		
		//Inizializzo la mappa dei parametri
		mappaDeiParametri =  new Hashtable<String,String>();
	    
		//Inizializzo la lista che conterrà i parametri da inviare
	    postParameters = new ArrayList<NameValuePair>();
	    
	    //Inizializzo la lista che conterrà gli headers
	    requestHeaders = new ArrayList<NameValuePair>();
	    
	    
	    
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.idScheda + "...");
    	
    	
    	try {

            String author = "Trygve Gulbranssen";
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO Autoveicoli(IdScheda, Marca, Modello, Versione, MeseImmatricolazione, AnnoImmatricolazione, Carburante, Tipologia, Carrozzeria, PostiASedere, PotenzaKW, PotenzaCV, ColoreEsterno, Metallizzato, PrecedentiProprietari, Chilometraggio, Prezzo, Trattabile, IVADeducibile, FinitureInterni, ColoreInterni, ABS, Airbag, Antifurto, ChiusuraCentralizzata, ControlloAutomTrazione, ESP, Immobilizer, FreniADisco, AlzacristalliElettrici, Clima, NavigatoreSatellitare, RadioCD, ParkDistControl, SediliRiscaldati, Servosterzo, VolanteMultifunzione, Handicap, CerchiInLega, GancioTraino, Portapacchi, SediliSportivi, Motore, Cambio, NumRapporti, Cilindrata, ClasseEmissione, ConsumoMedio, Immagine1, Immagine2, Immagine3, Immagine4, Immagine5, Immagine6, Immagine7, Immagine8, Immagine9, Immagine10, UrlYT, Descrizione) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //pst.setInt(1, 2);
            pst.setString(1, scheda.codiceScheda);
            pst.setString(2, "Volvo");
            pst.setString(3, "V40");
            pst.setString(4, "2200 Twin Spark");
            pst.setInt(5, 7);
            pst.setInt(6, 1980);
            pst.setString(7, "Benzina");
            pst.setString(8, "Come nuova");
            pst.setString(9, "Ammaccata");
            pst.setInt(10, 5);
            pst.setInt(11, 55);
            pst.setInt(12, 120);
            pst.setString(13, "Rosso");
            pst.setInt(14, 1);
            pst.setInt(15, 1);
            pst.setInt(16, 120000);
            pst.setInt(17, 3200);
            pst.setInt(18, 0);
            pst.setInt(19, 0);
            pst.setString(20, "Bella dentro");
            pst.setString(21, "Grigio");
            pst.setInt(22, 1);
            pst.setInt(23, 0);
            pst.setInt(24, 1);
            pst.setInt(25, 0);
            pst.setInt(26, 1);
            pst.setInt(27, 0);
            pst.setInt(28, 1);
            pst.setInt(29, 0);
            pst.setInt(30, 1);
            pst.setInt(31, 0);
            pst.setInt(32, 1);
            pst.setInt(33, 0);
            pst.setInt(34, 1);
            pst.setInt(35, 0);
            pst.setInt(36, 1);
            pst.setInt(37, 0);
            pst.setInt(38, 1);
            pst.setInt(39, 0);
            pst.setInt(40, 1);
            pst.setInt(41, 0);
            pst.setInt(42, 1);
            pst.setString(43, "Anteriore");
            pst.setString(44, "Manuale");
            pst.setInt(45, 5);
            pst.setInt(46, 2199);
            pst.setString(47, "Euro 2");
            pst.setFloat(48, 12.7f);
            
            FileInputStream fis = null;
            try {
				fis = new FileInputStream(scheda.imgFile1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            pst.setBinaryStream(49,fis,(int)scheda.imgFile1.length());
            pst.setNull(50, java.sql.Types.BLOB);
            pst.setNull(51, java.sql.Types.BLOB);
            pst.setNull(52, java.sql.Types.BLOB);
            pst.setNull(53, java.sql.Types.BLOB);
            pst.setNull(54, java.sql.Types.BLOB);
            pst.setNull(55, java.sql.Types.BLOB);
            pst.setNull(56, java.sql.Types.BLOB);
            pst.setNull(57, java.sql.Types.BLOB);
            pst.setNull(58, java.sql.Types.BLOB);
            pst.setString(59, "YT url....");
            pst.setString(60, "Descrizione completa... è");
            
            
            //PreparedStatement pst2 = con.prepareStatement("INSERT INTO Autoveicoli(Marca) VALUES(?)");
           // pst2.setString(1, "Autobianchi");
            
            pst.executeUpdate();
            //pst2.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(_portaleMLS.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(_portaleMLS.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    	
		
    	return inserimentoOK;     
	}
	
    
    //Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.idScheda + "...");
		//Apro il browser e inserisco credenziali		
		/*try {
			String url = URLROOT;
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
			
		} catch (IOException e ) {
			//Eventualità non gestita
		}*/

		return true;
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.idScheda + "...");
		
		
		try {

            //String author = "Trygve Gulbranssen";
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("DELETE FROM Autoveicoli WHERE IdScheda = " + "'" + scheda.codiceScheda + "'");
            
            
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(_portaleMLS.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(_portaleMLS.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

  		return true;
	
	}
		
	
}