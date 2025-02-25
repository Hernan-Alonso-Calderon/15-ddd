package com.riskgameapp.game.infra.mongo.repositories;

import com.riskgameapp.game.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
