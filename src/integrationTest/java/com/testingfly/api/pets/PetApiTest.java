package com.testingfly.api.pets;

import com.testingfly.ApiClient;
import com.testingfly.Category;
import com.testingfly.Pet;
import com.testingfly.Tag;
import com.testingfly.api.PetApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetApiTest {
    private final static int port = 8080;
    private final static String host = "localhost";
    private static PetApi petApi;

    @BeforeAll
    public static void beforeAll() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(String.format("http://%s:%s", host, port));
        petApi = new PetApi(apiClient);
    }

    @Test
    public void shouldGetPet() {
        assertEquals("Sky", petApi.getPetById(1L).getName());
    }

    private static List<Pet> failingCreates() {
        return List.of(
            new Pet().name(null),
            new Pet().id(1L),
            new Pet().category(new Category()),
            new Pet().name("Sunny")        );
    }
    @ParameterizedTest
    @MethodSource("failingCreates")
    public void shouldFailForInvalidCreates(Pet createPetRequest) {
        Assertions.assertThrows(Exception.class, () -> petApi.addPet(createPetRequest));
    }

    @Test
    public void shouldCreatePet() {
        Category category = new Category();
        List<String> photoUrls = Collections.singletonList("xyz.com");
        List<Tag> tags = Collections.singletonList(
                new Tag()
                        .id(1L)
                        .name("Birdie"));

        Pet pet = new Pet()
            .id(12L)
            .name("Sky")
            .category(category)
            .photoUrls(photoUrls)
            .tags(tags)
            .status(Pet.StatusEnum.AVAILABLE);

        assertEquals(HttpStatus.CREATED,
            petApi.addPetWithHttpInfo(
                pet
            ).getStatusCode());
    }

    @Test
    public void shouldUpdatePet() {
        assertEquals(HttpStatus.OK,
            petApi.updatePetWithHttpInfo(new Pet()).getStatusCode());
    }
}