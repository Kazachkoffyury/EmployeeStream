package ru.skypro.employeestream.Service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.EmployeeService;
import ru.skypro.employeestream.exception.DuplicateEmployee;
import ru.skypro.employeestream.exception.EmployeeNotFound;
import ru.skypro.employeestream.exception.ErrorString;


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
        if (isNumberString(firstName) && isNumberString(lastName)) {
            if(!employees.containsKey(getKeyString(firstName,lastName))) {
                Employee employee = new Employee(firstName,lastName,department,salary);
                employee.setFirstName(StringUtils.capitalize(firstName));
                employee.setLastName(StringUtils.capitalize(lastName));
                employees.put(employee.getFullName(), employee);
                return employee;
                }

            throw new DuplicateEmployee();

        }
        throw  new ErrorString();
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
        if (isNumberString(firstName) && isNumberString(lastName)) {
            return  employees.get(getKeyString(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName)));
        }
        throw  new ErrorString();

    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String getKeyString(String firstName, String lastName) {
        return  firstName+" "+lastName;
    }

    public boolean isNumberString(String str) {
        if (StringUtils.isAlpha(str)){
            return true;
        }
       else throw new ErrorString();

    }
}
