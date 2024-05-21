package application;

import java.sql.SQLException;
//import poo.RendezVous;
public interface Service_Dossier_Medicale {
	public void ajouterAntecedent(String antecedent,int id_patient)throws SQLException;
	public void ajouterAllergie(String allergie,int id_patient)throws SQLException;
	public void ajouterTraitement(String traitement,int id_patient)throws SQLException;
	//public void ajouterRendezVous(RendezVous rendezVous,int id_patient)throws SQLException;
	public void afficherDossierMedical(int id_patient)throws SQLException; 
}
