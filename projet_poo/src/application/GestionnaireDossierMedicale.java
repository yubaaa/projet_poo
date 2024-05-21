package poo.INT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionnaireDossierMedicale implements Service_Dossier_Medicale {
	private Connection connection;

	public GestionnaireDossierMedicale(Connection connection) {
		this.connection = connection;
	}

	public void ajouterAntecedent(String antecedent, int idPatient) throws SQLException {
		String query = "INSERT INTO Antecedent(antecedent, patient_id) VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, antecedent);
			statement.setInt(2, idPatient);
			statement.executeUpdate();
		}
	}

	public void ajouterAllergie(String allergie, int idPatient) throws SQLException {
		String query = "INSERT INTO Allergie(allergie, patient_id) VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, allergie);
			statement.setInt(2, idPatient);
			statement.executeUpdate();
		}
	}

	public void ajouterTraitement(String traitement, int idPatient) throws SQLException {
		String query = "INSERT INTO Traitement(traitement, patient_id) VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, traitement);
			statement.setInt(2, idPatient);
			statement.executeUpdate();
		}
	}

	public void afficherDossierMedical(int patientId) throws SQLException {
		// Retrieve patient information
		String patientQuery = "SELECT nom, prenom, date_de_naissance, sex FROM Patient JOIN users ON Patient.id = Utilisatuer.id WHERE Patient.id = ?";
		String patientName = null;
		String patientDateOfBirth = null;
		String patientSex = null;
		try (PreparedStatement patientStatement = connection.prepareStatement(patientQuery)) {
			patientStatement.setInt(1, patientId);
			try (ResultSet patientResultSet = patientStatement.executeQuery()) {
				if (patientResultSet.next()) {
					patientName = patientResultSet.getString("nom") + " " + patientResultSet.getString("prenom");
					patientDateOfBirth = patientResultSet.getString("date_de_naissance");
					patientSex = patientResultSet.getString("sex");
				}
			}
		}

		// patient information
		System.out.println("Patient Information:");
		System.out.println("Name: " + patientName);
		System.out.println("Date of Birth: " + patientDateOfBirth);
		System.out.println("Sex: " + patientSex);

		// Retrieve and display medical records
		// Antecedents
		System.out.println("\nAntecedents:");
		String antecedentQuery = "SELECT antecedent FROM Antecedent WHERE ID_PATIENT = ?";
		try (PreparedStatement antecedentStatement = connection.prepareStatement(antecedentQuery)) {
			antecedentStatement.setInt(1, patientId);
			try (ResultSet antecedentResultSet = antecedentStatement.executeQuery()) {
				while (antecedentResultSet.next()) {
					System.out.println(antecedentResultSet.getString("antecedent"));
				}
			}
		}

		// Allergies

		System.out.println("\nAllergies:");
		String allergiesQuery = "SELECT allergies FROM Allergie WHERE ID_PATIENT = ?";
		try (PreparedStatement allergiesStatement = connection.prepareStatement(allergiesQuery)) {
			allergiesStatement.setInt(1, patientId);
			try (ResultSet allergiesResultSet = allergiesStatement.executeQuery()) {
				while (allergiesResultSet.next()) {
					System.out.println(allergiesResultSet.getString("allergies"));
				}
			}
		}

		// Treatments

		System.out.println("\nTraitements:");
		String treatmentsQuery = "SELECT traitements FROM Traitement WHERE ID_PATIENT = ?";
		try (PreparedStatement treatmentsStatement = connection.prepareStatement(treatmentsQuery)) {
			treatmentsStatement.setInt(1, patientId);
			try (ResultSet treatmentsResultSet = treatmentsStatement.executeQuery()) {
				while (treatmentsResultSet.next()) {
					System.out.println(treatmentsResultSet.getString("traitements"));
				}
			}
		}
	}

}
