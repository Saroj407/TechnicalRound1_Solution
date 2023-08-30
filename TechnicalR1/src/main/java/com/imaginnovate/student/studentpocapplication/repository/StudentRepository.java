package com.imaginnovate.student.studentpocapplication.repository;

import com.imaginnovate.student.studentpocapplication.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
