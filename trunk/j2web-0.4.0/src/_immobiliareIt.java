/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;


/**
 *
 * @author marco
 */

//La classe principale
public class _immobiliareIt extends PortaleImmobiliare {     

    //Variabili generali
	private final String NOMEPORTALE = "immobiliare.it";
	private final String URLROOT = "http://getrix.ekbl.net";	//immobiliare.it si appoggia a Getrix
	private final String URLROOT2 = "http://media.getrix.ekbl.net";	//la root per le immagini è differente
	private final String USERNAME = "nfjrpnqp@sharklasers.com";
    private final String PASSWORD = "ts2cj1n3";
    
	
    //private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
    private String codiceInserzione;
    private boolean inserimentoOK = false;
    private String location;
    private boolean debugMode = true;
    
    private String nomeImmagine0;
    private String nomeImmagine1;
    private String nomeImmagine2;
    private String nomeImmagine3;
    private String nomeImmagine4;
    private String nomeImmagine5;
    private String nomeImmagine6;
    private String nomeImmagine7;
    private String nomeImmagine8;
    private String nomeImmagine9;

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParamerti;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;  

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;
    
    //Lista di alcuni dei parametri inviati
    String categoria;
    String tipo;
    String fasciaprezzo;
    String gtxNumeroCamereDaLetto;
    String gtxAnnoCostruzione;
    String codice;
    String prezzo;
    String gtxNumeroSoggiornoSalotto; //non supportato
    String piano;
    String tipologia;
    String locali;
    String gtxNumeroAltreCamereStanze; //non supportato
    String numeroPiani;
    String stato;
    String superficie;
    String gtxNumeroPostiAuto; //non supportato
    String tipoProprieta; //non supportato
    String tipoMandato; //non supportato
    String gtxIdContatto; //vuoto
    String idCont; //vuoto
    String classeImmobile; //non supportato
    String terreno_proprieta; //non supportato
    String gtxScadenzaMandato; //non supportato
    String spese_condominiali; //non supportato
    String in_asta; //non supportato
    String a_reddito; //non supportato
    String libero; //non supportato
    String spese; //non supportato
    String classe_energetica;
    String ipe; //valore id default
    String ipe_unita_misura; //valore id default
    String hidden_ipe; //valore id default
    String hidden_default_mq; //valore id default
    String hidden_default_mc; //valore id default
    String costruttore; //valore id default
    String statoCantiere; //valore id default
    String riscaldamento; //da non inviare in certe condizioni
    String cucina; //non supportato e da non inviare in certe condizioni
    String boxauto; //non supportato in questa maniera
    String bagni;
    String idGiardino;
    String idArredamento;
    String gtxIngresso; //non supportato
    String gtxRipostiglio; //non supportato
    String gtxCantina;
    String gtxMansarda; //non supportato
    String gtxTaverna;  //non supportato
    String gtxInfissiEsterni;  //non supportato
    String gtxImpiantoTv;  //non supportato
    String gtxFibraOttica;
    String ascensore;
    String gtxImpiantoAllarme;
    String gtxCancelloElettrico;
    String flag_auto_it; 
    String descrizione_it; 
    String flag_auto_en;  //non supportato
    String descrizione_en;  //non supportato
    String flag_auto_de;  //non supportato
    String descrizione_de;  //non supportato
    String flag_auto_fr;  //non supportato
    String descrizione_fr;  //non supportato
    String flag_auto_es;  //non supportato
    String descrizione_es;  //non supportato
    String flag_auto_pt;  //non supportato
    String descrizione_pt;  //non supportato
    String flag_auto_ru;  //non supportato
    String descrizione_ru;  //non supportato
    String flag_auto_gr;  //non supportato
    String descrizione_gr;  //non supportato
    String remLen_it;
    String remLen_en = "3000";  //valore id default
    String remLen_de = "3000";  //valore id default
    String remLen_fr = "3000";  //valore id default
    String remLen_es = "3000";  //valore id default
    String remLen_pt = "3000";  //valore id default
    String remLen_ru = "3000";  //valore id default
    String remLen_gr = "3000";  //valore id default
    String gtxTitolo_per_riviste;  //non supportato
    String gtxDescrizione_per_riviste;  //non supportato
    String callback;
    
    String _textComune;
    String action;
    String cap;
    String com_latitudine;
    String com_longitudine;
    String confirm_address;
    String flagIndirizzo;
    String flagIndirizzo_fake;
    String idAnnuncio;
    String idComune;
    String idLocalita;
    String idMacrozona;
    String idMicrozona;
    String idNazione;
    String idProvincia;
    String idRegione;
    String indirizzo;
    String indirizzo_fake;
    String indirizzo_fake_orig;
    String latitudine;
    String longitudine;
    String nextStep;
    String num_localita;
    String numeroCivico;
    String planimetria;
    String step;
    String textComune;
    String textNazione;
    String textProvincia;
    String textRegione;
    String virtual_tour;
    String zonaobbligatoria;
    String zoom;
    
