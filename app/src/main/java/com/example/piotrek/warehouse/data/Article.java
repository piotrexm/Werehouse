package com.example.piotrek.warehouse.data;

/**
 * Created by Piotrek on 2017-06-10.
 */

public class Article implements IParsable {
    private int id;
    private String name;
    private float price;
    private Category category;
    private Provider provider;

    public Article(int id, String name, float price, Category category, Provider provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    @Override
    public String parseName() {
        return getName();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
