package servise;

import java.util.List;
import java.util.Optional;

import Models.Etudiant;
public interface EtudiantServise {
	 
	

	   List<Etudiant> GetAllEtudiant();

	  Etudiant getEudiantById(Long var1);

	   void DeleteEtudiant(Long var1);

	   void AddEtudiant(Etudiant var1);

	   Boolean modifEtudiant(Long var1, Etudiant var2);
	}