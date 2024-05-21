package application;

public class RendezVous {
	private static int count = 0; // Pour générer des identifiants uniques
	private int id;
	private String heure_de_rendezvous;
	private String date_de_rendezvous;
	private Patient patient;
	
	public RendezVous(String heure_de_rendezvous, String date_de_rendezvous, boolean done) {
		count++;
		this.id = count;
		this.heure_de_rendezvous = heure_de_rendezvous;
		this.date_de_rendezvous = date_de_rendezvous;
		done = false;
	}

	public String getHeure_de_rendezvous() {
		return heure_de_rendezvous;
	}

	public void setHeure_de_rendezvous(String heure_de_rendezvous) {
		this.heure_de_rendezvous = heure_de_rendezvous;
	}

	public String getDate_de_rendezvous() {
		return date_de_rendezvous;
	}

	public void setDate_de_rendezvous(String date_de_rendezvous) {
		this.date_de_rendezvous = date_de_rendezvous;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
