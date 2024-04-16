package com.parth.importer.repository;

import com.parth.importer.model.Log;
import com.parth.importer.model.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogRepository {
    boolean addLogs(@Param("student") StudentEntity studentEntity,@Param("statusCode") Long statusCode,@Param("statusMessage") String statusMessage);
    Log getAllLogs();
}
