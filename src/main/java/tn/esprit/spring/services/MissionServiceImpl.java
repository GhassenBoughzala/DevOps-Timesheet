package tn.esprit.spring.services;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class MissionServiceImpl implements IMissionService {

	@Autowired
	MissionRepository MissionRepository;
	@Autowired
	DepartementRepository DepRepository;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger l = LogManager.getLogger(MissionServiceImpl.class);
	
	
		@Override
		public List<Mission> retrieveAllMission() {
			l.info("**GET ALL MISSIONS**");
			List<Mission> ms = null; 
			try {
		
				l.info("In retrieveAllMissions() : ");
				ms = (List<Mission>) MissionRepository.findAll();  
				for (Mission Mission : ms) {
					l.debug("Mission +++ : " + Mission);
				} 
				l.info("Out of retrieveAllMissions() : ");
			}catch (Exception e) {
				l.error("Error in retrieveAllMissions() : " + e);
			}
	
			return ms;
		}
	
		@Override
		public Mission addMission(Mission m) {
			l.info("**ADDING MISSION**");
			return MissionRepository.save(m); 
			
		}
	
		@Override
		public void deleteMission(String id) {
			MissionRepository.deleteById(Long.parseLong(id));
			
		}
	
		@Override
		public Mission updateMission(Mission m) {
			return MissionRepository.save(m); 
		}
	
		@Override
		public Mission retrieveMission(String id) {
			l.info("in  retrieveMission id = " + id);
			Mission d =  MissionRepository.findById(Long.parseLong(id)).get(); 
			l.info("Mission returned : " + d);
			return d; 
		}

		@Override
		public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
			l.info("**MISSION & EMP**");
			return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
		}

		@Override
		public List<Employe> getAllEmployeByMission(int missionId) {
			l.info("** EMP BY MISSION **");
			return timesheetRepository.getAllEmployeByMission(missionId);
		}

		@Override
		public void affecterMissionADepartement(int missionId, int depId) {
			l.info("**MISSION & DEP**");
			Mission mission = MissionRepository.findById((long) missionId).orElse(null);
			l.info("MissionID = " + mission);
			Departement dep = DepRepository.findById(depId).orElse(null);
			l.info("DepartementID = " + dep);
			if(mission!=null && dep !=null){
			mission.setDepartement(dep);
			MissionRepository.save(mission);
			}
  }
}
