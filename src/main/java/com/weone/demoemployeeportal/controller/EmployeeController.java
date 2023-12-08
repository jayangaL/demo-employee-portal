package com.weone.demoemployeeportal.controller;

import com.weone.demoemployeeportal.model.Employee;
import com.weone.demoemployeeportal.repo.impl.EmployeeRepositoryImpl;
import com.weone.demoemployeeportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController{
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepositoryImpl employeeRepositoryImpl;

    @PostMapping(value = "/save")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
        try {
            employeeService.addNewEmployee(employee);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee employee1 = new Employee();
        try {
            employee1 = employeeService.updateExistingEmployee(employee);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(employee1, HttpStatus.OK);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees =employeeService.getAllEmployees();

        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getManager_id()!=0){
                Optional<Employee> manager =employeeService.getEmployeeById(employees.get(i).getManager_id());
                if(manager.isPresent()) {
                    employees.get(i).setManager(manager.get().getFirst_name()+" "+ manager.get().getLast_name());
                }
            }
        }

        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @RequestMapping(value = "/managers",method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllManagers(){
        List<Employee> managers = employeeRepositoryImpl.getAllManagers();

        return new ResponseEntity<>(managers,HttpStatus.OK);
    }

    @RequestMapping(value = "/{emp_id}",method = RequestMethod.GET)
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long emp_id){
        Optional<Employee> employee = employeeService.getEmployeeById(emp_id);

        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}
