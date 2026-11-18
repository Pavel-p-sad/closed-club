package com.example.closed_club.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 100, message = "Имя не длиннее 100 символов")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(max = 100, message = "Фамилия не длиннее 100 символов")
    @Column(nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "Должность не может быть пустой")
    @Size(max = 100, message = "Должность не длиннее 100 символов")
    @Column(nullable = false, length = 100)
    private String postName;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private List<QrCode> qrCodes;

    public Participant() {
    }
}
