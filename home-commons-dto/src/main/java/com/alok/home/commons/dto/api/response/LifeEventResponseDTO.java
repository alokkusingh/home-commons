package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.entity.FamilyProfile;
import com.alok.home.commons.entity.LifeEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class LifeEventResponseDTO {
    private Long entityId;
    private String name;
    private List<EventDTO> events;

    public LifeEventResponseDTO(FamilyProfile profile) {
        this.entityId = profile.getId();
        this.name = profile.getName();
        this.events = profile.getEvents().stream()
                .sorted((e1, e2) -> e2.getEventDate().compareTo(e1.getEventDate())) // Newest first
                .map(EventDTO::new)
                .collect(Collectors.toList());
    }

    // Getters
    public Long getEntityId() { return entityId; }
    public String getName() { return name; }
    public List<EventDTO> getEvents() { return events; }

    public static class EventDTO {
        private Long id;
        private LocalDate eventDate;
        private LocalDate endDate;
        private String eventType;
        private String notes;
        private String externalId;
        private String eventAge;
        private String duration;

        public EventDTO(LifeEvent event) {
            this.id = event.getId();
            this.eventDate = event.getEventDate();
            this.endDate = event.getEndDate();
            this.eventType = event.getEventType();
            this.notes = event.getNotes();
            this.externalId = event.getExternalId();

            // Dynamic Age Calculation relative to current date
            Period agePeriod = Period.between(event.getEventDate(), LocalDate.now());
            this.eventAge = String.format("Y:%d M:%d D:%d", agePeriod.getYears(), agePeriod.getMonths(), agePeriod.getDays());

            // Dynamic Duration Calculation
            if (event.getEndDate() != null) {
                Period dur = Period.between(event.getEventDate(), event.getEndDate());
                this.duration = String.format("Y:%d M:%d D:%d", dur.getYears(), dur.getMonths(), dur.getDays());
            }
        }

        // Getters
        public Long getId() { return id; }
        public LocalDate getEventDate() { return eventDate; }
        public LocalDate getEndDate() { return endDate; }
        public String getEventType() { return eventType; }
        public String getNotes() { return notes; }
        public String getExternalId() { return externalId; }
        public String getEventAge() { return eventAge; }
        public String getDuration() { return duration; }
    }
}