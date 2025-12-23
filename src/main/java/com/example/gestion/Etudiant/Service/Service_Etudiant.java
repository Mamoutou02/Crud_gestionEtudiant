package com.example.gestion.Etudiant.Service;

import com.example.gestion.Etudiant.Dto.Dto_Etudiant;
import com.example.gestion.Etudiant.Entite.Entite_Etudiant;
import com.example.gestion.Etudiant.Enumeration.NiveauEtude;
import com.example.gestion.Etudiant.Repository.Repository_Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Service_Etudiant {

    @Autowired
    private Repository_Etudiant repositoryEtudiant;

    public Dto_Etudiant creerEtudiant(Dto_Etudiant dto) {
        Entite_Etudiant etudiant = new Entite_Etudiant();
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setMail(dto.getMail());
        etudiant.setNiveauEtude(dto.getNiveauEtude());

        Entite_Etudiant saved = repositoryEtudiant.save(etudiant);
        return convertirEnDto(saved);
    }

    public Dto_Etudiant modifierEtudiant(int id, Dto_Etudiant dto) {
        Entite_Etudiant etudiant = repositoryEtudiant.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setMail(dto.getMail());
        etudiant.setNiveauEtude(dto.getNiveauEtude());

        Entite_Etudiant updated = repositoryEtudiant.save(etudiant);
        return convertirEnDto(updated);
    }

    public void supprimerEtudiant(int id) {
        repositoryEtudiant.deleteById(id);
    }

    public List<Dto_Etudiant> listerTousEtudiants() {
        return repositoryEtudiant.findAll().stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public Dto_Etudiant obtenirEtudiantParId(int id) {
        Entite_Etudiant etudiant = repositoryEtudiant.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return convertirEnDto(etudiant);
    }

    public List<Dto_Etudiant> rechercherEtudiantsParNom(String nom) {
        return repositoryEtudiant.findByNomContainingIgnoreCase(nom).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public List<Dto_Etudiant> rechercherEtudiantsParNiveau(NiveauEtude niveau) {
        return repositoryEtudiant.findByNiveauEtude(niveau).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public List<Dto_Etudiant> rechercherEtudiantsParEmail(String email) {
        return repositoryEtudiant.findByMail(email)
                .stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    private Dto_Etudiant convertirEnDto(Entite_Etudiant etudiant) {
        return new Dto_Etudiant(
                etudiant.getId(),
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getMail(),
                etudiant.getNiveauEtude()
        );
    }
}
