package com.md.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.md.sa.model.StudentPassInOrder;

@Repository
public interface StudentPassInOrderRepository extends JpaRepository<StudentPassInOrder, Integer> {

    @Query("SELECT sp FROM StudentPassInOrder sp WHERE sp.lab.labId = :labId")
    List<StudentPassInOrder> findAllByLabId(@Param("labId") Integer labId);

    @Query("SELECT sp FROM StudentPassInOrder sp WHERE sp.lab.labId = :labId AND sp.user.username = :username")
    StudentPassInOrder findOneByLabAndUser(@Param("labId") Integer labId, @Param("username") String username);
}
