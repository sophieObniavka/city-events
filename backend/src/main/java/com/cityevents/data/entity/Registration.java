
package com.cityevents.data.entity;

import com.cityevents.data.enums.RegistrationStatus;
import jakarta.persistence.*;

import java.time.Instant;

/**
 * Represents a user's registration for an event.
 *
 * Each registration links one user to one event and tracks
 * the registration status and timestamps.
 *
 * A user can cancel a registration and register for the same
 * event again later.
 */
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RegistrationStatus status = RegistrationStatus.CONFIRMED;

    @Column(name = "registered_at", nullable = false, updatable = false)
    private Instant registeredAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Registration() {
    }

    public Registration(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        registeredAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void cancel() {
        this.status = RegistrationStatus.CANCELLED;
    }

    public void confirm() {
        this.status = RegistrationStatus.CONFIRMED;
    }
}
