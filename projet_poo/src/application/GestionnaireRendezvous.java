package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GestionnaireRendezvous implements ServiceRendezvous {
	private Connection connection;

    public GestionnaireRendezvous(Connection connection) {
        this.connection = connection;
    }

    public void ajouterRendezVous(int patientId, String heureRdv, String dateRdv) throws SQLException {
        String query = "INSERT INTO RendezVous (patient_id, heure_de_rendezvous, date_de_rendezvous, done) VALUES (?, ?, ?, FALSE)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            statement.setString(2, heureRdv);
            statement.setString(3, dateRdv);
            statement.executeUpdate();
        }
    }

 
    public void marquerRendezVousCommeFait(int rendezVousId) throws SQLException {
        String query = "UPDATE RendezVous SET done = TRUE WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, rendezVousId);
            statement.executeUpdate();
        }
    }

  
    public void modifierDateHeureRendezVous(int rendezVousId, String heureRdv, String dateRdv) throws SQLException {
        String query = "UPDATE RendezVous SET heure_de_rendezvous = ?, date_de_rendezvous = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, heureRdv);
            statement.setString(2, dateRdv);
            statement.setInt(3, rendezVousId);
            statement.executeUpdate();
        }
    }
    public void listerRendezVous(int patientId) throws SQLException {
        String query = "SELECT id, heure_de_rendezvous, date_de_rendezvous, done FROM RendezVous WHERE patient_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Liste des rendez-vous pour le patient " + patientId + ":");
                while (resultSet.next()) {
                    int rendezVousId = resultSet.getInt("id");
                    String heureRdv = resultSet.getString("heure_de_rendezvous");
                    String dateRdv = resultSet.getString("date_de_rendezvous");
                    boolean done = resultSet.getBoolean("done");

                    System.out.println("Rendez-vous ID: " + rendezVousId);
                    System.out.println("Heure du rendez-vous: " + heureRdv);
                    System.out.println("Date du rendez-vous: " + dateRdv);
                    System.out.println("Terminé: " + (done ? "Oui" : "Non"));
                    System.out.println();
                }
            }
        }
    }
   

}
