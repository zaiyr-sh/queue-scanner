package com.intro.web.webproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "create_time")
    private String currentTime;

    @Column(name = "in_service", nullable = false)
    private boolean inService;

    @Column(name = "queue_id")
    private Integer queueId;

    public ClientEntity() {
    }

    public ClientEntity(int id, String token, String currentTime, boolean inService, Integer queueId) {
        this.id = id;
        this.token = token;
        this.currentTime = currentTime;
        this.inService = inService;
        this.queueId = queueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return token;
    }

    public void setIpAddress(String token) {
        this.token = token;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isInService() {
        return inService;
    }

    public void setInService(boolean inService) {
        this.inService = inService;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }
}
