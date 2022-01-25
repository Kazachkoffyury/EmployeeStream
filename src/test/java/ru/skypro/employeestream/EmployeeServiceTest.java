package ru.skypro.employeestream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.employeestream.Data.Employee;
import ru.skypro.employeestream.Service.EmployeeService;
import ru.skypro.employeestream.Service.impl.EmployeeServiceImpl;
import ru.skypro.employeestream.exception.DuplicateEmployee;
import ru.skypro.employeestream.EmployeeTestData;
import ru.skypro.employeestream.exception.EmployeeNotFound;
import ru.skypro.employeestream.exception.WrongEmployeeNameException;

import java.util.*;
import java.util.concurrent.Callable;

import static java.util.List.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.employeestream.EmployeeTestData.*;

public class EmployeeServiceTest {

    private EmployeeService out;

    @BeforeEach
    void SetUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddEmployee() {
        Employee actualEmployee = out.addEmployee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        Employee expectedEmployee = new Employee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        assertEquals(actualEmployee, expectedEmployee);
    }

    @Test
    void shouldAddWrongArguments() {
        assertThrows(WrongEmployeeNameException.class, () -> out.addEmployee(FIRSTNAMEWRONG, LASTNAME, DEPARTMENT, SALARY));
    }

    @Test
    void shouldAddEmployeeDuplicate() {
        out.addEmployee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        assertThrows(DuplicateEmployee.class, () -> out.addEmployee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY));
    }

    @Test
    void shouldRemoveEmployee() {
        out.addEmployee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        Employee actualEmployee = out.removeEmployee(FIRSTNAME, LASTNAME);
        Employee expectedEmployee = new Employee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        assertEquals(actualEmployee, expectedEmployee);
    }

    @Test
    void shouldRemoveEmployeeNotFound() {
        assertThrows(EmployeeNotFound.class, () -> out.removeEmployee(FIRSTNAME, LASTNAME));
    }

    @Test
    void shouldGetEmployee() {
        out.addEmployee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        Employee actualEmployee = out.getEmployee(FIRSTNAME, LASTNAME);
        Employee expectedEmployee = new Employee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        assertEquals(actualEmployee, expectedEmployee);
    }

    @Test
    void shouldGetEmployeeNotFound() {
        assertThrows(EmployeeNotFound.class, () -> out.getEmployee(FIRSTNAME, LASTNAME));
    }

    @Test
    void shouldGetEmployees() {
        out.addEmployee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY);
        out.addEmployee(FIRSTNAME1, LASTNAME1, DEPARTMENT1, SALARY1);
        Collection<Employee> actual = out.getEmployees();
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(FIRSTNAME, LASTNAME, DEPARTMENT, SALARY));
        expected.add(new Employee(FIRSTNAME1, LASTNAME1, DEPARTMENT1, SALARY1));
        assertEquals(actual.size(), expected.size());
        assertTrue(expected.containsAll(actual));
    }

}
