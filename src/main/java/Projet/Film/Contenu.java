package Projet.Film;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public abstract class Contenu {

    private String titre;
    private String description;
    private int annee;
    private double noteMoyenne;

    // Nombre de fois que ce contenu a été regardé (pour les stats admin)
    private int nombreVues;

    private List<Categorie> categories = new ArrayList<>();

    public void ajouterCategorie(Categorie categorie) {
        if (!categories.contains(categorie)) {
            categories.add(categorie);
        }
    }

    public boolean appartientACategorie(Categorie categorie) {
        return categories.contains(categorie);
    }

    /**
     * Incrémente le compteur de vues quand quelqu'un regarde ce contenu.
     */
    public void incrementerVues() {
        this.nombreVues++;
    }

    public abstract int getDureeTotal();
}
