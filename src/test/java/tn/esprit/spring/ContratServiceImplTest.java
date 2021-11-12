package tn.esprit.spring;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe; 
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IContratService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	@Autowired
	IContratService cs;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	ContratRepository contratRepository;
	
	@Test
	public void testGetAllContrats(){
		List<Contrat> lc =cs.getAllContrats();
		assertNotNull(lc);
	}
	
	@Test
	public void testAjouterContrat() {
		Date date = new Date();
		Contrat c=new Contrat(date,"Stage",2000);
		Integer idC=cs.ajouterContrat(c);
		assertNotNull(idC);
	}
	
	
}