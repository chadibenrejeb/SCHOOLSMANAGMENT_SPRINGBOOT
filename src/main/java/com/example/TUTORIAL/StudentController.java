package com.example.TUTORIAL;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository repository;

    StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createStudent(@RequestBody StudentDto studentDto) {
        try {
            var student = toStudent(studentDto);
            var savedStudent =  repository.save(student);
            return toStudentResponseDto(savedStudent);
        } catch (Exception e) {
            // Handle specific exceptions or log the error for debugging
            throw new RuntimeException("Error creating student: " + e.getMessage());
        }
    }

    // n convertiw fel studentDto ---> student
    private Student toStudent(StudentDto studentDto) {
        var student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());

        var school = new School();
        school.setId(studentDto.schoolId());

        student.setSchool(school);

        return student;
    }



    // men student ---> studentresponseDTO , bech ki tssur request al student yetb3ath ken essm w lastname w mail w id lee
    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student getStudent(@PathVariable("student-id") int studentId) {
        return repository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentByName(@PathVariable("student-name") String studentName) {
        return repository.findByFirstName(studentName);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable("student-id") Integer studentId) {
        repository.deleteById(studentId);
    }

    @PutMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student updateStudent(@PathVariable("student-id") Integer studentId, @RequestBody Student updatedStudent) {
        return repository.findById(studentId).map(student -> {
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setAge(updatedStudent.getAge());
            student.setSchool(updatedStudent.getSchool());
            return repository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }
}
