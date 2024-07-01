package com.example.TUTORIAL.school;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public SchoolDto create(SchoolDto schoolDto) {
        School school = schoolMapper.toSchool(schoolDto);
        School savedSchool = schoolRepository.save(school);
        return schoolMapper.toSchoolDto(savedSchool);
    }

    public List<SchoolDto> getAll() {
        return this.schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
