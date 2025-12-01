package mk.ukim.finki.wp.lab1.web.controller;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    // 1. READ: Приказ на сите готвачи
    @GetMapping
    public String listChefsPage(@RequestParam(required = false) String error, Model model) {
        List<Chef> chefs = this.chefService.listChefs();
        model.addAttribute("chefs", chefs);

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "listChefs";
    }

    // 2. CREATE (GET): Прикажување на празна форма
    @GetMapping("/chef-form")
    public String getAddChefPage(Model model) {
        model.addAttribute("chef", new Chef());
        return "chef-form";
    }

    // 3. UPDATE (GET): Прикажување на форма со податоци за уредување
    @GetMapping("/chef-form/{id}")
    public String getEditChefForm(@PathVariable Long id, Model model) {
        Chef chef = chefService.findById(id);

        if (chef == null) {
            return "redirect:/chefs?error=ChefNotFound";
        }

        model.addAttribute("chef", chef);
        return "chef-form";
    }

    // 4. CREATE (POST): Зачувување на нов готвач
    @PostMapping("/add")
    public String saveChef(@RequestParam Long id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio) {
        try {
            this.chefService.create(id, firstName, lastName, bio);
            return "redirect:/chefs";
        } catch (Exception e) {
            return "redirect:/chefs/chef-form?error=Грешка при креирање: " + e.getMessage();
        }
    }

    // 5. UPDATE (POST): Ажурирање на постоечки готвач
    // ID се зема од @RequestParam (од скриеното поле во формата)
    @PostMapping("/edit/{id}")
    public String editChef(@PathVariable Long id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio) {
        this.chefService.update(id, firstName, lastName, bio);
        return "redirect:/chefs";
    }


    // 6. DELETE (POST): Бришење на готвач
    @PostMapping("/delete/{id}")
    public String deleteChef(@PathVariable Long id) {
        this.chefService.delete(id);
        return "redirect:/chefs";
    }
}