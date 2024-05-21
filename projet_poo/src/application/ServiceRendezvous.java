package application;

import java.sql.SQLException;
public interface ServiceRendezvous {
	public void ajouterRendezVous(int patientId, String heureRdv, String dateRdv) throws SQLException;
    public void listerRendezVous(int patientId) throws SQLException;
    public void marquerRendezVousCommeFait(int rendezVousId) throws SQLException;
    public void modifierDateHeureRendezVous(int rendezVousId, String heureRdv, String dateRdv) throws SQLException;
}
