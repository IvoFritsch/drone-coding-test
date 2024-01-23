/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ivo
 */
public class Main {

    public static void main(String[] args) {
       
        try {
            List<String> allLines = Files.readAllLines(Paths.get("./input.txt"));
            if(allLines.size() < 2) {
                throw new RuntimeException("The input.txt file must have at least 2 lines.");
            }
            // converts the raw file to an array containing all the lines, each one also being an array of Objects 
            //    representing the values in brackets(e.g. "[DroneA]") in that specific line
            List<List<ValueBetweenBrackets>> parsedFile = allLines.stream()
                    .filter(line -> !line.isBlank())
                    .map(line -> 
                            Arrays.stream(line.split(","))
                                    .map(item -> 
                                            new ValueBetweenBrackets(item)
                                    ).collect(Collectors.toList())
                    ).collect(Collectors.toList());
            List<Drone> availableDrones = new ArrayList<>();
            // Goes through the first line and add each drone to a list
            List<ValueBetweenBrackets> firstLine = parsedFile.get(0);
            for(int i = 1; i < firstLine.size(); i = i+2) {
                availableDrones.add(new Drone(firstLine.get(i - 1), firstLine.get(i), i - 1));
            }
            // Sorts the drones from the largest to the smallest
            availableDrones.sort((droneA, droneB) -> droneB.getMaximumWeight() - droneA.getMaximumWeight());
            // Declared as a Liked List because this list will be intenselly modified from now on
            List<Location> pendingLocations = new LinkedList<>();
            for(int i = 1; i < parsedFile.size(); i++) {
                List<ValueBetweenBrackets> line = parsedFile.get(i);
                pendingLocations.add(new Location(line.get(0), line.get(1)));
            }
            // Sort the locations from the higher to the lower weight
            pendingLocations.sort((locationA, locationB) -> locationB.getWeight() - locationA.getWeight());
            // Generate the trips list
            List<Trip> trips = new LinkedList<>();
            while (!pendingLocations.isEmpty()) {
                trips.add(new Trip(pendingLocations, availableDrones));
            }
            // Construct the output text
            StringBuilder output = new StringBuilder();
            // Resort the drones in the original order again
            availableDrones.sort((droneA, droneB) -> droneA.getOriginalIndex() - droneB.getOriginalIndex());
            availableDrones.forEach(d -> {
                output.append("[");
                output.append(d.getName());
                output.append("]\n");
                List<Trip> droneTrips = d.getTrips();
                for (int i = 0; i < droneTrips.size(); i++) {
                    output.append("Trip #");
                    output.append(i + 1);
                    output.append("\n");
                    output.append(droneTrips.get(i).getLocations().stream().map(Location::toString).collect(Collectors.joining(", ")));
                    output.append("\n");
                }
                output.append("\n");
            });
            System.out.println(output);
            try {
                Files.writeString(Paths.get("./output.txt"), output);
            } catch (IOException e) {
                System.err.println("ERROR: Couldn`t write output.txt file");
            }
        } catch (IOException ex) {
            System.err.println("ERROR: Couldn`t read input.txt file");
        } catch (Exception ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
        
        
    }
}
