package com.intro.web.webproject.repository;

import com.intro.web.webproject.entity.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity, Integer> {

}