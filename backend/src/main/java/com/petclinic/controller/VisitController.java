package com.petclinic.controller;

import com.petclinic.entity.Visit;
import com.petclinic.repository.PetRepository;
import com.petclinic.repository.VisitRepository;
import com.petclinic.request.CreateVisitRequest;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller(VisitController.VISITS)
@Validated
public class VisitController {

  public static final String VISITS = "/visits";

  private final VisitRepository visitRepository;
  private final PetRepository petRepository;

  public VisitController(VisitRepository visitRepository, PetRepository petRepository) {
    this.visitRepository = visitRepository;
    this.petRepository = petRepository;
  }

  @Get
  public List<Visit> list(
    @Nullable @QueryValue Integer petId
  ) {
    if (petId != null) {
      return visitRepository.findAllByPetId(petId);
    }
    return visitRepository.findAll();
  }

  @Post
  public HttpResponse<Visit> save(@Valid CreateVisitRequest createVisitRequest) {
    Visit visit = new Visit()
      .setPet(petRepository.findByIdOrThrow(createVisitRequest.petId))
      .setDate(createVisitRequest.date)
      .setDescription(createVisitRequest.description);
    visitRepository.save(visit);
    return HttpResponse
      .created(visit)
      .headers(headers -> headers.location(location(visit.getId())));
  }

  protected URI location(Integer id) {
    return URI.create(VISITS + "/" + id);
  }
}
