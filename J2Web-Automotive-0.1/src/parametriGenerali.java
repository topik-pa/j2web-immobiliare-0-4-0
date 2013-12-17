import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JComponent;


public interface parametriGenerali {

	//Versione di J2Web eintestazioni codice schede
	final String  j2web_version = "0.5";
	final String  intestazioneCodiceSchedaVeicolo = "j2w05_V_";
	final String  intestazioneCodiceSchedaCliente = "j2w05_C_";

	//Utente del programma
	final String UTENTE ="Marco Pavan";
	final String EMAIL_UTENTE ="marco--pavan@libero.it";
	final String RAGIONESOCIALE_UTENTE ="Auto&Auto srl";
	final String REGIONE_UTENTE ="Friuli-Venezia Giulia";
	final String PROVINCIA_UTENTE ="Udine";
	final String CITTA_UTENTE ="Udine";
	final String INDIRIZZO_UTENTE ="via Buttrio, 667 - 33100 Udine";
	final String TELEFONO_UTENTE ="3295698985";

	//Mail del back end
	final String BACKEND_EMAIL ="marcopavan.mp@gmail.com";
	final String BACKEND_EMAIL_DOMAIN ="@gmail.com";
	final String BACKEND_EMAIL_PSW ="IsMPsd80";
	final String BACKEND_EMAIL_SMTP_HOST ="smtp.gmail.com";
	final int BACKEND_EMAIL_SMTP_PORT =465;

	//Nome della GUI
	final String nomeGUI = "J2Web - Automotive" + " " + j2web_version;

	//Dimensioni di default della GUI 
	final int[] GUI_bounds = {120, 60, 1160, 625};

