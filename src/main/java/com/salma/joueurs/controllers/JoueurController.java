package com.salma.joueurs.controllers;

import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.repos.JoueurRepository;
import com.salma.joueurs.services.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JoueurController {
    @Autowired
    JoueurRepository joueurRepository;
    @Autowired
    private JoueurService joueurService;

    @RequestMapping("/ListeJoueurs")
    public String ListeJoueurs(ModelMap modelMap,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "2") int size)
    {
        Page<Joueur> joueurs = joueurService.getAllJoueursParPage(page,size);
        modelMap.addAttribute("joueurs", joueurs);
        modelMap.addAttribute("page", new int[joueurs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ListeJoueurs";
    }
    @RequestMapping("/showJoueur")
    public String showCreate(){
        return "createJoueur";
    }
    @RequestMapping("/saveJoueur")
    public String saveJoueur(@ModelAttribute ("joueur")Joueur joueur, @RequestParam("date")String date, ModelMap modelMap)
            throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(date);
        joueur.setDateNaissance(dateNaissance);
        Joueur savedJoueur = joueurService.saveJoueur(joueur);
        String msg="joueur enregistré avec id" + savedJoueur.getIdJoueur();
        modelMap.addAttribute("msg", msg);
        return "createJoueur";
    }
    @RequestMapping("/supprimerJoueur")
    public String supprimerJoueur(@RequestParam("id") Long id, ModelMap modelMap,
                                  @RequestParam(name="page",defaultValue = "0") int page,
                                  @RequestParam(name="size",defaultValue = "2")int size
                                  ) {
        joueurService.deleteJoueurById(id);
        Page<Joueur> joueurs = joueurService.getAllJoueursParPage(page,size);
        modelMap.addAttribute("joueurs", joueurs);
        modelMap.addAttribute("page", new int[joueurs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeJoueurs";
    }

    @RequestMapping("/modifierJoueur")
    public String editerJoueur(@RequestParam("id") Long id, ModelMap modelMap) {
        Joueur j = joueurService.getJoueur(id);
        modelMap.addAttribute("joueur", j);
        return "editerJoueur";
    }

    @RequestMapping("/updateJoueur")
    public String updateJoueur(@ModelAttribute("joueur") Joueur joueur,
                               @RequestParam("dateDeNaissance") String dateDeNaissance,
                               ModelMap modelMap) throws ParseException {
        // Conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNaissance = dateformat.parse(String.valueOf(dateDeNaissance));
        joueur.setDateNaissance(dateNaissance);

        joueurService.updateJoueur(joueur);
        List<Joueur> joueurs = joueurService.getAllJoueurs();
        modelMap.addAttribute("joueurs", joueurs);
        return "listeJoueurs";
    }
}
