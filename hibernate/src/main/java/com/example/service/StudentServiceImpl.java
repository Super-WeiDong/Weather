package com.example.service;


import com.example.entity.Student;
import com.example.exception.StudentNotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> s = repository.findById(id);
        Student theS=  null;
        if (s.isPresent()){
            theS = s.get();
        }else{
            throw new StudentNotFoundException("student with id "+ id + " not found");
        }
        return theS;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return repository.save(student);
    }


    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Optional<Student> s = repository.findById(id);
        Student theS=  null;
        if (s.isPresent()){
            repository.deleteById(id);
        }else{
            throw new StudentNotFoundException("student with id "+ id + " not found");
        }
    }

    @Override
    @Transactional
    public List<Integer> findTeacherId(int studentId) {
        return repository.findTeacherId(studentId);
    }
}
