package com.latihan.nursecall.repository;

import com.latihan.nursecall.entity.NurseCall;
import com.latihan.nursecall.enums.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NurseCallRepository extends JpaRepository<NurseCall, Long> {

    List<NurseCall> findByStatus(CallStatus status);

}