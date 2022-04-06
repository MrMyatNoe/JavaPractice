package com.java8.functional;

public class TestFuncionalInterfaceDemo{

    
    public static void main(String[] args) {
        MethodReference ref = new MethodReference();
        DistanceCalculator name = ref::getName;
        System.out.println(name.getName());
    } 
 
}
