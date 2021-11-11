package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	private static final Logger l = LogManager.getLogger(TimesheetServiceImpl.class);

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		
		l.info("In valider Timesheet");
		Optional<Employe> validateurOp = employeRepository.findById(validateurId);
		Optional<Mission> missionOp = missionRepository.findById((long) missionId);
		Employe employeva = new Employe();
		//verifier s'il est un chef de departement (interet des enum)
		if(validateurOp.isPresent()){
			employeva = validateurOp.get();
			if(!employeva.getRole().equals(Role.CHEF_DEPARTEMENT)){
				l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
			}
		}
		
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : employeva.getDepartements()){
			if(missionOp.isPresent()){
				Mission mission = missionOp.get();
				if(dep.getId() ==  mission.getDepartement().getId()){
					chefDeLaMission = true;
					break;
				}
			}			
		}
		
		if(!chefDeLaMission){
			l.info("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findByTimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
	}

	public List<Timesheet> getTimesheetsByMissionAndDate
	(Employe employe, Mission mission, Date dateDebut,Date dateFin) {

		return (timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin));
	}
}
