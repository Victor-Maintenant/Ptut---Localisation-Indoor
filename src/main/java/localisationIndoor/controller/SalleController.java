
package localisationIndoor.controller;

import localisationIndoor.entity.Salle;
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
import localisationIndoor.dao.SalleRepository;

@Controller
@RequestMapping(path = "/salle")
public class SalleController {
    
    @Autowired
    private SalleRepository salleDAO;
    @Autowired
    private BatimentRepository batimentDAO;
    
    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheSalle.html')
     */
    @GetMapping(path = "show")
    public String afficheTousLesSalles(Model model) {
        model.addAttribute("salles", salleDAO.findAll());
        return "afficheSalles";
    }
    
    /**
     * Montre le formulaire permettant d'ajouter une galerie
     *
     * @param salle initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('formulaireAjoutSalle.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjoutSalle(@ModelAttribute("salle") Salle salle, Model model) {
        model.addAttribute("batiments", batimentDAO.findAll());
        return "formulaireAjoutSalle";
    }
    
    /**
     * Appelé par 'formulaireGalerie.html', méthode POST
     *
     * @param salle Une salle initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des salles
     */
    @PostMapping(path = "save")
    public String ajouteLaSallePuisMontreLaListe(Salle salle, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            salleDAO.save(salle);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "La salle '" + salle.getNum() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : La salle '" + salle.getNum() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheBalise.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }
    
    /**
     * Appelé par le lien 'Supprimer' dans 'afficheSalle.html'
     *
     * @param Salle à partir de l'id d'une salle transmise en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le tableau dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des salles
     */
    @GetMapping(path = "delete")
    public String supprimeUneSallePuisMontreLaListe(@RequestParam("id") Salle salle, RedirectAttributes redirectInfo) {
        String message = "La salle '" + salle.getNum() + "' a bien été supprimée";
        try {
            salleDAO.delete(salle); // Ici on peut avoir une erreur 
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer une salles qui a des balises
            message = "Erreur : Impossible de supprimer la salle '" + salle.getNum() + "' !";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheSalle.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
