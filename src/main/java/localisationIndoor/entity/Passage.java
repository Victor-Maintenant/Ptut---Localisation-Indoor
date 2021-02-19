package localisationIndoor.entity;
import java.sql.Date;
import javax.persistence.*;
import lombok.*;
/**
 *
 * @author Victor Maintenant
 */
@Entity @Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_Telephone", "id_Balise"})
})
public class Passage {
    @Id @ManyToOne
    private Telephone telephone;
    @Id @ManyToOne
    private Balise balise;
    @Column 
    private Date date_Passage;
}
