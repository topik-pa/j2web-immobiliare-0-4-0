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
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicStatusLine;
import org.json.JSONObject;
import org.jsoup.Jsoup;
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
	private final String USERNAME = "c1917245@drdrb.com";
	private final String PASSWORD = "topik123";
	private final String HOST = "www.autoscout24.it";
	private final String HOST2 = "secure.autoscout24.it";
	private final String HOST3 = "offerta.autoscout24.it";

	private final String SECONDCOOKIENAME = "__RequestVerificationToken_Lw__";
	private final String SECONDCOOKIEDOMAIN = "secure.autoscout24.it";
	private final String SECONDCOOKIEHEADER = "";
	private final String SECONDCOOKIEVALUE = "";


	//Variabili navigazione
	//private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
	private String codiceInserzione;
	private String location;
	private String responseBody;
	private BasicNameValuePair sessionCookie;
	private boolean inserimentoOK = false;
	private boolean debugMode = true;

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
	Map<String,String> tabellaDiDipendenza;

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
		tabellaDiDipendenza = new Hashtable<String,String>();

	}


	//Metodo per l'inserimento della scheda immobile nel portale immobiliare
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {
		System.out.println("Inserimento scheda: " + scheda.codiceScheda + "...");

		//Inizializzazione scheda
		this.scheda=scheda;

		//Inposto le variabili per il session cookie
		SESSIONCOOKIENAME = "GUID";
		SESSIONCOOKIEDOMAIN = ".autoscout24.it";
		SESSIONCOOKIEHEADER = "";
		SESSIONCOOKIEVALUE = "";

		//Imposto qui gli headers che saranno utilizzati in tutte le altre connessioni
		requestHeaders.add(new BasicNameValuePair("Host", HOST));
		requestHeaders.add(new BasicNameValuePair("User-Agent", USER_AGENT_VALUE));	
		requestHeaders.add(new BasicNameValuePair("Connection", CONNECTION));
		requestHeaders.add(new BasicNameValuePair("Cache-Control", CACHE_CONTROL));
		requestHeaders.add(new BasicNameValuePair("Accept-Language", ACCEPT_LANGUAGE));
		requestHeaders.add(new BasicNameValuePair("Accept", ACCEPT));

		//Connessione 0 - GET della home page - Opzionale
		HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_0.get("Connessione 0 - GET della home page", URLROOT, requestHeaders, null, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(0);
		requestHeaders.add(new BasicNameValuePair("Host", HOST2));
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", SECUREURLROOT + "/Login.aspx", requestHeaders, null, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
			else {
				responseBody = (String)response[1];

				Header[] responseHeaders = (Header[])response[0];				
				//Gestione dei cookie
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_1.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
				setCookies(responseHeaders, requestCookies);
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		tabellaDiDipendenza.put("__EVENTARGUMENT","***site***");
		tabellaDiDipendenza.put("__EVENTTARGET","ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$weviLoginButton");
		tabellaDiDipendenza.put("__INFOMESSAGEPRESENT","***site***");
		tabellaDiDipendenza.put("__RequestVerificationToken","***site***");
		tabellaDiDipendenza.put("__VIEWSTATE","***site***");
		tabellaDiDipendenza.put("ctl00$ctl00$decoratedArea$contentArea$passwordForgotten$usernameTextbox","***site***");
		tabellaDiDipendenza.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$passwordTextbox",PASSWORD);
		tabellaDiDipendenza.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$passwordTextboxTxt","");
		tabellaDiDipendenza.put("ctl00$ctl00$decoratedArea$contentArea$weviUserLogin$usernameTextbox",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#aspnetForm input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", SECUREURLROOT + "/Login.aspx", postParameters, requestHeaders, requestCookies, debugMode);			

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
			tabellaDiDipendenza.clear();
			mappaDeiParamerti.clear();
			postParameters.clear();
		}


		//Connessione 3 - GET della pagina di inserzione annuncio semplificata
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		//Cambio del valore HOST nei request headers
		requestHeaders.remove(requestHeaders.size()-1);
		requestHeaders.add(new BasicNameValuePair("Host", HOST3));
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di inserzione annuncio semplificata", "https://offerta.autoscout24.it/classified", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()!=200)) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}


		//Connessione 4 - GET per ottenere l'articleId
		HttpPortalGetConnection connessione_4 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4.get("Connessione 4 - GET per ottenere l'articleId", "https://offerta.autoscout24.it/classified/temporary/C?_=1386424481612", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				responseBody = (String)response[1];

				//Parsing JSON della risposta
				JSONObject json = null;
				try {
					json = new JSONObject(responseBody);
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
		/*for(int i=1; i<scheda.arrayImages.length; i++) {
			if(scheda.arrayImages[i]!=null) {
				HttpPortalPostConnection connessione_4c = new HttpPortalPostConnection();        
				try {

					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					FileBody bin = new FileBody(scheda.arrayImages[i]);
					reqEntity.addPart("Filedata", bin );
					reqEntity.addPart("thumbnailHeight", new StringBody("480") );
					reqEntity.addPart("thumbnailWidth", new StringBody("640") );
					reqEntity.addPart("rotateAngleBeforeCrop", new StringBody("0") );
					reqEntity.addPart("imageWidth", new StringBody("800") );
					reqEntity.addPart("MultiPowUpload_browserCookie", new StringBody("") ); //sarebbero tutti i cookie...
					reqEntity.addPart("filesCount", new StringBody("1") );
					reqEntity.addPart("fileCreationdate", new StringBody(Long.toString(new Date().getTime())) );
					reqEntity.addPart("currentFileIndex", new StringBody(Integer.toString(i-1)) );
					reqEntity.addPart("fileModificationDate", new StringBody(Long.toString(new Date().getTime())) );
					reqEntity.addPart("fileId", new StringBody(codiceInserzione) );
					reqEntity.addPart("imageHeight", new StringBody("600") );
					reqEntity.addPart("MultiPowUploadId", new StringBody(codiceInserzione) );
					reqEntity.addPart("rotateAngle", new StringBody("0") );
					reqEntity.addPart("fileSize", new StringBody("Invia la foto") );

					Object[] response = connessione_4c.post("Connessione 9 - Invio delle foto", "https://offerta.autoscout24.it/classified/image/upload/" + articleId, reqEntity, requestHeaders, requestCookies, debugMode);			

					//Controllo il response status
					BasicStatusLine responseStatus = (BasicStatusLine) response[2];
					if( (responseStatus.getStatusCode()!=200)) {
						throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
					}

				} catch (IOException | RuntimeException e) {
					throw new HttpCommunicationException(e);
				}
				finally {
					tabellaDiDipendenza.clear();
					mappaDeiParamerti.clear();
					postParameters.clear();
				}
			}
		}*/


		//Connessione 5 - POST dei parametri di annuncio
		//Raccolgo i parametri nella tabella di dipendenza
		tabellaDiDipendenza.put("ArticleId", articleId);
		tabellaDiDipendenza.put("ArticleType", "***site***"); //Auto
		tabellaDiDipendenza.put("BaseData.Accident","***site***");
		tabellaDiDipendenza.put("BaseData.ArticleOfferTypeId",scheda.tipologiaVeicolo);  //da fare
		if(scheda.prezzoTrattabile){tabellaDiDipendenza.put("BaseData.AskingPrice","true");}
		tabellaDiDipendenza.put("BaseData.BodyColorId",scheda.coloreEsternoVeicolo); 
		tabellaDiDipendenza.put("BaseData.BodyTypeId",scheda.carrozzeriaVeicolo); 
		tabellaDiDipendenza.put("BaseData.Doors","***site***"); //bho 
		tabellaDiDipendenza.put("BaseData.FirstRegistrationMonth","0"+scheda.meseImmatricolazioneVeicoloIndex); 
		tabellaDiDipendenza.put("BaseData.FirstRegistrationYear",""+scheda.annoImmatricolazioneVeicoloIndex); 
		tabellaDiDipendenza.put("BaseData.FuelId",scheda.carburanteVeicolo); 
		tabellaDiDipendenza.put("BaseData.HSN","***site***"); 
		tabellaDiDipendenza.put("BaseData.LicensePlate","***site***"); 
		tabellaDiDipendenza.put("BaseData.MakeId",scheda.marcaVeicolo);
		if(scheda.coloreMetalizzato){tabellaDiDipendenza.put("BaseData.MetallicColor","true");}
		DecimalFormat df = new DecimalFormat("#.###.##0"); 
		tabellaDiDipendenza.put("BaseData.Mileage", df.format(scheda.chilometraggioVeicolo));
		//tabellaDiDipendenza.put("BaseData.ModelId",""); //lo devo valutare dopo
		tabellaDiDipendenza.put("BaseData.ParticulateFilter","***site***");
		tabellaDiDipendenza.put("BaseData.Power",scheda.KWVeicolo); 
		tabellaDiDipendenza.put("BaseData.PowerHp",scheda.CVVeicolo);
		tabellaDiDipendenza.put("BaseData.PreviousOwners",scheda.numeroPrecedentiProprietariVeicolo); 
		tabellaDiDipendenza.put("BaseData.PricePublic",df.format(scheda.prezzoVeicolo));
		tabellaDiDipendenza.put("BaseData.TSN", "***site***");
		tabellaDiDipendenza.put("BaseData.VatDeductible","***site***");
		tabellaDiDipendenza.put("BaseData.Version", scheda.versioneVeicolo);
		tabellaDiDipendenza.put("Description.Description",scheda.descrizioneVeicolo);
		tabellaDiDipendenza.put("Equipment.AlloyWheelInches", "Altezza"); 	

		if(scheda.disponibilitaABS){tabellaDiDipendenza.put("Equipment.SecurityLeft.1","1"); Equipment_EquipmentIds+="1,";}
		if(scheda.disponibilitaAirBag){tabellaDiDipendenza.put("Equipment.SecurityLeft.-1","-1");}
		if(scheda.disponibilitaAntifurto){tabellaDiDipendenza.put("Equipment.SecurityLeft.18","18");Equipment_EquipmentIds+="18,";}
		if(scheda.disponibilitaChiusuraCentralizzata){tabellaDiDipendenza.put("Equipment.SecurityLeft.17","17");Equipment_EquipmentIds+="17,";}
		if(scheda.disponibilitaContrlAutomTrazione){tabellaDiDipendenza.put("Equipment.SecurityLeft.31","31");Equipment_EquipmentIds+="31,";}
		if(scheda.disponibilitaESP){tabellaDiDipendenza.put("Equipment.SecurityRight.42","42");Equipment_EquipmentIds+="42,";}
		if(scheda.disponibilitaRadioOLettoreCD){tabellaDiDipendenza.put("Equipment.ComfortLeft.10","10");Equipment_EquipmentIds+="10,";}
		if(scheda.disponibilitaAlzacristalliElettrici){tabellaDiDipendenza.put("Equipment.ComfortLeft.13","13");Equipment_EquipmentIds+="13,";}
		if(scheda.disponibilitaClima){tabellaDiDipendenza.put("Equipment.ComfortLeft.5","5");Equipment_EquipmentIds+="5,";}
		if(scheda.disponibilitaParkDistControl){tabellaDiDipendenza.put("Equipment.ComfortLeft.40","40");Equipment_EquipmentIds+="40,";}
		if(scheda.disponibilitaServoSterzo){tabellaDiDipendenza.put("Equipment.ComfortRight.12","12");Equipment_EquipmentIds+="12,";}
		if(scheda.disponibilitaNavigatoreSattelitare){tabellaDiDipendenza.put("Equipment.ComfortRight.23","23");Equipment_EquipmentIds+="23,";}
		if(scheda.disponibilitaVolanteMultifunzione){tabellaDiDipendenza.put("Equipment.ComfortRight.114","114");Equipment_EquipmentIds+="114,";}
		if(scheda.disponibilitaAllestimentoHandicap){tabellaDiDipendenza.put("Equipment.ExtrasLeft.36","36");Equipment_EquipmentIds+="36,";}
		if(scheda.disponibilitaCerchiInLega){tabellaDiDipendenza.put("Equipment.ExtrasLeft.15","15");Equipment_EquipmentIds+="15,";}
		if(scheda.disponibilitaGancioTraino){tabellaDiDipendenza.put("Equipment.ExtrasLeft.20","20");Equipment_EquipmentIds+="20,";}
		if(scheda.disponibilitaPortaPacchi){tabellaDiDipendenza.put("Equipment.ExtrasRight.27","27");Equipment_EquipmentIds+="27,";}
		if(scheda.disponibilitaSediliSportivi){tabellaDiDipendenza.put("Equipment.ExtrasRight.117","117");Equipment_EquipmentIds+="117,";}
		if(scheda.disponibilitaSediliRiscaldati){tabellaDiDipendenza.put("Equipment.ComfortRight.34","34");Equipment_EquipmentIds+="34,";}

		if(Equipment_EquipmentIds.endsWith(",")){Equipment_EquipmentIds.substring(0, Equipment_EquipmentIds.length()-1);}
		tabellaDiDipendenza.put("Equipment.EquipmentIds","Equipment_EquipmentIds");
		tabellaDiDipendenza.put("Equipment.InteriorColorId",scheda.coloreInterniVeicolo);
		tabellaDiDipendenza.put("Equipment.Seats",scheda.postiASedereVeicolo);
		tabellaDiDipendenza.put("Equipment.UpholsteryId",scheda.finitureInterneVeicolo);
		tabellaDiDipendenza.put("Moto.Co2Emission","***site***");
		tabellaDiDipendenza.put("Moto.ConsumptionCity","***site***");
		tabellaDiDipendenza.put("Moto.ConsumptionHighway","***site***");
		tabellaDiDipendenza.put("Moto.ConsumptionMixed", scheda.comsumeMedioVeicolo);
		tabellaDiDipendenza.put("Moto.Cylinders","***site***");
		tabellaDiDipendenza.put("Moto.Displacement","***site***");
		tabellaDiDipendenza.put("Moto.EmissionClassId",scheda.classeEmissioniVeicolo);
		tabellaDiDipendenza.put("Moto.EnvironmentalStickerId","Seleziona");
		tabellaDiDipendenza.put("Moto.GearingId",scheda.tipologiaCambioVeicolo);
		tabellaDiDipendenza.put("Moto.Gears",scheda.numeroRapportiVeicolo);
		tabellaDiDipendenza.put("Moto.TransmissionId",scheda.tipologiaMotoreVeicolo);
		tabellaDiDipendenza.put("Moto.Weight","***site***");
		tabellaDiDipendenza.put("State.FullService","***site***");
		tabellaDiDipendenza.put("State.InspectionNextMonth","Mese");
		tabellaDiDipendenza.put("State.InspectionNextYear","Anno");
		tabellaDiDipendenza.put("State.LastCamBeltMonth","Mese");
		tabellaDiDipendenza.put("State.LastCamBeltYear","Anno");
		tabellaDiDipendenza.put("State.LastServiceDateMonth","Mese");
		tabellaDiDipendenza.put("State.LastServiceDateYear","Anno");
		tabellaDiDipendenza.put("State.NonSmokingVehicle","***site***");
		tabellaDiDipendenza.put("Tracking.Pagename","***site***");
		tabellaDiDipendenza.put("Tracking.TrackingAttribute","***site***");
		tabellaDiDipendenza.put("__RequestVerificationToken",""); //calcolato più sotto

		tabellaDiDipendenza.put("validate","continua");

		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#content form input, #content form select, #content form textarea", tabellaDiDipendenza, mappaDeiParamerti);

		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);

		//Questo parametro lo devo valorizzare qui
		String actualMakeId = mappaDeiParamerti.get("BaseData.MakeId");
		BaseData_ModelId = getBaseData_ModelId(actualMakeId);
		postParameters.add(new BasicNameValuePair("BaseData.ModelId", BaseData_ModelId));
		postParameters.add(new BasicNameValuePair("__RequestVerificationToken", "EhEdN_8j5znK565KcirBfcnQWYz6jTCUP1Aht70T_qYQ0yjxROZcztemzN15idgukEZwGfombkBQ8MqsmNxyCoqWPnWG7rOx93nCPb6wX79i-C18xP0__vxvYysSvZeC0"));

		HttpPortalPostConnection connessione_5 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_5.post("Connessione 5 - POST dei parametri annuncio", "https://offerta.autoscout24.it/classified/save", postParameters, requestHeaders, requestCookies, debugMode);			

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
			postParameters.clear();
			mappaDeiParamerti.clear();
			tabellaDiDipendenza.clear();
		}
		

		//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
		if(inserimentoOK) {

			//Aggiorna la lista dei portali in cui è inserita la scheda
			scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);

			if(!isSequential) {   			
				System.out.println("Inserita in: " + NOMEPORTALE);       		

				//Aggiorna i pulsanti del pannello inserimento
				PanelSicronizzazioneConPortali.updatePanello(scheda, false);

				//Invio mail di conferma inserimento 
				sendConfirmationMail(scheda, NOMEPORTALE, codiceInserzione);

				//Stampo a video un messaggio informativo
				JOptionPane.showMessageDialog(null, "Scheda immobile inserita in: " + NOMEPORTALE, "Scheda inserita", JOptionPane.INFORMATION_MESSAGE);
			}

			return inserimentoOK;        	
		}
		else {

			if(!isSequential) {
				//Stampo a video un messaggio informativo
				JOptionPane.showMessageDialog(null, "Problemi nell'inserimento scheda in: " + NOMEPORTALE + ".\n Verificare l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);	
			}

			return inserimentoOK;
		}

	}


	//Metodo per la visualizzazione della scheda immobile nel portale immobiliare
	public boolean visualizzaScheda(SchedaVeicolo scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codiceScheda + "...");

		codiceInserzione = scheda.getCodiceInserimento(idPortale);
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/concessionari/dett-annuncio.php?id=" + codiceInserzione;
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);

		} catch (IOException e ) {
			//
		}

		return true;
	}


	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceScheda + "...");

		codiceInserzione = scheda.getCodiceInserimento(idPortale);

		//Connessione 1 - GET della pagina di login
		HttpPortalGetConnection connessione_1 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_1.get("Connessione 1 - GET della pagina di login", URLROOT + "/login.php", requestHeaders, debugMode);
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


		//Connessione 2 - POST dei parametri di accesso
		//Raccolgo i parametri nella tabella di dipendennza
		tabellaDiDipendenza.put("password",PASSWORD);
		tabellaDiDipendenza.put("submit","Entra");
		tabellaDiDipendenza.put("username",USERNAME);
		//Valorizzo i parametri mettendoli nella mappaDeiParametri
		valutaParametri(responseBody, "#frmLogin input", tabellaDiDipendenza, mappaDeiParamerti);	
		//Trasferisco i parametri dalla mappa alla lista
		setPostParameters(mappaDeiParamerti, postParameters);
		HttpPortalPostConnection connessione_2 = new HttpPortalPostConnection();
		try {        	
			Object[] response = connessione_2.post("Connessione 2 - POST dei parametri di accesso", URLROOT + "/_login.php", postParameters, requestHeaders, debugMode);			

			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==302)) {
				Header[] responseHeaders = (Header[])response[0];
				//Trovo il cookie di sessione
				findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
				connessione_2.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
				//Aggiungo il cookie di sessione ai requestHeaders
				sessionCookie = new BasicNameValuePair("Cookie", "PHPSESSID" + "=" + SESSIONCOOKIEVALUE);
				requestHeaders.add(sessionCookie);
			}
			else {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}    	

		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
		finally {
			tabellaDiDipendenza.clear();
			mappaDeiParamerti.clear();
			postParameters.clear();
		}


		//Connessione 3 - GET della pagina di eliminazione annuncio
		HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_3.get("Connessione 3 - GET della pagina di eliminazione annuncio", URLROOT + "/concessionari/_del_annunci.php?id=" + codiceInserzione, requestHeaders, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (!(responseStatus.getStatusCode()==200 || responseStatus.getStatusCode()==302))) {
				throw new HttpCommunicationException(new HttpWrongResponseStatusCodeException("Status code non previsto"));
			}
		} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}

		//Aggiorno la lista dei portali in cui è presenta la scheda corrente
		scheda.eliminaInserimentoPortale(idPortale);			

		if(!isSequential) {
			//Aggiorno i pulsanti del pannello inserimento
			PanelSicronizzazioneConPortali.updatePanello(scheda, false);

			System.out.println("Eliminata da: " + NOMEPORTALE);

			//Stampo a video un messaggio informativo
			JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
		}

		return true;

	}


	//Metodi di supporto
	public String getBaseData_ModelId(String modelId) throws HttpCommunicationException {

		String returnValue=null; 

		//Connessione 4b - GET per ottenere il BaseData_ModelId
		HttpPortalGetConnection connessione_4b = new HttpPortalGetConnection();
		try {
			Object[] response = connessione_4b.get("Connessione 4b - GET per ottenere il BaseData_ModelId", "https://offerta.autoscout24.it/api/modelsC/" + modelId + "?_=1386424481611", requestHeaders, requestCookies, debugMode);
			//Controllo il response status
			BasicStatusLine responseStatus = (BasicStatusLine) response[2];
			if( (responseStatus.getStatusCode()==200)) {
				responseBody = (String)response[1];

				//Parse HMTL to retrieve some informations
				org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);                       
				Elements optionElements = doc.getElementsByTag("option");
				if(optionElements!=null) {
					Iterator<Element> iterator = optionElements.iterator();
					while(iterator.hasNext()) {
						Element currentElement = iterator.next();
						if(currentElement.text().equals(scheda.modelloVeicolo)) {  
							returnValue = currentElement.val();
							System.out.println("method: getBaseData_ModelId-->" + returnValue);
						}
					}
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