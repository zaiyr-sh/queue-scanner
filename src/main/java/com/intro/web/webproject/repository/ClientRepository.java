package com.intro.web.webproject.repository;

import com.intro.web.webproject.entity.ClientEntity;
import com.intro.web.webproject.entity.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    List<ClientEntity> findByToken(String Token);
}
