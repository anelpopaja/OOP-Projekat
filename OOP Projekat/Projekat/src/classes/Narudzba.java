package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Util.Status;
import database.Database;

public class Narudzba {

	private int id, kupacId, trgovacId;
	private Kupac kupac;
	private Trgovac trgovac;
	private Date datumNarudzbe, datumIsporuke;
	private String napomena;
	private Status status;
	
	private static ArrayList<Narudzba> listaNarudzbi;
	

	public Narudzba(int id, int kupacId, int trgovacId, Date datumNarudzbe, Date datumIsporuke, String napomena) {
		this.id = id;
		this.kupacId = kupacId;
		this.trgovacId = trgovacId;
		this.datumNarudzbe = datumNarudzbe;
		this.datumIsporuke = datumIsporuke;
		this.napomena = napomena;
		if(trgovacId == 0)
			this.status = Status.NA_CEKANJU;
		else if(datumIsporuke == null) 
			this.status = Status.ISPORUKA_U_TOKU;
		else
			this.status = Status.ISPORUCENO;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKupacId() {
		return this.kupacId;
	}

	public void setKupacId(int kupacId) {
		this.kupacId = kupacId;
	}

	public int getTrgovacId() {
		return this.trgovacId;
	}

	public void setTrgovacId(int trgovacId) {
		this.trgovacId = trgovacId;
	}

	public Date getDatumNarudzbe() {
		return this.datumNarudzbe;
	}

	public void setDatumNarudzbe(Date datumNarudzbe) {
		this.datumNarudzbe = datumNarudzbe;
	}

	public Date getDatumIsporuke() {
		return this.datumIsporuke;
	}

	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}

	public String getNapomena() {
		return this.napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Kupac getKupac() {
		for(Kupac k : Kupac.getListaKupaca())
		if(k.getId() == this.kupacId) {
			this.kupac = k;
			break;
		}
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Trgovac getTrgovac() {
		for(Trgovac t : Trgovac.getListaTrgovaca()) {
			if (t.getId() == trgovacId) {
				this.trgovac = t;
				break;
			}
		}
		return this.trgovac;
	}

	public void setTrgovac(Trgovac trgovac) {
		this.trgovac = trgovac;
	}
	

	public static ArrayList<Narudzba> getListaNarudzbi() {
		return listaNarudzbi;
	}
	
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static ArrayList<Narudzba> importNarudzbe() {
		listaNarudzbi = new ArrayList<Narudzba>();
		try {
			Database d = new Database();
			String query1 = "SELECT * FROM narudzba";
			Statement st;
			st = d.getConn().createStatement();
			ResultSet rs = st.executeQuery(query1);
			Narudzba narudzba;
			while (rs.next()) {
				narudzba = new Narudzba(rs.getInt("id"), rs.getInt("kupac_id"), rs.getInt("trgovac_id"),
						rs.getDate("datum_narudzbe"), rs.getDate("datum_isporuke"), rs.getString("napomena"));

				listaNarudzbi.add(narudzba);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return listaNarudzbi;
	}
	
	public Status statusNarudzbe() {
		if(datumIsporuke == null)
			return Status.NA_CEKANJU;
		else if(datumIsporuke.after(new Date()))
			return Status.NA_CEKANJU;
		return Status.ISPORUCENO;
	}
	
	

	@Override
	public String toString() {
		return "Narudzba [id=" + id + ", kupacId=" + kupacId + ", trgovacId=" + trgovacId + ", kupac=" + kupac
				+ ", trgovac=" + trgovac + ", datumNarudzbe=" + datumNarudzbe + ", datumIsporuke=" + datumIsporuke
				+ ", napomena=" + napomena + "]\n";
	}

}
