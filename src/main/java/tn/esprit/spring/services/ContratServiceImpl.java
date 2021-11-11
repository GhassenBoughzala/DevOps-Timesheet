package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@Service
public class ContratServiceImpl implements IContratService {

 
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EmployeRepository employeRepository;

	private static final Logger l = LogManager.getLogger(ContratServiceImpl.class);

	public List<Contrat> getAllContrats() {
		l.info("In getAllContrats() : ");
		
		l.debug("Accéss à la base de données");
		l.debug("Récupération de la liste des contrats");
		
		l.info("Out getAllContrats() ");
		return (List<Contrat>) contratRepository.findAll();
	}

	public Integer ajouterContrat(Contrat contrat) {
		l.debug("In ajouterContrat");
		try{
			contratRepository.save(contrat);
			l.info("Contrat ajouter avec ref = "+contrat.getReference());
			l.debug("Out ajouterContrat");
			return contrat.getReference();
		} catch (Exception e) {
			l.error("erreur dans la methode ajouterContrat :"+e);
			return null;
		}
	}

	public Contrat affecterContratAEmploye(int contratId, int employeId) {
		try {
			l.info("In affecterContratAEmploye : ");
			Contrat contratManagedEntity = contratRepository.findById(contratId).orElse(null);
			l.debug("Contrat par identifiant");
			Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
			l.debug("Employe par identifiant");

			if (contratManagedEntity != null) {
				l.debug("In If ");
				contratManagedEntity.setEmploye(employeManagedEntity);
				l.info("L'affectation a été faite");
				Contrat c = contratRepository.save(contratManagedEntity);
				l.info("Out of affecterContratAEmploye ");
				return c;
			}
			return null;
		} catch (Exception e) {
			l.error("erreur In affecterContratAEmploye() : Failed to affect " + e);
			return null;
		}

	}

public int deleteContratById(int contratId) {
		l.debug("In deleteContratById ");
		try {
			if (contratRepository.findById(contratId).orElse(null) != null) {
				contratRepository.delete(contratRepository.findById(contratId).orElse(null));
				return 0;
			} else {
				l.error("erreur methode deleteContratById : ");
				return -1;
			}

		} catch (Exception e) {
			l.error("erreur methode deleteContratById :" + e);
			return -1;

		}

	}
	public void deleteAllContratJPQL() {
		l.debug("In deleteAllContratJPQL ");
		employeRepository.deleteAllContratJPQL();
		l.info("Liste de contrats a été supprimer");
	}

}