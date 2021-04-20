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
    public String afficheLAccueil() {
        return "plan";
    }
    
    @GetMapping(path = "/planDonnees")
    public String afficheLePlan(Model model) {
        HashMap<String,List<Integer>> hm = new HashMap<>();
        List<Integer> l = new LinkedList<>();
        List<Passage> passages = new LinkedList<>();
        passages = passageDAO.findAll();
        List<Balise> balises = new LinkedList<>();
        balises = baliseDAO.findAll();
        for(Passage p : passages){
            p.addPassageDansBalise();
        }
        for(Balise b : balises){
            int NbPer = b.getNbPersonneDansChaqueSalle();
            l.add(nbPersonne);
            l.add(b.getSalle().getMaxPer());
            hmPPS.put(b.getSalle().getNum(), l);  
        }
        model.addAttribute("personnesParSalles", hm);
        return "planDonnees";
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
        return  "redirect:planDonnees?salle="+salleDAO.getNumSalleEnFonctionDePersonne(personne.getId_Personne())+"";
    }
    
    
}
