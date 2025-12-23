package com.example.gestion.Etudiant.Controller;

import com.example.gestion.Etudiant.Dto.Dto_Filiere;
import com.example.gestion.Etudiant.Service.Service_Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/filieres")
@CrossOrigin(origins = "*")
public class Controller_Filiere {

    @Autowired
    private Service_Filiere serviceFiliere;

    @PostMapping
    public ResponseEntity<Dto_Filiere> creerFiliere(@RequestBody Dto_Filiere dto) {
        try {
            Dto_Filiere created = serviceFiliere.creerFiliere(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dto_Filiere> modifierFiliere(@PathVariable int id, @RequestBody Dto_Filiere dto) {
        try {
            Dto_Filiere updated = serviceFiliere.modifierFiliere(id, dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerFiliere(@PathVariable int id) {
        try {
            serviceFiliere.supprimerFiliere(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dto_Filiere>> listerToutesFilieres() {
        try {
            List<Dto_Filiere> filieres = serviceFiliere.listerToutesFilieres();
            return new ResponseEntity<>(filieres, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dto_Filiere> obtenirFiliereParId(@PathVariable int id) {
        try {
            Dto_Filiere filiere = serviceFiliere.obtenirFiliereParId(id);
            return new ResponseEntity<>(filiere, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Dto_Filiere> obtenirFiliereParCode(@PathVariable String code) {
        try {
            Dto_Filiere filiere = serviceFiliere.obtenirFiliereParCode(code);
            return new ResponseEntity<>(filiere, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rechercher/nom")
    public ResponseEntity<List<Dto_Filiere>> rechercherFilieresParNom(@RequestParam String nom) {
        try {
            List<Dto_Filiere> filieres = serviceFiliere.rechercherFilieresParNom(nom);
            return new ResponseEntity<>(filieres, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
