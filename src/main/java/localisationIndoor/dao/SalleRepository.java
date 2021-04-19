package localisationIndoor.dao;

import java.util.List;
import localisationIndoor.entity.Salle;
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

    @Query(
            value = "SELECT SALLE.num from SALLE "
            + "join BALISE on SALLE.id_Salle = BALISE.salle_id_salle "
            + "join PASSAGE on BALISE.id_Balise = PASSAGE.balise_id_balise "
            + "join TELEPHONE on PASSAGE.telephone_id_telephone = TELEPHONE.id_telephone "
            + "where TELEPHONE.personne_id_personne = :id "
            + "ORDER BY A desc LIMIT 1",
            nativeQuery = true)
    public String getNumSalleEnFonctionDePersonne(int id);
}
