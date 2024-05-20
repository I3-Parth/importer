package com.parth.importer.services;

import com.parth.importer.dto.AcknowledgmentDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class StudentAcknowledgmentConsumerService {

    @KafkaListener(topics = "student-ack", groupId = "ack-group", containerFactory = "ackKafkaListenerContainerFactory")
    public void consumeAcknowledgment(AcknowledgmentDTO acknowledgmentDTO){
        if (acknowledgmentDTO.getMessage().equals("SUCCESS")){
            System.out.println("Student with name: "+acknowledgmentDTO.getStudentName()+" has successfully added");
        }
        else {
            System.out.println("Student with name: "+acknowledgmentDTO.getStudentName()+" has failed due to "+acknowledgmentDTO.getMessage());
        }
    }
}
