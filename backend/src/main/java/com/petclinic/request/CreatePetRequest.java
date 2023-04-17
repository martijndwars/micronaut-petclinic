package com.petclinic.request;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Introspected
public class CreatePetRequest {
  @NotNull
  public Integer ownerId;
  @NotBlank
  public String name;
  @NotNull
  public LocalDate birthDate;
  @NotBlank
  public String type;
}
