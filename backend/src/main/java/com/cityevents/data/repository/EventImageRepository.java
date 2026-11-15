package com.cityevents.data.repository;

import com.cityevents.data.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EventImageRepository extends JpaRepository<EventImage, Long> {

    List<EventImage> findAllByEventId(Long eventId);

    Optional<EventImage> findByEventIdAndPrimaryTrue(Long eventId);

    void deleteAllByEventId(Long eventId);
}
