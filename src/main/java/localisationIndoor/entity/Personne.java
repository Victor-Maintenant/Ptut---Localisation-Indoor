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
    @Column(nullable = false) @NonNull
    private String nom;
    @Column(nullable = false) @NonNull
    private String prenom;
    @Column(nullable = true)
    private String sexe;
    @Column(nullable = true)
    private int age;
    @Column(nullable = true)
    private String adresse;
    
    @ManyToOne
    private Type_Personne type;
    
    @ManyToOne
    private Ville ville;
    
    @OneToOne
    private Telephone telephone;
}
