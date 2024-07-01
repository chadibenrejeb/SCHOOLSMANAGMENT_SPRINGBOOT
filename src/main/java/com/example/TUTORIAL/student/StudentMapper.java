package com.example.TUTORIAL.student;


import com.example.TUTORIAL.school.School;
import com.example.TUTORIAL.student.Student;
import com.example.TUTORIAL.student.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    // n convertiw fel studentDto ---> student
    public com.example.TUTORIAL.student.Student toStudent(com.example.TUTORIAL.student.StudentDto studentDto) {
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
    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
