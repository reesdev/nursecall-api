package com.latihan.nursecall.controller;

import com.latihan.nursecall.dto.NurseCallRequest;
import com.latihan.nursecall.dto.NurseCallResponse;
import com.latihan.nursecall.enums.CallStatus;
import com.latihan.nursecall.service.NurseCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calls")
public class NurseCallController {

    private final NurseCallService service;

    public NurseCallController(NurseCallService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NurseCallResponse> createCall(@RequestBody NurseCallRequest request){
        return ResponseEntity.ok(service.createCall(request));
    }

    @GetMapping
    public ResponseEntity<List<NurseCallResponse>> getAllCalls(){
        return ResponseEntity.ok(service.getAllCalls());
    }

    @PutMapping("/{id}/acknowledge")
    public ResponseEntity<NurseCallResponse> acknowledgeCall(
            @PathVariable Long id,
            @RequestParam String nurseName){

        return ResponseEntity.ok(service.acknowledgeCall(id, nurseName));
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<NurseCallResponse> startHandling(@PathVariable Long id){
        return ResponseEntity.ok(service.startHandling(id));
    }

    @PutMapping("/{id}/escalate")
    public ResponseEntity<NurseCallResponse> escalateCall(@PathVariable Long id){
        return ResponseEntity.ok(service.escalateCall(id));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<NurseCallResponse> resolveCall(@PathVariable Long id){
        return ResponseEntity.ok(service.resolveCall(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NurseCallResponse>> getCallsByStatus(@PathVariable CallStatus status){
        return ResponseEntity.ok(service.getCallsByStatus(status));
    }

}