package com.riskgameapp.game.infra.mongo.adapters;

import com.riskgameapp.game.application.shared.ports.IEventsRepositoryPort;
import com.riskgameapp.game.infra.mongo.entities.Event;
import com.riskgameapp.game.infra.mongo.repositories.IEventsRepository;
import com.riskgameapp.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public class MongoAdapter implements IEventsRepositoryPort {
  private final IEventsRepository eventsRepository;

  public MongoAdapter(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Flux<DomainEvent> findEventsByAggregateId(String aggregateId) {
    return eventsRepository
      .findAll()
      .map(Event::getDomainEvent)
      .filter(event -> event.getAggregateRootId().equals(aggregateId));
  }

  @Override
  public void save(DomainEvent domainEvent) {
    eventsRepository.save(new Event(domainEvent)).subscribe();
  }
}
