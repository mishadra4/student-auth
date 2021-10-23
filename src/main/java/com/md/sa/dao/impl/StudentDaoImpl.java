package com.md.sa.dao.impl;

import com.md.sa.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.md.sa.dao.StudentDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Student student){
        entityManager.merge(student);
    }

    @Override
    public boolean enrollStudent(String username) {
        String query= "update student set id_lecture=? where username = ?1";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter(1, "0001");
        nativeQuery.setParameter(2, username);
        int result = nativeQuery.executeUpdate();
        return result > 0; // result show how many rows was updated.
    }

    @Override
    public Student getStudent(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student getStudent(String username) {
        String query= "from student where username = ?1";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Student> getAllStudents(){
        String query = "from Student order by ID";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }

    public boolean deleteStudent(int idStudent) {
        System.out.println("ORMExample deleteUser is called");

        String qlString = "delete from student where idStudent=?";
        Query query = entityManager.createNativeQuery(qlString);
        query.setParameter(1, idStudent);
        int result = query.executeUpdate();

        return result > 0;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
