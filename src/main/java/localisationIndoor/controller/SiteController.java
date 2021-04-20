/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localisationIndoor.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import localisationIndoor.dao.BaliseRepository;
import localisationIndoor.dao.PassageRepository;
import localisationIndoor.dao.PersonneRepository;
import localisationIndoor.dao.SalleRepository;
import localisationIndoor.entity.Balise;
import localisationIndoor.entity.Passage;
import localisationIndoor.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiteController {
    @Autowired
    private SalleRepository salleDAO;
    @Autowired
    private PersonneRepository personneDAO;
    @Autowired
    private BaliseRepository baliseDAO;
    @Autowired
    private PassageRepository passageDAO;
    
    @GetMapping(path = "/configuration")
    public String afficheLesConfiguration() {
        return "configuration";
    }
    
    @GetMapping(path = "/accueil")
    public String afficheLAccueil() {
        return "accueil";
    }
    
    @GetMapping(path = "/plan")
    public String afficheLePlan(Model model) {
        HashMap<String,Integer> hmPPS = new HashMap<>();
        HashMap<String,Integer> hmMPS = new HashMap<>();
        List<Passage> passages = new LinkedList<>();
        passages = passageDAO.findAll();
        List<Balise> balises = new LinkedList<>();
        balises = baliseDAO.findAll();
        for(Passage p : passages){
            p.addPassageDansBalise();
        }
        for(Balise b : balises){
            int NbPer = b.getNbPersonneDansChaqueSalle();
            hmPPS.put(b.getSalle().getNum(), NbPer);  
            hmMPS.put(b.getSalle().getNum(), salleDAO.getNbMaxPersonneSalle(b.getSalle().getId_Salle())); 
        }
        model.addAttribute("nbPersonne", hmMPS);
        model.addAttribute("personnesParSalles", hmPPS);
        return "plan";
    }
    
    @GetMapping(path = "/itineraire")
    public String afficheLitinéraire() {
        return "itineraire";
    }
    
    @GetMapping(path = "/rechercherPersonne")
    public String afficheLaRechercheDUnePersonne(@ModelAttribute("personne") Personne personne, Model model) {
        model.addAttribute("personnes", personneDAO.findAll());
        return "rechercherPersonne";
    }
    
    
    @PostMapping(path = "affichage")
    public String AffichelaPersonneSurLaCarte(Personne personne) {
        return  "redirect:plan?salle="+salleDAO.getNumSalleEnFonctionDePersonne(personne.getId_Personne())+"";
    }
    
    
}
