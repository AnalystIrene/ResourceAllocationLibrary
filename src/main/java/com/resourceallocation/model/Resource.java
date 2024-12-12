
package com.resourceallocation.model;

public class Resource {
    private int id;
    private int capacity;
    private int availability;

    public Resource(int id, int capacity, int availability) {
        if (capacity < 0 || availability < 0) {
            throw new IllegalArgumentException("Capacity and availability must be non-negative.");
        }
        this.id = id;
        this.capacity = capacity;
        this.availability = availability;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative.");
        }
        this.capacity = capacity;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        if (availability < 0) {
            throw new IllegalArgumentException("Availability must be non-negative.");
        }
        this.availability = availability;
    }

    public boolean isAvailable() {
        return availability > 0;
    }
}