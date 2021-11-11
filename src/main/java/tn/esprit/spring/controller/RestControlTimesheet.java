package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.MissionDTO;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.IMissionService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.services.IDepartementService;

@RestController
public class RestControlTimesheet {


	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	@Autowired
	IMissionService imissionservice;
	@Autowired
	IDepartementService idepartementService;
	
	// http://localhost:8081/SpringMVC/servlet/ajouterMission
	
	@PostMapping("/ajouterMission")
	@ResponseBody
	public int ajouterMission(@RequestBody MissionDTO missionDTO) {
		Mission persistentMission = mapIntoPersistentMission(missionDTO);
		imissionservice.addMission(persistentMission);
		return persistentMission.getId();
	}
	
	public Mission mapIntoPersistentMission(MissionDTO missionDTO) {
		Mission persistentMission = new Mission();
		Departement departement = idepartementService.findById(missionDTO.getDepartementIdDTO());
		persistentMission.setId(missionDTO.getIdDTO());
		persistentMission.setName(missionDTO.getNameDTO());
		persistentMission.setDescription(missionDTO.getDescriptionDTO());
		persistentMission.setDepartement(departement);
		persistentMission.setTimesheets(missionDTO.getTimesheetsDTO());
		return persistentMission;
	}
	
	@PutMapping(value = "/affecterMissionADepartement/{idmission}/{iddept}") 
	public void affecterMissionADepartement(@PathVariable("idmission") int missionId, @PathVariable("iddept") int depId) {
		imissionservice.affecterMissionADepartement(missionId, depId);
	}
	
	// http://localhost:8081/SpringMVC/servlet/ajouterTimesheet
    
	
	@PostMapping("/ajouterTimesheet/idmission/idemp/dated/datef")
	@ResponseBody
	public void ajouterTimesheet(@PathVariable("idmission") int missionId, @PathVariable("idemp") int employeId, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin) {
		itimesheetservice.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);
	}

	@PutMapping(value = "/validerTimesheet/{idmission}/{idemp}/{dated}/{datef}/{idval}") 
	public void validerTimesheet(@PathVariable("idmission") int missionId, @PathVariable("idemp") int employeId, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin, @PathVariable("idval") int validateurId) {
		itimesheetservice.validerTimesheet(missionId, employeId, dateDebut, dateFin, validateurId);
	}
	
   @GetMapping(value = "findAllMissionByEmployeJPQL/{idemp}")
    @ResponseBody
	public List<Mission> findAllMissionByEmployeJPQL(@PathVariable("idemp") int employeId) {

		return imissionservice.findAllMissionByEmployeJPQL(employeId);
	}

     @GetMapping(value = "getAllEmployeByMission/{idmission}")
    @ResponseBody
	public List<Employe> getAllEmployeByMission(@PathVariable("idmission") int missionId) {

		return imissionservice.getAllEmployeByMission(missionId);
	}
}
