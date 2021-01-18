package com.f1soft.college.dao.impl;


import com.f1soft.college.dao.StudentDao;
import com.f1soft.college.entity.Student;
import lombok.extern.slf4j.Slf4j;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Stateless
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext(unitName = "f1soft")
    private EntityManager em;

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Student.findAll");
            students = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            //log.error("Exception", e);
        }
        return students;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student student =  null;
        try {
            Query query = em.createNamedQuery("Student.findById", Student.class);
            query.setParameter("id", id);
            student = (Student) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            //log.error("Exception", e);
        }
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> findByName(String name) {
        Student student = null;
        try{
            Query query = em.createNamedQuery("Student.findByName", Student.class);
            query.setParameter("name", name);
            student = (Student) query.getSingleResult();
        } catch (Exception e) {
            System.out.printf("e" , e);
        }
        return Optional.ofNullable(student);
    }

    @Override
    public Student save(Student student) {
        if(student.getId() == 0){
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    @Override
    public void delete(Student student) {
        if (em.contains(student)) {
            em.remove(student);
        } else {
            em.remove(em.getReference(Student.class, student.getId()));
        }

    }

    @Override
    public Student updateById(Long id, Student student) {
        em.merge(student);
        return student;
    }
}
