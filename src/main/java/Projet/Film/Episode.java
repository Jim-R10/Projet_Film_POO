package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un épisode à l'intérieur d'une saison d'une série.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Episode {

    private String titre;
    private int numero;

    // Durée en minutes
    private int duree;
}
