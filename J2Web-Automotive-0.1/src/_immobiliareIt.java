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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
	private final String URLROOT_2 = "http://media.getrix.ekbl.net";	//la root per le immagini è differente
	private final String USERNAME = "nfjrpnqp@sharklasers.com";
    private final String PASSWORD = "ts2cj1n3";
    	
    //private String codiceInserzioneTemporaneo = UUID.randomUUID().toString();
    private String codiceInserzione;
    private boolean inserimentoOK = false;
    private String location;
    private boolean debugMode = true;
    
    /*private String nomeImmagine0;
    private String nomeImmagine1;
    private String nomeImmagine2;
    private String nomeImmagine3;
    private String nomeImmagine4;
    private String nomeImmagine5;
    private String nomeImmagine6;
    private String nomeImmagine7;
    private String nomeImmagine8;
    private String nomeImmagine9;*/

    //Mappa dei parametri da inviare
    Map<String,String> mappaDeiParametri;
    
    //Lista dei parametri inviati in una singola connessione
    List<NameValuePair> postParameters;
    
    //Lista degli headers inviati in una singola connessione
    List<NameValuePair> requestHeaders; 

    //La scheda immobile su cui si lavora
    SchedaImmobile scheda;
    
    //Lista di alcuni (non necessariamente tutti) parametri inviati
    String accedi;
    String backurl;
    String openTab;
    String password;
    String username;
    String categoria;
    String tipo;
    String fasciaprezzo;
    String gtxNumeroCamereDaLetto;
    String gtxAnnoCostruzione;
    String codice;
    String prezzo;
    String gtxNumeroSoggiornoSalotto;
    String piano;
    String tipologia;
    String sottotipologia;
    String locali;
    String gtxNumeroAltreCamereStanze;
    String numeroPiani;
    String stato;
    String superficie;
    String gtxNumeroPostiAuto;
    String tipoProprieta;
    String tipoMandato;
    String gtxIdContatto;
    String idCont;
    String classeImmobile;
    String terreno_proprieta;
    String gtxScadenzaMandato;
    String spese_condominiali;
    String in_asta;
    String a_reddito;
    String libero;
    String spese;
    String classe_energetica;
    String ipe;
    String ipe_unita_misura;
    String hidden_ipe;
    String hidden_default_mq;
    String hidden_default_mc;
    String costruttore;
    String statoCantiere;
    String riscaldamento;
    String cucina;
    String boxauto;
    String bagni;
    String idGiardino;
    String idArredamento;
    String gtxIngresso;
    String gtxRipostiglio;
    String gtxCantina;
    String gtxMansarda;
    String gtxTaverna; 
    String gtxInfissiEsterni; 
    String gtxImpiantoTv; 
    String gtxFibraOttica;
    String ascensore;
    String gtxImpiantoAllarme;
    String gtxCancelloElettrico;
    String flag_auto_it; 
    String descrizione_it; 
    String flag_auto_en; 
    String descrizione_en; 
    String flag_auto_de; 
    String descrizione_de; 
    String flag_auto_fr; 
    String descrizione_fr; 
    String flag_auto_es; 
    String descrizione_es; 
    String flag_auto_pt; 
    String descrizione_pt; 
    String flag_auto_ru; 
    String descrizione_ru; 
    String flag_auto_gr; 
    String descrizione_gr; 
    String remLen_it;
    String remLen_en;  
    String remLen_de;  
    String remLen_fr;  
    String remLen_es;  
    String remLen_pt;  
    String remLen_ru;  
    String remLen_gr;  
    String gtxTitolo_per_riviste; 
    String gtxDescrizione_per_riviste; 
    String idNaz;
    String maxRes;
    String s;
    String showNation;
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
		
		//Inizializzo il cookie di sessione 
		SESSIONCOOKIENAME = "GETRIXSID";
		SESSIONCOOKIEDOMAIN = ".getrix.ekbl.net";
		
		//Inizializzo la mappa dei parametri
		mappaDeiParametri =  new Hashtable<String,String>();
	    
		//Inizializzo la lista che conterrà i parametri da inviare
	    postParameters = new ArrayList<NameValuePair>();
	    
	    //Inizializzo la lista che conterrà gli headers
	    requestHeaders = new ArrayList<NameValuePair>();
	
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
        postParameters.add(new BasicNameValuePair("accedi", mappaDeiParametri.get("accedi")));
        postParameters.add(new BasicNameValuePair("backurl", mappaDeiParametri.get("backurl")));
        postParameters.add(new BasicNameValuePair("openTab", mappaDeiParametri.get("openTab")));
        postParameters.add(new BasicNameValuePair("password", mappaDeiParametri.get("password")));
        postParameters.add(new BasicNameValuePair("username", mappaDeiParametri.get("username")));
        try {
        	Object[] response = connessione_1.post("Connessione 1 - POST dei parametri di accesso e recupero del cookie di sessione", URLROOT + "/index.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Trovo il cookie di sessione
        	findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
        	//Leggo la location del redirect
        	location = getHeaderValueByName(responseHeaders, "Location");
        	//Imposto il cookie di sessione per tutte le successive connessioni
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
			connessione_3.get("Connessione 3 - GET dello step 1 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "2" + "&categoria=" + mappaDeiParametri.get("categoria") + "&tipo=" + mappaDeiParametri.get("tipo") + "&callback=refresh", debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 4 - POST dello step 2 di inserimento immobile
    	HttpPortalPostConnection connessione_4 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("tipo", mappaDeiParametri.get("tipo")));
        postParameters.add(new BasicNameValuePair("step", "2"));
        postParameters.add(new BasicNameValuePair("categoria", mappaDeiParametri.get("categoria")));
        postParameters.add(new BasicNameValuePair("fasciaprezzo", mappaDeiParametri.get("fasciaprezzo")));
        postParameters.add(new BasicNameValuePair("gtxNumeroCamereDaLetto", mappaDeiParametri.get("gtxNumeroCamereDaLetto")));
        postParameters.add(new BasicNameValuePair("gtxAnnoCostruzione", mappaDeiParametri.get("gtxAnnoCostruzione")));
        postParameters.add(new BasicNameValuePair("codice", mappaDeiParametri.get("codice")));
        postParameters.add(new BasicNameValuePair("prezzo", mappaDeiParametri.get("prezzo")));
        postParameters.add(new BasicNameValuePair("gtxNumeroSoggiornoSalotto", mappaDeiParametri.get("gtxNumeroSoggiornoSalotto")));
        postParameters.add(new BasicNameValuePair("piano", mappaDeiParametri.get("piano")));
        postParameters.add(new BasicNameValuePair("tipologia", mappaDeiParametri.get("tipologia")));
        postParameters.add(new BasicNameValuePair("sottotipologia", mappaDeiParametri.get("sottotipologia")));
        postParameters.add(new BasicNameValuePair("locali", mappaDeiParametri.get("locali")));
        postParameters.add(new BasicNameValuePair("gtxNumeroAltreCamereStanze", mappaDeiParametri.get("gtxNumeroAltreCamereStanze")));
        postParameters.add(new BasicNameValuePair("numeroPiani", mappaDeiParametri.get("numeroPiani")));
        postParameters.add(new BasicNameValuePair("stato", mappaDeiParametri.get("stato")));
        postParameters.add(new BasicNameValuePair("superficie", mappaDeiParametri.get("superficie")));
        postParameters.add(new BasicNameValuePair("gtxNumeroPostiAuto", mappaDeiParametri.get("gtxNumeroPostiAuto")));
        postParameters.add(new BasicNameValuePair("tipoProprieta", mappaDeiParametri.get("tipoProprieta")));
        postParameters.add(new BasicNameValuePair("tipoMandato", mappaDeiParametri.get("tipoMandato")));
        postParameters.add(new BasicNameValuePair("gtxIdContatto", mappaDeiParametri.get("gtxIdContatto")));
        postParameters.add(new BasicNameValuePair("idCont", mappaDeiParametri.get("idCont")));
        postParameters.add(new BasicNameValuePair("classeImmobile", mappaDeiParametri.get("classeImmobile")));
        postParameters.add(new BasicNameValuePair("terreno_proprieta", mappaDeiParametri.get("terreno_proprieta")));
        postParameters.add(new BasicNameValuePair("gtxScadenzaMandato", mappaDeiParametri.get("gtxScadenzaMandato")));
        postParameters.add(new BasicNameValuePair("spese_condominiali", mappaDeiParametri.get("spese_condominiali")));
        postParameters.add(new BasicNameValuePair("in_asta", mappaDeiParametri.get("in_asta")));
        postParameters.add(new BasicNameValuePair("a_reddito", mappaDeiParametri.get("a_reddito")));
        postParameters.add(new BasicNameValuePair("libero", mappaDeiParametri.get("libero")));
        postParameters.add(new BasicNameValuePair("spese", mappaDeiParametri.get("spese")));
        postParameters.add(new BasicNameValuePair("classe_energetica", mappaDeiParametri.get("classe_energetica")));
        postParameters.add(new BasicNameValuePair("ipe", mappaDeiParametri.get("ipe")));
        postParameters.add(new BasicNameValuePair("ipe_unita_misura", mappaDeiParametri.get("ipe_unita_misura")));
        postParameters.add(new BasicNameValuePair("hidden_ipe", mappaDeiParametri.get("hidden_ipe")));
        postParameters.add(new BasicNameValuePair("hidden_default_mq", mappaDeiParametri.get("hidden_default_mq")));
        postParameters.add(new BasicNameValuePair("hidden_default_mc", mappaDeiParametri.get("hidden_default_mc")));
        postParameters.add(new BasicNameValuePair("costruttore", mappaDeiParametri.get("costruttore")));
        postParameters.add(new BasicNameValuePair("statoCantiere", mappaDeiParametri.get("statoCantiere")));
        postParameters.add(new BasicNameValuePair("riscaldamento", mappaDeiParametri.get("riscaldamento")));
        postParameters.add(new BasicNameValuePair("cucina", mappaDeiParametri.get("cucina")));
        postParameters.add(new BasicNameValuePair("boxauto", mappaDeiParametri.get("boxauto")));
        postParameters.add(new BasicNameValuePair("bagni", mappaDeiParametri.get("bagni")));
        postParameters.add(new BasicNameValuePair("idGiardino", mappaDeiParametri.get("idGiardino")));
        postParameters.add(new BasicNameValuePair("idArredamento", mappaDeiParametri.get("idArredamento")));
        postParameters.add(new BasicNameValuePair("gtxIngresso", mappaDeiParametri.get("gtxIngresso")));
        postParameters.add(new BasicNameValuePair("gtxRipostiglio", mappaDeiParametri.get("gtxRipostiglio")));
        postParameters.add(new BasicNameValuePair("gtxCantina", mappaDeiParametri.get("gtxCantina")));
        postParameters.add(new BasicNameValuePair("gtxMansarda", mappaDeiParametri.get("gtxMansarda")));
        postParameters.add(new BasicNameValuePair("gtxTaverna", mappaDeiParametri.get("gtxTaverna")));
        postParameters.add(new BasicNameValuePair("gtxInfissiEsterni", mappaDeiParametri.get("gtxInfissiEsterni")));
        postParameters.add(new BasicNameValuePair("gtxImpiantoTv", mappaDeiParametri.get("gtxImpiantoTv")));
        postParameters.add(new BasicNameValuePair("gtxCaminetto", mappaDeiParametri.get("gtxCaminetto")));
        postParameters.add(new BasicNameValuePair("gtxPortaBlindata", mappaDeiParametri.get("gtxPortaBlindata")));
        postParameters.add(new BasicNameValuePair("gtxImpiantoAllarme", mappaDeiParametri.get("gtxImpiantoAllarme")));
        postParameters.add(new BasicNameValuePair("gtxPiscina", mappaDeiParametri.get("gtxPiscina")));
        postParameters.add(new BasicNameValuePair("gtxCampoTennis", mappaDeiParametri.get("gtxCampoTennis")));
        postParameters.add(new BasicNameValuePair("flag_auto_it", mappaDeiParametri.get("flag_auto_it")));
        postParameters.add(new BasicNameValuePair("descrizione_it", mappaDeiParametri.get("descrizione_it")));
        postParameters.add(new BasicNameValuePair("flag_auto_en", mappaDeiParametri.get("flag_auto_en")));
        postParameters.add(new BasicNameValuePair("descrizione_en", mappaDeiParametri.get("descrizione_en")));
        postParameters.add(new BasicNameValuePair("flag_auto_de", mappaDeiParametri.get("flag_auto_de")));
        postParameters.add(new BasicNameValuePair("descrizione_de", mappaDeiParametri.get("descrizione_de")));
        postParameters.add(new BasicNameValuePair("flag_auto_fr", mappaDeiParametri.get("flag_auto_fr")));
        postParameters.add(new BasicNameValuePair("descrizione_fr", mappaDeiParametri.get("descrizione_fr")));
        postParameters.add(new BasicNameValuePair("flag_auto_es", mappaDeiParametri.get("flag_auto_es")));
        postParameters.add(new BasicNameValuePair("descrizione_es", mappaDeiParametri.get("descrizione_es")));
        postParameters.add(new BasicNameValuePair("flag_auto_pt", mappaDeiParametri.get("flag_auto_pt")));
        postParameters.add(new BasicNameValuePair("descrizione_pt", mappaDeiParametri.get("descrizione_pt")));
        postParameters.add(new BasicNameValuePair("flag_auto_ru", mappaDeiParametri.get("flag_auto_ru")));
        postParameters.add(new BasicNameValuePair("descrizione_ru", mappaDeiParametri.get("descrizione_ru")));
        postParameters.add(new BasicNameValuePair("flag_auto_gr", mappaDeiParametri.get("flag_auto_gr")));
        postParameters.add(new BasicNameValuePair("descrizione_gr", mappaDeiParametri.get("descrizione_gr")));
        postParameters.add(new BasicNameValuePair("remLen_it", mappaDeiParametri.get("remLen_it")));
        postParameters.add(new BasicNameValuePair("remLen_en", mappaDeiParametri.get("remLen_en")));
        postParameters.add(new BasicNameValuePair("remLen_de", mappaDeiParametri.get("remLen_de")));
        postParameters.add(new BasicNameValuePair("remLen_fr", mappaDeiParametri.get("remLen_fr")));
        postParameters.add(new BasicNameValuePair("remLen_es", mappaDeiParametri.get("remLen_es")));
        postParameters.add(new BasicNameValuePair("remLen_pt", mappaDeiParametri.get("remLen_pt")));
        postParameters.add(new BasicNameValuePair("remLen_ru", mappaDeiParametri.get("remLen_ru")));
        postParameters.add(new BasicNameValuePair("remLen_gr", mappaDeiParametri.get("remLen_gr")));
        postParameters.add(new BasicNameValuePair("gtxTitolo_per_riviste", mappaDeiParametri.get("gtxTitolo_per_riviste")));
        postParameters.add(new BasicNameValuePair("gtxDescrizione_per_riviste", mappaDeiParametri.get("gtxDescrizione_per_rivist...")));
        postParameters.add(new BasicNameValuePair("callback", mappaDeiParametri.get("callback")));   
        
        //Rimuovo i parametri che non devono essere inviati
        postParameters.retainAll(removeNotUsedParams(postParameters));
       
        try {
        	Object[] response = connessione_4.post("Connessione 4 - POST dello step 2 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=" + "2" + "&categoria=" + mappaDeiParametri.get("categoria") + "&tipo=" + mappaDeiParametri.get("tipo") + "&callback=refresh", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Ottengo il valore dell'idAnnuncio
        	location = getHeaderValueByName(responseHeaders, "Location");
        	int start = location.indexOf("idAnnuncio=") + 11;
	        int end = location.length();
	        codiceInserzione = location.substring(start, end);
	        mappaDeiParametri.put("idAnnuncio", codiceInserzione);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 5 - GET dello step 3 di inserimento immobile
    	HttpPortalGetConnection connessione_5 = new HttpPortalGetConnection();
    	try {
			connessione_5.get("Connessione 5 - GET dello step 3 di inserimento immobile", URLROOT + location, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//POST di servizio per recupero codici Comune, Provincia e Regione
    	HttpPortalPostConnection connessione_a = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();          
        postParameters.add(new BasicNameValuePair("idNaz", mappaDeiParametri.get("idNaz")));
        postParameters.add(new BasicNameValuePair("maxRes", mappaDeiParametri.get("maxRes")));
        postParameters.add(new BasicNameValuePair("s", mappaDeiParametri.get("s")));
        postParameters.add(new BasicNameValuePair("showNation", mappaDeiParametri.get("showNation")));
        
        requestHeaders = new ArrayList<NameValuePair>();          
        requestHeaders.add(new BasicNameValuePair("Referer", URLROOT + location));
        
        try {
        	Object[] response = connessione_a.post("Connessione a - POST di servizio per recupero codici Comune, Provincia e Regione", URLROOT + "/comune_suggestion.php", postParameters, requestHeaders, debugMode);
        	String responseBody = (String)response[1];
        	JSONObject json = new JSONObject(responseBody);
        	JSONArray jsonResults = json.getJSONArray("results");        	
	        
	        double resultComparation = 0;	        
	        for(int i=0; i<jsonResults.length(); i++) {
	        	JSONObject currentJson = jsonResults.getJSONObject(i);
	        	
	        	List<char[]> charListPortale = bigram(currentJson.getString("comune_nome"));
        		List<char[]> charListImagination = bigram(scheda.comune);
        		
        		double actualResultComparation = dice(charListPortale, charListImagination);
        		if(actualResultComparation>=resultComparation) {
        			resultComparation = actualResultComparation;
        			
        			//Ottengo qui i valori di alcuni dei parametri che utilizzerò nelle connessioni successive
        			idComune = currentJson.getString("comune_idComune");
        			mappaDeiParametri.put("idComune", idComune);
        			
        			idNazione = currentJson.getString("nazione_idNazione");
        			mappaDeiParametri.put("idNazione", idNazione);
        			
        			idProvincia = currentJson.getString("provincia_idProvincia");
        			mappaDeiParametri.put("idProvincia", idProvincia);
        			
        			idRegione = currentJson.getString("regione_idRegione");
        			mappaDeiParametri.put("idRegione", idRegione);
        			
        			textComune = currentJson.getString("comune_nome");
        			mappaDeiParametri.put("textComune", textComune);
        			
        			textNazione = currentJson.getString("nazione_nome");
        			mappaDeiParametri.put("textNazione", textNazione);
        			
        			textProvincia = currentJson.getString("provincia_nome");
        			mappaDeiParametri.put("textProvincia", textProvincia);
        			
        			textRegione = currentJson.getString("regione_nome");
        			mappaDeiParametri.put("textRegione", textRegione);
        			
        			com_latitudine = currentJson.getString("comune_latitudine");
        			mappaDeiParametri.put("com_latitudine", com_latitudine);
        			
        			com_longitudine = currentJson.getString("comune_longitudine"); 
        			mappaDeiParametri.put("com_longitudine", com_longitudine);
        			
        		}       		
        		//System.out.println("Risultato comparazione: " + resultComparation);        		        	
	        }
	        
        } catch (IOException | RuntimeException | ParseException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //POST di servizio per recupero Macroregioe e Microregione (e eventualmente zonaobbligatoria)
    	HttpPortalPostConnection connessione_b = new HttpPortalPostConnection();   	
        
        requestHeaders = new ArrayList<NameValuePair>();          
        requestHeaders.add(new BasicNameValuePair("Referer", URLROOT + location));
        
        try {
        	Object[] response = connessione_b.post("Connessione b - POST di servizio per recupero Macroregioe e Microregione (e eventualmente zonaobbligatoria)", URLROOT + "/getZonaFromCoords.php?lat=" + mappaDeiParametri.get("latitudine") + "&lon=" + mappaDeiParametri.get("longitudine") + "&idComune=" + mappaDeiParametri.get("idComune"), postParameters, requestHeaders, debugMode);
        	String responseBody = (String)response[1];
        	
        	//Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);
            Elements id_macrozonaElements = doc.getElementsByTag("id_macrozona");
            if(!id_macrozonaElements.isEmpty()) {
            	Element id_macrozonaElement = id_macrozonaElements.first();
            	idMacrozona = id_macrozonaElement.text();
            	mappaDeiParametri.put("idMacrozona", idMacrozona);          	
            	
            	Elements microzonaElements = doc.getElementsByTag("microzona");
            	Element microzonaElement = microzonaElements.first();         	
            	Element microzonaIdElement = microzonaElement.child(0);
            	idMicrozona = microzonaIdElement.text();
            	mappaDeiParametri.put("idMicrozona", idMicrozona);
            	
            	zonaobbligatoria = "1";
            	mappaDeiParametri.put("zonaobbligatoria", zonaobbligatoria);
            	
            }
            else {
            	idMacrozona = "";
            	mappaDeiParametri.put("idMacrozona", idMacrozona); 
            	
            	idMicrozona = "";
            	mappaDeiParametri.put("idMicrozona", idMicrozona);
            	
            	zonaobbligatoria = dontSendThisParam;
            	mappaDeiParametri.put("zonaobbligatoria", zonaobbligatoria);
            }
        
        } catch (IOException | RuntimeException e) {
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
	    	        reqEntity.addPart("tipo", new StringBody(mappaDeiParametri.get("tipo")));
	    	        reqEntity.addPart("idAnnuncio", new StringBody(mappaDeiParametri.get("idAnnuncio")));
	    	        reqEntity.addPart("immagine", bin );
	    	        
	    	        connessione_6.post("Connessione 6_" + i + " - inserimento immagine " + i, URLROOT_2 + "/inserimento_immagini.php", reqEntity, debugMode);
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
    	postParameters.add(new BasicNameValuePair("_textComune", mappaDeiParametri.get("_textComune")));
    	postParameters.add(new BasicNameValuePair("action", "save"));
    	postParameters.add(new BasicNameValuePair("cap", mappaDeiParametri.get("cap")));
    	postParameters.add(new BasicNameValuePair("com_latitudine", mappaDeiParametri.get("com_latitudine")));
    	postParameters.add(new BasicNameValuePair("com_longitudine", mappaDeiParametri.get("com_longitudine")));
    	postParameters.add(new BasicNameValuePair("confirm_address", mappaDeiParametri.get("confirm_address")));
    	postParameters.add(new BasicNameValuePair("flagIndirizzo", mappaDeiParametri.get("flagIndirizzo")));
    	postParameters.add(new BasicNameValuePair("flagIndirizzo_fake", mappaDeiParametri.get("flagIndirizzo_fake")));
    	postParameters.add(new BasicNameValuePair("idAnnuncio", mappaDeiParametri.get("idAnnuncio")));
    	postParameters.add(new BasicNameValuePair("idComune", mappaDeiParametri.get("idComune")));
    	postParameters.add(new BasicNameValuePair("idLocalita", mappaDeiParametri.get("idLocalita")));
    	postParameters.add(new BasicNameValuePair("idMacrozona", mappaDeiParametri.get("idMacrozona")));
    	postParameters.add(new BasicNameValuePair("idMicrozona", mappaDeiParametri.get("idMicrozona")));
    	postParameters.add(new BasicNameValuePair("idNazione", mappaDeiParametri.get("idNazione")));
    	postParameters.add(new BasicNameValuePair("idProvincia", mappaDeiParametri.get("idProvincia")));
    	postParameters.add(new BasicNameValuePair("idRegione", mappaDeiParametri.get("idRegione")));
    	postParameters.add(new BasicNameValuePair("indirizzo", mappaDeiParametri.get("indirizzo")));
    	postParameters.add(new BasicNameValuePair("indirizzo_fake", mappaDeiParametri.get("indirizzo_fake")));
    	postParameters.add(new BasicNameValuePair("indirizzo_fake_orig", mappaDeiParametri.get("indirizzo_fake_orig")));
    	postParameters.add(new BasicNameValuePair("latitudine", mappaDeiParametri.get("latitudine")));
    	postParameters.add(new BasicNameValuePair("longitudine", mappaDeiParametri.get("longitudine")));
    	postParameters.add(new BasicNameValuePair("nextStep", mappaDeiParametri.get("nextStep")));
    	postParameters.add(new BasicNameValuePair("num_localita", mappaDeiParametri.get("num_localita")));
    	postParameters.add(new BasicNameValuePair("numeroCivico", mappaDeiParametri.get("numeroCivico")));
    	postParameters.add(new BasicNameValuePair("planimetria", mappaDeiParametri.get("planimetria")));
    	postParameters.add(new BasicNameValuePair("step", "3"));
    	postParameters.add(new BasicNameValuePair("textComune", mappaDeiParametri.get("textComune")));
    	postParameters.add(new BasicNameValuePair("textNazione", mappaDeiParametri.get("textNazione")));
    	postParameters.add(new BasicNameValuePair("textProvincia", mappaDeiParametri.get("textProvincia")));
    	postParameters.add(new BasicNameValuePair("textRegione", mappaDeiParametri.get("textRegione")));
    	postParameters.add(new BasicNameValuePair("tipo", mappaDeiParametri.get("tipo")));
    	postParameters.add(new BasicNameValuePair("virtual_tour", mappaDeiParametri.get("virtual_tour")));
    	postParameters.add(new BasicNameValuePair("zonaobbligatoria", mappaDeiParametri.get("zonaobbligatoria")));
    	postParameters.add(new BasicNameValuePair("zoom", mappaDeiParametri.get("zoom")));
        
        //Rimuovo i parametri che non devono essere inviati
    	postParameters.retainAll(removeNotUsedParams(postParameters));
        
        try {
        	Object[] response = connessione_7.post("Connessione 7 - POST dello step 3 di inserimento immobile", URLROOT + location, postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Leggo la location del redirect
        	location = getHeaderValueByName(responseHeaders, "Location");
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 8 - GET dello step 4 di inserimento immobile
    	HttpPortalGetConnection connessione_8 = new HttpPortalGetConnection();
    	try {
			connessione_8.get("Connessione 8 - GET dello step 4 di inserimento immobile", URLROOT + "/" + location, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
    	
    	//Connessione 9 - POST dello step 4 di inserimento immobile
    	HttpPortalPostConnection connessione_9 = new HttpPortalPostConnection();   	
    	postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("SelTipoPagamento", mappaDeiParametri.get("SelTipoPagamento")));
    	postParameters.add(new BasicNameValuePair("address1", mappaDeiParametri.get("address1")));
    	postParameters.add(new BasicNameValuePair("amount", mappaDeiParametri.get("amount")));
    	postParameters.add(new BasicNameValuePair("azionePayPal", mappaDeiParametri.get("azionePayPal")));
    	postParameters.add(new BasicNameValuePair("bn", mappaDeiParametri.get("bn")));
    	postParameters.add(new BasicNameValuePair("business", mappaDeiParametri.get("business")));
    	postParameters.add(new BasicNameValuePair("check_1", mappaDeiParametri.get("check_1")));
    	postParameters.add(new BasicNameValuePair("check_108", mappaDeiParametri.get("check_108")));
    	postParameters.add(new BasicNameValuePair("cmd", mappaDeiParametri.get("cmd")));
    	postParameters.add(new BasicNameValuePair("currency_code", mappaDeiParametri.get("currency_code")));
    	postParameters.add(new BasicNameValuePair("first_name", mappaDeiParametri.get("first_name")));
    	postParameters.add(new BasicNameValuePair("idAnnuncio", mappaDeiParametri.get("idAnnuncio")));
    	postParameters.add(new BasicNameValuePair("item_name", mappaDeiParametri.get("item_name")));
    	postParameters.add(new BasicNameValuePair("item_number", mappaDeiParametri.get("item_number")));
    	postParameters.add(new BasicNameValuePair("last_name", mappaDeiParametri.get("last_name")));
    	postParameters.add(new BasicNameValuePair("lc", mappaDeiParametri.get("lc")));
    	postParameters.add(new BasicNameValuePair("no_note", mappaDeiParametri.get("no_note")));
    	postParameters.add(new BasicNameValuePair("no_shipping", mappaDeiParametri.get("no_shipping")));
    	postParameters.add(new BasicNameValuePair("notify_url", mappaDeiParametri.get("notify_url")));
    	postParameters.add(new BasicNameValuePair("prezzoTotale", mappaDeiParametri.get("prezzoTotale")));
    	postParameters.add(new BasicNameValuePair("_return", mappaDeiParametri.get("_return")));
    	postParameters.add(new BasicNameValuePair("return_cancel", mappaDeiParametri.get("return_cancel")));
    	postParameters.add(new BasicNameValuePair("richiesta", mappaDeiParametri.get("richiesta")));
    	postParameters.add(new BasicNameValuePair("step", "4"));
    	postParameters.add(new BasicNameValuePair("tipo", mappaDeiParametri.get("tipo")));
    	postParameters.add(new BasicNameValuePair("zip", mappaDeiParametri.get("zip")));
        
        //Rimuovo i parametri che non devono essere inviati
    	postParameters.retainAll(removeNotUsedParams(postParameters)); 	
        
        try {
        	Object[] response = connessione_9.post("Connessione 9 - POST dello step 4 di inserimento immobile", URLROOT + "/inserimento_annuncio.php?step=4&idAnnuncio=" + mappaDeiParametri.get("idAnnuncio") + "&tipo=" + mappaDeiParametri.get("tipo") + "&azionePayPal=show", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Leggo la location del redirect
        	location = getHeaderValueByName(responseHeaders, "Location");
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 10 - GET dello step 5 di inserimento immobile
    	HttpPortalGetConnection connessione_10 = new HttpPortalGetConnection();
    	try {
    		Object[] response = connessione_10.get("Connessione 10 - GET dello step 5 di inserimento immobile", URLROOT + "/" + location, debugMode);
			String responseBody = (String)response[1];
        	
            if(responseBody.contains("Immobile inserito correttamente")) {
            	inserimentoOK = true;            	
            }
            else {
            	throw(new HttpWrongResponseBodyException("Non è stato possibile verificare l'inserimento, verificarlo manualmente"));
            }
    	} catch (IOException | RuntimeException | HttpWrongResponseBodyException e) {
			throw new HttpCommunicationException(e);
		}
		      
    	
    	//Verifico il successo dell'inserimento, aggiorno strutture dati e pannelli, comunico l'esito all'utente
    	if(inserimentoOK) {
    		
    		//Aggiorna la lista dei portali in cui è inserita la scheda
    		scheda.aggiungiInserimentoPortale(idPortale, codiceInserzione);
    		      	
    		if(!isSequential) {   			
    			System.out.println("Inserita in: " + NOMEPORTALE);       		
        		
    			//Aggiorna i pulsanti del pannello inserimento
    			j2web_GUI.panelInserimentoImmobiliInPortali.updatePanello(scheda, false);
    			
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
	public boolean visualizzaScheda(SchedaImmobile scheda) {
		System.out.println("Visualizzazione scheda: " + scheda.codiceInserzione + "...");
		//Apro il browser e inserisco credenziali		
		try {
			String url = URLROOT + "/annunci_agenzia.php?stato=attivo";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			System.out.println("Visualizzata in: " + NOMEPORTALE);
			
		} catch (IOException e ) {
			//Eventualità non gestita
		}

		//CONNESSIONI DI TEST
		/*ArrayList<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("User-Agent", "USER_AGENT"));
		headers.add(new BasicNameValuePair("Connection", "keep-alive"));
		headers.add(new BasicNameValuePair("Referer", "http://getrix.ekbl.net/inserimento_annuncio.php?step=3&tipo=1&idAnnuncio=42590053"));
		String[] cookie =  {"GETRIXSID", "a715d783cc6e20ae324503f96e9ba941", ".getrix.ekbl.net"};
		HttpPortalConnection connessione_test = new HttpPortalConnection();			
		try {
			Object[] response = connessione_test.testConnection("GET", "http://getrix.ekbl.net/getZonaFromCoords.php?lat=44.4870246&lon=11.365381800000023&idComune=5890", headers, null, cookie);
			String responseBody = (String)response[1];
			
			//Parse HMTL to retrieve some informations
            org.jsoup.nodes.Document doc = Jsoup.parse(responseBody);
            Elements id_macrozonaElements = doc.getElementsByTag("id_macrozona");
            if(id_macrozonaElements!=null) {
            	Element id_macrozonaElement = id_macrozonaElements.first();
            	idMacrozona = id_macrozonaElement.text();
            	mappaDeiParametri.put("idMacrozona", idMacrozona);  
            	System.out.println("idMacrozona " + idMacrozona);
            	
            	Elements microzonaElements = doc.getElementsByTag("microzona");
            	Element microzonaElement = microzonaElements.first();         	
            	Element microzonaIdElement = microzonaElement.child(0);
            	idMicrozona = microzonaIdElement.text();
            	mappaDeiParametri.put("idMicrozona", idMicrozona);
            	System.out.println("idMicrozona " + idMicrozona);
            	
            	zonaobbligatoria = "1";
            	mappaDeiParametri.put("zonaobbligatoria", zonaobbligatoria);
            	
            }
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		/*ArrayList<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0"));
		headers.add(new BasicNameValuePair("Connection", "keep-alive"));
		headers.add(new BasicNameValuePair("Referer", "	http://getrix.ekbl.net/inserimento_annuncio.php?step=3&tipo=1&idAnnuncio=42587299"));
		
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();          
		params.add(new BasicNameValuePair("idNaz", "IT"));
		params.add(new BasicNameValuePair("maxRes", "10"));
		params.add(new BasicNameValuePair("s", "Bologna"));
		params.add(new BasicNameValuePair("showNation", "0"));
		
		String[] cookie =  {"GETRIXSID", "257fbd878b4c0bc1f1d912c8f705819f", ".getrix.ekbl.net"};
		
		HttpPortalConnection connessione_test = new HttpPortalConnection();	
		try {
			connessione_test.testConnection("URLENCODED_POST", "http://getrix.ekbl.net/comune_suggestion.php", headers, params, cookie);
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		return true;
	}

	
	//Metodo per l'eliminazione della scheda immobile nel portale immobiliare
	public boolean cancellaScheda(SchedaImmobile scheda, boolean isSequential) throws HttpCommunicationException {		
		System.out.println("Eliminazione scheda: " + scheda.codiceInserzione + "...");
		
		codiceInserzione = scheda.getCodiceInserimento(idPortale);			
		
		
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
        postParameters.add(new BasicNameValuePair("backurl", "annunci_agenzia.php?stato=attivo"));
        postParameters.add(new BasicNameValuePair("openTab", "accedi"));
        postParameters.add(new BasicNameValuePair("password", PASSWORD));
        postParameters.add(new BasicNameValuePair("username", USERNAME));
        try {
        	Object[] response = connessione_1.post("Connessione 1 - POST dei parametri di accesso e recupero del cookie di sessione", URLROOT + "/index.php", postParameters, debugMode);
        	Header[] responseHeaders = (Header[])response[0];
        	//Trovo il cookie di sessione
        	findSessionCookie(responseHeaders, SESSIONCOOKIENAME, SESSIONCOOKIEDOMAIN);
        	//Leggo la location del redirect
        	location = getHeaderValueByName(responseHeaders, "Location");
        	//Imposto il cookie di sessione per tutte le successive connessioni
        	connessione_1.setSessionCookie(SESSIONCOOKIEHEADER, SESSIONCOOKIENAME, SESSIONCOOKIEVALUE, SESSIONCOOKIEDOMAIN);
        } catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	finally {
    		postParameters.clear();
    	}
        
        
        //Connessione 2 - GET della pagina Annunci agenzia per l'eliminazione dell'annuncio
    	HttpPortalGetConnection connessione_2 = new HttpPortalGetConnection();
    	try {
			connessione_2.get("Connessione 2 - GET della pagina Annunci agenzia per l'eliminazione dell'annuncio", URLROOT + "/annunci_agenzia.php?multimedia=no&action=delete&stato=attivo&newStatus=&pag=1&selectedItems=&idAnnuncio=&numAnnunci=20&selectAll=true&idAnnuncio%5B%5D=" + codiceInserzione, debugMode);
    	} catch (IOException | RuntimeException e) {
			throw new HttpCommunicationException(e);
		}
    	
        
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
		
		accedi = "accedi";
		mappaDeiParametri.put("accedi", accedi);
		
		
	    backurl = "/home_gestionale.php";
	    mappaDeiParametri.put("backurl", backurl);
	    
	    
	    openTab =  "";
	    mappaDeiParametri.put("openTab", openTab);
	    
	    
	    password = PASSWORD;
	    mappaDeiParametri.put("password", password);
	    
	    
	    username = USERNAME;
	    mappaDeiParametri.put("username", username);
	    
		
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
		mappaDeiParametri.put("categoria", categoria);
		
		
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
		mappaDeiParametri.put("tipo", tipo);
		
		
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
		mappaDeiParametri.put("fasciaprezzo", fasciaprezzo);
		
				
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
		mappaDeiParametri.put("gtxNumeroCamereDaLetto", gtxNumeroCamereDaLetto);
		
		
		gtxAnnoCostruzione = scheda.annoCostruzione;
		mappaDeiParametri.put("gtxAnnoCostruzione", gtxAnnoCostruzione);
		
		
		codice = scheda.codiceInserzione;
		mappaDeiParametri.put("codice", codice);
				
		
		prezzo = scheda.prezzoImmobile;
		mappaDeiParametri.put("prezzo", prezzo);
		
		
		gtxNumeroSoggiornoSalotto = "";
		mappaDeiParametri.put("gtxNumeroSoggiornoSalotto", gtxNumeroSoggiornoSalotto);
		
				
		switch (scheda.piano) {
		case "Interrato":
			piano = "1";
			break;
		case "Seminterrato":
			piano = "2";
			break;
		case "Piano terra":
			piano = "3";
			break;
		case "1":
			piano = "4";
			break;
		case "2":
			piano = "5";
			break;
		case "3":
			piano = "6";
			break;
		case "4":
			piano = "7";
			break;
		case "5":
			piano = "8";
			break;
		case "6":
			piano = "9";
			break;
		case "7":
			piano = "10";
			break;
		case "8":
			piano = "11";
			break;
		case "9":
			piano = "12";
			break;
		case "10":
			piano = "13";
			break;
		case ">10":
			piano = "14";
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
		mappaDeiParametri.put("piano", piano);
		
			
		sottotipologia = dontSendThisParam;
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
			sottotipologia = "75";
			break;
		case "Albergo":
			tipologia = "63";
			sottotipologia = "101";
			break;
		case "Bar":
			tipologia = "63";
			sottotipologia = "103";
			break;
		case "Negozio":
			tipologia = "63";
			sottotipologia = "79";
			break;
		case "Ristorante":
			tipologia = "63";
			sottotipologia = "101";
			break;
		case "Ufficio":
			tipologia = "63";
			sottotipologia = "81";
			break;
		case "Capannone":
			tipologia = "63";
			sottotipologia = "73";
			break;
		case "Laboratorio":
			tipologia = "63";
			sottotipologia = "99";
			break;
		case "Magazzino":
			tipologia = "63";
			sottotipologia = "91";
			break;
		case "Terreno residenziale":
			tipologia = "28";
			sottotipologia = "67";
			break;
		case "Terreno agricolo":
			tipologia = "28";
			sottotipologia = "71";
			break;
		case "Terreno industriale":
			tipologia = "28";
			sottotipologia = "70";
			break;
		default:
			tipologia = "";
			sottotipologia = dontSendThisParam;
		}
		mappaDeiParametri.put("tipologia", tipologia);
		mappaDeiParametri.put("sottotipologia", sottotipologia);
		
					
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
		case ">5":
			locali = ">5 ";
			break;
		default:
			locali = "";
		}
		mappaDeiParametri.put("locali", locali);
		
		
		gtxNumeroAltreCamereStanze = "";
		mappaDeiParametri.put("gtxNumeroAltreCamereStanze", gtxNumeroAltreCamereStanze);

		
		numeroPiani = scheda.numeroTotalePiani;
		mappaDeiParametri.put("numeroPiani", numeroPiani);
		
				
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
		mappaDeiParametri.put("stato", stato);
		
		 		
		superficie = scheda.superficieImmobile;
		mappaDeiParametri.put("superficie", superficie);
				
		
		gtxNumeroPostiAuto = "";
		mappaDeiParametri.put("gtxNumeroPostiAuto", gtxNumeroPostiAuto);
		
		
		tipoProprieta = "";
		mappaDeiParametri.put("tipoProprieta", tipoProprieta);
		
		
		tipoMandato = "";
		mappaDeiParametri.put("tipoMandato", tipoMandato);
				
		
		gtxIdContatto = "";
		mappaDeiParametri.put("gtxIdContatto", gtxIdContatto);
		
			
		idCont = "";
		mappaDeiParametri.put("idCont", idCont);
		
			
		classeImmobile = "";
		mappaDeiParametri.put("classeImmobile", classeImmobile);
		
		
		terreno_proprieta = "";
		mappaDeiParametri.put("terreno_proprieta", terreno_proprieta);
		
		
		gtxScadenzaMandato = "";
		mappaDeiParametri.put("gtxScadenzaMandato", gtxScadenzaMandato);
		
		
		spese_condominiali = "";
		mappaDeiParametri.put("spese_condominiali", spese_condominiali);
		
		in_asta = dontSendThisParam;
		mappaDeiParametri.put("in_asta", in_asta);
		
		
		a_reddito = dontSendThisParam;
		mappaDeiParametri.put("a_reddito", a_reddito);
		
		libero = dontSendThisParam;
		mappaDeiParametri.put("libero", libero);
		
				
		spese = "";
		mappaDeiParametri.put("spese", spese);
			
		
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
		mappaDeiParametri.put("classe_energetica", classe_energetica);
		
		
		ipe = "≥ 175";
		mappaDeiParametri.put("ipe", ipe);
		
		
		ipe_unita_misura = "m2";
		mappaDeiParametri.put("ipe_unita_misura", ipe_unita_misura);
		
		
		hidden_ipe = "-1";
		mappaDeiParametri.put("hidden_ipe", hidden_ipe);
		
		
		hidden_default_mq = "≥ 175";
		mappaDeiParametri.put("hidden_default_mq", hidden_default_mq);
		
		
		hidden_default_mc = "≥ 175";
		mappaDeiParametri.put("hidden_default_mc", hidden_default_mc);
		
		
		costruttore = "";
		mappaDeiParametri.put("costruttore", costruttore);
		
		
		statoCantiere = "";
		mappaDeiParametri.put("statoCantiere", statoCantiere);
		
				
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
			riscaldamento = dontSendThisParam;
		}
		mappaDeiParametri.put("riscaldamento", riscaldamento);
		
			
		switch (scheda.tipologiaCucina) {
		case "Abitabile":
			cucina = "1";
			break;
		case "Angolo cottura":
			cucina = "2";
			break;
		case "Semi abitabile":
			cucina = "4";
			break;
		default:
			cucina = dontSendThisParam;
		}
		mappaDeiParametri.put("cucina", cucina);
		
		
		switch (scheda.parcheggio) {
		case "Nessuno":
			boxauto = "2";
			break;
		case "Box auto singolo":
			boxauto = "1";
			break;
		case "Box auto doppio":
			boxauto = "3";
			break;
		case "Posto auto scoperto":
			boxauto = "4";
			break;
		default:
			boxauto = dontSendThisParam;
		}
		mappaDeiParametri.put("boxauto", boxauto);
		
		
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
			bagni = dontSendThisParam;
		}
		mappaDeiParametri.put("bagni", bagni);
		
				
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
			idGiardino = dontSendThisParam;
		}
		mappaDeiParametri.put("idGiardino", idGiardino);
		
				
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
			idArredamento = dontSendThisParam;
		}
		mappaDeiParametri.put("idArredamento", idArredamento);
		
		
		gtxIngresso = "0";
		mappaDeiParametri.put("gtxIngresso", gtxIngresso);
		
		
		gtxRipostiglio = "0";
		mappaDeiParametri.put("gtxRipostiglio", gtxRipostiglio);
		
		
		gtxCantina = scheda.cantina?"1":"0";
		mappaDeiParametri.put("gtxCantina", gtxCantina);
		
		
		gtxMansarda = "0";
		mappaDeiParametri.put("gtxMansarda", gtxMansarda);
		
		
		gtxTaverna = "0";
		mappaDeiParametri.put("gtxTaverna", gtxTaverna);
		
		
		gtxInfissiEsterni = "0";
		mappaDeiParametri.put("gtxInfissiEsterni", gtxInfissiEsterni);
		
		
		gtxImpiantoTv = "0";
		mappaDeiParametri.put("gtxImpiantoTv", gtxImpiantoTv);
		
		
		gtxFibraOttica = scheda.bandaLarga?"on":dontSendThisParam;
		mappaDeiParametri.put("gtxFibraOttica", gtxFibraOttica);
		
		
		ascensore = scheda.ascensore?"on":dontSendThisParam;
		mappaDeiParametri.put("ascensore", ascensore);		
				
		
		gtxImpiantoAllarme = scheda.sistemaDiAllarme?"on":dontSendThisParam;
		mappaDeiParametri.put("gtxImpiantoAllarme", gtxImpiantoAllarme);
		
				
		gtxCancelloElettrico = scheda.cancelloElettrico?"on":dontSendThisParam;
		mappaDeiParametri.put("gtxCancelloElettrico", gtxCancelloElettrico);
				
		
		flag_auto_it = "";
		mappaDeiParametri.put("flag_auto_it", flag_auto_it);
		
		
		descrizione_it = scheda.testoAnnuncio;
		mappaDeiParametri.put("descrizione_it", descrizione_it);
		
		
		flag_auto_en = "";
		mappaDeiParametri.put("flag_auto_en", flag_auto_en);
		
		
		descrizione_en = "";
		mappaDeiParametri.put("descrizione_en", descrizione_en);
		
		
		flag_auto_de = "";
		mappaDeiParametri.put("flag_auto_de", flag_auto_de);
		
		
		descrizione_de = "";
		mappaDeiParametri.put("descrizione_de", descrizione_de);
		
		
		flag_auto_fr = "";
		mappaDeiParametri.put("flag_auto_fr", flag_auto_fr);
		
		
		descrizione_fr = "";
		mappaDeiParametri.put("descrizione_fr", descrizione_fr);
		
		
		flag_auto_es = "";
		mappaDeiParametri.put("flag_auto_es", flag_auto_es);
		
		
		descrizione_es = "";
		mappaDeiParametri.put("descrizione_es", descrizione_es);
		
		
		flag_auto_pt = "";
		mappaDeiParametri.put("flag_auto_pt", flag_auto_pt);
		
		
		descrizione_pt = "";
		mappaDeiParametri.put("descrizione_pt", descrizione_pt);
		
		
		flag_auto_ru = "";
		mappaDeiParametri.put("flag_auto_ru", flag_auto_ru);
		
		
		descrizione_ru = "";
		mappaDeiParametri.put("descrizione_ru", descrizione_ru);
		
		
		flag_auto_gr = "";
		mappaDeiParametri.put("flag_auto_gr", flag_auto_gr);
		
		
		descrizione_gr = "";
		mappaDeiParametri.put("descrizione_gr", descrizione_gr);
		
		
		int lunghezzaDescrizione = scheda.testoAnnuncio.length();
		remLen_it = Integer.toString(3000 - lunghezzaDescrizione);
		mappaDeiParametri.put("remLen_it", remLen_it);
		
				
		remLen_en = "3000";
		mappaDeiParametri.put("remLen_en", remLen_en);
		
		
		remLen_de = "3000";
		mappaDeiParametri.put("remLen_de", remLen_de);
		
		
		remLen_fr = "3000";
		mappaDeiParametri.put("remLen_fr", remLen_fr);
		
		
		remLen_es = "3000";
		mappaDeiParametri.put("remLen_es", remLen_es);
		
		
		remLen_pt = "3000";
		mappaDeiParametri.put("remLen_pt", remLen_pt);
		
		
		remLen_ru = "3000";
		mappaDeiParametri.put("remLen_ru", remLen_ru);
		
		
		remLen_gr = "3000";
		mappaDeiParametri.put("remLen_gr", remLen_gr);
			
		
		gtxTitolo_per_riviste = "";
		mappaDeiParametri.put("gtxTitolo_per_riviste", gtxTitolo_per_riviste);
		
		
		gtxDescrizione_per_riviste = "";
		mappaDeiParametri.put("gtxDescrizione_per_riviste", gtxDescrizione_per_riviste);
		
		
		callback = "submit";
		mappaDeiParametri.put("callback", callback);
		
		
		idNaz = "IT";
		mappaDeiParametri.put("idNaz", idNaz);
		
		
		maxRes = "10";
		mappaDeiParametri.put("maxRes", maxRes);
		
		
		s = scheda.comune;
		mappaDeiParametri.put("s", s);
		
		
		showNation = "0";
		mappaDeiParametri.put("showNation", showNation);
		
		
		_textComune = scheda.comune;
		mappaDeiParametri.put("_textComune", _textComune);
		
				
		cap = scheda.cap;
		mappaDeiParametri.put("cap", cap);
		
				
		Map<String, String> latLon;
		try {
			latLon = getLatLonCoord(scheda.indirizzoLocalita, scheda.comune, scheda.provincia, scheda.regione);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new HttpCommunicationException(e);
		}
		
		latitudine = latLon.get("latitudine");
		mappaDeiParametri.put("latitudine", latitudine);
		
		longitudine = latLon.get("longitudine");
		mappaDeiParametri.put("longitudine", longitudine);
		
		
		confirm_address = "1";
		mappaDeiParametri.put("confirm_address", confirm_address);
		
		
		flagIndirizzo = "2";
		mappaDeiParametri.put("flagIndirizzo", flagIndirizzo);
		
				
		flagIndirizzo_fake = "2";
		mappaDeiParametri.put("flagIndirizzo_fake", flagIndirizzo_fake);
		
    
        idLocalita = "";
		mappaDeiParametri.put("idLocalita", idLocalita);
       
		
		indirizzo = scheda.indirizzoLocalita;
		mappaDeiParametri.put("indirizzo", indirizzo);
		
		
		indirizzo_fake = scheda.indirizzoLocalita;
		mappaDeiParametri.put("indirizzo_fake", indirizzo_fake);
		
		
		indirizzo_fake_orig = scheda.indirizzoLocalita;
		mappaDeiParametri.put("indirizzo_fake_orig", indirizzo_fake_orig);
		
				
		nextStep = "";
		mappaDeiParametri.put("nextStep", nextStep);
		
				
		num_localita = "";
		mappaDeiParametri.put("num_localita", num_localita);
		
				
		numeroCivico = "";
		mappaDeiParametri.put("numeroCivico", numeroCivico);
				
		
		planimetria = "";
		mappaDeiParametri.put("planimetria", planimetria);
		
				
		virtual_tour = "";
		mappaDeiParametri.put("virtual_tour", virtual_tour);
		
				
		zoom = "15";
		mappaDeiParametri.put("zoom", zoom);
		
				
		SelTipoPagamento = "borsellino";
		mappaDeiParametri.put("SelTipoPagamento", SelTipoPagamento);
		
				
		address1 = "";
		mappaDeiParametri.put("address1", address1);
		
				
		amount = "";
		mappaDeiParametri.put("amount", amount);
		
				
		azionePayPal = "compraSubito";
		mappaDeiParametri.put("azionePayPal", azionePayPal);
				
		
		bn = "PP-BuyNowBF";
		mappaDeiParametri.put("bn", bn);
		
				
		business = "SELLER";
		mappaDeiParametri.put("business", business);
		
				
		check_1 = "on";
		mappaDeiParametri.put("check_1", check_1);
		
		
		/*check_108 = "on";
		mappaDeiParametri.put("check_108", check_108);*/
		
				
		cmd = "_xclick";
		mappaDeiParametri.put("cmd", cmd);
		
				
		currency_code = "EUR";
		mappaDeiParametri.put("currency_code", currency_code);
		
				
		first_name = "";
		mappaDeiParametri.put("first_name", first_name);
		
				
		item_name = "acquisto pubblicazione e visibilita";
		mappaDeiParametri.put("item_name", item_name);
		
		
		item_number = "pubblicazione";
		mappaDeiParametri.put("item_number", item_number);
		
		
		last_name = "";
		mappaDeiParametri.put("last_name", last_name);
		
				
		lc = "IT";
		mappaDeiParametri.put("lc", lc);
		
		
		no_note = "1";
		mappaDeiParametri.put("no_note", no_note);
		
		
		no_shipping = "1";
		mappaDeiParametri.put("no_shipping", no_shipping);
		
		
		notify_url = "NOTIFY_URL";
		mappaDeiParametri.put("notify_url", notify_url);
		
		
		prezzoTotale = "";
		mappaDeiParametri.put("prezzoTotale", prezzoTotale);
		
		
		_return = "http://getrix.ekbl.net/inserimento_annuncio.php?step=5&azionePayPal=notifica&idAnnuncio=" + mappaDeiParametri.get("idAnnuncio") + "&tipo=" + mappaDeiParametri.get("tipo") + "&buyedVis=0";
		mappaDeiParametri.put("return", _return);
		
		
		return_cancel = "http://getrix.ekbl.net/inserimento_annuncio.php?step=4&azionePayPal=show&idAnnuncio=" + mappaDeiParametri.get("idAnnuncio") + "&tipo=" + mappaDeiParametri.get("tipo") + "&buyedVis=0";
		mappaDeiParametri.put("return_cancel", return_cancel);
		
		
		richiesta = "";
		mappaDeiParametri.put("richiesta", richiesta);
		
		
		zip = "";
		mappaDeiParametri.put("zip", zip);
		
	}
	
	
}