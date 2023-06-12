package com.example.asn2.controller;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam; 


import com.example.asn2.models.Students;
import com.example.asn2.models.StudentsRepository;



@Controller
public class StudentsController {
    
    @Autowired
    private StudentsRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllUsers(Model model){
        System.out.println("Getting users");
        List<Students> student = studentRepo.findAll();
        model.addAttribute("stu", student);
        return "student/showAll";
    }

    @GetMapping("/students/view/{uid}")
    public String getStudent(Model model, @PathVariable String uid){
        System.out.println("Getting user "+ uid);
        int id = Integer.parseInt(uid); 
        Students s = studentRepo.findById(id).get(); 
        model.addAttribute("student"); 
        return "showUser";
    }

    @GetMapping("/students/display")
    public String displayAllUsers(Model model){
        System.out.println("Display users");
        List<Students> student = studentRepo.findAll();
        model.addAttribute("stu", student);
        return "student/displayData";
    }

    
    @GetMapping(value="/student/add")
    public String AddStudents() {
        
        Students newStudent = new Students("Nadine",57,159,"brown","3.5","Data Science", 2025, "Indonesian", "nga57@sfu.ca");
        studentRepo.save(newStudent);
        return "student/added";
    }
    @PostMapping("/students/add")
    public String addStudents(@RequestParam Map<String,String> newStudent, HttpServletResponse response) {
        System.out.println("Add User");
        String newName = newStudent.get("name"); 
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height")); 
        String newHair = newStudent.get("hairColor"); 
        String newGpa = newStudent.get("gpa");
        String newMajor = newStudent.get("major"); 
        int newYear = Integer.parseInt(newStudent.get("year"));
        String newNationality = newStudent.get("nationality"); 
        String newEmail = newStudent.get("emailID"); 
        studentRepo.save(new Students(newName, newWeight, newHeight,newHair,newGpa, newMajor,newYear,newNationality,newEmail));
        response.setStatus(201);
        return "redirect:/students/display";
        
    }

    @GetMapping("/students/adding")
        public String addingStudents(){
            return"student/add"; 
        }

    @GetMapping("/students/{uid}")
        public String updatingStudents(@PathVariable int uid, Model model){
        System.out.println("Editing users");
        Students editStudent = studentRepo.findById(uid).get();
        model.addAttribute("stu", editStudent);
        return"student/editData";
        }
        

    @PostMapping("/students/edit/{uid}")
    public String editStudents(@PathVariable int uid, @RequestParam Map<String,String> exStudent){
        Students editStudent = studentRepo.findById(uid).get(); 
        editStudent.setUid(uid);
        editStudent.setStudentName(exStudent.get("name"));
        editStudent.setWeight(Integer.parseInt(exStudent.get("weight")));
        editStudent.setHeight(Integer.parseInt(exStudent.get("height"))); 
        editStudent.setHairColor(exStudent.get("hairColor")); 
        editStudent.setGpa(exStudent.get("gpa")); 
        editStudent.setMajor(exStudent.get("major"));
        editStudent.setNationality(exStudent.get("nationality"));
        editStudent.setGraduateYear(Integer.parseInt(exStudent.get("year")));
        editStudent.setEmailID(exStudent.get("emailID"));
        studentRepo.save(editStudent); 
        return"redirect:/students/display";
    }


    @GetMapping("/students/delete/{uid}")
        public String deleteStudents(@PathVariable int uid){
        System.out.println("Deleting users");
        Students delStudent = studentRepo.findById(uid).get();
        studentRepo.delete(delStudent);
        return"redirect:/students/display";
        }


}
    



