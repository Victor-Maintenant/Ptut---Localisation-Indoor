package localisationIndoor.dao;

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
public class PersonneRepositoryTest {
    
    @Autowired
    private PersonneRepository persoDAO;
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererDesPersonnesEnFonctionDeLAge() {
        log.info("On compte combien de personne on récupère avec un age déterminé");
        assertEquals(1, (persoDAO.getPersonneByAge(22)).size());
    }
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererDesPersonnesEnFonctionDuGenre() {
        log.info("On compte combien de personne on récupère avec un genre déterminé");
        assertEquals(1, (persoDAO.getPersonneByGenre("Homme")).size());
    }
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererDesPersonnesEnFonctionDuNom() {
        log.info("On compte combien de personne on récupère avec un nom déterminé");
        assertEquals(1, (persoDAO.getPersonneByNom("Gauthier")).size());
    }
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererDesPersonnesEnFonctionDuType() {
        log.info("On compte combien de personne on récupère avec un type déterminé");
        assertEquals(1, (persoDAO.getPersonneByType("radiologue")).size());
    }
    
}
