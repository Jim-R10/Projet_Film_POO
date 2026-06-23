package Projet.Film;

import java.time.LocalDateTime;

/**
 * Service principal pour qu'un CLIENT regarde un film.
 *
 * Flux complet :
 * 1. verifier abonnement
 * 2. demarrer() → crée une SessionLecture
 * 3. mettreEnPause() / reprendre() selon l'action du client
 * 4. arreter() → sauvegarde la progression dans l'historique
 */
public class LecteurService {

    private final AbonnementVerificateur verificateur;

    public LecteurService(AbonnementVerificateur verificateur) {
        this.verificateur = verificateur;
    }

    /**
     * Démarre la lecture d'un contenu pour un profil donné.
     * Vérifie l'abonnement avant de créer la session.
     *
     * @throws IllegalStateException si l'abonnement est invalide
     */
    public SessionLecture demarrer(Utilisateur utilisateur, Profil profil, Contenu contenu) {
        // Étape 1 : vérifier l'abonnement
        if (!verificateur.aAcces(utilisateur)) {
            throw new IllegalStateException(verificateur.messageRefus(utilisateur));
        }

        // Étape 2 : incrémenter le compteur de vues du contenu
        contenu.incrementerVues();

        // Étape 3 : créer et retourner la session de lecture
        return SessionLecture.builder()
                .profil(profil)
                .contenu(contenu)
                .progressionSecondes(0)
                .etat(EtatLecture.EN_COURS)
                .dateDebut(LocalDateTime.now())
                .build();
    }

    /**
     * Met la lecture en pause.
     */
    public void mettreEnPause(SessionLecture session) {
        session.mettreEnPause();
    }

    /**
     * Reprend la lecture après une pause.
     */
    public void reprendre(SessionLecture session) {
        session.reprendre();
    }

    /**
     * Met à jour la progression en secondes (appelé régulièrement pendant la lecture).
     */
    public void mettreAJourProgression(SessionLecture session, int secondes) {
        session.setProgressionSecondes(secondes);

        // Si on est à 95% ou plus, on marque comme terminé
        if (session.getProgressionPourcentage() >= 95) {
            session.terminer();
            sauvegarderDansHistorique(session);
        }
    }

    /**
     * Arrête la lecture et sauvegarde la progression dans l'historique.
     */
    public void arreter(SessionLecture session) {
        session.arreter();
        sauvegarderDansHistorique(session);
    }

    /**
     * Sauvegarde la progression dans l'historique du profil.
     */
    private void sauvegarderDansHistorique(SessionLecture session) {
        Profil profil = session.getProfil();

        if (profil.getHistorique() == null) {
            profil.setHistorique(new HistoriqueVisionnage());
        }

        Visionnage visionnage = Visionnage.builder()
                .contenu(session.getContenu())
                .dateVisionnage(java.time.LocalDate.now())
                .progression(session.getProgressionPourcentage())
                .build();

        profil.getHistorique().ajouterVisionnage(visionnage);
    }
}
