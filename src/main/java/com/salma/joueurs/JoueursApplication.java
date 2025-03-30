package com.salma.joueurs;

import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.services.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JoueursApplication implements CommandLineRunner {
	@Autowired
	JoueurService joueurService;

	public static void main(String[] args)
	{
		SpringApplication.run(JoueursApplication.class, args);
	}

	 @Override
	public void run(String... args) throws Exception {
		joueurService.saveJoueur(new Joueur("Federico Valverde",26.0,new Date()));
		joueurService.saveJoueur(new Joueur("Sergio Ramos",36.0,new Date()));
		joueurService.saveJoueur(new Joueur("Lamine Yamal",18.0,new Date()));
	 }

}
