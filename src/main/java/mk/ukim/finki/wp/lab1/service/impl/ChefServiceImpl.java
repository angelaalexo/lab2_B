package mk.ukim.finki.wp.lab1.service.impl;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;
import mk.ukim.finki.wp.lab1.repository.ChefRepository;
import mk.ukim.finki.wp.lab1.service.ChefService;
import mk.ukim.finki.wp.lab1.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishService dishService; // Зависност од DishService

    // Constructor Injection
    public ChefServiceImpl(ChefRepository chefRepository, DishService dishService) {
        this.chefRepository = chefRepository;
        this.dishService = dishService;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.listChefs();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = chefRepository.findById(chefId).orElse(null);
        Dish dish = dishService.findByDishId(dishId);

        if (chef == null || dish == null) {
            return null; // или фрли исклучок
        }

        if (chef.getDishes() != null) {
            chef.getDishes().add(dish);
        } else {
        }

        return chef;
    }
}