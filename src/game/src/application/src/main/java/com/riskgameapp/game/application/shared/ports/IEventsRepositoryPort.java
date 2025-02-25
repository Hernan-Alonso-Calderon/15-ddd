package com.riskgameapp.game.application.shared.ports;


import com.riskgameapp.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepositoryPort {
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  void save(DomainEvent domainEvent);
}
