package servisesImplementation;

import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Service;

import Models.Etudiant;
import repository.EtudiantRepository;
import repositoryImplementation.RepositoryServise;
import servise.EtudiantServise;
@Service

public class EtudiantServisesImplementation implements EtudiantServise {
	 private final  EtudiantRepository etudiantRepository;
	 public EtudiantServisesImplementation() {
		    this.etudiantRepository = new RepositoryServise();
		}


	    public EtudiantServisesImplementation(EtudiantRepository etudiantRepository) {
	        this.etudiantRepository = etudiantRepository;
	    }

	    @Override
	    public List<Etudiant> GetAllEtudiant() {
	       return etudiantRepository.GetAllEtudiant();
	    }

	    @Override
	    public Etudiant getEudiantById(Long id) {
	        return etudiantRepository.getEudiantById(id);
	    }

	    @Override
	    public void DeleteEtudiant(Long id) {
	        etudiantRepository.DeleteEtudiant(id);
	    }

	    @Override
	    public void AddEtudiant(Etudiant etudiant) {
	     etudiantRepository.AddEtudiant(etudiant);
	    }

	    @Override
	    public Boolean modifEtudiant(Long id, Etudiant etudiant) {
	        return etudiantRepository.modifEtudiant(id, etudiant);
	    }


	}