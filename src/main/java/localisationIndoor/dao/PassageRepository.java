package localisationIndoor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import localisationIndoor.entity.Passage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;

public interface PassageRepository extends JpaRepository<Passage, Integer> {
   
    @Query(value = "delete from PASAGGE",
           nativeQuery = true)
    public void supprmerLesPrésenceDeLaJournee();
}
