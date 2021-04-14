package localisationIndoor.entity;
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
    @Column 
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
