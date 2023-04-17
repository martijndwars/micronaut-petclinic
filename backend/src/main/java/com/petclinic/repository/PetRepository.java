package com.petclinic.repository;

import com.petclinic.entity.Pet;
import com.petclinic.exception.EntityNotFoundException;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class PetRepository extends BaseRepository {

  private final List<Pet> pets;

  public PetRepository() {
    this.pets = new ArrayList<>();
  }

  public Pet findByIdOrThrow(Integer id) {
    return findById(id).orElseThrow(() ->
      new EntityNotFoundException("Pet with ID " + id + " not found.")
    );
  }

  public Optional<Pet> findById(Integer id) {
    for (Pet pet : pets) {
      if (pet.getId().equals(id)) {
        return Optional.of(pet);
      }
    }
    return Optional.empty();
  }

  public List<Pet> findAll() {
    return new ArrayList<>(pets);
  }

  public List<Pet> findAllByOwnerId(Integer ownerId) {
    return pets
      .stream()
      .filter(pet -> pet.getOwner().getId().equals(ownerId))
      .collect(Collectors.toList());
  }

  public Pet save(Pet pet) {
    if (pet.getId() == null) {
      pet.setId(getNextId());
    }
    pets.add(pet);
    return pet;
  }

  public void delete(Pet pet) {
    this.pets.remove(pet);
  }
}
