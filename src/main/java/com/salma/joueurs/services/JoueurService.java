package com.salma.joueurs.services;

import com.salma.joueurs.entities.Equipe;
import com.salma.joueurs.entities.Joueur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JoueurService {
    Joueur saveJoueur(Joueur j);
    Joueur updateJoueur(Joueur j);
    void deleteJoueur(Joueur j);
    void deleteJoueurById(Long id);
    Joueur getJoueur(Long id);
    List<Joueur> getAllJoueurs();
    Page<Joueur> getAllJoueursParPage(int page, int size);
    List<Equipe> getAllEquipes();
    List<Joueur> findByNomJoueur(String nom);
    List<Joueur> findByNomJoueurContains(String nom);
    List<Joueur> findByNomAge(String nom, Double age);
    List<Joueur> findByEquipe(Equipe equipe);
    List<Joueur> findByEquipeIdEquipe(Long idEquipe);
    List<Joueur> findByOrderByNomJoueurAsc();
    List<Joueur> trierJoueursNomsAge();
}