	//Request headers utilizzati per le connessioni
	final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 5.1; rv:26.0) Gecko/20100101 Firefox/26.0";
	final String CONNECTION = "keep-alive";
	final String CACHE_CONTROL = "max-age=0";
	final String ACCEPT_LANGUAGE = "it-IT,it;q=0.8,en-US;q=0.6,en;q=0.4";
	//final String ACCEPT_ENCODING = "gzip,deflate,sdch"; //spacca tutto!!!
	final String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

	//Messaggi delle finestre modali: inizializzo la mappa che conterrà la tipologia di evento e il relativo messaggio. L'inizializzazione è nel file: j2web.java
	final Map<String,String> MapModalWindowsDialogs = new Hashtable<String,String>();

	//Dimensioni massime delle immagini
	final int maxFileSize = 1048576;
	//Formato immagini consentito
	final String format=".jpg";
	final String format2=".jpeg";

	//Path delle icone
	final String frameIcon = "images/j2webFrameIcon.png";
	final String iconCar = "images/icon_car.png";
	final String iconPilot = "images/icon_pilot.png";
	final String iconDB = "images/icon_db.png";
	final String iconUpdate = "images/update.png";
	final String refreshIcon = "images/refresh.png";
	final String forwardIcon = "images/forward.png";

	//Mappa che contiene il limite di caratteri immessi per certi campi testuali. L'inizializzazione è nel file: j2web.java
	Map<String,Integer> maxCaratteri =  new Hashtable<String,Integer>(); 

	//Percorsi directory e file
	String pathJ2web = System.getProperty("user.home") + File.separator + ".j2web\\";   
	String pathSchede = pathJ2web + "schede\\";
	String pathTemp = pathJ2web + "temp\\";
	String pathFileDatSchedeVeicolo = pathSchede + "schedeVeicolo.dat";
	String pathFileDatSchedeCliente = pathSchede + "schedeClienti.dat";

	//Url http tunneling
	String urlHTTPTunnel = "http://www.j2webstudio.it/httptunneling/mysqltunnel.php";

	//Url tracking remoto
	String urlTrackingRemoto = "http://www.j2webstudio.it/tracking/tracking.php";

	//Url inserimento immagini in remoto
	String urlInserimentoImmaginiInRemoto = "http://www.j2webstudio.it/uploadImages.php";

	//Url posizione immagini in remoto
	String urlLocationImmaginiInRemoto = "http://www.j2webstudio.it/j2webVeichleImages/";

	//Lista concatenata che contiene tutti i campi della form veicolo soggetti a lettura/scrittura o modifiche varie a runtime
	LinkedList<JComponent> listCampiFormVeicolo = new LinkedList<JComponent>();

	//Lista concatenata che contiene tutti i campi della form cliente soggetti a lettura/scrittura o modifiche varie a runtime
	LinkedList<JComponent> listCampiFormCliente = new LinkedList<JComponent>();

	//Lista concatenata che contiene tutti i campi della form veicolo che sono obbligatori
	LinkedList<JComponent> listCampiFormVeicoloObbligatori = new LinkedList<JComponent>();

	//Lista concatenata che contiene tutti i campi della form clienet che sono obbligatori
	LinkedList<JComponent> listCampiFormClienteObbligatori = new LinkedList<JComponent>();

	//La struttura dati che contiene i portali selezionati per l'inserimento sequenziale
	//public static LinkedList<PortaleWeb> listPortaliInserimentoSequenziale = new LinkedList<PortaleWeb>();

	//La struttura dati che contiene i portali selezionati per l'eliminazione sequenziale
	//public static LinkedList<PortaleWeb> listPortaliCancellazioneSequenziale = new LinkedList<PortaleWeb>();

	//La struttura dati che contiene le schede cliente che fanno match con la scheda veicolo eventualmente selezionata
	public static LinkedList<SchedaCliente> listSchedeClientiMatch = new LinkedList<SchedaCliente>();

	//La struttura dati che contiene le schede veicolo che fanno match con la scheda cliente eventualmente selezionata
	public static LinkedList<SchedaVeicolo> listSchedeVeicoliMatch = new LinkedList<SchedaVeicolo>();

	//La struttura dati che contiene i portali attivati
	public static LinkedList<PortaleWeb> listPortaliSincronizzazione = new LinkedList<PortaleWeb>();	

	//Inizializzo la lista buffer che contiene le possibili versioni del veicolo attuale
	LinkedList<String> listVersioniVeicoli = new LinkedList<String>();

	//Tutti gli array<String> che definiscono le combo box
	final String[] marcheAutoveicoli = {"Seleziona", "Abarth", "AC", "Acura", "Alfa Romeo", "Allard", "Alpina", "Alpine", "Alvis", "AMC", "Ariel", "Armstrong Siddeley", "Ascari", "Aston Martin", "Audi", "Austin", "Austin-Healey", "Autobianchi", "Auverland", "Avanti", "Beijing", "Bentley", "Berkeley", "Bitter", "Bizzarrini", "BMW", "Brilliance", "Bristol", "Bugatti", "Buick", "Cadillac", "Caterham", "Checker", "Chevrolet", "Chrysler", "Citroen", "Dacia", "Daewoo", "DAF", "Daihatsu", "Daimler", "Datsun", "De Tomaso", "DKW", "Dodge", "Donkervoort", "Eagle", "Fairthorpe", "Ferrari", "Fiat", "Fisker", "Ford", "GAZ", "Geely", "Ginetta", "GMC", "Holden", "Honda", "Hudson", "Humber", "Hummer", "Hyundai", "Infiniti", "Innocenti", "Isuzu", "Italdesign", "Jaguar", "Jeep", "Jensen", "Kia", "Koenigsegg", "Lada", "Lamborghini", "Lancia", "Land Rover", "Lexus", "Lincoln", "Lotec", "Lotus", "Luxgen", "Mahindra", "Marcos", "Maserati", "Matra-Simca", "Maybach", "Mazda", "MCC", "McLaren", "Mercedes-Benz", "Mercury", "MG", "Mini", "Mitsubishi", "Monteverdi", "Moretti", "Morgan", "Morris", "Nissan", "Noble", "NSU", "Oldsmobile", "Opel", "Packard", "Pagani", "Panoz", "Peugeot", "Pininfarina", "Plymouth", "Pontiac", "Porsche", "Proton", "Reliant", "Renault", "Riley", "Rolls-Royce", "Rover", "Saab", "Saleen", "Samsung", "Saturn", "Scion", "Seat", "Simca", "Singer", "Skoda", "Smart", "Spyker", "SsangYong", "SSC", "Steyr", "Studebaker", "Subaru", "Sunbeam", "Suzuki", "Talbot", "Tata", "Tatra", "Tesla", "Toyota", "Trabant", "Triumph", "TVR", "Vauxhall", "Vector", "Venturi", "Volkswagen", "Volvo", "Wartburg", "Westfield", "Willys-Overland", "Xedos", "Zagato", "Zastava", "ZAZ", "Zenvo", "ZIL", "Altro"};
	final String[] marcheMotoveicoli = {"Seleziona", "A.T.U", "Adler", "Adly", "Aeon", "AGM", "Aiyumo", "Alisze", "Alpha", "Aprilia", "Arctic Cat", "AWO", "Baotian", "Barossa", "Bashan", "Beeline", "Benda", "Benelli", "Benzhou", "Beta", "Big Dog", "Bimota", "Blata", "BMW", "Bombardier", "Boom Trike", "Borossi", "Boss Hoss", "Brammo", "BSA", "Buell", "Burelli", "Cagiva", "Can Am", "CCM", "Cectek", "CFMOTO", "Chang - Jiang", "CPI", "CR&amp;S", "CSR", "CZ", "Daelim", "Derbi", "Dinli", "DKW", "Dnepr", "Ducati", "E.-ATV", "Ecomobile", "E-Max", "Emco", "EMW", "Epella", "Ering", "E-Ton", "Explorer", "Fantic", "Fecht Trike", "Garelli", "Gas Gas", "Genata", "Generic", "GG", "Giantco", "Gilera", "Gillet", "Goes", "Govecs", "Harley-Davidson", "Heinkel", "Hercules", "Hisun", "HM", "Honda", "Horex", "Husaberg", "Husqvarna", "Hyosung", "IFA", "Indian", "Italjet", "IWL", "Jack Fox", "Jawa", "Jincheng", "Jinlun", "Jonway", "Junak", "Kawasaki", "Keeway", "Kinroad", "Kreidler", "KSR MOTO", "KTM", "Kumpan Electric", "Kwang Yang", "Kymco", "Laverda", "LEM", "Leonart", "Lifan", "Linhai", "LML", "Loncin", "Luxxon", "Maico", "Malaguti", "Masai", "masini", "MBK", "Mc Motors", "Megelli", "Miele", "Montesa", "Moto Guzzi", "Moto Morini", "Motobecane", "Motobi", "Motorhispania", "Motowell", "MTR", "MV Agusta", "MZ", "Nitro Motors", "Norton", "Nova Motors", "NSU", "Palmo", "Panther", "Pegasus", "Peugeot", "PGO", "Piaggio", "Pocket Bike", "Polaris", "Puch", "Qingqi", "Real", "Regal-Raptor", "Rewaco", "REX", "Rieju", "Rivero", "Royal Enfield", "Sachs", "Seikel", "SEV", "Sherco", "Shineray", "Simson", "Skyteam", "SMC", "Solo", "Standard", "Suzuki", "SYM", "Tauris", "TGB", "TM", "Tomos", "Trike", "Triton", "Triumph", "Turbho", "Ural", "Vectrix", "Velosolex", "Venturino", "Vespa", "Vespino", "Victoria", "Victory", "VOR Vertemati", "Voxan", "WMI", "Xingfu", "Xingyue", "Yamaha", "Zero", "Zhongyu", "Zing", "Zipp", "Zongshen", "Zündapp", "Zweirad Union", "Altro"};
	final String[] carburantiAutoveicoli = {"Seleziona", "Benzina", "Diesel", "GPL", "Metano", "Elettrica/Benzina", "Elettrica/Diesel", "Etanolo", "Elettrica", "Idrogeno", "Altro"};
	final String[] carburantiMotoveicoli = {"Seleziona", "Benzina 2T", "Diesel", "Elettrica/Benzina", "Elettrica", "Gas"};
	final String[] tipologiaAutoveicoli = {"Seleziona", "Veicolo d'epoca", "Veicolo km 0", "Veicolo nuovo", "Veicolo semestrale", "Veicolo usato", "Veicolo aziendale"};
	final String[] tipologiaMotoveicoli = {"Seleziona", "Veicolo d'epoca", "Veicolo nuovo", "Veicolo usato"};
	final String[] comboboxModelMesi = {"Mese", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
	final String[] comboboxModelAnni = {"Anno", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"};
	final String[] comboboxModelCarrozzeria = {"Seleziona", "City car", "Cabrio", "Coupé", "SUV/Fuoristrada", "Station wagon", "Berlina", "Altro", "Monovolume", "Furgoni/Van"};
	final String[] comboboxModelPostiASedere = {"Seleziona", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	final String[] comboboxModelColoreEsterno = {"Seleziona", "Arancione", "Argento", "Beige", "Bianco", "Blu/Azzurro", "Bronzo", "Giallo", "Grigio", "Lilla", "Marrone", "Nero", "Oro", "Rosso", "Verde"};
	final String[] comboboxModelPrecedentiProprietari = {"Seleziona", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	final String[] comboboxModelFinitureInterni = {"Seleziona", "Alcantara", "Pelle parziale", "Pelle scamosciata", "Pelle totale", "Stoffa", "Altro"};
	final String[] comboboxModelColoreInterni = {"Seleziona", "Beige", "Grigio", "Marrone", "Nero", "Altro"};
	final String[] comboboxModelMotore = {"Seleziona", "4x4", "6wd", "Anteriore", "Posteriore"};
	final String[] comboboxModelCambio = {"Seleziona", "Automatico", "Manuale", "Semiautomatico", "Nessuno"};
	final String[] comboboxModelNumeroRapporti = {"Seleziona", "3", "4", "5", "6", "7"};
	final String[] comboboxModelClasseEmissioni = {"Seleziona", "Euro 1", "Euro 2", "Euro 3", "Euro 4", "Euro 5", "Euro 6"};
	final String[] comboboxModelTipologieContratto = {"Seleziona", "Vendita", "Affitto", "Leasing"};

}