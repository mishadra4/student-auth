package com.md.sa.dao;

import com.md.sa.model.Authority;

public interface AuthorityDao {

    void save(Authority authority);

    Authority getAuthority(String code);

}
