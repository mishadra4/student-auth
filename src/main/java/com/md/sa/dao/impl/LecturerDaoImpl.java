package com.md.sa.dao.impl;

import com.md.sa.dao.LecturerDao;
import com.md.sa.model.Lecturer;
import com.md.sa.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class LecturerDaoImpl implements LecturerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Lecturer getLecturer(String username) {
        String query= "from lecturer where username = ?1";
        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(query, Lecturer.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getSingleResult();
    }

    public List<Lecturer> getLecturers(){
        String query= "from lecturer order by username";
        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(query, Lecturer.class);
        return typedQuery.getResultList();
    }

    @Override
    public void save(Lecturer lecturer) {
        entityManager.merge(lecturer);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
