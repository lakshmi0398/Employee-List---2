// Write your code here
package com.example.employee.repository;

import java.util.*;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeH2Service;

public interface EmployeeRepository {
    ArrayList<Employee> getEmployees();

    Employee getEmployeeById(int employeeId);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int employeeId, Employee employee);

    void deleteEmployee(int employeeId);

}