package com.example.closed_club.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Participant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String postName;
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private List<QrCode> qrCodes;

    public Participant() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostName() {
        return postName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setQrCodes(List<QrCode> qrCodes) {
        this.qrCodes = qrCodes;
    }

    public List<QrCode> getQrCodes() {
        return qrCodes;
    }
}
