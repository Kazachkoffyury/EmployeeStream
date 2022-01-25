package ru.skypro.employeestream.Service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.DepartmentService;
import ru.skypro.employeestream.Service.EmployeeService;
import ru.skypro.employeestream.exception.EmployeeNotFound;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceimpl implements DepartmentService {
    private  final EmployeeService employeeService;

    public DepartmentServiceimpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMinSalary(Integer department) {
        Optional<Employee> employeeMinSalary = employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment()== department)
                .min(Comparator.comparingInt(Employee::getSalary));
        if (employeeMinSalary.isPresent()) {
            return employeeMinSalary.get();
        }
        throw new EmployeeNotFound();
    }

    @Override
    public Employee getMaxSalary(Integer department) {
        Optional<Employee> employeeMaxSalary = employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment()== department)
                .max(Comparator.comparingInt(Employee::getSalary));
        if (employeeMaxSalary.isPresent()) {
            return employeeMaxSalary.get();
        }
     throw new EmployeeNotFound();
    }

    @Override
    public List<Employee> getAllEmployees(Integer department) {
        if(department == null) {
            return employeeService.getEmployees().stream()
                    .sorted(Comparator.comparingInt(Employee::getDepartment))
                    .collect(Collectors.toList());
        }

        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment()== department)
                .collect(Collectors.toList());
    }
}
