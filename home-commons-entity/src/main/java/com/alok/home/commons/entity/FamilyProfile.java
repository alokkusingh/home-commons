package com.alok.home.commons.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "entities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "events")
public class FamilyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = false)
    private String email;

    @Column(nullable = false, unique = false)
    private String mobile;

    @ManyToMany(mappedBy = "participants")
    private Set<LifeEvent> events = new HashSet<>();

    // MANUALLY OVERRIDE TO FORCE JUST FIELD CHECKS (Safe from Hibernate proxies)
    @Override
    public boolean equals(Object someObject) {
        if (this == someObject) return true;
        if (someObject == null || getClass() != someObject.getClass()) return false;
        FamilyProfile that = (FamilyProfile) someObject;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name); // Zero references to 'events' collection
    }
}
