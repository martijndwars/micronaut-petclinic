package com.petclinic.repository;

import com.petclinic.entity.Vet;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class VetRepository extends BaseRepository {

  private final List<Vet> vets;

  public VetRepository() {
    super();
    this.vets = new ArrayList<>();
  }

  public Optional<Vet> findById(Integer id) {
    for (Vet vet : vets) {
      if (vet.getId().equals(id)) {
        return Optional.of(vet);
      }
    }
    return Optional.empty();
  }

  public List<Vet> findAll() {
    return new ArrayList<>(vets);
  }

  public Vet save(Vet vet) {
    if (vet.getId() == null) {
      vet.setId(getNextId());
    }
    vets.add(vet);
    return vet;
  }

  public void delete(Vet vet) {
    this.vets.remove(vet);
  }
}
