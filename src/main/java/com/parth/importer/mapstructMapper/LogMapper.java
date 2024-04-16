package com.parth.importer.mapstructMapper;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.model.Log;
import com.parth.importer.model.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "studentId", target = "studentId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "statusCode", target = "statusCode")
    @Mapping(source = "statusMessage", target = "statusMessage")
    LogDisplayDto convertLogEntityToLogDisplayDto(Log log);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studentEntity.id", target = "studentId")
    @Mapping(source = "studentEntity.name", target = "name")
    @Mapping(source = "studentEntity.age", target = "age")
    @Mapping(source = "studentEntity.email", target = "email")
    @Mapping(source = "studentEntity.city", target = "city")
    @Mapping(source = "statusCode", target = "statusCode")
    @Mapping(source = "statusMessage", target = "statusMessage")
    Log convertDataToLogEntity(StudentEntity studentEntity, Long statusCode, String statusMessage);
}
