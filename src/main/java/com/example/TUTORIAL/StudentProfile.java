package com.example.TUTORIAL;
import jakarta.persistence.*;


@Entity
public class StudentProfile {

    @Id
    @GeneratedValue
    private Integer id ;
    private String bio ;


    @OneToOne // second entity
    @JoinColumn(
            name = "student_id"
    )
    private Student student ;  // kima student ali fel mappedBy lezem nektbohom zouz kif kif

    public StudentProfile(){

    }

    StudentProfile(String bio){
        this.bio = bio;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
