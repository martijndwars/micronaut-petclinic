package com.petclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Visit {

  private Integer id;
  @JsonIgnore
  private Pet pet;
  private LocalDate date;
  private String description;

  public Visit() {
    this.date = LocalDate.now();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Pet getPet() {
    return pet;
  }

  public Visit setPet(Pet pet) {
    pet.addVisit(this);
    this.pet = pet;
    return this;
  }

  public LocalDate getDate() {
    return date;
  }

  public Visit setDate(LocalDate date) {
    this.date = date;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Visit setDescription(String description) {
    this.description = description;
    return this;
  }
}
