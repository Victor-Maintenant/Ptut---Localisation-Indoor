package localisationIndoor.entity;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Victor Maintenant
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class Balise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Balise;
    @Column
    @NonNull
    private String num;

    @ManyToOne
    private Salle salle;

    @OneToMany(mappedBy = "balise")
    List<Passage> passages = new LinkedList<>();

    public Balise(int id, Salle s) {
        this.id_Balise = id;
        this.salle = s;
    }

    public int getNbPersonneDansChaqueSalle() {
        int nbPer = 0;
        Set<Integer> personneDejaVu = new HashSet<>();
        for (Passage passage : this.passages) {
            if (passage.getA().isBefore(LocalDateTime.now()) || passage.getA().equals(LocalDateTime.now())) {
                if ((passage.getA().isAfter(LocalDateTime.now().minusMinutes(5)))) {
                    if(!personneDejaVu.contains(passage.getTelephone().getId_Telephone())){
                        personneDejaVu.add(passage.getTelephone().getId_Telephone());
                        nbPer += 1;
                    }
                    
                }
            }
        }
        return nbPer;
    }
    
    public Salle getSalle(){
        return this.salle;
    }
}
