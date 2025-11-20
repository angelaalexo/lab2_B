package mk.ukim.finki.wp.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab1.model.Chef;
import mk.ukim.finki.wp.lab1.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        chefs.add(new Chef(0L, "Gordon", "Ramsey", "A really angry chef.", new ArrayList<>()));
        chefs.add(new Chef(1L, "Jamie", "Oliver", "A really calm chef.", new ArrayList<>()));
        chefs.add(new Chef(2L, "Marco", "Pierre White", "Youngest chef to ever be awarded three Michelin stars.", new ArrayList<>()));
        chefs.add(new Chef(3L, "Gino", "D'Acampo", "One of the best italian chefs and TV personality.", new ArrayList<>()));
        chefs.add(new Chef(4L, "Guy", "Fieri", "An American restaurateur, author and an Emmy Award winning television presenter.", new ArrayList<>()));

        dishes.add(new Dish("dish_0", "Pasta Carbonara", "Italian", 25));
        dishes.add(new Dish("dish_1", "Beef Wellington", "British", 60));
        dishes.add(new Dish("dish_2", "Chicken Tikka Masala", "Indian", 45));
        dishes.add(new Dish("dish_3", "Pepperoni Pizza", "Italian", 15));
        dishes.add(new Dish("dish_4", "Quiche Lorraine", "French", 30));
    }
}
