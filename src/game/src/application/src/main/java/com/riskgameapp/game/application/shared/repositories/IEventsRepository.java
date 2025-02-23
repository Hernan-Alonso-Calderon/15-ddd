package com.riskgameapp.game.application.shared.repositories;


import com.riskgameapp.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  void save(DomainEvent domainEvent);
}
