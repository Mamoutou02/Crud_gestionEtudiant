package com.example.gestion.Etudiant.Repository;

import com.example.gestion.Etudiant.Entite.Entite_Etudiant;
import com.example.gestion.Etudiant.Enumeration.NiveauEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Repository_Etudiant extends JpaRepository<Entite_Etudiant, Integer> {
    List<Entite_Etudiant> findByNomContainingIgnoreCase(String nom);
    List<Entite_Etudiant> findByNiveauEtude(NiveauEtude niveauEtude);
    Optional<Entite_Etudiant> findByMail(String mail);
}
