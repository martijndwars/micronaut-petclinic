package com.petclinic.repository;

import com.petclinic.entity.Owner;
import com.petclinic.exception.EntityNotFoundException;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class OwnerRepository extends BaseRepository {

  private final List<Owner> owners;

  public OwnerRepository() {
    super();
    this.owners = new ArrayList<>();
  }

  public Owner findByIdOrThrow(Integer id) {
    return findById(id).orElseThrow(() ->
      new EntityNotFoundException("Owner with ID " + id + " not found.")
    );
  }

  public Optional<Owner> findById(Integer id) {
    for (Owner owner : owners) {
      if (owner.getId().equals(id)) {
        return Optional.of(owner);
      }
    }
    return Optional.empty();
  }

  public List<Owner> findAll() {
    return new ArrayList<>(owners);
  }

  public Owner save(Owner owner) {
    if (owner.getId() == null) {
      owner.setId(getNextId());
    }
    owners.add(owner);
    return owner;
  }

  public void delete(Owner owner) {
    this.owners.remove(owner);
  }
}
