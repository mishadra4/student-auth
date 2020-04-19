package com.md.sa.service.impl;

import com.md.sa.dao.GroupDao;
import com.md.sa.model.Groups;
import com.md.sa.model.Student;
import com.md.sa.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Groups> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public Groups getGroup(String name) {
        return groupDao.getGroup(name);
    }

    @Override
    public void save(Groups groups) {
        getGroupDao().save(groups);
    }

    @Override
    public void saveAll(List<Groups> groups) {
        groups.forEach(this::save);
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
