package localisationIndoor.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Victor Maintenant
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Passage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NonNull
    private Telephone telephone;

    @ManyToOne
    @NonNull
    private Balise balise;

    private LocalDateTime A = LocalDateTime.now();

    public Passage(int id, Telephone t, Balise b, LocalDateTime time) {
        this.id = id;
        this.telephone = t;
        this.balise = b;
        this.A = time;
    }

    public LocalDateTime getA(){
        return this.A;
    }
    
    public void addPassageDansBalise(){
        this.balise.getPassages().add(this);
    }
}
