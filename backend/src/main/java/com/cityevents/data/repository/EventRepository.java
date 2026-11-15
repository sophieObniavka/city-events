package com.cityevents.data.repository;

import com.cityevents.data.entity.Event;
import com.cityevents.data.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByOrganizerId(Long organizerId);

    List<Event> findAllByCategoryId(Long categoryId);

    List<Event> findAllByStatus(EventStatus status);
}
