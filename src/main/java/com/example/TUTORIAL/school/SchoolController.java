package com.example.TUTORIAL.school;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto schooldto
    ){
        return schoolService.create(schooldto);
    }



    @GetMapping("/schools")
    public List<SchoolDto> getAll() {
        return schoolService.getAll();
    }

}
