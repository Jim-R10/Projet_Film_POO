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
public class HistoriqueVisionnage {

    @Builder.Default
    private List<Visionnage> visionnages = new ArrayList<>();

    public void ajouterVisionnage(Visionnage visionnage) {
        visionnages.add(visionnage);
    }

    public List<Visionnage> getVisionnagesTermines() {
        return visionnages.stream()
                .filter(v -> v.getProgression() == 100)
                .toList();
    }

    public List<Visionnage> getVisionnagesEnCours() {
        return visionnages.stream()
                .filter(v -> v.getProgression() > 0 && v.getProgression() < 100)
                .toList();
    }
}