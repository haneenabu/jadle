package dao;

import models.Restaurant;

import java.util.List;

public interface RestaurantDao {
    //create
    void add (Restaurant restaurant); //J

    //get all
    List<Restaurant> getAll(); //A

    //find
    Restaurant findById(int id); //B & C

    //update
    void update(int id, String name, String address, String zipcode, String phone, String website, String email, String image); //L

    //delete
    void deleteById(int id); //K

    // void addRestaurantToFoodType(Restaurant restaurant, Foodtype foodtype) //D & E

    // List<Foodtype> getAllFoodtypesForARestaurant(int restaurantId); //D & E - we will implement this soon.


}
