package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Représente une entrée dans l'historique de visionnage d'un profil.
 * Créée par LecteurService quand une session de lecture est arrêtée
 * ou terminée, pour garder une trace de ce qui a été regardé.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visionnage {

    private Contenu contenu;
    private LocalDate dateVisionnage;

    // Pourcentage de progression au moment de la sauvegarde (0 à 100)
    private int progression;

    /**
     * Vérifie si ce visionnage a été regardé jusqu'au bout.
     */
    public boolean estTermine() {
        return this.progression >= 100;
    }
}