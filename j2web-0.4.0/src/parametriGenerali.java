import java.awt.Image;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.ImageIcon;

public interface parametriGenerali {
	
	//Versione di J2Web
	final String  j2web_version = "0.4.0";
	
	//Utente del programma
	final String UTENTE ="Marco Pavan";
	
	//Mail dell'utente
	final String EMAIL_1 ="marcopavan.mp@gmail.com";
	final String EMAIL_1_DOMAIN ="@gmail.com";
	final String EMAIL_1_PSW ="IsMPsd80";
	final String EMAIL_1_SMTP_HOST ="smtp.gmail.com";
	final int EMAIL_1_SMTP_PORT =465;
	
	//Mail del backend
	final String EMAIL_BACKEND ="marco--pavan@libero.it";
	
	//Nome della GUI
	final String nomeGUI = "J2Web - Immobiliare" + " " + j2web_version;
	
	//Dimensioni di default della GUI 
	final int[] GUI_bounds = {100, 100, 1024, 600};
	
	//Icona della finestra principale
    final Image frameIcon = new ImageIcon("./images/imaginationLogo.png").getImage();
    
    //icone dei pulsanti
    final ImageIcon icoProcedi = new ImageIcon("./images/forward.png");
    final ImageIcon icoResetta = new ImageIcon("./images/refresh.png");
	
	//Tutti gli array che compongono le combo box
	final String[] arrayRegioni = {"Seleziona la Regione", "Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia-Romagna", "Friuli-Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana", "Trentino-Alto Adige", "Umbria", "Valle d'Aosta", "Veneto"};
	
	final String[] provincieAbruzzo = {"L'Aquila", "Chieti", "Pescara", "Teramo"};
	final String[] provincieBasilicata = {"Matera", "Potenza"};
	final String[] provincieCalabria = {"Cosenza", "Catanzaro", "Crotone", "Reggio Calabria", "Vibo Valentia"};
	final String[] provincieCampania = {"Avellino", "Benevento", "Caserta", "Napoli", "Salerno"};
	final String[] provincieEmiliaRomagna = {"Bologna", "Forlì-Cesena", "Ferrara", "Modena", "Piacenza", "Parma", "Ravenna", "Reggio Emilia", "Rimini"};
	final String[] provincieFriuliVeneziaGiulia = {"Gorizia", "Pordenone", "Trieste", "Udine"};
	final String[] provincieLazio = {"Frosinone", "Latina", "Rieti", "Roma", "Viterbo"};
	final String[] provincieLiguria = {"Genova", "Imperia", "La Spezia", "Savona"};
	final String[] provincieLombardia = {"Bergamo", "Brescia", "Como", "Cremona", "Lecco", "Milano", "Mantova", "Pavia", "Sondrio", "Varese"};
	final String[] provincieMarche = {"Ancona", "Ascoli Piceno", "Massa Carrara", "Pesaro e Urbino"};
	final String[] provincieMolise = {"Campobasso", "Isernia"};
	final String[] provinciePiemonte = {"Alessandria", "Asti", "Biella", "Cuneo", "Novara", "Torino", "Verbano-Cusio-Ossola", "Vercelli"};
	final String[] provinciePuglia = {"Bari", "Brindisi", "Foggia", "Lecce", "Taranto"};
	final String[] provincieSardegna = {"Olbia-Tempio", "Ogliastra", "Medio Campidano", "Carbonia Iglesias", "Cagliari", "Nuoro", "Oristano", "Sassari"};
	final String[] provincieSicilia = {"Agrigento", "Caltanissetta", "Catania", "Enna", "Messina", "Palermo", "Ragusa", "Siracusa", "Trapani"};
	final String[] provincieToscana = {"Arezzo", "Firenze", "Grosseto", "Livorno", "Lucca", "Massa-Carrara", "Pisa", "Prato", "Pistoia", "Siena"};
	final String[] provincieTrentinoAltoAdige = {"Bolzano", "Trento"};
	final String[] provincieUmbria = {"Perugia", "Terni"};
	final String[] provincieValleDAosta = {"Aosta"};
	final String[] provincieVeneto = {"Belluno", "Padova", "Rovigo", "Treviso", "Venezia", "Vicenza", "Verona"};
	
	Map<String,String[]> regioneProvincie =  new Hashtable<String,String[]>();	
	
	final String[] arrayCategorieImmobili = {"Seleziona la categoria", "Residenziale", "Commerciale", "Industriale", "Turistico"};
	final String[] arrayTipologieContratto = {"Affitto", "Vendita"};
	final String[] arrayNumeroLocali = {"1", "2", "3", "4", "5", "6", "7", ">7"};
	final String[] arrayNumeroCamere = {"1", "2", "3", "4", "5", ">5"};
	final String[] arrayNumeroBagni = {"1", "2", "3", "4", "5", ">5"};
	final String[] arrayStatoImmobile = {"Seleziona", "Nuovo", "Ristrutturato", "Da ristrutturare", "In buono stato", "Abitabile", "Ottimo", "In costruzione"};
	final String[] arrayArredamenti = {"Seleziona", "Arredato", "Semi arredato", "Non arredato"};
	final String[] arrayPiano = {"Seleziona", "Piano terra", "Primo piano", "Piano intermedio", "Piano alto", "Ultimo piano", "Su più livelli"};
	final String[] arrayCertificazioniEnergetiche = {"Seleziona", "Certificazione energetica A+", "Certificazione energetica A", "Certificazione energetica B", "Certificazione energetica C", "Certificazione energetica D", "Certificazione energetica E", "Certificazione energetica F", "Certificazione energetica G", "Non specificata"};
	final String[] arrayTipologieRiscaldamento = {"Seleziona", "Assente", "Centralizzato", "Autonomo", "Stufa"};
	final String[] arrayClima = {"Seleziona", "Assente", "Aria condizionata", "Climatizzatore"};
	final String[] arrayParcheggio = {"Seleziona", "Nessuno", "Garage", "Posto auto coperto", "Posto auto scoperto"};
	final String[] arrayGiardino = {"Seleziona", "Assente", "Giardino condominiale", "Giardino ad uso esclusivo"};
	
	
}