package com.salma.joueurs.repos;

import com.salma.joueurs.entities.Equipe;
import com.salma.joueurs.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "rest")
public interface JoueurRepository extends JpaRepository<Joueur, Long> {
    List<Joueur> findByNomJoueur(String nom);
    List<Joueur> findByNomJoueurContains(String nom);

    @Query("select j from Joueur j where j.nomJoueur = :nom and j.age >= :age")
    List<Joueur> findByNomAge(@Param("nom") String nom, @Param("age") Double age);

    @Query("select j from Joueur j where j.equipe = ?1")
    List<Joueur> findByEquipe(Equipe equipe);

    List<Joueur> findByEquipeIdEquipe(Long idEquipe);

    List<Joueur>findByOrderByNomJoueurAsc();

    @Query("select j from Joueur j order by j.nomJoueur ASC, j.age Desc")
    List<Joueur> trierJoueursNomsAge();

}
