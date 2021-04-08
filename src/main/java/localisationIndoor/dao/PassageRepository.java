package localisationIndoor.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import localisationIndoor.entity.Passage;
import org.springframework.data.jpa.repository.Query;

public interface PassageRepository extends JpaRepository<Passage, Integer> {

    @Query(
            value = "SELECT count(DISNTINCT PASSAGE.balise_id_balise) FROM BALISE "
            + "join PASSAGE  on BALISE.id_balise = PASSAGE.balise_id_balise "
            + "WHERE BALISE.salle_id_salle = :id AND "
            + "PASSAGE.a between NOW() AND DATEDD(minute, 5, NOW())"
            + "Group by id_Salle",
            nativeQuery = true)
    public int getNbPersonneDansChaqueSalle(int id);

}
