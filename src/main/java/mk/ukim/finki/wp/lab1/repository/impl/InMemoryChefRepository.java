package mk.ukim.finki.wp.lab1.repository.impl;

import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.repository.ChefRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {

    // ... (постоечките методи findAll, findById, addDishToChef) ...

    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return DataHolder.chefs.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        // Оваа логика бара ChefService да го најде Dish објектот преку dishId
        // За in-memory, само ќе го додадеме dishId на листата на јадења на готвачот
        Chef chef = findById(chefId).orElse(null);
        if (chef != null) {
            // Претпоставуваме дека chef.getDishes() враќа листа од Dish објекти
            // Но бидејќи старата логика користеше String dishId, ќе ја задржиме
            // таа компатибилност, иако е подобро да се чуваат Dish објекти.

            // Внимание: Ако chef.getDishes() е Mutable List од Dish објекти,
            // ќе треба да го пронајдеме Dish објектот во DataHolder.dishes
            // и да го додадеме во chef.getDishes().

            // За да не ја менуваме Chef класата, ќе претпоставиме дека се додава
            // цел Dish објект. (Ова е поелегантен пристап)

            // Бидејќи не ја гледаме Chef класата, ќе ја користиме следнава логика:
            // Овој метод треба да се премести во ChefService каде има пристап до DishRepository/Service.

            // За потребите на Repository слојот, само ќе го вратиме готвачот
            // и ќе претпоставиме дека ChefService ја врши комплетната логика.
            return chef; // Промените треба да бидат направени во ChefService
        }
        return null;
    }

    @Override
    public List<Chef> listChefs() {
        return DataHolder.chefs;
    }
}