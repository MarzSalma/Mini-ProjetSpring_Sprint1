package com.salma.joueurs.restcontrollers;

import com.salma.joueurs.dto.JoueurDTO;
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
    public List<JoueurDTO> getAllJoueursDto() {
        return joueurService.getAllJoueursDto();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JoueurDTO getJoueursDto(@PathVariable ("id") Long id) {
        return joueurService.getJoueurDto(id);
    }

    @RequestMapping( method = RequestMethod.POST)
    public JoueurDTO createJoueur (@RequestBody JoueurDTO joueur) {
        return joueurService.updateJoueur(joueur);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public JoueurDTO updateJoueur(@RequestBody JoueurDTO joueur) {
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