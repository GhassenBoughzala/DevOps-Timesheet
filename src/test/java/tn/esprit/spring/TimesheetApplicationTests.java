package tn.esprit.spring;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.controller.ControllerEmployeImpl;
import tn.esprit.spring.controller.ControllerEntrepriseImpl;
import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;


@SpringBootTest
class TimesheetApplicationTests {
	private static final Logger l = LogManager.getLogger(TimesheetApplicationTests.class.getName());
	@Autowired
	ControllerEntrepriseImpl entrepriseControl;
	@Autowired
	ControllerEmployeImpl employeControl;
	@Autowired
	RestControlEmploye RestemployeControl;
	
	@Test
	void contextLoads() {

		
		  Entreprise ssiiConsulting = new Entreprise("SSII Consulting","Cite El Ghazela"); 
		  Departement depTelecom = new Departement("Telecom");
		  Departement depRH = new Departement("RH");
		  ssiiConsulting.addDepartement(depRH);
		  ssiiConsulting.addDepartement(depTelecom); 
		  int ssiiConsultingId =entrepriseControl.ajouterEntreprise(ssiiConsulting);
		  ssiiConsulting.setId(ssiiConsultingId);
		  depTelecom.setEntreprise(ssiiConsulting); 
		 // int depTelecomId = entrepriseControl.ajouterDepartement(depTelecom);
		  depRH.setEntreprise(ssiiConsulting);
		 // int depRhId =entrepriseControl.ajouterDepartement(depRH);
		  
		//  entrepriseControl.affecterDepartementAEntreprise(depTelecomId,ssiiConsultingId); 
		//  entrepriseControl.affecterDepartementAEntreprise(depRhId, ssiiConsultingId);
		//  List<String> departements = entrepriseControl.getAllDepartementsNamesByEntreprise(ssiiConsultingId); 
		 // for(String departementName : departements) { l.info(departementName); }
		
		 
	/* ************************************************************* */
	
	  
  /*f4*/  //  List<Departement> listAlldepartements= entrepriseControl.getAllDepartements();
	  //  for(Departement departement : listAlldepartements) { l.info(departement.getId() +" " + departement.getName());}
 
	}
}