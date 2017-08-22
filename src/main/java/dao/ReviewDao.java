package dao;

import models.Restaurant;
import models.Review;

import java.util.List;

public interface ReviewDao {
    //create
    void add(Review review); //F

    //get all
    List<Review> getAllReviewsByRestaurant(int restaurantId); // H & G

    //find by id
    Review findById(int id);

    //update
    void update(String writtenBy, int rating, int restaurantId, int id, String content);

    //delete
    void deleteById(int id); //M
}
