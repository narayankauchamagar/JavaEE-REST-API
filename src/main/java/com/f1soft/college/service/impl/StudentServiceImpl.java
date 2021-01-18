package com.f1soft.college.service.impl;

import com.f1soft.college.dao.StudentDao;
import com.f1soft.college.entity.Student;
import com.f1soft.college.service.StudentService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDao studentDao;

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public Optional<Student> findByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public Student saveOrUpdate(Student student) {
       return studentDao.save(student);
    }

    @Override
    public void delete(Long id) {
       Optional<Student>  student = studentDao.findById(id);
       if(student.isPresent()) {
           studentDao.delete(student.get());
       }
    }

}
