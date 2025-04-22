package com.salma.joueurs.entities;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJoueur;
    @NotNull
    @Size(min = 4, max = 50)
    private String nomJoueur;
    @Min(value = 16)
    @Max(value = 50)
    private Double age;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateNaissance;
    @ManyToOne
    private Equipe equipe;

    public Joueur() {
        super();
    }

    public Joueur(String nomJoueur, Double age, Date dateNaissance) {
        super();
        this.nomJoueur = nomJoueur;
        this.age = age;
        this.dateNaissance = dateNaissance;
    }

    public Long getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Long idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Joueur [idJoueur=" + idJoueur + ", nomJoueur=" + nomJoueur + ", age=" + age
                + ", dateNaissance=" + dateNaissance + "]";
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
