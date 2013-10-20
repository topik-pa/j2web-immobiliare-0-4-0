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
	//String url = "jdbc:mysql://localhost:3306/veicoli";
	String host = "sql.j2webstudio.it";
	String port = "3306";
	String charset = "latin1";
	String dbname = "j2webstu85037";
	String username = "j2webstu85037";
	String password = "j2we20858";
	String query;
	
   
	//Costruttore
	public _portaleMLS (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(urlIcona, valoreLabel, idPortale, isActive);
		
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.idScheda + "...");	
    	
    	

            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veicoli", "testuser", "test623");
            
            //String querySQL = "INSERT INTO autoveicoli(IdScheda, Marca, Modello, Versione, MeseImmatricolazione, AnnoImmatricolazione, Carburante, Tipologia, Carrozzeria, PostiASedere, PotenzaKW, PotenzaCV, ColoreEsterno, Metallizzato, PrecedentiProprietari, Chilometraggio, Prezzo, Trattabile, IVADeducibile, FinitureInterni, ColoreInterni, ABS, Airbag, Antifurto, ChiusuraCentralizzata, ControlloAutomTrazione, ESP, Immobilizer, FreniADisco, AlzacristalliElettrici, Clima, NavigatoreSatellitare, RadioCD, ParkDistControl, SediliRiscaldati, Servosterzo, VolanteMultifunzione, Handicap, CerchiInLega, GancioTraino, Portapacchi, SediliSportivi, Motore, Cambio, NumRapporti, Cilindrata, ClasseEmissione, ConsumoMedio, UrlYT, Descrizione, RagioneSociale, Indirizzo, TelefonoGenerico, NomeReferente, TelefonoReferente, EmailReferente) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            String IdScheda = "'" + scheda.codiceScheda + "'";
            String Marca = "'" + scheda.marcaVeicolo + "'";
            String Modello ="'" +  scheda.modelloVeicolo + "'";
            String Versione = "'" + scheda.versioneVeicolo + "'";
            int MeseImmatricolazione = scheda.meseImmatricolazioneVeicoloIndex;
            int AnnoImmatricolazione = Integer.parseInt(scheda.annoImmatricolazioneVeicolo);
            String Carburante = "'" + scheda.carburanteVeicolo + "'";
            String Tipologia = "'" + scheda.tipologiaVeicolo + "'";
            String Carrozzeria = "'" + scheda.carrozzeriaVeicolo + "'";
            int PostiASedere = scheda.postiASedereVeicoloIndex;
            int PotenzaKW = Integer.parseInt(scheda.KWVeicolo);
            int PotenzaCV = Integer.parseInt(scheda.CVVeicolo);
            String ColoreEsterno = "'" + scheda.coloreEsternoVeicolo + "'";
            int Metallizzato = scheda.coloreMetalizzato?1:0;
            int PrecedentiProprietari = scheda.numeroPrecedentiProprietariVeicoloIndex;
            int Chilometraggio = Integer.parseInt(scheda.chilometraggioVeicolo);
            int Prezzo = Integer.parseInt(scheda.prezzoVeicolo);
            int Trattabile = scheda.prezzoTrattabile?1:0;
            int IVADeducibile = scheda.ivaDeducibile?1:0;
            String FinitureInterni = "'" + scheda.finitureInterneVeicolo + "'";
            String ColoreInterni = "'" + scheda.coloreInterniVeicolo + "'";
            int ABS = scheda.disponibilitaABS?1:0;
            int Airbag = scheda.disponibilitaAirBag?1:0;
            int Antifurto = scheda.disponibilitaAntifurto?1:0;
            int ChiusuraCentralizzata = scheda.disponibilitaChiusuraCentralizzata?1:0;
            int ControlloAutomTrazione = scheda.disponibilitaContrlAutomTrazione?1:0;
            int ESP  = scheda.disponibilitaESP?1:0;
            int Immobilizer = scheda.disponibilitaImmobilizer?1:0;
            int FreniADisco = scheda.disponibilitaFreniADisco?1:0;
            int AlzacristalliElettrici = scheda.disponibilitaAlzacristalliElettrici?1:0;
            int Clima = scheda.disponibilitaClima?1:0;
            int NavigatoreSatellitare = scheda.disponibilitaNavigatoreSattelitare?1:0;
            int RadioCD = scheda.disponibilitaRadioOLettoreCD?1:0;
            int ParkDistControl = scheda.disponibilitaParkDistControl?1:0;
            int SediliRiscaldati = scheda.disponibilitaSediliRiscaldati?1:0;
            int Servosterzo = scheda.disponibilitaServoSterzo?1:0;
            int VolanteMultifunzione = scheda.disponibilitaVolanteMultifunzione?1:0;
            int Handicap = scheda.disponibilitaAllestimentoHandicap?1:0;
            int CerchiInLega = scheda.disponibilitaCerchiInLega?1:0;
            int GancioTraino = scheda.disponibilitaGancioTraino?1:0;
            int Portapacchi = scheda.disponibilitaPortaPacchi?1:0;
            int SediliSportivi = scheda.disponibilitaSediliSportivi?1:0;
            String Motore = "'" + scheda.tipologiaMotoreVeicolo + "'";
            String Cambio = "'" + scheda.tipologiaCambioVeicolo + "'";
            int NumRapporti = Integer.parseInt(scheda.numeroRapportiVeicolo);
            int Cilindrata = Integer.parseInt(scheda.cilindrataVeicolo);
            String ClasseEmissione = "'" + scheda.classeEmissioniVeicolo + "'";
            float ConsumoMedio = Float.parseFloat(scheda.comsumeMedioVeicolo);
            String UrlYT = "'" + scheda.urlVideoYouTube + "'";
            String Descrizione = "'" + scheda.descrizioneVeicolo + "'";
            String RagioneSociale = "'" + scheda.ragioneSociale + "'";
            String Indirizzo = "'" + scheda.Indirizzo + "'";
            String TelefonoGenerico = "'" + scheda.Telefono + "'";
            String NomeReferente = "'" + scheda.nomeReferente + "'";
            String TelefonoReferente = "'" + scheda.TelefonoReferente + "'";
            String EmailReferente = "'" + scheda.emailReferente + "'";
            
            //Costruisco la query sql
            String querySQL_1 = "INSERT INTO autoveicoli(";
            String querySQL_2 = "IdScheda,        Marca,        Modello,        Versione,        MeseImmatricolazione,        AnnoImmatricolazione,        Carburante,        Tipologia,        Carrozzeria,        PostiASedere,        PotenzaKW,        PotenzaCV,        ColoreEsterno,        Metallizzato,        PrecedentiProprietari,        Chilometraggio,        Prezzo,        Trattabile,        IVADeducibile,        FinitureInterni,        ColoreInterni,        ABS,        Airbag,        Antifurto,        ChiusuraCentralizzata,        ControlloAutomTrazione,        ESP,        Immobilizer,        FreniADisco,        AlzacristalliElettrici,        Clima,        NavigatoreSatellitare,        RadioCD,        ParkDistControl,        SediliRiscaldati,        Servosterzo,        VolanteMultifunzione,        Handicap,        CerchiInLega,        GancioTraino,        Portapacchi,        SediliSportivi,        Motore,        Cambio,        NumRapporti,        Cilindrata,        ClasseEmissione,        ConsumoMedio,        UrlYT,        Descrizione,        RagioneSociale,        Indirizzo,        TelefonoGenerico,        NomeReferente,        TelefonoReferente,        EmailReferente";
            String querySQL_4 =  IdScheda + "," + Marca + "," + Modello + "," + Versione + "," + MeseImmatricolazione + "," + AnnoImmatricolazione + "," + Carburante + "," + Tipologia + "," + Carrozzeria + "," + PostiASedere + "," + PotenzaKW + "," + PotenzaCV + "," + ColoreEsterno + "," + Metallizzato + "," + PrecedentiProprietari + "," + Chilometraggio + "," + Prezzo + "," + Trattabile + "," + IVADeducibile + "," + FinitureInterni + "," + ColoreInterni + "," + ABS + "," + Airbag + "," + Antifurto + "," + ChiusuraCentralizzata + "," + ControlloAutomTrazione + "," + ESP + "," + Immobilizer + "," + FreniADisco + "," + AlzacristalliElettrici + "," + Clima + "," + NavigatoreSatellitare + "," + RadioCD + "," + ParkDistControl + "," + SediliRiscaldati + "," + Servosterzo + "," + VolanteMultifunzione + "," + Handicap + "," + CerchiInLega + "," + GancioTraino + "," + Portapacchi + "," + SediliSportivi + "," + Motore + "," + Cambio + "," + NumRapporti + "," + Cilindrata + "," + ClasseEmissione + "," + ConsumoMedio + "," + UrlYT + "," + Descrizione + "," + RagioneSociale + "," + Indirizzo + "," + TelefonoGenerico + "," + NomeReferente + "," + TelefonoReferente + "," + EmailReferente;
            String querySQL_3 = ") VALUES(";
            String querySQL_5 = ")";
            
            String querySQL_2_normalized = querySQL_2.replace(" ", "");
            String querySQL = querySQL_1 + querySQL_2_normalized + querySQL_3 + querySQL_4 + querySQL_5;
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