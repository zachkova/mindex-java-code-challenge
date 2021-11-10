package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompensationServiceImplTest {
    @Autowired
    private CompensationService compensationService;

    @Mock
    private CompensationRepository compensationRepository;

    private Employee employee;
    private Compensation compensation;

    @Before
    public void setup() {
        employee = new Employee(
                "c0c2293d-16bd-4603-8e08-638a9d18b22c",
                "George",
                "Harrison",
                "Developer III",
                "Engineering",
                null);

        compensation = new Compensation(employee, 5000000, LocalDate.now());
    }

    @Test
    public void testCreate() {
        Compensation compensation1 = compensationService.create(compensation);
        assertNotNull(compensation1.getEmployee().getEmployeeId());
        assertEmployeeEquivalence(employee, compensation1.getEmployee());
    }

    @Test
    public void testRead() {
        when(compensationRepository.findByEmployee_EmployeeId(anyString())).thenReturn(compensation);
        Compensation compensationRes = compensationService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        assertNotNull(compensationRes.getEmployee().getEmployeeId());
        assertEmployeeEquivalence(employee, compensationRes.getEmployee());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
