package localisationIndoor.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import localisationIndoor.entity.Passage;
import org.springframework.data.jpa.repository.Query;

public interface PassageRepository extends JpaRepository<Passage, Integer> {
   
    
}
