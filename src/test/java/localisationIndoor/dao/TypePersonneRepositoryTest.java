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
public class TypePersonneRepositoryTest {
    
    @Autowired
    private TypePersonneRepository tpDAO;
    
    @Test
    @Sql("test-SQL.sql")
    public void recupérerlesLibelles() {
        log.info("On compte combien de personne on récupère avec un age déterminé");
        assertEquals(3, (tpDAO.getLibelle()).size());
        assertEquals("patient", tpDAO.getLibelle().get(0));
    }
}
