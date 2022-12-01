package com.example.studentmanager.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public String showStudentList(Model model){
        return showPage(model, 1);
    }

    @GetMapping("/students/{pageNum}")
    public String showPage(Model model , @PathVariable("pageNum") int currentPage){
        Page<Student> page = service.listAll(currentPage);
        Long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Student> students = page.getContent();
        model.addAttribute("currentPage" , currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("students",students);
        return "students";
    }

    @GetMapping("/students/new")
    public String showStudentForm(Model model){
        model.addAttribute("students",new Student());
        return "students_form";
    }

    @PostMapping("/students/save")
    public String savedStudent(Student student){
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable("id") Integer id , Model model){
        try {
            Student students = service.getById(id);
            model.addAttribute("students",students);
            model.addAttribute("pageTitle" , "Edit student by ID: " + id);
            return "students_form";
        } catch (NotFoundStudentException e) {
            return "redirect:/students";
        }
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        try {
            service.deleteById(id);
        } catch (NotFoundStudentException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/students";
    }

    @GetMapping("/403")
    public String errorPage(){
        return "403";
    }


}
