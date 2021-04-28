package app;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;

import Util.ComboItem;
import classes.ProdajnoMjesto;
import classes.Trgovac;
import database.Database;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class DodajTrgovca {
	
	private JFrame inputFrameT;
	private JPasswordField passwordField;
	private JTextField textFieldPrezime;
	private JTextField textFieldIme;
	private JTextField textFieldKorisnickoIme;
	private JTextField textFieldTelefon;
	private JTextField textFieldEmail;
	JComboBox<ComboItem> comboBoxIDProdajnoMjesto;
	
	public DodajTrgovca() {
		initialize();
		inputFrameT.setVisible(true);
	}
	private void initialize() {
		inputFrameT = new JFrame();
		inputFrameT.setSize(600,600);
		inputFrameT.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inputFrameT.getContentPane().setLayout(null);
		inputFrameT.setResizable(false);
		inputFrameT.setLocationRelativeTo(null);
		inputFrameT.setTitle("Unos Trgovca");
		inputFrameT.getContentPane().setBackground(Color.white);
		
		JPanel panelMojProfilHeading = new JPanel();
		panelMojProfilHeading.setBackground(new Color(0, 128, 128));
		panelMojProfilHeading.setBounds(20, 40, 560, 70);
		inputFrameT.getContentPane().add(panelMojProfilHeading);
		panelMojProfilHeading.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUnosNovogTrgovca = new JLabel("Dodavanje Novog Trgovca");
		lblUnosNovogTrgovca.setVerticalAlignment(SwingConstants.BOTTOM);
		panelMojProfilHeading.add(lblUnosNovogTrgovca, BorderLayout.CENTER);
		lblUnosNovogTrgovca.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnosNovogTrgovca.setForeground(Color.WHITE);
		lblUnosNovogTrgovca.setFont(new Font("Candara", Font.BOLD, 35));
		lblUnosNovogTrgovca.setBackground(Color.WHITE);
		
		
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setBounds(20, 120, 560, 435);
	    inputFrameT.getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    passwordField = new JPasswordField();
	    passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
	    passwordField.setColumns(10);
	    passwordField.setBorder(new LineBorder(new Color(51, 153, 153)));
	    passwordField.setBounds(30, 270, 220, 33);
	    panel.add(passwordField);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Lozinka");
	    lblNewLabel_1_1.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_1_1.setBounds(30, 250, 220, 20);
	    panel.add(lblNewLabel_1_1);
	    
	    textFieldPrezime = new JTextField();
	    textFieldPrezime.setFont(new Font("Arial", Font.PLAIN, 15));
	    textFieldPrezime.setColumns(10);
	    textFieldPrezime.setBorder(new LineBorder(new Color(51, 153, 153)));
	    textFieldPrezime.setBounds(30, 190, 220, 33);
	    panel.add(textFieldPrezime);
	    
	    JLabel lblNewLabel_2 = new JLabel("Prezime");
	    lblNewLabel_2.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_2.setBounds(30, 170, 220, 20);
	    panel.add(lblNewLabel_2);
	    
	    textFieldIme = new JTextField();
	    textFieldIme.setFont(new Font("Arial", Font.PLAIN, 15));
	    textFieldIme.setColumns(10);
	    textFieldIme.setBorder(new LineBorder(new Color(51, 153, 153)));
	    textFieldIme.setBounds(30, 120, 220, 33);
	    panel.add(textFieldIme);
	    
	    JLabel lblNewLabel_1 = new JLabel("Ime");
	    lblNewLabel_1.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_1.setBounds(30, 100, 220, 20);
	    panel.add(lblNewLabel_1);
	    
	    textFieldKorisnickoIme = new JTextField();
	    textFieldKorisnickoIme.setFont(new Font("Arial", Font.PLAIN, 15));
	    textFieldKorisnickoIme.setColumns(10);
	    textFieldKorisnickoIme.setBorder(new LineBorder(new Color(51, 153, 153)));
	    textFieldKorisnickoIme.setBounds(30, 50, 220, 33);
	    panel.add(textFieldKorisnickoIme);
	    
	    JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime");
	    lblNewLabel.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel.setBounds(30, 30, 200, 20);
	    panel.add(lblNewLabel);
	    
	    JLabel lblNewLabel_3 = new JLabel("Telefon");
	    lblNewLabel_3.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_3.setBounds(310, 30, 200, 20);
	    panel.add(lblNewLabel_3);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("E-mail");
	    lblNewLabel_1_2.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_1_2.setBounds(310, 100, 200, 20);
	    panel.add(lblNewLabel_1_2);
	    
	    textFieldEmail = new JTextField();
	    textFieldEmail.setFont(new Font("Arial", Font.PLAIN, 15));
	    textFieldEmail.setColumns(10);
	    textFieldEmail.setBorder(new LineBorder(new Color(51, 153, 153)));
	    textFieldEmail.setBounds(310, 120, 220, 33);
	    panel.add(textFieldEmail);
	    
	    JLabel lblNewLabel_2_1 = new JLabel("Prodajno Mjesto");
	    lblNewLabel_2_1.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_2_1.setBounds(310, 172, 200, 20);
	    panel.add(lblNewLabel_2_1);
	    
	    JLabel lblNewLabel_1_1_1 = new JLabel("Pol");
	    lblNewLabel_1_1_1.setFont(new Font("Candara", Font.BOLD, 20));
	    lblNewLabel_1_1_1.setBounds(310, 250, 200, 20);
	    panel.add(lblNewLabel_1_1_1);
	    
	    JRadioButton rdbtn_reg_musko = new JRadioButton("Mu\u0161ko");
	    rdbtn_reg_musko.setSelected(true);
	    rdbtn_reg_musko.setFont(new Font("Arial", Font.PLAIN, 15));
	    rdbtn_reg_musko.setBackground(Color.WHITE);
	    rdbtn_reg_musko.setBounds(310, 270, 97, 33);
	    panel.add(rdbtn_reg_musko);
	    
	    JRadioButton rdbtn_reg_zensko = new JRadioButton("\u017Densko");
	    rdbtn_reg_zensko.setFont(new Font("Arial", Font.PLAIN, 15));
	    rdbtn_reg_zensko.setBackground(Color.WHITE);
	    rdbtn_reg_zensko.setBounds(409, 270, 97, 33);
	    panel.add(rdbtn_reg_zensko);
	    
	    ButtonGroup btn_group_pol = new ButtonGroup();
	    btn_group_pol.add(rdbtn_reg_zensko);
	    btn_group_pol.add(rdbtn_reg_musko);
	    
	    textFieldTelefon = new JTextField();
	    textFieldTelefon.setBounds(310, 50, 220, 33);
	    panel.add(textFieldTelefon);
	    textFieldTelefon.setFont(new Font("Arial", Font.PLAIN, 15));
	    textFieldTelefon.setColumns(10);
	    textFieldTelefon.setBorder(new LineBorder(new Color(51, 153, 153)));
	    
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
				String pol = "M";
				
				if(rdbtn_reg_zensko.isSelected()) {
					pol = "Ž";
				}
				
				if(textFieldKorisnickoIme.getText().equals("") || textFieldIme.getText().equals("") || textFieldPrezime.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("") ||  textFieldTelefon.getText().equals("") || textFieldEmail.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Jedno ili više polja je prazno!", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
				System.out.println("Novi Trgovac je: " + getIduciIDTrgovca() + " " + textFieldKorisnickoIme.getText() + " " + textFieldIme.getText() + " " + textFieldPrezime.getText() + " " + String.valueOf(passwordField.getPassword()) + " " + pol + " " + textFieldTelefon.getText() + " " + textFieldEmail.getText() + " " + comboBoxIDProdajnoMjesto.getSelectedItem());
				dodajTrgovca(getIduciIDTrgovca(), textFieldKorisnickoIme.getText(), textFieldIme.getText(), textFieldPrezime.getText(), String.valueOf(passwordField.getPassword()), pol, textFieldTelefon.getText(), textFieldEmail.getText(), (ComboItem)comboBoxIDProdajnoMjesto.getSelectedItem());
				Trgovac.importTrgovce();
				inputFrameT.dispose();
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
	    
	    comboBoxIDProdajnoMjesto = new JComboBox<ComboItem>();
	    comboBoxIDProdajnoMjesto.setFont(new Font("Arial", Font.PLAIN, 15));
	    comboBoxIDProdajnoMjesto.setBackground(Color.WHITE);
	    comboBoxIDProdajnoMjesto.setBounds(310, 201, 220, 35);
	    fillComboBox();
	    panel.add(comboBoxIDProdajnoMjesto);
	}
	
	 private static int getIduciIDTrgovca() {
		 	int location = Trgovac.getListaTrgovaca().size()-1;
	        Trgovac posljednji = Trgovac.getListaTrgovaca().get(location);
	        int id = posljednji.getId()+1;
	        
	        return id;
	    }
	

	private void fillComboBox() {
		for(ProdajnoMjesto p: ProdajnoMjesto.getListaProdajnihMjesta())
			comboBoxIDProdajnoMjesto.addItem(new ComboItem(p.getId(), (p.getAdresa() + ", " + p.getGrad() + ", " + p.getDrzava())));
			
	}
	
	private void dodajTrgovca(int id, String korisnicko_ime, String ime, String prezime, String lozinka, String pol, String telefon, 
            String email, ComboItem comboitem) {
		int prodajno_mjesto_id = comboitem.getKey();
		
        try {
            Database d = new Database();
            String query = " insert into trgovac (id, korisnicko_ime, ime, prezime, lozinka, pol, telefon, email, prodajno_mjesto_id)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            @SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();
 
            PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
             preparedStmt.setInt(1, id);
             preparedStmt.setString (2, korisnicko_ime );
             preparedStmt.setString (3, ime);
             preparedStmt.setString (4, prezime);
             preparedStmt.setString (5, lozinka);
             preparedStmt.setString (6, pol);
             preparedStmt.setString(7, telefon);
             preparedStmt.setString(8, email);
             preparedStmt.setInt(9, prodajno_mjesto_id);
           
              // execute the preparedstatement
              preparedStmt.execute();
            
        }
        catch (SQLException e) {
            System.err.println(e);
        }
        
    }
}
