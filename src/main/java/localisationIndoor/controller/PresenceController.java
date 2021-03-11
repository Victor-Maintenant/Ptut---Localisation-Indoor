package localisationIndoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import localisationIndoor.dao.PassageRepository;
import localisationIndoor.entity.Balise;
import localisationIndoor.entity.Passage;
import localisationIndoor.entity.Telephone;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PresenceController {
	@Autowired
	private PassageRepository dao;
	@GetMapping(path = "/presence")
	/**
	 * Enregistre le passage d'un téléphone sur une balise
	 * ex: http://localhost:8080/presence?phone=1&balise=1
	 * @param phone le téléphone (transmettre la clé)
	 * @param beacon la balise (transmettre la clé)
	 * @return le nouveau passage en JSON (pour info)
	 */
	public @ResponseBody Passage phoneDetected (
		@RequestParam("phone") Telephone phone,
		@RequestParam("beacon") Balise beacon
	) {
		Passage nouveau = new Passage(phone, beacon);
		dao.save(nouveau);
		return nouveau;
	}
	
}
