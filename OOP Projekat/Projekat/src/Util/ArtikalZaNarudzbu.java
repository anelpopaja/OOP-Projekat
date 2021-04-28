package Util;

@SuppressWarnings("rawtypes")
public class ArtikalZaNarudzbu implements Comparable{
	private int id;
	private int kolicina;
	private String naziv;
	private double cijena;
	public ArtikalZaNarudzbu(int id, String naziv, double cijena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cijena = cijena;
		this.kolicina = 1;
	}
	public int getId() {
		return id;
	}
	public String getNaziv() {
		return naziv;
	}
	public double getCijena() {
		return cijena;
	}
	
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	@Override
	public String toString() {
		return "ArtikalZaNarudzbu [id=" + id + ", kolicina=" + kolicina + ", naziv=" + naziv + ", cijena=" + cijena
				+ "]";
	}
	@Override
	public int compareTo(Object o) {
		if(this.getId() == ((ArtikalZaNarudzbu) o).getId())
			return 1;
		return 0;
	}
		
	
}
