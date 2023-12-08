package com.weone.demoemployeeportal.repo.impl;

import com.weone.demoemployeeportal.model.Employee;
import com.weone.demoemployeeportal.repo.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unused")
    public List<Employee> getAllManagers(){
        Query query = entityManager.createQuery("FROM Employee e where e.is_manager=:param");
        query.setParameter("param",true);

        return (List<Employee>) query.getResultList();
    }
}
