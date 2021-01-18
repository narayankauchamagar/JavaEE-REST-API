package com.f1soft.college.dao;

import com.f1soft.college.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    List<Student> getAll();
    Optional<Student> findById(Long id);
    Optional<Student> findByName(String name);
    Student save(Student student);
    void delete(Student student);
    Student updateById(Long id, Student student);

}
