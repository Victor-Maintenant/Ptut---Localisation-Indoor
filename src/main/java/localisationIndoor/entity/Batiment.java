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
public class Batiment {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Hopital;
    @Column @NonNull
    private String nom;
    @Column @NonNull
    private String adresse;
    
    
    @OneToMany(mappedBy = "batiment")
    private List<Plan> plans;
}
