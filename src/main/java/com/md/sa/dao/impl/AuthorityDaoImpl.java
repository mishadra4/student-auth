package com.md.sa.dao.impl;

import com.md.sa.dao.AuthorityDao;
import com.md.sa.model.Authority;
import com.md.sa.model.Groups;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class AuthorityDaoImpl implements AuthorityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Authority authority) {
        entityManager.merge(authority);
    }

    @Override
    public Authority getAuthority(String code) {
        String query= "from Authority where authority = ?1";
        TypedQuery<Authority> typedQuery = entityManager.createQuery(query, Authority.class);
        typedQuery.setParameter(1, code);
        return typedQuery.getSingleResult();
    }
}
