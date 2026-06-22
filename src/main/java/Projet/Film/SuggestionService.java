package Projet.Film;

import Projet.Film.Categorie;
import Projet.Film.Contenu;
import Projet.Film.Film;
import Projet.Film.Profil;
import Projet.Film.Recommandation;
import Projet.Film.CatalogueService;

import java.util.Comparator;
import java.util.List;

/**
 * Service de recommandation de contenus.
 * S'appuie sur le catalogue pour proposer des suggestions
 * adaptées aux préférences d'un profil.
 */
public class SuggestionService {

    private final CatalogueService catalogueService;

    public SuggestionService(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    /**
     * Construit une recommandation à partir des catégories
     * préférées du profil donné.
     */
    public Recommandation suggererPourProfil(Profil profil) {
        Recommandation recommandation = Recommandation.builder()
                .profil(profil)
                .build();

        for (Categorie categorie : profil.getCategoriesPreferees()) {
            catalogueService.rechercherParCategorie(categorie)
                    .forEach(recommandation::ajouterContenu);
        }

        return recommandation;
    }

    /**
     * Suggère les meilleurs films du catalogue, triés par note moyenne.
     */
    public List<Film> suggererMeilleursFilms(int limite) {
        return catalogueService.getMeilleursFilms(limite);
    }

    /**
     * Suggère des contenus similaires à celui donné,
     * c'est-à-dire partageant au moins une catégorie commune.
     */
    public List<Contenu> suggererSimilaires(Contenu contenu, int limite) {
        return catalogueService.getTousLesContenus()
                .stream()
                .filter(c -> !c.equals(contenu))
                .filter(c -> c.getCategories().stream()
                        .anyMatch(contenu.getCategories()::contains))
                .limit(limite)
                .toList();
    }

    /**
     * Retourne les contenus les plus populaires, triés par nombre de vues.
     */
    public List<Contenu> getContenusPopulaires(int limite) {
        return catalogueService.getTousLesContenus()
                .stream()
                .sorted(Comparator.comparingInt(Contenu::getNombreVues).reversed())
                .limit(limite)
                .toList();
    }
}