package mk.ukim.finki.wp.lab1.repository;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;

import java.util.List;
import java.util.Optional;

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);

    List<Chef> listChefs();

    // Chef save(Chef chef);
    // void deleteById(Long id);
}