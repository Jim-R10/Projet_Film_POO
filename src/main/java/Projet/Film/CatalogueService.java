package Projet.Film;

import com.streaming.enums.Categorie;
import com.streaming.model.Contenu;
import com.streaming.model.Film;
import com.streaming.model.Serie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CatalogueService {

    private final List<Contenu> catalogue = new ArrayList<>();

    public void ajouterContenu(Contenu contenu) {
        catalogue.add(contenu);
    }

    public List<Contenu> getTousLesContenus() {
        return new ArrayList<>(catalogue);
    }

    public List<Film> rechercherFilmParTitre(String titre) {
        return catalogue.stream()
                .filter(c -> c instanceof Film)
                .map(c -> (Film) c)
                .filter(f -> f.getTitre().toLowerCase()
                        .contains(titre.toLowerCase()))
                .toList();
    }

    public List<Film> rechercherFilmParAnnee(int annee) {
        return catalogue.stream()
                .filter(c -> c instanceof Film)
                .map(c -> (Film) c)
                .filter(f -> f.getAnnee() == annee)
                .toList();
    }

    public List<Serie> rechercherSerieParTitre(String titre) {
        return catalogue.stream()
                .filter(c -> c instanceof Serie)
                .map(c -> (Serie) c)
                .filter(s -> s.getTitre().toLowerCase()
                        .contains(titre.toLowerCase()))
                .toList();
    }

    public List<Contenu> rechercherParCategorie(Categorie categorie) {
        return catalogue.stream()
                .filter(c -> c.appartientACategorie(categorie))
                .toList();
    }

    public List<Film> getMeilleursFilms(int limite) {
        return catalogue.stream()
                .filter(c -> c instanceof Film)
                .map(c -> (Film) c)
                .sorted(Comparator.comparingDouble(Film::getNoteMoyenne).reversed())
                .limit(limite)
                .toList();
    }

    public List<Film> getTousLesFilms() {
        return catalogue.stream()
                .filter(c -> c instanceof Film)
                .map(c -> (Film) c)
                .toList();
    }

    public List<Serie> getToutesLesSeries() {
        return catalogue.stream()
                .filter(c -> c instanceof Serie)
                .map(c -> (Serie) c)
                .toList();
    }

    public List<Contenu> rechercherEntreAnnees(int anneeDebut, int anneeFin) {
        return catalogue.stream()
                .filter(c -> c.getAnnee() >= anneeDebut && c.getAnnee() <= anneeFin)
                .toList();
    }
}
