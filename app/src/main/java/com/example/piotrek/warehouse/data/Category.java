package com.example.piotrek.warehouse.data;

/**
 * Created by Piotrek on 2017-06-10.
 */

public class Category implements IParsable {
    private int id;
    private String name;
    private Category parent;

    public Category(int id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String parseName() {
        return getName();
    }

    public int getId() {
        return id;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
