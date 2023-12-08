package com.weone.demoemployeeportal.service;

import com.weone.demoemployeeportal.model.Employee;
import com.weone.demoemployeeportal.repo.EmployeeRepository;
import com.weone.demoemployeeportal.repo.impl.EmployeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void addNewEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee updateExistingEmployee(Employee employee) throws Exception {
        Employee db_employee;
        if(employeeRepository.findById(employee.getEmployee_id()).isPresent()) {
            db_employee = employeeRepository.findById(employee.getEmployee_id()).get();
            System.out.println(db_employee);
            db_employee.setFirst_name(employee.getFirst_name());
            db_employee.setLast_name(employee.getLast_name());
            db_employee.setDob(employee.getDob());
            db_employee.setSalary(employee.getSalary());
            db_employee.setIs_manager(employee.getIs_manager());
            db_employee.setDepartment(employee.getDepartment());
            db_employee.setManager_id(employee.getManager_id());
            employeeRepository.save(db_employee);
        }else{
            throw new Exception("Employee Does not exist. Please check employee id");
        }

        return db_employee;

    }
}
