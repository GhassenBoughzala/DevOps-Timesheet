package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;

import tn.esprit.spring.repository.DepartementRepository;

import tn.esprit.spring.services.IDepartementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {
	@Autowired
	IDepartementService es;
	@Autowired
	DepartementRepository er;


	@Test
	public void ajouterDepartementTest()  {
		Departement dep =new Departement("génie civile");
	int a=es.ajouterDepartement(dep);
	assertEquals(dep.getId(),a);
	
	}
	
	@Test
	public void getAllDepartementsNamesByEntrepriseTest1()  {
	List<String> names=es.getAllDepartementsNamesByEntreprise(1);
	assertNotNull(names);
	}
	
	
	
	@Test
	public void testRetrieveAlllistEntreprises() {
		List<Departement> listDepartement = es.getAllDepartements(); 
		// if there are 7 users in DB : 
		assertNotNull(listDepartement);
	}
	
	
}