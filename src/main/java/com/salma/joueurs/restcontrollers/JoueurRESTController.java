package com.salma.joueurs.restcontrollers;

import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.services.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ListeJoueurs/api")
@CrossOrigin
public class JoueurRESTController {
    @Autowired
    JoueurService joueurService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAllJoueurs();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Joueur getJoueursById(@PathVariable ("id") Long id) {
        return joueurService.getJoueur(id);
    }

    @RequestMapping( method = RequestMethod.POST)
    public Joueur createJoueur (@RequestBody Joueur joueur) {
        return joueurService.saveJoueur(joueur);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Joueur updateJoueur(@RequestBody Joueur joueur) {
        return joueurService.updateJoueur(joueur);
    }


    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void deleteJoueur(@PathVariable("id") Long id)
    {
        joueurService.deleteJoueurById(id);
    }


    @RequestMapping(value="/equipe/{idEquipe}",method = RequestMethod.GET)
    public List<Joueur> getJoueurByEquipeId(@PathVariable("idEquipe") Long idEquipe) {
        return joueurService.findByEquipeIdEquipe(idEquipe);
    }



}