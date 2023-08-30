package com.imaginnovate.student.studentpocapplication.controller;

import com.imaginnovate.student.studentpocapplication.pojo.Student;
import com.imaginnovate.student.studentpocapplication.requestpojo.RequestPojo;
import com.imaginnovate.student.studentpocapplication.service.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class Controller {

  @Autowired
  private Service service;

  @PostMapping(value ="/insert", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> insertStudentt(@RequestBody RequestPojo requestPojo){
    String response = "";
    try {
      response = service.insertStudent(requestPojo);
    }
    catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @GetMapping("/students")
  public List<Student> getAllStudents(){
    return service.retriveAllStudents();
  }

  @GetMapping("/student")
  public Optional<Student> retriveStudentById(@RequestParam long id){
    return service.getStudenById(id);
  }

  @PutMapping("/updatestudent")
  public String updateStudentDetails(@RequestParam long id, @RequestParam int mark1,@RequestParam int mark2,@RequestParam int mark3){
    try{
      service.fetchStudenById(id,mark1,mark2,mark3);
    }catch (Exception e){
      e.printStackTrace();
    }
    return "Student details has been updated in the data base. ";
  }
}
