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
    private Integer id_Batiment;
    @Column @NonNull
    private String nom;
    @Column @NonNull
    private String adresse;
    
    @ManyToOne
    private Ville ville;
    
    @OneToMany(mappedBy = "batiment")
    private List<Salle> salles;
    
    @OneToMany(mappedBy = "batiment")
    private List<Plan> plans;
}
