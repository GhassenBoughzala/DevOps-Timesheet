package tn.esprit.spring;


import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTests {
	
	private static final Logger logger = LogManager.getLogger(TimesheetTests.class);

	@Autowired
	private ITimesheetService timesheetService;	
	@Autowired 
	private MissionRepository missionRepository;
	@Autowired
	private TimesheetRepository timesheetRepository;
	@Autowired
	private EmployeRepository employeRepository;
	
	DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
	
	@Test
	public void affichage() throws ParseException{
		int i=0; 
		Long num=timesheetRepository.count();
		logger.info("there are "+num.intValue() +" timesheets in total");
	List<Timesheet> lt=timesheetService.getTimesheetsByMissionAndDate
			(employeRepository.findById(1).get(), missionRepository.findById(1).get(), format.parse("2020/00/00"), format.parse("2021/00/00"));
	assertNotNull(lt);
	for (Timesheet t:lt)
	{String valide="NOT VALID";
	i++;
	if (t.isValide())
		valide="VALID";
			logger.info("timesheet "+i +" Mission "+ missionRepository.findById(1).get().getName()+ " has employee  "+t.getEmploye().getNom()+" "+t.getEmploye().getPrenom()+ " working from " +t.getTimesheetPK().getDateDebut() + " to " +t.getTimesheetPK().getDateFin()+"======"+valide);}
		
	}
	

}
