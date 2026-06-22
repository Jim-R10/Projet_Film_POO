package testProjetFilmPOO;

import Projet.Film.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LecteurServiceTest {

    private LecteurService lecteurService;
    private AbonnementVerificateur verificateur;
    private Utilisateur client;
    private Utilisateur clientSansAbonnement;
    private Profil profil;
    private Film film;

    @BeforeEach
    void setUp() {
        verificateur = new AbonnementVerificateur();
        lecteurService = new LecteurService(verificateur);

        // Abonnement valide
        Abonnement abonnementValide = Abonnement.builder()
                .type(TypeAbonnement.STANDARD)
                .dateDebut(LocalDate.now().minusMonths(1))
                .dateExpiration(LocalDate.now().plusMonths(1))
                .montantMensuel(9.99)
                .build();

        client = Utilisateur.builder()
                .nom("Alice")
                .email("alice@mail.com")
                .motDePasse("1234")
                .role(Role.CLIENT)
                .abonnement(abonnementValide)
                .build();

        clientSansAbonnement = Utilisateur.builder()
                .nom("Bob")
                .email("bob@mail.com")
                .motDePasse("5678")
                .role(Role.CLIENT)
                .build();

        profil = Profil.builder()
                .nom("Alice")
                .profilEnfant(false)
                .favoris(new ArrayList<>())
                .categoriesPreferees(new ArrayList<>())
                .historique(new HistoriqueVisionnage())
                .build();

        film = Film.builder()
                .titre("Inception")
                .annee(2010)
                .noteMoyenne(8.8)
                .duree(148)
                .categories(new ArrayList<>())
                .build();
    }

    @Test
    void demarrer_avecAbonnementValide_creeLaSession() {
        SessionLecture session = lecteurService.demarrer(client, profil, film);

        assertNotNull(session);
        assertEquals(EtatLecture.EN_COURS, session.getEtat());
        assertEquals(film, session.getContenu());
        assertEquals(profil, session.getProfil());
    }

    @Test
    void demarrer_sansAbonnement_leveUneException() {
        assertThrows(IllegalStateException.class, () ->
                lecteurService.demarrer(clientSansAbonnement, profil, film));
    }

    @Test
    void demarrer_incrementeLeNombreDeVues() {
        int vuesAvant = film.getNombreVues();
        lecteurService.demarrer(client, profil, film);

        assertEquals(vuesAvant + 1, film.getNombreVues());
    }

    @Test
    void mettreEnPause_changeEtatEnPause() {
        SessionLecture session = lecteurService.demarrer(client, profil, film);
        lecteurService.mettreEnPause(session);

        assertEquals(EtatLecture.EN_PAUSE, session.getEtat());
    }

    @Test
    void reprendre_apresUnePatuse_repasseEnCours() {
        SessionLecture session = lecteurService.demarrer(client, profil, film);
        lecteurService.mettreEnPause(session);
        lecteurService.reprendre(session);

        assertEquals(EtatLecture.EN_COURS, session.getEtat());
    }

    @Test
    void arreter_sauvegardeDansHistorique() {
        SessionLecture session = lecteurService.demarrer(client, profil, film);
        lecteurService.mettreAJourProgression(session, 4000);
        lecteurService.arreter(session);

        assertFalse(profil.getHistorique().getVisionnages().isEmpty());
    }

    @Test
    void mettreAJourProgression_a95Pourcent_marqueTermine() {
        SessionLecture session = lecteurService.demarrer(client, profil, film);
        // 148 min * 60s = 8880s → 95% = 8436s
        lecteurService.mettreAJourProgression(session, 8436);

        assertEquals(EtatLecture.TERMINE, session.getEtat());
    }

    @Test
    void getProgressionPourcentage_calculeCorrectement() {
        SessionLecture session = lecteurService.demarrer(client, profil, film);
        // 50% de 148 min = 74 min = 4440 secondes
        session.setProgressionSecondes(4440);

        assertEquals(50, session.getProgressionPourcentage());
    }
}
