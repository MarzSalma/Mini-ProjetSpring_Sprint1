package com.salma.joueurs.dto;

import com.salma.joueurs.entities.Equipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoueurDTO {
    private Long idJoueur;
    private String nomJoueur;
    private Double age;
    @DateTimeFormat(pattern = "yyyy_MM_dd")
    private Date dateDeNaissance;
    private Equipe equipe;
    private String nomEquipe;
}
