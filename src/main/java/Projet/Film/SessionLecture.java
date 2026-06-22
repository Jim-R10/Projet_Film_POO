package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente une session de visionnage en cours.
 * Créée quand un client démarre un film ou un épisode.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionLecture {

    private Profil profil;
    private Contenu contenu;

    // Progression en secondes depuis le début
    private int progressionSecondes;

    @Builder.Default
    private EtatLecture etat = EtatLecture.EN_COURS;

    private LocalDateTime dateDebut;

    /**
     * Met la lecture en pause.
     */
    public void mettreEnPause() {
        if (this.etat == EtatLecture.EN_COURS) {
            this.etat = EtatLecture.EN_PAUSE;
        }
    }

    /**
     * Reprend la lecture après une pause.
     */
    public void reprendre() {
        if (this.etat == EtatLecture.EN_PAUSE) {
            this.etat = EtatLecture.EN_COURS;
        }
    }

    /**
     * Arrête définitivement la lecture.
     */
    public void arreter() {
        this.etat = EtatLecture.ARRETE;
    }

    /**
     * Marque la lecture comme terminée.
     */
    public void terminer() {
        this.etat = EtatLecture.TERMINE;
        this.progressionSecondes = contenu.getDureeTotal() * 60;
    }

    /**
     * Calcule la progression en pourcentage (0 à 100).
     */
    public int getProgressionPourcentage() {
        int dureeEnSecondes = contenu.getDureeTotal() * 60;
        if (dureeEnSecondes == 0) return 0;
        return Math.min(100, (progressionSecondes * 100) / dureeEnSecondes);
    }

    /**
     * Vérifie si la lecture est active (pas en pause, pas arrêtée).
     */
    public boolean estEnCours() {
        return this.etat == EtatLecture.EN_COURS;
    }
}
