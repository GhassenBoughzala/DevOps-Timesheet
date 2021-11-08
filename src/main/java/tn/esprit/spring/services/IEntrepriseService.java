package tn.esprit.spring.services;

import java.util.List;
import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	
	public Integer ajouterEntreprise(Entreprise entreprise);
	List<Entreprise> retrieveAllEntreprises(); 
	void affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	Entreprise updateEntreprise(Entreprise d);
	public int deleteEntrepriseById(int entrepriseId);
	public Entreprise getEntrepriseById(int entrepriseId);
}
