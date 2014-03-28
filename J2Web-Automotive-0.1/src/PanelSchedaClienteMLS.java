/**
 * Questa classe definisce tutti i sottopannelli schede cliente nella sezione MLS
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JComponent;
import org.json.JSONArray;
import org.json.JSONObject;

class PanelSchedaClienteMLS extends JPanel implements parametriGenerali {   

	private static final long serialVersionUID = -4834316489876756142L;

	static Component spaziatoreVerticaleRigido = Box.createVerticalStrut(6);

	SchedaCliente scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;

	String labelSpaziatore = "   "; 

	public PanelSchedaClienteMLS(final SchedaCliente scheda, final LinkedList<SchedaCliente> listaSchedeCliente, final ButtonGroup radioGrpSchede) {
		this.scheda = scheda;
		idScheda = scheda.idSchedaCliente;
		codiceScheda = scheda.codiceSchedaCliente;

		setLayout(new BorderLayout(0, 0));
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setMaximumSize(new Dimension(700, 150));

		//Radio button dei sottopannelli
		schedaRadio = new JRadioButton("Seleziona scheda");
		//Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		radioGrpSchede.add(schedaRadio); 
		//Clicco su una radio button di una scheda
		schedaRadio.addActionListener(new ActionListener() {			 
			public void actionPerformed(ActionEvent e) {
				System.out.println("Scheda selezionata: " + scheda.codiceSchedaCliente + " per MLS..."); 

				//Il cursone viene messo in modalità attesa
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				JPanel pannelloListaEstrazioneMLS = J2Web_UI.getPanel_15();
				pannelloListaEstrazioneMLS.removeAll();
				pannelloListaEstrazioneMLS.updateUI();

				Component[] test = getParent().getComponents();
				for(int i=0; i<test.length; i++) {
					if(test[i].getClass().toString().contains("PanelSchedaCliente"))  {
						((JComponent) test[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
					}
				}

				setBorder(new LineBorder(Color.ORANGE));

				matchClienteVeicoloMLS(scheda);

				//Il cursone viene messo in modalità standard
				setCursor(Cursor.getDefaultCursor());

			}
		});
		add(schedaRadio, BorderLayout.NORTH);

		//La label delle schede
		String linea1 = scheda.nomeCliente + " " + scheda.cognomeCliente;	
		String linea2 = scheda.telefono1Cliente + " " + scheda.emailCliente;if(linea2.length()>45) {linea2 = linea2.substring(0, 44);}
		String labelScheda = "<html><p style='padding:5px;'><strong>" + linea1 + "</strong><br/><i>" + linea2 + "</i></p></html>";

		String tooltipScheda = labelScheda;

		JLabel label = new JLabel(labelScheda);
		Font font = new Font("Monospaced", Font.PLAIN, 11);
		label.setFont(font);
		label.setHorizontalTextPosition(SwingConstants.RIGHT);

		String numRisultatiMLS = scheda.numResultsMLS;
		String labelNumeroRisultati = "Numero risultati MLS: " + numRisultatiMLS;
		JLabel labelNumeroRisultati2 = new JLabel(labelNumeroRisultati);
		labelNumeroRisultati2.setHorizontalTextPosition(SwingConstants.RIGHT);

		// Get current classloader
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon humanIcon = null;
		if(scheda.titoloCliente.equals("signora")) {
			humanIcon  = new ImageIcon(cl.getResource("images/icon_woman.png"));
		}
		else {
			humanIcon  = new ImageIcon(cl.getResource("images/icon_man.png"));
		}

		add(label, BorderLayout.CENTER);
		add(new JLabel(" ", humanIcon, JLabel.LEFT),BorderLayout.WEST);
		add(labelNumeroRisultati2, BorderLayout.SOUTH);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);

	}

	private void  matchClienteVeicoloMLS(SchedaCliente schedaCliente) {

		String queryString = "";

		JPanel pannelloMatchClienteVeicoloMLS = J2Web_UI.getPanel_15();

		pannelloMatchClienteVeicoloMLS.add(spaziatoreVerticaleRigido);

		//Costruisco la query sql
		queryString = "?v=407";
		try {
			queryString += "&marcaVeicoloCliente=" + URLEncoder.encode(scheda.marcaVeicoloCliente, "UTF-8");
			queryString += "&modelloVeicoloCliente=" + URLEncoder.encode(scheda.modelloVeicoloCliente, "UTF-8");
			queryString += "&versioneVeicoloCliente=" + URLEncoder.encode(scheda.versioneVeicoloCliente, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		System.out.println("query: " + queryString);

		//Invio la richiesta al server remoto
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			Object[] response = getInfoVeicolo.get("GET", urlTunnelDBNekta + queryString, null, null, true);
			String responseBody = (String)response[1];
			JSONObject json = null;
			try {
				json = new JSONObject(responseBody);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(!json.get("affectedrows").equals("0")) {
				JSONArray jsonResults = json.getJSONArray("rows"); 

				for(int i=0; i<jsonResults.length(); i++) {
					JSONArray currentJsonArray = jsonResults.getJSONArray(i); //rappresenta una riga della tabella ed una veicolo differente

					SchedaVeicolo schedaVeicoloMLS = new SchedaVeicolo(currentJsonArray);

					PanelSchedaVeicoloMLS panelSchedaVeicoloMLS = new PanelSchedaVeicoloMLS(schedaVeicoloMLS);

					pannelloMatchClienteVeicoloMLS.add(panelSchedaVeicoloMLS);

					pannelloMatchClienteVeicoloMLS.add(spaziatoreVerticaleRigido);
				}
			}
			else {
				JPanel panelNessunaVeicoloMLSDisponibile = new JPanel();
				JLabel lblNessunaVeicoloMLSDisponibile = new JLabel("Al momento non è disponibile un veicolo per il cliente selezionato");                
				panelNessunaVeicoloMLSDisponibile.add(lblNessunaVeicoloMLSDisponibile);
				pannelloMatchClienteVeicoloMLS.add(panelNessunaVeicoloMLSDisponibile);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

		pannelloMatchClienteVeicoloMLS.updateUI();		

		//Tracking dell'evento di matching MLS
		System.out.print("Tracking dell'evento di matching MLS...");
		try {
			j2web.trackEvent("matching_j2web_" + j2web_version + "_" + EMAIL_UTENTE, schedaCliente.codiceSchedaCliente);
		} catch (IOException e) {
		}
		System.out.print(" fatto." + "\n");

	}

	public static void  sincronizzaRisultati(final LinkedList<SchedaCliente> listaSchedeCliente) {

		System.out.print("numero schede: " + listaSchedeCliente.size());

		String queryString = "";

		ListIterator<SchedaCliente> iterator = listaSchedeCliente.listIterator();
		while(iterator.hasNext()) {
			SchedaCliente schedaCorrente = iterator.next();

			//Costruisco la query sql
			queryString = "?v=407";
			try {
				queryString += "&marcaVeicoloCliente=" + URLEncoder.encode(schedaCorrente.marcaVeicoloCliente, "UTF-8");
				queryString += "&modelloVeicoloCliente=" + URLEncoder.encode(schedaCorrente.modelloVeicoloCliente, "UTF-8");
				queryString += "&versioneVeicoloCliente=" + URLEncoder.encode(schedaCorrente.versioneVeicoloCliente, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			System.out.println("query: " + queryString);

			//Invio la richiesta al server remoto
			HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
			try {
				Object[] response = getInfoVeicolo.get("GET", urlTunnelDBNekta + queryString, null, null, true);
				String responseBody = (String)response[1];
				JSONObject json = null;
				try {
					json = new JSONObject(responseBody);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				String numResults = (String) json.get("affectedrows");

				System.out.print("numero risultati: " + numResults);

				schedaCorrente.numResultsMLS = numResults;

			} catch (IOException e) {
				e.printStackTrace();
			}		

		}

		J2Web_UI.aggiornaPannelloListaSchedeCliente();		
	}

}