package com.company;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Person {
    private String name;

    private String age;

    private ArrayList<Pet> personHasPets = new ArrayList<>();


    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }


    public ArrayList<Pet> getPets() {
        return personHasPets;
    }


    public void getPet(Pet pet) {
        personHasPets.add(pet);
    }

    private String displayPet() { // to display Result 1
        StringBuilder s = new StringBuilder();
        if (!personHasPets.isEmpty()) {
            personHasPets.forEach(p -> s.append(p.toString()).append(", "));
            return s.toString().substring(0, s.toString().length() - 2);
        }
        return "";
    }

    public int getPetNumber() {
        return personHasPets.size();
    }

    @Override
    public String toString() {
        return "Person " + name + "(" + age + ")" + "owns the animals:" + "[" + displayPet() + "]";
    }
}
