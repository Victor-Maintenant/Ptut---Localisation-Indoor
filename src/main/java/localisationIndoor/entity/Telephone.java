package localisationIndoor.entity;
import javax.persistence.*;
import lombok.*;
/**
 *
 * @author Victor Maintenant
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Telephone {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Telephone;
    @Column @NonNull
    private String num;
    
    @OneToOne
    private Personne personne;

    public Telephone(int id, String num) {
        this.id_Telephone = id;
        this.num = num;
    }
}
