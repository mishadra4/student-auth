package com.md.sa.repository;

import com.md.sa.model.Lab;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends CrudRepository<Lab, Integer> {
}
