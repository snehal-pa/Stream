package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    private List<String[]> personInfo;

    private List<Person> persons = new ArrayList<>();


    public Program() {


        createPeople();             // cr8 list of 20 ppl
        assignRandomPetToPerson();  // loop thru all ppl   {  have each perfson visit the pet store
        // inside petStore.visit(Person p) the person gets/buys 0-5 pets
        //if the store has  no pets ledft to sell - the person visiting gets 0 pets (o crash)
        //}
        sortResult_1();
        sortResult_2();
        sortResult_3();
    }

    public List<String[]> loadPersonInfoList() {
        Path path = Paths.get("person.csv");
        try {
            personInfo = Files.lines(path)
                    .map(s -> s.split(","))
                    //.peek(System.out::println)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return personInfo;


    }

    private void createPeople() {
        loadPersonInfoList().forEach(s -> persons.add(new Person(s[0], s[1])));

    }

    private void assignRandomPetToPerson() {
        // persons.forEach(p -> PetStore.getInstance().visit(p));
        persons.forEach(PetStore.getInstance()::visit);
    }

    private void sortResult_1() {
        persons.forEach(System.out::println);
    }

    private void sortResult_2() {
        List<Person> sortedByName = persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());

        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Persons sorted by names and Animal grouping");
        System.out.println("---------------------------------------------------------------------------\n");
        sortedByName.forEach(this::displayPetByGrouping);
        //sortedByName.forEach(p -> displayPetByGrouping(p));
    }

    private void sortResult_3() {
        List<Person> sortedByPetNum = persons.stream()
                .sorted(Comparator.comparingInt(Person::getPetNumber))
                .collect(Collectors.toList());

        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Persons sorted by number of pets");
        System.out.println("---------------------------------------------------------------------------\n");

        sortedByPetNum.forEach(this::displayResult3);
    }

    private void displayPetByGrouping(Person p) { // to display Result 2
        String petString = "";
        if (!p.getPets().isEmpty())
            petString = getPetGroupingString(p);
        System.out.println("Person " + p.getName() + "(" + p.getAge() + ")" + "owns the animals:" + "[" + petString + "]");


    }

    private void displayResult3(Person p) {
        if (p.getPets().size() == 1) {
            System.out.println("Person " + p.getName().toUpperCase() + "(" + p.getAge() + ")" + " owns the " +
                    p.getPets().get(0).getType() + ":" + p.getPets().get(0).getName());
        } else if (p.getPets().size() > 1) {
            System.out.println("Person " + p.getName().toUpperCase() + "(" + p.getAge() + ")" + " owns the animals:" + "[" + getPetGroupingString(p) + "]");

        }

    }

    private String getPetGroupingString(Person p) {
        String petString;
        Map<String, Set<String>> petNameByGrouping = p.getPets().stream().collect(
                Collectors.groupingBy(Pet::getType,
                        Collectors.mapping(Pet::getName, Collectors.toSet())));
        StringBuilder s = new StringBuilder();

        petNameByGrouping.entrySet().forEach(entry -> {
            if (entry.getValue().size() > 1) {
                s.append(entry.getKey() + "s:" + entry.getValue() + ";  ");
            } else {
                s.append(entry.getKey() + ":" + entry.getValue() + ";  ");
            }
        });

        petString = s.toString().substring(0, s.toString().length() - 3).replace("[", "").replace("]", "");

        return petString;
    }


}
