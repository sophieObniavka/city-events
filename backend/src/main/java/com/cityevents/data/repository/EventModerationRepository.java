package com.cityevents.data.repository;

import com.cityevents.data.entity.EventModeration;
import com.cityevents.data.enums.ModerationAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventModerationRepository extends JpaRepository<EventModeration, Long> {

    List<EventModeration> findAllByEventId(Long eventId);

    List<EventModeration> findAllByActorId(Long actorId);

    List<EventModeration> findAllByAction(ModerationAction action);
}
