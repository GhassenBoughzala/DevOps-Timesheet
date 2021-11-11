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
	//Ajout d'une departement
	public int ajouterDepartement(Departement dep) {
		l.info("lancer  la methode ajouter departement");
		l.debug("je vais lancer la methode save du departement");
		
		deptRepoistory.save(dep);
		
		l.debug("je viens de finir save de departement");
		l.info("fin de  la methode ajouter departement");
		return dep.getId();
	}
	
	//Affectation d'une departement à une entreprise
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
		//Désaffectation d'une département à une entreprise
		@Transactional
		public Departement desaffecterDepartementDuEntreprise (int depId , int entId){
			try {
				l.info("In desaffecterDepartementDuEntreprise :  ");
				Entreprise ent = entrepriseRepoistory.findById(entId).orElse(null);
				l.info("Entreprise récupérer avec succés");
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
				l.info("depManagedEntity récupérer avec succés");
				if (depManagedEntity != null){
			  if (depManagedEntity.getEntreprise() == ent )
			    {
				l.debug("Désaffectation d'un département d'un entreprise ");
				depManagedEntity.setEntreprise(null);
				l.info("Département désaffecté avec succés ");
			    }
		
			    l.info("Out of desaffecterDepartementDuEntreprise  ");
			    return depManagedEntity ;
				}
				return null; 
		
			}catch (Exception e) {
				l.error("erreur In affecterDepartementAEntreprise() " + e);
				return null ; 

			}

			}
		//Récupération de la liste des département
		public List<Departement> getAllDepartements() {
			ArrayList<Departement> list=new ArrayList<>();

	    	try{
				l.info("In getAllDepartements()");
				l.debug("Je vais récupérer la liste département");
				list.add( (Departement) deptRepoistory.findAll());
				l.debug("La liste de département est récupéré avec succés");
				l.info("Out getAllDepartements()");		
				return list;
	    	} catch (Exception e) {
				l.error("erreur dans la methode getAllDepartements() :"+e);
				list.clear();
				return list;
			}		
		}
		@Transactional
		public void deleteDepartementById(int depId) {
			l.info("lancer  la methode delete department by id");
			l.debug("je vais lancer  la methode delete departement by id");
			Optional<Departement> value = deptRepoistory.findById(depId);
			if (value.isPresent()) {
				Departement dep=value.get();
			deptRepoistory.delete(dep);
			
			l.debug("je viens de finir la delete departement by id");
			l.info("fin de  la methode delete department by id");
			}
			else {
				l.debug("le departement n'existe pas");
				l.info("fin de  la methode delete department by id");
			}
		}
		
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.info("lancer  la methode get all department names by entreprise");
		l.debug("lancer  la recherche de l entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) 
		
		{Entreprise entrepriseManagedEntity= value.get();
			
		l.debug("je viens de trouver l entreprise" +entrepriseManagedEntity);
		List<String> depNames = new ArrayList<>();
		l.debug("je vais lancer  la boucle sur tous les departements et ajouter le nom du departementt au tableau depNames");
		
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		l.debug("je viens de remplir le tableau depNames");
		l.info("fin de   la methode get all department names by entreprise");
		return depNames;
		}
		else
		{l.debug("l'entreprisee n'existe pas");
		l.info("fin de   la methode get all department names by entreprise");
		
		return new ArrayList<>();
		}
	}

	public Departement findById(int id) {
		Optional<Departement> departementOptinal = deptRepoistory.findById(id);
		if (departementOptinal.isPresent()) {
			return departementOptinal.get();
		}
		return new Departement();
	}

}