/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
	//Connection con = null;
	//PreparedStatement pst = null;
	
	String urlHTTPTunnel = "http://www.j2webstudio.it/mysqltunnel.php";
	String host = "sql.j2webstudio.it";
	String port = "3306";
	String charset = "latin1";
	String dbname = "j2webstu85037";
	String username = "j2webstu85037";
	String password = "j2we20858";
	String query;
	
	
	
	
	
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
    String UrlYT = "";
    String Descrizione = "";
    String RagioneSociale = "";
    String Indirizzo = "";
    String TelefonoGenerico = "";
    String NomeReferente = "";
    String TelefonoReferente = "";
    String EmailReferente = "";
	
   
	//Costruttore
	public _portaleMLS (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(urlIcona, valoreLabel, idPortale, isActive);
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.idScheda + "...");	
    	

            IdScheda = "'" + scheda.codiceScheda + "'";
            Marca = "'" + scheda.marcaVeicolo + "'";
            Modello ="'" +  scheda.modelloVeicolo + "'";
            Versione = "'" + scheda.versioneVeicolo + "'";
            MeseImmatricolazione = scheda.meseImmatricolazioneVeicoloIndex;
            AnnoImmatricolazione = Integer.parseInt(scheda.annoImmatricolazioneVeicolo);
            Carburante = "'" + scheda.carburanteVeicolo + "'";
            Tipologia = "'" + scheda.tipologiaVeicolo + "'";
            Carrozzeria = "'" + scheda.carrozzeriaVeicolo + "'";
            PostiASedere = scheda.postiASedereVeicoloIndex;
            PotenzaKW = Integer.parseInt(scheda.KWVeicolo);
            PotenzaCV = Integer.parseInt(scheda.CVVeicolo);
            ColoreEsterno = "'" + scheda.coloreEsternoVeicolo + "'";
            Metallizzato = scheda.coloreMetalizzato?1:0;
            PrecedentiProprietari = scheda.numeroPrecedentiProprietariVeicoloIndex;
            Chilometraggio = Integer.parseInt(scheda.chilometraggioVeicolo);
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
            String querySQL_4 =  IdScheda + "," + Marca + "," + Modello + "," + Versione + "," + MeseImmatricolazione + "," + AnnoImmatricolazione + "," + Carburante + "," + Tipologia + "," + Carrozzeria + "," + PostiASedere + "," + PotenzaKW + "," + PotenzaCV + "," + ColoreEsterno + "," + Metallizzato + "," + PrecedentiProprietari + "," + Chilometraggio + "," + Prezzo + "," + Trattabile + "," + IVADeducibile + "," + FinitureInterni + "," + ColoreInterni + "," + ABS + "," + Airbag + "," + Antifurto + "," + ChiusuraCentralizzata + "," + ControlloAutomTrazione + "," + ESP + "," + Immobilizer + "," + FreniADisco + "," + AlzacristalliElettrici + "," + Clima + "," + NavigatoreSatellitare + "," + RadioCD + "," + ParkDistControl + "," + SediliRiscaldati + "," + Servosterzo + "," + VolanteMultifunzione + "," + Handicap + "," + CerchiInLega + "," + GancioTraino + "," + Portapacchi + "," + SediliSportivi + "," + Motore + "," + Cambio + "," + NumRapporti + "," + Cilindrata + "," + ClasseEmissione + "," + ConsumoMedio + "," + "Immagine1"+ ","+ UrlYT + "," + Descrizione + "," + RagioneSociale + "," + Indirizzo + "," + TelefonoGenerico + "," + NomeReferente + "," + TelefonoReferente + "," + EmailReferente;
            String querySQL_3 = ") VALUES(";
            String querySQL_5 = ")";
            
            String querySQL_2_normalized = querySQL_2.replace(" ", "");
            String querySQL_4_normalized = querySQL_4.replace("'", "\'");
            String querySQL = querySQL_1 + querySQL_2_normalized + querySQL_3 + querySQL_4_normalized + querySQL_5;
            String encodedQuerySQL = "";
            
            System.out.println("query: " + querySQL);
            
            // normalizadQuery = querySQL.replace(" ", "");
            try {
            	encodedQuerySQL = URLEncoder.encode(querySQL, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            

            //Costruisco la query sql
            /*pst = con.prepareStatement("INSERT INTO autoveicoli(IdScheda, Marca, Modello, Versione, MeseImmatricolazione, AnnoImmatricolazione, Carburante, Tipologia, Carrozzeria, PostiASedere, PotenzaKW, PotenzaCV, ColoreEsterno, Metallizzato, PrecedentiProprietari, Chilometraggio, Prezzo, Trattabile, IVADeducibile, FinitureInterni, ColoreInterni, ABS, Airbag, Antifurto, ChiusuraCentralizzata, ControlloAutomTrazione, ESP, Immobilizer, FreniADisco, AlzacristalliElettrici, Clima, NavigatoreSatellitare, RadioCD, ParkDistControl, SediliRiscaldati, Servosterzo, VolanteMultifunzione, Handicap, CerchiInLega, GancioTraino, Portapacchi, SediliSportivi, Motore, Cambio, NumRapporti, Cilindrata, ClasseEmissione, ConsumoMedio, UrlYT, Descrizione, RagioneSociale, Indirizzo, TelefonoGenerico, NomeReferente, TelefonoReferente, EmailReferente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, scheda.codiceScheda);
            pst.setString(2, scheda.marcaVeicolo);
            pst.setString(3, scheda.modelloVeicolo);
            pst.setString(4, scheda.versioneVeicolo);
            pst.setInt(5, scheda.meseImmatricolazioneVeicoloIndex);
            pst.setInt(6, Integer.parseInt(scheda.annoImmatricolazioneVeicolo));
            pst.setString(7, scheda.carburanteVeicolo);
            pst.setString(8, scheda.tipologiaVeicolo);
            pst.setString(9, scheda.carrozzeriaVeicolo);
            pst.setInt(10, scheda.postiASedereVeicoloIndex);
            pst.setInt(11, Integer.parseInt(scheda.CVVeicolo));
            pst.setInt(12, Integer.parseInt(scheda.KWVeicolo));
            pst.setString(13, scheda.coloreEsternoVeicolo);
            if (scheda.coloreMetalizzato)
				pst.setInt(14, 1);
			else
				pst.setInt(14, 0);
            pst.setInt(15, scheda.numeroPrecedentiProprietariVeicoloIndex);
            pst.setInt(16, Integer.parseInt(scheda.chilometraggioVeicolo));
            pst.setInt(17, Integer.parseInt(scheda.prezzoVeicolo));
            if (scheda.prezzoTrattabile)
				pst.setInt(18, 1);
			else
				pst.setInt(18, 0);
            if (scheda.ivaDeducibile)
				pst.setInt(19, 1);
			else
				pst.setInt(19, 0);

            pst.setString(20, scheda.finitureInterneVeicolo);
            pst.setString(21, scheda.coloreInterniVeicolo);
            
            if (scheda.disponibilitaABS)
				pst.setInt(22, 1);
			else
				pst.setInt(22, 0);
            if (scheda.disponibilitaAirBag)
				pst.setInt(23, 1);
			else
				pst.setInt(23, 0);
            if (scheda.disponibilitaAntifurto)
				pst.setInt(24, 1);
			else
				pst.setInt(24, 0);
            if (scheda.disponibilitaChiusuraCentralizzata)
				pst.setInt(25, 1);
			else
				pst.setInt(25, 0);
            if (scheda.disponibilitaContrlAutomTrazione)
				pst.setInt(26, 1);
			else
				pst.setInt(26, 0);
            if (scheda.disponibilitaESP)
				pst.setInt(27, 1);
			else
				pst.setInt(27, 0);
            if (scheda.disponibilitaImmobilizer)
				pst.setInt(28, 1);
			else
				pst.setInt(28, 0);
            if (scheda.disponibilitaFreniADisco)
				pst.setInt(29, 1);
			else
				pst.setInt(29, 0);
            if (scheda.disponibilitaAlzacristalliElettrici)
				pst.setInt(30, 1);
			else
				pst.setInt(30, 0);
            if (scheda.disponibilitaClima)
				pst.setInt(31, 1);
			else
				pst.setInt(31, 0);
            if (scheda.disponibilitaNavigatoreSattelitare)
				pst.setInt(32, 1);
			else
				pst.setInt(32, 0);
            if (scheda.disponibilitaRadioOLettoreCD)
				pst.setInt(33, 1);
			else
				pst.setInt(33, 0);
            if (scheda.disponibilitaParkDistControl)
				pst.setInt(34, 1);
			else
				pst.setInt(34, 0);
            if (scheda.disponibilitaSediliRiscaldati)
				pst.setInt(35, 1);
			else
				pst.setInt(35, 0);
            if (scheda.disponibilitaServoSterzo)
				pst.setInt(36, 1);
			else
				pst.setInt(36, 0);
            if (scheda.disponibilitaVolanteMultifunzione)
				pst.setInt(37, 1);
			else
				pst.setInt(37, 0);
            if (scheda.disponibilitaAllestimentoHandicap)
				pst.setInt(38, 1);
			else
				pst.setInt(38, 0);
            if (scheda.disponibilitaCerchiInLega)
				pst.setInt(39, 1);
			else
				pst.setInt(39, 0);
            if (scheda.disponibilitaGancioTraino)
				pst.setInt(40, 1);
			else
				pst.setInt(40, 0);
            if (scheda.disponibilitaPortaPacchi)
				pst.setInt(41, 1);
			else
				pst.setInt(41, 0);
            if (scheda.disponibilitaSediliSportivi)
				pst.setInt(42, 1);
			else
				pst.setInt(42, 0);
           
            pst.setString(43, scheda.tipologiaMotoreVeicolo);
            pst.setString(44, scheda.tipologiaCambioVeicolo);
            pst.setInt(45, Integer.parseInt(scheda.numeroRapportiVeicolo));
            pst.setInt(46, Integer.parseInt(scheda.cilindrataVeicolo));
            pst.setString(47, scheda.classeEmissioniVeicolo);
            pst.setFloat(48, Float.parseFloat(scheda.comsumeMedioVeicolo));*/
            
           /* FileInputStream fis = null;
            try {
				fis = new FileInputStream(scheda.imgFile1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            pst.setBinaryStream(49,fis,(int)scheda.imgFile1.length());*/
            /*pst.setNull(49, java.sql.Types.BLOB);
            pst.setNull(50, java.sql.Types.BLOB);
            pst.setNull(51, java.sql.Types.BLOB);
            pst.setNull(52, java.sql.Types.BLOB);
            pst.setNull(53, java.sql.Types.BLOB);
            pst.setNull(54, java.sql.Types.BLOB);
            pst.setNull(55, java.sql.Types.BLOB);
            pst.setNull(56, java.sql.Types.BLOB);
            pst.setNull(57, java.sql.Types.BLOB);
            pst.setNull(58, java.sql.Types.BLOB);*/
           /* pst.setString(49, scheda.urlVideoYouTube);
            pst.setString(50, scheda.descrizioneVeicolo);
            pst.setString(51, scheda.ragioneSociale);
            pst.setString(52, scheda.Indirizzo);
            pst.setString(53, scheda.Telefono);
            pst.setString(54, scheda.nomeReferente);
            pst.setString(55, scheda.TelefonoReferente);
            pst.setString(56, scheda.emailReferente);*/
            
            
            
            //pst.executeUpdate();
            
            //Invio la richiesta al server remoto
    		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
    		try {
    			getInfoVeicolo.get("GET", urlHTTPTunnel + "?host=" + host + "&port=" + port + "&charset=" + charset + "&dbname=" + dbname + "&username=" + username + "&password=" + password + "&query=" + encodedQuerySQL, true);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}


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