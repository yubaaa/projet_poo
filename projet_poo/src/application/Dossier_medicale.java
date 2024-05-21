package application;

import java.util.ArrayList;
import java.util.List;

public class Dossier_medicale {
	private List<String> antecedents;
	private List<String> allergies;
	private List<String> traitements;
	private ArrayList<RendezVous> rendezVousList;

	public List<String> getAntecedents() {
		return antecedents;
	}
	public void setAntecedents(List<String> antecedents) {
		this.antecedents = antecedents;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	public List<String> getTraitements() {
		return traitements;
	}
	public void setTraitements(List<String> traitements) {
		this.traitements = traitements;
	}
	public ArrayList<RendezVous> getRendezVousList() {
		return rendezVousList;
	}
	public void setRendezVousList(ArrayList<RendezVous> rendezVousList) {
		this.rendezVousList = rendezVousList;
	}
	
	

}
