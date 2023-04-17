package com.petclinic.entity;

import java.util.HashSet;
import java.util.Set;

public class Vet {

  private Integer id;
  private String name;
  private Set<Specialty> specialties;

  public Vet() {
    this.specialties = new HashSet<>();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Specialty> getSpecialties() {
    return specialties;
  }

  public void addSpecialty(Specialty specialty) {
    specialties.add(specialty);
  }

  public int getNrOfSpecialties() {
    return specialties.size();
  }
}