    String SelTipoPagamento;
    String address1;
    String amount;
    String azionePayPal;
    String bn;
    String business;
    String check_1;
    String check_108;
    String cmd;
    String currency_code;
    String first_name;
    String item_name;
    String item_number;
    String last_name;
    String lc;
    String no_note;
    String no_shipping;
    String notify_url;
    String prezzoTotale;
    String _return;
    String return_cancel;
    String richiesta;
    String zip;   
    
    
	//Costruttore
	public _immobiliareIt (String urlIcona, String valoreLabel, String idPortale, boolean isActive) {		
		
		super(urlIcona, valoreLabel, idPortale, isActive);
		
		//Inizializzo i cookie 
		SESSIONCOOKIENAME = "GETRIXSID";
		SESSIONCOOKIEDOMAIN = ".getrix.ekbl.net";
		
		mappaDeiParamerti =  new Hashtable<String,String>();
	    
	    postParameters = new ArrayList<NameValuePair>();		
	
	}

	
    //Metodo per l'inserimento della scheda immobile nel portale immobiliare
    public boolean inserisciScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {
    	System.out.println("Inserimento scheda: " + scheda.codiceInserzione + "...");
    	
    	//Inizializzazione parametri
    	this.scheda=scheda;
    	    	
    	//Inizializza i parametri http del portale 
		inizializzaParametri();
	
		
		//Connessione 0 - GET della home page Getrix - Opzionale
    	HttpPortalGetConnection connessione_0 = new HttpPortalGetConnection();
    	try {
			connessione_0.get("Connessione 0 - GET della home page Getrix", URLROOT, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 1 - POST dei parametri di accesso e recupero del cookie di sessione
    	HttpPortalPostConnection connessione_1 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("accedi", "accedi"));
        postParameters.add(new BasicNameValuePair("backurl", "/home_gestionale.php"));
        postParameters.add(new BasicNameValuePair("openTab", ""));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        postParameters.add(new BasicNameValuePair("username", USERNAME));
        try {
        	Object[] response = connessione_1.post("Connessione 1 - POST dei parametri di accesso e recupero del cookie di sessione", URLROOT + "/index.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
        	location = getHeaderValueByName(responseHeaders, "Location");
        	connessione_1.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 2 - GET della pagina riservata BackOffice
    	HttpPortalGetConnection connessione_2 = new HttpPortalGetConnection();
    	try {
			connessione_2.get("Connessione 2 - GET della pagina riservata BackOffice", URLROOT + location, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 3 - GET dello step 1 di inserimento immobile
    	HttpPortalGetConnection connessione_3 = new HttpPortalGetConnection();
    	try {
			connessione_3.get("Connessione 3 - GET dello step 1 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "2" + "&categoria=" + mappaDeiParamerti.get("categoria") + "&tipo=" + mappaDeiParamerti.get("tipo") + "&callback=refresh", debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 4 - POST dello step 2 di inserimento immobile
    	HttpPortalPostConnection connessione_4 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("tipo", mappaDeiParamerti.get("tipo")));
        postParameters.add(new BasicNameValuePair("step", "2"));
        postParameters.add(new BasicNameValuePair("categoria", mappaDeiParamerti.get("categoria")));
        postParameters.add(new BasicNameValuePair("fasciaprezzo", mappaDeiParamerti.get("fasciaprezzo")));
        postParameters.add(new BasicNameValuePair("gtxNumeroCamereDaLetto", mappaDeiParamerti.get("gtxNumeroCamereDaLetto")));
        postParameters.add(new BasicNameValuePair("gtxAnnoCostruzione", mappaDeiParamerti.get("gtxAnnoCostruzione")));
        postParameters.add(new BasicNameValuePair("codice", mappaDeiParamerti.get("codice")));
        postParameters.add(new BasicNameValuePair("prezzo", mappaDeiParamerti.get("prezzo")));
        postParameters.add(new BasicNameValuePair("gtxNumeroSoggiornoSalotto", mappaDeiParamerti.get("gtxNumeroSoggiornoSalotto")));
        postParameters.add(new BasicNameValuePair("piano", mappaDeiParamerti.get("piano")));
        postParameters.add(new BasicNameValuePair("tipologia", mappaDeiParamerti.get("tipologia")));
        postParameters.add(new BasicNameValuePair("locali", mappaDeiParamerti.get("locali")));
        postParameters.add(new BasicNameValuePair("gtxNumeroAltreCamereStanze", mappaDeiParamerti.get("gtxNumeroAltreCamereStanze")));
        postParameters.add(new BasicNameValuePair("numeroPiani", mappaDeiParamerti.get("numeroPiani")));
        postParameters.add(new BasicNameValuePair("stato", mappaDeiParamerti.get("stato")));
        postParameters.add(new BasicNameValuePair("superficie", mappaDeiParamerti.get("superficie")));
        postParameters.add(new BasicNameValuePair("gtxNumeroPostiAuto", mappaDeiParamerti.get("gtxNumeroPostiAuto")));
        postParameters.add(new BasicNameValuePair("tipoProprieta", mappaDeiParamerti.get("tipoProprieta")));
        postParameters.add(new BasicNameValuePair("tipoMandato", mappaDeiParamerti.get("tipoMandato")));
        postParameters.add(new BasicNameValuePair("gtxIdContatto", mappaDeiParamerti.get("gtxIdContatto")));
        postParameters.add(new BasicNameValuePair("idCont", mappaDeiParamerti.get("idCont")));
        postParameters.add(new BasicNameValuePair("classeImmobile", mappaDeiParamerti.get("classeImmobile")));
        postParameters.add(new BasicNameValuePair("terreno_proprieta", mappaDeiParamerti.get("terreno_proprieta")));
        postParameters.add(new BasicNameValuePair("gtxScadenzaMandato", mappaDeiParamerti.get("gtxScadenzaMandato")));
        postParameters.add(new BasicNameValuePair("spese_condominiali", mappaDeiParamerti.get("spese_condominiali")));
        postParameters.add(new BasicNameValuePair("in_asta", mappaDeiParamerti.get("in_asta")));
        postParameters.add(new BasicNameValuePair("a_reddito", mappaDeiParamerti.get("a_reddito")));
        postParameters.add(new BasicNameValuePair("libero", mappaDeiParamerti.get("libero")));
        postParameters.add(new BasicNameValuePair("spese", mappaDeiParamerti.get("spese")));
        postParameters.add(new BasicNameValuePair("classe_energetica", mappaDeiParamerti.get("classe_energetica")));
        postParameters.add(new BasicNameValuePair("ipe", mappaDeiParamerti.get("ipe")));
        postParameters.add(new BasicNameValuePair("ipe_unita_misura", mappaDeiParamerti.get("ipe_unita_misura")));
        postParameters.add(new BasicNameValuePair("hidden_ipe", mappaDeiParamerti.get("hidden_ipe")));
        postParameters.add(new BasicNameValuePair("hidden_default_mq", mappaDeiParamerti.get("hidden_default_mq")));
        postParameters.add(new BasicNameValuePair("hidden_default_mc", mappaDeiParamerti.get("hidden_default_mc")));
        postParameters.add(new BasicNameValuePair("costruttore", mappaDeiParamerti.get("costruttore")));
        postParameters.add(new BasicNameValuePair("statoCantiere", mappaDeiParamerti.get("statoCantiere")));
        postParameters.add(new BasicNameValuePair("riscaldamento", mappaDeiParamerti.get("riscaldamento")));
        postParameters.add(new BasicNameValuePair("cucina", mappaDeiParamerti.get("cucina")));
        postParameters.add(new BasicNameValuePair("boxauto", mappaDeiParamerti.get("boxauto")));
        postParameters.add(new BasicNameValuePair("bagni", mappaDeiParamerti.get("bagni")));
        postParameters.add(new BasicNameValuePair("idGiardino", mappaDeiParamerti.get("idGiardino")));
        postParameters.add(new BasicNameValuePair("idArredamento", mappaDeiParamerti.get("idArredamento")));
        postParameters.add(new BasicNameValuePair("gtxIngresso", mappaDeiParamerti.get("gtxIngresso")));
        postParameters.add(new BasicNameValuePair("gtxRipostiglio", mappaDeiParamerti.get("gtxRipostiglio")));
        postParameters.add(new BasicNameValuePair("gtxCantina", mappaDeiParamerti.get("gtxCantina")));
        postParameters.add(new BasicNameValuePair("gtxMansarda", mappaDeiParamerti.get("gtxMansarda")));
        postParameters.add(new BasicNameValuePair("gtxTaverna", mappaDeiParamerti.get("gtxTaverna")));
        postParameters.add(new BasicNameValuePair("gtxInfissiEsterni", mappaDeiParamerti.get("gtxInfissiEsterni")));
        postParameters.add(new BasicNameValuePair("gtxImpiantoTv", mappaDeiParamerti.get("gtxImpiantoTv")));
        postParameters.add(new BasicNameValuePair("gtxCaminetto", mappaDeiParamerti.get("gtxCaminetto")));
        postParameters.add(new BasicNameValuePair("gtxPortaBlindata", mappaDeiParamerti.get("gtxPortaBlindata")));
        postParameters.add(new BasicNameValuePair("gtxImpiantoAllarme", mappaDeiParamerti.get("gtxImpiantoAllarme")));
        postParameters.add(new BasicNameValuePair("gtxPiscina", mappaDeiParamerti.get("gtxPiscina")));
        postParameters.add(new BasicNameValuePair("gtxCampoTennis", mappaDeiParamerti.get("gtxCampoTennis")));
        postParameters.add(new BasicNameValuePair("flag_auto_it", mappaDeiParamerti.get("flag_auto_it")));
        postParameters.add(new BasicNameValuePair("descrizione_it", mappaDeiParamerti.get("descrizione_it")));
        postParameters.add(new BasicNameValuePair("flag_auto_en", mappaDeiParamerti.get("flag_auto_en")));
        postParameters.add(new BasicNameValuePair("descrizione_en", mappaDeiParamerti.get("descrizione_en")));
        postParameters.add(new BasicNameValuePair("flag_auto_de", mappaDeiParamerti.get("flag_auto_de")));
        postParameters.add(new BasicNameValuePair("descrizione_de", mappaDeiParamerti.get("descrizione_de")));
        postParameters.add(new BasicNameValuePair("flag_auto_fr", mappaDeiParamerti.get("flag_auto_fr")));
        postParameters.add(new BasicNameValuePair("descrizione_fr", mappaDeiParamerti.get("descrizione_fr")));
        postParameters.add(new BasicNameValuePair("flag_auto_es", mappaDeiParamerti.get("flag_auto_es")));
        postParameters.add(new BasicNameValuePair("descrizione_es", mappaDeiParamerti.get("descrizione_es")));
        postParameters.add(new BasicNameValuePair("flag_auto_pt", mappaDeiParamerti.get("flag_auto_pt")));
        postParameters.add(new BasicNameValuePair("descrizione_pt", mappaDeiParamerti.get("descrizione_pt")));
        postParameters.add(new BasicNameValuePair("flag_auto_ru", mappaDeiParamerti.get("flag_auto_ru")));
        postParameters.add(new BasicNameValuePair("descrizione_ru", mappaDeiParamerti.get("descrizione_ru")));
        postParameters.add(new BasicNameValuePair("flag_auto_gr", mappaDeiParamerti.get("flag_auto_gr")));
        postParameters.add(new BasicNameValuePair("descrizione_gr", mappaDeiParamerti.get("descrizione_gr")));
        postParameters.add(new BasicNameValuePair("remLen_it", mappaDeiParamerti.get("remLen_it")));
        postParameters.add(new BasicNameValuePair("remLen_en", mappaDeiParamerti.get("remLen_en")));
        postParameters.add(new BasicNameValuePair("remLen_de", mappaDeiParamerti.get("remLen_de")));
        postParameters.add(new BasicNameValuePair("remLen_fr", mappaDeiParamerti.get("remLen_fr")));
        postParameters.add(new BasicNameValuePair("remLen_es", mappaDeiParamerti.get("remLen_es")));
        postParameters.add(new BasicNameValuePair("remLen_pt", mappaDeiParamerti.get("remLen_pt")));
        postParameters.add(new BasicNameValuePair("remLen_ru", mappaDeiParamerti.get("remLen_ru")));
        postParameters.add(new BasicNameValuePair("remLen_gr", mappaDeiParamerti.get("remLen_gr")));
        postParameters.add(new BasicNameValuePair("gtxTitolo_per_riviste", mappaDeiParamerti.get("gtxTitolo_per_riviste")));
        postParameters.add(new BasicNameValuePair("gtxDescrizione_per_riviste", mappaDeiParamerti.get("gtxDescrizione_per_rivist...")));
        postParameters.add(new BasicNameValuePair("callback", mappaDeiParamerti.get("callback")));   
        
        //Rimuovo i parametri che non devono essere inviati
        Iterator<NameValuePair> iterator = postParameters.iterator();
        while(iterator.hasNext()) {
        	BasicNameValuePair currentParam = (BasicNameValuePair) iterator.next();
        	if(currentParam.getValue()=="***DONOTSEND***")  {
        		iterator.remove();	
        	}
        }
        
        try {
        	Object[] response = connessione_4.post("Connessione 4 - POST dello step 2 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "2" + "&categoria=" + mappaDeiParamerti.get("categoria") + "&tipo=" + mappaDeiParamerti.get("tipo") + "&callback=refresh", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	location = getHeaderValueByName(responseHeaders, "Location");
        	int start = location.indexOf("idAnnuncio=") + 11;
	        int end = location.length();
	        codiceInserzione = location.substring(start, end);
	        mappaDeiParamerti.put("idAnnuncio", codiceInserzione);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 5 - GET dello step 3 di inserimento immobile
    	HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
    	try {
			connessione_5.get("Connessione 5 - GET dello step 3 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "3"  + "&tipo=" + mappaDeiParamerti.get("tipo") + "&idAnnuncio=" + mappaDeiParamerti.get("idAnnuncio"), debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//POST per recupero codici Comune, Provincia e Regione
    	HttpPortalPostConnection connessione_a = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("idNaz", "IT"));
        postParameters.add(new BasicNameValuePair("maxRes", "10"));
        postParameters.add(new BasicNameValuePair("s", scheda.comune));
        postParameters.add(new BasicNameValuePair("showNation", "0"));
        try {
        	Object[] response = connessione_a.post_test("Connessione a - POST per recupero codici Comune, Provincia e Regione", URLROOT + "/comune_suggestion.php", postParameters, debugMode, URLROOT + "/inserimento_annuncio.php?step=" + "3"  + "&tipo=" + mappaDeiParamerti.get("tipo") + "&idAnnuncio=" + mappaDeiParamerti.get("idAnnuncio"));
        	String responseBody = (String)response[1];
        	JSONObject json = new JSONObject(responseBody);
        	JSONArray jsonResults = json.getJSONArray("results");
        	
        	System.out.println("JSON: " + jsonResults);
	        System.out.println("JSON: " + jsonResults.length());
	        
	        double resultComparation = 0;
	        
	        for(int i=0; i<jsonResults.length(); i++) {
	        	JSONObject currentJson = jsonResults.getJSONObject(i);
	        	
	        	List<char[]> charListPortale = bigram(currentJson.getString("comune_nome"));
        		List<char[]> charListImagination = bigram(scheda.comune);
        		
        		double actualResultComparation = dice(charListPortale, charListImagination);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			idComune = currentJson.getString("comune_idComune");
        			mappaDeiParamerti.put("idComune", idComune);
        			
        			idNazione = currentJson.getString("nazione_idNazione");
        			mappaDeiParamerti.put("idNazione", idNazione);
        			
        			idProvincia = currentJson.getString("provincia_idProvincia");
        			mappaDeiParamerti.put("idProvincia", idProvincia);
        			
        			idRegione = currentJson.getString("regione_idRegione");
        			mappaDeiParamerti.put("idRegione", idRegione);
        			
        			textComune = currentJson.getString("comune_nome");
        			mappaDeiParamerti.put("textComune", textComune);
        			
        			textNazione = currentJson.getString("nazione_nome");
        			mappaDeiParamerti.put("textNazione", textNazione);
        			
        			textProvincia = currentJson.getString("provincia_nome");
        			mappaDeiParamerti.put("textProvincia", textProvincia);
        			
        			textRegione = currentJson.getString("regione_nome");
        			mappaDeiParamerti.put("textRegione", textRegione);
        			
        			com_latitudine = currentJson.getString("comune_latitudine");
        			mappaDeiParamerti.put("com_latitudine", com_latitudine);
        			
        			com_longitudine = currentJson.getString("comune_longitudine"); 
        			mappaDeiParamerti.put("com_longitudine", com_longitudine);
        			
        			System.out.println("JSON1: " + idComune);
        			System.out.println("JSON2: " + idNazione);
        			System.out.println("JSON3: " + idProvincia);
        			System.out.println("JSON4: " + idRegione);
        			System.out.println("JSON5: " + textComune);
        			System.out.println("JSON6: " + textNazione);
        			System.out.println("JSON7: " + textProvincia);
        		}       		
        		System.out.println("Risultato comparazione: " + resultComparation);        		        	
	        }
	        
        } catch (IOException | RuntimeException | ParseException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
    	
    	
    	//Connessioni 6 - POST delle immagini a corredo della scheda
    	for(int i=0; i<scheda.arrayImages.length; i++) {
    		if(scheda.arrayImages[i]!=null) {
    			HttpPortalPostConnection connessione_6 = new HttpPortalPostConnection();
    	    	
    			try {
	    			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	    	        FileBody bin = new FileBody(scheda.arrayImages[i]);
	    	        reqEntity.addPart("action", new StringBody("add"));
	    	        reqEntity.addPart("imageid", new StringBody(""));
	    	        reqEntity.addPart("pdftoremove", new StringBody(""));
	    	        reqEntity.addPart("tipo", new StringBody(mappaDeiParamerti.get("tipo")));
	    	        reqEntity.addPart("idAnnuncio", new StringBody(mappaDeiParamerti.get("idAnnuncio")));
	    	        reqEntity.addPart("immagine", bin );
	    	        
	    	        connessione_6.post("Connessione 6_" + i + " - inserimento immagine " + i, URLROOT2 + "/inserimento_immagini.php", reqEntity, debugMode);
    			} catch (IOException | RuntimeException e) {
    				throw new HttpCommunicationException(e);
    			}
    	    	finally {
    	    		postParameters.clear();
    	    	}
            }
    	}
    	
    	
    	
    	//Connessione 7 - POST dello step 3 di inserimento immobile
    	HttpPortalPostConnection connessione_7 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("_textComune", mappaDeiParamerti.get("_textComune")));
    	postParameters.add(new BasicNameValuePair("action", "save"));
    	postParameters.add(new BasicNameValuePair("cap", mappaDeiParamerti.get("cap")));
    	postParameters.add(new BasicNameValuePair("com_latitudine", mappaDeiParamerti.get("com_latitudine")));
    	postParameters.add(new BasicNameValuePair("com_longitudine", mappaDeiParamerti.get("com_longitudine")));
    	postParameters.add(new BasicNameValuePair("confirm_address", mappaDeiParamerti.get("confirm_address")));
    	postParameters.add(new BasicNameValuePair("flagIndirizzo", mappaDeiParamerti.get("flagIndirizzo")));
    	postParameters.add(new BasicNameValuePair("flagIndirizzo_fake", mappaDeiParamerti.get("flagIndirizzo_fake")));
    	postParameters.add(new BasicNameValuePair("idAnnuncio", mappaDeiParamerti.get("idAnnuncio")));
    	postParameters.add(new BasicNameValuePair("idComune", mappaDeiParamerti.get("idComune")));
    	postParameters.add(new BasicNameValuePair("idLocalita", mappaDeiParamerti.get("idLocalita")));
    	postParameters.add(new BasicNameValuePair("idMacrozona", mappaDeiParamerti.get("idMacrozona")));
    	postParameters.add(new BasicNameValuePair("idMicrozona", mappaDeiParamerti.get("idMicrozona")));
    	postParameters.add(new BasicNameValuePair("idNazione", mappaDeiParamerti.get("idNazione")));
    	postParameters.add(new BasicNameValuePair("idProvincia", mappaDeiParamerti.get("idProvincia")));
    	postParameters.add(new BasicNameValuePair("idRegione", mappaDeiParamerti.get("idRegione")));
    	postParameters.add(new BasicNameValuePair("indirizzo", mappaDeiParamerti.get("indirizzo")));
    	postParameters.add(new BasicNameValuePair("indirizzo_fake", mappaDeiParamerti.get("indirizzo_fake")));
    	postParameters.add(new BasicNameValuePair("indirizzo_fake_orig", mappaDeiParamerti.get("indirizzo_fake_orig")));
    	postParameters.add(new BasicNameValuePair("latitudine", mappaDeiParamerti.get("latitudine")));
    	postParameters.add(new BasicNameValuePair("longitudine", mappaDeiParamerti.get("longitudine")));
    	postParameters.add(new BasicNameValuePair("nextStep", mappaDeiParamerti.get("nextStep")));
    	postParameters.add(new BasicNameValuePair("num_localita", mappaDeiParamerti.get("num_localita")));
    	postParameters.add(new BasicNameValuePair("numeroCivico", mappaDeiParamerti.get("numeroCivico")));
    	postParameters.add(new BasicNameValuePair("planimetria", mappaDeiParamerti.get("planimetria")));
    	postParameters.add(new BasicNameValuePair("step", "3"));
    	postParameters.add(new BasicNameValuePair("textComune", mappaDeiParamerti.get("textComune")));
    	postParameters.add(new BasicNameValuePair("textNazione", mappaDeiParamerti.get("textNazione")));
    	postParameters.add(new BasicNameValuePair("textProvincia", mappaDeiParamerti.get("textProvincia")));
    	postParameters.add(new BasicNameValuePair("textRegione", mappaDeiParamerti.get("textRegione")));
    	postParameters.add(new BasicNameValuePair("tipo", mappaDeiParamerti.get("tipo")));
    	postParameters.add(new BasicNameValuePair("virtual_tour", mappaDeiParamerti.get("virtual_tour")));
    	//postParameters.add(new BasicNameValuePair("zonaobbligatoria", mappaDeiParamerti.get("zonaobbligatoria")));
    	postParameters.add(new BasicNameValuePair("zoom", mappaDeiParamerti.get("zoom")));
        
        //Rimuovo i parametri che non devono essere inviati
        /*Iterator<NameValuePair> iterator_7 = postParameters.iterator();
        while(iterator_7.hasNext()) {
        	BasicNameValuePair currentParam = (BasicNameValuePair) iterator.next();
        	if(currentParam.getValue()=="***DONOTSEND***")  {
        		iterator.remove();	
        	}
        }*/
    	
        
        try {
        	connessione_7.post("Connessione 7 - POST dello step 3 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "3" + "&tipo=" + mappaDeiParamerti.get("tipo") + "&idAnnuncio=" + mappaDeiParamerti.get("idAnnuncio"), postParameters, debugMode);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 8 - GET dello step 4 di inserimento immobile
    	HttpPortalGetConnection connessione_8 = new HttpPortalGetConnection();
    	try {
			connessione_8.get("Connessione 8 - GET dello step 4 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "4"  + "&idAnnuncio=" + mappaDeiParamerti.get("idAnnuncio") + "&tipo=" + mappaDeiParamerti.get("tipo") + "&azionePayPal=show", debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 9 - POST dello step 4 di inserimento immobile
    	HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("SelTipoPagamento", mappaDeiParamerti.get("SelTipoPagamento")));
    	postParameters.add(new BasicNameValuePair("address1", mappaDeiParamerti.get("address1")));
    	postParameters.add(new BasicNameValuePair("amount", mappaDeiParamerti.get("amount")));
    	postParameters.add(new BasicNameValuePair("azionePayPal", mappaDeiParamerti.get("azionePayPal")));
    	postParameters.add(new BasicNameValuePair("bn", mappaDeiParamerti.get("bn")));
    	postParameters.add(new BasicNameValuePair("business", mappaDeiParamerti.get("business")));
    	postParameters.add(new BasicNameValuePair("check_1", mappaDeiParamerti.get("check_1")));
    	postParameters.add(new BasicNameValuePair("check_108", mappaDeiParamerti.get("check_108")));
    	postParameters.add(new BasicNameValuePair("cmd", mappaDeiParamerti.get("cmd")));
    	postParameters.add(new BasicNameValuePair("currency_code", mappaDeiParamerti.get("currency_code")));
    	postParameters.add(new BasicNameValuePair("first_name", mappaDeiParamerti.get("first_name")));
    	postParameters.add(new BasicNameValuePair("idAnnuncio", mappaDeiParamerti.get("idAnnuncio")));
    	postParameters.add(new BasicNameValuePair("item_name", mappaDeiParamerti.get("item_name")));
    	postParameters.add(new BasicNameValuePair("item_number", mappaDeiParamerti.get("item_number")));
    	postParameters.add(new BasicNameValuePair("last_name", mappaDeiParamerti.get("last_name")));
    	postParameters.add(new BasicNameValuePair("lc", mappaDeiParamerti.get("lc")));
    	postParameters.add(new BasicNameValuePair("no_note", mappaDeiParamerti.get("no_note")));
    	postParameters.add(new BasicNameValuePair("no_shipping", mappaDeiParamerti.get("no_shipping")));
    	postParameters.add(new BasicNameValuePair("notify_url", mappaDeiParamerti.get("notify_url")));
    	postParameters.add(new BasicNameValuePair("prezzoTotale", mappaDeiParamerti.get("prezzoTotale")));
    	postParameters.add(new BasicNameValuePair("_return", mappaDeiParamerti.get("_return")));
    	postParameters.add(new BasicNameValuePair("return_cancel", mappaDeiParamerti.get("return_cancel")));
    	postParameters.add(new BasicNameValuePair("richiesta", mappaDeiParamerti.get("richiesta")));
    	postParameters.add(new BasicNameValuePair("step", "4"));
    	postParameters.add(new BasicNameValuePair("tipo", mappaDeiParamerti.get("tipo")));
    	postParameters.add(new BasicNameValuePair("zip", mappaDeiParamerti.get("zip")));
        
        //Rimuovo i parametri che non devono essere inviati
        Iterator<NameValuePair> iterator_9 = postParameters.iterator();
        while(iterator_9.hasNext()) {
        	BasicNameValuePair currentParam = (BasicNameValuePair) iterator.next();
        	if(currentParam.getValue()=="***DONOTSEND***")  {
        		iterator.remove();	
        	}
        }  	
        
        try {
        	connessione_9.post("Connessione 9 - POST dello step 9 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "4" + "&idAnnuncio=" + mappaDeiParamerti.get("idAnnuncio") + "&tipo=" + mappaDeiParamerti.get("tipo") + "&azionePayPal=show", postParameters, debugMode);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
		
      
    	
    	//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
    	if(inserimentoOK) {
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		//scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);
    		      	
    		if(!isSequential) {   			
    			System.out.println("Inserita in: " + NOMEPORTALE);       		
        		
    			//Aggiorna i pulsanti del pannello inserimento
    			//j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
    			
    			//Invio mail di conferma inserimento 
    			//sendConfirmationMail(scheda, NOMEPORTALE, codiceInserzione);
           	
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
	public boolean visualizzaScheda(SchedaImmobile scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codiceInserzione + "...");
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/risultati_ricerca.php";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
			
		} catch (IOException e ) {
			//
		}
		
		return true;
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceInserzione + "...");
		
		codiceInserzione = scheda.getCodiceInserimento(idPortale);			
		
	
		//CONNESSIONI
    	
        
        //Aggiorno la lista dei portali in cui è presenta la scheda corrente
  		scheda.eliminaInserimentoPortale(idPortale);			
  	 		
  		if(!isSequential) {
  			//Aggiorno i pulsanti del pannello inserimento
  			j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
  			
  			System.out.println("Eliminata da: " + NOMEPORTALE);
  		  	
  	  		//Stampo a video un messaggio informativo
  	        JOptionPane.showMessageDialog(null, "Scheda immobile eliminata da: " + NOMEPORTALE);
		}
  		
  		return true;
	
	}
		
	
	//Metodo per la valutazione dei parametri
	public void inizializzaParametri() throws HttpCommunicationException {			
		
		switch (scheda.categoriaImmobile) {
		case "Residenziale":
			categoria = "1";
			break;
		case "Commerciale":
			categoria = "2";
			break;
		/*case "Industriale":	//non esiste nel sito
			categoria = "";
			break;*/
		case "Terreni":
			categoria = "10";
			break;
		default:
			categoria = "";
		}
		mappaDeiParamerti.put("categoria", categoria);
		
		
		switch (scheda.tipologiaContratto) {
		case "Affitto":
			tipo = "2";
			break;
		case "Vendita":
			tipo = "1";
			break;
		default:
			tipo = "";
		}
		mappaDeiParamerti.put("tipo", tipo);
		
		
		int prezzoInserito = Integer.parseInt(scheda.prezzoImmobile);
		fasciaprezzo = "";
		switch (scheda.tipologiaContratto)
    	{
    	    case "Vendita":
    	    	if(prezzoInserito==0){
    	    		fasciaprezzo = "";
    	        }
    	        if(prezzoInserito>500000){
    	        	fasciaprezzo = "6";
    	        }
    	        if(prezzoInserito<=500000){
    	        	fasciaprezzo = "5";
    	        }
    	        if(prezzoInserito<=300000){
    	        	fasciaprezzo = "4";
    	        }
    	        if(prezzoInserito<=200000){
    	        	fasciaprezzo = "3";
    	        }
    	        if(prezzoInserito<=150000){
    	        	fasciaprezzo = "2";
    	        }
    	        if(prezzoInserito<=100000){
    	        	fasciaprezzo = "1";
    	        }
    	        break;
    	    case "Affitto":
    	    	if(prezzoInserito==0){
    	        	fasciaprezzo = "";
    	        }
    	    	if(prezzoInserito>0){
    	        	fasciaprezzo = "7";
    	        }
    	    	if(prezzoInserito>500){
    	        	fasciaprezzo = "8";
    	        }
    	    	if(prezzoInserito>1000){
    	        	fasciaprezzo = "9";
    	        }
		        if(prezzoInserito>1500){
		        	fasciaprezzo = "10";
		        }
    	        if(prezzoInserito>2000){
    	        	fasciaprezzo = "11";
    	        }
    	    	break;
    	}
		mappaDeiParamerti.put("fasciaprezzo", fasciaprezzo);
		
				
		switch (scheda.numeroCamere) {
		case "1":
			gtxNumeroCamereDaLetto = "1";
			break;
		case "2":
			gtxNumeroCamereDaLetto = "2";
			break;
		case "3":
			gtxNumeroCamereDaLetto = "3";
			break;
		case "4":
			gtxNumeroCamereDaLetto = "4";
			break;
		case "5":
			gtxNumeroCamereDaLetto = "5";
			break;
		case ">5":
			gtxNumeroCamereDaLetto = "5+";
			break;
		default:
			gtxNumeroCamereDaLetto = "";
		}
		mappaDeiParamerti.put("gtxNumeroCamereDaLetto", gtxNumeroCamereDaLetto);
		
		
		gtxAnnoCostruzione = scheda.annoCostruzione;
		mappaDeiParamerti.put("gtxAnnoCostruzione", gtxAnnoCostruzione);
		
		
		codice = scheda.codiceInserzione;
		mappaDeiParamerti.put("codice", codice);
				
		
		prezzo = scheda.prezzoImmobile;
		mappaDeiParamerti.put("prezzo", prezzo);
		
		
		gtxNumeroSoggiornoSalotto = "";
		mappaDeiParamerti.put("gtxNumeroSoggiornoSalotto", gtxNumeroSoggiornoSalotto);
		
				
		switch (scheda.piano) {
		case "Piano terra":
			piano = "3";
			break;
		case "Primo piano":
			piano = "4";
			break;
		case "Piano intermedio":
			piano = "";
			break;
		case "Piano alto":
			piano = "";
			break;
		case "Ultimo piano":
			piano = "15";
			break;
		case "Su più livelli":
			piano = "17";
			break;
		default:
			piano = "";
		}
		mappaDeiParamerti.put("piano", piano);
		
				
		switch (scheda.tipologiaImmobile) {
		case "Appartamento":
			tipologia = "4";
			break;
		case "Attico":
			tipologia = "5";
			break;
		case "Bifamiliare":
			tipologia = "7";
			break;
		case "Casa":
			tipologia = "7";
			break;
		case "Garage":
			tipologia = "6";
			break;
		case "Palazzo":
			tipologia = "10";
			break;
		case "Rustico":
			tipologia = "11";
			break;
		case "Villa":
			tipologia = "12";
			break;
		case "Villaschiera":
			tipologia = "13";
			break;
		case "Agriturismo":
			tipologia = "63";
			break;
		case "Albergo":
			tipologia = "63";
			break;
		case "Bar":
			tipologia = "63";
			break;
		case "Negozio":
			tipologia = "63";
			break;
		case "Ristorante":
			tipologia = "63";
			break;
		case "Ufficio":
			tipologia = "63";
			break;
		case "Capannone":
			tipologia = "63";
			break;
		case "Laboratorio":
			tipologia = "63";
			break;
		case "Magazzino":
			tipologia = "63";
			break;
		case "Terreno residenziale":
			tipologia = "28";
			break;
		case "Terreno agricolo":
			tipologia = "28";
			break;
		case "Terreno industriale":
			tipologia = "28";
			break;
		default:
			tipologia = "";
		}
		mappaDeiParamerti.put("tipologia", tipologia);
		
					
		switch (scheda.numeroLocali) {
		case "1":
			locali = "1 ";
			break;
		case "2":
			locali = "2 ";
			break;
		case "3":
			locali = "3 ";
			break;
		case "4":
			locali = "4 ";
			break;
		case "5":
			locali = "5 ";
			break;
		case "6":
			locali = ">5 ";
			break;
		case "7":
			locali = ">5 ";
			break;
		case ">7":
			locali = ">5 ";
			break;
		default:
			locali = "";
		}
		mappaDeiParamerti.put("locali", locali);
		
		
		gtxNumeroAltreCamereStanze = "";
		mappaDeiParamerti.put("gtxNumeroAltreCamereStanze", gtxNumeroAltreCamereStanze);

		
		numeroPiani = scheda.numeroTotalePiani;
		mappaDeiParamerti.put("numeroPiani", numeroPiani);
		
				
		switch (scheda.statoImmobile) {
		case "Nuovo":
			stato = "1";
			break;
		case "Ristrutturato":
			stato = "6";
			break;
		case "Da ristrutturare":
			stato = "5";
			break;
		case "In buono stato":
			stato = "2";
			break;
		case "Abitabile":
			stato = "2";
			break;
		case "Ottimo":
			stato = "6";
			break;
		case "In costruzione":
			stato = "1";
			break;
		default:
			stato = "";
		}
		mappaDeiParamerti.put("stato", stato);
		
		 		
		superficie = scheda.superficieImmobile;
		mappaDeiParamerti.put("superficie", superficie);
				
		
		gtxNumeroPostiAuto = "";
		mappaDeiParamerti.put("gtxNumeroPostiAuto", gtxNumeroPostiAuto);
		
		
		tipoProprieta = "";
		mappaDeiParamerti.put("tipoProprieta", tipoProprieta);
		
		
		tipoMandato = "";
		mappaDeiParamerti.put("tipoMandato", tipoMandato);
				
		
		gtxIdContatto = "";
		mappaDeiParamerti.put("gtxIdContatto", gtxIdContatto);
		
			
		idCont = "";
		mappaDeiParamerti.put("idCont", idCont);
		
			
		classeImmobile = "";
		mappaDeiParamerti.put("classeImmobile", classeImmobile);
		
		
		terreno_proprieta = "";
		mappaDeiParamerti.put("terreno_proprieta", terreno_proprieta);
		
		
		gtxScadenzaMandato = "";
		mappaDeiParamerti.put("gtxScadenzaMandato", gtxScadenzaMandato);
		
		
		spese_condominiali = "";
		mappaDeiParamerti.put("spese_condominiali", spese_condominiali);
		
		in_asta = "***DONOTSEND***";
		mappaDeiParamerti.put("in_asta", in_asta);
		
		
		a_reddito = "***DONOTSEND***";
		mappaDeiParamerti.put("a_reddito", a_reddito);
		
		libero = "***DONOTSEND***";
		mappaDeiParamerti.put("libero", libero);
		
				
		spese = "";
		mappaDeiParamerti.put("spese", spese);
			
		
		switch (scheda.certificazioniEnergetiche) {
		case "Certificazione energetica A+":
			classe_energetica = "A+";
			break;
		case "Certificazione energetica A":
			classe_energetica = "A";
			break;
		case "Certificazione energetica B":
			classe_energetica = "B";
			break;
		case "Certificazione energetica C":
			classe_energetica = "C";
			break;
		case "Certificazione energetica D":
			classe_energetica = "D";
			break;
		case "Certificazione energetica E":
			classe_energetica = "E";
			break;
		case "Certificazione energetica F":
			classe_energetica = "F";
			break;
		case "Certificazione energetica G":
			classe_energetica = "G";
			break;
		case "Non specificata":
			classe_energetica = "";
			break;
		default:
			classe_energetica = "NC";
		}
		mappaDeiParamerti.put("classe_energetica", classe_energetica);
		
		
		ipe = "≥ 175";
		mappaDeiParamerti.put("ipe", ipe);
		
		
		ipe_unita_misura = "m2";
		mappaDeiParamerti.put("ipe_unita_misura", ipe_unita_misura);
		
		
		hidden_ipe = "-1";
		mappaDeiParamerti.put("hidden_ipe", hidden_ipe);
		
		
		hidden_default_mq = "≥ 175";
		mappaDeiParamerti.put("hidden_default_mq", hidden_default_mq);
		
		
		hidden_default_mc = "≥ 175";
		mappaDeiParamerti.put("hidden_default_mc", hidden_default_mc);
		
		
		costruttore = "";
		mappaDeiParamerti.put("costruttore", costruttore);
		
		
		statoCantiere = "";
		mappaDeiParamerti.put("statoCantiere", statoCantiere);
		
				
		switch (scheda.tipologiaRiscaldamento) {
		case "Assente":
			riscaldamento = "3";
			break;
		case "Centralizzato":
			riscaldamento = "2";
			break;
		case "Autonomo":
			riscaldamento = "1";
			break;
		case "Stufa":
			break;
		default:
			riscaldamento = "***DONOTSEND***";
		}
		mappaDeiParamerti.put("riscaldamento", riscaldamento);
		
			
		cucina = "***DONOTSEND***";
		mappaDeiParamerti.put("cucina", cucina);
		
		
		boxauto = "***DONOTSEND***";
		mappaDeiParamerti.put("boxauto", boxauto);
		
		
		switch (scheda.numeroBagni) {
		case "1":
			bagni = "1";
			break;
		case "2":
			bagni = "2";
			break;
		case "3":
			bagni = "3";
			break;
		case "4":
			bagni = ">3";
			break;
		case "5":
			bagni = ">3";
			break;
		case ">5":
			bagni = ">3";
			break;
		default:
			bagni = "***DONOTSEND***";
		}
		mappaDeiParamerti.put("bagni", bagni);
		
				
		switch (scheda.giardino) {
		case "Assente":
			idGiardino = "30";
			break;
		case "Giardino condominiale":
			idGiardino = "20";
			break;
		case "Giardino ad uso esclusivo":
			idGiardino = "10";
			break;
		default:
			idGiardino = "***DONOTSEND***";
		}
		mappaDeiParamerti.put("idGiardino", idGiardino);
		
				
		switch (scheda.arredamenti) {
		case "Arredato":
			idArredamento = "10";
			break;
		case "Semi arredato":
			idArredamento = "30";
			break;
		case "Non arredato":
			idArredamento = "20";
			break;
		default:
			idArredamento = "***DONOTSEND***";
		}
		mappaDeiParamerti.put("idArredamento", idArredamento);
		
		
		gtxIngresso = "0";
		mappaDeiParamerti.put("gtxIngresso", gtxIngresso);
		
		
		gtxRipostiglio = "0";
		mappaDeiParamerti.put("gtxRipostiglio", gtxRipostiglio);
		
		
		gtxCantina = scheda.cantina?"1":"0";
		mappaDeiParamerti.put("gtxCantina", gtxCantina);
		
		
		gtxMansarda = "0";
		mappaDeiParamerti.put("gtxMansarda", gtxMansarda);
		
		
		gtxTaverna = "0";
		mappaDeiParamerti.put("gtxTaverna", gtxTaverna);
		
		
		gtxInfissiEsterni = "0";
		mappaDeiParamerti.put("gtxInfissiEsterni", gtxInfissiEsterni);
		
		
		gtxImpiantoTv = "0";
		mappaDeiParamerti.put("gtxImpiantoTv", gtxImpiantoTv);
		
		
		gtxFibraOttica = scheda.bandaLarga?"on":"***DONOTSEND***";
		mappaDeiParamerti.put("gtxFibraOttica", gtxFibraOttica);
		
		
		ascensore = scheda.ascensore?"on":"***DONOTSEND***";
		mappaDeiParamerti.put("ascensore", ascensore);		
				
		
		gtxImpiantoAllarme = scheda.sistemaDiAllarme?"on":"***DONOTSEND***";
		mappaDeiParamerti.put("gtxImpiantoAllarme", gtxImpiantoAllarme);
		
				
		gtxCancelloElettrico = scheda.cancelloElettrico?"on":"***DONOTSEND***";
		mappaDeiParamerti.put("gtxCancelloElettrico", gtxCancelloElettrico);
				
		
		flag_auto_it = "";
		mappaDeiParamerti.put("flag_auto_it", flag_auto_it);
		
		
		descrizione_it = scheda.testoAnnuncio;
		mappaDeiParamerti.put("descrizione_it", descrizione_it);
		
		
		flag_auto_en = "";
		mappaDeiParamerti.put("flag_auto_en", flag_auto_en);
		
		
		descrizione_en = "";
		mappaDeiParamerti.put("descrizione_en", descrizione_en);
		
		
		flag_auto_de = "";
		mappaDeiParamerti.put("flag_auto_de", flag_auto_de);
		
		
		descrizione_de = "";
		mappaDeiParamerti.put("descrizione_de", descrizione_de);
		
		
		flag_auto_fr = "";
		mappaDeiParamerti.put("flag_auto_fr", flag_auto_fr);
		
		
		descrizione_fr = "";
		mappaDeiParamerti.put("descrizione_fr", descrizione_fr);
		
		
		flag_auto_es = "";
		mappaDeiParamerti.put("flag_auto_es", flag_auto_es);
		
		
		descrizione_es = "";
		mappaDeiParamerti.put("descrizione_es", descrizione_es);
		
		
		flag_auto_pt = "";
		mappaDeiParamerti.put("flag_auto_pt", flag_auto_pt);
		
		
		descrizione_pt = "";
		mappaDeiParamerti.put("descrizione_pt", descrizione_pt);
		
		
		flag_auto_ru = "";
		mappaDeiParamerti.put("flag_auto_ru", flag_auto_ru);
		
		
		descrizione_ru = "";
		mappaDeiParamerti.put("descrizione_ru", descrizione_ru);
		
		
		flag_auto_gr = "";
		mappaDeiParamerti.put("flag_auto_gr", flag_auto_gr);
		
		
		descrizione_gr = "";
		mappaDeiParamerti.put("descrizione_gr", descrizione_gr);
		
		
		int lunghezzaDescrizione = scheda.testoAnnuncio.length();
		remLen_it = Integer.toString(3000 - lunghezzaDescrizione);
		mappaDeiParamerti.put("remLen_it", remLen_it);
		
				
		remLen_en = "300";
		mappaDeiParamerti.put("remLen_en", remLen_en);
		
		
		remLen_de = "300";
		mappaDeiParamerti.put("remLen_de", remLen_de);
		
		
		remLen_fr = "300";
		mappaDeiParamerti.put("remLen_fr", remLen_fr);
		
		
		remLen_es = "300";
		mappaDeiParamerti.put("remLen_es", remLen_es);
		
		
		remLen_pt = "300";
		mappaDeiParamerti.put("remLen_pt", remLen_pt);
		
		
		remLen_ru = "300";
		mappaDeiParamerti.put("remLen_ru", remLen_ru);
		
		
		remLen_gr = "300";
		mappaDeiParamerti.put("remLen_gr", remLen_gr);
			
		
		gtxTitolo_per_riviste = "";
		mappaDeiParamerti.put("gtxTitolo_per_riviste", gtxTitolo_per_riviste);
		
		
		gtxDescrizione_per_riviste = "";
		mappaDeiParamerti.put("gtxDescrizione_per_riviste", gtxDescrizione_per_riviste);
		
		
		callback = "submit";
		mappaDeiParamerti.put("callback", callback);
		
		
		_textComune = scheda.comune;
		mappaDeiParamerti.put("_textComune", _textComune);
		
				
		cap = scheda.cap;
		mappaDeiParamerti.put("cap", cap);
		
				
		Map<String, String> latLon;
		try {
			latLon = getLatLonCoord(scheda.indirizzoLocalita, scheda.comune, scheda.provincia, scheda.regione);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new HttpCommunicationException(e);
		}
		
		latitudine = latLon.get("latitudine");
		mappaDeiParamerti.put("latitudine", latitudine);
		
		longitudine = latLon.get("longitudine");
		mappaDeiParamerti.put("longitudine", longitudine);
		
		
		confirm_address = "1";
		mappaDeiParamerti.put("confirm_address", confirm_address);
		
		
		flagIndirizzo = "2";
		mappaDeiParamerti.put("flagIndirizzo", flagIndirizzo);
		
				
		flagIndirizzo_fake = "2";
		mappaDeiParamerti.put("flagIndirizzo_fake", flagIndirizzo_fake);
		
    
        idLocalita = "";
		mappaDeiParamerti.put("idLocalita", idLocalita);
        
				
		idMacrozona = "10197";
		mappaDeiParamerti.put("idMacrozona", idMacrozona);
		
		
		idMicrozona = "11098";
		mappaDeiParamerti.put("idMicrozona", idMicrozona);
		
				
		indirizzo = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("indirizzo", indirizzo);
		
		
		indirizzo_fake = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("indirizzo_fake", indirizzo_fake);
		
		
		indirizzo_fake_orig = scheda.indirizzoLocalita;
		mappaDeiParamerti.put("indirizzo_fake_orig", indirizzo_fake_orig);
		
				
		nextStep = "";
		mappaDeiParamerti.put("nextStep", nextStep);
		
				
		num_localita = "";
		mappaDeiParamerti.put("num_localita", num_localita);
		
				
		numeroCivico = "";
		mappaDeiParamerti.put("numeroCivico", numeroCivico);
		
		
		
		planimetria = "";
		mappaDeiParamerti.put("planimetria", planimetria);
		
				
		virtual_tour = "";
		mappaDeiParamerti.put("virtual_tour", virtual_tour);
		
				
		zonaobbligatoria = "***DONOTSEND***";
		mappaDeiParamerti.put("zonaobbligatoria", zonaobbligatoria);
		
				
		zoom = "15";
		mappaDeiParamerti.put("zoom", zoom);
		
				
		SelTipoPagamento = "borsellino";
		mappaDeiParamerti.put("SelTipoPagamento", SelTipoPagamento);
		
				
		address1 = "";
		mappaDeiParamerti.put("address1", address1);
		
				
		amount = "";
		mappaDeiParamerti.put("amount", amount);
		
				
		azionePayPal = "compraSubito";
		mappaDeiParamerti.put("azionePayPal", azionePayPal);
				
		
		bn = "PP-BuyNowBF";
		mappaDeiParamerti.put("bn", bn);
		
				
		business = "SELLER";
		mappaDeiParamerti.put("business", business);
		
				
		check_1 = "on";
		mappaDeiParamerti.put("check_1", check_1);
		
		
		check_108 = "on";
		mappaDeiParamerti.put("check_108", check_108);
		
				
		cmd = "_xclick";
		mappaDeiParamerti.put("cmd", cmd);
		
				
		currency_code = "EUR";
		mappaDeiParamerti.put("currency_code", currency_code);
		
				
		first_name = "";
		mappaDeiParamerti.put("first_name", first_name);
		
				
		item_name = "acquisto pubblicazione e visibilita";
		mappaDeiParamerti.put("item_name", item_name);
		
		
		item_number = "pubblicazione";
		mappaDeiParamerti.put("item_number", item_number);
		
		
		last_name = "";
		mappaDeiParamerti.put("last_name", last_name);
		
				
		lc = "IT";
		mappaDeiParamerti.put("lc", lc);
		
		
		no_note = "1";
		mappaDeiParamerti.put("no_note", no_note);
		
		
		no_shipping = "1";
		mappaDeiParamerti.put("no_shipping", no_shipping);
		
		
		notify_url = "NOTIFY_URL";
		mappaDeiParamerti.put("notify_url", notify_url);
		
		
		prezzoTotale = "";
		mappaDeiParamerti.put("prezzoTotale", prezzoTotale);
		
		
		_return = "http://getrix.ekbl.net/inserimento_annuncio.php?step=5&azionePayPal=notifica&idAnnuncio=" + "42575673" + "&tipo=" + "1" + "&buyedVis=0";
		mappaDeiParamerti.put("return", _return);
		
		
		return_cancel = "http://getrix.ekbl.net/inserimento_annuncio.php?step=4&azionePayPal=show&idAnnuncio=" + "42575673" + "&tipo=" + "1" + "&buyedVis=0";
		mappaDeiParamerti.put("return_cancel", return_cancel);
		
		
		richiesta = "";
		mappaDeiParamerti.put("richiesta", richiesta);
		
		
		zip = "";
		mappaDeiParamerti.put("zip", zip);
		
	}
	
	
}