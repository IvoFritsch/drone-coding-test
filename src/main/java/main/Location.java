/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Ivo
 */
public class Location {
    private final String name;
    private final int weight;
    
    public Location(ValueBetweenBrackets name, ValueBetweenBrackets maximumWeight) {
        this.name = name.getStringValue();
        this.weight = maximumWeight.getIntValue();
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}
