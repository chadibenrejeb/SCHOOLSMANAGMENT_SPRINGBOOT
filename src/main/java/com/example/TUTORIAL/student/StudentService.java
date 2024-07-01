package com.example.TUTORIAL.student;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);
        var savedStudent =  repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto getStudentById(@PathVariable("student-id") int studentId) {
        return repository.findById(studentId)
                        .map(studentMapper::toStudentResponseDto)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String studentName) {
        return repository.findByFirstName(studentName)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(@PathVariable("student-id") Integer studentId) {
        repository.deleteById(studentId);
    }


    public StudentResponseDto updateStudent(@PathVariable("student-id") Integer studentId, @RequestBody Student updatedStudent) {
        return repository.findById(studentId).map(student -> {
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setAge(updatedStudent.getAge());
            student.setSchool(updatedStudent.getSchool());
            var savedStudent = repository.save(student);
            return studentMapper.toStudentResponseDto(savedStudent);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }

}
