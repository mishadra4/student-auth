package com.md.sa.dao.impl;

import com.md.sa.dao.UserDao;
import com.md.sa.model.Subject;
import com.md.sa.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(String username) {
        String query= "from User where username = ?1";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
