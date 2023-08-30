package com.imaginnovate.student.studentpocapplication.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date dob;
  private String section;
  private String gender;
  private int marks1;
  private int marks2;
  private int marks3;
  private int total;
  private float average;
  private String result;
}
