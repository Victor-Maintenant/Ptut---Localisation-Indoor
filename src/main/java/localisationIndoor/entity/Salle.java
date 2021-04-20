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
public class Salle {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_Salle;
    @Column @NonNull
    private String num;
    @Column 
    private int max_Per;
    
    @ManyToOne
    private Batiment batiment;
    
    @OneToMany(mappedBy = "salle")
    private List<Balise> balises;
    
    public Salle(int id){
        this.id_Salle = id;
    }

    public Integer getMaxPer() {
        return this.max_Per;
    }

    public String getNum() {
        return this.num;
    }
}
