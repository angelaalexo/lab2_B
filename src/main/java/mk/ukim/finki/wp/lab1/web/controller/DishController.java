package mk.ukim.finki.wp.lab1.web.controller;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;
import mk.ukim.finki.wp.lab1.service.ChefService;
import mk.ukim.finki.wp.lab1.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    // list dishes
    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Dish> dishes = this.dishService.listDishes();
        model.addAttribute("dishes", dishes);

        return "listDishes";
    }

    // shows empty form
    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        return "dish-form";
    }

    // shows dish form
    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);

        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }

        model.addAttribute("dish", dish);
        return "dish-form";
    }

    // method of saving new dish POST
    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {

        this.dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    // methot od edit dish POST
    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {

        this.dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        this.dishService.delete(id);
        return "redirect:/dishes";
    }

    // method that shows the page with selection of dishes
    @GetMapping("/select")
    public String selectDishPage(@RequestParam Long chefId, Model model) {

        Chef chef = chefService.findById(chefId);

        if (chef == null) {
            return "redirect:/listChefs?error=ChefNotFound";
        }

        model.addAttribute("dishes", dishService.listDishes());
        model.addAttribute("chefId", chefId);
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());

        return "dishesList";
    }

    // method that adds a dish to the chef
    @PostMapping("/select")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId) {

        Chef chef = chefService.addDishToChef(chefId, dishId);

        if (chef == null) {
            return "redirect:/dishes/select?chefId=" + chefId + "&error=DishOrChefNotFound";
        }

        return "redirect:/chefDetails?chefId=" + chef.getId();
    }
}