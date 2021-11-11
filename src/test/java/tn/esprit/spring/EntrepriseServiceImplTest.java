package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);

	@Autowired
	IEntrepriseService ientrepriseservice;
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	Integer idE;
	
	
	@Test
	public void testAjouterEntreprise()  { 
		 l.debug("methode ajouterEntreprise");
		 try {
		 idE=ientrepriseservice.ajouterEntreprise(new Entreprise("test_nom_entreprise","test_raison_sociale "));
		assertNotNull(idE);
		 } catch (Exception e) {
		       l.error("erreur methode ajouterEntreprise :" +e);	

			}
	}
	

	@Test
	public void testGetAllDepartementsNamesByEntreprise() {
		 l.debug("methode GetAllDepartementNAmesByEntreprise");
		List<String> depNames = ientrepriseservice.getAllDepartementsNamesByEntreprise(1);
		assertNotNull(depNames);
	}	
	

	@Test
	public void testRetrieveAlllistEntreprises() {
		l.debug("methode testRetrieveAlllistEntreprises");
		List<Entreprise> listEntreprises = ientrepriseservice.retrieveAllEntreprises(); 
		assertNotNull(listEntreprises);
	}	
	
	@Test
	public void testRetrieveEntreprise() {
		l.debug("methode testRetrieveAlllistEntreprises");
		Entreprise entrepriseRetrieved = ientrepriseservice.retrieveEntreprise("1"); 
		assertEquals(1L, entrepriseRetrieved.getId());
	}
	
	
}