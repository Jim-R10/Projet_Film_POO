package Projet.Film;

/**
 * Vérifie qu'un utilisateur a le droit de regarder un contenu.
 * Appelé par LecteurService avant chaque démarrage de lecture.
 */
public class AbonnementVerificateur {

    /**
     * Retourne true si l'utilisateur a un abonnement actif valide.
     */
    public boolean aAcces(Utilisateur utilisateur) {
        if (utilisateur.getAbonnement() == null) {
            return false;
        }
        return utilisateur.getAbonnement().estValide();
    }

    /**
     * Retourne un message expliquant pourquoi l'accès est refusé.
     */
    public String messageRefus(Utilisateur utilisateur) {
        if (utilisateur.getAbonnement() == null) {
            return "Aucun abonnement trouvé. Veuillez souscrire un abonnement.";
        }
        if (!utilisateur.getAbonnement().estValide()) {
            return "Votre abonnement a expiré. Veuillez le renouveler.";
        }
        return "Accès autorisé.";
    }
}
