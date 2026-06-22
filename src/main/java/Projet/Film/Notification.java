package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Représente une notification envoyée à un utilisateur
 * (ex : nouvelle sortie, rappel d'abonnement, recommandation).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String message;
    private LocalDateTime dateEnvoi;

    @Builder.Default
    private boolean lue = false;

    public void marquerCommeLue() {
        this.lue = true;
    }
}
