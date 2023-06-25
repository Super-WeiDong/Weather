package com.example.controller;



import com.example.dto.StudentDTO;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final Logger logger;
    @Autowired
    public StudentController(StudentService studentService,Logger logger) {
        this.studentService = studentService;
        this.logger = logger;
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        logger.debug("Get all students");
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentsById(@PathVariable int studentId){
        Student stu = studentService.getStudentById(studentId);
        StudentDTO sDTO = new StudentDTO(stu.getFirstName(),stu.getLastName(),stu.getEmail());
        if(stu == null){
            logger.error("Can't find student with id " + studentId);
        }
        logger.debug("Get one student with id "+studentId);
        return new ResponseEntity<>(sDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        student.setId(0);//enforce updating
        Student stu = studentService.save(student);
        logger.debug("Create a new student");
        return new ResponseEntity<>(stu, HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable int studentId){
        if(studentService.getStudentById(studentId)!=null){
            student.setId(studentId);
            Student stu = studentService.save(student);
            logger.debug("Update student with id "+ studentId);
            return new ResponseEntity<>(stu, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Can't find student with id " + studentId, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteById(@PathVariable int studentId){
        Student theS = studentService.getStudentById(studentId);
        if(theS == null){
            logger.debug("Can't find student with id " + studentId);
            return  new ResponseEntity<>("Can't find student with id " + studentId, HttpStatus.BAD_REQUEST);
        }
        studentService.deleteStudentById(studentId);
        logger.debug("Delete student with id " + studentId);
        return new ResponseEntity<>("Delete student with id " + theS.getId(), HttpStatus.OK);
    }

    @GetMapping("/teacher_student/{studentId}")
    public ResponseEntity<?> getTeacherId(@PathVariable int studentId){

        List<Integer> tIds = studentService.findTeacherId(studentId);

        if(tIds == null){
            logger.debug("Can't find teacher id for student with id " + studentId);
            return new ResponseEntity<>("Can't find teacher id for student with id " + studentId, HttpStatus.OK);
        }else{
            logger.debug("Get teachers' id for student with id " + studentId);
            return new ResponseEntity<>(tIds, HttpStatus.OK);
        }
    }
}
