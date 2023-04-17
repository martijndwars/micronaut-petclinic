package com.petclinic.entity;

import java.util.ArrayList;
import java.util.List;

public class Owner {

  private Integer id;
  private String firstName;
  private String lastName;
  private String address;
  private String city;
  private String telephone;
  private List<Pet> pets;

  public Owner() {
    this.pets = new ArrayList<>();
  }

  public Integer getId() {
    return id;
  }

  public Owner setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Owner setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Owner setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Owner setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getCity() {
    return city;
  }

  public Owner setCity(String city) {
    this.city = city;
    return this;
  }

  public String getTelephone() {
    return telephone;
  }

  public Owner setTelephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  public List<Pet> getPets() {
    return pets;
  }

  public Owner addPet(Pet pet) {
    if (!this.pets.contains(pet)) {
      this.pets.add(pet);
    }
    return this;
  }

  public Pet getPet(String name) {
    return getPet(name, false);
  }

  public Pet getPet(Integer id) {
    for (Pet pet : getPets()) {
      if (!pet.isNew()) {
        Integer compId = pet.getId();
        if (compId.equals(id)) {
          return pet;
        }
      }
    }
    return null;
  }

  public Pet getPet(String name, boolean ignoreNew) {
    name = name.toLowerCase();
    for (Pet pet : getPets()) {
      if (!ignoreNew || !pet.isNew()) {
        String compName = pet.getName();
        compName = compName == null ? "" : compName.toLowerCase();
        if (compName.equals(name)) {
          return pet;
        }
      }
    }
    return null;
  }

  public Owner addVisit(Integer petId, Visit visit) {
    Pet pet = getPet(petId);
    pet.addVisit(visit);
    return this;
  }

  @Override
  public String toString() {
    return "Owner{" +
      "address='" + address + '\'' +
      ", city='" + city + '\'' +
      ", telephone='" + telephone + '\'' +
      ", pets=" + pets +
      '}';
  }
}
