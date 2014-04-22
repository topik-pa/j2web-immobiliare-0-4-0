/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;

/**
 *
 * @author marco
 */

//La classe principale
public class _cuboAutoIt extends PortaleWeb {     

	//Variabili portale
	private final String NOMEPORTALE = "www.cuboauto.it";
	private final String URLROOT = "http://www.cuboauto.it";
	private final String USERNAME = CUBOAUTO_USERNAME;
	private final String PASSWORD = CUBOAUTO_PASSWORD;
	private final String HOST = "www.cuboauto.it";

	private final String COOKIE_DEFAULT_PATH = "/";
	private final String COOKIE_DEFAULT_DOMAIN = "www.cuboauto.it";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean modifica = false;
	
	//Messaggi personalizzati per questo portale
	

	//Strutture dati di supporto
	//Mappa dei parametri da inviare
	Map<String,String> mappaDeiParamerti;

	//Lista dei parametri inviati in una singola connessione
	List<NameValuePair> postParameters;

	//Lista degli headers inviati in una singola connessione
	List<NameValuePair> requestHeaders;

	//Lista dei cookies inviati in una singola connessione
	List<BasicClientCookie> requestCookies;

	//Mappa che rappresenta la associazione input/valore da cui dipende
	Map<String,String> mappaAssociativaInputValore;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;

	//Altre variabili di supporto a livello globale
	String var_idMarca;

	//Costruttore
	public _cuboAutoIt (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);			

		//La hashTable contenente i valori dei parametri da inviare durante la sessione
		mappaDeiParamerti =  new Hashtable<String,String>();

		//La lista dei parametri (nome-valore) inviati
		postParameters = new ArrayList<NameValuePair>();	

		//La lista degli header (nome-valore) inviati
		requestHeaders = new ArrayList<NameValuePair>();

		//Iniziallizzo la tabella di dipendenza
		mappaAssociativaInputValore = new Hashtable<String,String>();

		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//Inizializzazione scheda
		this.scheda=scheda;

		//Inizializzo gli headers e i cookie
		inizializzaHeaders(requestHeaders, HOST);
		requestCookies.clear();


