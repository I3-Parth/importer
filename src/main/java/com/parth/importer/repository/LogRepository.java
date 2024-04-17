package com.parth.importer.repository;

import com.parth.importer.model.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogRepository {
    void addLogs(Log log);
    Log getAllLogs();
}
