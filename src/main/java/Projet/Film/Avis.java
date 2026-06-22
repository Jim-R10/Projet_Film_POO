package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    /**
     * Représente l'avis laissé par un profil sur un contenu :
     * une note, un commentaire et une réaction (LIKE, LOVE, DISLIKE).
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Avis {

        private Profil profil;
        private Contenu contenu;
        private String commentaire;
        private int note;
        private Reaction reaction;

        /**
         * Vérifie que la note est comprise entre 0 et 10.
         */
        public boolean noteEstValide() {
            return note >= 0 && note <= 10;
        }
    }

