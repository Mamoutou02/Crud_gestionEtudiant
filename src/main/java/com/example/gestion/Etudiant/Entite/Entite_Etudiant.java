package com.example.gestion.Etudiant.Entite;


import com.example.gestion.Etudiant.Enumeration.NiveauEtude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="Etudiant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entite_Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String mail;

    @Enumerated(EnumType.STRING)
    private NiveauEtude niveauEtude;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Entite_Inscription> inscriptions;
}