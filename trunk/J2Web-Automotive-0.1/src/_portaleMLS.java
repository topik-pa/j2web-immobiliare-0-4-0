/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.swing.ImageIcon;


/**
 *
 * @author marco
 */

//La classe principale
public class _portaleMLS extends PortaleWeb {     
	
	/*Dati di accesso al DB MLS remoto*/
	String host = "sql.j2webstudio.it";
	String port = "3306";
	String charset = "latin1";
	String dbname = "j2webstu85037";
	String username = "j2webstu85037";
	String password = "j2we20858";
	
	//La query di inserimento
	String query;
	
	//Inizializzo i dati della da inviare con dei parametri di default
	String IdScheda = "";
    String Marca = "";
    String Modello = "";
    String Versione = "";
    int MeseImmatricolazione = 0;
    int AnnoImmatricolazione = 0;
    String Carburante = "";
    String Tipologia = "";
    String Carrozzeria = "";
    int PostiASedere = 0;
    int PotenzaKW = 0;
    int PotenzaCV = 0;
    String ColoreEsterno = "";
    int Metallizzato = 0;
    int PrecedentiProprietari = 0;
    int Chilometraggio = 0;
    int Prezzo = 0;
    int Trattabile = 0;
    int IVADeducibile = 0;
    String FinitureInterni = "";
    String ColoreInterni = "";
    int ABS = 0;
    int Airbag = 0;
    int Antifurto = 0;
    int ChiusuraCentralizzata = 0;
    int ControlloAutomTrazione = 0;
    int ESP = 0;
    int Immobilizer = 0;
    int FreniADisco = 0;
    int AlzacristalliElettrici = 0;
    int Clima = 0;
    int NavigatoreSatellitare = 0;
    int RadioCD = 0;
    int ParkDistControl = 0;
    int SediliRiscaldati = 0;
    int Servosterzo = 0;
    int VolanteMultifunzione = 0;
    int Handicap = 0;
    int CerchiInLega = 0;
    int GancioTraino = 0;
    int Portapacchi = 0;
    int SediliSportivi = 0;
    String Motore = "";
    String Cambio = "";
    int NumRapporti = 0;
    int Cilindrata = 0;
    String ClasseEmissione = "";
    float ConsumoMedio = 0.0f;
    File Immagine1;
    String Immagine1Path;
    String UrlYT = "";
    String Descrizione = "";
    String RagioneSociale = "";
    String Indirizzo = "";
    String TelefonoGenerico = "";
    String NomeReferente = "";
    String TelefonoReferente = "";
    String EmailReferente = "";
	
   
	//Costruttore
	public _portaleMLS (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(icon, valoreLabel, idPortale, isActive);
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("(MLS) Inserimento scheda: " + scheda.codiceScheda + "...");	
    	
    		//I dati da inviare sono valorizzati con i dati presi dalla scheda
            IdScheda = "'" + scheda.codiceScheda + "'";
            Marca = "'" + scheda.marcaVeicolo + "'";
            Modello ="'" +  scheda.modelloVeicolo + "'";
            Versione = "'" + scheda.versioneVeicolo + "'";
            MeseImmatricolazione = scheda.meseImmatricolazioneVeicoloIndex;
            AnnoImmatricolazione = scheda.annoImmatricolazioneVeicoloIndex;
            Carburante = "'" + scheda.carburanteVeicolo + "'";
            Tipologia = "'" + scheda.tipologiaVeicolo + "'";
            Carrozzeria = "'" + scheda.carrozzeriaVeicolo + "'";
            PostiASedere = scheda.postiASedereVeicoloIndex;
            PotenzaKW = Integer.parseInt(scheda.KWVeicolo);
            PotenzaCV = Integer.parseInt(scheda.CVVeicolo);
            ColoreEsterno = "'" + scheda.coloreEsternoVeicolo + "'";
            Metallizzato = scheda.coloreMetalizzato?1:0;
            PrecedentiProprietari = scheda.numeroPrecedentiProprietariVeicoloIndex;
            if(!scheda.chilometraggioVeicolo.equals("")){
            	Chilometraggio = Integer.parseInt(scheda.chilometraggioVeicolo);
            }          	
            Prezzo = Integer.parseInt(scheda.prezzoVeicolo);
            Trattabile = scheda.prezzoTrattabile?1:0;
            IVADeducibile = scheda.ivaDeducibile?1:0;
            FinitureInterni = "'" + scheda.finitureInterneVeicolo + "'";
            ColoreInterni = "'" + scheda.coloreInterniVeicolo + "'";
            ABS = scheda.disponibilitaABS?1:0;
            Airbag = scheda.disponibilitaAirBag?1:0;
            Antifurto = scheda.disponibilitaAntifurto?1:0;
            ChiusuraCentralizzata = scheda.disponibilitaChiusuraCentralizzata?1:0;
            ControlloAutomTrazione = scheda.disponibilitaContrlAutomTrazione?1:0;
            ESP  = scheda.disponibilitaESP?1:0;
            Immobilizer = scheda.disponibilitaImmobilizer?1:0;
            FreniADisco = scheda.disponibilitaFreniADisco?1:0;
            AlzacristalliElettrici = scheda.disponibilitaAlzacristalliElettrici?1:0;
            Clima = scheda.disponibilitaClima?1:0;
            NavigatoreSatellitare = scheda.disponibilitaNavigatoreSattelitare?1:0;
            RadioCD = scheda.disponibilitaRadioOLettoreCD?1:0;
            ParkDistControl = scheda.disponibilitaParkDistControl?1:0;
            SediliRiscaldati = scheda.disponibilitaSediliRiscaldati?1:0;
            Servosterzo = scheda.disponibilitaServoSterzo?1:0;
            VolanteMultifunzione = scheda.disponibilitaVolanteMultifunzione?1:0;
            Handicap = scheda.disponibilitaAllestimentoHandicap?1:0;
            CerchiInLega = scheda.disponibilitaCerchiInLega?1:0;
            GancioTraino = scheda.disponibilitaGancioTraino?1:0;
            Portapacchi = scheda.disponibilitaPortaPacchi?1:0;
            SediliSportivi = scheda.disponibilitaSediliSportivi?1:0;
            Motore = "'" + scheda.tipologiaMotoreVeicolo + "'";
            Cambio = "'" + scheda.tipologiaCambioVeicolo + "'";
            NumRapporti = Integer.parseInt(scheda.numeroRapportiVeicolo);
            Cilindrata = Integer.parseInt(scheda.cilindrataVeicolo);
            ClasseEmissione = "'" + scheda.classeEmissioniVeicolo + "'";
            if(!scheda.comsumeMedioVeicolo.equals("")){ConsumoMedio = Float.parseFloat(scheda.comsumeMedioVeicolo);}
            if(scheda.arrayImages[1]!=null){
            	Immagine1 = scheda.arrayImages[1];
            	Immagine1Path = Immagine1.getAbsolutePath();
            }
            UrlYT = "'" + scheda.urlVideoYouTube + "'";
            Descrizione = "'" + scheda.descrizioneVeicolo + "'";
            RagioneSociale = "'" + scheda.ragioneSociale + "'";
            Indirizzo = "'" + scheda.Indirizzo + "'";
            TelefonoGenerico = "'" + scheda.Telefono + "'";
            NomeReferente = "'" + scheda.nomeReferente + "'";
            TelefonoReferente = "'" + scheda.TelefonoReferente + "'";
            EmailReferente = "'" + scheda.emailReferente + "'";
            
            //Costruisco la query sql
            String querySQL_1 = "INSERT INTO autoveicoli(";
            String querySQL_2 = "IdScheda,        Marca,        Modello,        Versione,        MeseImmatricolazione,        AnnoImmatricolazione,        Carburante,        Tipologia,        Carrozzeria,        PostiASedere,        PotenzaKW,        PotenzaCV,        ColoreEsterno,        Metallizzato,        PrecedentiProprietari,        Chilometraggio,        Prezzo,        Trattabile,        IVADeducibile,        FinitureInterni,        ColoreInterni,        ABS,        Airbag,        Antifurto,        ChiusuraCentralizzata,        ControlloAutomTrazione,        ESP,        Immobilizer,        FreniADisco,        AlzacristalliElettrici,        Clima,        NavigatoreSatellitare,        RadioCD,        ParkDistControl,        SediliRiscaldati,        Servosterzo,        VolanteMultifunzione,        Handicap,        CerchiInLega,        GancioTraino,        Portapacchi,        SediliSportivi,        Motore,        Cambio,        NumRapporti,        Cilindrata,        ClasseEmissione,        ConsumoMedio,        Immagine1,        UrlYT,        Descrizione,        RagioneSociale,        Indirizzo,        TelefonoGenerico,        NomeReferente,        TelefonoReferente,        EmailReferente";
            String querySQL_4 =  IdScheda + "," + Marca + "," + Modello + "," + Versione + "," + MeseImmatricolazione + "," + AnnoImmatricolazione + "," + Carburante + "," + Tipologia + "," + Carrozzeria + "," + PostiASedere + "," + PotenzaKW + "," + PotenzaCV + "," + ColoreEsterno + "," + Metallizzato + "," + PrecedentiProprietari + "," + Chilometraggio + "," + Prezzo + "," + Trattabile + "," + IVADeducibile + "," + FinitureInterni + "," + ColoreInterni + "," + ABS + "," + Airbag + "," + Antifurto + "," + ChiusuraCentralizzata + "," + ControlloAutomTrazione + "," + ESP + "," + Immobilizer + "," + FreniADisco + "," + AlzacristalliElettrici + "," + Clima + "," + NavigatoreSatellitare + "," + RadioCD + "," + ParkDistControl + "," + SediliRiscaldati + "," + Servosterzo + "," + VolanteMultifunzione + "," + Handicap + "," + CerchiInLega + "," + GancioTraino + "," + Portapacchi + "," + SediliSportivi + "," + Motore + "," + Cambio + "," + NumRapporti + "," + Cilindrata + "," + ClasseEmissione + "," + ConsumoMedio + "," + "LOAD_FILE(\'/img.jpg\')"+    ","+ UrlYT + "," + Descrizione + "," + RagioneSociale + "," + Indirizzo + "," + TelefonoGenerico + "," + NomeReferente + "," + TelefonoReferente + "," + EmailReferente;
            String querySQL_3 = ") VALUES(";
            String querySQL_5 = ")";
            
            String querySQL_2_normalized = querySQL_2.replace(" ", "");
            //String querySQL_4_normalized = querySQL_4.replace("'", "\'");
            String querySQL = querySQL_1 + querySQL_2_normalized + querySQL_3 + querySQL_4 + querySQL_5;
            String encodedQuerySQL = "";
            
            System.out.println("test query: " + querySQL);
            
            try {
            	encodedQuerySQL = URLEncoder.encode(querySQL, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            System.out.println("test encoded query: " + encodedQuerySQL);
            //Invio la richiesta al server remoto
    		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
    		try {
    			getInfoVeicolo.get("GET", urlHTTPTunnel + "?host=" + host + "&port=" + port + "&charset=" + charset + "&dbname=" + dbname + "&username=" + username + "&password=" + password + "&query=" + encodedQuerySQL, true);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		//Tracking dell'evento inserzione di una scheda veicolo in MLS
			System.out.print("Tracking dell'evento inserzione di una scheda veicolo in MLS...");
			j2web.trackEvent("inserimentoMLSSchedaVeicolo_j2web_"+j2web_version, EMAIL_UTENTE+"_"+scheda.codiceScheda);
			System.out.print(" fatto." + "\n");


    	return true;     
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
		
		
	

			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veicoli", "testuser", "test623");

            //pst = con.prepareStatement("DELETE FROM autoveicoli WHERE IdScheda = " + "'" + scheda.codiceScheda + "'");            
                        
            //La query sql viene trasformata in stringa e tradotta in formato urlencoded
            String encodedQuery="";
            //String statementText = pst.toString();
            //query = statementText.substring( statementText.indexOf( ": " ) + 2 );
         
            try {
				encodedQuery = URLEncoder.encode(query, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                        
            //Invio la richiesta al server remoto
    		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
    		try {
    			getInfoVeicolo.get("GET", urlHTTPTunnel + "?host=" + host + "&port=" + port + "&charset=" + charset + "&dbname=" + dbname + "&username=" + username + "&password=" + password + "&query=" + encodedQuery, true);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

        

  		return true;
	
	}
		
	
}