package com.parth.importer.repository;

import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.model.Log;

public interface logRepository {
    boolean addLogs(StudentAdditionDto studentAdditionDto, Long statusCode, String statusMessage);
    Log getAllLogs();
}
