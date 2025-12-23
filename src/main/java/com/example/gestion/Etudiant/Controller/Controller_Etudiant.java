package com.example.gestion.Etudiant.Controller;

import com.example.gestion.Etudiant.Dto.Dto_Etudiant;
import com.example.gestion.Etudiant.Enumeration.NiveauEtude;
import com.example.gestion.Etudiant.Service.Service_Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
public class Controller_Etudiant {

    @Autowired
    private Service_Etudiant serviceEtudiant;

    @PostMapping
    public ResponseEntity<Dto_Etudiant> creerEtudiant(@RequestBody Dto_Etudiant dto) {
        try {
            Dto_Etudiant created = serviceEtudiant.creerEtudiant(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dto_Etudiant> modifierEtudiant(@PathVariable int id, @RequestBody Dto_Etudiant dto) {
        try {
            Dto_Etudiant updated = serviceEtudiant.modifierEtudiant(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtudiant(@PathVariable int id) {
        try {
            serviceEtudiant.supprimerEtudiant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dto_Etudiant>> listerTousEtudiants() {
        try {
            List<Dto_Etudiant> etudiants = serviceEtudiant.listerTousEtudiants();
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dto_Etudiant> obtenirEtudiantParId(@PathVariable int id) {
        try {
            Dto_Etudiant etudiant = serviceEtudiant.obtenirEtudiantParId(id);
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rechercher/nom")
    public ResponseEntity<List<Dto_Etudiant>> rechercherEtudiantsParNom(@RequestParam String nom) {
        try {
            List<Dto_Etudiant> etudiants = serviceEtudiant.rechercherEtudiantsParNom(nom);
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rechercher/niveau")
    public ResponseEntity<List<Dto_Etudiant>> rechercherEtudiantsParNiveau(@RequestParam NiveauEtude niveau) {
        try {
            List<Dto_Etudiant> etudiants = serviceEtudiant.rechercherEtudiantsParNiveau(niveau);
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rechercher/email")
    public ResponseEntity<List<Dto_Etudiant>> rechercherEtudiantsParEmail(@RequestParam String email) {
        try {
            List<Dto_Etudiant> etudiants = serviceEtudiant.rechercherEtudiantsParEmail(email);
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
