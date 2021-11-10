package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	
	
	
	void affecterDepartementAEntreprise(int depId, int entrepriseId);

	public void deleteDepartement(int depId);
	public List<Departement> getAllDepartements();
	Departement updateDepartement(Departement d);
	Departement addDepartement(Departement d);



}
