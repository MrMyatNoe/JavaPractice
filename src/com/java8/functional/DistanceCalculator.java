package com.java8.functional;

@FunctionalInterface
public interface DistanceCalculator{
    
    // only allowed to create method with object return type
    String getName();
        
    default void printName() {};

}
