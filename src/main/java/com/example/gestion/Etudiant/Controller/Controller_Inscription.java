package com.example.gestion.Etudiant.Controller;

import com.example.gestion.Etudiant.Dto.Dto_Inscription;
import com.example.gestion.Etudiant.Enumeration.AnneeAcademique;
import com.example.gestion.Etudiant.Enumeration.Periode;
import com.example.gestion.Etudiant.Service.Service_Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
@CrossOrigin(origins = "*")
public class Controller_Inscription {

    @Autowired
    private Service_Inscription serviceInscription;

    @PostMapping
    public ResponseEntity<Dto_Inscription> creerInscription(@RequestBody Dto_Inscription dto) {
        try {
            Dto_Inscription created = serviceInscription.creerInscription(dto);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerInscription(@PathVariable int id) {
        try {
            serviceInscription.supprimerInscription(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dto_Inscription>> listerToutesInscriptions() {
        try {
            List<Dto_Inscription> inscriptions = serviceInscription.listerToutesInscriptions();
            return new ResponseEntity<>(inscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dto_Inscription> obtenirInscriptionParId(@PathVariable int id) {
        try {
            Dto_Inscription inscription = serviceInscription.obtenirInscriptionParId(id);
            return new ResponseEntity<>(inscription, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Dto_Inscription>> rechercherInscriptionsParEtudiant(@PathVariable int etudiantId) {
        try {
            List<Dto_Inscription> inscriptions = serviceInscription.rechercherInscriptionsParEtudiant(etudiantId);
            return new ResponseEntity<>(inscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<Dto_Inscription>> rechercherInscriptionsParFiliere(@PathVariable int filiereId) {
        try {
            List<Dto_Inscription> inscriptions = serviceInscription.rechercherInscriptionsParFiliere(filiereId);
            return new ResponseEntity<>(inscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/periode/{periode}")
    public ResponseEntity<List<Dto_Inscription>> rechercherInscriptionsParPeriode(@PathVariable Periode periode) {
        try {
            List<Dto_Inscription> inscriptions = serviceInscription.rechercherInscriptionsParPeriode(periode);
            return new ResponseEntity<>(inscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/annee/{annee}")
    public ResponseEntity<List<Dto_Inscription>> rechercherInscriptionsParAnneeAcademique(@PathVariable AnneeAcademique annee) {
        try {
            List<Dto_Inscription> inscriptions = serviceInscription.rechercherInscriptionsParAnneeAcademique(annee);
            return new ResponseEntity<>(inscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filiere/{filiereId}/periode/{periode}")
    public ResponseEntity<List<Dto_Inscription>> rechercherInscriptionsParFiliereEtPeriode(
            @PathVariable int filiereId, @PathVariable Periode periode) {
        try {
            List<Dto_Inscription> inscriptions = serviceInscription.rechercherInscriptionsParFiliereEtPeriode(filiereId, periode);
            return new ResponseEntity<>(inscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filiere/{filiereId}/count")
    public ResponseEntity<Integer> compterInscriptionsParFiliere(@PathVariable int filiereId) {
        try {
            int count = serviceInscription.compterInscriptionsParFiliere(filiereId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/etudiant/{etudiantId}/count")
    public ResponseEntity<Integer> compterInscriptionsParEtudiant(@PathVariable int etudiantId) {
        try {
            int count = serviceInscription.compterInscriptionsParEtudiant(etudiantId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
