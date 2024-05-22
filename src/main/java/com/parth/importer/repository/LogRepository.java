package com.parth.importer.repository;

import com.parth.importer.model.LogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogRepository {
    void addLog(LogEntity logEntity);
    LogEntity getAllLogs();
    void updateLog(@Param("logId") Long id,@Param("statusCode") Long statusCode,@Param("statusMessage") String statusMessage);
}
