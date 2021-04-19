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
public class SalleRepositoryTest {
    
    @Autowired
    private SalleRepository salleDAO;
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererIdDeTouteLesSalle() {
        log.info("On récupère tous les id des Salles");
        assertEquals(4, salleDAO.getIdSalle().size());
    }
    
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererLeMaxPersonnePourUneSalle() {
        log.info("On récupère le nombre max de personn dans une salle donné");
        assertEquals(20, salleDAO.getNbMaxPersonneSalle(1));
    }
    
    @Test
    @Sql("test-SQL.sql")
    public void recupererLaSallePourUnePersonneDonnee() {
        log.info("On récupère la salle de la présence de la personne");
        assertEquals("A001", salleDAO.getNumSalleEnFonctionDePersonne(1));
    }
}
