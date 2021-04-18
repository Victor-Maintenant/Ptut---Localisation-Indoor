package localisationIndoor.dao;

import localisationIndoor.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Victor Maintenant
 */
public interface VilleRepository extends JpaRepository<Ville, Integer>{
    
}
