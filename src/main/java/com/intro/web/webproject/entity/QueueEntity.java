package com.intro.web.webproject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "queue")
public class QueueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "queue_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", referencedColumnName = "queue_id")
    private List<ClientEntity> clients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id", referencedColumnName = "queue_id")
    private List<EmployeeEntity> employees;

    public QueueEntity() {
    }

    public QueueEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public QueueEntity(String name) {
        this.name = name;
    }

    public QueueEntity(String name, List<ClientEntity> clients, List<EmployeeEntity> employees) {
        this.name = name;
        this.clients = clients;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClientEntity> getClients() {
        return clients;
    }

    public void setClients(List<ClientEntity> clients) {
        this.clients = clients;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
