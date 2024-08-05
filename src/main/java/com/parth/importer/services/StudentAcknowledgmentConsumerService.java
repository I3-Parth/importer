package com.parth.importer.services;

import com.parth.importer.dto.AcknowledgmentDTO;
import com.parth.importer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class StudentAcknowledgmentConsumerService {

    @Autowired
    LogRepository logRepository;

    @KafkaListener(topics = "student-ack-topic", groupId = "ack-group", containerFactory = "ackKafkaListenerContainerFactory")
    public void consumeAcknowledgment(AcknowledgmentDTO acknowledgmentDTO){
        if (acknowledgmentDTO.getStatusMessage().equals(HttpStatus.OK.getReasonPhrase())){
            logRepository.updateLog(acknowledgmentDTO.getLogId(), acknowledgmentDTO.getStatusCode(), acknowledgmentDTO.getStatusMessage());
            System.out.println("Student with log Id: " + acknowledgmentDTO.getLogId() + " has successfully added");
        }
        else {
            logRepository.updateLog(acknowledgmentDTO.getLogId(), acknowledgmentDTO.getStatusCode(), acknowledgmentDTO.getStatusMessage());
            System.out.println("Student with log Id: " + acknowledgmentDTO.getLogId()+" has failed due to " + acknowledgmentDTO.getStatusCode() + " " + acknowledgmentDTO.getStatusMessage());
        }
    }
}
