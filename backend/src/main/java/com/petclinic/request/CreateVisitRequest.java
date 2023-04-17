package com.petclinic.request;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Introspected
public class CreateVisitRequest {

  public Integer petId;
  @NotNull
  public LocalDate date;
  @NotBlank
  public String description;
}