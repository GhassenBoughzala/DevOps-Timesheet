package tn.esprit.spring.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository deptRepoistory;
    @Autowired
    EntrepriseRepository entrepriseRepoistory;
	private static final Logger l = LogManager.getLogger(DepartementServiceImpl.class);
	
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
    //Récupération d'un département par son id
	public Departement getDepartmentById(int departmentId) {
		try {
				l.info(" In getDepartementById() : ");
				
				Departement dep =  deptRepoistory.findById(departmentId).orElse(null);
				l.info(" Out of getDepartementById(). ");
				return dep ; 
		} catch (Exception e) {
			l.error("get departement operation failed");
			return null;
		}	

}
    	
	@Transactional
    //Ajout d'une département	
	public Integer ajouterDepartement(Departement dep) {
		try{
			l.info("In ajouterDepartement()");
			deptRepoistory.save(dep);
			l.debug("departement ajouté et portant la ref  = "+dep.getId());
			l.info("Out ajouterDepartement()");
			return dep.getId();
		} catch (Exception e) {
			l.error("erreur dans la methode ajouterContrat() :"+e);
			return null;
		}
		
	}
	//Affectation d'une département à une entreprise
	public Departement affecterDepartementAEntreprise(int depId, int entrepriseId) {
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
					return depManagedEntity;
						}
				}
				catch (Exception e) {
					l.error("erreur dans la methode affecterDepartementAEntreprise() :"+e);

				}
				return null;
				
		      
	}
	//Suppression d'un département par son id
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

}