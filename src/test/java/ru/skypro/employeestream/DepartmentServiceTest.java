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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        employees.add(new Employee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY));
        employees.add(new Employee(FIRSTNAME1,LASTNAME1,DEPARTMENT1,SALARY1));
        Mockito.when(employeeServiceMock.getEmployees()).thenReturn(employees);

    }
    @Test
    void shouldGetEmployeeMinSalary() {
        Employee actual = out.getMinSalary(DEPARTMENT);
        Employee expected = new Employee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        assertEquals(expected,actual);
    }





}
