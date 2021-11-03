package tn.esprit.spring.services;


import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.MissionDTO;

public interface IMissionService {

	public int ajouterMission(Mission mission);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
	public void affecterMissionADepartement(int missionId, int depId);
}
