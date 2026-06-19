package Projet.Film;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
