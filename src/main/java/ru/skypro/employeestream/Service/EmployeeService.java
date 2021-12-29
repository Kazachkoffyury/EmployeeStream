package ru.skypro.employeestream.Service;

import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.exception.DuplicateEmployee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName,String lastName,int department,int salary) throws DuplicateEmployee;
    Employee removeEmployee(String firstName,String lastName);
    Employee getEmployee(String firstName,String lastName);
    Collection<Employee> getEmployees();
}
