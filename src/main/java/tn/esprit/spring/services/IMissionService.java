package tn.esprit.spring.services;


import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;

public interface IMissionService {

	List<Mission> retrieveAllMission(); 
	Mission addMission(Mission m);
	void deleteMission(String id);
	Mission updateMission(Mission m);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
	public void affecterMissionADepartement(int missionId, int depId);
}
