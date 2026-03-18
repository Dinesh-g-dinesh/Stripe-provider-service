package com.hulkhiretech.payments.controller;


import com.hulkhiretech.payments.pojo.CreateEmpReq;
import com.hulkhiretech.payments.pojo.EmployeeResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employees")
public class EmployeeController {

    @PostMapping
    public EmployeeResponce CreateEmployee(@RequestBody  CreateEmpReq createEmpReq) {
        log.info("Inside CreateEmployee method of EmployeeController with request body: {}", createEmpReq);

        EmployeeResponce employeeResponce = new EmployeeResponce();
        employeeResponce.setId("emp12345"); //TODO Dummy Response

        log.info("Created EmployeeResponce: {}", employeeResponce);

        return employeeResponce;
    }

    @GetMapping("/{empId}")
    public EmployeeResponce getEmployee(@PathVariable String empId) {
        log.info("Inside getEmployee method of EmployeeController with empId: {}", empId);

        EmployeeResponce employeeResponce = new EmployeeResponce();
        employeeResponce.setId(empId); //TODO Dummy Response

        log.info("Retrieved EmployeeResponce: {}", employeeResponce);

        return employeeResponce;
    }

    @GetMapping
    public List<EmployeeResponce> getAllEmployee() {
        log.info("Inside getALlEmployee method of EmployeeController");

        EmployeeResponce employeeResponce = new EmployeeResponce();
        employeeResponce.setId("Id1"); //TODO Dummy Response

        log.info("Retrieved EmployeeResponce: {}", employeeResponce);
        List<EmployeeResponce> empResList = List.of(employeeResponce);
        return empResList;
    }
}
