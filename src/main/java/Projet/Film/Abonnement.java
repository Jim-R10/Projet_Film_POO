package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {

    private TypeAbonnement type;
    private LocalDate dateDebut;
    private LocalDate dateExpiration;
    private double montantMensuel;

    /**
     * Vérifie si l'abonnement est encore valide aujourd'hui.
     * Utilisé par AbonnementVerificateur avant chaque lecture.
     */
    public boolean estValide() {
        return LocalDate.now().isBefore(dateExpiration)
                || LocalDate.now().isEqual(dateExpiration);
    }

    /**
     * Calcule le nombre de jours restants avant expiration.
     */
    public long joursRestants() {
        return java.time.temporal.ChronoUnit.DAYS.between(
                LocalDate.now(), dateExpiration);
    }
}