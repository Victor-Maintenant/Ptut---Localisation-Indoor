package localisationIndoor.dao;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import localisationIndoor.entity.Personne;
import org.springframework.data.jpa.repository.Query;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

    @Query(
            value = "SELECT * FROM PERSONNE join TYPE_PERSONNE "
            + "on PERSONNE.type_id_type = TYPE_PERSONNE.id_Type "
            + "WHERE libelle = :type",
            nativeQuery = true)
    public List<Personne> getPersonneByType(String type);

    @Query(
            value = "SELECT id_Personne FROM PERSONNE WHERE nom = :nom and prenom = :prenom",
            nativeQuery = true)
    public int getPersonneByNomPrenom(String nom, String prenom);

    @Query(
            value = "SELECT nom FROM PERSONNE",
            nativeQuery = true)
    public List<String> getNoms();

    @Query(
            value = "Select * from PERSONNE p where not exists "
            + "(Select * from TELEPHONE t "
            + "where p.id_Personne = t.personne_id_personne)",
            nativeQuery = true)
    public List<Personne> getPersonneSansTel();
}
