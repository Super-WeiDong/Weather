package com.example.service;



import com.example.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(int id);

    public Student save(Student student);

    void deleteStudentById(int id);

    List<Integer> findTeacherId(int studentId);
}
