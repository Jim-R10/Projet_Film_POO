package testProjetFilmPOO;

import Projet.Film.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    private AdminService adminService;
    private CatalogueService catalogueService;
    private List<Utilisateur> utilisateurs;
    private Utilisateur admin;
    private Utilisateur client;
    private Film filmPopulaire;
    private Film filmMoinsPopulaire;

    @BeforeEach
    void setUp() {
        catalogueService = new CatalogueService();
        utilisateurs = new ArrayList<>();

        admin = Utilisateur.builder()
                .nom("Admin")
                .email("admin@streaming.com")
                .role(Role.ADMIN)
                .build();

        Abonnement abonnementValide = Abonnement.builder()
                .type(TypeAbonnement.PREMIUM)
                .dateDebut(LocalDate.now().minusMonths(1))
                .dateExpiration(LocalDate.now().plusMonths(1))
                .montantMensuel(14.99)
                .build();

        client = Utilisateur.builder()
                .nom("Alice")
                .email("alice@mail.com")
                .role(Role.CLIENT)
                .abonnement(abonnementValide)
                .build();

        utilisateurs.add(admin);
        utilisateurs.add(client);

        filmPopulaire = Film.builder()
                .titre("Avengers")
                .annee(2019)
                .noteMoyenne(8.4)
                .duree(181)
                .categories(new ArrayList<>())
                .build();
        // Simuler 10 vues
        for (int i = 0; i < 10; i++) filmPopulaire.incrementerVues();

        filmMoinsPopulaire = Film.builder()
                .titre("Film Inconnu")
                .annee(2020)
                .noteMoyenne(5.0)
                .duree(90)
                .categories(new ArrayList<>())
                .build();
        // Simuler 2 vues
        filmMoinsPopulaire.incrementerVues();
        filmMoinsPopulaire.incrementerVues();

        catalogueService.ajouterContenu(filmPopulaire);
        catalogueService.ajouterContenu(filmMoinsPopulaire);

        adminService = new AdminService(catalogueService, utilisateurs);
    }

    @Test
    void getFilmsLePlusPopulaires_trieParNombreDeVues() {
        List<Film> populaires = adminService.getFilmsLePlusPopulaires(2);

        assertEquals("Avengers", populaires.get(0).getTitre());
        assertEquals("Film Inconnu", populaires.get(1).getTitre());
    }

    @Test
    void getAbonnementsActifs_retourneSeulementLesValides() {
        List<Abonnement> actifs = adminService.getAbonnementsActifs();

        assertEquals(1, actifs.size());
    }

    @Test
    void getRevenuMensuelTotal_calculeCorrectement() {
        double revenu = adminService.getRevenuMensuelTotal();

        assertEquals(14.99, revenu, 0.01);
    }

    @Test
    void getNombreAbonnesActifs_compteBien() {
        long nombre = adminService.getNombreAbonnesActifs();

        assertEquals(1, nombre);
    }

    @Test
    void verifierAdmin_avecAdmin_neLevePasException() {
        assertDoesNotThrow(() -> adminService.verifierAdmin(admin));
    }

    @Test
    void verifierAdmin_avecClient_leveSecurityException() {
        assertThrows(SecurityException.class,
                () -> adminService.verifierAdmin(client));
    }
}
