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
public class Type_Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Type;
    @Column @NonNull
    private String libelle;
    
    @OneToMany(mappedBy = "type")
    private List<Personne> personnes;
}
