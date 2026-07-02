package Projet.Film;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/MiniNetflix";
        String utilisateur = "postgres";
        String motDePasse = "1017";

        try {
            Connection connexion = DriverManager.getConnection(
                    url,
                    utilisateur,
                    motDePasse);

            System.out.println("Connexion réussie !");

            connexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}