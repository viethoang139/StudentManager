package com.example.studentmanager.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = repo.getStudentByEmail(email);
        if(student == null){
            throw  new UsernameNotFoundException("Could not found student");
        }
        return new CustomUserDetails(student);
    }
}
