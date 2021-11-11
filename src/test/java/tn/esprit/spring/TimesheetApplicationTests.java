package tn.esprit.spring;

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
		  depRH.setEntreprise(ssiiConsulting);

 
	}
}