package ru.skypro.employeestream.Service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.EmployeeService;
import ru.skypro.employeestream.exception.DuplicateEmployee;
import ru.skypro.employeestream.exception.EmployeeNotFound;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String,Employee>  employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<String,Employee>();
    }
    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) throws DuplicateEmployee {

        if(!employees.containsKey(getKeyString(firstName,lastName))) {
            Employee employee = new Employee(firstName,lastName,department,salary);
            if(StringUtils.isAlpha(employee.getFullName())) {
                employee.setFirstName(StringUtils.capitalize(firstName));
                employee.setLastName(StringUtils.capitalize(lastName));
                employees.put(employee.getFullName(), employee);
                return employee;
            }
            throw  new EmployeeNotFound();
        }

        throw new DuplicateEmployee();

   }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if(employees.containsKey(getKeyString(firstName,lastName))){
            return employees.remove(getKeyString(firstName,lastName));
        }
        throw new EmployeeNotFound();

    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        return null;
    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String getKeyString(String firstName, String lastName) {
        return  firstName+" "+lastName;
    }
}
