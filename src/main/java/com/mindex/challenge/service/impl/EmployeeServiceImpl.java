package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    public ReportingStructure getReportingStructure(String id) {

        LOG.debug("Looking for employee with id [{if}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        List<Employee> totalEmployees = new ArrayList();
        totalEmployees.add(employee);

        int totalNumReports = numDirReporters(totalEmployees,0);

        return new ReportingStructure(totalNumReports, employee);
    }


    public int numDirReporters (List<Employee> employees, int numOfReports){

        List<Employee> dirReports = new ArrayList<>();

        for(int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getDirectReports() != null) {
                List<Employee> reportees = employees.get(i).getDirectReports();
                for (int y = 0; y < reportees.size(); y++) {
                    dirReports.add(employeeRepository.findByEmployeeId(reportees.get(y).getEmployeeId()));
                }
                numOfReports += dirReports.size();
            }
        }

        int totalNumOfReports = numOfReports;

        if(dirReports.size() > 0){
            return numDirReporters(dirReports, totalNumOfReports);
        }
        return totalNumOfReports;
    }
}
