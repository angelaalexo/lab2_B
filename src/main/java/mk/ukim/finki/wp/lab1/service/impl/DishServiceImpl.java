package mk.ukim.finki.wp.lab1.service.impl;

import mk.ukim.finki.wp.lab1.model.Dish;
import mk.ukim.finki.wp.lab1.repository.DishRepository;
import mk.ukim.finki.wp.lab1.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    // Dependency Injection преку конструктор
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        // Користиме orElse(null) за да го вратиме јадењето или null
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        // Креирање нов Dish објект. ID-то се генерира во конструкторот на Dish.
        Dish dish = new Dish(dishId, name, cuisine, preparationTime);

        // Зачувување на новиот објект во репозиториумот
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        // 1. Наоѓање на постоечкото јадење
        Dish dish = dishRepository.findById(id).orElse(null);

        if (dish == null) {
            // Фрлање исклучок или враќање null ако не е пронајдено
            return null;
        }

        // 2. Ажурирање на својствата на постоечкиот објект
        // (ID-то останува исто)
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        // 3. Зачувување на ажурираниот објект
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}