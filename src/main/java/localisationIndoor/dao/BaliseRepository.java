package localisationIndoor.dao;

import localisationIndoor.entity.Balise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BaliseRepository extends JpaRepository<Balise, Integer> {

    @Query(
            value = "SELECT coor_x FROM BALISE WHERE id_Balise = :id",
            nativeQuery = true)
    public double getCoorX(int id);

    @Query(
            value = "SELECT coor_y FROM BALISE WHERE id_Balise = :id",
            nativeQuery = true)
    public double getCoorY(int id);

    @Query(
            value = "SELECT * from BALISE "
            + "join PASSAGE on BALISE.id_Balise = PASSAGE.balise_id_balise "
            + "where PASSAGE.telephone_id_telephone = :id "
            + "ORDER BY A desc LIMIT 1",
            nativeQuery = true)
    public Balise getBaliseEnFonctionDePersonne(int id);

}
