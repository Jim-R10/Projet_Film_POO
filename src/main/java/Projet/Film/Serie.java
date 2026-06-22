package Projet.Film;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Serie extends Contenu {

    @Builder.Default
    private List<Saison> saisons = new ArrayList<>();

    @Builder
    public Serie(String titre, String description, int annee,
                 double noteMoyenne, List<Categorie> categories, List<Saison> saisons) {
        super(titre, description, annee, noteMoyenne, 0,
                categories != null ? categories : new ArrayList<>());
        this.saisons = saisons != null ? saisons : new ArrayList<>();
    }

    public void ajouterSaison(Saison saison) {
        saisons.add(saison);
    }

    public int getNombreSaisons() {
        return saisons.size();
    }

    /**
     * Durée totale de la série, toutes saisons confondues.
     */
    @Override
    public int getDureeTotal() {
        return saisons.stream()
                .mapToInt(Saison::getDureeTotal)
                .sum();
    }
}
