package localisationIndoor.entity;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;
import lombok.*;
/**
 *
 * @author Victor Maintenant
 */
@Entity 
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class Passage {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne
    @NonNull
    private Telephone telephone;

    @ManyToOne
    @NonNull
    private Balise balise;

    private LocalDate A = LocalDate.now();

}
