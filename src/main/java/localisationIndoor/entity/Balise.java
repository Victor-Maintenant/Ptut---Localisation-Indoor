package localisationIndoor.entity;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.*;
/**
 *
 * @author Victor Maintenant
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Balise {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Balise;
    @Column @NonNull
    private String num;
    @Column 
    private double coor_x;
    @Column 
    private double coor_y;
    
    @ManyToOne
    private Salle salle;
    
    @OneToMany(mappedBy = "balise")
    List<Passage> passages = new LinkedList<>();

    public Balise(int id, Salle s) {
        this.id_Balise = id;
        this.salle = s;
    }
}
