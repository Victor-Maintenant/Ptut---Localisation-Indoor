package localisationIndoor.dao;

import java.util.List;
import localisationIndoor.entity.Balise;
import localisationIndoor.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BaliseRepository extends JpaRepository<Balise, Integer>{
   @Query(
            value = "SELECT coor_x FROM BALISE WHERE id_Balise = :id",
            nativeQuery = true)
    public double getCoorX(int id); 
    
    @Query(
            value = "SELECT coor_y FROM BALISE WHERE id_Balise = :id",
            nativeQuery = true)
    public double getCoorY(int id); 
}
