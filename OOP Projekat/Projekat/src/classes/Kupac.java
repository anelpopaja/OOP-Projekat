package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;

public class Kupac extends Korisnik{
	private static ArrayList<Kupac> listaKupaca = new ArrayList<Kupac>();
	
	private String adresa, grad, drzava, postanski_broj;
	private ArrayList<Narudzba> listaNarudzbiKupca;
	
	public Kupac(int id, String korisnicko_ime, String ime, String prezime, String lozinka, String pol,
			String telefon, String email, String adresa, String grad, String drzava, String postanski_broj) {
		super(id, korisnicko_ime, ime, prezime, lozinka, pol, telefon, email);
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.postanski_broj = postanski_broj;
		fillListaNarudzbi(this);
	}
	
	public static ArrayList<Kupac> importKupce() {
		try {
			Database d = new Database();
			String query1 = "SELECT * FROM `seminarski_ors1`.`kupac`";
			Statement st;
			st = d.getConn().createStatement();
			ResultSet rs = st.executeQuery(query1); 
			Kupac kupac;
			while(rs.next()) {
				kupac = new Kupac(rs.getInt("id"), rs.getString("korisnicko_ime"), rs.getString("ime"), rs.getString("prezime"),
						rs.getString("lozinka"), rs.getString("pol"), rs.getString("telefon"),  rs.getString("email"),
						rs.getString("adresa"), rs.getString("grad"),  rs.getString("drzava"), rs.getString("postanski_broj"));
				listaKupaca.add(kupac);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaKupaca; 
	}
	public static void fillListaNarudzbi(Kupac k) {
		k.listaNarudzbiKupca = new ArrayList<Narudzba>();
		for(Narudzba temp: Narudzba.getListaNarudzbi()) {
			if(temp.getKupacId() == k.getId())
				k.listaNarudzbiKupca.add(temp);
		}
	}
	

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public ArrayList<Narudzba> getListaNarudzbiKupca() {
		return listaNarudzbiKupca;
	}
	public static void updateListaNarudzbiKupca(Kupac k) {
		//importing new list of orders
		Narudzba.importNarudzbe();
		//filling new list of orders for a patron
		fillListaNarudzbi(k);
	}

	public void setListaNarudzbiKupca(ArrayList<Narudzba> listaNarudzbi) {
		this.listaNarudzbiKupca = listaNarudzbi;
	}

	public String getPostanski_broj() {
		return postanski_broj;
	}

	public void setPostanski_broj(String postanski_broj) {
		this.postanski_broj = postanski_broj;
	}
	
	
	public static ArrayList<Kupac> getListaKupaca() {
		return listaKupaca;
	}

	public static void setListaKupaca(ArrayList<Kupac> listaKupaca) {
		Kupac.listaKupaca = listaKupaca;
	}
	

	@Override
	public String toString() {
		return super.toString() + "adresa=" + adresa + ", grad=" + grad + ", drzava=" + drzava + ", postanski_broj="
				+ postanski_broj + "]\n";
	}
}
