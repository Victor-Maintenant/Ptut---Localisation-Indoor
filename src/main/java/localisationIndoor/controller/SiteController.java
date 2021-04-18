/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localisationIndoor.controller;

import localisationIndoor.dao.BaliseRepository;
import localisationIndoor.dao.PersonneRepository;
import localisationIndoor.dao.TypePersonneRepository;
import localisationIndoor.entity.Balise;
import localisationIndoor.entity.Personne;
import localisationIndoor.entity.Telephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SiteController {
    @Autowired
    private BaliseRepository baliseDAO;
    @Autowired
    private PersonneRepository personneDAO;
    
    @GetMapping(path = "/configuration")
    public String afficheLesConfiguration() {
        return "configuration";
    }
    
    @GetMapping(path = "/accueil")
    public String afficheLAccueil() {
        return "accueil";
    }
    
    @GetMapping(path = "/plan")
    public String afficheLePlan() {
        return "plan";
    }
    
    @GetMapping(path = "/itineraire")
    public String afficheLitinéraire() {
        return "itineraire";
    }
    
    @GetMapping(path = "/rechercherPersonne")
    public String afficheLaRechercheDUnePersonne() {
        return "rechercherPersonne";
    }
    
    
    @PostMapping(path = "affichage")
    public @ResponseBody Balise AffichelaPersonneSurLaCarte(Personne personne, Model model) {
        model.addAttribute("noms", personneDAO.getNoms());
        int id = personneDAO.getPersonneByNomPrenom(personne.getNom(), personne.getPrenom());
        return  baliseDAO.getBaliseEnFonctionDePersonne(id);
    }
    
    
}
