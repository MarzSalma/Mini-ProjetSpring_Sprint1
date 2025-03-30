package com.salma.joueurs.repos;

import com.salma.joueurs.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {
}
