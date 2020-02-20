package com.company;

import java.util.ArrayList;
import java.util.Random;

public class PetStore {
    private static final PetStore instance = new PetStore();

    private ArrayList<Pet> petsInStore = new ArrayList<>();

    private int numOfPets = PetNameGenerator.getInstance().getPetNameList().size();

    private PetStore() {
        createPetStore();
    }

    public static PetStore getInstance() {
        return instance;
    }

    public void createPetStore() {
        Random random = new Random();
        for (int i = 0; i < numOfPets; i++) {
            Pet pet = new Pet(Pet.PET[random.nextInt(Pet.PET.length)], PetNameGenerator.getInstance().getName(i));
            petsInStore.add(pet);
            // System.out.println(pet);
        }

    }

    public void visit (Person p){
        Random random = new Random();
        int numOfPetsAPersonGets = random.nextInt(6); // 1 person can have 0 - 5 pet
        for(int i=0; i< numOfPetsAPersonGets; i++){
            int randomPetNum = random.nextInt(petsInStore.size());
            if(!petsInStore.isEmpty()){
                p.getPet(petsInStore.get(randomPetNum));
                petsInStore.remove(randomPetNum);
            }
        }
    }

//    public  ArrayList<Pet> getPetsInStore() {
//        return petsInStore;
//    }
}
