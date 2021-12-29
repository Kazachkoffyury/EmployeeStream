package ru.skypro.employeestream.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.impl.DepartmentServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServiceimpl departmentServiceimpl;

    public DepartmentController(DepartmentServiceimpl departmentServiceimpl) {
        this.departmentServiceimpl = departmentServiceimpl;
    }
    @RequestMapping("/min-salary")
    public Employee getMinSalary(@RequestParam Integer department){
        return departmentServiceimpl.getMinSalary(department);
    }

    @RequestMapping("/max-salary")
    public  Employee getMaxSalary(@RequestParam Integer department) {
        return departmentServiceimpl.getMaxSalary(department);
    }

    @RequestMapping("/get-all")
    public List<Employee> getAllEmployeesInDepartment(@RequestParam(required = false) Integer department) {
        return departmentServiceimpl.getAllEmployees(department);
    }


}
