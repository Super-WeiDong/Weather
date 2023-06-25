package com.example.repository;



import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select teacher_id from student s join teacher_student ts on s.id = ts.student_id where s.id = :studentId", nativeQuery = true)
    List<Integer> findTeacherId(int studentId);
}
