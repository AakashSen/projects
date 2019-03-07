package com.epam.junit.student.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="student_id")
	private Long studentId;
	@Column(name="student_name")
	private String studentName;
	@Column(name="student_marks")
	private Long studentMarks;
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Long getStudentMarks() {
		return studentMarks;
	}
	public void setStudentMarks(Long studentMarks) {
		this.studentMarks = studentMarks;
	}	
	
}
