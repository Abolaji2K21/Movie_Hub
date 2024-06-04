package com.mavericktube.MaverickHub.Models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeUpdated;

    @PrePersist
    public void setTimeCreated() {
        this.timeCreated = now();
    }

    @PreUpdate
    public void setTimeUpdated() {
        this.timeUpdated = now();
    }
}
