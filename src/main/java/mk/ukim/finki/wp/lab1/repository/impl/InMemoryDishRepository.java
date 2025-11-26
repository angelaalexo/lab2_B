package mk.ukim.finki.wp.lab1.repository.impl;

import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1.model.Dish;
import mk.ukim.finki.wp.lab1.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream()
                .filter(d -> d.getDishId().equals(dishId))
                .findFirst()
                .orElse(null); // Враќа null ако не најде јадење по dishId
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst(); // Враќа Optional<Dish>
    }

    @Override
    public Dish save(Dish dish) {
        // Ако јадењето има ID, го бараме и го бришеме старото
        if (dish.getId() != null) {
            DataHolder.dishes.removeIf(d -> d.getId().equals(dish.getId()));
        }

        // Ако dish.id е null, тоа е ново јадење, но мора да го користиме
        // конструкторот за да му се генерира ново ID.
        // Сепак, бидејќи имаме само еден конструктор, ќе претпоставиме
        // дека `dish` објектот е веќе правилно креиран со ID.
        // Ако користиме Lombok @Setter, можеме да го ажурираме постоечкиот објект наместо да го бришеме.

        // За поедноставна имплементација на In-Memory Repository:
        // 1. Прво го отстрануваме стариот објект (ако постои)
        DataHolder.dishes.removeIf(d -> d.getId() != null && d.getId().equals(dish.getId()));

        // 2. Потоа го додаваме новиот објект.
        DataHolder.dishes.add(dish);

        return dish;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(d -> d.getId().equals(id));
    }
}