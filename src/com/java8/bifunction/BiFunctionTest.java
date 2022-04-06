package com.java8.bifunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BiFunctionTest {
    
    private static final double DISCOUNT_RATE = 2.0;

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Thet");
        map.put(2, "Htet");
        map.put(3, "Wint");
        
        System.out.println("========== Function type demo ==========");
        System.out.println("map.get(4): " + map.get(4));
        // direct with lambda
        map.computeIfAbsent(4, (name) -> "hello");
        System.out.println("map.get(4): " + map.get(4));
        
        // method reference
        map.computeIfAbsent(5, BiFunctionTest::addNameIfAbsent);
        System.out.println("map.get(5): " + map.get(5));
        
        System.out.println("========== BiFunction type demo ==========");
        map.computeIfPresent(3, (key, value) -> key + " : " + value);
        System.out.println("map.get(3): " + map.get(3));
        
        map.computeIfPresent(2, BiFunctionTest::addNameIfPresent);
        System.out.println("map.get(2): " + map.get(2));
        
        System.out.println("========== Compose Functions type demo ==========");
        Map<Product, Double> productDiscountMap = new HashMap<>();
        
        List<Product> products = new ArrayList<>(Arrays.asList(new Product(1, 199.99), 
                new Product(2, 79.99),
                new Product(3, 49.99)));
        
        for (Product product : products) {
            Function<Product, Double> getPriceFunction
                = getProduct -> getProduct.getPrice();
             
            Function<Double, Double> getDiscountFunction
                = price -> price * DISCOUNT_RATE;
               
            Function<Product, Double> getPriceAndThenDiscountFunction 
            = getPriceFunction.andThen(getDiscountFunction);
           
            Function<Product, Double> getPriceAndThenDiscountFunction2 
            = getDiscountFunction.compose(getPriceFunction);
                
            productDiscountMap.computeIfAbsent(product, getPriceAndThenDiscountFunction);
        }
        System.out.println(productDiscountMap);
        System.out.println("========== Comparator.comparing() demo ==========");
        products.sort(Comparator.comparing(Product::getPrice));
        //products.sort(Comparator.comparing(product -> product.getPrice()));
        System.out.println(products);
    }
    
    private static String addNameIfAbsent(int number) {
        return "Absent " + Integer.toString(number);
    }
    
    private static String addNameIfPresent(int key, String value) {
        return "Present " + Integer.toString(key) + " "+ value;
    }
}

class Product {
    private int id;
    private double price;
    
    public Product(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", price=" + price + "]";
    }
    
    
}
