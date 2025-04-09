package com.salma.joueurs;

import com.salma.joueurs.entities.Equipe;
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
		Joueur joueur = new Joueur("Robert Lewandowski",36.0,new Date());
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
		Joueur j = joueurRepository.findById(1L).get();
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
	public void testFindJoueurByNom()
	{
		List<Joueur> j = joueurRepository.findByNomJoueur("Lamine Yamal");
		for (Joueur j1 : j)
		System.out.println(j);
	}

	@Test
	public void testFindJoueurByNomContains ()
	{
		List<Joueur> jou=joueurRepository.findByNomJoueurContains("luka");
		for (Joueur j : jou)
		{
			System.out.println(j);
		} }

	@Test
	public void testfindByNomAge(){
		List<Joueur> jou = joueurRepository.findByNomAge("Lamine Yamal", 18.0);
		for (Joueur j : jou){
			System.out.println(j);
		}
		if (jou.isEmpty()) {
			System.out.println("Aucun joueur trouvé !");
		}

	}

	@Test
	public void testfindByEquipe()
	{
		Equipe Eq = new Equipe();
		Eq.setIdEquipe(1L);
		List<Joueur> jou = joueurRepository.findByEquipe(Eq);
		for (Joueur j : jou)
		{
			System.out.println(j);
		}
	}

	@Test
	public void findByEquipeIdEquipe(){
		List<Joueur> jou = joueurRepository.findByEquipeIdEquipe(2L);
		for (Joueur j : jou){
			System.out.println(j);
		}
	}

	@Test
	public void testfindByOrderByNomJoueurAsc(){
		List<Joueur> jou = joueurRepository.findByOrderByNomJoueurAsc();
		for (Joueur j : jou){
			System.out.println(j);
		}
	}

	@Test
	public void testTrierJoueursNomsAge(){
		List<Joueur> jou = joueurRepository.trierJoueursNomsAge();
		for (Joueur j : jou){
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
