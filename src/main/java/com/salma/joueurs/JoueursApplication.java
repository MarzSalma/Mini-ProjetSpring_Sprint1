package com.salma.joueurs;

import com.salma.joueurs.entities.Joueur;
import com.salma.joueurs.entities.Role;
import com.salma.joueurs.entities.User;
import com.salma.joueurs.services.JoueurService;
import com.salma.joueurs.services.UserService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;

@SpringBootApplication
public class JoueursApplication implements CommandLineRunner {
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService; // <-- AJOUTER CETTE LIGNE

	public static void main(String[] args) {
		SpringApplication.run(JoueursApplication.class, args);
	}

/*	@PostConstruct
	void init_users() {
		// ajouter les rôles
		userService.addRole(new Role(null,"ADMIN"));
		userService.addRole(new Role(null,"AGENT"));
		userService.addRole(new Role(null,"USER"));

		// ajouter les users
		userService.saveUser(new User(null,"admin","123",true,null));
		userService.saveUser(new User(null,"salma","123",true,null));
		userService.saveUser(new User(null,"user1","123",true,null));

		// ajouter les rôles aux users
		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("salma", "USER");
		userService.addRoleToUser("salma", "AGENT");
		userService.addRoleToUser("user1", "USER");
	}*/

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Joueur.class);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}

