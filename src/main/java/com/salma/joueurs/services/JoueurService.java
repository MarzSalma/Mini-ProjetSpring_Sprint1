package com.salma.joueurs.services;

import com.salma.joueurs.dto.JoueurDTO;
import com.salma.joueurs.entities.Equipe;
import com.salma.joueurs.entities.Joueur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JoueurService {
    Joueur saveJoueur(Joueur j);
    Joueur updateJoueur(Joueur j);
    JoueurDTO saveJoueur(JoueurDTO joueur);
    JoueurDTO updateJoueur(JoueurDTO joueur);

    void deleteJoueur(Joueur j);
    void deleteJoueurById(Long id);
    Joueur getJoueur(Long id);
    List<Joueur> getAllJoueurs();
    Page<Joueur> getAllJoueursParPage(int page, int size);
    List<Equipe> getAllEquipes();

    // Méthodes spécifiques DTO
    JoueurDTO convertEntityToDTO(Joueur joueur);
    Joueur convertDtoToEntity(JoueurDTO jDTO);
    JoueurDTO saveJoueurDto(Joueur j);
    JoueurDTO getJoueurDto(Long id);
    List<JoueurDTO> getAllJoueursDto();

    // Recherche
    List<Joueur> findByNomJoueur(String nom);
    List<Joueur> findByNomJoueurContains(String nom);
    List<Joueur> findByNomAge(String nom, Double age);
    List<Joueur> findByEquipe(Equipe equipe);
    List<Joueur> findByEquipeIdEquipe(Long idEquipe);
    List<Joueur> findByOrderByNomJoueurAsc();
    List<Joueur> trierJoueursNomsAge();
}
