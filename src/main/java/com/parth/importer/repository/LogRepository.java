package com.parth.importer.repository;

import com.parth.importer.model.LogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogRepository {
    void addLog(LogEntity logEntity);
    LogEntity getAllLogs();
}
