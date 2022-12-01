package com.example.studentmanager;

import com.example.studentmanager.student.Student;
import com.example.studentmanager.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repo;

    @Test
    public void testAddNew(){
        Student student = new Student();
        student.setEmail("khai2802@gmail.com");
        student.setPassword("2802");
        student.setFirstName("Li Lam");
        student.setLastName("Khai");
        student.setAddress("Tuyen Quang");
        student.setGpa(2.5F);
        Student savedStudent = repo.save(student);
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
        Assertions.assertThat(savedStudent).isNotNull();
    }

    @Test
    public void testListAll(){
        List<Student> listAll = repo.findAll();
        Assertions.assertThat(listAll).hasSizeGreaterThan(0);
        for(Student student : listAll){
            System.out.println(student);
        }
    }

    @Test
    public void testUpdate(){
        Integer id = 1;
        Optional<Student> byId = repo.findById(id);
        Student student = byId.get();
        student.setPassword("hoang123");
        repo.save(student);

        Student savedStudent = repo.findById(id).get();
        Assertions.assertThat(savedStudent.getPassword()).isEqualTo("hoang123");
    }

    @Test
    public void testGet(){
        Integer id = 2;
        Optional<Student> byId = repo.findById(id);
        Assertions.assertThat(byId).isPresent();
        Student student = byId.get();
        System.out.println(student);
    }

    @Test
    public void testDelete(){
        Integer id = 2;
        repo.deleteById(id);
        Optional<Student> byId = repo.findById(id);
        Assertions.assertThat(byId).isNotPresent();

    }


}
