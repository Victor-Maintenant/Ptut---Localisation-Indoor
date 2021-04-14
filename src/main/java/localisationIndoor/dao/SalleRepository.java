package localisationIndoor.dao;

import java.util.List;
import localisationIndoor.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalleRepository extends JpaRepository<Salle, Integer> {
    @Query(
            value = "SELECT id_Salle FROM SALLE",
            nativeQuery = true)
    public List<Integer> getIdSalle(); 
    
    @Query(
            value = "SELECT max_Per FROM SALLE WHERE id_Salle = :id",
            nativeQuery = true)
    public int getNbMaxPersonneSalle(int id); 
}
