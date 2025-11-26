package mk.ukim.finki.wp.lab1.web.controller;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/listChefs")
    public String listChefsPage(@RequestParam(required = false) String error, Model model) {
        List<Chef> chefs = this.chefService.listChefs();
        model.addAttribute("chefs", chefs);

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "listChefs";
    }

    @GetMapping("/chefDetails")
    public String chefDetailsPage(@RequestParam Long chefId, Model model) {
        Chef chef = this.chefService.findById(chefId);

        if (chef == null) {
            return "redirect:/listChefs?error=ChefNotFound";
        }

        model.addAttribute("chef", chef);
        // Note: chefDetails.html must access dishes via ${chef.dishes}

        return "chefDetails";
    }
}