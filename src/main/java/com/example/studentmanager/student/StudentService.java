package com.example.studentmanager.student;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public Page<Student> listAll(int pageNum){
        Pageable pageable = PageRequest.of(pageNum - 1 , 5);
        return repo.findAll(pageable);
    }

    public void save(Student student){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(student.getPassword());
        student.setPassword(encoderPassword);
        repo.save(student);
    }

    public Student getById(Integer id) throws NotFoundStudentException {
        Optional<Student> byId = repo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw  new NotFoundStudentException("Could not found student with ID: " + id);
    }

    public void deleteById(Integer id) throws NotFoundStudentException {
        Long count = repo.countById(id);
        if(count == 0 || count == null){
            throw  new NotFoundStudentException("Could not found student with ID: " + id);
        }
        repo.deleteById(id);
    }


}
