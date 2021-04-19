package localisationIndoor.controller;

import localisationIndoor.dao.BaliseRepository;
import localisationIndoor.entity.Balise;
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
import localisationIndoor.dao.SalleRepository;

@Controller
@RequestMapping(path = "/balise")
public class BaliseController {
    
    @Autowired
    private BaliseRepository baliseDAO;
    @Autowired
    private SalleRepository salleDAO;
    
    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheBalise.html')
     */
    @GetMapping(path = "show")
    public String afficheTousLesBalises(Model model) {
        model.addAttribute("balises", baliseDAO.findAll());
        return "afficheBalises";
    }
    
    /**
     * Montre le formulaire permettant d'ajouter une galerie
     *
     * @param salle initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('formulaireAjoutBalise.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjoutBalise(@ModelAttribute("balise") Balise balise, Model model) {
        model.addAttribute("salles", salleDAO.getSalleSansBalise());
        return "formulaireAjoutBalise";
    }
    
    /**
     * Appelé par 'formulaireAjoutBalise.html', méthode POST
     *
     * @param Balise Une balise initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des balises
     */
    @PostMapping(path = "save")
    public String ajouteLaBalisePuisMontreLaListe(Balise balise, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            baliseDAO.save(balise);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "La balise '" + balise.getNum() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : La balise '" + balise.getNum() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheBalise.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }
    
    /**
     * Appelé par le lien 'Supprimer' dans 'afficheGaleries.html'
     *
     * @param Balise à partir de l'id d'une balise transmise en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le tableau dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des balises
     */
    @GetMapping(path = "delete")
    public String supprimeUneBalisePuisMontreLaListe(@RequestParam("id") Balise balise, RedirectAttributes redirectInfo) {
        String message = "La balise '" + balise.getNum() + "' a bien été supprimée";
        try {
            baliseDAO.delete(balise); // Ici on peut avoir une erreur
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Impossible de supprimer la balise '" + balise.getNum() + "' !";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheBalise.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
