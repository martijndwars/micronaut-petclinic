package com.petclinic.controller;

import com.petclinic.entity.Owner;
import com.petclinic.repository.OwnerRepository;
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
@Controller(OwnerController.OWNERS)
public class OwnerController {

  public static final String OWNERS = "/owners";

  protected final OwnerRepository ownerRepository;

  public OwnerController(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Get
  public List<Owner> list() {
    return ownerRepository.findAll();
  }

  @Get("/{id}")
  public Optional<Owner> show(Integer id) {
    return ownerRepository.findById(id);
  }

  @Post
  public HttpResponse<Owner> save(Owner owner) {
    ownerRepository.save(owner);
    return HttpResponse
      .created(owner)
      .headers(headers -> headers.location(location(owner.getId())));
  }

  @Delete("/{id}")
  public HttpResponse<?> delete(Integer id) {
    ownerRepository.delete(ownerRepository.findByIdOrThrow(id));
    return HttpResponse.noContent();
  }

  protected URI location(Integer id) {
    return URI.create(OWNERS + "/" + id);
  }
}
