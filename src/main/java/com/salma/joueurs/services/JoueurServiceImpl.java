package com.salma.joueurs.services;

import java.util.List;
import java.util.stream.Collectors;

import com.salma.joueurs.dto.JoueurDTO;
import com.salma.joueurs.entities.Equipe;
import com.salma.joueurs.repos.EquipeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
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
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    ModelMapper modelMapper;

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

    @Override
    public List<Joueur> findByNomJoueur(String nom) {
        return List.of();
    }

    @Override
    public List<Joueur> findByNomJoueurContains(String nom) {
        return List.of();
    }

    @Override
    public List<Joueur> findByNomAge(String nom, Double age) {
        return List.of();
    }

    @Override
    public List<Joueur> findByEquipe(Equipe equipe) {
        return List.of();
    }

    @Override
    public List<Joueur> findByEquipeIdEquipe(Long idEquipe) {
        return joueurRepository.findByEquipeIdEquipe(idEquipe);
    }

    @Override
    public List<Joueur> findByOrderByNomJoueurAsc() {
        return List.of();
    }

    @Override
    public List<Joueur> trierJoueursNomsAge() {
        return List.of();
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();    }

    /*@Override
    public JoueurDTO convertEntityToDTO(Joueur j) {
        JoueurDTO joueurDTO = new JoueurDTO();
        joueurDTO.setIdJoueur(j.getIdJoueur());
        joueurDTO.setNomJoueur(j.getNomJoueur());
        joueurDTO.setAge(j.getAge());
        joueurDTO.setDateDeNaissance(j.getDateNaissance());
        joueurDTO.setEquipe(j.getEquipe());
       // joueurDTO.setNomEquipe(j.getEquipe() != null ? j.getEquipe().getNomEquipe() : null);

        return joueurDTO;
    }*/
    @Override
    public JoueurDTO convertEntityToDTO(Joueur joueur) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        JoueurDTO dto = modelMapper.map(joueur, JoueurDTO.class);
        return dto;
    }

    @Override
    public JoueurDTO saveJoueurDto(Joueur j) {
        return convertEntityToDTO(joueurRepository.save(j));
    }

    @Override
    public JoueurDTO getJoueurDto(Long id) {
        return convertEntityToDTO(joueurRepository.findById(id).get());
    }

    @Override
    public List<JoueurDTO> getAllJoueursDto() {
        return joueurRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }


    /*@Override
    public Joueur convertDtoToEntity(JoueurDTO joueurDto) {
        Joueur joueur = new Joueur();
        joueur.setIdJoueur(joueurDto.getIdJoueur());
        joueur.setNomJoueur(joueurDto.getNomJoueur());
        joueur.setAge(joueurDto.getAge());
        joueur.setDateNaissance(joueurDto.getDateDeNaissance());
        joueur.setEquipe(joueurDto.getEquipe());
        return joueur;
    }*/
    @Override
    public Joueur convertDtoToEntity(JoueurDTO joueurDto){
        Joueur joueur = new Joueur();
        joueur = modelMapper.map(joueurDto, Joueur.class);
        return joueur;
    }

    @Override
    public JoueurDTO saveJoueur(JoueurDTO j) {
        return convertEntityToDTO(joueurRepository.save(convertDtoToEntity(j)));
    }
    @Override
    public  JoueurDTO updateJoueur(JoueurDTO j) {
        return convertEntityToDTO(joueurRepository.save(convertDtoToEntity(j)));
    }

}
