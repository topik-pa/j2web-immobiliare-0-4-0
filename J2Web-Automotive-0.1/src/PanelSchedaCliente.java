/**
 * Questa classe definisce tutti i sottopannelli schede cliente
 * @author marco - marcopavan.mp@gmail.com 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComponent;

class PanelSchedaCliente extends JPanel implements parametriGenerali{   

	private static final long serialVersionUID = 1L;

	SchedaCliente scheda;
	Long idScheda;
	String codiceScheda;
	JButton btnCancellaScheda;
	JButton btnEsportaScheda;
	JRadioButton schedaRadio;

	String labelSpaziatore = "   "; 

	//Costruttore
	public PanelSchedaCliente(final SchedaCliente scheda, final LinkedList<SchedaCliente> listaSchedeCliente, final ButtonGroup radioGrpSchede) {

		Component horizontalGlue = Box.createHorizontalGlue();

		this.scheda = scheda;
		idScheda = scheda.idSchedaCliente;
		codiceScheda = scheda.codiceSchedaCliente;

		setLayout(new BorderLayout(0, 0));
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setMaximumSize(new Dimension(500, 150));

		//Radio button dei sottopannelli
		schedaRadio = new JRadioButton("Seleziona scheda");
		//Le radio button devono appartenere allo stesso gruppo per funzionare correttamente
		radioGrpSchede.add(schedaRadio); 
		//Clicco su una radio button di una scheda
		schedaRadio.addActionListener(new ActionListener() {			 
			public void actionPerformed(ActionEvent e) {
				System.out.println("Scheda cliente selezionata: " + scheda.codiceSchedaCliente); 

				//La scheda selezionata ha un bordo arancione
				Component[] test = getParent().getComponents();
				for(int i=0; i<test.length; i++) {
					if(test[i].getClass().toString().contains("PanelSchedaCliente"))  {
						((JComponent) test[i]).setBorder(new LineBorder(Color.LIGHT_GRAY));
					}
				}
				setBorder(new LineBorder(Color.ORANGE));

				//Eseguo un match cliente-veicolo in locale
				matchClienteVeicolo(scheda);

				//I dati della scheda selezionata sono visualizzati nella form
				mostraDatiScheda(scheda);
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
		label.setHorizontalTextPosition(SwingConstants.LEFT);

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
		add(new JLabel(" ", humanIcon, JLabel.RIGHT),BorderLayout.EAST);

		//Aggiungo una tooltip
		setToolTipText(tooltipScheda);

		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_26, BorderLayout.SOUTH);
		panel_26.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//Pulsante "Cancella"
		btnCancellaScheda = new JButton("Elimina scheda");
		btnCancellaScheda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancella scheda cliente: " + scheda.codiceSchedaCliente);

				//Rimozione di una scheda immobile dalla LinkedList
				ListIterator<SchedaCliente> iterator = listaSchedeCliente.listIterator();
				while(iterator.hasNext()) {
					SchedaCliente schedaCorrente = iterator.next();
					//La rimozione avviene confrontando l'id univoco della scheda immobile
					if(schedaCorrente.idSchedaCliente==idScheda) {
						iterator.remove();
						System.out.println("Scheda cliente rimossa dalla linkedlist");
					}
				}

				//Aggiorno il file dat delle schede
				j2web.salvaListaSchedeClienteCreate();

				//Aggiornamento del pannello centrale, la scheda corrente è stata cancellata
				J2Web_UI.aggiornaPannelloListaSchedeCliente();

			}
		});
		panel_26.add(btnCancellaScheda);

		panel_26.add(horizontalGlue);     

	}

	//Funzione di matching tra clienti e veicoli salvati in locale
	private void  matchClienteVeicolo(SchedaCliente schedaCliente) {

		JPanel pannelloMatchClienteVeicolo = J2Web_UI.getPanel_6();

		J2Web_UI.listSchedeVeicoliMatch.clear();

		pannelloMatchClienteVeicolo.removeAll();

		boolean matchPositivo = false; 

		ListIterator<SchedaVeicolo> iterator = J2Web_UI.listSchedeVeicolo.listIterator();
		while(iterator.hasNext()) {
			SchedaVeicolo schedaCorrente = iterator.next();

			if(
					(schedaCliente.marcaVeicoloCliente!=null && schedaCliente.marcaVeicoloCliente.equalsIgnoreCase(schedaCorrente.marcaVeicolo) && (schedaCliente.modelloVeicoloCliente!=null && schedaCliente.modelloVeicoloCliente.equalsIgnoreCase(schedaCorrente.modelloVeicolo) )) ||
					(schedaCliente.versioneVeicoloCliente!=null && schedaCliente.versioneVeicoloCliente.equalsIgnoreCase(schedaCorrente.versioneVeicolo)) ||
					(schedaCliente.modelloVeicoloCliente.equalsIgnoreCase(schedaCorrente.modelloVeicolo) && (schedaCliente.tipologiaCarburanteVeicoloCliente.equalsIgnoreCase(schedaCorrente.carburanteVeicolo) )) ||
					(schedaCliente.modelloVeicoloCliente.equalsIgnoreCase(schedaCorrente.modelloVeicolo) && (schedaCliente.coloreEsternoVeicoloCliente.equalsIgnoreCase(schedaCorrente.coloreEsternoVeicolo) )) ||
					(schedaCliente.modelloVeicoloCliente.equalsIgnoreCase(schedaCorrente.modelloVeicolo) && (schedaCliente.tipologiaVeicoloCliente.equalsIgnoreCase(schedaCorrente.tipologiaVeicolo) )) ||
					(schedaCliente.tipologiaCarburanteVeicoloCliente.equalsIgnoreCase(schedaCorrente.carburanteVeicolo) && (schedaCliente.tipologiaVeicoloCliente.equalsIgnoreCase(schedaCorrente.tipologiaVeicolo) ))

					) {
				J2Web_UI.listSchedeVeicoliMatch.add(schedaCorrente);
			}

		}
		if(!J2Web_UI.listSchedeVeicoliMatch.isEmpty()){	
			matchPositivo = true;
		}

		if(!matchPositivo) {
			//Match negativo
			System.out.println("Non è stato trovato alcun veicolo interessante per il cliente.");
			JPanel panelNessunMatch = new JPanel();
			JLabel lblNessunMatch = new JLabel("Non è stato trovato alcun veicolo interessante per il cliente.");                
			panelNessunMatch.add(lblNessunMatch);
			pannelloMatchClienteVeicolo.add(panelNessunMatch);
		}    	
		else {
			//Match positivo
			System.out.println("Trovati potenziali veicoli interessanti per il cliente");

			String[][] matrix = new String[J2Web_UI.listSchedeVeicoliMatch.size()][5];

			for (int row = 0; row < matrix.length; row++) {
				matrix[row][0] = J2Web_UI.listSchedeVeicoliMatch.get(row).marcaVeicolo;
				matrix[row][1] = J2Web_UI.listSchedeVeicoliMatch.get(row).modelloVeicolo;
				matrix[row][2] = J2Web_UI.listSchedeVeicoliMatch.get(row).versioneVeicolo;
				matrix[row][3] = J2Web_UI.listSchedeVeicoliMatch.get(row).coloreEsternoVeicolo;
				matrix[row][4] = J2Web_UI.listSchedeVeicoliMatch.get(row).prezzoVeicolo;
			}

			JTable table = new JTable(matrix, new String[] {
					"Marca", "Modello", "Versione", "Colore", "Prezzo"
			});

			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setFillsViewportHeight(true);

			pannelloMatchClienteVeicolo.add(table.getTableHeader(), BorderLayout.PAGE_START);
			pannelloMatchClienteVeicolo.add(table, BorderLayout.CENTER);

		}
	}

	//Mostra sul pannello form i dati della scheda correntemente selezionata
	private void  mostraDatiScheda(SchedaCliente scheda) {

		J2Web_UI.disabilitaCampiForm(listCampiFormCliente);

		J2Web_UI.nonUserSelection = true;

		J2Web_UI.formCliente_getMarcaVeicolo().setSelectedIndex(scheda.marcaVeicoloClienteIndex);
		J2Web_UI.formCliente_getModelloVeicolo().setModel(new DefaultComboBoxModel<String>(new String[]{scheda.modelloVeicoloCliente}));
		J2Web_UI.formCliente_getVersioneVeicolo().setModel(new DefaultComboBoxModel<String>(new String[]{scheda.versioneVeicoloCliente}));
		J2Web_UI.formCliente_getTipologiaCarburanteVeicolo().setModel(new DefaultComboBoxModel<String>(new String[]{scheda.tipologiaCarburanteVeicoloCliente}));
		J2Web_UI.formCliente_getColoreVeicolo().setModel(new DefaultComboBoxModel<String>(new String[]{scheda.coloreEsternoVeicoloCliente}));
		J2Web_UI.formCliente_getTipologiaCliente().setModel(new DefaultComboBoxModel<String>(new String[]{scheda.tipologiaVeicoloCliente}));

		J2Web_UI.formCliente_getNome().setText(scheda.nomeCliente);
		J2Web_UI.formCliente_getCognome().setText(scheda.cognomeCliente);
		J2Web_UI.formCliente_getEmail().setText(scheda.emailCliente);
		J2Web_UI.formCliente_getTelefono1().setText(scheda.telefono1Cliente);
		J2Web_UI.formCliente_getTelefono2().setText(scheda.telefono2Cliente);
		J2Web_UI.formCliente_getVia().setText(scheda.viaCliente);
		J2Web_UI.formCliente_getNumeroCivico().setText(scheda.numeroCivicoCliente);
		J2Web_UI.formCliente_getCAP().setText(scheda.CAPCliente);
		J2Web_UI.formCliente_getCitta().setText(scheda.cittaCliente);	

		if(scheda.titoloCliente=="Sig.") {
			J2Web_UI.formCliente_getRdbtnSignore().setSelected(true);
		}
		else {
			J2Web_UI.formCliente_getRdbtnSignora().setSelected(true);
		}

		J2Web_UI.nonUserSelection = false;
	}

}