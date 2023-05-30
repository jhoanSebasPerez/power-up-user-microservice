package com.pragma.powerup.usermicroservice.domain.model;

public class Employee {

    private User user;
    private Long restaurantId;

    public Employee(){}

    public Employee(User user, Long restaurantId) {
        this.user = user;
        this.restaurantId = restaurantId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
