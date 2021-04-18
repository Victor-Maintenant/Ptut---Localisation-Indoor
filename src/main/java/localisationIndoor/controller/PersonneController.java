package localisationIndoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import localisationIndoor.dao.PersonneRepository;
import localisationIndoor.dao.TypePersonneRepository;
import localisationIndoor.dao.VilleRepository;
import localisationIndoor.entity.Personne;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/personne")
public class PersonneController {
    
    @Autowired
    private PersonneRepository personneDAO;
    @Autowired
    private VilleRepository villeDAO;
    @Autowired
    private TypePersonneRepository typePDAO;
    
    /**
     * Affiche toutes les catégories dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher
     */
    @GetMapping(path = "show")
    public String afficheTousLesPersonne(Model model) {
        model.addAttribute("personnes", personneDAO.findAll());
        return "affichePersonnes";
    }
    
    /**
     * Montre le formulaire permettant d'ajouter une galerie
     *
     * @param Personne initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher 
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjoutPersonne(@ModelAttribute("personne") Personne personne, Model model) {
        model.addAttribute("villes", villeDAO.findAll());
        model.addAttribute("typeP", typePDAO.findAll());
        return "formulaireAjoutPersonne";
    }
    
    /**
     * Appelé par 'formulaireAjoutPersonne.html', méthode POST
     *
     * @param Personne Une parsonne initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des personnes
     */
    @PostMapping(path = "save")
    public String ajouteLaPersonnePuisMontreLaListe(Personne personne, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            personneDAO.save(personne);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "La personne '" + personne.getNom() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : La personne '" + personne.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'affichePersonne.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }
    
    /**
     * Appelé par le lien 'Supprimer' dans 'affichePersonne.html'
     *
     * @param Batiment à partir de l'id d'une salle transmise en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le personne dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des personnes
     */
    @GetMapping(path = "delete")
    public String supprimeUnePersonnePuisMontreLaListe(@RequestParam("id") Personne personne, RedirectAttributes redirectInfo) {
        String message = "Le batiment '" + personne.getNom() + "' a bien été supprimée";
        try {
            personneDAO.delete(personne); // Ici on peut avoir une erreur 
        } catch (DataIntegrityViolationException e) {
            message = "Erreur : Impossible de supprimer le tableau '" + personne.getNom() + "' !";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'affichePersonne.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
