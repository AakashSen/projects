package com.epam.junit.student.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.junit.student.entity.Student;
@Repository
public interface StudentRespository extends CrudRepository<Student,Long>{

}
