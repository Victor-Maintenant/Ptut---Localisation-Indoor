package localisationIndoor.entity;
import javax.persistence.*;
import lombok.*;
/**
 *
 * @author Victor Maintenant
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Plan {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Plan;
    
    @NonNull
    private String plan;

    @NonNull
    @ManyToOne
    private Batiment batiment;
    
}
