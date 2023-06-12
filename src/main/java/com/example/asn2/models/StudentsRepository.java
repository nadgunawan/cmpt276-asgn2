package com.example.asn2.models;

import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentsRepository extends JpaRepository<Students,Integer> {
    
    List<Students> findByStudentName(String studentName);
    List<Students> findByEmailID(String emailID);
    List<Students> findByHeight(int height);
    List<Students> findByWeight(int weight);
    List<Students> findByGpa(String gpa);
    List<Students> findByHairColor(String hairColor);
    List<Students> findByMajor(String major);
    List<Students> findByNationality(String nationality);
    List<Students> findByGraduateYear(int graduateYear);

}



