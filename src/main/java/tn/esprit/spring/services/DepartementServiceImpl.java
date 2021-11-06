package tn.esprit.spring.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	private static final Logger l = LogManager.getLogger(DepartementServiceImpl.class);

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		l.info("In getAllDepartements()");
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}
	
	@Transactional
	public Integer deleteDepartementById(int depId) {
		try {
			l.info("In deleteDepartementById()");
			Optional<Departement> departement=deptRepoistory.findById(depId);
			if(departement.isPresent()) {
			l.debug("je vais supprimer le département par son id:"+depId);
		    deptRepoistory.delete(departement.get());
			l.debug("Département supprimé avec succés");
			l.info("Out deleteDepartementById()");
			}
			return 1;
			
		}
		catch (Exception e) {
			l.error("erreur dans la methode deleteDepartementById() :"+e);
			return 0;
		}

		 
	}



}
