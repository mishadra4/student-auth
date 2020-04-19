package com.md.sa.dao.impl;

import com.md.sa.dao.GroupDao;
import com.md.sa.model.Groups;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Groups groups){
        entityManager.persist(groups);
    }

    @Override
    public List<Groups> getAllGroups() {
        String query = "from Groups";
        TypedQuery<Groups> typedQuery = entityManager.createQuery(query, Groups.class);
        return typedQuery.getResultList();
    }

    @Override
    public Groups getGroup(String name) {
        String query= "from Groups where name = ?1";
        TypedQuery<Groups> typedQuery = entityManager.createQuery(query, Groups.class);
        typedQuery.setParameter(1, name);
        return typedQuery.getSingleResult();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
