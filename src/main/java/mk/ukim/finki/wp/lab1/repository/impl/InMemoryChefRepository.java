package mk.ukim.finki.wp.lab1.repository.impl;

import mk.ukim.finki.wp.lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.repository.ChefRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {

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
        Chef chef = findById(chefId).orElse(null);
        if (chef != null) {
            // Во in-memory репозиториум нема потреба да го зачувуваме,
            // бидејќи Chef објектот е референца и е веќе ажуриран во сервисот.
            return chef;
        }
        return null;
    }

    @Override
    public List<Chef> listChefs() {
        return DataHolder.chefs;
    }

    @Override
    public Chef save(Chef chef) {
        // Ако ID постои, го отстрануваме стариот елемент.
        if (chef.getId() != null) {
            DataHolder.chefs.removeIf(d -> d.getId().equals(chef.getId()));
        }

        DataHolder.chefs.add(chef);

        return chef;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.chefs.removeIf(d -> d.getId().equals(id));
    }
}