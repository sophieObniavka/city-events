
package com.cityevents.data.entity;

import jakarta.persistence.*;

import java.time.Instant;

/**
 * Represents an image associated with an event.
 *
 * An event can have multiple images, but only one image
 * can be marked as the primary image.
 *
 * The image file is stored externally, while this entity
 * stores its URL and metadata.
 */
@Entity
@Table(name = "event_images")
public class EventImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "is_primary", nullable = false)
    private boolean primary = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected EventImage() {
    }

    public EventImage(Event event, String imageUrl) {
        this.event = event;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isPrimary() {
        return primary;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void markAsPrimary() {
        this.primary = true;
    }

    public void removeAsPrimary() {
        this.primary = false;
    }
}
