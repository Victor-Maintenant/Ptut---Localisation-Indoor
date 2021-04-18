package localisationIndoor.controller;

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
import localisationIndoor.dao.VilleRepository;
import localisationIndoor.dao.BatimentRepository;
import localisationIndoor.entity.Batiment;

@Controller
@RequestMapping(path = "/batiment")
public class BatimentController {
    
    @Autowired
    private BatimentRepository batimentDAO;
    @Autowired
    private VilleRepository villeDAO;
    
    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher
     */
    @GetMapping(path = "show")
    public String afficheTousLesBatiment(Model model) {
        model.addAttribute("batiments", batimentDAO.findAll());
        return "afficheBatiments";
    }
    
    /**
     * Montre le formulaire permettant d'ajouter une galerie
     *
     * @param Batiments initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher 
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjoutBatiment(@ModelAttribute("batiment") Batiment batiment, Model model) {
        model.addAttribute("villes", villeDAO.findAll());
        return "formulaireAjoutBatiment";
    }
    
    /**
     * Appelé par 'formulaireAjoutBatiment.html', méthode POST
     *
     * @param Batiment Une salle initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des batiments
     */
    @PostMapping(path = "save")
    public String ajouteLeBatimentPuisMontreLaListe(Batiment batiment, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            batimentDAO.save(batiment);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "Le  batiment '" + batiment.getNom() + "' a été correctement enregistré";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : Le batiment '" + batiment.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheBatiment.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }
    
    /**
     * Appelé par le lien 'Supprimer' dans 'afficheBatiment.html'
     *
     * @param Batiment à partir de l'id d'une salle transmise en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le tableau dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des batiments
     */
    @GetMapping(path = "delete")
    public String supprimeUneSallePuisMontreLaListe(@RequestParam("id") Batiment batiment, RedirectAttributes redirectInfo) {
        String message = "Le batiment '" + batiment.getNom() + "' a bien été supprimée";
        try {
            batimentDAO.delete(batiment); // Ici on peut avoir une erreur 
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Impossible de supprimer le tableau '" + batiment.getNom() + "' !";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheSalle.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
