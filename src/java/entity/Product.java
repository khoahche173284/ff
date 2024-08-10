/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Quang
 */
public class Product {

    private int id;
    private String productName;
    private String price;
    private String description;
    private List<String> features;
    private String duration;

    public Product() {
    }

    public Product(int id, String productName, String price, String description, List<String> features, String duration) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.features = features;
        this.duration = duration;
    }

    public Product(int id, String productName, String price, String description, List<String> features) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.features = features;
    }

    public Product(int id, String productName, String price, String description, String duration) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.duration = duration;
    }

   

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

//    @Override
//    public String toString() {
//        return "Product{" + "id=" + id + ", productName=" + productName + ", price=" + price + ", description=" + description + ", features=" + features + '}';
//    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productName=" + productName + ", price=" + price + ", description=" + description + ", features=" + features + ", duration=" + duration + '}';
    }
    

}