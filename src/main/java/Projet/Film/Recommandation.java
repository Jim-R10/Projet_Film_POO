package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une liste de contenus recommandés à un profil donné.
 * Générée par SuggestionService.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommandation {

    private Profil profil;

    @Builder.Default
    private List<Contenu> contenus = new ArrayList<>();

    public void ajouterContenu(Contenu contenu) {
        if (!contenus.contains(contenu)) {
            contenus.add(contenu);
        }
    }

    public int getNombreReco() {
        return contenus.size();
    }
}