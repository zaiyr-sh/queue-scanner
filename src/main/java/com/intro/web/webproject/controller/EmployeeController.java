package com.intro.web.webproject.controller;


import com.intro.web.webproject.entity.EmployeeEntity;
import com.intro.web.webproject.entity.QueueEntity;
import com.intro.web.webproject.exeption.RecordNotFoundException;
import com.intro.web.webproject.repository.EmployeeRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    @ApiOperation(value = "findEmployeeById")
    public EmployeeEntity findEmployeeById(@PathVariable("id") Integer id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @GetMapping("/all")
    @ApiOperation(value = "findAllEmployees")
    public List<EmployeeEntity> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/queue")
    @ApiOperation(value = "findAllEmployeesByQueue")
    public List<EmployeeEntity> findAllByQueueId(@RequestBody Integer id) {
        return employeeRepository.findAllByQueueId(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "createEmployees")
    public EmployeeEntity create(@RequestBody EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "updateEmployeeById")
    public EmployeeEntity updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeEntity employeeEntity) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setUsername(employeeEntity.getUsername());
                    employee.setEmail(employeeEntity.getEmail());
                    employee.setPassword(employeeEntity.getPassword());
                    employee.setQueueId(employeeEntity.getQueueId());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    employeeEntity.setId(id);
                    return employeeRepository.save(employeeEntity);
                });
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteEmployeeById")
    public void delete(@PathVariable("id") Integer id) {
        employeeRepository.deleteById(id);
    }
}
