/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */ 

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author marco
 */

//La classe principale
public class _autoscout24It extends PortaleWeb {


	//Variabili portale
	private final String NOMEPORTALE = "www.autoscout24.it";
	private final String URLROOT = "http://www.autoscout24.it";
	private final String SECUREURLROOT = "https://secure.autoscout24.it";
	private final String USERNAME = AUTOSCOUT24_USERNAME;
	private final String PASSWORD = AUTOSCOUT24_PASSWORD;
	private final String HOST = "www.autoscout24.it";
	private final String HOST2 = "secure.autoscout24.it";
	private final String HOST3 = "offerta.autoscout24.it";

	private final String COOKIE_DEFAULT_PATH = "/";
	private final String COOKIE_DEFAULT_DOMAIN = ".autoscout24.it";

	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private boolean inserimentoOK = false;
	private boolean modifica = false;

	//Strutture dati di supporto
	//Mappa dei parametri da inviare
	Map<String,String> mappaDeiParamerti;

	//Lista dei parametri inviati in una singola connessione
	List<NameValuePair> postParameters;

	//Lista degli headers inviati in una singola connessione
	List<NameValuePair> requestHeaders;

	//Lista dei cookies inviati in una singola connessione
	List<BasicClientCookie> requestCookies;

	//Mappa che rappresenta la tabella di dipendennza dei parametri da inviare
	Map<String,String> mappaAssociativaInputValore;

	//La scheda immobile su cui si lavora
	SchedaVeicolo scheda;

	//Altre variabili di supporto a livello globale
	String articleId;
	String BaseData_ModelId;
	String Equipment_EquipmentIds="";

