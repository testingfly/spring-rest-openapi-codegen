package com.testingfly.api.pets;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

//@RequestMapping("/")
@Validated
public interface PetOperations {

    @Operation(summary = "Get a pet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the pet",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet not found",
                    content = @Content) })
    @GetMapping("/pet/{id}")
    @ResponseStatus(HttpStatus.OK)
    Pet getPet(@PathVariable long id);

    @Operation(summary = "Get all pets")
    @GetMapping("/pets")
    @ResponseStatus(HttpStatus.OK)
    List<Pet> getPets();

    @Operation(summary = "Update an existing Pet")
    @PutMapping("/pet")
    @ResponseStatus(HttpStatus.OK)
    void updatePet(@Valid @RequestBody final PetOperations.Pet pet);

//    void updatePet(String id);

    @Operation(summary = "Create a new pet")
    @PostMapping("/pet")
    @ResponseStatus(HttpStatus.CREATED)
    void createPet(@Valid @RequestBody final PetOperations.Pet pet);

    enum Status {
        AWAKE("awake"),
        SLEEPING("sleeping"),
        PLAYING("playing");

        Status(String status) {
        }
    }

    record Pet(
            Long id,
            String name,
            Category category,
            List<String> photoUrls,
            List <Tag> tags,
            String status) {}

    record Category(
            int id,
            String name
    ) {}

    record Tag(
            int id,
            String name
    ){}

//    @Data
//    @AllArgsConstructor
//    @Validated
//    class CreatePetRequest {
//        @NotBlank
//        @Size(min = 1, max = 50)
//        private String name;
//        @NotBlank
//        @Size(min = 1, max = 50)
//        private String tags;
//        @Positive
//        private long id;
//        private String status;
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            CreatePetRequest that = (CreatePetRequest) o;
//            return id == that.id && Objects.equals(name, that.name) && Objects.equals(tags, that.tags);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(name, tags, id);
//        }
//    }
}
