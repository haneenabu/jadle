package models;


import java.time.LocalDateTime;

public class Review {
    private String writtenBy;
    private int rating;
    private LocalDateTime createdAt;
    private int id;
    private int restaurantId; //i will be used to connect Restaurant to Review.

    public Review(String writtenBy, int rating, int restaurantId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.restaurantId = restaurantId;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (id != review.id) return false;
        if (restaurantId != review.restaurantId) return false;
        return writtenBy != null ? writtenBy.equals(review.writtenBy) : review.writtenBy == null;
    }

    @Override
    public int hashCode() {
        int result = writtenBy != null ? writtenBy.hashCode() : 0;
        result = 31 * result + rating;
        result = 31 * result + id;
        result = 31 * result + restaurantId;
        return result;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


    //we will create the setter for createdAt in a later lesson.
}