	//Costruttore
	public _autoscout24It (ImageIcon icon, String valoreLabel, String idPortale, boolean isActive) {		

		super(icon, valoreLabel, idPortale, isActive);

		//La hashTable contenente i valori dei parametri da inviare durante la sessione
		mappaDeiParamerti =  new Hashtable<String,String>();

		//La lista dei parametri (nome-valore) inviati
		postParameters = new ArrayList<NameValuePair>();	

		//La lista degli header (nome-valore) inviati
		requestHeaders = new ArrayList<NameValuePair>();

		//La lista dei cookies inviati
		requestCookies = new ArrayList<BasicClientCookie>();

		//Iniziallizzo la tabella di dipendenza
		mappaAssociativaInputValore = new Hashtable<String,String>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {

		//autoscout24 è un portale che accetta solo veicoli usati
		if(scheda.tipologiaVeicolo.equals("Veicolo nuovo") || scheda.meseImmatricolazioneVeicolo.equals("Da immatricolare") || scheda.annoImmatricolazioneVeicolo.equals("Da immatricolare")) {
			messageInserimentoKO(NOMEPORTALE);
			return false;
		}

		//Inizializzazione scheda
		this.scheda=scheda;

		//Inizializzo gli headers
		inizializzaHeaders(requestHeaders, HOST);

		//Connessione 0 - GET della home page - Opzionale
		/*HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT, requestHeaders, null, DEBUG_MODE);
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
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(0);
		requestHeaders.add(new BasicNameValuePair("Host", HOST2));
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", SECUREURLROOT + "/Login.aspx", requestHeaders, null, DEBUG_MODE);
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
		mappaAssociativaInputValore.put("__EVENTTARGET","ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$weviLoginButton");
		mappaAssociativaInputValore.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$passwordTextbox",PASSWORD);
		mappaAssociativaInputValore.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$usernameTextbox",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#aspnetForm input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", SECUREURLROOT + "/Login.aspx", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("MyPrivateArea")) {
					responseBody = (String)response[1];
				}
				else {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Location non corretta"));
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


		//Connessione 3 - GET della pagina di inserzione annuncio semplificata
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(requestHeaders.size()-1);
		requestHeaders.add(new BasicNameValuePair("Host", HOST3));
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di inserzione annuncio semplificata", "https://offerta.autoscout24.it/classified", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 4 - GET per ottenere l'articleId
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET per ottenere l'articleId", "https://offerta.autoscout24.it/classified/temporary/C?_=1386424481612", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				String responseBody2 = (String)response[1];

				//Parsing JSON della risposta
				JSONObject json = null;
				try {
					json = new JSONObject(responseBody2);
					if(json.getString("status").equals("Success")) {
						articleId = json.getString("articleId");
					}
					else {
						throw new HttpCommunicationException(new HttpWrongResponseBodyException("La GET ha ritornato un response inatteso"));
					}				
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 4c - Invio delle foto
		for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_4c = new HttpPortalPostConnection();        
				try {
					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);

					reqEntity.addPart("file1", bin );

					String articleGuid = returnCookieValue(requestCookies, "GUID");					

					Object[] response = connessione_4c.post("Connessione 4c - Invio delle foto", "https://offerta.autoscout24.it/classified/image/UploadHTML5/?articleId=" + articleId + "&articleGuid=" + articleGuid, reqEntity, requestHeaders, requestCookies, DEBUG_MODE);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()!=200)) {
						throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
					}

				} catch (IOException | RuntimeException e) {
					throw new HttpCommunicationException(e);
				}
				finally {
					clearStruttureDati(mappaAssociativaInputValore, mappaDeiParamerti, postParameters);
				}
			}
		}


		//Connessione 5 - POST dei parametri di annuncio
		DecimalFormat df = new DecimalFormat("###,###.###"); 
		//Raccolgo i parametri nella tabella di dipendenza
		mappaAssociativaInputValore.put("ArticleId", articleId); 
		mappaAssociativaInputValore.put("BaseData.BodyColorId",scheda.coloreEsternoVeicolo); 
		mappaAssociativaInputValore.put("BaseData.BodyTypeId",scheda.carrozzeriaVeicolo); 
		mappaAssociativaInputValore.put("BaseData.FirstRegistrationMonth", "0" + Integer.toString(scheda.meseImmatricolazioneVeicoloIndex-1)); 
		mappaAssociativaInputValore.put("BaseData.FirstRegistrationYear",scheda.annoImmatricolazioneVeicolo); 
		mappaAssociativaInputValore.put("BaseData.FuelId",scheda.carburanteVeicolo); 
		mappaAssociativaInputValore.put("BaseData.MakeId",scheda.marcaVeicolo);
		mappaAssociativaInputValore.put("BaseData.Mileage", df.format(new Integer(scheda.chilometraggioVeicolo)));
		mappaAssociativaInputValore.put("BaseData.Power",scheda.KWVeicolo); 
		mappaAssociativaInputValore.put("BaseData.PowerHp",scheda.CVVeicolo);
		mappaAssociativaInputValore.put("BaseData.PreviousOwners",""+scheda.numeroPrecedentiProprietariVeicolo); 
		mappaAssociativaInputValore.put("BaseData.PricePublic",df.format(new Integer(scheda.prezzoVeicolo)));
		mappaAssociativaInputValore.put("BaseData.Version", scheda.versioneVeicolo);
		mappaAssociativaInputValore.put("Description.Description",scheda.descrizioneVeicolo);
		mappaAssociativaInputValore.put("Equipment.AlloyWheelInches", "Altezza"); 	
		if(scheda.coloreInterniVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("Equipment.InteriorColorId","colore");}else {mappaAssociativaInputValore.put("Equipment.InteriorColorId",scheda.coloreInterniVeicolo);}
		if(!scheda.postiASedereVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("Equipment.Seats",scheda.postiASedereVeicolo);}
		if(scheda.finitureInterneVeicolo.equals("Seleziona")) {	mappaAssociativaInputValore.put("Equipment.UpholsteryId", "Materiale");}else {mappaAssociativaInputValore.put("Equipment.UpholsteryId",scheda.finitureInterneVeicolo);}
		mappaAssociativaInputValore.put("Moto.ConsumptionMixed", scheda.consumoMedioVeicolo);
		mappaAssociativaInputValore.put("Moto.EmissionClassId",scheda.classeEmissioniVeicolo);
		mappaAssociativaInputValore.put("Moto.EnvironmentalStickerId","Seleziona");
		mappaAssociativaInputValore.put("Moto.GearingId",scheda.tipologiaCambioVeicolo);
		if(!scheda.numeroRapportiVeicolo.equals("Seleziona")) {mappaAssociativaInputValore.put("Moto.Gears",scheda.numeroRapportiVeicolo);}
		mappaAssociativaInputValore.put("Moto.TransmissionId",scheda.tipologiaMotoreVeicolo);
		mappaAssociativaInputValore.put("BaseData.ModelId","***DONOTSEND***"); //viene calcolato dopo
		mappaAssociativaInputValore.put("BaseData.ArticleOfferTypeId","***DONOTSEND***"); //viene calcolato dopo
		mappaAssociativaInputValore.put("Equipment.EquipmentIds","***DONOTSEND***"); //viene calcolato dopo

		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#OfferData input, #OfferData select, #OfferData textarea", mappaAssociativaInputValore, mappaDeiParamerti);

		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);

		//Questi parametri li devo valorizzare qui
		String actualMakeId = mappaDeiParamerti.get("BaseData.MakeId");
		BaseData_ModelId = getBaseData_ModelId(actualMakeId);
		postParameters.add(new BasicNameValuePair("BaseData.ModelId", BaseData_ModelId));
		postParameters.add(new BasicNameValuePair("__RequestVerificationToken", "EhEdN_8j5znK565KcirBfcnQWYz6jTCUP1Aht70T_qYQ0yjxROZcztemzN15idgukEZwGfombkBQ8MqsmNxyCoqWPnWG7rOx93nCPb6wX79i-C18xP0__vxvYysSvZeC0")); //lo metto statico perchè non so come valutarlo
		
		if(scheda.coloreMetalizzato) {
			postParameters.add(new BasicNameValuePair("BaseData.MetallicColor", "false"));
		}
		if(scheda.disponibilitaABS) {
			postParameters.add(new BasicNameValuePair("Equipment.SecurityLeft.1", "1"));
			Equipment_EquipmentIds += "1,";
		}
		if(scheda.disponibilitaAirBag) {
			postParameters.add(new BasicNameValuePair("Equipment.SecurityLeft.-1", "-1"));
			Equipment_EquipmentIds += "-1,";
		}
		if(scheda.disponibilitaAntifurto) {
			postParameters.add(new BasicNameValuePair("Equipment.SecurityLeft.18", "18"));
			Equipment_EquipmentIds += "18,";
		}
		if(scheda.disponibilitaChiusuraCentralizzata) {
			postParameters.add(new BasicNameValuePair("Equipment.SecurityLeft.17", "17"));
			Equipment_EquipmentIds += "17,";
		}
		if(scheda.disponibilitaContrlAutomTrazione) {
			postParameters.add(new BasicNameValuePair("Equipment.SecurityLeft.31", "31"));
			Equipment_EquipmentIds += "31,";
		}
		if(scheda.disponibilitaESP) {
			postParameters.add(new BasicNameValuePair("Equipment.SecurityRight.42", "42"));
			Equipment_EquipmentIds += "42,";
		}
		if(scheda.disponibilitaRadioOLettoreCD) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortLeft.10", "10"));
			Equipment_EquipmentIds += "10,";
		}
		if(scheda.disponibilitaAlzacristalliElettrici) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortLeft.13", "13"));
			Equipment_EquipmentIds += "13,";
		}
		if(scheda.disponibilitaClima) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortLeft.5", "5"));
			Equipment_EquipmentIds += "5,";
		}
		if(scheda.disponibilitaParkDistControl) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortLeft.40", "40"));
			Equipment_EquipmentIds += "40,";
		}
		if(scheda.disponibilitaServoSterzo) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortRight.12", "12"));
			Equipment_EquipmentIds += "12,";
		}
		if(scheda.disponibilitaNavigatoreSatellitare) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortRight.23", "23"));
			Equipment_EquipmentIds += "23,";
		}
		if(scheda.disponibilitaVolanteMultifunzione) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortRight.114", "114"));
			Equipment_EquipmentIds += "114,";
		}
		if(scheda.disponibilitaAllestimentoHandicap) {
			postParameters.add(new BasicNameValuePair("Equipment.ExtrasLeft.36", "36"));
			Equipment_EquipmentIds += "36,";
		}
		if(scheda.disponibilitaCerchiInLega) {
			postParameters.add(new BasicNameValuePair("Equipment.ExtrasLeft.15", "15"));
			Equipment_EquipmentIds += "15,";
		}
		if(scheda.disponibilitaGancioTraino) {
			postParameters.add(new BasicNameValuePair("Equipment.ExtrasLeft.20", "20"));
			Equipment_EquipmentIds += "20,";
		}
		if(scheda.disponibilitaPortaPacchi) {
			postParameters.add(new BasicNameValuePair("Equipment.ExtrasRight.27", "27"));
			Equipment_EquipmentIds += "27,";
		}
		if(scheda.disponibilitaSediliSportivi) {
			postParameters.add(new BasicNameValuePair("Equipment.ExtrasRight.117", "117"));
			Equipment_EquipmentIds += "117,";
		}
		if(scheda.disponibilitaSediliRiscaldati) {
			postParameters.add(new BasicNameValuePair("Equipment.ComfortRight.34", "34"));
			Equipment_EquipmentIds += "34,";
		}
		
		if(Equipment_EquipmentIds.equals("")) {
			postParameters.add(new BasicNameValuePair("Equipment.EquipmentIds", ""));
		}
		else {
			Equipment_EquipmentIds.substring(0, Equipment_EquipmentIds.lastIndexOf(",")-1);
			postParameters.add(new BasicNameValuePair("Equipment.EquipmentIds", Equipment_EquipmentIds));
		}	

		switch (scheda.tipologiaVeicolo) {
		case "Veicolo d'epoca":
			postParameters.add(new BasicNameValuePair("BaseData.ArticleOfferTypeId", "O"));
			break;
		case "Veicolo aziendale":
			postParameters.add(new BasicNameValuePair("BaseData.ArticleOfferTypeId", "J"));
			break;
			/*case "Veicolo nuovo":
			postParameters.add(new BasicNameValuePair("BaseData.ArticleOfferTypeId", "J"));
			break;*/
		default:
			postParameters.add(new BasicNameValuePair("BaseData.ArticleOfferTypeId", "U"));
			break;
		}

		HttpPortalPostConnection connessione_5 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_5.post("Connessione 5 - POST dei parametri annuncio", "https://offerta.autoscout24.it/classified/save", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				responseBody = (String)response[1];

				//Parsing JSON della risposta
				JSONObject json = null;
				try {
					json = new JSONObject(responseBody);
					if(json.getString("status").equals("Success")) {
						codiceInserzione = json.getString("articleId");
						inserimentoOK = true;
					}
					else {
						throw new HttpCommunicationException(new HttpWrongResponseBodyException("La GET ha ritornato un response body inatteso"));
					}				
				} catch (ParseException e) {
					e.printStackTrace();
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

		String articleGUID = "";
		Date date = new Date();
		long timeStamp = date.getTime();

		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(0);
		requestHeaders.add(new BasicNameValuePair("Host", HOST2));
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", SECUREURLROOT + "/Login.aspx", requestHeaders, null, DEBUG_MODE);
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
		mappaAssociativaInputValore.put("__EVENTTARGET","ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$weviLoginButton");
		mappaAssociativaInputValore.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$passwordTextbox",PASSWORD);
		mappaAssociativaInputValore.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$usernameTextbox",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#aspnetForm input", mappaAssociativaInputValore, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", SECUREURLROOT + "/Login.aspx", postParameters, requestHeaders, requestCookies, DEBUG_MODE);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo la location
				location = getHeaderValueByName(responseHeaders, "Location");
				if(location.contains("MyPrivateArea")) {
					responseBody = (String)response[1];
				}
				else {
					throw new HttpCommunicationException(new HttpWrongResponseHeaderException("Location non corretta"));
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


		//Connessione 3 - GET della pagina PrivateArea
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(0);
		requestHeaders.add(new BasicNameValuePair("Host", HOST3));
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina PrivateArea", "https://offerta.autoscout24.it/PrivateArea", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];

				Document dom = Jsoup.parse(responseBody);
				Element link = dom.select("a[data-name=deleteLink][data-article-id=" + codiceInserzione + "]").first();
				if(link!=null) {
					articleGUID = link.attr("data-article-guid");
				}			

			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 4 - GET di eliminazione annuncio
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET di eliminazione annuncio", "https://offerta.autoscout24.it/privatearea/deletearticlewithsurvey?articleId=" + codiceInserzione + "&articleGuid=" + articleGUID + "&_=" + timeStamp, requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200 || responseStatus.getStatusCode()==500)) { //Se l'annuncio non esiste più, risponde con 500
				//
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
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


	//Metodi di supporto
	public String getBaseData_ModelId(String modelId) throws HttpCommunicationException {

		String returnValue=null; 

		//Connessione 4b - GET per ottenere il BaseData_ModelId
		HttpPortalGetConnection connessione_4b = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4b.get("Connessione 4b - GET per ottenere il BaseData_ModelId", "https://offerta.autoscout24.it/api/modelsC/" + modelId + "?_=1386424481611", requestHeaders, requestCookies, DEBUG_MODE);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				responseBody = (String)response[1];

				//Parse HMTL to retrieve some informations
				org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
				Elements optionElements = doc.getElementsByTag("option");
				if(optionElements!=null) {
					Iterator<Element> iterator = optionElements.iterator();
					
					//Una comparazione fatta con criterio
					double resultComparation = 0;
					while(iterator.hasNext()) {
						Element currentElement = iterator.next();
						String modello = scheda.modelloVeicolo;
						String comparazione = currentElement.text();
						List<char[]> stringaModello = bigram(modello);
						List<char[]> stringaComparazione = bigram(comparazione);
						double actualResultComparation = dice(stringaComparazione, stringaModello);
						System.out.println("Comparazione "  + modello + "/" + comparazione + ": " + actualResultComparation);
						if(actualResultComparation>resultComparation) {
							resultComparation = actualResultComparation;
							returnValue = currentElement.val();							
						}
						
					}
					System.out.println("Comparazione - risultato finale "  + returnValue);
					//Una comparazione fatta con criterio
					
				}
				else {
					System.out.println("method: getBaseData_ModelId-->" + "Non trovo elementi option");
				}
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException | HttpCommunicationException e) {
			throw new HttpCommunicationException(e);
		}

		return returnValue;
	}

}