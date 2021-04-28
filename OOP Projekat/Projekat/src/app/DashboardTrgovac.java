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

import Util.Status;

import javax.swing.ListSelectionModel;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import classes.ArtikalNarudzbe;
import classes.Kupac;
import classes.Narudzba;
import classes.ProdajnoMjesto;
import classes.Proizvod;
import classes.Trgovac;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DashboardTrgovac {
	
	JLayeredPane layeredPane;
	private JFrame frame;
	private static Trgovac trenutniTrgovac;
	public static int idProizvoda;
	public static String nazivProizvoda;
	
	
	private static ArrayList<String> listaDržava;
	private static ArrayList<Narudzba> listaNarudzbi;
	
	//private static ArrayList<Narudzba> narudzbeKupca;
	private static JTable table_narudzbe;
	private static JTable tableArtikli;
	
	public DashboardTrgovac() {
		
		initialize();
		frame.setVisible(true);
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
		
		trenutniTrgovac = LoginRegisterForm.getTrenutniTrgovac();
		System.out.println("Trenutni Trgovac: " + trenutniTrgovac);
		
		getListaDržava();
		System.out.println(listaDržava);
		listaNarudzbi = new ArrayList<Narudzba>();
		popuniListuNarudzbi();
		System.out.println(listaNarudzbi);

		
		
		
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
		/*
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
		*/
		JLabel labelPodaciKIme = new JLabel("");
		labelPodaciKIme.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciKIme.setForeground(Color.BLACK);
		labelPodaciKIme.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciKIme.setBackground(Color.WHITE);
		labelPodaciKIme.setBounds(155, 10, 210, 35);
		labelPodaciKIme.setText(trenutniTrgovac.getKorisnicko_ime());
		panelMojProfilPodaci.add(labelPodaciKIme);
		
		JLabel labelPodaciIme = new JLabel("");
		labelPodaciIme.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciIme.setForeground(Color.BLACK);
		labelPodaciIme.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciIme.setBackground(Color.WHITE);
		labelPodaciIme.setBounds(155, 56, 210, 35);
		labelPodaciIme.setText(trenutniTrgovac.getIme());
		panelMojProfilPodaci.add(labelPodaciIme);
		
		JLabel labelPodaciPrezime = new JLabel("");
		labelPodaciPrezime.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciPrezime.setForeground(Color.BLACK);
		labelPodaciPrezime.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciPrezime.setBackground(Color.WHITE);
		labelPodaciPrezime.setBounds(155, 102, 210, 35);
		labelPodaciPrezime.setText(trenutniTrgovac.getPrezime());
		panelMojProfilPodaci.add(labelPodaciPrezime);
		
		JLabel labelPodaciPol = new JLabel("");
		labelPodaciPol.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciPol.setForeground(Color.BLACK);
		labelPodaciPol.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciPol.setBackground(Color.WHITE);
		labelPodaciPol.setBounds(155, 148, 210, 35);
		if(trenutniTrgovac.getPol().equals("M"))
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
		labelPodaciBrTel.setText(trenutniTrgovac.getTelefon());
		panelMojProfilPodaci.add(labelPodaciBrTel);
		
		JLabel labelPodaciEmail = new JLabel("");
		labelPodaciEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciEmail.setForeground(Color.BLACK);
		labelPodaciEmail.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciEmail.setBackground(Color.WHITE);
		labelPodaciEmail.setBounds(155, 240, 235, 35);
		labelPodaciEmail.setText(trenutniTrgovac.getEmail());
		panelMojProfilPodaci.add(labelPodaciEmail);
		/*
		JLabel labelPodaciAdresa = new JLabel("");
		labelPodaciAdresa.setHorizontalAlignment(SwingConstants.LEFT);
		labelPodaciAdresa.setForeground(Color.BLACK);
		labelPodaciAdresa.setFont(new Font("Candara", Font.BOLD, 20));
		labelPodaciAdresa.setBackground(Color.WHITE);
		labelPodaciAdresa.setBounds(155, 286, 210, 35);
		labelPodaciAdresa.setText(trenutniTrgovac.getAdresa());
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
		*/
		JPanel panelMojProfilNarudzbe = new JPanel();
		panelMojProfilNarudzbe.setBounds(500, 200, 350, 284);
		panelMojProfil.add(panelMojProfilNarudzbe);
		panelMojProfilNarudzbe.setLayout(null);
		
		JLabel labelNarudzbe1 = new JLabel("Broj prihvacenih narud\u017Ebi: ");
		labelNarudzbe1.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe1.setForeground(Color.BLACK);
		labelNarudzbe1.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe1.setBackground(Color.WHITE);
		labelNarudzbe1.setBounds(10, 10, 330, 35);
		panelMojProfilNarudzbe.add(labelNarudzbe1);
		
		JLabel labelNarudzbe2 = new JLabel("Broj neprihva\u0107enih narud\u017Ebi:");
		labelNarudzbe2.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe2.setForeground(Color.BLACK);
		labelNarudzbe2.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe2.setBackground(Color.WHITE);
		labelNarudzbe2.setBounds(10, 102, 330, 35);
		panelMojProfilNarudzbe.add(labelNarudzbe2);
		
		JLabel labelNarudzbe3 = new JLabel("Vrijednost prihvacenih narud\u017Ebi:");
		labelNarudzbe3.setHorizontalAlignment(SwingConstants.LEFT);
		labelNarudzbe3.setForeground(Color.BLACK);
		labelNarudzbe3.setFont(new Font("Candara", Font.BOLD, 20));
		labelNarudzbe3.setBackground(Color.WHITE);
		labelNarudzbe3.setBounds(10, 194, 330, 35);
		panelMojProfilNarudzbe.add(labelNarudzbe3);
		
		JLabel labelbrOdobrenih = new JLabel(brojOdobrenihNarudzbi()+"");
		labelbrOdobrenih.setHorizontalAlignment(SwingConstants.LEFT);
		labelbrOdobrenih.setForeground(Color.BLACK);
		labelbrOdobrenih.setFont(new Font("Candara", Font.BOLD, 20));
		labelbrOdobrenih.setBackground(Color.WHITE);
		labelbrOdobrenih.setBounds(10, 57, 210, 35);
		panelMojProfilNarudzbe.add(labelbrOdobrenih);
		
		
		JLabel labelbrNeprihvacenih = new JLabel(brojNeprihvacenihNarudzbi()+"");
		labelbrNeprihvacenih.setHorizontalAlignment(SwingConstants.LEFT);
		labelbrNeprihvacenih.setForeground(Color.BLACK);
		labelbrNeprihvacenih.setFont(new Font("Candara", Font.BOLD, 20));
		labelbrNeprihvacenih.setBackground(Color.WHITE);
		labelbrNeprihvacenih.setBounds(10, 147, 210, 35);
		panelMojProfilNarudzbe.add(labelbrNeprihvacenih);
		
		JLabel labelVrijednostSvihNarudzbi = new JLabel(vrijednostSvihNaruzbi()+"");
		labelVrijednostSvihNarudzbi.setHorizontalAlignment(SwingConstants.LEFT);
		labelVrijednostSvihNarudzbi.setForeground(Color.BLACK);
		labelVrijednostSvihNarudzbi.setFont(new Font("Candara", Font.BOLD, 20));
		labelVrijednostSvihNarudzbi.setBackground(Color.WHITE);
		labelVrijednostSvihNarudzbi.setBounds(10, 227, 210, 35);
		panelMojProfilNarudzbe.add(labelVrijednostSvihNarudzbi);
		/*
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
		*/
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
		
		JPanel panelNarudzbe = new JPanel();
		panelNarudzbe.setBackground(Color.WHITE);
		panelNarudzbe.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		layeredPane.add(panelNarudzbe, "name_78027246231700");
		panelNarudzbe.setLayout(null);
		
		JPanel panelMojeNarudzbeHeading = new JPanel();
		panelMojeNarudzbeHeading.setBackground(new Color(0, 128, 128));
		panelMojeNarudzbeHeading.setBounds(162, 35, 551, 70);
		panelNarudzbe.add(panelMojeNarudzbeHeading);
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
		panelNarudzbe.add(panelMojeNarudzbeButton1);
		
		
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
				formTableEdited(listaNarudzbi, 0);
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelMojeNarudzbeButton1.setBackground(new Color(0, 110, 110));
			}
		});
		
		JLabel labelNeprihvaceneNarudzbe = new JLabel("Neprihvacene Narudzbe");
		labelNeprihvaceneNarudzbe.setHorizontalAlignment(SwingConstants.CENTER);
		labelNeprihvaceneNarudzbe.setForeground(Color.WHITE);
		labelNeprihvaceneNarudzbe.setFont(new Font("Candara", Font.BOLD, 25));
		labelNeprihvaceneNarudzbe.setBackground(new Color(0, 100, 0));
		labelNeprihvaceneNarudzbe.setBounds(10, 11, 280, 32);
		panelMojeNarudzbeButton1.add(labelNeprihvaceneNarudzbe);
		
		JPanel panelMojeNarudzbeButton2 = new JPanel();
		panelMojeNarudzbeButton2.setLayout(null);
		panelMojeNarudzbeButton2.setBorder(null);
		panelMojeNarudzbeButton2.setBackground(new Color(0, 128, 128));
		panelMojeNarudzbeButton2.setBounds(470, 183, 300, 45);
		panelNarudzbe.add(panelMojeNarudzbeButton2);
		
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
				formTableEdited(listaNarudzbi, 1);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelMojeNarudzbeButton2.setBackground(new Color(0, 110, 110));
			}
		});
		
		JLabel labeOdobreneNarudzbe = new JLabel("Odobrene Narudzbe");
		labeOdobreneNarudzbe.setHorizontalAlignment(SwingConstants.CENTER);
		labeOdobreneNarudzbe.setForeground(Color.WHITE);
		labeOdobreneNarudzbe.setFont(new Font("Candara", Font.BOLD, 25));
		labeOdobreneNarudzbe.setBackground(new Color(0, 100, 0));
		labeOdobreneNarudzbe.setBounds(10, 11, 280, 32);
		panelMojeNarudzbeButton2.add(labeOdobreneNarudzbe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 261, 690, 349);
		panelNarudzbe.add(scrollPane);
		
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
		System.out.println(listaNarudzbi);
		formTable(listaNarudzbi);
		
		JPanel panelArtikli = new JPanel();
		panelArtikli.setLayout(null);
		panelArtikli.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		panelArtikli.setBackground(Color.WHITE);
		layeredPane.add(panelArtikli, "name_1874399203039200");
		
		JPanel panelArtikliHeading = new JPanel();
		panelArtikliHeading.setBackground(new Color(0, 128, 128));
		panelArtikliHeading.setBounds(162, 35, 551, 70);
		panelArtikli.add(panelArtikliHeading);
		panelArtikliHeading.setLayout(new BorderLayout(0, 0));
		
		JLabel lblArtikliHeading = new JLabel("ARTIKLI");
		lblArtikliHeading.setVerticalAlignment(SwingConstants.BOTTOM);
		lblArtikliHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtikliHeading.setForeground(Color.WHITE);
		lblArtikliHeading.setFont(new Font("Candara", Font.BOLD, 35));
		lblArtikliHeading.setBackground(Color.WHITE);
		panelArtikliHeading.add(lblArtikliHeading, BorderLayout.CENTER);
		
		JPanel panelArtikliButton1 = new JPanel();
		panelArtikliButton1.setBorder(null);
		panelArtikliButton1.setBackground(new Color(0, 128, 128));
		panelArtikliButton1.setBounds(130, 193, 300, 45);
		panelArtikli.add(panelArtikliButton1);
		panelArtikliButton1.setLayout(new BorderLayout(0, 0));
		
		// dodavanje artikla 
		
		panelArtikliButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelArtikliButton1.setBackground(new Color(0, 110, 110));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(panelArtikliButton1.getBackground() != new Color(0, 100, 100))
					panelArtikliButton1.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelArtikliButton1.setBackground(new Color(0, 100, 100));
				@SuppressWarnings("unused")
				DodajArtikal panelDodajArtikal = new DodajArtikal();
				
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelArtikliButton1.setBackground(new Color(0, 110, 110));
			}
		});
		
		JLabel labelDodajArtikal = new JLabel("Dodaj Artikal");
		labelDodajArtikal.setVerticalAlignment(SwingConstants.BOTTOM);
		labelDodajArtikal.setHorizontalAlignment(SwingConstants.CENTER);
		labelDodajArtikal.setForeground(Color.WHITE);
		labelDodajArtikal.setFont(new Font("Candara", Font.BOLD, 25));
		labelDodajArtikal.setBackground(new Color(0, 100, 0));
		panelArtikliButton1.add(labelDodajArtikal);
		
		JPanel panelArtikliButton2 = new JPanel();
		panelArtikliButton2.setBorder(null);
		panelArtikliButton2.setBackground(new Color(0, 128, 128));
		panelArtikliButton2.setBounds(460, 195, 300, 45);
		panelArtikli.add(panelArtikliButton2);
		panelArtikliButton2.setLayout(new BorderLayout(0, 0));
		
		//azuriranje artikla
		
		panelArtikliButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelArtikliButton2.setBackground(new Color(0, 110, 110));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(panelArtikliButton2.getBackground() != new Color(0, 100, 100))
					panelArtikliButton2.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelArtikliButton2.setBackground(new Color(0, 100, 100));
				if(nazivProizvoda == null) {
					JOptionPane.showConfirmDialog(null, "Morate odabrati artikal za azuriranje", "OK", JOptionPane.DEFAULT_OPTION);
				}
				else {
					@SuppressWarnings("unused")
					AzurirajArtikal panelAzurirajArtikal = new AzurirajArtikal();
					nazivProizvoda = null;
				}
				
				
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelArtikliButton2.setBackground(new Color(0, 110, 110));
			}
		});
		
		JLabel labelAzurirajArtikal = new JLabel("Azuriraj Artikal");
		panelArtikliButton2.add(labelAzurirajArtikal, BorderLayout.CENTER);
		labelAzurirajArtikal.setVerticalAlignment(SwingConstants.BOTTOM);
		labelAzurirajArtikal.setHorizontalAlignment(SwingConstants.CENTER);
		labelAzurirajArtikal.setForeground(Color.WHITE);
		labelAzurirajArtikal.setFont(new Font("Candara", Font.BOLD, 25));
		labelAzurirajArtikal.setBackground(new Color(0, 100, 0));
		
		JScrollPane panelArtikliScrollPane = new JScrollPane();
		panelArtikliScrollPane.setBounds(100, 270, 690, 349);
		panelArtikli.add(panelArtikliScrollPane);
		
		tableArtikli = new JTable();
		panelArtikliScrollPane.setViewportView(tableArtikli);
		popunitiTabeluArtikala();
		
		tableArtikli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idProizvoda = Integer.parseInt(tableArtikli.getValueAt(tableArtikli.getSelectedRow(), 0).toString());
				nazivProizvoda = tableArtikli.getValueAt(tableArtikli.getSelectedRow(), 1).toString();
				

			}
		});
		
		JPanel panelDodatneOpcije = new JPanel();
		panelDodatneOpcije.setLayout(null);
		panelDodatneOpcije.setBorder(new MatteBorder(1, 1, 0, 0, (Color) new Color(0, 0, 0)));
		panelDodatneOpcije.setBackground(Color.WHITE);
		layeredPane.add(panelDodatneOpcije, "name_1884940779425900");
		
		JPanel panelDodatneOpcijeHeading = new JPanel();
		panelDodatneOpcijeHeading.setBackground(new Color(0, 128, 128));
		panelDodatneOpcijeHeading.setBounds(162, 35, 551, 70);
		panelDodatneOpcije.add(panelDodatneOpcijeHeading);
		panelDodatneOpcijeHeading.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDodatneOpcijeHeading = new JLabel("Dodatne Opcije");
		lblDodatneOpcijeHeading.setForeground(Color.WHITE);
		lblDodatneOpcijeHeading.setBackground(Color.WHITE);
		lblDodatneOpcijeHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblDodatneOpcijeHeading.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDodatneOpcijeHeading.setFont(new Font("Candara", Font.BOLD, 35));
		panelDodatneOpcijeHeading.add(lblDodatneOpcijeHeading, BorderLayout.CENTER);
		
		JPanel panelDodatneOpcijeBtn1 = new JPanel();
		panelDodatneOpcijeBtn1.setLayout(null);
		panelDodatneOpcijeBtn1.setBorder(null);
		panelDodatneOpcijeBtn1.setBackground(new Color(0, 128, 128));
		panelDodatneOpcijeBtn1.setBounds(286, 173, 300, 45);
		panelDodatneOpcije.add(panelDodatneOpcijeBtn1);
		

		//dodavanje trgovca
		
		panelDodatneOpcijeBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelDodatneOpcijeBtn1.setBackground(new Color(0, 110, 110));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(panelDodatneOpcijeBtn1.getBackground() != new Color(0, 100, 100))
					panelDodatneOpcijeBtn1.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelDodatneOpcijeBtn1.setBackground(new Color(0, 100, 100));
				@SuppressWarnings("unused")
				DodajTrgovca panelDodajTrgovca = new DodajTrgovca();
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelDodatneOpcijeBtn1.setBackground(new Color(0, 110, 110));
			}
		});
		
		
		
		
		JLabel labelDodajTrgovca = new JLabel("Dodaj Trgovca");
		labelDodajTrgovca.setHorizontalAlignment(SwingConstants.CENTER);
		labelDodajTrgovca.setForeground(Color.WHITE);
		labelDodajTrgovca.setFont(new Font("Candara", Font.BOLD, 25));
		labelDodajTrgovca.setBackground(new Color(0, 100, 0));
		labelDodajTrgovca.setBounds(10, 11, 280, 32);
		panelDodatneOpcijeBtn1.add(labelDodajTrgovca);
		
		JPanel panelDodatneOpcijeBtn2 = new JPanel();
		panelDodatneOpcijeBtn2.setLayout(null);
		panelDodatneOpcijeBtn2.setBorder(null);
		panelDodatneOpcijeBtn2.setBackground(new Color(0, 128, 128));
		panelDodatneOpcijeBtn2.setBounds(286, 288, 300, 45);
		panelDodatneOpcije.add(panelDodatneOpcijeBtn2);
		
		//dodavanje prodajnog mjesta
		
		panelDodatneOpcijeBtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelDodatneOpcijeBtn2.setBackground(new Color(0, 110, 110));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(panelDodatneOpcijeBtn2.getBackground() != new Color(0, 100, 100))
					panelDodatneOpcijeBtn2.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panelDodatneOpcijeBtn2.setBackground(new Color(0, 100, 100));
				@SuppressWarnings("unused")
				DodajProdajnoMjesto panelDodajProdajnoMjesto = new DodajProdajnoMjesto();
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				panelDodatneOpcijeBtn2.setBackground(new Color(0, 110, 110));
			}
		});
		
		
		JLabel labelDodajProdajnoMjesto = new JLabel("Dodaj Prodajno Mjesto");
		labelDodajProdajnoMjesto.setHorizontalAlignment(SwingConstants.CENTER);
		labelDodajProdajnoMjesto.setForeground(Color.WHITE);
		labelDodajProdajnoMjesto.setFont(new Font("Candara", Font.BOLD, 25));
		labelDodajProdajnoMjesto.setBackground(new Color(0, 100, 0));
		labelDodajProdajnoMjesto.setBounds(10, 11, 280, 32);
		panelDodatneOpcijeBtn2.add(labelDodajProdajnoMjesto);
		
		
		
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
				labelbrOdobrenih.setText(brojOdobrenihNarudzbi()+"");
				labelbrNeprihvacenih.setText(brojNeprihvacenihNarudzbi()+"");
				labelVrijednostSvihNarudzbi.setText(vrijednostSvihNaruzbi()+"");
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
				layeredPane.add(panelNarudzbe);
				layeredPane.repaint();
				layeredPane.revalidate();
				formTable(listaNarudzbi);
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
				layeredPane.add(panelArtikli);
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
		
		JLabel labelArtikli = new JLabel("Artikli");
		labelArtikli.setBounds(50, 11, 200, 32);
		labelArtikli.setHorizontalAlignment(SwingConstants.CENTER);
		labelArtikli.setForeground(Color.WHITE);
		labelArtikli.setFont(new Font("Candara", Font.BOLD, 25));
		labelArtikli.setBackground(new Color(0, 100, 0));
		menuPanelButton3.add(labelArtikli);
		
		JPanel menuPanelButton5 = new JPanel();
		menuPanelButton5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelButton5.setBackground(new Color(0, 110, 110));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelButton5.setBackground(new Color(0, 128, 128));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				menuPanelButton5.setBackground(new Color(0, 100, 100));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				menuPanelButton5.setBackground(new Color(0, 110, 110));
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
		menuPanelButton5.setLayout(null);
		menuPanelButton5.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menuPanelButton5.setBackground(new Color(0, 128, 128));
		menuPanelButton5.setBounds(0, 600, 300, 45);
		PanelMenu.add(menuPanelButton5);
		
		JLabel labelOdjaviSe = new JLabel("Odjavi se");
		labelOdjaviSe.setHorizontalAlignment(SwingConstants.CENTER);
		labelOdjaviSe.setForeground(Color.WHITE);
		labelOdjaviSe.setFont(new Font("Candara", Font.BOLD, 25));
		labelOdjaviSe.setBackground(new Color(0, 100, 0));
		labelOdjaviSe.setBounds(50, 11, 200, 32);
		menuPanelButton5.add(labelOdjaviSe);
		
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
				layeredPane.removeAll();
				layeredPane.add(panelDodatneOpcije);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				menuPanelButton4.setBackground(new Color(0, 110, 110));
			}
		});
		menuPanelButton4.setLayout(null);
		menuPanelButton4.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menuPanelButton4.setBackground(new Color(0, 128, 128));
		menuPanelButton4.setBounds(0, 490, 300, 45);
		PanelMenu.add(menuPanelButton4);
		
		JLabel labelDodatneOpcije = new JLabel("Dodatne Opcije");
		labelDodatneOpcije.setHorizontalAlignment(SwingConstants.CENTER);
		labelDodatneOpcije.setForeground(Color.WHITE);
		labelDodatneOpcije.setFont(new Font("Candara", Font.BOLD, 25));
		labelDodatneOpcije.setBackground(new Color(0, 100, 0));
		labelDodatneOpcije.setBounds(50, 11, 200, 32);
		menuPanelButton4.add(labelDodatneOpcije);
	
		
	}
	
	public static void popunitiTabeluArtikala() {
		DefaultTableModel dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			boolean [] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		tableArtikli.setModel(dtm);
		dtm.setColumnIdentifiers(new String [] { "ID proizvoda ", "Naziv proizvoda", "Cijena"});
		tableArtikli.getColumnModel().getColumn(0).setPreferredWidth(15);
		for(Proizvod p : Proizvod.getListaProizvoda()) {
			dtm.addRow(new Object [] { p.getId(), p.getNaziv(), p.getCijena()});
		}
	}

	public int getIdProizvoda() {
		return idProizvoda;
	}

	@SuppressWarnings("static-access")
	public void setIdProizvoda(int idProizvoda) {
		this.idProizvoda = idProizvoda;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	@SuppressWarnings("static-access")
	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

	
	
	private ArrayList<String> getListaDržava(){
		listaDržava = new ArrayList<String>();
		for(ProdajnoMjesto p: ProdajnoMjesto.getListaProdajnihMjesta()) {
			if(!(listaDržava.contains(p.getDrzava())))
				listaDržava.add(p.getDrzava());
		}
		return listaDržava;
	}
	
	public static void popuniListuNarudzbi() {
		listaNarudzbi = new ArrayList<Narudzba>();
		System.out.println("Lista narudzbi: ");
		for(Narudzba n: Narudzba.getListaNarudzbi())
			getListaNarudzbiZaTrgovca(n);
	}
	
	private static void getListaNarudzbiZaTrgovca(Narudzba n){
		System.out.println("Trgovac id:" + trenutniTrgovac.getId());
		if(n.getTrgovacId() != 0 && n.getTrgovacId()!=trenutniTrgovac.getId())
			return;
		String drzavaTrgovca = "";
		System.out.println("DrzavaTrgovca: " + drzavaTrgovca );
		String drzavaKupca = "";
		System.out.println("DrzavaKupca: " + drzavaKupca);
		for(ProdajnoMjesto pm: ProdajnoMjesto.getListaProdajnihMjesta()) {
			if(pm.getId() == trenutniTrgovac.getProdajno_mjesto_id()) {
				drzavaTrgovca = pm.getDrzava();
				System.out.println("DrzavaTrgovca: " + drzavaTrgovca );
				break;
			}
		}
		for(Kupac k: Kupac.getListaKupaca()) {
			if(k.getId() == n.getKupacId()) {
				drzavaKupca = k.getDrzava();
				System.out.println("DrzavaKupca: " + drzavaKupca);
				break;
			}
		}
		
		
			if(!(listaDržava.contains(drzavaKupca))){
			listaNarudzbi.add(n);
		}	

			
			if(drzavaKupca.equals(drzavaTrgovca)) {
				listaNarudzbi.add(n);
			}
			
			
		if(!listaNarudzbi.contains(n)) {
			if(n.getTrgovacId() == trenutniTrgovac.getId())
				listaNarudzbi.add(n);
		}
		
		
		
	}
	
	@SuppressWarnings("static-access")
	private void openDetaljiNarudzbe() {
		int Id = Integer.parseInt(table_narudzbe.getValueAt(table_narudzbe.getSelectedRow(), 0).toString());
		for(Narudzba n : listaNarudzbi)
			if(n.getId() == Id) {
				new DetaljiNarudzbe(n, this.trenutniTrgovac);
			}
	}
	
	public static void formTable(ArrayList<Narudzba> listaNarudzbi) {
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
		for(Narudzba n : listaNarudzbi) {
			dtm.addRow(new Object [] { n.getId(), n.getDatumNarudzbe(), n.getDatumIsporuke(), vrijednostNarudzbe(n), n.getStatus() });
		}
	}
	public static void formTableEdited(ArrayList<Narudzba> listaNarudzbi, int arg) {
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
		if(arg == 0) {
		
		for(Narudzba n : listaNarudzbi) {
			if(n.getStatus() == Status.NA_CEKANJU)
				dtm.addRow(new Object [] { n.getId(), n.getDatumNarudzbe(), n.getDatumIsporuke(), vrijednostNarudzbe(n), n.getStatus() });
			}
		}
		if(arg == 1) {
			
			for(Narudzba n : listaNarudzbi) {
				if(n.getStatus() != Status.NA_CEKANJU)
				dtm.addRow(new Object [] { n.getId(), n.getDatumNarudzbe(), n.getDatumIsporuke(), vrijednostNarudzbe(n), n.getStatus() });
			}
		}
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
	
	private static int brojOdobrenihNarudzbi() {
		int brojac = 0;
 
		for(Narudzba n : Narudzba.getListaNarudzbi()) {
			if(n.getTrgovacId() == trenutniTrgovac.getId()) {
				if(n.getStatus() != Status.NA_CEKANJU)
					brojac++;
			}
		}
		return brojac;
	}
 
	private static int brojNeprihvacenihNarudzbi() {
		int brojac = 0;
 
		for(Narudzba n : Narudzba.getListaNarudzbi()) {
			if(n.getTrgovacId() == 0) {
				if(n.getDatumIsporuke() == null)
					brojac++;
			}
		}
		return brojac;
	}
 
	private static int vrijednostSvihNaruzbi() {
		int value = 0;
 
		for(Narudzba n : Narudzba.getListaNarudzbi()) {
			if(n.getTrgovacId() == trenutniTrgovac.getId()) {
				for(ArtikalNarudzbe artikal : ArtikalNarudzbe.getListaArtikalaNarudzbe()) {
					if(artikal.getNarudzbaId() == n.getId()) 
						value += artikal.getCijenaPoKomadu()*artikal.getKolicina();
				}	
			}
 
		}
		return value;
	}

	public static ArrayList<Narudzba> getListaNarudzbi() {
		return listaNarudzbi;
	}

	public static void setListaNarudzbi(ArrayList<Narudzba> listaNarudzbi) {
		DashboardTrgovac.listaNarudzbi = listaNarudzbi;
	}
	
}
