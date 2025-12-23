package com.example.gestion.Etudiant.Repository;

import com.example.gestion.Etudiant.Entite.Entite_Inscription;
import com.example.gestion.Etudiant.Enumeration.AnneeAcademique;
import com.example.gestion.Etudiant.Enumeration.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Repository_Inscription extends JpaRepository<Entite_Inscription, Integer> {
    List<Entite_Inscription> findByEtudiantId(int etudiantId);
    List<Entite_Inscription> findByFiliereId(int filiereId);
    List<Entite_Inscription> findByPeriode(Periode periode);
    List<Entite_Inscription> findByAnneeAcademique(AnneeAcademique anneeAcademique);
    List<Entite_Inscription> findByFiliereIdAndPeriode(int filiereId, Periode periode);

    @Query("SELECT COUNT(i) FROM Entite_Inscription i WHERE i.filiere.id = :filiereId")
    int countByFiliereId(@Param("filiereId") int filiereId);

    @Query("SELECT COUNT(i) FROM Entite_Inscription i WHERE i.etudiant.id = :etudiantId")
    int countByEtudiantId(@Param("etudiantId") int etudiantId);

    boolean existsByEtudiantIdAndFiliereIdAndAnneeAcademiqueAndPeriode(
            int etudiantId, int filiereId, AnneeAcademique anneeAcademique, Periode periode);
}