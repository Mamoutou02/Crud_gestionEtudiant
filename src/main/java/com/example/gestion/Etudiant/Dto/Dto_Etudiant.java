package com.example.gestion.Etudiant.Dto;

import com.example.gestion.Etudiant.Enumeration.NiveauEtude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dto_Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private NiveauEtude niveauEtude;
}