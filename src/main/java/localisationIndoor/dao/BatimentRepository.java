/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localisationIndoor.dao;

import localisationIndoor.entity.Batiment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Victor Maintenant
 */
public interface BatimentRepository extends JpaRepository<Batiment, Integer> {

    
}
