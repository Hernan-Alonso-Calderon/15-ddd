package com.riskgameapp.shared.domain.generic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class DomainActionsHandler {
  private final List<DomainEvent> events = new LinkedList<>();
  private final Map<String, AtomicLong> versions = new ConcurrentHashMap<>();
  private final Set<Consumer<? super DomainEvent>> actions= new HashSet<>();

  public List<DomainEvent> getEvents() {
    return events;
  }

  public void subscribe(final DomainActionsContainer actionsContainer) {
    actions.addAll(actionsContainer.actions);
  }

  public void apply(final DomainEvent event) {
    events.add(event);
    actions.forEach(action -> handle(event, action));
  }

  private void handle(final DomainEvent event, final Consumer<? super DomainEvent> action) {
    try {
      action.accept(event);
      final Long newVersion = increaseVersion(event);
      event.setVersion(newVersion);
    }
    catch (ClassCastException ignored) {}
  }

  private Long increaseVersion(final DomainEvent event) {
    final AtomicLong version = versions.get(event.getName());
    final Long newVersion = version == null ? 1L : version.incrementAndGet();
    versions.put(event.getName(), new AtomicLong(newVersion));
    return newVersion;
  }

}
