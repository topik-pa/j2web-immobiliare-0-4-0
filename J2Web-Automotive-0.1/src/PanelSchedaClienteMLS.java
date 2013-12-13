import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

	private static final long serialVersionUID = 1L;

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
		setMaximumSize(new Dimension(600, 130));

		//Radio button dei sottopannelli
		schedaRadio = new JRadioButton("Seleziona scheda");
		//Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		radioGrpSchede.add(schedaRadio); 
		//Clicco su una radio button di una scheda
		schedaRadio.addActionListener(new ActionListener() {			 
			public void actionPerformed(ActionEvent e) {
				System.out.println("Scheda selezionata: " + scheda.codiceSchedaCliente + " per MLS..."); 
				
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

			}
		});
		add(schedaRadio, BorderLayout.NORTH);

		//La label delle schede
		String labelScheda = scheda.nomeCliente + " " + scheda.cognomeCliente + " - " + scheda.telefono1Cliente + " - " + scheda.telefono2Cliente + " - " + scheda.emailCliente + " - " + scheda.viaCliente + " " + scheda.numeroCivicoCliente + " "  + scheda.cittaCliente;
		String tooltipScheda = labelScheda;
		if(labelScheda.length()>60) {	//è molto probabile che lo sia... :)
			labelScheda = labelScheda.substring(0, 60); 
		}		 
		labelScheda+="...";
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
		if(scheda.titoloCliente.equals("signora")) {
			ImageIcon womanIcon  = new ImageIcon(cl.getResource("images/icon_woman.png"));
			label.setIcon(womanIcon);
		}
		else {
			ImageIcon manIcon  = new ImageIcon(cl.getResource("images/icon_man.png"));
			label.setIcon(manIcon);
		}

		add(label, BorderLayout.CENTER);
		add(labelNumeroRisultati2, BorderLayout.SOUTH);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);

	}

	private void  matchClienteVeicoloMLS(SchedaCliente schedaCliente) {

		JPanel pannelloMatchClienteVeicoloMLS = J2Web_UI.getPanel_15();

		pannelloMatchClienteVeicoloMLS.add(Box.createVerticalStrut(6));

		String host = "sql.j2webstudio.it";
		String port = "3306";
		String charset = "latin1";
		String dbname = "j2webstu85037";
		String username = "j2webstu85037";
		String password = "j2we20858";

		//Costruisco la query sql
		String querySQL_1 = "SELECT * FROM autoveicoli WHERE ";
		String querySQL_2 = "(Marca = ";
		String querySQL_3 =  "'" + scheda.marcaVeicoloCliente + "'";
		String querySQL_4 = " AND ";
		String querySQL_5 = " Modello = ";
		String querySQL_6 = "'" + scheda.modelloVeicoloCliente + "')";
		String querySQL_7 = " OR ";
		String querySQL_8 = "(Marca = ";
		String querySQL_9 =  "'" + scheda.marcaVeicoloCliente + "'";
		String querySQL_10 = " AND ";
		String querySQL_11 = " Carburante = ";
		String querySQL_12 = "'" + scheda.tipologiaCarburanteVeicoloCliente + "')";
		String querySQL_13 = " OR ";
		String querySQL_14 = "(Versione = ";
		String querySQL_15 =  "'" + scheda.versioneVeicoloCliente + "')";

		String querySQL_16 = " OR ";
		String querySQL_17 = "(Marca = ";
		String querySQL_18 =  "'" + scheda.marcaVeicoloCliente + "'";
		String querySQL_19 = " AND ";
		String querySQL_20 = " ColoreEsterno = ";
		String querySQL_21 = "'" + scheda.coloreEsternoVeicoloCliente + "')";

		String querySQL_22 = " OR ";
		String querySQL_23 = "(Marca = ";
		String querySQL_24 =  "'" + scheda.marcaVeicoloCliente + "'";
		String querySQL_25 = " AND ";
		String querySQL_26 = " Tipologia = ";
		String querySQL_27 = "'" + scheda.tipologiaVeicoloCliente + "')";


		String querySQL = querySQL_1 + querySQL_2 + querySQL_3 + querySQL_4 + querySQL_5 + querySQL_6 + querySQL_7 + querySQL_8 + querySQL_9 + querySQL_10 + querySQL_11 + querySQL_12 + querySQL_13 + querySQL_14 + querySQL_15 + querySQL_16 + querySQL_17 + querySQL_18 + querySQL_19 + querySQL_20 + querySQL_21 + querySQL_22 + querySQL_23 + querySQL_24 + querySQL_25 + querySQL_26 + querySQL_27;
		String encodedQuerySQL = "";

		System.out.println("query: " + querySQL);

		//Encoding della query
		try {
			encodedQuerySQL = URLEncoder.encode(querySQL, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Invio la richiesta al server remoto
		HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
		try {
			Object[] response = getInfoVeicolo.get("GET", urlHTTPTunnel + "?host=" + host + "&port=" + port + "&charset=" + charset + "&dbname=" + dbname + "&username=" + username + "&password=" + password + "&query=" + encodedQuerySQL, true);
			String responseBody = (String)response[1];
			JSONObject json = null;
			try {
				json = new JSONObject(responseBody);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(!json.get("affectedrows").equals("0")) {
				JSONArray jsonResults = json.getJSONArray("rows"); 

				for(int i=0; i<jsonResults.length(); i++) {
					JSONArray currentJsonArray = jsonResults.getJSONArray(i); //rappresenta una riga della tabella

					SchedaVeicolo schedaVeicoloMLS = new SchedaVeicolo(currentJsonArray);

					PanelSchedaVeicoloMLS panelSchedaVeicoloMLS = new PanelSchedaVeicoloMLS(schedaVeicoloMLS);

					pannelloMatchClienteVeicoloMLS.add(panelSchedaVeicoloMLS);
					
					pannelloMatchClienteVeicoloMLS.add(Box.createVerticalStrut(6));
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
			// TODO Auto-generated catch block
		}
		System.out.print(" fatto." + "\n");

	}

	
	public static void  sincronizzaRisultati(final LinkedList<SchedaCliente> listaSchedeCliente) {
		
		System.out.print("numero schede: " + listaSchedeCliente.size());
		
		String host = "sql.j2webstudio.it";
		String port = "3306";
		String charset = "latin1";
		String dbname = "j2webstu85037";
		String username = "j2webstu85037";
		String password = "j2we20858";
		
		ListIterator<SchedaCliente> iterator = listaSchedeCliente.listIterator();
		while(iterator.hasNext()) {
			SchedaCliente schedaCorrente = iterator.next();

			//Costruisco la query sql
			String querySQL_1 = "SELECT * FROM autoveicoli WHERE ";
			String querySQL_2 = "(Marca = ";
			String querySQL_3 =  "'" + schedaCorrente.marcaVeicoloCliente + "'";
			String querySQL_4 = " AND ";
			String querySQL_5 = " Modello = ";
			String querySQL_6 = "'" + schedaCorrente.modelloVeicoloCliente + "')";
			String querySQL_7 = " OR ";
			String querySQL_8 = "(Marca = ";
			String querySQL_9 =  "'" + schedaCorrente.marcaVeicoloCliente + "'";
			String querySQL_10 = " AND ";
			String querySQL_11 = " Carburante = ";
			String querySQL_12 = "'" + schedaCorrente.tipologiaCarburanteVeicoloCliente + "')";
			String querySQL_13 = " OR ";
			String querySQL_14 = "(Versione = ";
			String querySQL_15 =  "'" + schedaCorrente.versioneVeicoloCliente + "')";

			String querySQL_16 = " OR ";
			String querySQL_17 = "(Marca = ";
			String querySQL_18 =  "'" + schedaCorrente.marcaVeicoloCliente + "'";
			String querySQL_19 = " AND ";
			String querySQL_20 = " ColoreEsterno = ";
			String querySQL_21 = "'" + schedaCorrente.coloreEsternoVeicoloCliente + "')";

			String querySQL_22 = " OR ";
			String querySQL_23 = "(Marca = ";
			String querySQL_24 =  "'" + schedaCorrente.marcaVeicoloCliente + "'";
			String querySQL_25 = " AND ";
			String querySQL_26 = " Tipologia = ";
			String querySQL_27 = "'" + schedaCorrente.tipologiaVeicoloCliente + "')";


			String querySQL = querySQL_1 + querySQL_2 + querySQL_3 + querySQL_4 + querySQL_5 + querySQL_6 + querySQL_7 + querySQL_8 + querySQL_9 + querySQL_10 + querySQL_11 + querySQL_12 + querySQL_13 + querySQL_14 + querySQL_15 + querySQL_16 + querySQL_17 + querySQL_18 + querySQL_19 + querySQL_20 + querySQL_21 + querySQL_22 + querySQL_23 + querySQL_24 + querySQL_25 + querySQL_26 + querySQL_27;
			String encodedQuerySQL = "";

			System.out.println("query: " + querySQL);

			//Encoding della query
			try {
				encodedQuerySQL = URLEncoder.encode(querySQL, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//Invio la richiesta al server remoto
			HttpPortalGetConnection getInfoVeicolo = new HttpPortalGetConnection();
			try {
				Object[] response = getInfoVeicolo.get("GET", urlHTTPTunnel + "?host=" + host + "&port=" + port + "&charset=" + charset + "&dbname=" + dbname + "&username=" + username + "&password=" + password + "&query=" + encodedQuerySQL, true);
				String responseBody = (String)response[1];
				JSONObject json = null;
				try {
					json = new JSONObject(responseBody);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
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