package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository deptRepoistory;


	public List<Departement> getAllDepartements() {
		return (List<Departement>) deptRepoistory.findAll();
	}

	public Departement findById(int id) {
		Optional<Departement> departementOptinal = deptRepoistory.findById(id);
		if (departementOptinal.isPresent()) {
			return departementOptinal.get();
		}
		return new Departement();
	}

}
