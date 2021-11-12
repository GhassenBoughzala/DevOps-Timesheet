package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;


@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);


	public Integer ajouterEntreprise(Entreprise entreprise) {
		l.debug("methode ajouterEntreprise");
		try {
		entrepriseRepoistory.save(entreprise);
		l.info("entreprise ajoutée avec id = "+entreprise.getId());
		return entreprise.getId();
		} catch (Exception e) {
		       l.error("erreur methode ajouterEntreprise :" +e);	
		       return null;       
				}	
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {

		l.info(" methode affectation departement a entreprise");
		l.debug("chercher de l'entreprise par id ");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise entrepriseManagedEntity = value.get();

			l.debug(" trouver l'entreprise" + entrepriseManagedEntity);
			l.debug("j recherche du departement par id ");
			Optional<Departement> value1 = deptRepoistory.findById(depId);
			if (value1.isPresent()) {
				Departement depManagedEntity = value1.get();

				l.debug(" trouver le departement" + depManagedEntity);
				l.debug("update de l'ntreprise et l'enregistré");

				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);

				l.debug("'update de l'entreprise ");
				l.info("fin de   la methode ");

			}
		}
		else {
			l.debug("l'entreprise ou departement n'exite pas");
			l.info("fin de   la methode affectation departement a entreprise");

		}
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.debug("methode getAllDepartementsNamesByEntreprise ");
		List<String> depNames = new ArrayList<>();
		try {
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			
			if(entrepriseManagedEntity!=null && entrepriseManagedEntity.getDepartements()!=null){
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			l.debug("getAllDepartementsNamesByEntreprise fini avec succes ");
			return depNames;
			}
			else {
				l.error("erreur methode getAllDepartementsNamesByEntreprise : " );
				return depNames;
			}
		} catch (Exception e) {
			l.error("erreur methode getAllDepartementsNamesByEntreprise : " +e);
			return depNames;
		}
	}

	
	@Transactional
	public int deleteEntrepriseById(int entrepriseId) {
		l.debug("methode deleteEntrepriseById ");
		
		try {
			if(entrepriseRepoistory.findById(entrepriseId).orElse(null)!=null){
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).orElse(null));
			l.debug("deleteEntrepriseById fini avec succes ");
			return 0;}else {
				l.error("erreur methode deleteEntrepriseById : " );
				return -1;
			}
		} catch (Exception e) {
			l.error("erreur methode deleteEntrepriseById : " +e);
			return -1;
		}		
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		l.debug("methode getEntrepriseById ");
		
		
		try {
			Entreprise et= entrepriseRepoistory.findById(entrepriseId).orElse(null);
			l.debug("getEntrepriseById fini avec succes ");
			return et;
		} catch (Exception e) {
			l.error("erreur methode getEntrepriseById : " +e);
			return null;
		}	
		
		
	}

	@Override
	public Entreprise retrieveEntreprise(String id) {
		l.info("in  retrieveEntreprise id = " + id);
		Optional <Entreprise> e =  entrepriseRepoistory.findById((int) Long.parseLong(id)); 
		Entreprise enp = new Entreprise();
		if (e.isPresent()) {
			enp=e.get();
			l.info("entreprise returned : " + enp);
			
		}
		return enp;
	}

	@Override
	public List<Entreprise> retrieveAllEntreprises() {
		List<Entreprise> entreprises = null; 
		try {
	
			l.info("In retrieveAllEntreprises() : ");
			entreprises = (List<Entreprise>) entrepriseRepoistory.findAll();  
			for (Entreprise entreprise : entreprises) {
				l.debug("entreprise +++ : " + entreprise);
			} 
			l.info("Out of retrieveAllEntreprises() : ");
		}catch (Exception e) {
			l.error("Error in retrieveAllEntreprises() : " + e);
		}

		return entreprises;
	}

}