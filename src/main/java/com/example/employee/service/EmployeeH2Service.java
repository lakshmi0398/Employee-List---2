/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 * 
 */

// Write your code here
package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeRowMapper;
import com.example.employee.repository.EmployeeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import java.lang.*;

// Do not modify the below code
@Service
public class EmployeeH2Service implements EmployeeRepository {

    @Autowired
    public JdbcTemplate db;

    @Override
    public ArrayList<Employee> getEmployees() {
        Collection<Employee> employeesCollection = db.query("select *from employeeList", new EmployeeRowMapper());
        ArrayList<Employee> employees = new ArrayList<>(employeesCollection);

        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {

        try {
            Employee employee = db.queryForObject("select * from employeeList where employeeId=?",
                    new EmployeeRowMapper(), employeeId);
            return employee;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Employee addEmployee(Employee employee) {
        db.update("insert into employeeList (employeeName,email,department) values(?,?,?)", employee.getEmployeeName(),
                employee.getEmail(), employee.getDepartment());
        Employee saveEmployee = db.queryForObject(
                "select * from employeeList where employeeName=? and email=?and department=?  ",
                new EmployeeRowMapper(), employee.getEmployeeName(), employee.getEmail(), employee.getDepartment());
        return saveEmployee;
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
  

        if (employee.getEmployeeName() != null) {
            db.update("update employeeList set employeeName=? where employeeId=? ", employee.getEmployeeName(),
                    employeeId);
            
        }

        if (employee.getDepartment() != null) {
            db.update("update employeeList set department=?  where employeeId=? ", employee.getDepartment(),
                    employeeId);
        }

        if (employee.getEmail() != null) {
            db.update("update employeeList set email=?  where employeeId=? ", employee.getEmail(), employeeId);
        }

        return getEmployeeById(employeeId);
    }

    @Override
    public void deleteEmployee(int employeeId) {

        db.update("delete from employeeList where employeeId=? ", employeeId);
    }
}
