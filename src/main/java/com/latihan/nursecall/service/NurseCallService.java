package com.latihan.nursecall.service;

import com.latihan.nursecall.dto.NurseCallRequest;
import com.latihan.nursecall.dto.NurseCallResponse;
import com.latihan.nursecall.entity.NurseCall;
import com.latihan.nursecall.enums.CallStatus;
import com.latihan.nursecall.exception.CallNotFoundException;
import com.latihan.nursecall.exception.InvalidCallStateException;
import com.latihan.nursecall.repository.NurseCallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NurseCallService {

    private final NurseCallRepository repo;

    public NurseCallService(NurseCallRepository repo) {
        this.repo = repo;
    }

    private NurseCall findCall(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new CallNotFoundException("Call not found"));
    }

    private NurseCallResponse mapToResponse(NurseCall call){

        NurseCallResponse res = new NurseCallResponse();

        res.setId(call.getId());
        res.setRoomNumber(call.getRoomNumber());
        res.setPatientName(call.getPatientName());
        res.setNurseName(call.getNurseName());
        res.setStatus(call.getStatus());
        res.setCreatedAt(call.getCreatedAt());
        res.setResolvedAt(call.getResolvedAt());

        return res;
    }

    public NurseCallResponse createCall(NurseCallRequest request){

        NurseCall call = new NurseCall();

        call.setRoomNumber(request.getRoomNumber());
        call.setPatientName(request.getPatientName());
        call.setStatus(CallStatus.CALLING);
        call.setCreatedAt(LocalDateTime.now());

        repo.save(call);

        return mapToResponse(call);
    }

    public List<NurseCallResponse> getAllCalls(){

        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NurseCallResponse acknowledgeCall(Long id, String nurseName){

        NurseCall call = findCall(id);

        if(call.getStatus() != CallStatus.CALLING){
            throw new InvalidCallStateException("Call already acknowledged");
        }

        call.setStatus(CallStatus.ACKNOWLEDGED);
        call.setNurseName(nurseName);

        repo.save(call);

        return mapToResponse(call);
    }

    public NurseCallResponse startHandling(Long id){

        NurseCall call = findCall(id);

        if(call.getStatus() != CallStatus.ACKNOWLEDGED){
            throw new InvalidCallStateException("Call must be acknowledged first");
        }

        call.setStatus(CallStatus.IN_PROGRESS);

        repo.save(call);

        return mapToResponse(call);
    }

    public NurseCallResponse escalateCall(Long id){

        NurseCall call = findCall(id);

        if(call.getStatus() != CallStatus.IN_PROGRESS){
            throw new InvalidCallStateException("Call must be in progress before escalation");
        }

        call.setStatus(CallStatus.ESCALATED);

        repo.save(call);

        return mapToResponse(call);
    }

    public NurseCallResponse resolveCall(Long id){

        NurseCall call = findCall(id);

        if(call.getStatus() != CallStatus.IN_PROGRESS &&
                call.getStatus() != CallStatus.ESCALATED){
            throw new InvalidCallStateException("Call cannot be resolved yet");
        }

        call.setStatus(CallStatus.RESOLVED);
        call.setResolvedAt(LocalDateTime.now());

        repo.save(call);

        return mapToResponse(call);
    }

    public List<NurseCallResponse> getCallsByStatus(CallStatus status){

        return repo.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

}