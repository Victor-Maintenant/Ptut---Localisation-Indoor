package localisationIndoor.dao;

import java.util.List;
import localisationIndoor.entity.Type_Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypePersonneRepository extends JpaRepository<Type_Personne, Integer> {

    @Query(
            value = "SELECT libelle FROM TYPE_PERSONNE",
            nativeQuery = true)
    public List<String> getLibelle();
}
