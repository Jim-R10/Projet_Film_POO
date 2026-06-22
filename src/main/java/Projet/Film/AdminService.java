package Projet.Film;

import java.util.Comparator;
import java.util.List;

/**
 * Service réservé aux ADMINISTRATEURS.
 * Permet de consulter les statistiques de la plateforme :
 * - films les plus populaires
 * - revenus des abonnements
 * - nombre d'abonnés actifs
 */
public class AdminService {

    private final CatalogueService catalogueService;
    private final List<Utilisateur> utilisateurs;

    public AdminService(CatalogueService catalogueService,
                        List<Utilisateur> utilisateurs) {
        this.catalogueService = catalogueService;
        this.utilisateurs = utilisateurs;
    }

    /**
     * Retourne les films les plus regardés, triés par nombre de vues.
     */
    public List<Film> getFilmsLePlusPopulaires(int limite) {
        return catalogueService.getTousLesFilms()
                .stream()
                .sorted(Comparator.comparingInt(Film::getNombreVues).reversed())
                .limit(limite)
                .toList();
    }

    /**
     * Retourne tous les abonnements actifs (non expirés).
     */
    public List<Abonnement> getAbonnementsActifs() {
        return utilisateurs.stream()
                .filter(u -> u.getAbonnement() != null)
                .map(Utilisateur::getAbonnement)
                .filter(Abonnement::estValide)
                .toList();
    }

    /**
     * Calcule le revenu mensuel total de tous les abonnements actifs.
     * C'est "l'argent qui rentre" mentionné dans le projet.
     */
    public double getRevenuMensuelTotal() {
        return getAbonnementsActifs()
                .stream()
                .mapToDouble(Abonnement::getMontantMensuel)
                .sum();
    }

    /**
     * Retourne le nombre de clients ayant un abonnement actif.
     */
    public long getNombreAbonnesActifs() {
        return utilisateurs.stream()
                .filter(Utilisateur::estClient)
                .filter(u -> u.getAbonnement() != null)
                .filter(u -> u.getAbonnement().estValide())
                .count();
    }

    /**
     * Vérifie qu'un utilisateur est bien admin avant d'exécuter une action.
     *
     * @throws SecurityException si l'utilisateur n'est pas admin
     */
    public void verifierAdmin(Utilisateur utilisateur) {
        if (!utilisateur.estAdmin()) {
            throw new SecurityException(
                    "Accès refusé : seuls les administrateurs peuvent voir ces données.");
        }
    }
}