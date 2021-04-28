package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import classes.ProdajnoMjesto;
import database.Database;

public class DodajProdajnoMjesto {


	private JFrame inputFrameProdajnoMjesto;
	private JTextField textFieldTelefon;
	private JTextField textFieldAdresa;
	private JTextField textFieldDrzava;
	private JTextField textFieldGrad;

	public DodajProdajnoMjesto() {
		initialize();
		inputFrameProdajnoMjesto.setVisible(true);
	}


	private void initialize() {
		inputFrameProdajnoMjesto = new JFrame();
		inputFrameProdajnoMjesto.setSize(600,600);
		inputFrameProdajnoMjesto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputFrameProdajnoMjesto.getContentPane().setLayout(null);
		inputFrameProdajnoMjesto.setResizable(false);
		inputFrameProdajnoMjesto.setLocationRelativeTo(null);
		inputFrameProdajnoMjesto.setTitle("Unos Trgovca");
		inputFrameProdajnoMjesto.getContentPane().setBackground(Color.white);

		JPanel panelDodajProdajnoMjestoHeading = new JPanel();
		panelDodajProdajnoMjestoHeading.setBackground(new Color(0, 128, 128));
		panelDodajProdajnoMjestoHeading.setBounds(20, 40, 560, 70);
		inputFrameProdajnoMjesto.getContentPane().add(panelDodajProdajnoMjestoHeading);
		panelDodajProdajnoMjestoHeading.setLayout(new BorderLayout(0, 0));

		JLabel lblHeadingDodajProdajnoMjesto = new JLabel("Dodavanje Prodajnog Mjesta");
		lblHeadingDodajProdajnoMjesto.setVerticalAlignment(SwingConstants.BOTTOM);
		panelDodajProdajnoMjestoHeading.add(lblHeadingDodajProdajnoMjesto, BorderLayout.CENTER);
		lblHeadingDodajProdajnoMjesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadingDodajProdajnoMjesto.setForeground(Color.WHITE);
		lblHeadingDodajProdajnoMjesto.setFont(new Font("Candara", Font.BOLD, 35));
		lblHeadingDodajProdajnoMjesto.setBackground(Color.WHITE);



		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 121, 560, 435);
		inputFrameProdajnoMjesto.getContentPane().add(panel);
		panel.setLayout(null);

		textFieldTelefon = new JTextField();
		textFieldTelefon.setColumns(10);
		textFieldTelefon.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldTelefon.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldTelefon.setBounds(170, 270, 220, 33);
		panel.add(textFieldTelefon);

		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Candara", Font.BOLD, 20));
		lblTelefon.setBounds(170, 250, 220, 20);
		panel.add(lblTelefon);

		textFieldAdresa = new JTextField();
		textFieldAdresa.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldAdresa.setColumns(10);
		textFieldAdresa.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldAdresa.setBounds(170, 190, 220, 33);
		panel.add(textFieldAdresa);

		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setFont(new Font("Candara", Font.BOLD, 20));
		lblAdresa.setBounds(170, 170, 220, 20);
		panel.add(lblAdresa);

		textFieldDrzava = new JTextField();
		textFieldDrzava.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldDrzava.setColumns(10);
		textFieldDrzava.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldDrzava.setBounds(170, 120, 220, 33);
		panel.add(textFieldDrzava);

		JLabel lblDrzava = new JLabel("Drzava");
		lblDrzava.setFont(new Font("Candara", Font.BOLD, 20));
		lblDrzava.setBounds(170, 100, 220, 20);
		panel.add(lblDrzava);

		textFieldGrad = new JTextField();
		textFieldGrad.setFont(new Font("Arial", Font.PLAIN, 15));
		textFieldGrad.setColumns(10);
		textFieldGrad.setBorder(new LineBorder(new Color(51, 153, 153)));
		textFieldGrad.setBounds(170, 50, 220, 33);
		panel.add(textFieldGrad);

		JLabel lblGrad = new JLabel("Grad");
		lblGrad.setFont(new Font("Candara", Font.BOLD, 20));
		lblGrad.setBounds(170, 30, 200, 20);
		panel.add(lblGrad);

		@SuppressWarnings("unused")
		ButtonGroup btn_group_pol = new ButtonGroup();

		JPanel panelBtnDodaj = new JPanel();
		panelBtnDodaj.setLayout(null);
		panelBtnDodaj.setBorder(null);
		panelBtnDodaj.setBackground(new Color(0, 128, 128));
		panelBtnDodaj.setBounds(205, 330, 150, 50);
		panel.add(panelBtnDodaj);

		panelBtnDodaj.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 128, 128));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 100, 100));
				Object [] options = { "Da", "Ne" };
				if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da želite dodati prodajno mjesto?", "Otkazivanje",
										    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
										    options, options[0]) == JOptionPane.YES_OPTION) {

					if(textFieldGrad.getText().equals("") || textFieldDrzava.getText().equals("") || textFieldAdresa.getText().equals("") || textFieldTelefon.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Jedno ili više polja je prazno!", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
					dodajProdajnoMjesto(getIduciIDProdajnogMjesta(), textFieldGrad.getText(), textFieldDrzava.getText(), textFieldAdresa.getText(), textFieldTelefon.getText());
					ProdajnoMjesto.importProdajnaMjesta();
					}
                    
                    inputFrameProdajnoMjesto.dispose();
                    
				}
               
                    
               
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				panelBtnDodaj.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel lblDodaj = new JLabel("Dodaj");
		lblDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblDodaj.setForeground(Color.WHITE);
		lblDodaj.setFont(new Font("Candara", Font.BOLD, 25)); 
		lblDodaj.setBackground(new Color(0, 100, 0));
		lblDodaj.setBounds(0, 11, 150, 39);
		panelBtnDodaj.add(lblDodaj);
	}



	private int getIduciIDProdajnogMjesta() {
		int location = ProdajnoMjesto.getListaProdajnihMjesta().size()-1;
		ProdajnoMjesto posljednje = ProdajnoMjesto.getListaProdajnihMjesta().get(location);
		int id = posljednje.getId()+1;

		return id;
	}
	
	private void dodajProdajnoMjesto(int id, String grad, String drzava, String adresa, String telefon) {
        try {
            Database d = new Database();
            String query = " insert into prodajno_mjesto (id, grad, drzava, adresa, telefon)"
                    + " values (?, ?, ?, ?, ?)";
            @SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();
 
            PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
             preparedStmt.setInt(1, id);
             preparedStmt.setString (2, grad);
             preparedStmt.setString (3, drzava);
             preparedStmt.setString (4, adresa);
             preparedStmt.setString (5, telefon);
           
              // execute the preparedstatement
              preparedStmt.execute();
            
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        
    }
}

