package localisationIndoor.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import localisationIndoor.entity.Passage;
import org.springframework.data.jpa.repository.Query;

public interface PassageRepository extends JpaRepository<Passage, Integer> {
    
    @Query(
            value = "SELECT count(*) FROM BALISE "
            + "join PASSAGE on BALISE.id_balise = PASSAGE.balise_id_balise "
            + "WHERE BALISE.salle_id_salle = :id AND "
            + "A <= NOW() AND A > DATE_SUB(NOW(), INTERVAL 5 MINUTE)",
            nativeQuery = true)
    public int getNbPersonneDansChaqueSalle(int id);
    
}
