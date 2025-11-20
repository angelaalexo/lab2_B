package mk.ukim.finki.wp.lab1.service.impl;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;
import mk.ukim.finki.wp.lab1.repository.ChefRepository;
import mk.ukim.finki.wp.lab1.repository.DishRepository;
import mk.ukim.finki.wp.lab1.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = findById(chefId);
        List<Dish> dishes = chef.getDishes();
        Dish targetDish = dishRepository.findByDishId(dishId);
        if(targetDish != null && !dishes.contains(targetDish)) {
            dishes.add(targetDish);
            chef.setDishes(dishes);
        }
        return chefRepository.save(chef);
    }
}
