package com.md.sa.dao.impl;

import com.md.sa.dao.SubjectDao;
import com.md.sa.model.Lecture;
import com.md.sa.model.Subject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class SubjectDaoImpl implements SubjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Subject subject) {
        entityManager.merge(subject);
    }

    @Override
    public Subject getSubject(int id) {
        String query= "from Subject where id = ?1";
        TypedQuery<Subject> typedQuery = entityManager.createQuery(query, Subject.class);
        typedQuery.setParameter(1, id);
        return typedQuery.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Subject> getSubjects(String username) {
        String query= "from Subject where lecturer_username = ?1";
        TypedQuery<Subject> typedQuery = entityManager.createQuery(query, Subject.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getResultList();
    }

    @Override
    public Subject getSubject(String name) {
        String query= "from Subject where name = ?1";
        TypedQuery<Subject> typedQuery = entityManager.createQuery(query, Subject.class);
        typedQuery.setParameter(1, name);
        return typedQuery.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
