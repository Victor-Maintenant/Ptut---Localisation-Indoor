package localisationIndoor.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import localisationIndoor.entity.Personne;
import org.springframework.data.jpa.repository.Query;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
     @Query(
            value = "SELECT * FROM PERSONNE WHERE age = :age",
            nativeQuery = true)
    public List<Personne> getPersonneByAge(int age);
    
    @Query(
            value = "SELECT * FROM PERSONNE WHERE nom = :nom",
            nativeQuery = true)
    public List<Personne> getPersonneByNom(String nom);
    
    @Query(
            value = "SELECT * FROM PERSONNE WHERE sexe = :sexe",
            nativeQuery = true)
    public List<Personne> getPersonneByGenre(String sexe);
    
    @Query(
            value = "SELECT * FROM PERSONNE join TYPE_PERSONNE "+
                    "on PERSONNE.type_id_type = TYPE_PERSONNE.id_Type "+
                    "WHERE libelle = :type",
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
}
