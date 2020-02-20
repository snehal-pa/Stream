package com.company;

public class Pet {
    public static final String [] PET = {"Cat","Dog","Rat","Rabbit"};

    private  String name;

    private String type;

    public Pet(String type,String name) {
        this.name = name;
        this.type =type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return String.format("%s: %s",type,name);
    }
}
