package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import static org.assertj.core.api.Assertions.assertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeServiceImplTest {

	@Autowired
	IEmployeService employeS;

	 

	@Autowired
	EmployeRepository employeRepository;

	@Autowired
	IEntrepriseService entrepriseS;

	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);

	@Test
	public void testGetEmployePrenomById() {
		try {
			int idE = employeS
					.ajouterEmploye(new Employe("Mehdy", "benromdhane", "mehdy.benromdhane@spring.tn", true, Role.TECHNICIEN));
			String prenomEmp = employeS.getEmployePrenomById(idE);
			l.info("------> Prenom de lemploye est : " + prenomEmp);
			assertThat(prenomEmp).isEqualTo("benromdhane");
			employeS.deleteEmployeById(idE);
		} catch (Exception e) {
			l.error(String.format("Erreur dans Get EmployePrenom By Id : %s ", e));

		}

	}

	@Test
	public void testAjouterEmploye() {
		try {
			int id = employeS
					.ajouterEmploye(new Employe("Mehdy", "benromdhane", "mehdy.benromdhane@spring.tn", true, Role.INGENIEUR));

			assertThat(id).isPositive();
			l.info("Employe added successfully!");
			employeS.deleteEmployeById(id);

		} catch (Exception e) {
			l.error(String.format("Erreur dans Ajout d'Employe : %s ", e));
		}
	}

	@Test
	public void testMettreAjourEmailByEmployeId() {
		try {
			String email = "mehdy.benromdhane@spring.tn";
			int id = employeS
					.ajouterEmploye(new Employe("Mehdy", "benromdhane", "mehdy.benromdhane@gmail.com", true, Role.INGENIEUR));

			employeS.mettreAjourEmailByEmployeId(email, id);

			Employe e = employeS.getEmployerById(id);

			assertThat(e.getEmail()).isEqualTo(email);
			employeS.deleteEmployeById(id);
		} catch (Exception e) {
			l.error(String.format("Erreur dans Mettre A jour Email By Employe Id : %s ", e));
		}
	}
/*
	@Test
	public void affecterEmployeADepartement() {
		try {
			int idE = employeS
					.ajouterEmploye(new Employe("Mehdy", "benromdhane", "mehdy.benromdhane@gmail.com", true, Role.TECHNICIEN));
			int idD = entrepriseS.ajouterDepartement(new Departement("info"));

			employeS.affecterEmployeADepartement(idE, idD);
			l.info("--->Employe added successfully to Departement ");
			l.info("---->desaffectaion, de lemployer de departement");
			employeS.desaffecterEmployeDuDepartement(idE, idD);
			employeS.deleteEmployeById(idE);
			assertThat(idE).isNull();

			entrepriseS.deleteDepartementById(idD);
			assertThat(idD).isNull();


		} catch (Exception e) {
			l.error(String.format("Erreur dans l'affectaion : %s ", e));
		}

	}
*/
	@Test
	public void testDeleteEmployeById() {

		try {
			int id = employeS
					.ajouterEmploye(new Employe("Mehdy", "benromdhane", "mehdy.benromdhane@gmail.com", true, Role.INGENIEUR));

			employeS.deleteEmployeById(id);

			Employe e = employeS.getEmployerById(id);

			assertThat(e).isNull();
			l.info("--->Employe deleted successfully!");
		} catch (Exception e) {
			l.error(String.format("Erreur dans Delete Employe By Id : %s ", e));
		}
	}

}