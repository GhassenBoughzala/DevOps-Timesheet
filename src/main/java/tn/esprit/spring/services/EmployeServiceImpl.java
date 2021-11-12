package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.log4j.Logger;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger l = Logger.getLogger(EmployeServiceImpl.class);

	public Employe authenticate(String login, String password) {
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = employeRepository.findById(employeId).orElse(null);
		if(employe!=null){
		employe.setEmail(email);
		employeRepository.save(employe);
		
		}
		
		
	}


	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional <Departement> depManagedEntity = deptRepoistory.findById(depId);
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		Departement dep;
		Employe emp = new Employe();

		if (depManagedEntity.isPresent()) {
			
			dep=depManagedEntity.get();
		if(dep.getEmployes() == null){
			
			if (employeManagedEntity.isPresent()) {
				emp = employeManagedEntity.get();
				List<Employe> employes = new ArrayList<>();
				employes.add(emp);
				dep.setEmployes(employes);
			}
			
		}else{
			
			dep.getEmployes().add(emp);
		}

		deptRepoistory.save(dep); 

	}}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Optional <Departement> dep = deptRepoistory.findById(depId);
		Departement dept;
		
		if (dep.isPresent()) {
			dept = dep.get();
			int employeNb = dept.getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dept.getEmployes().get(index).getId() == employeId){
					dept.getEmployes().remove(index);
					break;
				}
			}
		}
	
	} 
	

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Optional <Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		Optional <Employe> employeManagedEntity = employeRepository.findById(employeId);
		Contrat cont = new Contrat();
		Employe emp = new Employe();
		if (contratManagedEntity.isPresent()) {
			cont =contratManagedEntity.get();
			if (employeManagedEntity.isPresent()) {
				emp = employeManagedEntity.get();

			}
			cont.setEmploye(emp);

		}
		contratRepoistory.save(cont);

	}

	public String getEmployePrenomById(int employeId) {
		Optional <Employe> employeManagedEntity = employeRepository.findById(employeId);
		Employe emp = new Employe();
		if (employeManagedEntity.isPresent()) {
			emp = employeManagedEntity.get();
			
		}
		return emp.getPrenom();

	}
	 
	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> employe = employeRepository.findById(employeId);
		Employe emp;
		
		if (employe.isPresent()) {
			emp = employe.get();
			for(Departement dep : emp.getDepartements()){
				dep.getEmployes().remove(emp);
			}

			employeRepository.delete(emp);
		}
		
		
	}

	public void deleteContratById(int contratId) {
		Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		Contrat cont;
		if (contratManagedEntity.isPresent()) {
			cont = contratManagedEntity.get();
			contratRepoistory.delete(cont);

		}

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() { 
		return (List<Employe>) employeRepository.findAll();
	}
	
	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	@Override
	public Employe getEmployerById(int id) {
           l.debug("methode getEmployeById ");
		
		
		try {
			Employe et= employeRepository.findById(id).orElse(null);
			l.debug("getEmployeById fini avec succes ");
			return et;
		} catch (Exception e) {
			l.error("erreur methode getEmployeById : " +e);
			return null;
		}	
	}

}