package com.petclinic.controller;

import com.petclinic.entity.Pet;
import com.petclinic.repository.OwnerRepository;
import com.petclinic.repository.PetRepository;
import com.petclinic.request.CreatePetRequest;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller(PetController.PETS)
@Validated
public class PetController {

  public static final String PETS = "/pets";

  private final PetRepository petRepository;
  private final OwnerRepository ownerRepository;

  public PetController(PetRepository petRepository, OwnerRepository ownerRepository) {
    this.petRepository = petRepository;
    this.ownerRepository = ownerRepository;
  }

  @Get
  public List<Pet> list(
    @Nullable @QueryValue Integer ownerId
  ) {
    if (ownerId != null) {
      return petRepository.findAllByOwnerId(ownerId);
    }
    return petRepository.findAll();
  }

  @Get("/{id}")
  public Optional<Pet> show(Integer id) {
    return petRepository.findById(id);
  }

  @Post
  public HttpResponse<Pet> save(@Valid CreatePetRequest createPetRequest) {
    Pet pet = new Pet()
      .setName(createPetRequest.name)
      .setBirthDate(createPetRequest.birthDate)
      .setOwner(ownerRepository.findByIdOrThrow(createPetRequest.ownerId));
    petRepository.save(pet);
    return HttpResponse
      .created(pet)
      .headers(headers -> headers.location(location(pet.getId())));
  }

  @Delete("/{id}")
  public HttpResponse<?> delete(Integer id) {
    petRepository.delete(petRepository.findByIdOrThrow(id));
    return HttpResponse.noContent();
  }

  protected URI location(Integer id) {
    return URI.create(PETS + "/" + id);
  }
}
