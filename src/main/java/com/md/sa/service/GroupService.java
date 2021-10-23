package com.md.sa.service;

import com.md.sa.model.Groups;

import java.util.List;

public interface GroupService {

    List<Groups> getAllGroups();

    Groups getGroup(String name);

    void save(Groups groups);

    void saveAll(List<Groups> groups);
}