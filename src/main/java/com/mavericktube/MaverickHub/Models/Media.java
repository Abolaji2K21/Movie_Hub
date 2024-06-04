package com.mavericktube.MaverickHub.Models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;


@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String url;
    private String description;
    @Enumerated(value = STRING)
    private Category category;
    @Setter(AccessLevel.NONE)
    private LocalDateTime time_created;

    @ManyToOne
    private User uploader;



    @PrePersist
    public void setTime_created() {
        this.time_created = now();
    }




}
