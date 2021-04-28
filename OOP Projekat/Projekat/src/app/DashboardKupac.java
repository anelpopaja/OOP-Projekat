package app;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import Util.ArtikalZaNarudzbu;
import java.util.Date;

import javax.swing.ListSelectionModel;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import classes.ArtikalNarudzbe;
import classes.Kupac;
import classes.Narudzba;
import classes.Proizvod;
import database.Database;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DashboardKupac{
	JLayeredPane layeredPane;
	private JFrame frame;
	private static Kupac trenutniKupac;
	private static ArrayList<Narudzba> narudzbeKupca;
	private static JTable table_narudzbe;
	private JTable tableSviArtikli;
	private JTable tableMojaNarudzba;

	private int id;
	private double cijena;
	private String naziv;
	@SuppressWarnings("unused")
	private ArtikalZaNarudzbu trenutniArtikal;
	private ArrayList<ArtikalZaNarudzbu> listaArtikalaZaNarudzbu;

	public DashboardKupac() {
		initialize();
		frame.setVisible(true);
	}

	//pomocne funkcije 

	private static int brojIsporucenihNarudzbi() {

		int br_isporucenihN = 0;
		for(Narudzba n: narudzbeKupca) {
			if(n.getDatumIsporuke()!=null) 
				br_isporucenihN++;
		}
		return br_isporucenihN;
	}
	public static ArrayList<Narudzba> getNarudzbeKupca() {
		return narudzbeKupca;
	}

	public static void setNarudzbeKupca(ArrayList<Narudzba> listaNarudzbiKupca) {
		narudzbeKupca = listaNarudzbiKupca;

	}

	private static int brojNarudzbiNaCekanju() {

		int br_cekanihN = 0;
		for(Narudzba n: narudzbeKupca) {
			if(n.getDatumIsporuke()==null) 
				br_cekanihN++;
		}
		return br_cekanihN;
	}
	private static double vrijednostNarudzbiNaCekanju() {
		double vrijednost = 0;
		for(Narudzba n : narudzbeKupca) {
			if(n.getDatumIsporuke() == null) {
				for(ArtikalNarudzbe stavka : ArtikalNarudzbe.getListaArtikalaNarudzbe()) {
					if(stavka.getNarudzbaId() == n.getId())
						vrijednost += stavka.getCijenaPoKomadu()*stavka.getKolicina();
				}
			}
		}

		return vrijednost;
	}


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBackground(new Color(0, 153, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Anel\\Downloads\\shopify-bag.png"));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		trenutniKupac = LoginRegisterForm.getTrenutniKupac();
		System.out.println("Trenutni Kupac: " + trenutniKupac);
		narudzbeKupca = trenutniKupac.getListaNarudzbiKupca();

		listaArtikalaZaNarudzbu = new ArrayList<ArtikalZaNarudzbu>();




		//Right Panel

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(299, 0, 885, 661);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		JPanel panelMojProfil = new JPanel();
		panelMojProfil.setBackground(new Color(255, 255, 255));
		panelMojProfil.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		layeredPane.add(panelMojProfil, "name_78027238592000");
		panelMojProfil.setLayout(null);

		JPanel panelMojProfilPodaci = new JPanel();
		panelMojProfilPodaci.setBounds(50, 200, 400, 420);
		panelMojProfil.add(panelMojProfilPodaci);
		panelMojProfilPodaci.setLayout(null);

		JLabel labelPodaci1 = new JLabel("Korisnicko ime:");
		labelPodaci1.setBounds(10, 10, 160, 35);
		panelMojProfilPodaci.add(labelPodaci1);
		labelPodaci1.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci1.setForeground(new Color(0, 0, 0));
		labelPodaci1.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci1.setBackground(Color.WHITE);

		JLabel labelPodaci2 = new JLabel("Ime:");
		labelPodaci2.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci2.setForeground(Color.BLACK);
		labelPodaci2.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci2.setBackground(Color.WHITE);
		labelPodaci2.setBounds(10, 56, 160, 35);
		panelMojProfilPodaci.add(labelPodaci2);

		JLabel labelPodaci3 = new JLabel("Prezime:");
		labelPodaci3.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci3.setForeground(Color.BLACK);
		labelPodaci3.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci3.setBackground(Color.WHITE);
		labelPodaci3.setBounds(10, 102, 160, 35);
		panelMojProfilPodaci.add(labelPodaci3);

		JLabel labelPodaci4 = new JLabel("Pol:");
		labelPodaci4.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci4.setForeground(Color.BLACK);
		labelPodaci4.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci4.setBackground(Color.WHITE);
		labelPodaci4.setBounds(10, 148, 160, 35);
		panelMojProfilPodaci.add(labelPodaci4);

		JLabel labelPodaci5 = new JLabel("Broj Telefona");
		labelPodaci5.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci5.setForeground(Color.BLACK);
		labelPodaci5.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci5.setBackground(Color.WHITE);
		labelPodaci5.setBounds(10, 194, 160, 35);
		panelMojProfilPodaci.add(labelPodaci5);

		JLabel labelPodaci6 = new JLabel("E-Mail:");
		labelPodaci6.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci6.setForeground(Color.BLACK);
		labelPodaci6.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci6.setBackground(Color.WHITE);
		labelPodaci6.setBounds(10, 240, 160, 35);
		panelMojProfilPodaci.add(labelPodaci6);

		JLabel labelPodaci7 = new JLabel("Adresa:");
		labelPodaci7.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci7.setForeground(Color.BLACK);
		labelPodaci7.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci7.setBackground(Color.WHITE);
		labelPodaci7.setBounds(10, 286, 160, 35);
		panelMojProfilPodaci.add(labelPodaci7);

		JLabel labelPodaci8 = new JLabel("Grad:");
		labelPodaci8.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci8.setForeground(Color.BLACK);
		labelPodaci8.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci8.setBackground(Color.WHITE);
		labelPodaci8.setBounds(10, 332, 160, 35);
		panelMojProfilPodaci.add(labelPodaci8);

		JLabel labelPodaci9 = new JLabel("Dr\u017Eava:");
		labelPodaci9.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaci9.setForeground(Color.BLACK);
		labelPodaci9.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaci9.setBackground(Color.WHITE);
		labelPodaci9.setBounds(10, 378, 160, 35);
		panelMojProfilPodaci.add(labelPodaci9);

		JLabel labelPodaciKIme = new JLabel("");
		labelPodaciKIme.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciKIme.setForeground(Color.BLACK);
		labelPodaciKIme.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciKIme.setBackground(Color.WHITE);
		labelPodaciKIme.setBounds(155, 10, 210, 35);
		labelPodaciKIme.setText(trenutniKupac.getKorisnicko_ime());
		panelMojProfilPodaci.add(labelPodaciKIme);

		JLabel labelPodaciIme = new JLabel("");
		labelPodaciIme.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciIme.setForeground(Color.BLACK);
		labelPodaciIme.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciIme.setBackground(Color.WHITE);
		labelPodaciIme.setBounds(155, 56, 210, 35);
		labelPodaciIme.setText(trenutniKupac.getIme());
		panelMojProfilPodaci.add(labelPodaciIme);

		JLabel labelPodaciPrezime = new JLabel("");
		labelPodaciPrezime.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciPrezime.setForeground(Color.BLACK);
		labelPodaciPrezime.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciPrezime.setBackground(Color.WHITE);
		labelPodaciPrezime.setBounds(155, 102, 210, 35);
		labelPodaciPrezime.setText(trenutniKupac.getPrezime());
		panelMojProfilPodaci.add(labelPodaciPrezime);

		JLabel labelPodaciPol = new JLabel("");
		labelPodaciPol.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciPol.setForeground(Color.BLACK);
		labelPodaciPol.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciPol.setBackground(Color.WHITE);
		labelPodaciPol.setBounds(155, 148, 210, 35);
		if(trenutniKupac.getPol().equals("M"))
			labelPodaciPol.setText("Muški");
		else
			labelPodaciPol.setText("Ženski");
		panelMojProfilPodaci.add(labelPodaciPol);

		JLabel labelPodaciBrTel = new JLabel("");
		labelPodaciBrTel.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciBrTel.setForeground(Color.BLACK);
		labelPodaciBrTel.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciBrTel.setBackground(Color.WHITE);
		labelPodaciBrTel.setBounds(155, 194, 210, 35);
		labelPodaciBrTel.setText(trenutniKupac.getTelefon());
		panelMojProfilPodaci.add(labelPodaciBrTel);

		JLabel labelPodaciEmail = new JLabel("");
		labelPodaciEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciEmail.setForeground(Color.BLACK);
		labelPodaciEmail.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciEmail.setBackground(Color.WHITE);
		labelPodaciEmail.setBounds(155, 240, 235, 35);
		labelPodaciEmail.setText(trenutniKupac.getEmail());
		panelMojProfilPodaci.add(labelPodaciEmail);

		JLabel labelPodaciAdresa = new JLabel("");
		labelPodaciAdresa.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciAdresa.setForeground(Color.BLACK);
		labelPodaciAdresa.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciAdresa.setBackground(Color.WHITE);
		labelPodaciAdresa.setBounds(155, 286, 210, 35);
		labelPodaciAdresa.setText(trenutniKupac.getAdresa());
		panelMojProfilPodaci.add(labelPodaciAdresa);

		JLabel labelPodaciGrad = new JLabel("");
		labelPodaciGrad.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciGrad.setForeground(Color.BLACK);
		labelPodaciGrad.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciGrad.setBackground(Color.WHITE);
		labelPodaciGrad.setBounds(155, 332, 210, 35);
		labelPodaciGrad.setText(trenutniKupac.getGrad());
		panelMojProfilPodaci.add(labelPodaciGrad);

		JLabel labelPodaciDržava = new JLabel("");
		labelPodaciDržava.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciDržava.setForeground(Color.BLACK);
		labelPodaciDržava.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciDržava.setBackground(Color.WHITE);
		labelPodaciDržava.setBounds(155, 378, 210, 35);
		labelPodaciDržava.setText(trenutniKupac.getDrzava());
		panelMojProfilPodaci.add(labelPodaciDržava);

		JPanel panelMojProfilNarudzbe = new JPanel();
		panelMojProfilNarudzbe.setBounds(500, 200, 350, 284);
		panelMojProfil.add(panelMojProfilNarudzbe);
		panelMojProfilNarudzbe.setLayout(null);

		JLabel labelNarudzbe1 = new JLabel("Broj obavljenih narud\u017Ebi: ");
		labelNarudzbe1.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe1.setForeground(Color.BLACK);
		labelNarudzbe1.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe1.setBackground(Color.WHITE);
		labelNarudzbe1.setBounds(10, 10, 330, 35);
		panelMojProfilNarudzbe.add(labelNarudzbe1);

		JLabel labelNarudzbe2 = new JLabel("Broj narud\u017Ebi koje se \u010Dekaju:");
		labelNarudzbe2.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe2.setForeground(Color.BLACK);
		labelNarudzbe2.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe2.setBackground(Color.WHITE);
		labelNarudzbe2.setBounds(10, 102, 330, 35);
		panelMojProfilNarudzbe.add(labelNarudzbe2);

		JLabel labelNarudzbe3 = new JLabel("Vrijednost narud\u017Ebi koje se \u010Dekaju:");
		labelNarudzbe3.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe3.setForeground(Color.BLACK);
		labelNarudzbe3.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe3.setBackground(Color.WHITE);
		labelNarudzbe3.setBounds(10, 194, 330, 35);
		panelMojProfilNarudzbe.add(labelNarudzbe3);

		JLabel labelNarudzbe1_1 = new JLabel("");
		labelNarudzbe1_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe1_1.setForeground(Color.BLACK);
		labelNarudzbe1_1.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe1_1.setBackground(Color.WHITE);
		labelNarudzbe1_1.setBounds(10, 56, 330, 35);
		labelNarudzbe1_1.setText(brojIsporucenihNarudzbi() + "");
		panelMojProfilNarudzbe.add(labelNarudzbe1_1);

		JLabel labelNarudzbe2_1 = new JLabel("");
		labelNarudzbe2_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe2_1.setForeground(Color.BLACK);
		labelNarudzbe2_1.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe2_1.setBackground(Color.WHITE);
		labelNarudzbe2_1.setBounds(10, 148, 330, 35);
		labelNarudzbe2_1.setText(brojNarudzbiNaCekanju() + "");
		panelMojProfilNarudzbe.add(labelNarudzbe2_1);

		JLabel labelNarudzbe3_1 = new JLabel("");
		labelNarudzbe3_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe3_1.setForeground(Color.BLACK);
		labelNarudzbe3_1.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe3_1.setBackground(Color.WHITE);
		labelNarudzbe3_1.setBounds(10, 238, 330, 35);
		labelNarudzbe3_1.setText(vrijednostNarudzbiNaCekanju() + " KM");
		panelMojProfilNarudzbe.add(labelNarudzbe3_1);

		JPanel panelMojProfilPodaciTitle = new JPanel();
		panelMojProfilPodaciTitle.setBackground(new Color(0, 128, 128));
		panelMojProfilPodaciTitle.setBounds(70, 164, 350, 30);
		panelMojProfil.add(panelMojProfilPodaciTitle);

		JLabel labelPodaciTitle = new JLabel("Moji Podaci");
		panelMojProfilPodaciTitle.add(labelPodaciTitle);
		labelPodaciTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelPodaciTitle.setForeground(new Color(255, 255, 255));
		labelPodaciTitle.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciTitle.setBackground(new Color(0, 128, 128));

		JPanel panelMojProfilHeading = new JPanel();
		panelMojProfilHeading.setBackground(new Color(0, 128, 128));
		panelMojProfilHeading.setBounds(162, 35, 550, 70);
		panelMojProfil.add(panelMojProfilHeading);
		panelMojProfilHeading.setLayout(new BorderLayout(0, 0));

		JLabel labelPanelHeading = new JLabel("MOJ PROFIL");
		labelPanelHeading.setVerticalAlignment(SwingConstants.BOTTOM);
		labelPanelHeading.setFont(new Font("Candara", Font.BOLD, 35));
		labelPanelHeading.setForeground(new Color(255, 255, 255));
		labelPanelHeading.setBackground(new Color(255, 255, 255));
		labelPanelHeading.setHorizontalAlignment(SwingConstants.CENTER);
		panelMojProfilHeading.add(labelPanelHeading);

		JPanel panelMojProfilNarudzbeTitle = new JPanel();
		panelMojProfilNarudzbeTitle.setBackground(new Color(0, 128, 128));
		panelMojProfilNarudzbeTitle.setBounds(520, 164, 310, 30);
		panelMojProfil.add(panelMojProfilNarudzbeTitle);

		JLabel labelNarudzbeTitle = new JLabel("Narudzbe");
		labelNarudzbeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelNarudzbeTitle.setForeground(Color.WHITE);
		labelNarudzbeTitle.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbeTitle.setBackground(new Color(0, 128, 128));
		panelMojProfilNarudzbeTitle.add(labelNarudzbeTitle);

		//Moje narudzbe

		JPanel panelMojeNarudzbe = new JPanel();
		panelMojeNarudzbe.setBackground(Color.WHITE);
		panelMojeNarudzbe.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		layeredPane.add(panelMojeNarudzbe, "name_78027246231700");
		panelMojeNarudzbe.setLayout(null);

		JPanel panelMojeNarudzbeHeading = new JPanel();
		panelMojeNarudzbeHeading.setBackground(new Color(0, 128, 128));
		panelMojeNarudzbeHeading.setBounds(162, 35, 551, 70);
		panelMojeNarudzbe.add(panelMojeNarudzbeHeading);
		panelMojeNarudzbeHeading.setLayout(new BorderLayout(0, 0));

		JLabel lblNarudbe = new JLabel("NARUD\u017DBE");
		lblNarudbe.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNarudbe.setHorizontalAlignment(SwingConstants.CENTER);
		lblNarudbe.setForeground(Color.WHITE);
		lblNarudbe.setFont(new Font("Candara", Font.BOLD, 35));
		lblNarudbe.setBackground(Color.WHITE);
		panelMojeNarudzbeHeading.add(lblNarudbe, BorderLayout.CENTER);

		JPanel panelMojeNarudzbeButton1 = new JPanel();
		panelMojeNarudzbeButton1.setLayout(null);
		panelMojeNarudzbeButton1.setBorder(null);
		panelMojeNarudzbeButton1.setBackground(new Color(0, 128, 128));
		panelMojeNarudzbeButton1.setBounds(120, 183, 300, 45);
		panelMojeNarudzbe.add(panelMojeNarudzbeButton1);


		panelMojeNarudzbeButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelMojeNarudzbeButton1.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(panelMojeNarudzbeButton1.getBackground() != new Color(0, 100, 100))
					panelMojeNarudzbeButton1.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelMojeNarudzbeButton1.setBackground(new Color(0, 100, 100));
				formTable(0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelMojeNarudzbeButton1.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel labelSveNarudzbe = new JLabel("Sve Narudzbe");
		labelSveNarudzbe.setHorizontalAlignment(SwingConstants.CENTER);
		labelSveNarudzbe.setForeground(Color.WHITE);
		labelSveNarudzbe.setFont(new Font("Candara", Font.BOLD, 25));
		labelSveNarudzbe.setBackground(new Color(0, 100, 0));
		labelSveNarudzbe.setBounds(50, 11, 200, 32);
		panelMojeNarudzbeButton1.add(labelSveNarudzbe);

		JPanel panelMojeNarudzbeButton2 = new JPanel();
		panelMojeNarudzbeButton2.setLayout(null);
		panelMojeNarudzbeButton2.setBorder(null);
		panelMojeNarudzbeButton2.setBackground(new Color(0, 128, 128));
		panelMojeNarudzbeButton2.setBounds(470, 183, 300, 45);
		panelMojeNarudzbe.add(panelMojeNarudzbeButton2);

		panelMojeNarudzbeButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelMojeNarudzbeButton2.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(panelMojeNarudzbeButton2.getBackground() != new Color(0, 100, 100))
					panelMojeNarudzbeButton2.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelMojeNarudzbeButton2.setBackground(new Color(0, 100, 100));
				formTable(1);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelMojeNarudzbeButton2.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel labeNeisporuceneNarudzbe = new JLabel("Neisporucene Narudzbe");
		labeNeisporuceneNarudzbe.setHorizontalAlignment(SwingConstants.CENTER);
		labeNeisporuceneNarudzbe.setForeground(Color.WHITE);
		labeNeisporuceneNarudzbe.setFont(new Font("Candara", Font.BOLD, 25));
		labeNeisporuceneNarudzbe.setBackground(new Color(0, 100, 0));
		labeNeisporuceneNarudzbe.setBounds(10, 11, 280, 32);
		panelMojeNarudzbeButton2.add(labeNeisporuceneNarudzbe);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 261, 690, 349);
		panelMojeNarudzbe.add(scrollPane);

		table_narudzbe = new JTable();

		scrollPane.setViewportView(table_narudzbe);
		table_narudzbe.setRowHeight(30);
		table_narudzbe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_narudzbe.getTableHeader().setResizingAllowed(false);
		table_narudzbe.getTableHeader().setReorderingAllowed(false);
		table_narudzbe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openDetaljiNarudzbe();
			}
		});
		formTable(0);


		//Panel kupovina

		JPanel panelKupovina = new JPanel();
		panelKupovina.setBackground(Color.WHITE);
		panelKupovina.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		layeredPane.add(panelKupovina, "name_78027254452000");
		panelKupovina.setLayout(null);

		JPanel panelKupovinaHeading = new JPanel();
		panelKupovinaHeading.setBackground(new Color(0, 128, 128));
		panelKupovinaHeading.setBounds(162, 35, 551, 70);
		panelKupovina.add(panelKupovinaHeading);
		panelKupovinaHeading.setLayout(new BorderLayout(0, 0));

		JLabel lblKupovina = new JLabel("KUPOVINA");
		lblKupovina.setVerticalAlignment(SwingConstants.BOTTOM);
		lblKupovina.setHorizontalAlignment(SwingConstants.CENTER);
		lblKupovina.setForeground(Color.WHITE);
		lblKupovina.setFont(new Font("Candara", Font.BOLD, 35));
		lblKupovina.setBackground(Color.WHITE);
		panelKupovinaHeading.add(lblKupovina, BorderLayout.CENTER);

		JPanel panelKupovinaSviArtikli = new JPanel();
		panelKupovinaSviArtikli.setBackground(new Color(0, 128, 128));
		panelKupovinaSviArtikli.setBounds(60, 164, 350, 30);
		panelKupovina.add(panelKupovinaSviArtikli);

		JLabel lblSviArtikli = new JLabel("Svi Artikli");
		lblSviArtikli.setHorizontalAlignment(SwingConstants.CENTER);
		lblSviArtikli.setForeground(Color.WHITE);
		lblSviArtikli.setFont(new Font("Candara", Font.BOLD, 20));
		lblSviArtikli.setBackground(new Color(0, 128, 128));
		panelKupovinaSviArtikli.add(lblSviArtikli);

		JPanel panelKupovinaMojaNarudzba = new JPanel();
		panelKupovinaMojaNarudzba.setBackground(new Color(0, 128, 128));
		panelKupovinaMojaNarudzba.setBounds(520, 164, 350, 30);
		panelKupovina.add(panelKupovinaMojaNarudzba);

		JLabel lblMojaNarudzba = new JLabel("Moja Naru\u017Eba");
		lblMojaNarudzba.setHorizontalAlignment(SwingConstants.CENTER);
		lblMojaNarudzba.setForeground(Color.WHITE);
		lblMojaNarudzba.setFont(new Font("Candara", Font.BOLD, 20));
		lblMojaNarudzba.setBackground(new Color(0, 128, 128));
		panelKupovinaMojaNarudzba.add(lblMojaNarudzba);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(60, 235, 340, 325);
		panelKupovina.add(scrollPane1);

		tableSviArtikli = new JTable();

		scrollPane1.setViewportView(tableSviArtikli);
		tableSviArtikli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSviArtikli.getTableHeader().setResizingAllowed(false);
		tableSviArtikli.getTableHeader().setReorderingAllowed(false);
		tableSviArtikli.setVisible(true);

		if(!(tableSviArtikli.getModel().getRowCount() > 0))
			popunitiTabeluArtikala();

		tableSviArtikli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.parseInt(tableSviArtikli.getValueAt(tableSviArtikli.getSelectedRow(), 0).toString());
				naziv = tableSviArtikli.getValueAt(tableSviArtikli.getSelectedRow(), 1).toString();
				cijena = Double.parseDouble(tableSviArtikli.getValueAt(tableSviArtikli.getSelectedRow(), 2).toString());

			}
		});

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(520, 235, 340, 325);
		panelKupovina.add(scrollPane2);

		tableMojaNarudzba = new JTable();
		scrollPane2.setViewportView(tableMojaNarudzba);
		tableMojaNarudzba.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMojaNarudzba.getTableHeader().setResizingAllowed(false);
		tableMojaNarudzba.getTableHeader().setReorderingAllowed(false);
		tableMojaNarudzba.setVisible(true);
		tableMojaNarudzba.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.parseInt(tableMojaNarudzba.getValueAt(tableMojaNarudzba.getSelectedRow(), 0).toString());
				naziv = tableMojaNarudzba.getValueAt(tableMojaNarudzba.getSelectedRow(), 1).toString();
				cijena = Double.parseDouble(tableMojaNarudzba.getValueAt(tableMojaNarudzba.getSelectedRow(), 2).toString());

			}
		});

		JPanel panelKupovinaBtn1 = new JPanel();
		panelKupovinaBtn1.setBorder(null);
		panelKupovinaBtn1.setBackground(new Color(0, 128, 128));
		panelKupovinaBtn1.setBounds(410, 335, 100, 40);
		panelKupovina.add(panelKupovinaBtn1);
		panelKupovinaBtn1.setLayout(new BorderLayout(0, 0));

		panelKupovinaBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelKupovinaBtn1.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(panelKupovinaBtn1.getBackground() != new Color(0, 100, 100))
					panelKupovinaBtn1.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelKupovinaBtn1.setBackground(new Color(0, 100, 100));
				ArtikalZaNarudzbu a1 = new ArtikalZaNarudzbu(id, naziv, cijena);
				boolean uslov = false;
				for(ArtikalZaNarudzbu temp : listaArtikalaZaNarudzbu)
					if(temp.compareTo(a1) == 1) {
						uslov = true;
						System.out.println("Lista sadrzi artikal "+ a1);
						int vrijednost = temp.getKolicina();
						temp.setKolicina(vrijednost+1);
						break;
					}
				if(!uslov && a1.getId() != 0) {
					listaArtikalaZaNarudzbu.add(a1);
				}

				popuniTabeluNarudzbi(listaArtikalaZaNarudzbu);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelKupovinaBtn1.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel lblDodaj = new JLabel("Dodaj >>");
		lblDodaj.setVerticalAlignment(SwingConstants.BOTTOM);
		panelKupovinaBtn1.add(lblDodaj);
		lblDodaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblDodaj.setForeground(Color.WHITE);
		lblDodaj.setFont(new Font("Candara", Font.BOLD, 25));
		lblDodaj.setBackground(new Color(0, 100, 0));



		JPanel paneKupovinaBtn2 = new JPanel();
		paneKupovinaBtn2.setBorder(null);
		paneKupovinaBtn2.setBackground(new Color(0, 128, 128));
		paneKupovinaBtn2.setBounds(410, 427, 100, 40);
		panelKupovina.add(paneKupovinaBtn2);
		paneKupovinaBtn2.setLayout(new BorderLayout(0, 0));

		paneKupovinaBtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				paneKupovinaBtn2.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(paneKupovinaBtn2.getBackground() != new Color(0, 100, 100))
					paneKupovinaBtn2.setBackground(new Color(0, 128, 128));
			}
			@Override

			public void mousePressed(MouseEvent e) {
				paneKupovinaBtn2.setBackground(new Color(0, 100, 100));
				ArtikalZaNarudzbu a1 = new ArtikalZaNarudzbu(id, naziv, cijena);
				for(ArtikalZaNarudzbu temp : listaArtikalaZaNarudzbu)
					if(temp.compareTo(a1) == 1) {
						if(temp.getKolicina()>1) {
							temp.setKolicina(temp.getKolicina() - 1);
						}
						else
							listaArtikalaZaNarudzbu.remove(temp);
						break;
					}

				popuniTabeluNarudzbi(listaArtikalaZaNarudzbu);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				paneKupovinaBtn2.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel lblBrisi = new JLabel("<< Bri\u0161i");
		lblBrisi.setVerticalAlignment(SwingConstants.BOTTOM);
		lblBrisi.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrisi.setForeground(Color.WHITE);
		lblBrisi.setFont(new Font("Candara", Font.BOLD, 25));
		lblBrisi.setBackground(new Color(0, 100, 0));
		paneKupovinaBtn2.add(lblBrisi);

		JPanel panelKupovinaBtnNaruci = new JPanel();
		panelKupovinaBtnNaruci.setBorder(null);
		panelKupovinaBtnNaruci.setBackground(new Color(0, 128, 128));
		panelKupovinaBtnNaruci.setBounds(570, 595, 250, 45);
		panelKupovina.add(panelKupovinaBtnNaruci);
		panelKupovinaBtnNaruci.setLayout(new BorderLayout(0, 0));
		panelKupovinaBtnNaruci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelKupovinaBtnNaruci.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(panelKupovinaBtnNaruci.getBackground() != new Color(0, 100, 100))
					panelKupovinaBtnNaruci.setBackground(new Color(0, 128, 128));
			}
			@Override

			public void mousePressed(MouseEvent e) {
				
				
				
				
				if(!listaArtikalaZaNarudzbu.isEmpty()) {

					
					String napomena = (String) JOptionPane.showInputDialog(null, "Unesite napomenu trgovcu. (Opcionalno) :", "Napomena", JOptionPane.PLAIN_MESSAGE, null, null, null);
					if(napomena == null)
						return;
					
					//EmptyStringWindow confirmationWindow = new EmptyStringWindow("Upišite napomenu trgovcu (opcionalno)");


//TODO
							Narudzba trenutnaNarudzba = novaNarudzba(trenutniKupac, napomena);

							noviArtikliNarudzbe(trenutnaNarudzba);
							ArtikalNarudzbe.importArtikleNarudzbe();
							narudzbeKupca.add(trenutnaNarudzba);
							ArtikalNarudzbe.importArtikleNarudzbe();
							JOptionPane.showMessageDialog(null, "Uspješno ste završili narudžbu", "Info", JOptionPane.INFORMATION_MESSAGE);
							labelNarudzbe2_1.setText(brojNarudzbiNaCekanju() + "");
							labelNarudzbe3_1.setText(vrijednostNarudzbiNaCekanju() + " KM");
							formTable(0);

							popuniTabeluNarudzbi(null);
					
					}
				else {
					JOptionPane.showMessageDialog(null, "Lista artikala ne može biti prazna", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
				}
				//TODO

				/*
				ArrayList<Proizvod> listaNarucenihProizvoda = new ArrayList<>();
				ArrayList<Integer> listaKolicinaNarucenihProizvoda = new ArrayList<>();

			    for (int i = 0; i < tableMojaNarudzba.getRowCount(); i++) {

			        int id = (int) tableMojaNarudzba.getValueAt(i, 0);
			        int kolicina = (int) tableMojaNarudzba.getValueAt(i, 2);
			        listaKolicinaNarucenihProizvoda.add(kolicina);
			        for(Proizvod p : Proizvod.getListaProizvoda())
			        	if(p.getId() == id)
			        		listaNarucenihProizvoda.add(p);

			     }
			     System.out.println(listaNarucenihProizvoda);
				 */
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				panelKupovinaBtnNaruci.setBackground(new Color(0, 110, 110));
			}
		});



		JLabel labelKupovinaNaruci = new JLabel("Naru\u010Di");
		labelKupovinaNaruci.setVerticalAlignment(SwingConstants.BOTTOM);
		labelKupovinaNaruci.setHorizontalAlignment(SwingConstants.CENTER);
		labelKupovinaNaruci.setForeground(Color.WHITE);
		labelKupovinaNaruci.setFont(new Font("Candara", Font.BOLD, 25));
		labelKupovinaNaruci.setBackground(new Color(0, 128, 0));
		panelKupovinaBtnNaruci.add(labelKupovinaNaruci, BorderLayout.CENTER);

		JPanel panelKupovinaBtnOtkazi = new JPanel();
		panelKupovinaBtnOtkazi.setBorder(null);
		panelKupovinaBtnOtkazi.setBackground(new Color(0, 128, 128));
		panelKupovinaBtnOtkazi.setBounds(105, 595, 250, 45);
		panelKupovina.add(panelKupovinaBtnOtkazi);
		panelKupovinaBtnOtkazi.setLayout(new BorderLayout(0, 0));
		panelKupovinaBtnOtkazi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelKupovinaBtnOtkazi.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(panelKupovinaBtnOtkazi.getBackground() != new Color(0, 100, 100))
					panelKupovinaBtnOtkazi.setBackground(new Color(0, 128, 128));
			}
			@Override

			public void mousePressed(MouseEvent e) {
				if(!listaArtikalaZaNarudzbu.isEmpty())
					if(JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite otkazati narudžbu?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
						labelNarudzbe2_1.setText(brojNarudzbiNaCekanju() + "");
						labelNarudzbe3_1.setText(vrijednostNarudzbiNaCekanju() + " KM");
						popuniTabeluNarudzbi(null);
					}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				panelKupovinaBtnOtkazi.setBackground(new Color(0, 110, 110));
			}
		});

		JLabel labelKupovinaOtkazi = new JLabel("Otkaži");
		labelKupovinaOtkazi.setVerticalAlignment(SwingConstants.BOTTOM);
		labelKupovinaOtkazi.setHorizontalAlignment(SwingConstants.CENTER);
		labelKupovinaOtkazi.setForeground(Color.WHITE);
		labelKupovinaOtkazi.setFont(new Font("Candara", Font.BOLD, 25));
		labelKupovinaOtkazi.setBackground(new Color(0, 128, 0));
		panelKupovinaBtnOtkazi.add(labelKupovinaOtkazi, BorderLayout.CENTER);






		//Menu Panel


		JPanel PanelMenu = new JPanel();
		PanelMenu.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0)));
		PanelMenu.setBackground(new Color(0, 128, 128));
		PanelMenu.setBounds(0, 0, 300, 661);
		frame.getContentPane().add(PanelMenu);
		PanelMenu.setLayout(null);

		JLabel MenuLogoLabel = new JLabel("");
		MenuLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuLogoLabel.setBackground(new Color(255, 255, 255));
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\Anel\\Downloads\\shopify-bag.png");
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		MenuLogoLabel.setIcon(imageIcon);
		MenuLogoLabel.setBounds(0, 40, 300, 100);
		PanelMenu.add(MenuLogoLabel);

		JLabel labelMenu = new JLabel("MENU");
		labelMenu.setVerticalAlignment(SwingConstants.BOTTOM);
		labelMenu.setForeground(new Color(255, 255, 255));
		labelMenu.setBackground(new Color(255, 255, 255));
		labelMenu.setFont(new Font("Candara", Font.BOLD, 30));
		labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
		labelMenu.setBounds(75, 150, 150, 60);
		PanelMenu.add(labelMenu);

		JPanel menuPanelButton1 = new JPanel();
		menuPanelButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelButton1.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelButton1.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				menuPanelButton1.setBackground(new Color(0, 100, 100));
				layeredPane.removeAll();
				layeredPane.add(panelMojProfil);
				layeredPane.repaint();
				layeredPane.revalidate();
				labelNarudzbe2_1.setText(brojNarudzbiNaCekanju() + "");
				labelNarudzbe3_1.setText(vrijednostNarudzbiNaCekanju() + " KM");
				labelNarudzbe1_1.setText(brojIsporucenihNarudzbi() + "");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				menuPanelButton1.setBackground(new Color(0, 110, 110));
			}
		});
		menuPanelButton1.setLayout(null);
		menuPanelButton1.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menuPanelButton1.setBackground(new Color(0, 128, 128));
		menuPanelButton1.setBounds(0, 280, 300, 45);
		PanelMenu.add(menuPanelButton1);

		JLabel labelImeKorisnika = new JLabel("Moj Profil");
		labelImeKorisnika.setHorizontalAlignment(SwingConstants.CENTER);
		labelImeKorisnika.setForeground(Color.WHITE);
		labelImeKorisnika.setFont(new Font("Candara", Font.BOLD, 25));
		labelImeKorisnika.setBackground(new Color(0, 100, 0));
		labelImeKorisnika.setBounds(50, 11, 200, 32);
		menuPanelButton1.add(labelImeKorisnika);

		JPanel menuPanelButton2 = new JPanel();
		menuPanelButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelButton2.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelButton2.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				menuPanelButton2.setBackground(new Color(0, 100, 100));
				layeredPane.removeAll();
				layeredPane.add(panelMojeNarudzbe);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				menuPanelButton2.setBackground(new Color(0, 110, 110));
			}
		});
		menuPanelButton2.setLayout(null);
		menuPanelButton2.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menuPanelButton2.setBackground(new Color(0, 128, 128));
		menuPanelButton2.setBounds(0, 350, 300, 45);
		PanelMenu.add(menuPanelButton2);

		JLabel labelNarudzbe = new JLabel("Narudzbe");
		labelNarudzbe.setHorizontalAlignment(SwingConstants.CENTER);
		labelNarudzbe.setForeground(Color.WHITE);
		labelNarudzbe.setFont(new Font("Candara", Font.BOLD, 25));
		labelNarudzbe.setBackground(new Color(0, 100, 0));
		labelNarudzbe.setBounds(50, 11, 200, 32);
		menuPanelButton2.add(labelNarudzbe);



		JPanel menuPanelButton3 = new JPanel();

		menuPanelButton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelButton3.setBackground(new Color(0, 110, 110));
			}


			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelButton3.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				menuPanelButton3.setBackground(new Color(0, 100, 100));
				layeredPane.removeAll();
				layeredPane.add(panelKupovina);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				menuPanelButton3.setBackground(new Color(0, 110, 110));
			}
		});

		menuPanelButton3.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menuPanelButton3.setBackground(new Color(0, 128, 128));
		menuPanelButton3.setBounds(0, 420, 300, 45);
		PanelMenu.add(menuPanelButton3);
		menuPanelButton3.setLayout(null);

		JLabel labelKupovina = new JLabel("Kupovina");
		labelKupovina.setBounds(50, 11, 200, 32);
		labelKupovina.setHorizontalAlignment(SwingConstants.CENTER);
		labelKupovina.setForeground(Color.WHITE);
		labelKupovina.setFont(new Font("Candara", Font.BOLD, 25));
		labelKupovina.setBackground(new Color(0, 100, 0));
		menuPanelButton3.add(labelKupovina);

		JPanel menuPanelButton4 = new JPanel();
		menuPanelButton4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelButton4.setBackground(new Color(0, 110, 110));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelButton4.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				menuPanelButton4.setBackground(new Color(0, 100, 100));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				menuPanelButton4.setBackground(new Color(0, 110, 110));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Object [] options = { "Da", "Ne" };
				if(	JOptionPane.showOptionDialog (null, "Da li ste sigurni da se želite odjaviti?", "Odajava",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
						options, options[0]) == JOptionPane.YES_OPTION) { 
					frame.dispose();
					@SuppressWarnings("unused")
					LoginRegisterForm newApp = new LoginRegisterForm();
				}
			}
		});
		menuPanelButton4.setLayout(null);
		menuPanelButton4.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menuPanelButton4.setBackground(new Color(0, 128, 128));
		menuPanelButton4.setBounds(0, 600, 300, 45);
		PanelMenu.add(menuPanelButton4);

		JLabel labelOdjaviSe = new JLabel("Odjavi se");
		labelOdjaviSe.setHorizontalAlignment(SwingConstants.CENTER);
		labelOdjaviSe.setForeground(Color.WHITE);
		labelOdjaviSe.setFont(new Font("Candara", Font.BOLD, 25));
		labelOdjaviSe.setBackground(new Color(0, 100, 0));
		labelOdjaviSe.setBounds(50, 11, 200, 32);
		menuPanelButton4.add(labelOdjaviSe);



	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private static double vrijednostNarudzbe(Narudzba n) {
		double vrijednost = 0;
		for(ArtikalNarudzbe artikal: ArtikalNarudzbe.getListaArtikalaNarudzbe()) {
			if(artikal.getNarudzbaId() == n.getId()) {
				vrijednost += (artikal.getCijenaPoKomadu() * artikal.getKolicina());
			}
		}


		return vrijednost;
	}

	public static void formTable(int t) {
		int narudzbeDisplayOption = t;
		DefaultTableModel dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			boolean [] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table_narudzbe.setModel(dtm);
		dtm.setColumnIdentifiers(new String [] { "ID narud\u017ebe", "Datum narud\u017Ebe", "Datum isporuke", "Vrijednost", "Status" });
		table_narudzbe.getColumnModel().getColumn(0).setPreferredWidth(15);
		Kupac.updateListaNarudzbiKupca(trenutniKupac);
		narudzbeKupca = trenutniKupac.getListaNarudzbiKupca();
		for(Narudzba n : narudzbeKupca) {
			if(n.getDatumIsporuke() != null && narudzbeDisplayOption == 1)
				continue;
			dtm.addRow(new Object [] { n.getId(), n.getDatumNarudzbe(), n.getDatumIsporuke(), vrijednostNarudzbe(n), n.getStatus() });
		}
	}

	private void openDetaljiNarudzbe() {
		int Id = Integer.parseInt(table_narudzbe.getValueAt(table_narudzbe.getSelectedRow(), 0).toString());
		for(Narudzba n : trenutniKupac.getListaNarudzbiKupca())
			if(n.getId() == Id) {
				new DetaljiNarudzbe(n, trenutniKupac);
			}
	}

	private void popunitiTabeluArtikala() {
		DefaultTableModel dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			boolean [] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tableSviArtikli.setModel(dtm);
		dtm.setColumnIdentifiers(new String [] { "ID proizvoda ", "Naziv proizvoda", "Cijena"});
		tableSviArtikli.getColumnModel().getColumn(0).setPreferredWidth(15);
		for(Proizvod p : Proizvod.getListaProizvoda()) {
			dtm.addRow(new Object [] { p.getId(), p.getNaziv(), p.getCijena()});
		}
	}

	private void popuniTabeluNarudzbi(ArrayList<ArtikalZaNarudzbu> listaArtikala) {

		DefaultTableModel dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			boolean [] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tableMojaNarudzba.setModel(dtm);
		dtm.setColumnIdentifiers(new String [] { "ID proizvoda ", "Naziv proizvoda", "Količina", "Cijena"});
		tableMojaNarudzba.getColumnModel().getColumn(0).setPreferredWidth(15);

		if(listaArtikala != null)
			for(ArtikalZaNarudzbu artikal : listaArtikala) {
				dtm.addRow(new Object [] { artikal.getId(), artikal.getNaziv(), artikal.getKolicina(), artikal.getCijena()});
			}
		else {
			listaArtikalaZaNarudzbu = new ArrayList<ArtikalZaNarudzbu>();
		}
	}


	private Narudzba novaNarudzba(Kupac k, String napomena) {
		int id = getIduciIDNarudzbe();
		Narudzba novaNarudzba = new Narudzba(id, k.getId(), 0, new java.util.Date(), null, napomena);
		Narudzba.getListaNarudzbi().add(novaNarudzba);
		insertNaruzba(id, k.getId(), 0, new Date(), null, napomena);

		return novaNarudzba;

	}


	private int getIduciIDNarudzbe() {
		
		int location = Narudzba.getListaNarudzbi().size()-1;
		Narudzba posljednja = Narudzba.getListaNarudzbi().get(location);
		System.out.println("Id zadnje narudzbe je: " + posljednja.getId());
		int id = posljednja.getId()+1;
		System.out.println("Novi id je : " + id);

		return id;
	}

	private static int getIduciIDArtikalNarudzbe() {
		int location = ArtikalNarudzbe.getListaArtikalaNarudzbe().size()-1;
		ArtikalNarudzbe posljednji = ArtikalNarudzbe.getListaArtikalaNarudzbe().get(location);
		int id = posljednji.getId()+1;

		return id;
	}

	private ArrayList<ArtikalNarudzbe> noviArtikliNarudzbe(Narudzba trenutnaNarudzba){
		ArrayList<ArtikalNarudzbe> listaNarucenihArtikala = new ArrayList<ArtikalNarudzbe>();
		int idArtikalNarudzbe = getIduciIDArtikalNarudzbe();

		System.out.println("Pokusaj Ubacivanje artikla u bazu");

		System.out.println("Velicina tabele: " + tableMojaNarudzba.getRowCount());
		for (int i = 0; i < tableMojaNarudzba.getRowCount(); i++) {
			int proizvod_id = (int) tableMojaNarudzba.getValueAt(i, 0);
			@SuppressWarnings("unused")
			String naziv_proizvoda = (String) tableMojaNarudzba.getValueAt(i, 1);
			int kolicina = (int) tableMojaNarudzba.getValueAt(i, 2);
			double cijena = (double) tableMojaNarudzba.getValueAt(i, 3);

			double cijenaPoKomadu = cijena/kolicina;
			ArtikalNarudzbe temp = new ArtikalNarudzbe(trenutnaNarudzba.getId(), proizvod_id, kolicina, cijenaPoKomadu, idArtikalNarudzbe);

			listaNarucenihArtikala.add(temp);
			insertArtiklNarudzbe(trenutnaNarudzba.getId(), proizvod_id, kolicina, cijenaPoKomadu, idArtikalNarudzbe);
			idArtikalNarudzbe++; 
		}
		return listaNarucenihArtikala;

	}


	public static void insertNaruzba(int id, int kupac_id, int trgovac_id, Date date, Date datumIsporuke, String napomena) {
		try {
			Database d = new Database();
			String query = " insert into narudzba (id, kupac_id, trgovac_id, datum_narudzbe, datum_isporuke, napomena)"
					+ " values (?, ?, ?, ?, ?, ?)";
			@SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();

			java.sql.Date sqlDate1 = new java.sql.Date(date.getTime()); 

			PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.setInt (2, kupac_id );
			preparedStmt.setInt (3, trgovac_id);
			preparedStmt.setDate (4,  (java.sql.Date) sqlDate1);
			preparedStmt.setDate (5,  (java.sql.Date) datumIsporuke);
			preparedStmt.setString (6, napomena);


			// execute the preparedstatement
			preparedStmt.execute();

		}
		catch (SQLException e) {
			System.err.println(e);
		}

	}

	public static void insertArtiklNarudzbe(int narudzba_id, int proizvod_id, int kolicina, double cijena_po_komadu, int id) {
		try {
			Database d = new Database();
			String query = " insert into artikal_narudzbe (narudzba_id, proizvod_id, kolicina, cijena_po_komadu, id)"
					+ " values (?, ?, ?, ?, ?)";
			@SuppressWarnings("unused")
			Statement st = d.getConn().createStatement();

			PreparedStatement preparedStmt = d.getConn().prepareStatement(query);
			preparedStmt.setInt(1, narudzba_id);
			preparedStmt.setInt (2, proizvod_id );
			preparedStmt.setInt (3, kolicina);
			preparedStmt.setDouble(4, cijena_po_komadu);
			preparedStmt.setInt (5, id);


			// execute the preparedstatement
			preparedStmt.execute();
			System.out.println("Dodadn Artikal narduzbe u bazu");

		}
		catch (SQLException e) {
			System.err.println(e);
		}

	}

}
