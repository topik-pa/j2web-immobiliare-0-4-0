import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
//import java.util.NoSuchElementException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
//import javax.swing.border.TitledBorder;
import javax.swing.JComponent;

import org.json.JSONArray;
import org.json.JSONObject;

class PanelSchedaClienteMLS extends JPanel implements parametriGenerali {   
	//JPanel pannelloListaPortali = J2Web_UI.getPanel_10();
	
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
               System.out.println("Scheda selezionata: " + scheda.marcaVeicoloCliente + " per MLS..."); 
               
               JPanel pannelloInfoVeicoloMLS = J2Web_UI.getPanel_8();
               pannelloInfoVeicoloMLS.removeAll();
               pannelloInfoVeicoloMLS.updateUI();
               
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
		 String labelScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente + "-" + scheda.telefono2Cliente + "-" + scheda.emailCliente;
		 if(labelScheda.length()>60) {	//è molto probabile che lo sia... :)
			 labelScheda = labelScheda.substring(0, 60); 
		 }		 
		 labelScheda+="...";
		 JLabel label = new JLabel(labelScheda);
		 Font font = new Font("Monospaced", Font.PLAIN, 11);
		 label.setFont(font);
		 label.setHorizontalTextPosition(SwingConstants.RIGHT);		 
		 
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
		 
		 //Aggiungo una tooltip
		 String tooltipScheda = scheda.nomeCliente + "-" + scheda.cognomeCliente + "-" + scheda.telefono1Cliente + "-" + scheda.emailCliente;
		 setToolTipText(tooltipScheda);
		 		 
		 //add(new JLabel(labelSpaziatore));
		 
       
       
	 }
	 
	 private void  matchClienteVeicoloMLS(SchedaCliente schedaCliente) {
		 
		 JPanel pannelloMatchClienteVeicoloMLS = J2Web_UI.getPanel_15();
		 
		 pannelloMatchClienteVeicoloMLS.removeAll();
		 
		 pannelloMatchClienteVeicoloMLS.add(Box.createVerticalStrut(6));
		 
		String host = "sql.j2webstudio.it";
		String port = "3306";
		String charset = "latin1";
		String dbname = "j2webstu85037";
		String username = "j2webstu85037";
		String password = "j2we20858";
		//String query;
		
		
		//Costruisco la query sql
        String querySQL_1 = "SELECT * FROM autoveicoli WHERE ";
        String querySQL_2 = "Marca = ";
        String querySQL_3 =  "'" + scheda.marcaVeicoloCliente + "'";
        
        //String querySQL_2_normalized = querySQL_2.replace(" ", "");
        //String querySQL_3_normalized = querySQL_4.replace("'", "\'");
        String querySQL = querySQL_1 + querySQL_2 + querySQL_3;
        String encodedQuerySQL = "";
        
        System.out.println("query: " + querySQL);
        
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
        	//JSONObject json2 = null;
			try {
				json = new JSONObject(responseBody);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	//System.out.println("test1:" + json.getString("affectedrows"));
        	
        	JSONArray jsonResults = json.getJSONArray("rows"); 
        	
        	//System.out.println("test1:" + jsonResults + "-" + jsonResults.length());
        	
        	for(int i=0; i<jsonResults.length(); i++) {
        		JSONArray currentJsonArray = jsonResults.getJSONArray(i); //rappresenta una riga della tabella
        		//System.out.println("test2:" + currentJsonArray);
        		
        		SchedaVeicolo schedaVeicoloMLS = new SchedaVeicolo(currentJsonArray);
        		
        		PanelSchedaVeicoloMLS panelSchedaVeicoloMLS = new PanelSchedaVeicoloMLS(schedaVeicoloMLS);
                
                pannelloMatchClienteVeicoloMLS.add(panelSchedaVeicoloMLS);
        	}
        	
        	
        	//for(int i=1; i<=jsonResults.length(); i++) {
        		//JSONArray currentJsonArray = jsonResults.getJSONArray(i);
        		
        		//System.out.println("test2:" + currentJsonArray.getString(i));
        		
        		//System.out.println("test1:" + currentJsonArray);
        		//if(i==0){
    				/*try {
						json2 = new JSONObject(currentJsonArray);
						//System.out.println("test1:" + json2);
						//System.out.println("test2:" + json2.getString(Integer.toString(i)));
						//System.out.println("test5:" + json2.getString(2));
						//System.out.println("test6:" + json2.getString("3"));
						
					} catch (NoSuchElementException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
    				//System.out.println("test3:" + json2.getString("2"));
    			//}
        		
        		/*for (int h=1; h<=currentJsonArray.length(); h++) {
        			if(h==1){
        				JSONObject currentJson = currentJsonArray.getJSONObject(1);
        				System.out.println("test3:" + currentJson.getInt("1"));
        			}
        		
        		}*/
        		
        		
        	//}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		//SchedaVeicolo schedaVeicoloMLS = new SchedaVeicolo(rs);
        
        //PanelSchedaVeicoloMLS panelSchedaVeicoloMLS = new PanelSchedaVeicoloMLS(schedaVeicoloMLS);
        
        //pannelloMatchClienteVeicoloMLS.add(panelSchedaVeicoloMLS);
	        
	        
	    pannelloMatchClienteVeicoloMLS.updateUI();
	    
	   
		 
		 
	 }

}