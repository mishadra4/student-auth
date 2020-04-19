package com.md.sa.dao;

import com.md.sa.model.Groups;

import java.util.List;

public interface GroupDao {

    void save(Groups groups);

    List<Groups> getAllGroups();

    Groups getGroup(String name);
}
