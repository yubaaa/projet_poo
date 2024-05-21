package application;


import java.sql.SQLException;

public interface ServiceSecretaire {
	public void ajouterpatient(String nom, String prenome, double num_tel,String email,int id_patient, String date_de_naissance, String sex)throws SQLException;
	public void supprimerPatient(int id_patient)throws SQLException;
	public void modifierPatient(int id_patient, String nouveauNom, String nouveauPrenom, double nouveauNumTel,String nouveauemail, String nouvelleDateDeNaissance, String nouveauSexe)throws SQLException;

}
