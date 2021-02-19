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
    
    @OneToMany(mappedBy = "salle")
    private List<Balise> balises;
}
