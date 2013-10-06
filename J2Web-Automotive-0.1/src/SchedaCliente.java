/*
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda veicolo
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import javax.swing.JOptionPane;


public class SchedaCliente implements Serializable, parametriGenerali  {
	
	private static final long serialVersionUID = 1L;
	
	//Attributi della scheda veicolo	
	long idSchedaCliente = new Date().getTime();	//id univoco riferito alla scheda
	String codiceSchedaCliente= "j2w05_V" + UUID.randomUUID().toString(); //codice scheda univoco
	
	//Inizializzo il path per il file hash di questa scheda
	//String singolaSchedaClienteDatPath = ".\\schede\\" + codiceSchedaCliente + ".dat";
	
	String titoloCliente;
	String marcaVeicoloCliente;
	int marcaVeicoloClienteIndex;
	String modelloVeicoloCliente;
	int modelloVeicoloClienteIndex;
	String versioneVeicoloCliente;
	int versioneVeicoloClienteIndex;
	String tipologiaCarburanteVeicoloCliente;
	int tipologiaCarburanteVeicoloClienteIndex;
	String coloreEsternoVeicoloCliente;
	int coloreEsternoVeicoloClienteIndex;
	
	
	String nomeCliente;
	String cognomeCliente;
	String emailCliente;
	String telefono1Cliente;
	String telefono2Cliente;
	String viaCliente;
	String numeroCivicoCliente;
	String CAPCliente;
	String cittaCliente;
	
	
	
	//Costruttore
	public SchedaCliente () {	 	
	
		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		titoloCliente = J2Web_UI.formCliente_getRdbtnSignora().isSelected()?"signora":"signor";
		marcaVeicoloCliente = (String) J2Web_UI.formCliente_getMarcaVeicolo().getSelectedItem();
		marcaVeicoloClienteIndex =J2Web_UI.formCliente_getMarcaVeicolo().getSelectedIndex();	
		modelloVeicoloCliente = (String) J2Web_UI.formCliente_getModelloVeicolo().getSelectedItem();
		modelloVeicoloClienteIndex =J2Web_UI.formCliente_getModelloVeicolo().getSelectedIndex();
		versioneVeicoloCliente = (String) J2Web_UI.formCliente_getVersioneVeicolo().getSelectedItem();
		versioneVeicoloClienteIndex =J2Web_UI.formCliente_getVersioneVeicolo().getSelectedIndex();
		tipologiaCarburanteVeicoloCliente = (String) J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().getSelectedItem();
		tipologiaCarburanteVeicoloClienteIndex =J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().getSelectedIndex();
		coloreEsternoVeicoloCliente = (String) J2Web_UI.formCliente_getColoreVeicolo().getSelectedItem();
		coloreEsternoVeicoloClienteIndex =J2Web_UI.formCliente_getColoreVeicolo().getSelectedIndex();	
				
		nomeCliente = J2Web_UI.formCliente_getNome().getText().trim();
		cognomeCliente = J2Web_UI.formCliente_getCognome().getText().trim();
		emailCliente = J2Web_UI.formCliente_getEmail().getText().trim();
		telefono1Cliente = J2Web_UI.formCliente_getTelefono1().getText().trim();
		telefono2Cliente = J2Web_UI.formCliente_getTelefono1().getText().trim();
		viaCliente = J2Web_UI.formCliente_getVia().getText().trim();
		numeroCivicoCliente = J2Web_UI.formCliente_getNumeroCivico().getText().trim();
		CAPCliente = J2Web_UI.formCliente_getCAP().getText().trim();
		cittaCliente = J2Web_UI.formCliente_getCitta().getText().trim();
		
	}
}

	
	//Metodi





//Classi Comparator per l'ordinamento delle schede secondo specifici criteri
/*class IdComparator implements Comparator<SchedaVeicolo> {

    public int compare(SchedaVeicolo s1, SchedaVeicolo s2) {
        if (s1.idScheda > s2.idScheda)
            return 1;
        else if (s1.idScheda < s2.idScheda)
            return -1;
        else
            return 0;
    }
}*/


/*class CodeComparator implements Comparator<SchedaVeicolo>  {
	
    public int compare(SchedaVeicolo s1, SchedaVeicolo s2) { 	
    	int i = s1.codiceInserzione.compareTo(s2.codiceInserzione); 
    	System.out.println("i:" + i);
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/


/*class CityComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.comune.compareTo(s2.comune); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/


/*class ProvinceComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.provincia.compareTo(s2.provincia); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/


/*class RegionComparator implements Comparator<SchedaImmobile>	{
	
    public int compare(SchedaImmobile s1, SchedaImmobile s2) {    	
    	int i = s1.regione.compareTo(s2.regione); 
        if (i > 0)
            return 1;
        else if (i < 0)
            return -1;
        else
            return 0;
    }
}*/