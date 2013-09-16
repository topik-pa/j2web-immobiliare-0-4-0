/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/ 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		return isSequential;
    	
    	
    	       
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


	@Override
	public boolean inserisciScheda(SchedaVeicolo scheda, boolean isSequential)
			throws HttpCommunicationException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean visualizzaScheda(SchedaVeicolo scheda)
			throws HttpCommunicationException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean cancellaScheda(SchedaVeicolo scheda, boolean isSequential)
			throws HttpCommunicationException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}