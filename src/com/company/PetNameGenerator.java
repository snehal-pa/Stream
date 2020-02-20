package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class    PetNameGenerator {

    // make this a singleton
    private static final PetNameGenerator instance = new PetNameGenerator();

    private List<String> petNameList = null;

    private PetNameGenerator() {
        loadNamesFromFile();
    }

    public static PetNameGenerator getInstance() {
        return instance;
    }

    public String getName(int i) {
        return petNameList.get(i);
    }

    private List<String> loadNamesFromFile() {
        Path path = Paths.get("pet-names-4.txt");

        try {
            petNameList = Files.lines(path)
                    .flatMap(s -> Stream.of(s.split("\"")))
                    .flatMap(s -> Stream.of(s.split("[0-9]")))
                    .flatMap(s -> Stream.of(s.split(",")))
                    .flatMap(s -> Stream.of(s.split(" ")))
                    .filter(s -> !s.equals("."))
                    .filter(s -> !s.equals(""))
                    //.flatMap(s ->Stream.of(s.split(". ")))
                    //.peek(System.out::println)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return petNameList;

    }

    public List<String> getPetNameList() {
        return petNameList;
    }
}



