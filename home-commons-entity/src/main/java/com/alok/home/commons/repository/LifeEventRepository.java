package com.alok.home.commons.repository;

import com.alok.home.commons.entity.LifeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LifeEventRepository extends JpaRepository<LifeEvent, Long> {
    // Eagerly fetch participants to avoid N+1 issues when returning the API response
    @Query("SELECT l FROM LifeEvent l LEFT JOIN FETCH l.participants ORDER BY l.eventDate DESC")
    List<LifeEvent> findAllWithParticipants();

    // Eagerly fetches all events along with their participant profiles, ordered chronologically (newest first)
    @Query("SELECT DISTINCT l FROM LifeEvent l LEFT JOIN FETCH l.participants ORDER BY l.eventDate DESC")
    List<LifeEvent> findAllEventsWithParticipantsEagerly();
}
