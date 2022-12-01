package com.example.studentmanager.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student , Integer> {


    public Long countById(Integer id);

    @Query("select s from Student  s where s.email = ?1")
    public Student getStudentByEmail(String email);

}
