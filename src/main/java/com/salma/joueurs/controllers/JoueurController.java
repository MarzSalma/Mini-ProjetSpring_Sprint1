package com.salma.joueurs.controllers;

import com.salma.joueurs.entities.Equipe;
import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.repos.JoueurRepository;
import com.salma.joueurs.services.JoueurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/accessDenied")
    public String error()
    {
        return "accessDenied";
    }

    @RequestMapping("/ListeJoueurs")
    public String ListeJoueurs(ModelMap modelMap,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "2") int size)
    {
        Page<Joueur> joueurs = joueurService.getAllJoueursParPage(page,size);
        modelMap.addAttribute("joueurs", joueurs);
        modelMap.addAttribute("page", new int[joueurs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "ListeJoueurs";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<Equipe> equipes = joueurService.getAllEquipes();
        modelMap.addAttribute("joueur", new Joueur());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("equipes", equipes);
        return "formJoueur";
    }


    @RequestMapping("/saveJoueur")
    public String saveJoueur(@Valid @ModelAttribute("joueur") Joueur joueur,
                             BindingResult bindingResult,
                             @RequestParam(name = "page",defaultValue = "0")int page,
                             @RequestParam (name = "size",defaultValue = "2")int size) {
        int currentPage;
        boolean isNew = false;
        if (bindingResult.hasErrors()) {
            return "formJoueur";
        }
        if (joueur.getIdJoueur() == null)
            isNew = true;
        joueurService.saveJoueur(joueur);
        if (isNew) {
            Page<Joueur> joueurs = joueurService.getAllJoueursParPage(page,size);
            currentPage = joueurs.getTotalPages()-1;
        }
        else {
            currentPage = page;
        }
        return ("redirect:/ListeJoueurs?page=" + currentPage+"&size=" + size);
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
    public String editerJoueur(@RequestParam("id") Long id, ModelMap modelMap,
                               @RequestParam(name="page",defaultValue = "0") int page,
                               @RequestParam(name="size",defaultValue = "2")int size) {
        Joueur j = joueurService.getJoueur(id);
        List<Equipe> equipes = joueurService.getAllEquipes();
        modelMap.addAttribute("joueur", j);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("equipes", equipes);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);
        return "formJoueur";
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
    @GetMapping(value = "/")
    public String welcome() {

        return "index";
    }

}
