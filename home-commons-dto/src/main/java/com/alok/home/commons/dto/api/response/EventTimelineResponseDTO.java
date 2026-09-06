package com.alok.home.commons.dto.api.response;

import com.alok.home.commons.entity.FamilyProfile;
import com.alok.home.commons.entity.LifeEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class EventTimelineResponseDTO {
    private Long eventId;
    private LocalDate eventDate;
    private LocalDate endDate;
    private String eventType;
    private String notes;
    private String externalId;
    private List<String> participants;
    private String eventAge;
    private String duration;

    public EventTimelineResponseDTO(LifeEvent event) {
        this.eventId = event.getId();
        this.eventDate = event.getEventDate();
        this.endDate = event.getEndDate();
        this.eventType = event.getEventType();
        this.notes = event.getNotes();
        this.externalId = event.getExternalId();

        // Map participating people profiles into a list of name strings
        this.participants = event.getParticipants().stream()
                .map(FamilyProfile::getName)
                .collect(Collectors.toList());

        // Dynamic Event Age calculation (relative to today)
        Period agePeriod = Period.between(event.getEventDate(), LocalDate.now());
        this.eventAge = String.format("Y:%d M:%d D:%d", agePeriod.getYears(), agePeriod.getMonths(), agePeriod.getDays());

        // Dynamic Duration calculation
        if (event.getEndDate() != null) {
            Period dur = Period.between(event.getEventDate(), event.getEndDate());
            this.duration = String.format("Y:%d M:%d D:%d", dur.getYears(), dur.getMonths(), dur.getDays());
        }
    }

    // Getters
    public Long getEventId() { return eventId; }
    public LocalDate getEventDate() { return eventDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getEventType() { return eventType; }
    public String getNotes() { return notes; }
    public String getExternalId() { return externalId; }
    public List<String> getParticipants() { return participants; }
    public String getEventAge() { return eventAge; }
    public String getDuration() { return duration; }
}

