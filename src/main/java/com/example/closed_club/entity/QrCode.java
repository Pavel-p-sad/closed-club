package com.example.closed_club.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private LocalDateTime dateTime;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public UUID getUuid() {
        return uuid;
    }
    public Participant getParticipant() {
        return participant;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public Long getId() {
        return id;
    }
    public QrCode() {
    }
}


