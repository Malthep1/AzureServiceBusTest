package com.map.azureservicebuscheeseservice.models;

public class CheeseData {
    private String description;
    private String goodFor;
    private int smellFactor;

    public CheeseData() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoodFor() {
        return goodFor;
    }

    public void setGoodFor(String goodFor) {
        this.goodFor = goodFor;
    }

    public int getSmellFactor() {
        return smellFactor;
    }

    public void setSmellFactor(int smellFactor) {
        this.smellFactor = smellFactor;
    }

    @Override
    public String toString() {
        return "CheeseData{" +
                "description='" + description + '\'' +
                ", goodFor='" + goodFor + '\'' +
                ", smellFactor=" + smellFactor +
                '}';
    }
}
