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
        // Run hrough the locations from outside out
        for(int larger = 0, smaller = pendingLocations.size() - 1; 
                larger <= smaller; 
                larger++, smaller--) {
            // Inserts the largest pending location if it is supported by the drone
            Location largerLocation = pendingLocations.get(larger);
            if(canAddLocationWithCurrentDrone(largerLocation)){
                addLocation(largerLocation);
            }
            // If the smaller current location is different than the one just inserted 
            if(smaller != larger) {
                // Insert the smallest pending location if its suported by the current drone
                Location smallerLocation = pendingLocations.get(smaller);
                if(canAddLocationWithCurrentDrone(smallerLocation)){
                    addLocation(smallerLocation);
                }   
            }
        }
        if(locations.isEmpty()) {
            throw new RuntimeException("Couldn`t find any viable trip for the remaining locations: "+ pendingLocations);
        }
        // After finished, check if heres some smaller drone that can take this trip
        useNextLargestDroneThatSupportsCurrentWeight(drones);
        pendingLocations.removeAll(locations);
        drone.addTrip(this);
    }
    
    private void addLocation(Location location){
        locations.add(location);
        totalWeight += location.getWeight();
    }
    
    private boolean canAddLocationWithCurrentDrone(Location location) {
        return drone.getMaximumWeight() >= totalWeight + location.getWeight();
    }
    
    private void useNextLargestDroneThatSupportsCurrentWeight(List<Drone> dronesList) {
        // Changes the drone of this trip to smallest one that will support the total weight
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

    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Trip{" + "drone=" + drone + ",\n totalWeight=" + totalWeight + ",\n locations=" + locations + '}';
    }
    
    
}