		//Connessione 0 - GET della home page - Opzionale
		/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT + "/index.php", requestHeaders, null, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		mappaAssociativaInputValore.put("password",PASSWORD);
		mappaAssociativaInputValore.put("username",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#frmLogin input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Inizializzo i cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}


		//Connessione 3 - GET della pagina "Area concessionario" - Opzionale
		/*HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina \"Area concessionario\"", URLROOT + "/" + location, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 4 - GET della pagina "Inserisci un nuovo annuncio"
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET della pagina \"Inserisci un nuovo annuncio\"", URLROOT + "/concessionari/inserisci-annuncio.php", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 5 - GET della pagina "Inserisci un nuovo annuncio (con parametro circa la marca veicolo)"
		//Raccolgo i parametri nella tabella di dipendenza
		mappaAssociativaInputValore.put("idMarca",scheda.marcaVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "select#idMarca", mappaAssociativaInputValore, mappaDeiParamerti);
		HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_5.get("Connessione 5 - GET della pagina \"Inserisci un nuovo annuncio (con parametro circa la marca veicolo)\"", URLROOT + "/concessionari/inserisci-annuncio.php?idMarca=" + mappaDeiParamerti.get("idMarca"), requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			var_idMarca = mappaDeiParamerti.get("idMarca");
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);	
		}


		//Connessione 6 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		mappaAssociativaInputValore.put("CV",scheda.CVVeicolo);
		mappaAssociativaInputValore.put("KW",scheda.KWVeicolo);
		if(scheda.annoImmatricolazioneVeicolo.equals("Anno")) {mappaAssociativaInputValore.put("annoimmatricolazione","vuoto");}else {mappaAssociativaInputValore.put("annoimmatricolazione",scheda.annoImmatricolazioneVeicolo);}
		mappaAssociativaInputValore.put("annoprossimarevisione","vuoto");
		mappaAssociativaInputValore.put("cambio",scheda.tipologiaCambioVeicolo);
		mappaAssociativaInputValore.put("chilometri",scheda.chilometraggioVeicolo);
		mappaAssociativaInputValore.put("cilindrata",scheda.cilindrataVeicolo);
		mappaAssociativaInputValore.put("cilindri","vuoto");
		mappaAssociativaInputValore.put("codice",scheda.codiceScheda);
		mappaAssociativaInputValore.put("coloreesterno",scheda.coloreEsternoVeicolo);
		if(scheda.coloreInterniVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("coloreinterni","vuoto");}	else {mappaAssociativaInputValore.put("coloreinterni",scheda.coloreInterniVeicolo);}	
		mappaAssociativaInputValore.put("contratto",scheda.tipologiaContrattoVeicolo);
		mappaAssociativaInputValore.put("descrizione",scheda.descrizioneVeicolo);
		mappaAssociativaInputValore.put("idAlimentazione",scheda.carburanteVeicolo);
		mappaAssociativaInputValore.put("idCarrozzeria",scheda.carrozzeriaVeicolo);
		mappaAssociativaInputValore.put("idMarca",var_idMarca);
		mappaAssociativaInputValore.put("idMarca2",var_idMarca);
		mappaAssociativaInputValore.put("idModello",scheda.modelloVeicolo);
		mappaAssociativaInputValore.put("idTipologia",scheda.tipologiaVeicolo);
		if(scheda.meseImmatricolazioneVeicolo.equals("Mese")) {mappaAssociativaInputValore.put("meseimmatricolazione","vuoto");}else {mappaAssociativaInputValore.put("meseimmatricolazione","0"+(scheda.meseImmatricolazioneVeicoloIndex-1));}
		mappaAssociativaInputValore.put("meseprossimarevisione","vuoto");
		if(scheda.classeEmissioniVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("normativa","vuoto");}else {mappaAssociativaInputValore.put("normativa",scheda.classeEmissioniVeicolo);}
		mappaAssociativaInputValore.put("prezzo",scheda.prezzoVeicolo);
		if(scheda.numeroRapportiVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("rapporti","vuoto");}else {mappaAssociativaInputValore.put("rapporti",scheda.numeroRapportiVeicolo);}	
		if(scheda.postiASedereVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("sedili","vuoto");}	else {mappaAssociativaInputValore.put("sedili",scheda.postiASedereVeicolo);}
		mappaAssociativaInputValore.put("versione",scheda.versioneVeicolo);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#centrale form input, #centrale form select, #centrale form textarea", mappaAssociativaInputValore, mappaDeiParamerti);
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);

		//Le checkbox
		if(scheda.coloreMetalizzato){postParameters.add(new BasicNameValuePair("metallizzato", "1")); }
		if(scheda.disponibilitaABS){ postParameters.add(new BasicNameValuePair("sicurezza[]", "1")); }
		if(scheda.disponibilitaAirBag){ postParameters.add(new BasicNameValuePair("sicurezza[]", "2")); postParameters.add(new BasicNameValuePair("sicurezza[]", "4"));}
		if(scheda.disponibilitaAntifurto){ postParameters.add(new BasicNameValuePair("sicurezza[]", "128")); }
		if(scheda.disponibilitaChiusuraCentralizzata){ postParameters.add(new BasicNameValuePair("comfort[]", "64")); }
		if(scheda.disponibilitaContrlAutomTrazione){ postParameters.add(new BasicNameValuePair("sicurezza[]", "512")); }
		if(scheda.disponibilitaESP){ postParameters.add(new BasicNameValuePair("sicurezza[]", "2048")); }
		if(scheda.disponibilitaImmobilizer){ postParameters.add(new BasicNameValuePair("sicurezza[]", "256")); }
		if(scheda.disponibilitaAlzacristalliElettrici){ postParameters.add(new BasicNameValuePair("comfort[]", "4")); }
		if(scheda.disponibilitaClima){ postParameters.add(new BasicNameValuePair("comfort[]", "1")); }
		if(scheda.disponibilitaRadioOLettoreCD){ postParameters.add(new BasicNameValuePair("audio[]", "8")); postParameters.add(new BasicNameValuePair("audio[]", "2")); }
		if(scheda.disponibilitaParkDistControl){ postParameters.add(new BasicNameValuePair("comfort[]", "8192")); }
		if(scheda.disponibilitaSediliRiscaldati){ postParameters.add(new BasicNameValuePair("comfort[]", "32")); }
		if(scheda.disponibilitaServoSterzo){ postParameters.add(new BasicNameValuePair("comfort[]", "128")); }
		if(scheda.disponibilitaVolanteMultifunzione){ postParameters.add(new BasicNameValuePair("audio[]", "128")); }
		if(scheda.disponibilitaCerchiInLega){ postParameters.add(new BasicNameValuePair("linea[]", "2")); }
		if(scheda.disponibilitaGancioTraino){ postParameters.add(new BasicNameValuePair("varie[]", "16")); }
		if(scheda.disponibilitaPortaPacchi){ postParameters.add(new BasicNameValuePair("varie[]", "8")); }

		HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_6.post("Connessione 6 - POST dei parametri annuncio", URLROOT + "/concessionari/_inserisci-annuncio.php", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);

				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("?id=")) {
					int start = location.indexOf("?id=")+4;
					int end = location.length();
					codiceInserzione = location.substring(start, end);

					//L'inserzione è assicurata
					inserimentoOK = true;
				}
				else {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Header Location non previsto"));
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}


		//Connessione 7 - GET della pagina "Dettaglio annuncio" - Opzionale
		/*HttpPortalGetConnection connessione_7 = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_7.get("Connessione 7 - GET della pagina \"Dettaglio annuncio\"", URLROOT + "/" + location, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}*/


		//Connessione 8 - GET della pagina "Inserisci una nuova foto" - Opzionale
		HttpPortalGetConnection connessione_8 = new HttpPortalGetConnection();
		try {
			Object[] response =  connessione_8.get("Connessione 8 - GET della pagina \"Inserisci una nuova foto\"", URLROOT + "/concessionari/foto.php?id=" + codiceInserzione, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 9 - Invio delle foto
		for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();        
				try {

					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);
					reqEntity.addPart("foto", bin );
					reqEntity.addPart("id", new StringBody(codiceInserzione) );

					Object[] response = connessione_9.post("Connessione 9 - Invio delle foto", URLROOT + "/concessionari/_foto.php", reqEntity, requestHeaders, requestCookies, DEBUG_MODE);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()!=302)) {
						throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
					}
					else {
						Header[] responseHeaders = (Header[])response[0];				
						//Gestione dei cookie
						setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
					}

				} catch (IOException | RuntimeException e) {
					throw new HttpCommunicationException(e);
				}
				finally {
					clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
				}
			}
		}

		//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
		if(inserimentoOK) {

			//Aggiorna la lista dei portali in cui è inserita la scheda
			scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);

			//Aggiorna i pulsanti del pannello inserimento
			PanelSicronizzazioneConPortali.updatePanello(scheda, false);

			//Mail e messaggio informativo OK
			if(!modifica) {
				sendConfirmationMail(scheda, NOMEPORTALE, scheda.codiceScheda);
				messageInserimentoOK(NOMEPORTALE);
			}	
			else {
				messageModificaOK(NOMEPORTALE);
			}       	
		}
		else {
			if(!modifica) {
				messageInserimentoKO(NOMEPORTALE);
			}
			else {
				messageModificaKO(NOMEPORTALE);
			}
		}

		return inserimentoOK; 

	}


	//Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {

		//Apro il browser e inserisco credenziali		
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(URLROOT));
		} catch (IOException e ) {
			//
		}
		return true;
	}


	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//La scheda è da aggiornare
		if(J2Web_UI.protoScheda!=null) {
			modifica = true;
		}

		//Inizializzazione scheda
		this.scheda=scheda;

		//Ottengo il codice con cui è stata inserita la scheda
		codiceInserzione = scheda.getCodiceInserimento(idPortale);

		//Inizializzo gli headers
		inizializzaHeaders(requestHeaders, HOST);
		requestCookies.clear();

		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		mappaAssociativaInputValore.put("password",PASSWORD);
		mappaAssociativaInputValore.put("username",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#frmLogin input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
		}


		//Connessione 3 - GET della pagina di eliminazione annuncio
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di eliminazione annuncio", URLROOT + "/concessionari/_del_annunci.php?id=" + codiceInserzione, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (!(responseStatus.getStatusCode()==200 || responseStatus.getStatusCode()==302))) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				setCookies(responseHeaders, requestCookies, COOKIE_DEFAULT_PATH, COOKIE_DEFAULT_DOMAIN);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}

		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			

		//Aggiorno i pulsanti del pannello inserimento
		PanelSicronizzazioneConPortali.updatePanello(scheda, false);	

		//Stampo a video un messaggio informativo
		if(!modifica) {
			messageEliminazioneOK(NOMEPORTALE);
		}
		else {
			System.out.println("Eliminata da: " + NOMEPORTALE);
		}

		return true;

	}


}