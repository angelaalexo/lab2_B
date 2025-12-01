package mk.ukim.finki.wp.lab1.service;

import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;

import java.util.List;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
    Chef create(Long id, String firstName, String lastName, String bio);
    Chef update(Long id, String firstName, String lastName, String bio);
    void delete(Long id);
}