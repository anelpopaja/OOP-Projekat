package classes;

public class Korisnik {
	private int id;
	private String korisnicko_ime, ime, prezime, lozinka, pol, telefon, email;
	
	public Korisnik(int id, String korisnicko_ime, String ime, String prezime, String lozinka, String pol,
			String telefon, String email) {
		this.id = id;
		this.korisnicko_ime = korisnicko_ime;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.pol = pol;
		this.telefon = telefon;
		this.email = email;
	}
	
	



	public int getId() {
		return id;
	}


	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}

	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", korisnicko_ime=" + korisnicko_ime + ", ime=" + ime + ", prezime=" + prezime
				+ ", lozinka=" + lozinka + ", pol=" + pol + ", telefon=" + telefon + ", email=" + email + "]";
	}

}
