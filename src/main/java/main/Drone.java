/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ivo
 */
public class Drone {
    private final String name;
    private final int maximumWeight;
    private final int originalIndex;
    private final List<Trip> trips = new ArrayList<>();
    
    public Drone(ValueBetweenBrackets name, ValueBetweenBrackets maximumWeight, int index) {
        this.name = name.getStringValue();
        this.maximumWeight = maximumWeight.getIntValue();
        this.originalIndex = index;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public String getName() {
        return name;
    }
    
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() {
        return Collections.unmodifiableList(trips);
    }

    public int getOriginalIndex() {
        return originalIndex;
    }

    @Override
    public String toString() {
        return "Drone{" + "name=" + name + ", maximumWeight=" + maximumWeight + '}';
    }
}
