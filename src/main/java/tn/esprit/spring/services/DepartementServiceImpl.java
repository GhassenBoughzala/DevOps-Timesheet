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
	
	//Affectation d'une département à une entreprise
		public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
			         try {


						l.info("In affecterDepartementAEntreprise()");
						l.debug("Je vais récupérer le département par son id");
						Optional<Departement> departementManaged=deptRepoistory.findById(depId);
							l.debug("Je vais récupérer l'entreprise par son id");
							Optional<Entreprise> entrpriseManaged=entrepriseRepoistory.findById(entrepriseId);	
			        	 if(departementManaged.isPresent() && entrpriseManaged.isPresent()) {	

						l.debug("je vais récupérer l'entreprise par son id");
						l.debug("entreprise récupérée avec succés avec une référence :"+entrpriseManaged.get().getId());
						l.debug("je vais récupérer le departement par son id");
						Departement depManagedEntity = departementManaged.get();
						l.debug("je vais affecter l'entreprise récupérer au département ");
						depManagedEntity.setEntreprise(entrpriseManaged.get());
						l.debug("entreprise affectée à l'entreprise avec succés dont l'id est de département est"+depManagedEntity.getId());
						deptRepoistory.save(depManagedEntity);	
						l.debug("entreprise est affectée a l'entreprise avec succées,id de département est   = "+depManagedEntity.getId());
						l.info("Out ajouterDepartement()");
						
							}
					}
					catch (Exception e) {
						l.error("erreur dans la methode affecterDepartementAEntreprise() :"+e);

					}
				
					
			      
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
