/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.IllegalFormatException;

/**
 *
 * @author Ivo
 */
public class ValueBetweenBrackets {
    
    private final String rawValue;

    public ValueBetweenBrackets(String rawValue) {
        rawValue = rawValue.trim();
        if (rawValue.startsWith("[") && rawValue.endsWith("]")) {
            this.rawValue = rawValue.trim();
        } else {
            throw new IllegalArgumentException("The input string \""+rawValue+"\" has no square brackets around it.");
        }
    }
   
    public int getIntValue() {
        return Integer.parseInt(getStringValue());
    }
    
    public String getStringValue() {
       return this.rawValue.substring(1, this.rawValue.length() - 1);
    }
}
