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
public class Ville {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Ville;
    @Column @NonNull
    private String nom;
    @Column @NonNull
    private String code_Postal;
    
    @OneToMany(mappedBy = "ville")
    private List<Personne> personnes;
    
    @OneToMany(mappedBy = "ville")
    private List<Batiment> batiments;
}
