package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une saison à l'intérieur d'une série.
 * Contient une liste d'épisodes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Saison {

    private int numero;

    @Builder.Default
    private List<Episode> episodes = new ArrayList<>();

    public void ajouterEpisode(Episode episode) {
        episodes.add(episode);
    }

    /**
     * Calcule la durée totale de la saison en additionnant
     * la durée de chaque épisode.
     */
    public int getDureeTotal() {
        return episodes.stream()
                .mapToInt(Episode::getDuree)
                .sum();
    }
}
