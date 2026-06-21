package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Représente un paiement effectué par un utilisateur
 * pour régler son abonnement mensuel.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    private double montant;
    private LocalDate date;
    private ModePaiement modePaiement;

    /**
     * Vérifie si ce paiement a été effectué dans les 30 derniers jours.
     */
    public boolean estRecent() {
        return ChronoUnit.DAYS.between(date, LocalDate.now()) <= 30;
    }
}