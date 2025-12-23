package com.example.gestion.Etudiant.Entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Filiere")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entite_Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String code;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<Entite_Inscription> inscriptions;
}
