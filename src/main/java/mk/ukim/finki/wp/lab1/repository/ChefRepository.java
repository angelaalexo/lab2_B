package mk.ukim.finki.wp.lab1.repository;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;

import java.util.List;
import java.util.Optional;

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);

    // Додадено: Метод за листање на сите готвачи
    List<Chef> listChefs();

    // Додадено: Методи за CRUD операции (ако се потребни)
    // Chef save(Chef chef);
    // void deleteById(Long id);
}