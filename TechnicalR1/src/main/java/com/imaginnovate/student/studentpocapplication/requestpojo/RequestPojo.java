package com.imaginnovate.student.studentpocapplication.requestpojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RequestPojo {
  private String firstName;
  private String lastName;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date dob;
  private String section;
  private String gender;
  private int marks1;
  private int marks2;
  private int marks3;
}
