package Projet.Film;

import lombok.Builder;


import java.util.ArrayList;
import java.util.List;
public class Film extends Contenu {

    private final int duree;

    @Builder
    public Film(String titre, String description, int annee,
                double noteMoyenne, List<Categorie> categories, int duree) {
        super(titre, description, annee, noteMoyenne, 0,
                categories != null ? categories : new ArrayList<>());
        this.duree = duree;
    }

    @Override
    public int getDureeTotal() {
        return duree;
    }
}
