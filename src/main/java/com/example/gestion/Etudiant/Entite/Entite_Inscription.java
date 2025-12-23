package com.example.gestion.Etudiant.Entite;


import com.example.gestion.Etudiant.Enumeration.AnneeAcademique;
import com.example.gestion.Etudiant.Enumeration.Periode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Inscription")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entite_Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private AnneeAcademique anneeAcademique;

    @Enumerated(EnumType.STRING)
    private Periode periode;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Entite_Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Entite_Filiere filiere;
}