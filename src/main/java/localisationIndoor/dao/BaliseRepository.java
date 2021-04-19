package localisationIndoor.dao;

import localisationIndoor.entity.Balise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BaliseRepository extends JpaRepository<Balise, Integer> {

}
