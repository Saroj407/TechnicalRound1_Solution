package com.imaginnovate.student.studentpocapplication.service;

import com.imaginnovate.student.studentpocapplication.exception.MyCustomException;
import com.imaginnovate.student.studentpocapplication.pojo.Student;
import com.imaginnovate.student.studentpocapplication.repository.StudentRepository;
import com.imaginnovate.student.studentpocapplication.requestpojo.RequestPojo;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Service
public class Service {

  @Autowired
  private StudentRepository studentRepository;

  public String insertStudent(@RequestBody RequestPojo requestPojo) throws MyCustomException {
    Student student = new Student();

    String fName = requestPojo.getFirstName();
    String lName = requestPojo.getLastName();
    Date dob = requestPojo.getDob();
    String section = requestPojo.getSection();
    String gender = requestPojo.getGender();
    int m1 = requestPojo.getMarks1();
    int m2 = requestPojo.getMarks2();
    int m3 = requestPojo.getMarks3();

    if ((m1 < 0 || m1 > 100) || (m2 < 0 || m2 > 100) || (m3 < 0
        || m3 > 100)) {
      throw new MyCustomException(
          "valid range for mark is 0 to 100. ");
    }

    int msum = m1 + m2 + m3;
    float avg = msum / 3;
    String result = "";
    if (avg < 35.0) {
      result = "Fail";
    }
    else {
      result = "Pass";
    }

    if (fName.length() < 3 || lName.length() < 3) {
      throw new MyCustomException("Length of ftName and lName should be 3 characters");
    }
    if (dob.toString().isEmpty()) {
      throw new MyCustomException("DOB field can't be null");
    }

    LocalDate curDate = LocalDate.now();
    LocalDate tDob = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int age = Period.between(tDob, curDate).getYears();

    if (age < 15 || age > 20) {
      throw new MyCustomException(
          "Age should be greater than 15 year and less than or equal to 20 years.");
    }

    if (!section.equals("A") && !section.equals("B") && !section.equals("C")) {
      throw new MyCustomException(
          "Invalid Section.");
    }
    if (!gender.equals("M") && !gender.equals("F")) {
      throw new MyCustomException(
          "Invalid Gender");
    }

    student.setFirstName(fName);
    student.setLastName(lName);
    student.setDob(dob);
    student.setSection(section);
    student.setGender(gender);
    student.setMarks1(m1);
    student.setMarks2(m2);
    student.setMarks3(m3);
    student.setTotal(msum);
    student.setAverage(avg);
    student.setResult(result);
    studentRepository.save(student);
    return "Student details has been added in the data base.";
  }

  public Optional<Student> getStudenById(Long id){
    return studentRepository.findById(id);
  }

  public List<Student> retriveAllStudents() {
    return studentRepository.findAll();
  }

  public void fetchStudenById(long id, int marks1, int marks2, int marks3) throws MyCustomException {
    if ((marks1 < 0 || marks1 > 100) || (marks2 < 0 || marks2 > 100) || (marks3 < 0
        || marks3 > 100)) {
      throw new MyCustomException(
          "valid range for mark is 0 to 100. ");
    }
    int total = marks1 + marks3 + marks2;
    float avg = total / 3;
    String result = "";
    if (avg < 35.0) {
      result = "Fail";
    }
    else {
      result = "Pass";
    }
    Student student = studentRepository.findById(id).orElse(null);
    student.setMarks3(marks3);
    student.setMarks2(marks2);
    student.setMarks1(marks1);
    student.setTotal(total);
    student.setAverage(avg);
    student.setResult(result);
    studentRepository.save(student);

  }
}
