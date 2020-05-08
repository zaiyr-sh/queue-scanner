package com.intro.web.webproject.controller;

import com.intro.web.webproject.entity.ClientEntity;
import com.intro.web.webproject.entity.QueueEntity;
import com.intro.web.webproject.exeption.RecordNotFoundException;
import com.intro.web.webproject.repository.ClientRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/{id}")
    @ApiOperation(value = "findDefaultClient")
    public ClientEntity findClientById(@PathVariable("id") Integer id) throws Exception {
        return clientRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @GetMapping("/ip/{ipAddress}")
    @ApiOperation(value = "findAllClientByQueue")
    public List<ClientEntity> findAllClientByToken(@PathVariable("ipAddress") String token) {
        return clientRepository.findByToken(token);
    }

    @GetMapping("/all")
    @ApiOperation(value = "findAllClient")
    public List<ClientEntity> findAllClient() {
        return clientRepository.findAll();
    }

    @PostMapping("/")
    @ApiOperation(value = "createClient")
    public ClientEntity createClient(@RequestBody ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "updateClientById")
    public ClientEntity updateClientById(@PathVariable("id") Integer id, @RequestBody ClientEntity clientEntity) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setInService(clientEntity.isInService());
                    return clientRepository.save(client);
                })
                .orElseGet(() -> {
                    clientEntity.setId(id);
                    return clientRepository.save(clientEntity);
                });
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleteClientById")
    public void deleteClientById(@PathVariable("id") Integer id) {
        clientRepository.deleteById(id);
    }
}
