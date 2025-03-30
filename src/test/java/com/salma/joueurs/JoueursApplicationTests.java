package com.salma.joueurs;

import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.repos.JoueurRepository;
import com.salma.joueurs.services.JoueurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@SpringBootTest
class JoueursApplicationTests {
	@Autowired
	private JoueurRepository joueurRepository;
	@Autowired
	private JoueurService joueurService;

	@Test
	public void testCreateJoueur() {
		Joueur joueur = new Joueur("Leonel Messi",37.0,new Date());
		joueurRepository.save(joueur);
	}
	@Test
	public void testFindJoueur()
	{
		Joueur j = joueurRepository.findById(1L).get();
		System.out.println(j);
	}

	@Test
	public void testUpdateJoueur()
	{
		Joueur j = joueurRepository.findById(2L).get();
		j.setNomJoueur(("Cristiano Ronaldo"));
		j.setAge(40.0);
		joueurRepository.save(j);
	}

	@Test
	public void testDeleteJoueur()
	{
		joueurRepository.deleteById(1L);
	}

	@Test
	public void testListerTousJoueurs()
	{
		List<Joueur> joueurs = joueurRepository.findAll();
		for (Joueur j : joueurs)
		{
			System.out.println(j);
		}
	}
	@Test
	public void testFindByNomJoueurContains()
	{
		Page<Joueur> jou = joueurService.getAllJoueursParPage(0,2);
		System.out.println(jou.getSize());
		System.out.println(jou.getTotalElements());
		System.out.println(jou.getTotalPages());
		jou.getContent().forEach(j -> {System.out.println(j.toString());
		});
	}


}
