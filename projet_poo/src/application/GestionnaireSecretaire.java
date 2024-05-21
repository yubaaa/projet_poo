package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestionnaireSecretaire implements ServiceSecretaire {
	private Connection connection;

	public GestionnaireSecretaire(Connection connection) {
		this.connection = connection;
	}
	
	public void ajouterpatient(String nom, String prenom, double num_tel,String email,int id_patient, String date_de_naissance, String sex)throws SQLException{
		String query = "INSERT INTO Utilisatuer(nom, prenom, num_tel,email,id_patient) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, nom);
			statement.setString(2, prenom);
			statement.setDouble(3, num_tel);
			statement.setString(4, email);
			statement.setInt(5, id_patient);
			statement.executeUpdate();
		}
		String query1 = "INSERT INTO Patient(id_patient,date_de_naissance,sex) VALUES (?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query1)) {
			statement.setInt(1, id_patient);
			statement.setString(2,date_de_naissance );
			statement.setString(3,sex);
			statement.executeUpdate();
		}
		
	}
    
	public void supprimerPatient(int id_patient)throws SQLException{
        String query = "DELETE  FROM Patient WHERE id_patient = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id_patient);
            statement.executeUpdate();
        }
    }


	public void modifierPatient(int id_patient, String nouveauNom, String nouveauPrenom, double nouveauNumTel,String nouveauemail, String nouvelleDateDeNaissance, String nouveauSexe)throws SQLException{
        String query = "UPDATE Utilisatuer SET nom = ?, prenom = ?, num_tel = ?,email=?  WHERE id = id_patient ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nouveauNom);
            statement.setString(2, nouveauPrenom);
            statement.setDouble(3, nouveauNumTel);
            statement.setString(4, nouveauemail);

        }
        String query1 = "UPDATE Patient SET DATE_DE_NAISSANCE =? ,SEXE=?  WHERE id = id_patient ";
        try (PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1,nouvelleDateDeNaissance);
            statement.setString(2,nouveauSexe );
        }
	}
}
