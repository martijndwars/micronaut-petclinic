package com.petclinic.repository;

import com.petclinic.entity.Visit;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class VisitRepository extends BaseRepository {

  private final List<Visit> visits;

  public VisitRepository() {
    super();
    this.visits = new ArrayList<>();
  }

  public Optional<Visit> findById(Integer id) {
    for (Visit visit : visits) {
      if (visit.getId().equals(id)) {
        return Optional.of(visit);
      }
    }
    return Optional.empty();
  }

  public List<Visit> findAll() {
    return new ArrayList<>(visits);
  }

  public List<Visit> findAllByPetId(Integer petId) {
    return visits
      .stream()
      .filter(visit -> visit.getPet().getId().equals(petId))
      .collect(Collectors.toList());
  }

  public Visit save(Visit visit) {
    if (visit.getId() == null) {
      visit.setId(getNextId());
    }
    visits.add(visit);
    return visit;
  }
}
