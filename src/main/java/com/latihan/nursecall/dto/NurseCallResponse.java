package com.latihan.nursecall.dto;

import com.latihan.nursecall.enums.CallStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NurseCallResponse {

    private Long id;

    private String roomNumber;

    private String patientName;

    private String nurseName;

    private CallStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;

}