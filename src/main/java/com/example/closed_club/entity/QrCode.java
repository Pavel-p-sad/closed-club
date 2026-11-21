package com.example.closed_club.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QrCode {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private LocalDateTime dateTime;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public QrCode(UUID uuid, LocalDateTime dateTime, Participant participant) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.participant = participant;
    }
    public QrCode() {
    }
}
