package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Employe implements Serializable {
	
	private static final long serialVersionUID = -1396669830860400871L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String prenom;
	
	private String nom;
	
	//@Column(unique=true)
	//@Pattern(regex=".+\@.+\..+")
	private String email;

	private String password;
	
	private boolean actif;
	
	@Enumerated(EnumType.STRING)
	//@NotNull
	private Role role;
	
	//@JsonBackReference  
	@JsonIgnore
	@ManyToMany(mappedBy="employes",fetch=FetchType.EAGER )
	//@NotNull
	private List<Departement> departements;
	
	@JsonIgnore
	//@JsonBackReference
	@OneToOne(mappedBy="employe")
	private Contrat contrat;
	
	@JsonIgnore
	//@JsonBackReference
	@OneToMany(mappedBy="employe")
	private List<Timesheet> timesheets;
	
	
	public Employe() {
		super();
	}
	
		
	public Employe(int id, String prenom, String nom, String email, String password, boolean actif, Role role) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.actif = actif;
		this.role = role;
	}



	public Employe(String nom, String prenom, String email, String password, boolean actif, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.actif = actif;
		this.role = role;
	}
	
	public Employe(String nom, String prenom, String email, boolean actif, Role role) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.actif = actif;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}
	 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	public boolean isActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departement) {
		this.departements = departement;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public List<Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}


	@Override
	public String toString() {
		return "Employe [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", password="
				+ password + ", actif=" + actif + ", role=" + role + "]";
	}
	
	
	
}
