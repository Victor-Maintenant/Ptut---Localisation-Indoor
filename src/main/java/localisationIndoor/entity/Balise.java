package localisationIndoor.entity;
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
    
    @ManyToOne
    private Salle salle;
}
