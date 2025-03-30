package com.salma.joueurs.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.repos.JoueurRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class JoueurServiceImpl implements JoueurService {
    @Autowired
    JoueurRepository joueurRepository;

    @Override
    public Joueur saveJoueur(Joueur j) {
        return joueurRepository.save(j);
    }

    @Override
    public Joueur updateJoueur(Joueur j) {
        return joueurRepository.save(j);
    }

    @Override
    public void deleteJoueur(Joueur j) {
        joueurRepository.delete(j);
    }

    @Override
    public void deleteJoueurById(Long id) {
        joueurRepository.deleteById(id);
    }

    @Override
    public Joueur getJoueur(Long id) {
        return joueurRepository.findById(id).get();
    }

    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }
    @Override
    public Page<Joueur> getAllJoueursParPage(int page, int size) {
        return joueurRepository.findAll(PageRequest.of(page, size));
    }

}
