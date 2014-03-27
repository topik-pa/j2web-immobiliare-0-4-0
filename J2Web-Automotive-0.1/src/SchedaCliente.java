/**
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda cliente
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;


public class SchedaCliente implements Serializable, parametriGenerali  {

	private static final long serialVersionUID = 1L;

	//Attributi della scheda veicolo	
	long idSchedaCliente;
	String codiceSchedaCliente;

	String numResultsMLS = "ND"; //Valore predefinito

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
	String tipologiaVeicoloCliente;
	int tipologiaVeicoloClienteIndex;

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

		//Attributi della scheda veicolo	
		idSchedaCliente = new Date().getTime();	//id univoco riferito alla scheda per ordinamento cronologico
		codiceSchedaCliente= intestazioneCodiceSchedaVeicolo + (UUID.randomUUID().toString().substring(0,8)); //codice scheda univoco

		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		titoloCliente = J2Web_UI.formCliente_getRdbtnSignora().isSelected()?"signora":"signor";
		marcaVeicoloCliente = ((String) J2Web_UI.formCliente_getMarcaVeicolo().getSelectedItem()).trim();
		marcaVeicoloClienteIndex = J2Web_UI.formCliente_getMarcaVeicolo().getSelectedIndex();	
		modelloVeicoloCliente = ((String) J2Web_UI.formCliente_getModelloVeicolo().getSelectedItem()).trim();
		modelloVeicoloClienteIndex = J2Web_UI.formCliente_getModelloVeicolo().getSelectedIndex();
		versioneVeicoloCliente = ((String) J2Web_UI.formCliente_getVersioneVeicolo().getSelectedItem()).trim();
		versioneVeicoloClienteIndex = J2Web_UI.formCliente_getVersioneVeicolo().getSelectedIndex();
		tipologiaCarburanteVeicoloCliente = ((String) J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().getSelectedItem()).trim();
		tipologiaCarburanteVeicoloClienteIndex = J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().getSelectedIndex();
		coloreEsternoVeicoloCliente = ((String) J2Web_UI.formCliente_getColoreVeicolo().getSelectedItem()).trim();
		coloreEsternoVeicoloClienteIndex = J2Web_UI.formCliente_getColoreVeicolo().getSelectedIndex();
		tipologiaVeicoloCliente = ((String) J2Web_UI.formCliente_getTipologiaCliente().getSelectedItem()).trim();
		tipologiaVeicoloClienteIndex = J2Web_UI.formCliente_getTipologiaCliente().getSelectedIndex();

		nomeCliente = J2Web_UI.formCliente_getNome().getText().trim();	
		if(nomeCliente.length()>maxCaratteri.get("formCliente_textFieldNome")) {nomeCliente = nomeCliente.substring(0, maxCaratteri.get("formCliente_textFieldNome")-1);}
		cognomeCliente = J2Web_UI.formCliente_getCognome().getText().trim();	
		if(cognomeCliente.length()>maxCaratteri.get("formCliente_textFieldCognome")) {cognomeCliente = cognomeCliente.substring(0, maxCaratteri.get("formCliente_textFieldCognome")-1);}
		emailCliente = J2Web_UI.formCliente_getEmail().getText().trim();	
		if(emailCliente.length()>maxCaratteri.get("formCliente_textFieldEmail")) {emailCliente = emailCliente.substring(0, maxCaratteri.get("formCliente_textFieldEmail")-1);}
		telefono1Cliente = J2Web_UI.formCliente_getTelefono1().getText().trim();	
		if(telefono1Cliente.length()>maxCaratteri.get("formCliente_textFieldTelefono1")) {telefono1Cliente = telefono1Cliente.substring(0, maxCaratteri.get("formCliente_textFieldTelefono1")-1);}
		telefono2Cliente = J2Web_UI.formCliente_getTelefono2().getText().trim();	
		if(telefono2Cliente.length()>maxCaratteri.get("formCliente_textFieldTelefono2")) {telefono2Cliente = telefono2Cliente.substring(0, maxCaratteri.get("formCliente_textFieldTelefono2")-1);}
		viaCliente = J2Web_UI.formCliente_getVia().getText().trim();	
		if(viaCliente.length()>maxCaratteri.get("formCliente_textFieldVia")) {viaCliente = viaCliente.substring(0, maxCaratteri.get("formCliente_textFieldVia")-1);}
		numeroCivicoCliente = J2Web_UI.formCliente_getNumeroCivico().getText().trim();	
		if(numeroCivicoCliente.length()>maxCaratteri.get("formCliente_textFieldNumeroCivico")) {numeroCivicoCliente = numeroCivicoCliente.substring(0, maxCaratteri.get("formCliente_textFieldNumeroCivico")-1);}
		CAPCliente = J2Web_UI.formCliente_getCAP().getText().trim();	
		if(CAPCliente.length()>maxCaratteri.get("formCliente_textFieldCAP")) {CAPCliente = CAPCliente.substring(0, maxCaratteri.get("formCliente_textFieldCAP")-1);}
		cittaCliente = J2Web_UI.formCliente_getCitta().getText().trim();	
		if(cittaCliente.length()>maxCaratteri.get("formCliente_textFieldCitta")) {cittaCliente = cittaCliente.substring(0, maxCaratteri.get("formCliente_textFieldCitta")-1);}

	}


	//Metodi


}


//Classi Comparator per l'ordinamento delle schede secondo specifici criteri
class IdComparatorCliente implements Comparator<SchedaCliente> {

	//Per ID della scheda (data di inserimento)
	public int compare(SchedaCliente s1, SchedaCliente s2) {
		if (s1.idSchedaCliente > s2.idSchedaCliente)
			return 1;
		else if (s1.idSchedaCliente < s2.idSchedaCliente)
			return -1;
		else
			return 0;
	}
}

class cognomeComparator implements Comparator<SchedaCliente>	{

	public int compare(SchedaCliente s1, SchedaCliente s2) {    	
		int i = s1.cognomeCliente.compareTo(s2.cognomeCliente); 
		if (i > 0)
			return 1;
		else if (i < 0)
			return -1;
		else
			return 0;
	}
}

class cittaComparator implements Comparator<SchedaCliente>	{

	public int compare(SchedaCliente s1, SchedaCliente s2) {    	
		int i = s1.cittaCliente.compareTo(s2.cittaCliente); 
		if (i > 0)
			return 1;
		else if (i < 0)
			return -1;
		else
			return 0;
	}
}