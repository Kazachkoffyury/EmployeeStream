package ru.skypro.employeestream.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private String firstName;
    private String lastName;
    private int salary;
    private int department;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/post")
    public Employee addEmployee(@RequestParam String firstName,@RequestParam String lastName,@RequestParam(value = "salary") int salary,@RequestParam(value="department") int department) {

        return employeeService.addEmployee(firstName,lastName,salary,department);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName,String lastName) {
        return employeeService.removeEmployee(firstName,lastName);
    }

    @GetMapping("/getAll")
    public Collection<Employee> getAll() {
        return employeeService.getEmployees();
    }
}
