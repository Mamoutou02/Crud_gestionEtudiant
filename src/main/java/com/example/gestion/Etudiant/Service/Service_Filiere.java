package com.example.gestion.Etudiant.Service;

import com.example.gestion.Etudiant.Dto.Dto_Filiere;
import com.example.gestion.Etudiant.Entite.Entite_Filiere;
import com.example.gestion.Etudiant.Repository.Repository_Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Service_Filiere {

    @Autowired
    private Repository_Filiere repositoryFiliere;

    public Dto_Filiere creerFiliere(Dto_Filiere dto) {
        Entite_Filiere filiere = new Entite_Filiere();
        filiere.setNom(dto.getNom());
        filiere.setCode(dto.getCode());

        Entite_Filiere saved = repositoryFiliere.save(filiere);
        return convertirEnDto(saved);
    }

    public Dto_Filiere modifierFiliere(int id, Dto_Filiere dto) {
        Entite_Filiere filiere = repositoryFiliere.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));

        filiere.setNom(dto.getNom());
        filiere.setCode(dto.getCode());

        Entite_Filiere updated = repositoryFiliere.save(filiere);
        return convertirEnDto(updated);
    }

    public void supprimerFiliere(int id) {
        repositoryFiliere.deleteById(id);
    }

    public List<Dto_Filiere> listerToutesFilieres() {
        return repositoryFiliere.findAll().stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    public Dto_Filiere obtenirFiliereParId(int id) {
        Entite_Filiere filiere = repositoryFiliere.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
        return convertirEnDto(filiere);
    }

    public Dto_Filiere obtenirFiliereParCode(String code) {
        Entite_Filiere filiere = repositoryFiliere.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
        return convertirEnDto(filiere);
    }

    public List<Dto_Filiere> rechercherFilieresParNom(String nom) {
        return repositoryFiliere.findByNomContainingIgnoreCase(nom).stream()
                .map(this::convertirEnDto)
                .collect(Collectors.toList());
    }

    private Dto_Filiere convertirEnDto(Entite_Filiere filiere) {
        return new Dto_Filiere(
                filiere.getId(),
                filiere.getNom(),
                filiere.getCode()
        );
    }
}
