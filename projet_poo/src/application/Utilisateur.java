package application;


public class Utilisateur {

	private String nom;
	private String prenom;
	private double num_tel;
	private String email;
	private int id;

	public Utilisateur(String nom, String prenom, double num_tel, String email,int id ) {

		this.nom = nom;
		this.prenom = prenom;
		this.num_tel = num_tel;
		this.email = email;
		this.id=id;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getNum_tel() {
		return num_tel;
	}

	public void setNum_tel(double num_tel) {
		this.num_tel = num_tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}

