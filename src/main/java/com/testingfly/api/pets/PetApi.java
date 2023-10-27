package com.testingfly.api.pets;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class PetApi implements PetOperations {
    @Override
    public Pet getPet(final long id) {

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("xyz.com");

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "Birdie"));

        return new Pet(
                id,
                "Sky",
                new Category(1, "Bird"),
                photoUrls,
                tags,
                "available"
        );
    }

    @Override
    public List<Pet> getPets() {


        List<String> photoUrls = Collections.singletonList("xyz.com");
        List<Tag> tags = Collections.singletonList(
                new Tag(1, "Birdie"));


        return List.of(new Pet(
                11L,
                "Sunny",
                new Category(1, "Bird"),
                photoUrls,
                tags,
                "pending"
        ));
    }

    @Override
    public void updatePet(final Pet pet) {
        System.out.println(pet);
    }

    @Override
    public void createPet(final Pet pet) {
        System.out.println(pet);
        if (pet.status() == null) {
            throw new RuntimeException();
        }
    }
}
