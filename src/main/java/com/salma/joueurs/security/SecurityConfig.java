package com.salma.joueurs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/joueurs/ListeJoueurs/api").permitAll()
                        .requestMatchers("/showCreate", "/saveProduit").hasAnyAuthority("ADMIN", "AGENT")
                        .requestMatchers("/modifierJoueur", "/supprimerJoueur").hasAnyAuthority("ADMIN")
                        .requestMatchers("/ListeProduits").hasAnyAuthority("ADMIN", "AGENT", "USER")
                        .requestMatchers("/login", "/webjars/**").permitAll()
                        .requestMatchers("/ListeJoueurs/api/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/"))
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling((exception) -> exception.accessDeniedPage("/accessDenied"))
                .csrf(csrf -> csrf.disable());


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder ();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("123"))
                .authorities("ADMIN")
                .build();
        UserDetails userSalma = User
                .withUsername("salma")
                .password(passwordEncoder.encode("123"))
                .authorities("AGENT","USER")
                .build();
        UserDetails user1 = User
                .withUsername("user1")
                .password(passwordEncoder.encode("123"))
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, userSalma,user1);
    }*/

   /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager =new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select username , password , enabled from user where username =?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.username, r.role as authority   " +
                "FROM user_role ur, user u , role r " +
                "WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND u.username = ?");

        return jdbcUserDetailsManager;
    }*/

}
