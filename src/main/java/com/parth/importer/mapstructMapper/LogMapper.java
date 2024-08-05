package com.parth.importer.mapstructMapper;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.model.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public interface LogMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "statusCode", target = "statusCode")
    @Mapping(source = "statusMessage", target = "statusMessage")
    LogDisplayDto convertLogEntityToLogDisplayDto(LogEntity logEntity);

    List<LogDisplayDto> convertListOfLogEntitiesToListOfLogDisplayDtos(List<LogEntity> logEntities);

    @Mapping(source = "studentAdditionDto.name", target = "name")
    @Mapping(source = "studentAdditionDto.age", target = "age")
    @Mapping(source = "studentAdditionDto.email", target = "email")
    @Mapping(source = "studentAdditionDto.city", target = "city")
    @Mapping(source = "statusCode", target = "statusCode")
    @Mapping(source = "statusMessage", target = "statusMessage")
    LogEntity convertDataToLogEntity(StudentAdditionDto studentAdditionDto, Long statusCode, String statusMessage);

}
