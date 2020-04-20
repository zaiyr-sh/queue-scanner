package com.intro.web.webproject.admin;

import com.intro.web.webproject.controller.ClientController;
import com.intro.web.webproject.controller.EmployeeController;
import com.intro.web.webproject.controller.QueueController;
import com.intro.web.webproject.entity.ClientEntity;
import com.intro.web.webproject.entity.EmployeeEntity;
import com.intro.web.webproject.entity.QueueEntity;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QueueAdminController {
    @Autowired
    QueueController queueController;

    @Autowired
    ClientController clientController;

    @Autowired
    EmployeeController employeeController;

    @GetMapping("/index")
    public String main(Map<String, Object> object) {
        List<QueueEntity> queueList = new ArrayList<>();
        for (QueueEntity queue : queueController.findAllQueue()) {
            queueList.add(new QueueEntity(queue.getId(), queue.getName()));
        }
        object.put("queues", queueList);
        return "main";
    }

    @GetMapping("/queue/{id}")
    public String queue(@PathVariable("id") Integer id, Map<String, Object> object) throws Exception {
        object.put("queues", queueController.findQueueById(id));
        object.put("clients", queueController.findQueueById(id).getClients());
        object.put("employees", employeeController.findAllByQueueId(id));
        return "queue";
    }

    @PostMapping("/queue/{id}")
    public String delete(@PathVariable("id") Integer queueId,
                         @RequestParam Integer id,
                         Map<String, Object> object) throws Exception {
        clientController.deleteClientById(id);
        return queue(queueId, object);
    }


    @GetMapping("/employee")
    public String employee(Map<String, Object> object) {
        object.put("employees", employeeController.findAllEmployees());
        object.put("queues", queueController.findAllQueue());
        object.put("clients", clientController.findAllClient());
        return "employee";
    }

    @PostMapping("/employee")
    public String post(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String password,
                       @RequestParam Integer queueId,
                       Map<String, Object> object) {
        employeeController.create(new EmployeeEntity(name, email, password, queueId));

        return employee(object);
    }


    @PostMapping("/employeePut")
    public String put(@RequestParam Integer idPut,
                      @RequestParam String namePut,
                      @RequestParam String emailPut,
                      @RequestParam String passwordPut,
                      @RequestParam Integer queueIdPut,
                      Map<String, Object> object) {
        employeeController.updateEmployee(idPut, new EmployeeEntity(namePut, emailPut, passwordPut, queueIdPut));

        return employee(object);
    }

    @PostMapping("/index")
    public String postQueue(@RequestParam String name, Map<String, Object> object) {
        queueController.create(new QueueEntity(name));
        return main(object);
    }


}
