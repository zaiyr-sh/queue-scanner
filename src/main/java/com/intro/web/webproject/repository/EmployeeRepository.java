package com.intro.web.webproject.repository;

import com.intro.web.webproject.entity.EmployeeEntity;
import com.intro.web.webproject.entity.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findAllByQueueId(Integer id);
}
