package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profil {

    private String nom;
    private boolean profilEnfant;

    @Builder.Default
    private List<Contenu> favoris = new ArrayList<>();

    @Builder.Default
    private List<Categorie> categoriesPreferees = new ArrayList<>();

    private HistoriqueVisionnage historique;

    public void ajouterFavori(Contenu contenu) {
        if (!favoris.contains(contenu)) {
            favoris.add(contenu);
        }
    }

    public void retirerFavori(Contenu contenu) {
        favoris.remove(contenu);
    }

    public void ajouterCategoriePreferee(Categorie categorie) {
        if (!categoriesPreferees.contains(categorie)) {
            categoriesPreferees.add(categorie);
        }
    }

    public boolean estFavori(Contenu contenu) {
        return favoris.contains(contenu);
    }
}