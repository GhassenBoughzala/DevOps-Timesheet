package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);

	@Test
	public void testRetrieveAllMissions() {
		List<Mission> listMissions = m.retrieveAllMission();
		l.info("*** ALL MISSIONS HERE ***" + listMissions);
		assertNotNull(listMissions);
	}
		
	@Test
	public void testAddMission() throws ParseException {
		Mission mi = new Mission("Project", "Project"); 
		Mission MissionAdded = m.addMission(mi); 
		l.info("*** ADD MISSION HERE ***" + MissionAdded);
		Assert.assertEquals(mi.getName(), MissionAdded.getName());
	}
	
	@Test
	public void testEmpByMission() {
		List<Employe> listEmp = m.getAllEmployeByMission(21);
		l.info("*** EMP BY MISSION ***" + listEmp);
		assertNotNull(listEmp);
	}
	
	@Test
	public void testMissionByEmp() {
		List<Mission> listM = m.findAllMissionByEmployeJPQL(2);
		l.info("*** MISSION BY EMP ***" + listM);
		assertNotNull(listM);
	}

 


}