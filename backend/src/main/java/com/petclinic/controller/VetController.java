package com.petclinic.controller;

import com.petclinic.entity.Vet;
import com.petclinic.exception.EntityNotFoundException;
import com.petclinic.repository.VetRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller(VetController.PETS)
public class VetController {

  public static final String PETS = "/vets";

  protected final VetRepository vetRepository;

  public VetController(VetRepository vetRepository) {
    this.vetRepository = vetRepository;
  }

  @Get
  public List<Vet> list() {
    return vetRepository.findAll();
  }

  @Get("/{id}")
  public Optional<Vet> show(Integer id) {
    return vetRepository.findById(id);
  }

  @Post
  public HttpResponse<Vet> save(Vet vet) {
    vetRepository.save(vet);
    return HttpResponse
      .created(vet)
      .headers(headers -> headers.location(location(vet.getId())));
  }

  @Delete("/{id}")
  public HttpResponse<?> delete(Integer id) {
    Optional<Vet> vetOpt = vetRepository.findById(id);
    if (vetOpt.isEmpty()) {
      throw new EntityNotFoundException("Vet with id " + id + " not found.");
    }
    vetRepository.delete(vetOpt.get());
    return HttpResponse.noContent();
  }

  protected URI location(Integer id) {
    return URI.create(PETS + "/" + id);
  }
}
