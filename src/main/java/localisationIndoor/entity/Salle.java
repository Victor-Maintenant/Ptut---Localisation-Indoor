package localisationIndoor.entity;
import java.util.List;
import javax.persistence.*;
import lombok.*;
/**
 *
 * @author Victor Maintenant
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Salle {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Salle;
    @Column @NonNull
    private String num;
    @Column @ NonNull
    private int maxPer;
    
    @ManyToOne
    private Batiment batiment;
    
    @OneToMany(mappedBy = "salle")
    private List<Balise> balises;
}
