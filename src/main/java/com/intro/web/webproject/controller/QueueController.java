package com.intro.web.webproject.controller;

import com.intro.web.webproject.entity.QueueEntity;
import com.intro.web.webproject.exeption.RecordNotFoundException;
import com.intro.web.webproject.repository.QueueRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;


    @GetMapping("/{id}")
    @ApiOperation(value = "findQueueById")
    public QueueEntity findQueueById(@PathVariable("id") Integer id) throws Exception {
        return queueRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @GetMapping("/all")
    @ApiOperation(value = "findAllQueue")
    public List<QueueEntity> findAllQueue() {
        return queueRepository.findAll();
    }

    @PostMapping("/")
    @ApiOperation(value = "createQueue")
    public QueueEntity create(@RequestBody QueueEntity queueEntity) {
        return queueRepository.save(queueEntity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "updateQueueById")
    public QueueEntity updateQueue(@PathVariable("id") @RequestBody QueueEntity queueEntity) {
        return queueRepository.save(queueEntity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteQueueById")
    public void delete(@PathVariable("id") Integer id) {
        queueRepository.deleteById(id);
    }
}
