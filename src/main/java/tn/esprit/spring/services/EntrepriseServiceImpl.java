package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

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

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
	}
	
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.debug("methode getAllDepartementsNamesByEntreprise ");
		List<String> depNames = new ArrayList<String>();
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

	@Transactional
	public int deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).orElse(null));
		return depId;	
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

}
