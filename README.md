# Drone Coding Test
A solution for the drone partitions problem, where theres no time and drone size concerns
## Explanation
The purpose of this algorithm is to try to get the smallest amount of trips as possible 

This solution tries lower the amount of trips by optimizing the weight of each trip trying to reach the nearest to the maximum weight allowed by the largest drone.
It works by basically analysing the list of pending deliveries sorted by weight from outside-in:

- Generates a new empty Trip to he largest available drone
- Adds the largest pending delivery to this trip
- Adds the smallest pending delivery to the trip, if theres still weight available
- Adds the largest pending location if theres still weight available
- Continues this cycle (larger-smaller-larger-smaller) untils the available drone is filled or the next smaller location is larger than the available space
- After finalizing filling the trip, checks if theres some smaller drone that can take this trip and uses it
- Repeat everything until all locations are fullfilled

  The code takes an `input.txt` file formatted as the following:
```
[DroneA], [200], [DroneB], [250], [DroneC], [100]
[LocationA], [200]
[LocationF], [200]
[LocationB], [150]
[LocationD], [150]
[LocationE], [100]
[LocationP], [90]
[LocationH], [80]
[LocationI], [70]
[LocationJ], [50]
```
  and outputs a file named `output.txt`, as the following:
```
[DroneA]
Trip #1
[LocationF]
Trip #2
[LocationE], [LocationP]

[DroneB]
Trip #1
[LocationA], [LocationJ]
Trip #2
[LocationB], [LocationI]
Trip #3
[LocationD], [LocationH]

[DroneC]
```
## Tools

The code was written in Java, compatible with Java 8 and have Maven as the runner and dependency manager tool.
It has no external dependency other than Java JDK
It was written using Netbeans as the IDE
