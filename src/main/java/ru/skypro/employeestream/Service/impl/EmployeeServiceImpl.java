package ru.skypro.employeestream.Service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.EmployeeService;
import ru.skypro.employeestream.exception.DuplicateEmployee;
import ru.skypro.employeestream.exception.EmployeeNotFound;
import ru.skypro.employeestream.exception.WrongEmployeeNameException;


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

        }
        else throw new WrongEmployeeNameException();

        if(!employees.containsKey(getKeyString(firstName,lastName))) {
            Employee employee = new Employee(StringUtils.capitalize(firstName),StringUtils.capitalize(lastName),department,salary);
            employees.put(employee.getFullName(), employee);
            return employee;
        }

        else throw new DuplicateEmployee();
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
        if (employees.containsKey(getKeyString(firstName,lastName))) {
            return  employees.get(getKeyString(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName)));
        }
        throw  new EmployeeNotFound();

    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String getKeyString(String firstName, String lastName) {
        return  StringUtils.capitalize(firstName)+" "+StringUtils.capitalize(lastName);
    }

    public boolean isNumberString(String str) {
        if (StringUtils.isAlpha(str)){
            return true;
        }
       else throw new WrongEmployeeNameException();

    }
}
