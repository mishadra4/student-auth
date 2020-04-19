package com.md.sa.dao.impl;

import com.md.sa.dao.LabDao;
import com.md.sa.model.Groups;
import com.md.sa.model.Lab;
import com.md.sa.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LabDaoImpl implements LabDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createLab() {
        Lab lab = new Lab();
        lab.setLabId(3);
        entityManager.persist(lab);
    }

    @Override
    public Lab getLab(Integer id) {
        Student student = new Student();
        student.setFirstName("Mykhailo");
        student.setLastName("Drach");
        student.setGroups(new Groups());
        Lab lab = new Lab();
        lab.setLabId(1);
        return lab;
    }

    @Override
    public List<Lab> getLabs(String subjectId) {
        String query = "select l from lab as l left join l.subject as s left join l.group as g where s.name=?1";
        TypedQuery<Lab> typedQuery = entityManager.createQuery(query, Lab.class);
        typedQuery.setParameter(1, subjectId);
        return typedQuery.getResultList();
    }

    @Override
    public void saveLab(Lab lab) {
        entityManager.persist(lab);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
