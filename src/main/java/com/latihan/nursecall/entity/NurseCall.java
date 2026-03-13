package com.latihan.nursecall.entity;

import com.latihan.nursecall.enums.CallStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "nurse_calls")
@Getter
@Setter
public class NurseCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private String patientName;

    private String nurseName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CallStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;

}