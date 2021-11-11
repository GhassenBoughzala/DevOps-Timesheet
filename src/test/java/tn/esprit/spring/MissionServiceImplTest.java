package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.MissionServiceImpl;





@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionServiceImplTest {

	@Autowired 
	private MissionServiceImpl m;

	@Test
	public void testRetrieveAllMissions() {
		List<Mission> listMissions = m.retrieveAllMission(); 
		assertNotNull(listMissions);
	}
		
	@Test
	public void testAddMission() throws ParseException {
		Mission mi = new Mission("Project", "Project"); 
		Mission MissionAdded = m.addMission(mi); 
		Assert.assertEquals(mi.getName(), MissionAdded.getName());
	}
	
	@Test
	public void testEmpByMission() {
		List<Employe> listEmp = m.getAllEmployeByMission(21);
		assertNotNull(listEmp);
	}
	
	@Test
	public void testMissionByEmp() {
		List<Mission> listM = m.findAllMissionByEmployeJPQL(2);
		assertNotNull(listM);
	}

 


}