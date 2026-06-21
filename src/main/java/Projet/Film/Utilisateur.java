package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    private String nom;
    private String email;
    private String motDePasse;

    // CLIENT ou ADMIN
    private Role role;

    @Builder.Default
    private List<Profil> profils = new ArrayList<>();

    private Abonnement abonnement;

    /**
     * Simule la connexion : vérifie email + mot de passe.
     */
    public boolean seConnecter(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }

    /**
     * Ajoute un profil à cet utilisateur.
     */
    public void ajouterProfil(Profil profil) {
        this.profils.add(profil);
    }

    /**
     * Recherche un profil par son nom.
     */
    public Profil trouverProfil(String nomProfil) {
        return profils.stream()
                .filter(p -> p.getNom().equalsIgnoreCase(nomProfil))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vérifie si cet utilisateur est un administrateur.
     */
    public boolean estAdmin() {
        return Role.ADMIN.equals(this.role);
    }

    /**
     * Vérifie si cet utilisateur est un client.
     */
    public boolean estClient() {
        return Role.CLIENT.equals(this.role);
    }
}