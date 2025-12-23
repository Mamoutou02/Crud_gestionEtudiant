package com.example.gestion.Etudiant.Repository;

import com.example.gestion.Etudiant.Entite.Entite_Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Repository_Filiere extends JpaRepository<Entite_Filiere, Integer> {
    Optional<Entite_Filiere> findByCode(String code);
    List<Entite_Filiere> findByNomContainingIgnoreCase(String nom);
    boolean existsByCode(String code);
}