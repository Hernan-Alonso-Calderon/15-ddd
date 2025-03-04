package com.riskgameapp.shared.domain.generic;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
  private String name;
  private Instant when;
  private String uuid;
  private Long version;
  private String aggregateRootId;
  private String aggregateName;

  protected DomainEvent(String name) {
    this.name = name;
    this.when = Instant.now();
    this.uuid = UUID.randomUUID().toString();
    this.version = 1L;
  }

  public String getName() {
    return name;
  }

  public Instant getWhen() {
    return when;
  }

  public String getUuid() {
    return uuid;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getAggregateRootId() {
    return aggregateRootId;
  }

  public void setAggregateRootId(String aggregateRootId) {
    this.aggregateRootId = aggregateRootId;
  }

  public String getAggregateName() {
    return aggregateName;
  }

  public void setAggregateName(String aggregateName) {
    this.aggregateName = aggregateName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setWhen(Instant when) {
    this.when = when;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
