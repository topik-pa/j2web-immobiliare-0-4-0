/*
 * Questa classe definische i metodi e gli attributi dell'oggetto scheda veicolo
 *
 */


/**
 *
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public class SchedaCliente implements Serializable, parametriGenerali  {
	
	private static final long serialVersionUID = 1L;
	
	//Attributi della scheda veicolo	
	long idSchedaCliente = new Date().getTime();	//id univoco riferito alla scheda
	String codiceSchedaCliente= intestazioneCodiceSchedaCliente + UUID.randomUUID().toString(); //codice scheda univoco
	
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
	
		//Al momento dell'istanziazione, una scheda immobile inizializza i propri campi prendendone il valore da quelli inseriti nel pannello Form
		titoloCliente = J2Web_UI.formCliente_getRdbtnSignora().isSelected()?"signora":"signor";
		marcaVeicoloCliente = (String) J2Web_UI.formCliente_getMarcaVeicolo().getSelectedItem();
		marcaVeicoloClienteIndex = J2Web_UI.formCliente_getMarcaVeicolo().getSelectedIndex();	
		modelloVeicoloCliente = (String) J2Web_UI.formCliente_getModelloVeicolo().getSelectedItem();
		modelloVeicoloClienteIndex = J2Web_UI.formCliente_getModelloVeicolo().getSelectedIndex();
		versioneVeicoloCliente = (String) J2Web_UI.formCliente_getVersioneVeicolo().getSelectedItem();
		versioneVeicoloClienteIndex = J2Web_UI.formCliente_getVersioneVeicolo().getSelectedIndex();
		tipologiaCarburanteVeicoloCliente = (String) J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().getSelectedItem();
		tipologiaCarburanteVeicoloClienteIndex = J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().getSelectedIndex();
		coloreEsternoVeicoloCliente = (String) J2Web_UI.formCliente_getColoreVeicolo().getSelectedItem();
		coloreEsternoVeicoloClienteIndex = J2Web_UI.formCliente_getColoreVeicolo().getSelectedIndex();
		tipologiaVeicoloCliente = (String) J2Web_UI.formCliente_getTipologiaCliente().getSelectedItem();
		tipologiaVeicoloClienteIndex = J2Web_UI.formCliente_getTipologiaCliente().getSelectedIndex();
				
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
	
	//Metodi
	
	
}