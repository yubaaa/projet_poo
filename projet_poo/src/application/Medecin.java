package application;

public class Medecin extends Utilisateur {
	private String specialty;
	
	public Medecin(String nom, String prenom,double num_tel,String email,int id,String specialty) {
		super(nom, prenom,num_tel,email,id);
		this.specialty=specialty;
	}
	public String getSpecialty() {
		return specialty;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

}