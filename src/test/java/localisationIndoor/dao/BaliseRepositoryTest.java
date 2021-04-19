package localisationIndoor.dao;

import java.time.LocalDateTime;
import localisationIndoor.entity.Balise;
import localisationIndoor.entity.Passage;
import localisationIndoor.entity.Salle;
import localisationIndoor.entity.Telephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.jdbc.Sql;


@Log4j2
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaliseRepositoryTest {
    
    @Test
    public void recupererNbPersonneParSalle() {
        Telephone t1 = new Telephone(1,"0102030405");
        Telephone t2 = new Telephone(2,"0102030405");
        Salle s1 = new Salle(1);
        Salle s2 = new Salle(2);
        Salle s3 = new Salle(3);
        Balise b1 = new Balise(1,s1);
        Balise b2 = new Balise(2,s2);
        Balise b3 = new Balise(3,s3);
        Passage p1 = new Passage(1,t1,b1,LocalDateTime.now().minusMinutes(6));
        p1.addPassageDansBalise();
        Passage p2 = new Passage(2,t2,b2,LocalDateTime.now().minusMinutes(5));
        p2.addPassageDansBalise();
        Passage p3 = new Passage(3,t2,b3,LocalDateTime.now().minusMinutes(5));
        p3.addPassageDansBalise();
        Passage p4 = new Passage(4,t1,b3,LocalDateTime.now().minusMinutes(4));
        p4.addPassageDansBalise();
        Passage p5 = new Passage(5,t1,b3,LocalDateTime.now().minusMinutes(3));
        p5.addPassageDansBalise();
        Passage p6 = new Passage(6,t2,b2,LocalDateTime.now().minusMinutes(3));
        p6.addPassageDansBalise();
        Passage p7 = new Passage(7,t1,b2,LocalDateTime.now().minusMinutes(2));
        p7.addPassageDansBalise();
        Passage p8 = new Passage(8,t1,b1,LocalDateTime.now().minusMinutes(1));
        p8.addPassageDansBalise();
        Passage p9 = new Passage(9,t2,b1,LocalDateTime.now());
        p9.addPassageDansBalise();
        Passage p10 = new Passage(10,t2,b3,LocalDateTime.now());
        p10.addPassageDansBalise();
        
        log.info("On compte combien de personne se trouve dans une salle de t à t-5 minutes");
        assertEquals(2, b1.getNbPersonneDansChaqueSalle());
        assertEquals(2, b2.getNbPersonneDansChaqueSalle());
        assertEquals(3, b3.getNbPersonneDansChaqueSalle());
    }
}
