package mk.ukim.finki.wp.lab1.repository.impl;

import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1.repository.DishRepository;
import mk.ukim.finki.wp.lab1.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes
                .stream()
                .filter(d -> d.getDishId().toString().equals(dishId))
                .findFirst()
                .orElse(null);
    }
}
