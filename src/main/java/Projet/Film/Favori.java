package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    /**
     * Représente l'ajout d'un contenu dans les favoris d'un profil.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Favori {

        private Profil profil;
        private Contenu contenu;
    }

