package com.petclinic.repository;

public class BaseRepository {

  private Integer autoIncrement;

  public BaseRepository() {
    this.autoIncrement = 1;
  }

  protected synchronized Integer getNextId() {
    return autoIncrement++;
  }
}