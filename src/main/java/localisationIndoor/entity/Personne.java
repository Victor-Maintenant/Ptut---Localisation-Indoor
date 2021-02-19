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
public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Personne;
    @Column @NonNull
    private String nom;
    @Column @NonNull
    private String prenom;
    @Column
    private String sexe;
    @Column 
    private int age;
    @Column @NonNull
    private String adresse;
    
    @ManyToOne
    private Type_Personne type;
    
    @ManyToOne
    private Ville ville;
    
    @OneToOne
    private Telephone telephone;
}
