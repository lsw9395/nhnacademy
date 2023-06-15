package exercise;

import java.util.ArrayList;

public class FoodStand {
    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food onion1) {
        foods.add(onion1);
    }
    public ArrayList<Food> getFoods(){
        return foods;
    }
}
