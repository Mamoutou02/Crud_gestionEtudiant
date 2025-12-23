package com.example.gestion.Etudiant.Dto;

import com.example.gestion.Etudiant.Enumeration.AnneeAcademique;
import com.example.gestion.Etudiant.Enumeration.Periode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dto_Inscription {
    private int id;
    private AnneeAcademique anneeAcademique;
    private Periode periode;
    private int etudiantId;
    private int filiereId;
    private String etudiantNom;
    private String etudiantPrenom;
    private String filiereNom;
    private String filiereCode;
}
