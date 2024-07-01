package com.example.TUTORIAL.student;

import com.example.TUTORIAL.student.StudentDto;
import com.example.TUTORIAL.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto saveStudent(@RequestBody StudentDto studentDto) {
        try {
            return this.studentService.saveStudent(studentDto);
        } catch (Exception e) {
            // Handle specific exceptions or log the error for debugging
            throw new RuntimeException("Error creating student: " + e.getMessage());
        }
    }



    @GetMapping("/students")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }




    @GetMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentResponseDto getStudentById(@PathVariable("student-id") int studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String studentName) {
        return studentService.findStudentByName(studentName);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable("student-id") Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentResponseDto updateStudent(@PathVariable("student-id") Integer studentId, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(studentId , updatedStudent);
    }
}
