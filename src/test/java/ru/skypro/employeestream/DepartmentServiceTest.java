package ru.skypro.employeestream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.impl.DepartmentServiceimpl;
import ru.skypro.employeestream.Service.impl.EmployeeServiceImpl;
import ru.skypro.employeestream.exception.EmployeeNotFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.employeestream.EmployeeTestData.*;
import static ru.skypro.employeestream.EmployeeTestData.SALARY;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceimpl out;

    @BeforeEach
    void setUp () {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(FIRSTNAME, LASTNAME, SALARY, DEPARTMENT));
        employees.add(new Employee(FIRSTNAME1,LASTNAME1,SALARY1,DEPARTMENT1));
        employees.add(new Employee(FIRSTNAME3,LASTNAME3,SALARY3,DEPARTMENT3));
        Mockito.when(employeeServiceMock.getEmployees()).thenReturn(employees);

    }
    @Test
    void shouldGetEmployeeMinSalary() {
        Employee actual = out.getMinSalary(DEPARTMENT);
        Employee expected = new Employee(FIRSTNAME, LASTNAME, SALARY, DEPARTMENT);
        assertEquals(expected,actual);
    }

    @Test
    void shouldGetEmployeeMaxSalary() {
        Employee actual = out.getMaxSalary(DEPARTMENT);
        Employee expected = new Employee(FIRSTNAME1, LASTNAME1, SALARY1, DEPARTMENT1);
        assertEquals(actual,expected);
    }

    @Test
    void shouldGetAllEmployees() {
        List<Employee> actual = out.getAllEmployees(DEPARTMENT3);
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(FIRSTNAME3,LASTNAME3,SALARY3,DEPARTMENT3));
        assertIterableEquals(actual,expected);


    }





}
