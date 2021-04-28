package Util;

public enum Status {
	NA_CEKANJU ("Na \u010dekanju"),
	ISPORUKA_U_TOKU ("Isporuka u toku"),
	ISPORUCENO ("Isporuceno");

	private String o;
	
	Status(String s) {
		this.o = s;
	}
	
	public String toString() {
		return o;
	}
}
