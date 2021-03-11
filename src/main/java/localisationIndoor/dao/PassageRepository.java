package localisationIndoor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import localisationIndoor.entity.Passage;

public interface PassageRepository extends JpaRepository<Passage, Integer> {

}
