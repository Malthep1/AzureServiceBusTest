package com.map.azureservicebuscheeseservice.models;


public abstract class Cheese {
    private int id;
    private String name;
    private CheeseData cheeseData;

    public Cheese()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheeseData getCheeseData() {
        return cheeseData;
    }

    public void setCheeseData(CheeseData cheeseData) {
        this.cheeseData = cheeseData;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cheese{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cheeseData=" + cheeseData +
                '}';
    }
}
