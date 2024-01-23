package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ivo
 */
public class Trip {
    private Drone drone;
    private final List<Location> locations = new ArrayList<>();
    private int totalWeight = 0;
    
   /**
    * Generates the optimal trip for the given list of locations and list of drones
    * The optimal trip is defined by the highest possible amount of locations X smallest possible drone
    * This also removes the fulfilled locations from the pendingLocations list
    * 
    * @author Ivo
     * @param pendingLocations list of pending location to be shipped
     * @param drones list of availables drones
    */
    public Trip(List<Location> pendingLocations, List<Drone> drones) {
        drone = drones.get(0);
        for(int larger = 0, smaller = pendingLocations.size() - 1; 
                larger <= smaller; 
                larger++, smaller--) {
            
            Location largerLocation = pendingLocations.get(larger);
            if(canAddLocationWithCurrentDrone(largerLocation)){
                addLocation(largerLocation);
            }
            if(smaller != larger) {
                Location smallerLocation = pendingLocations.get(smaller);
                if(canAddLocationWithCurrentDrone(smallerLocation)){
                    addLocation(smallerLocation);
                }   
            }
        }
        if(locations.isEmpty()) {
            throw new RuntimeException("Couldn`t find any viable trip for the remaining locations: "+ pendingLocations);
        }
        useNextLargestDroneThatSupportsCurrentWeight(drones);
        pendingLocations.removeAll(locations);
        drone.addTript(this);
    }
    
    private void addLocation(Location location){
        locations.add(location);
        totalWeight += location.getWeight();
    }
    
    private boolean canAddLocationWithCurrentDrone(Location location) {
        return drone.getMaximumWeight() >= totalWeight + location.getWeight();
    }
    
    private void useNextLargestDroneThatSupportsCurrentWeight(List<Drone> dronesList) {
        for(int i = dronesList.size() - 1; i >= 0; i--) {
            Drone currentDrone = dronesList.get(i);
            if(currentDrone.getMaximumWeight() >= totalWeight) {
                this.drone = currentDrone;
                return;
            }
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return "Trip{" + "drone=" + drone + ",\n totalWeight=" + totalWeight + ",\n locations=" + locations + '}';
    }
    
    
}
