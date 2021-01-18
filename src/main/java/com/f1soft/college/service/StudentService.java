package com.f1soft.college.service;

import com.f1soft.college.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAll();
    Optional<Student> findById(Long id );
    Optional<Student> findByName(String name);
    Student saveOrUpdate(Student student);
    void delete(Long id);
}
