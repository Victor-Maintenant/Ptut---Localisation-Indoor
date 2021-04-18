/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localisationIndoor.controller;

import localisationIndoor.dao.PersonneRepository;
import localisationIndoor.dao.TelephoneRepository;
import localisationIndoor.dao.VilleRepository;
import localisationIndoor.entity.Telephone;
import localisationIndoor.entity.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/ville")
public class VilleController {
    
    @Autowired
    private VilleRepository villeDAO;
    
    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher
     */
    @GetMapping(path = "show")
    public String afficheTousLesTelephone(Model model) {
        model.addAttribute("villes", villeDAO.findAll());
        return "afficheVilles";
    }
    
    /**
     * Montre le formulaire permettant d'ajouter un téléphone
     *
     * @param Ville initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher 
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjoutVille(@ModelAttribute("ville") Ville ville, Model model) {
        return "formulaireAjoutVille";
    }
    
    /**
     * Appelé par 'formulaireAjoutVille.html', méthode POST
     *
     * @param Ville Une parsonne initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des personnes
     */
    @PostMapping(path = "save")
    public String ajouteLaVillePuisMontreLaListe(Ville ville, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            villeDAO.save(ville);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "La ville '" + ville.getNom() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : La ville '" + ville.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheTelephone.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }
    
    /**
     * Appelé par le lien 'Supprimer' dans 'afficheTelephone.html'
     *
     * @param Ville à partir de l'id d'une salle transmise en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le telephone dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des telephones
     */
    @GetMapping(path = "delete")
    public String supprimeUneVillePuisMontreLaListe(@RequestParam("id") Ville ville, RedirectAttributes redirectInfo) {
        String message = "La ville '" + ville.getNom() + "' a bien été supprimée";
        try {
            villeDAO.delete(ville); // Ici on peut avoir une erreur 
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Impossible de supprimer la ville '" + ville.getNom() + "' !";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheTelephone.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
