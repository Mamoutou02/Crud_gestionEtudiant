package com.example.gestion.Etudiant.Service;

import com.example.gestion.Etudiant.Dto.Dto_Inscription;
import com.example.gestion.Etudiant.Entite.Entite_Etudiant;
import com.example.gestion.Etudiant.Entite.Entite_Filiere;
import com.example.gestion.Etudiant.Entite.Entite_Inscription;
import com.example.gestion.Etudiant.Enumeration.AnneeAcademique;
import com.example.gestion.Etudiant.Enumeration.Periode;
import com.example.gestion.Etudiant.Repository.Repository_Etudiant;
import com.example.gestion.Etudiant.Repository.Repository_Filiere;
import com.example.gestion.Etudiant.Repository.Repository_Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Service_Inscription {

    @Autowired
    private Repository_Inscription repositoryInscription;

    @Autowired
    private Repository_Etudiant repositoryEtudiant;

    @Autowired
    private Repository_Filiere repositoryFiliere;

    @Transactional
    public Dto_Inscription creerInscription(Dto_Inscription dto) {
        // Vérifier si l'inscription existe déjà (règle de gestion)
        boolean existeDeja = repositoryInscription.existsByEtudiantIdAndFiliereIdAndAnneeAcademiqueAndPeriode(
                dto.getEtudiantId(), dto.getFiliereId(), dto.getAnneeAcademique(), dto.getPeriode());

        if (existeDeja) {
            throw new RuntimeException("L'étudiant est déjà inscrit dans cette filière pour cette année académique et période");
        }

        Entite_Etudiant etudiant = repositoryEtudiant.findById(dto.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        Entite_Filiere filiere = repositoryFiliere.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));

        Entite_Inscription inscription = new Entite_Inscription();
        inscription.setAnneeAcademique(dto.getAnneeAcademique());
        inscription.setPeriode(dto.getPeriode());
        inscription.setEtudiant(etudiant);
        inscription.setFiliere(filiere);

        Entite_Inscription saved = repositoryInscription.save(inscription);
        return convertirEnDto(saved);
    }

    public void supprimerInscription(int id) {
        repositoryInscription.deleteById(id);
    }

    public List<Dto_Inscription> listerToutesInscriptions() {
        return repositoryInscription.findAll().stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public Dto_Inscription obtenirInscriptionParId(int id) {
        Entite_Inscription inscription = repositoryInscription.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée"));
        return convertirEnDto(inscription);
    }

    public List<Dto_Inscription> rechercherInscriptionsParEtudiant(int etudiantId) {
        return repositoryInscription.findByEtudiantId(etudiantId).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public List<Dto_Inscription> rechercherInscriptionsParFiliere(int filiereId) {
        return repositoryInscription.findByFiliereId(filiereId).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public List<Dto_Inscription> rechercherInscriptionsParPeriode(Periode periode) {
        return repositoryInscription.findByPeriode(periode).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public List<Dto_Inscription> rechercherInscriptionsParAnneeAcademique(AnneeAcademique annee) {
        return repositoryInscription.findByAnneeAcademique(annee).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public List<Dto_Inscription> rechercherInscriptionsParFiliereEtPeriode(int filiereId, Periode periode) {
        return repositoryInscription.findByFiliereIdAndPeriode(filiereId, periode).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public int compterInscriptionsParFiliere(int filiereId) {
        return repositoryInscription.countByFiliereId(filiereId);
    }

    public int compterInscriptionsParEtudiant(int etudiantId) {
        return repositoryInscription.countByEtudiantId(etudiantId);
    }

    private Dto_Inscription convertirEnDto(Entite_Inscription inscription) {
        Dto_Inscription dto = new Dto_Inscription();
        dto.setId(inscription.getId());
        dto.setAnneeAcademique(inscription.getAnneeAcademique());
        dto.setPeriode(inscription.getPeriode());
        dto.setEtudiantId(inscription.getEtudiant().getId());
        dto.setFiliereId(inscription.getFiliere().getId());
        dto.setEtudiantNom(inscription.getEtudiant().getNom());
        dto.setEtudiantPrenom(inscription.getEtudiant().getPrenom());
        dto.setFiliereNom(inscription.getFiliere().getNom());
        dto.setFiliereCode(inscription.getFiliere().getCode());
        return dto;
    }
}
