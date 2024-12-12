package com.resourceallocation.model;

public class Task {
    private int id;
    private int timeRequired;
    private int performanceRequired;

    public Task(int id, int timeRequired, int performanceRequired) {
        if (timeRequired <= 0 || performanceRequired <= 0) {
            throw new IllegalArgumentException("Time required and performance required must be positive.");
        }
        this.id = id;
        this.timeRequired = timeRequired;
        this.performanceRequired = performanceRequired;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(int timeRequired) {
        if (timeRequired <= 0) {
            throw new IllegalArgumentException("Time required must be positive.");
        }
        this.timeRequired = timeRequired;
    }

    public int getPerformanceRequired() {
        return performanceRequired;
    }

    public void setPerformanceRequired(int performanceRequired) {
        if (performanceRequired <= 0) {
            throw new IllegalArgumentException("Performance required must be positive.");
        }
        this.performanceRequired = performanceRequired;
    }

    public boolean isUrgent() {
        return timeRequired < 10; // Example threshold for urgency
    }
}
