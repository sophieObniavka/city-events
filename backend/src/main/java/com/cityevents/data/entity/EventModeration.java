
package com.cityevents.data.entity;

import com.cityevents.data.enums.ModerationAction;
import jakarta.persistence.*;

import java.time.Instant;

/**
 * Represents an entry in an event's moderation history.
 *
 * Each record stores a moderation action performed by a user
 * on a specific event, including the action type, optional
 * comment, and timestamp.
 *
 * Moderation records are immutable to preserve the history
 * of moderation decisions.
 */
@Entity
@Table(name = "event_moderation")
public class EventModeration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id", nullable = false)
    private User actor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ModerationAction action;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected EventModeration() {
    }

    public EventModeration(
            Event event,
            User actor,
            ModerationAction action,
            String comment
    ) {
        this.event = event;
        this.actor = actor;
        this.action = action;
        this.comment = comment;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public User getActor() {
        return actor;
    }

    public ModerationAction getAction() {
        return action;
    }

    public String getComment() {
        return comment;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
