package data;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -7657408796442402380L;
	
	private int id;
	private String license;
	private String vorname;
	private String nachname;
	private String telephon;
	private Functions functions;
	
	public User(int id, String license, String vorname, String nachname, String telephon, Functions functions) {
		this.id = id;
		this.license = license;
		this.vorname = vorname;
		this.nachname = nachname;
		this.telephon = telephon;
		this.functions = functions;
	}

	public int getID() {
		return id;
	}

	public String getLicense() {
		return license;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getTelephon() {
		return telephon;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public void setTelephon(String telephon) {
		this.telephon = telephon;
	}

	public Functions getFunctions() {
		return functions;
	}

	public void setFunctions(Functions functions) {
		this.functions = functions;
	}
	
}