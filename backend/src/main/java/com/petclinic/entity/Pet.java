package com.petclinic.entity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Pet {

  private Integer id;
  private Owner owner;
  private String name;
  private LocalDate birthDate;
  private PetType type;
  private Set<Visit> visits;

  public Pet() {
    this.visits = new LinkedHashSet<>();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Owner getOwner() {
    return owner;
  }

  public Pet setOwner(Owner owner) {
    this.owner = owner;
    return this;
  }

  public String getName() {
    return name;
  }

  public Pet setName(String name) {
    this.name = name;
    return this;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public Pet setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public PetType getType() {
    return type;
  }

  public Pet setType(PetType type) {
    this.type = type;
    return this;
  }

  public Set<Visit> getVisits() {
    return visits;
  }

  public Pet addVisit(Visit visit) {
    if (!this.visits.contains(visit)) {
      this.visits.add(visit);
      visit.setPet(this);
    }
    return this;
  }

  public boolean isNew() {
    return id == null;
  }
}
