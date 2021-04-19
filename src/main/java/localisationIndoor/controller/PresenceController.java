package localisationIndoor.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import localisationIndoor.dao.BaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import localisationIndoor.dao.PassageRepository;
import localisationIndoor.dao.SalleRepository;
import localisationIndoor.dao.TelephoneRepository;
import localisationIndoor.entity.Balise;
import localisationIndoor.entity.Passage;
import localisationIndoor.entity.Salle;
import localisationIndoor.entity.Telephone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;

@Controller
@Slf4j
public class PresenceController {
	@Autowired
	private PassageRepository passageDAO;
        @Autowired
	private TelephoneRepository telDAO;
        @Autowired
	private BaliseRepository baliseDAO;
        @Autowired
	private SalleRepository salleDAO;
        
	
	/**
	 * Enregistre le passage d'un téléphone sur une balise
	 * ex: http://localhost:8080/presence?phone=1&balise=1
	 * @param phone le téléphone (transmettre la clé)
	 * @param beacon la balise (transmettre la clé)
	 * @return le nouveau passage en JSON (pour info)
	 */
        @GetMapping(path = "/presence")
	public String affichePhoneDetected (Model model) {
            model.addAttribute("passages", passageDAO.findAll());
            return "presence";
	}
	
        @Scheduled(cron ="01 * * * * *")
        public void ajouPhoneDetected(){
            Optional<Telephone> tel = telDAO.findById(1 + (int)(Math.random()*((telDAO.count() - 1) + 1)));
            Optional<Balise> balise = baliseDAO.findById(1 + (int)(Math.random()*((baliseDAO.count() - 1) + 1)));
            Passage nouveau = new Passage(tel.get(), balise.get());
            passageDAO.save(nouveau);
            nouveau.addPassageDansBalise();
        }
        
        @GetMapping(path = "/presence/nbPersonne")
	public HashMap<String, Integer> afficheNbPersonne(Model model) {
            HashMap<String,Integer> hm = new HashMap<>();
            List<Passage> passages = new LinkedList<>();
            passages = passageDAO.findAll();
            for(Passage p : passages){
                for(Salle s : salleDAO.findAll()){
                    int NbPer = p.getNbPersonneDansChaqueSalle(s.getId_Salle());
                    hm.put(s.getNum(), NbPer);
                }
            }
            return hm;
	}
}
