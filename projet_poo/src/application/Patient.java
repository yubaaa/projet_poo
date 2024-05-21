package application;

public class Patient extends Utilisateur {

	private String date_de_naissance;
	private String sex;

	public Patient(String nom, String prenom, double num_tel, String email, int id, String date_de_naissance,
			String sex) {
		super(nom, prenom, num_tel, email, id);
		this.date_de_naissance = date_de_naissance;
		this.sex = sex;

	}

	public String getDate_de_naissance() {
		return date_de_naissance;
	}

	public void setDate_de_naissance(String date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
