package com.alok.home.commons.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "life_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "participants")
public class LifeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "external_id")
    private String externalId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "entity_id")
    )
    @Builder.Default
    private Set<FamilyProfile> participants = new HashSet<>();

    public void addParticipant(FamilyProfile profile) {
        if (profile != null) {
            if (this.participants == null) {
                this.participants = new HashSet<>();
            }
            this.participants.add(profile);
        }
    }

    // MANUALLY OVERRIDE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LifeEvent lifeEvent = (LifeEvent) o;
        return Objects.equals(id, lifeEvent.id) &&
                Objects.equals(eventDate, lifeEvent.eventDate) &&
                Objects.equals(eventType, lifeEvent.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventDate, eventType); // Zero references to 'participants' collection
    }
}