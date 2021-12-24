package ru.skypro.employeestream.Service;

import ru.skypro.employeestream.Data.Employee;

import java.util.List;

public interface DepartmentService {
    Employee getMinSalary(Integer department);
    Employee getMaxSalary(Integer department);
    List<Employee> getAllEmployees(Integer department);

}
